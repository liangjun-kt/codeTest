package com.upsider.invoices.demo.service

import com.upsider.invoices.demo.model.db.dataobject.UsersDO
import com.upsider.invoices.demo.model.request.CreateUserDTO
import com.upsider.invoices.demo.model.request.LoginDTO
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

/**
 * Auth Service, deals with authorization related problems
 */
@Service
class AuthService(val dbService: DBService, val cacheService: CacheService) {
    @Value("\${jwt.expire}")
    val expire: Long = 3600000
    val keypair = generateKeyPair()

    /**
     * Generate key pair for JWT signature
     * @return KeyPair
     */
    private fun generateKeyPair(): KeyPair {
        val generator = KeyPairGenerator.getInstance("RSA")
        generator.initialize(2048, SecureRandom())
        val keypair = generator.genKeyPair()
        return keypair
    }

    /**
     * Service level function for create a new user
     *
     * @param createUserDTO data object for creating a new user
     * @return null
     */
    fun createUser(createUserDTO: CreateUserDTO){
        val oldUser = dbService.getUserByMail(createUserDTO.email);
        if(null == oldUser){
            dbService.createUser(createUserDTO)
        }else{
            throw Exception("User already exist.");
        }
    }

    /**
     * Service level function to make user login
     *
     * @param loginDTO data object contains id and pw
     * @return token returns jwt access token.
     */
    fun loginUser(loginDTO: LoginDTO): String?{
        val user = dbService.getUserByMail(loginDTO.email);
        user?.let {
            val hash = MessageDigest.getInstance("MD5").digest(loginDTO.password.toByteArray(Charsets.UTF_8))
            val cal = Base64.getEncoder().encodeToString(hash);
            if (cal != user.password){
                throw Exception("User password un-match, please try again")
            }
            val token = generateToken(user);
            cacheService.saveForMilliseconds(token, user.id.toString(), expire);
            return token
        }
        throw Exception("User not found, please try again")
    }

    /**
     * Service level function to valid a user action.
     *
     * @param accessToken token from the request header.
     * @param userid parsed from the request body
     * @return null The expiration time of the token in seconds.
     */
    fun validTokenAction(accessToken:String, userid:String){
        validToken(accessToken)
        val userID = cacheService.get(accessToken)
        if (userID.toString() != userid){
            throw Exception("User ID un-match with current Invoices user")
        }
    }

    /**
     * Service level function to check access token still valid (in redis or not)
     *
     * @param accessToken
     * @return null
     */
    fun validToken(accessToken:String):Any?{
        if (!cacheService.hasKey(accessToken)){
            throw Exception("Please use /api/auth/login to login first");
        }
        return cacheService.get(accessToken)
    }

    /**
     * Service level function generate a new access token by userinfo and cache it in redis within expire time
     *
     * @param usersDO userinfo in DB
     * @return access token in JWT format
     */
    fun generateToken(usersDO: UsersDO): String {
        val claims = mapOf(
            "sub" to usersDO.id,
            "email" to usersDO.email,
            "name" to usersDO.name,
            "company" to usersDO.companyName,
            "iss" to "com.upsider.invoices.demo",
        )
        return Jwts.builder()
            .setHeader(mapOf(
                "alg" to SignatureAlgorithm.RS256,
                "keyid" to "1",
            ))
            .setClaims(claims)
            .setSubject(usersDO.id.toString())
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis()+expire))
            .signWith( keypair.private, SignatureAlgorithm.RS256)
            .compact()
    }
}
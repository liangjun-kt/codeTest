package com.upsider.invoices.demo.model.db

import org.mybatis.generator.api.MyBatisGenerator
import org.mybatis.generator.config.Configuration
import org.mybatis.generator.config.xml.ConfigurationParser
import org.mybatis.generator.internal.DefaultShellCallback

object Generator {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val warnings: List<String> = ArrayList()
        val overwrite = true
        val inputStream = Generator::class.java.getResourceAsStream("/db/generatorConfig.xml")
        val cp = ConfigurationParser(warnings)
        val config: Configuration? = cp.parseConfiguration(inputStream)
        inputStream?.close()
        val callback = DefaultShellCallback(overwrite)
        val myBatisGenerator = MyBatisGenerator(config, callback, warnings)
        myBatisGenerator.generate(null)
        for (warning in warnings) {
            println(warning)
        }
    }
}
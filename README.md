```
    model
        db
            dataObject          # pojo of DB dataobject
            mapper              # mybaits mapper
            Generator           # Generator class for myBaits 
        request
            AddInvoicesDTO      # request body for post /api/invoices
            CreateUserDTO       # request body for post /api/auth/user
            LoginDTO            # request body for post /api/auth/login
    service
        AuthService             # service deal with tasks related to AUTH
        CacheService            # service deal with redis to save/get value from redis
        DBService               # service deal with DB
        InvoicesService         # service deal with tasks related to INVOICES
    web
        AuthApi                 # controller deal with auth requests
        ExceptionController     # controller deal with all exceptions
        InvoicesController      # controller deal with INVOICES requests

```

# samples:
## create user
```
curl --location 'http://localhost:8081/api/auth/user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"user1",
    "email":"user1@gmail.com",
    "password":"password",
    "companyName":"test株式会社"
}'
```
## login
```
curl --location 'http://localhost:8081/api/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email":"user1@gmail.com",
    "password":"password"
}'
response access token
eyJhbGciOiJSUzI1NiIsImtleWlkIjoiMSJ9.eyJzdWIiOiI1IiwiZW1haWwiOiJ1c2VyMUBnbWFpbC5jb20iLCJuYW1lIjoidXNlcjEiLCJjb21wYW55IjoidGVzdOagquW8j-S8muekviIsImlzcyI6ImNvbS51cHNpZGVyLmludm9pY2VzLmRlbW8iLCJpYXQiOjE3MzQwODU3MTUsImV4cCI6MTczNDA4NzUxNX0.TWm3lPzoy0UkRbHzsFJtMI3rjrZwb_QSTRakMXs9dWfDUAqwsMYwy_hwNEYGGtZiyHPQopM36YpGAtBetYRNrkSCwpDYKcCYqNAcVvonwaFM8l2n0AbpssICAVQvO9N6XFXI_9XZvvnIdkMPh3PFBCXjJC9R_WWz6lZN86mWVwlnsXPfw5mYi2vUBJLbZbiYgDeI7A9GyHbT_cpLUYGdg2xOttx1yGmaBPuEKksolVzwKLA44AKQJAW1NYcWb6uWC8vVqlZGJv6vf4ncNXpttkubdxYUx6pYT2KWxMJc8j8R5xdUqoTZAt3m09jUr99oz5tP1BYNmNlhUtlHz3RLYQ%
```
## add invoice
```
curl --location 'http://localhost:8081/api/invoices' \
--header 'Content-Type: application/json' \
--header 'Authorization: ••••••' \
--data '{
    "userId":"5",
    "paymentAmount":11000,
    "paymentDueDate":"2024-12-25"
}'
```
## list invoices 
```
curl --location 'http://localhost:8081/api/invoices?from=2024-12-10&to=2024-12-13&pageIndex=0' \
--header 'Authorization: ••••••'
```
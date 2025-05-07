# RegisterLoginAPIs
Working on Register & Login APIs project.
RegisterLoginAPI is a simple RESTful authentication system built with Java Spring Boot.
It allows users to register and login (with JWT tokens) securely.

🚀 Features
✅ User registration
✅ Secure login with JWT token
✅ Spring Boot 3.x + Spring Security 6
✅ Swagger UI documentation
✅ Passwords encrypted with BCrypt
✅ Role-based access support

🛠 Tech Stack(assets/Tech Track.png)
Java 21
Spring Boot 3.x
Spring Security
JWT (JSON Web Tokens)
PostgreSQL
Lombok
Swagger (Springdoc OpenAPI)

**📂 Project Structure**
userauthapi/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── RegisterLoginAPI/
│   │   │           └── userauthapi/
│   │   │               ├── config/
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── controller/
│   │   │               │   └── AuthController.java
│   │   │               ├── dto/
│   │   │               │   └── LoginRequest.java
│   │   │               │   └── RegisterRequest.java
│   │   │               ├── entity/
│   │   │               │   └── User.java
│   │   │               ├── repository/
│   │   │               │   └── UserRepository.java
│   │   │               ├── security/
│   │   │               │   └── JwtTokenProvider.java
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── service/
│   │   │               │   └── AuthService.java
|   |   |               |   |__ RegisterService.java
│   │   │               └── UserAuthApiApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │       └── templates/
│   └── test/
│       └── java/
│           └── com/
│               └── userauthapi/
│                   └── registerloginapi/
│                       └── AuthControllerTest.java
├── .gitignore
├── pom.xml
└── README.md


📚 API Endpoints

Method	    Endpoint	             Description
POST	      /api/auth/register	   Register a new user
POST	     /api/auth/login	       Login an existing user

# Run the project
./mvnw spring-boot:run

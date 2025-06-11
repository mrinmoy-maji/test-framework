# restapi-framework

A robust Java-based REST API automation framework using TestNG, with provisions for BDD (Cucumber) and CI/CD integration.

## Features
- Modular design for easy maintenance and scalability
- Centralized configuration management (environments, tokens, etc.)
- Automatic access-token fetching and header management
- Interceptors for request/response logging and header injection
- Utility classes for request formation
- Standardized reporting (TestNG, Allure-ready)
- Ready for CI/CD pipelines (Gradle build)
- Easy switch to BDD (Cucumber) if needed

## Structure
```
restapi-framework/
├── build.gradle
├── settings.gradle
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               ├── api/
│   │               │   ├── ApiClient.java
│   │               │   ├── AuthManager.java
│   │               │   └── interceptors/
│   │               │       └── HeaderInterceptor.java
│   │               ├── config/
│   │               │   └── ConfigManager.java
│   │               └── utils/
│   │                   └── Logger.java
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── tests/
│       │               └── ExampleApiTest.java
│       └── resources/
│           ├── config.properties
│           └── environments/
│               ├── dev.properties
│               ├── qa.properties
│               └── prod.properties
└── reports/
```

## Getting Started
- Build: `./gradlew clean build`
- Run tests: `./gradlew test`
- Reports: `build/reports/tests/test` or `build/allure-results` (if Allure is enabled)

## Switching to BDD
- Uncomment Cucumber dependencies in `build.gradle`
- Add feature files and step definitions under `src/test/resources` and `src/test/java`

---

This framework is designed for extensibility and can be adapted for any REST API automation needs with minimal changes.

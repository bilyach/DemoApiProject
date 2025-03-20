
# DEMO Api Automation project

This project is to complete the API automation testing asessment using the Bookstore demo api service.



## Index
 - [Project Demonstates](#project-demonstates)
 - [Tech Stack](#tech-stack) 
 - [Project Folder Structure](#project-folder-structure) 
 - [Framework](#framework)
 - [Project Setup](#project-setup)
 - [Run Locally](#run-locally)
 - [Execution Report](#execution-report)


## Project Demonstates

- Use of testng framework
- Maven project structure
- Rest Assured API framework
- Reading data from @DataProvider method
- Running test cases multiple test classes by added under testng.xml
- Execution report using Allure Report



## Tech Stack

**Language:** Java 17

**API library:** Rest Assured (5.5.1)

**Testing framework library:** TestNg (7.10.2)

**Project Building Tool:** Maven 3.8.0

**Reporting:** Allure Report (2.12.0)

**VCS:** Git

**Test data/Configuration data:** .properties file


## Project Folder Structure
 It has 3 source folders

    1. src/main/java - contains all code except test cases
    2. src/test/java - contains test case classes
    3. src/test/resource - contails configurations, test data related files


## Framework

Used testNg and POM results in hybrid Framework
It has following components:

    1. Test Base
    2. Test Cases
    3. Test Data providers
    4. API models
    5. API clients
    6. Configurations
    7. Utilities
    8. Report


## Project Setup
To run the test scenarios, need to have followings on your machine:
    
    1. Java 17
    2. Maven 3.8.0
    3. Git
    4. Any IDE (Eclipse/IntelliJ)
## Run Locally

- Clone the project

  Go to the folder location where you want to keep local copy of the project

```bash
  git clone https://github.com/bilyach/DemoApiProject.git
```

- Import the project as Maven project in any IDE (Eclipse/IntelliJ)


#### To run complete suite via testng.xml
  ```bash
  mvn install -DskipTests
  mvn clean test
```


## Execution Report
- Once the Execution is done, the report should be generated with the following commands

```bash
  mvn allure:report
  mvn allure:serve
```

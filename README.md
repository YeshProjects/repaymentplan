
## Plan Generator
In order to inform borrowers about the final repayment schedule, we need to have pre-calculated repayment plans throughout the lifetime of a loan.
To be able to calculate a repayment plan specific input parameters are necessary:
duration (number of installments in months)
nominal rate (annual interest rate)
loan amount (principal amount)
Date of Disbursement/Payout ("startDate")
These four parameters need to be input parameters.
The goal is to calculate a repayment plan for an annuity loan. Therefore the amount that the borrower has to pay back every month, consisting of principal
and interest repayments, does not
change (the last installment might be an exception).
The annuity amount has to be derived from three of the input parameters (duration, nominal interest rate, total loan amount) before starting the plan
calculation.
(use http://financeformulas.net/Annuity_Payment_Formula.html as reference)
 Note: the nominal interest rate is an annual rate and must be converted to monthly before using in the annuity formula 
 
## Prerequisite
 Install Docker 
 
## Running the application locally

Steps to execute : 

1. docker build -t repaymentplan .
2. docker run -p 8080:8080 -it repaymentplan
3.   curl -u user:password  --header "Content-Type: application/json" -X POST  --data '{"loanAmmount" : 5000,"nominalRate" : 5,"duration" : 24,"startDate": "01.01.2018"}' http://localhost:8080/generatePlan
OR OUTPUT FILE
curl -u user:password  --header "Content-Type: application/json" -X POST  --data '{"loanAmmount" : 5000,"nominalRate" : 5,"duration" : 24,"startDate": "01.01.2018"}' http://localhost:8080/generatePlan --output output.json
OR Windows Docker Tool Box
curl -u user:password  --header "Content-Type: application/json" -X POST  --data '{"loanAmmount" : 5000,"nominalRate" : 5,"duration" : 24,"startDate": "01.01.2018"}' http://192.168.99.100:8080/generatePlan --output output.json

4. verify output.json in current directory



## Tech stack & Open-source libraries

Server - Backend

* 	[JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Junit 5](https://junit.org/junit5/docs/current/user-guide/) - Distributed version control system
* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[Git](https://github.com/) - Version Controller 
* 	[Docker](https://www.docker.com/) - Docker is a set of platform as a service products that use OS-level virtualization to deliver software in packages called containers. Containers are isolated from one another and bundle their own software, libraries and configuration files; they can communicate with each other through well-defined channels

External Tools & Services

* 	[Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)




				
	
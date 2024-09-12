**Project Documentation: E-Commerce Inventory Management System with CI/CD Pipeline**
**Project Overview:**

    The E-Commerce Inventory Management System is a Java-based web application developed using Spring Boot. This project is designed to manage product inventories, track stock 
    levels, and process customer orders for a fictional e-commerce platform. The system provides real-time inventory updates to keep operations running smoothly.

    As a DevOps engineer, your responsibility was to ensure the delivery of high-quality code through a robust CI/CD pipeline while deploying the application on an Apache Tomcat 
    server and monitoring it with AWS CloudWatch. The pipeline integrates code quality checks, artifact management, and automated deployment using AWS infrastructure.

**Technologies Used:**

  **Java & Spring Boot**: To develop the web application.
  **Maven**: For project management and building.
  **Git & GitHub**: For version control.
  **Jenkins**: To set up the CI/CD pipeline.
  **SonarQube**: For static code analysis and quality gate enforcement.
  **JFrog Artifactory**: For storing build artifacts (WAR files).
  **Apache Tomcat**: To deploy the application.
  **AWS CloudWatch**: To monitor the application health and performance.
  **Terraform**: To provision the infrastructure on AWS.

**Application Features:**

   **Add Products**  : Allows users to add new products to the inventory.
   
   **Update Stock**  : Enables updates to product stock levels.
   
   **View Products** : Displays details about products and their stock levels.
   
   **Process Orders**: Processes orders and updates the stock accordingly.
   
**CI/CD Pipeline Architecture:**
The pipeline is implemented using Jenkins and consists of the following steps:

**Code Checkout:**
   Jenkins pulls the latest code from the GitHub repository.
Build with Maven:
  The project is built using Maven. The pipeline runs all relevant Maven lifecycle stages: 
  clean, compile, test, and package.

**SonarQube Analysis:**
  The code undergoes static analysis using SonarQube to identify bugs, vulnerabilities, 
  and code smells.
  A quality gate is enforced. If the code does not pass, the pipeline fails, preventing 
  the application from being deployed.

**Artifact Management:**
  Upon successful code analysis, the pipeline pushes the WAR file to JFrog Artifactory for 
  artifact management and versioning.

**Deployment to Apache Tomcat:**
   After passing the quality gates and storing artifacts, the pipeline deploys the WAR 
   file to the Apache Tomcat server on AWS EC2.

**Application Monitoring with AWS CloudWatch:**
  AWS CloudWatch is configured to monitor system metrics (CPU, memory) and custom 
  application metrics, and to trigger alerts if thresholds are breached.


**Infrastructure Setup Using Terraform:**
 Terraform is used to provision AWS resources for the following:
  **Jenkins Server**: Hosts Jenkins to manage the CI/CD pipeline
  **SonarQube Server**: Dedicated instance for performing code quality checks.
  **JFrog Artifactory Server**: Stores artifacts.
  **Tomcat Server**: Hosts the deployed application.
  **AWS CloudWatch**: Configured on all servers to track performance metrics.

**Project Setup:**
**Application Development:**

Develop the Java-based web application using Spring Boot. The application structure includes controllers, services, repositories, and entities to handle product and order management.

**Version Control:**

Use Git to track changes and push all commits to a GitHub repository.

**Pipeline Setup in Jenkins:**
**Plugin Installation:**

Install Jenkins plugins for Git, Maven, SonarQube, JFrog Artifactory, and CloudWatch.

**Jenkinsfile:**

Write a Jenkinsfile to define the pipeline stages:
Stage 1: Checkout code from GitHub.
Stage 2: Build using Maven.
Stage 3: Run SonarQube analysis.
Stage 4: If quality gate passes, upload artifact to JFrog.
Stage 5: Deploy to Tomcat.
Stage 6: Set up AWS CloudWatch monitoring.

**Monitoring with AWS CloudWatch:**
**AWS CloudWatch Agent:**
  Configure the CloudWatch agent on the Tomcat server to collect logs and system metrics.
**Dashboard Setup:**
Set up a CloudWatch dashboard to monitor application metrics such as CPU usage, memory utilization, response time, and custom heartbeat metrics.

**Challenges & Lessons Learned:**
**SonarQube Quality Gate Failure:**
The application failed to deploy because it did not meet SonarQube's quality gate standards. This emphasizes the importance of maintaining high-quality code.
Actions taken included fixing critical bugs and refactoring code to pass the quality gate in subsequent pipeline runs.

**Conclusion:**
The E-Commerce Inventory Management System project showcased the full CI/CD lifecycle using Jenkins, SonarQube, JFrog Artifactory, and AWS CloudWatch. While the first deployment attempt failed due to code quality issues, it highlighted the importance of enforcing quality gates and automating deployment processes. After resolving the issues, the system was successfully deployed and monitored, fulfilling business requirements for reliable inventory management.

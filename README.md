# CafeOrderManagement: Dynamic Web Project

## Overview
This project is a Java-based web application that integrates with a MySQL database and is deployed on Apache Tomcat. It requires the MySQL JDBC driver and Apache Tomcat for running the application successfully.

## Prerequisites
Before you begin, ensure you have the following installed:

1. **JDK (Java Development Kit) 8 or above**  
   Download from: [Oracle JDK Downloads](https://www.oracle.com/in/java/technologies/downloads)

2. **Eclipse IDE**  
   Download from: [Eclipse IDE for Enterprise Java and Web Developers](https://www.eclipse.org/downloads/packages/release/2022-06/r/eclipse-ide-enterprise-java-and-web-developers)

3. **MySQL Database**  
   Download from: [MySQL Downloads](https://dev.mysql.com/downloads/)

4. **Apache Tomcat 9.x**  
   Download from: [Apache Tomcat 9.x Downloads](https://tomcat.apache.org/download-90.cgi)

5. **MySQL JDBC Driver (Connector/J)**  
   Download from: [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)

6. **MySQL Workbench** (for managing the database)  
   Download from: [MySQL Workbench](https://www.mysql.com/products/workbench/)

## Setup

### 1. Add MySQL JDBC Driver to Your Project in Eclipse
1. Right-click on your project in the **Project Explorer**.
2. Select **Build Path > Configure Build Path**.
3. Under the **Libraries** tab, click **Add External JARs**.
4. Navigate to the location where you extracted the MySQL Connector/J, and select the `mysql-connector-java-.jar` file.
5. Click **OK** to close the properties window.
6. Add the JAR file to the `lib` folder under `WEB-INF` in your project.

### 2. Add Apache Tomcat to Eclipse
1. Open **Eclipse IDE**.
2. Go to **Window > Preferences** from the top menu.
3. In the Preferences window, go to **Server > Runtime Environments**.
4. Click **Add Runtime** and choose **Apache Tomcat v9.0** (or your installed version).
5. Click **Next** and browse to the location where you extracted Tomcat. Select the `apache-tomcat-9.x.x` folder.
6. Click **Finish** to complete the setup.

### 3. Verify Tomcat Runtime Environment in Eclipse
1. Go to **Window > Show View > Servers** to open the **Servers** view.
2. In the **Servers** view, you should see **Tomcat v9.0 Server** (or your version) listed. If it's not listed, right-click in the **Servers** view, select **New > Server**, and follow the steps to add the Tomcat runtime.

---

## Running the Project

1. **Deploy the Application to Tomcat**:
   - Right-click on your project in **Project Explorer**.
   - Select **Run As > Run on Server**.
   - Select **Tomcat v9.0** (or your installed version) from the list of servers and click **Finish**.

2. **Access the Application**:
   - After deploying and running the project on Tomcat, open your browser and navigate to:
     ```
     http://localhost:8080/your-app-name
     ```
     Replace `your-app-name` with the name of your deployed application.

## Troubleshooting

- **Tomcat Fails to Start**:
  - Check the logs in the **logs** directory (`catalina.out` for errors).
  - Ensure no other applications are using port `8080`. You can change the port in the `conf/server.xml` file if necessary.

- **Database Connection Issues**:
  - Double-check your database credentials and URL in the `context.xml` file of Tomcat.
  - Make sure the MySQL JDBC driver is added to the Tomcat `lib` folder.

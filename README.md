CafeOrderManagement:-Dynamic Web Project
Overview
This project is a Java-based web application that integrates with a MySQL database and is deployed on Apache Tomcat. It requires the MySQL JDBC driver and Apache Tomcat for running the application successfully.

Prerequisites
Before you begin, ensure you have the following installed:

JDK (Java Development Kit) 8 or above
Download from:- https://www.oracle.com/in/java/technologies/downloads

Install Eclipse IDE 
download:-https://www.eclipse.org/downloads/packages/release/2022-06/r/eclipse-ide-enterprise-java-and-web-developers


MySQL Database
Download MySQL from:-https://dev.mysql.com/downloads/

Apache Tomcat 9.x
Download Apache Tomcat from:-https://tomcat.apache.org/download-90.cgi

MySQL JDBC Driver (Connector/J)
download mysql-connector-j-9.1.0.jar

Download MySQL Workbench to manage Database.
Download:- https://www.mysql.com/products/workbench/

Setup:-
1.Add MySQL JDBC Driver to Your Project
In Eclipse (for Java projects):
1) Right-click on your project in the Project Explorer.
2) Select Build Path > Configure Build Path.
3) Under the Libraries tab, click Add External JARs.
4)Navigate to the location where you extracted the MySQL Connector/J, and select the mysql-connector-java-<version>.jar file.
5)Click OK to close the properties window.
6)Add jar file to lib folder in WEB-INF in your project.

2.Add Apache Tomcat to Eclipse
1)Open Eclipse IDE.
2)Go to Window > Preferences from the top menu.
3)In the Preferences window, go to Server > Runtime Environments.
4)Click Add Runtime and choose Apache Tomcat v9.0 (or your installed version).
5)Click Next and then browse to the location where you extracted Tomcat. Select the apache-tomcat-9.x.x folder.
6)Click Finish to complete the setup.
7)Verify Tomcat Runtime Environment in Eclipse
8)Go to Window > Show View > Servers to open the Servers view.
9)In the Servers view, you should see Tomcat v9.0 Server (or your version) listed. If it's not listed, right-click in the Servers view, select New > Server, and follow the steps to add the Tomcat runtime.

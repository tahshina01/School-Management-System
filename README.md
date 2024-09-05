This is our javafx school management project

We used java open jdk 19 version and the project is built on Intellij Idea

In our project we used mysql database to store the data.
In resources, in databases folder there are two sql files -
1. routineDB.sql
2. schoolDB.sql

To create the database, run the queries of these two files in mysql terminal or workbench.
In our app, we allowed the types of users to log in -
1. Admin
2. Teacher
3. Staff

When you run the query, the Admin data will be inserted it the Database.
Admin id will be : 1111111 and 
Admin password will be : a

After logging in as admin, you can update your profile, password as well as create or delete other users.
All these you can do directly by the app, don't need to manipulate database directly.
The application will update the data in database automatically.

To run the project, you need to connect jdbc( java-mysql-connector ) to the project in library inside project structure.

Finally in java code folder, in package com.schoolmanagementsystem.database, There is a java file called ConnectDatabase.
In that file, you need to update the DB_USER, DB_PASSWORD to connect the database with your system.
Similarly, in package com.schoolmanagementsystem.database.routineDB there is a java file named ConnectRoutineDB.
In that file also, you need to update the DB_USER, DB_PASSWORD like previous to connect the database with your system.


If you have done all these, then your application is ready to launch.
You can log in as Admin and you will get access to all kind of data and permissions related to the application.
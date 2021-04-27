Ensolvers challenge ToDo API

This app is the underlaying logic and must be consumed by a front-end application.
You can run it by executing "java -jar" on .jar file located at: 
https://drive.google.com/file/d/1tI1zcZdbx6cWOp9VdpGt8DwaXyOKHK_F/view?usp=sharing

Also you can check the online deployment at:
https://todo-ensolvers.herokuapp.com

Aviable endpoints:
/folder
/task

Models format:
TASK:
    {
        "id": Integer
        "description": "String",
        "done": boolean
    }

FOLDER:
    {
        "id": Integer,
        "name": String,
        "tasks": [] (Task list) 
    }

Technologies used:
Java 11.0.10
Apache Maven 3.6.3
Spring Core 5.3.6
Spring Boot 2.4.5
MySQL 8.0.23

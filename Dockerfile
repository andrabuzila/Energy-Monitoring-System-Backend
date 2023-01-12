FROM openjdk:19-jdk

VOLUME /main-app

ADD target/DS2022_30641_Buzila_Andra_Assignment_1_Backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar","/app.jar"]
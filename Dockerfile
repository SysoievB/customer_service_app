FROM openjdk:22
ADD target/customer_service_app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar","app.jar" ]
FROM eclipse-temurin:17-jdk
LABEL maintainer="test_user@gmail.com"
EXPOSE 8080
COPY target/aws-ec2-fargate-0.0.1-SNAPSHOT.jar aws-ec2-fargate-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/aws-ec2-fargate-0.0.1-SNAPSHOT.jar"]
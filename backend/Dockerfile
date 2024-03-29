FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} earthquake-mapping.jar
ENTRYPOINT ["java","-jar","/earthquake-mapping.jar"]
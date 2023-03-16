FROM openjdk:11
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} allsolved.jar
ENTRYPOINT ["java","-jar","/allsolved.jar"]
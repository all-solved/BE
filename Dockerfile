FROM openjdk:11
ARG JAR_FILE=./build/libs/*.jar
VOLUME /allso_Img
COPY ${JAR_FILE} allsolved.jar
ENTRYPOINT ["java","-jar","/allsolved.jar"]
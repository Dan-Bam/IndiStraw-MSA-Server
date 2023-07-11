FROM openjdk:11-jdk

WORKDIR /usr/src/app

COPY account/build/libs/indistraw-account-0.0.1-SNAPSHOT.jar account/build/libs/indistraw-account-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java","-jar","account/build/libs/indistraw-account-0.0.1-SNAPSHOT.jar"]
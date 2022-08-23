
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
WORKDIR /build/

COPY pom.xml /build/
COPY user-platform-consumer /build/user-platform-consumer
COPY user-platform-interface /build/user-platform-interface
COPY user-platform-provider /build/user-platform-provider
COPY user-platform-starter /build/user-platform-starter
RUN ls -l
RUN mvn package

FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY --from=MAVEN_BUILD /build/user-platform-starter/target/user-platform-starter-1.0.jar /app/

ENTRYPOINT ["java","-jar","user-platform-starter-1.0.jar"]
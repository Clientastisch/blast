FROM maven:3.8.2-openjdk-11 AS build
WORKDIR /home
COPY . .
RUN mvn install -B -ntp -DskipTests=true -f pom.xml

FROM gcr.io/distroless/java
WORKDIR /home
COPY --from=build /home/target/blast-1.0.jar /home/blast-1.0.jar
ENTRYPOINT ["java","-jar","blast-1.0.jar"]
# start with base image containig Java Runtime
FROM openjdk:17-jdk-slim

# information around who maintains the image
LABEL "org.opencontainers.image.authors"="shaswatidash"

# add application jar to image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]
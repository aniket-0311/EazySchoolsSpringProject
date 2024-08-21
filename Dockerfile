# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Volume for temporary files
VOLUME /tmp

# Copy the executable JAR file into the root directory
COPY target/eazyschool-aws-deployment.jar /eazyschool-aws-deployment.jar

# Expose port 8082 to the outside world
EXPOSE 8082

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/eazyschool-aws-deployment.jar"]

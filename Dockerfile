# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file into the container at /app
COPY target/eazyschool-aws-deployment.jar /app/eazyschool-aws-deployment.jar

# Expose port 8082 to the outside world
EXPOSE 8082

# Run the JAR file
ENTRYPOINT ["java", "-jar", "eazyschool-aws-deployment.jar"]

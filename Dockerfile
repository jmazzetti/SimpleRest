FROM Java8
MAINTAINER Jose Mazzetti "<jmazzetti@gmail.com>"

# Example on how VERSION build-arg can be used

EXPOSE 8080

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/spring-boot-app.jar"]


FROM java:8-jre

LABEL maintainers="burakkilincofficial@gmail.com" \
	  image_description="bring-0.0.1-SNAPSHOT jar app" \
	  app_environment="dev" \
	  app_version='0.0.1-SNAPSHOT.jar"
	  
COPY ./target/bring-0.0.1-SNAPSHOT.jar bring-0.0.1-SNAPSHOT.jar

EXPOSE 8080
CMD ["java", "-jar", "bring-0.0.1-SNAPSHOT.jar"]
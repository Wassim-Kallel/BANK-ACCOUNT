FROM fabric8/java-jboss-openjdk8-jdk
ENV JAVA_APP_JAR bank-account-0.0.1-SNAPSHOT.jar
ENV AB_OFF true
EXPOSE 8080
ADD $JAVA_APP_JAR /deployments/

FROM java:openjdk-8-jdk

ARG JAR_FILE
ADD target/${JAR_FILE} /opt/thorntail.jar

ENV JAVA_OPTS="-Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true"

EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/thorntail.jar

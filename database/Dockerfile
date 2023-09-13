FROM openjdk:17
VOLUME /tmp
COPY target/db-migration-demo-job.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

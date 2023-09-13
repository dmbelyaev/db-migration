# db-migration-demo

**Abstract**:  
We all love Spring Boot. It makes many things easy. One of these things is database migration. One just need to add 
either Flyway or Liquibase dependency to an application and migration scripts will be picked up on the application startup
and automatically executed. Really nice feature which may even be considered as an act of true magic. 🙂
But is this really a solution which is good to be used in production? Are there any pitfalls and hidden surprises?
Let's explore it together.

This repository explores pitfalls one can get into if database migration on application start-up is used.

A simple application is used to demo the issue. The application provides a RESTfull CRUD API managing customer profiles.
Customer profile contains main entity containing firstname, lastname and email and an associated address entity. Initially
only one address can be associated with a customer profile. In the version 1.1 this is changed to one-to-many relationship.

A kubernetes cluster based on a [kind tool](https://kind.sigs.k8s.io/) is used as a deployment target.

A PostgreSQL database running in container is used.

## Showcase the issue
To showcase the issue one can face, if database migration on startup is used, you can just follow commits starting
from the Version 1.0 and up to the Version 1.1.1. 

## Version 1.0
Single-module application with one-to-one relationship between customer profile and address. With this version you can:

### Run PostgreSQL in a container
```shell
docker-compose up -d
```

### Create kind cluster
```shell
./scripts/kind-create-cluster.sh
```

### Build spring boot fat JAR
```shell
./mvnw clean package
```

### Build docker image containing the demo application and push it to the registry
```shell
./scripts/build-and-push-app-image.sh
```
> Be aware that prior to run this command you may need to log in to an image registry of your choice!

### Deploy the app onto the cluster
```shell
./scripts/deploy-app.sh
```

# Delete app from the cluster
```shell
./scripts/delete-app.sh
```

### Make endpoints available through port forwarding
```shell
./scripts/port-forwarding.sh
```

### Test endpoints manually
Open **customer-rofile-api.http** in IntelliJ and try out available endpoints

## Version 1.1
You have recognized that one-to-one relationship between customer profile and address is a major point of customer's complains.
Your customers want to being able to keep several addresses associated with their profiles to being able to request delivery
to their relatives and/or loved ones without need to manually type address information every time they do it.

Version 1.1 is a version of the application were this limitation id fixed. This version supports one-to-many relationship
between customer profile and address. Database migration script extends the database and migrates the existing data.

To emulate a real world situation and fill in the database with data the ***DataGenerator*** java class is included
in the test sources. In order to reproduce the problem this class must be executed before deploying the new version
of the application.

All actions from the Version 1.0 are available.

If you try to deploy this version of the application you'll see that it is never succeed because of the long running
migration which causes Kubernetes to restart the Pod again and again.

## Version 1.1.1
TBA

## Version 1.1.2
In this version the database migration is not part of the application bootstrap and therefore can't cause long start-up time
what triggered constant Pod restart in the version 1.1. To achieve this the application structure is converted to a multi-module
project with application and database migration living in separate module. The database migration module is included 
as a test dependency to the application module to simplify database setup for integration tests.

The built result of every of these modules is an executable self-contained JAR. They can be executed using commands bellow.

### Run demo application locally accessing PostgreSQL running in a container
```shell
./mvnw -pl app spring-boot:run -Dspring-boot.run.profiles=local
```

### Run database migration locally against PostgreSQL running in a container
```shell
./mvnw -pl database clean package
export DATASOURCE_URL='jdbc:postgresql://localhost:5432/local_db'
export DATASOURCE_USERNAME='user'
export DATASOURCE_PASSWORD='password'
java -jar ./database/target/db-migration-demo-job.jar
```

Now we can execute a database migration as a Kubernetes Job.

### Build docker image containing the database migration job and push it to the registry
```shell
./scripts/build-and-push-job-image.sh
```

### Deploy and execute the database migration job on the cluster
```shell
./scripts/deploy-job.sh
```

After that we can deploy the application which will be successful this time.

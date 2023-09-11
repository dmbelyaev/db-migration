# db-migration-demo

# Run postgres in a container
```shell
docker-compose up -d
```

# Create **kind** cluster
```shell
./scripts/kind-create-cluster.sh
```

# Build spring boot fat JAR
```shell
./mvnw clean package
```

# Build docker image containing the app and push it to the registry
```shell
./scripts/build-and-push-image.sh
```

# Deploy app onto the cluster
```shell
./scripts/deploy-app.sh
```

# Open service port
```shell
./scripts/port-forwarding.sh
```

# Manually call endpoints
Open **customer-rofile-api.http** in IntelliJ and try out available endpoints

# Run locally accessing PostgreSQL running in a container
```shell
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

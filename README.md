# my-complicated-service

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/my-complicated-service-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Running with Docker

### Building the Docker Image

Build the application first:

```shell script
./mvnw package
```

Then build the Docker image:

```shell script
docker build -f src/main/docker/Dockerfile.jvm -t my-complicated-service .
```

### Running the Container

Run the container:

```shell script
docker run -i --rm -p 8080:8080 my-complicated-service
```

The application will be available at http://localhost:8080/hello

### Docker Hub Images

Pre-built images are automatically published to Docker Hub via GitHub Actions on every push to the main branch:

```shell script
docker run -i --rm -p 8080:8080 <your-dockerhub-username>/my-complicated-service:latest
```

## CI/CD

This project includes a GitHub Actions workflow that:
- Runs tests on every push and pull request
- Builds and pushes Docker images to Docker Hub on pushes to the main branch
- Performs security vulnerability scanning with Trivy

### Required Secrets

Configure the following secrets in your GitHub repository:
- `DOCKER_USERNAME`: Your Docker Hub username
- `DOCKER_PASSWORD`: Your Docker Hub password or access token

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

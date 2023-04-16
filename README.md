# API Jewel Bank

This little project is a example of a microservice build using Kotlin, Spring boot, Kubernetes and Google cloud following [this article](https://medium.com/rapaduratech/microservices-com-kotlin-spring-boot-e-kubernetes-rodando-na-google-cloud-63056044d8a9).
The purpose of this project is just studying all techs involving.

## Tech Stack

**Server:**
- Language Java 17 and Kotlin 1.7.22
- Framework Spring (Springboot, Spring Data, Spring Web, DevTools), Flyway, H2, Swagger

**DevOps:**
- Google as cloud provider


## Features

- [x] 


## API Reference

#### Swagger

Just access in your browser: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Run Locally

Clone the project

```bash
  git clone git@github.com:itzmatheus/api-jewel-bank.git
```

Go to the project directory

```bash
  cd api-jewel-bank
```

Install dependencies

```bash
  make install
  or 
  chmod +x mvnw /
  ./mvnw clean package -Dmaven.test.skip=true
```

Start the server

```bash
  make run
  or
  chmod +x mvnw /
  ./mvnw clean package -Dmaven.test.skip=true /
  java -jar target/kotlin-*.jar
```


## Running Tests

To run tests, run the following command

```bash
  make test
  or
  chmod +x mvnw /
  ./mvnw clean package -Dmaven.test.skip=true /
  ./mvnw verify
```


## Deployment

To deploy this project run

## Acknowledgements

- [Microservices com Kotlin, Spring boot e Kubernetes rodando na Google Cloud](https://medium.com/rapaduratech/microservices-com-kotlin-spring-boot-e-kubernetes-rodando-na-google-cloud-63056044d8a9)
- [Google Cloud](https://cloud.google.com/?hl=pt-br)
- [Kubernetes](https://kubernetes.io/pt-br/)
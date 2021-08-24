# endstation-beverage-mashine
A spring native based microservice who is handling use cases for the "endstation beverage mashine"

## How to run
The repository contains everything to run the service locally with mysql.

### Prerequisite
1. Maven
2. Docker
3. Docker-Compose
### Manual
1. Checkout the repository + join the folder
```commandline
git clone https://github.com/4ND3R50N/endstation-beverage-machine-service.git

or

git clone git@github.com:4ND3R50N/endstation-beverage-machine-service.git

cd endstation-beverage-machine-servic
```
2. Build the native docker application
```commandline
mvn spring-boot:build-image
```
3. start docker-compose
```commandline
cd docker
docker-compose up --detach
```
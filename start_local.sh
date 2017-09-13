
cd account-service
mvn clean install docker:build
cd ..
cd api-gateway
mvn clean install docker:build
cd ..
cd clothes-service
mvn clean install docker:build
cd ..
cd image-service
mvn clean install docker:build
cd ..
cd order-service
mvn clean install docker:build
cd ..
cd price-service
mvn clean install docker:build
cd ..
cd service-registry
mvn clean install docker:build
cd ..
cd shoes-service
mvn clean install docker:build
cd ..
cd stock-service
mvn clean install docker:build
cd ..
cd uaa-service
mvn clean install docker:build
cd ..
cd user-service
mvn clean install docker:build
cd ..
docker-compose up -d
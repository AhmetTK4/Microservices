# Microservices

## How To Run

cd /yourfilepath/Microservices/order-service       
mvn clean package

cd /yourfilepath/Microservices/notification-service
mvn clean package

cd /yourfilepath/Microservices                     
docker-compose up --build

you can reach the rabbitmq console
http://localhost:15672

you can reach the h2 console
http://localhost:8081/h2-console




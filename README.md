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






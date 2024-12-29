# Java 17 (veya projenizin kullandığı versiyon) kullanarak bir Docker image oluşturuyoruz
FROM openjdk:21-jdk-slim

# Uygulama için bir çalışma dizini oluştur
WORKDIR /app

# Maven build çıktısını image'e kopyala
COPY target/order-service-0.0.1-SNAPSHOT.jar order-service.jar

# Uygulamayı çalıştır
ENTRYPOINT ["java", "-jar", "order-service.jar"]

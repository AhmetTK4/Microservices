# Java 17 (veya projenizin kullandığı versiyon) kullanarak bir Docker image oluşturuyoruz
FROM openjdk:21-jdk-slim

# Uygulama için bir çalışma dizini oluştur
WORKDIR /app

# Maven build çıktısını image'e kopyala
COPY target/notification-service-0.0.1-SNAPSHOT.jar notification-service.jar

# Uygulamayı çalıştır
ENTRYPOINT ["java", "-jar", "notification-service.jar"]

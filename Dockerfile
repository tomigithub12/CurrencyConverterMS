FROM gradle:latest AS BUILD
WORKDIR /usr/app/
COPY . .
RUN gradle build

FROM amazoncorretto:17-alpine-jdk
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/CurrencyConverterMS-0.0.1-SNAPSHOT.jar /app/CurrencyConverterMS-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/CurrencyConverterMS-0.0.1-SNAPSHOT.jar"]
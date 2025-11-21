FROM amazoncorretto:21

WORKDIR /lotto-challenge

COPY ../build/libs/*SNAPSHOT.jar /java-lotto.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "/java-lotto.jar"]

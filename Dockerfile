FROM arm32v7/eclipse-temurin:17-jre
COPY build/libs/spliticket-*.jar app.jar

ENV DB_HOST=127.0.0.1
ENV DB_PORT=3306
ENV DB_DATABASE=spliticket
ENV DB_USERNAME=spliticket
ENV DB_PASSWORD=password

ENTRYPOINT ["sh", "-c", "java -Xmx1024m -Xms512m -XX:ParallelGCThreads=2 -jar /app.jar"]

# configure for twitter4j
FROM java:8

COPY . /var/www/java

WORKDIR /var/www/java

RUN javac -cp .:./lib/twitter4j-core-4.0.7.jar *.java

EXPOSE 5678/tcp

CMD java -cp .:./lib/twitter4j-core-4.0.7.jar Backend 5678

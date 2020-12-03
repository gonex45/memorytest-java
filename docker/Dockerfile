FROM adoptopenjdk/openjdk8-openj9:latest

ENV LANG en_US.utf8
ENV TZ CST-8
ENV JAVA_OPTS -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.awt.headless=true -Djava.library.path=/usr/share/app


COPY app.jar /app.jar

ENTRYPOINT ["sh", "-c", "java  $JAVA_OPTS -jar app.jar --stop "]
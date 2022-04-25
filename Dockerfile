FROM openjdk:11

RUN apt-get install curl tar bash procps

ARG MAVEN_VERSION=3.8.5

ARG USER_HOME_DIR="/root"

ARG BASE_URL=https://dlcdn.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && echo "Downlaoding maven" \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  \
  && echo "Unziping maven" \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  \
  && echo "Cleaning and setting links" \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

COPY ./ /app

RUN mvn -f /app clean package -Dmaven.test.skip
RUN ls -all /app/target/demo-0.0.1-SNAPSHOT.jar
RUN cp /app/target/*.jar /app/app.jar

EXPOSE 8000

CMD ["java", "-jar", "/app/app.jar"]

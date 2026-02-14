FROM eclipse-temurin:21-jdk-alpine

ENV CATALINA_HOME /usr/local/tomcat

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8
ENV JAVA_OPTS="-Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8"

RUN apk add --no-cache curl tar

RUN curl -fsSL https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.98/bin/apache-tomcat-9.0.98.tar.gz | tar xzf - -C /tmp && \
    mv /tmp/apache-tomcat-9.0.98 $CATALINA_HOME && \
    rm -rf $CATALINA_HOME/webapps/*

COPY target/semester-work-servlets-Fazam-coder-1.0-SNAPSHOT.war $CATALINA_HOME/webapps/ROOT.war

EXPOSE 8080

CMD ["sh", "-c", "$CATALINA_HOME/bin/catalina.sh run"]
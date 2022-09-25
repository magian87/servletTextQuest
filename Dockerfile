FROM tomcat:9.0.65-jdk17-corretto

EXPOSE 8080

COPY ./target/servletTextQuest.war /usr/local/tomcat/webapps/servletTextQuest.war
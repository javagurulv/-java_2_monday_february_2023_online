FROM mysql:latest

ENV MYSQL_DATABASE=servify_db
ENV MYSQL_USER=dbuser
ENV MYSQL_PASSWORD=Password@
ENV MYSQL_ROOT_PASSWORD=Password@

COPY initial-script.sql /docker-entrypoint-initdb.d/
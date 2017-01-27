#!/bin/bash

if [ $1 = b ]; then
    mvn clean install
fi

#export DATABASE_URL=jdbc:postgresql://uidxjooylvwuox:3d0731cf9e6444c5be77ac0f1e019ee7a021b01311f4e411e5a1e46a7c299cc3@ec2-54-225-127-147.compute-1.amazonaws.com:5432/d22l5h0da3nibd
#export SPRING_DATASOURCE_URL=jdbc:postgresql://ec2-54-225-127-147.compute-1.amazonaws.com:5432/d22l5h0da3nibd?sslmode=require&user=uidxjooylvwuox&password=3d0731cf9e6444c5be77ac0f1e019ee7a021b01311f4e411e5a1e46a7c299cc3
export JDBC_DATABASE_URL="jdbc:postgresql://ec2-54-225-127-147.compute-1.amazonaws.com:5432/d22l5h0da3nibd?sslmode=require"
export JDBC_DATABASE_USERNAME="uidxjooylvwuox"
export JDBC_DATABASE_PASSWORD="3d0731cf9e6444c5be77ac0f1e019ee7a021b01311f4e411e5a1e46a7c299cc3"

heroku local web

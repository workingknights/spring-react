#!/bin/bash

if [ $1 = b ]; then
    mvn clean install
fi

heroku local web

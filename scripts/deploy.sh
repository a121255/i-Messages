#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/i-Messages
cd $REPOSITORY

APP_NAME=i-Messages-Backend
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep 'i-Messages-Backend.*SNAPSHOT\.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME
CONFIG_PATH=file:$REPOSITORY/src/main/resources/application.yml

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> No running application found to stop."
else
  echo "> kill -9 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> Deploy - $JAR_PATH "
nohup java -jar $JAR_PATH --spring.config.location=$CONFIG_PATH > $REPOSITORY/nohup.out 2>&1 &
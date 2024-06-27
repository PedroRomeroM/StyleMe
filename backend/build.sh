#!/bin/sh

# Definindo os serviços
SERVICES="microauth microchallenge microorquestrador microusers"

# Loop pelos serviços
for SERVICE in $SERVICES
do
    echo "Cleaning and building the image for $SERVICE"
    cd "$SERVICE" || exit
    mvn clean package -DskipTests
    cd ..
done

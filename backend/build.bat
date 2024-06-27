@echo off
SETLOCAL EnableDelayedExpansion

SET SERVICES=microauth microchallenge microorquestrador microusers

FOR %%S IN (%SERVICES%) DO (
    PUSHD "%%S"
    echo Cleaning and building the image for %%S
    mvn clean package -DskipTests
    POPD
)
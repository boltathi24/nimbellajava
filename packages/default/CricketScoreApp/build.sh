#!/bin/bash

set -e 

# set to 'maven' to use maven
# set to 'gradle' to use gradle
BUILD="maven"

if [ $BUILD == "maven" ]; then
    mvn install
    echo target/nimc-1.0jar-with-dependencies.jar > .include
else
    if [ $BUILD == "gradle" ]; then
        gradle jar
        echo build/libs/qr-java-1.0.jar > .include
    else
        echo unknown builder
        exit -1
    fi
fi
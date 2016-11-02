#!/bin/bash

# Exit on error
set -e

if [ ! -f ./data/google-services.json ]; then
    echo "Using mock google-services.json"
    cp ./mock-google-services.json ./data/google-services.json
fi

if [ ! -f ./data/src/main/res/values/secret.xml ]; then
    echo "Using mock secret.xml"
    mkdir -p ./data/src/main/res/values && cp ./mock_secret.xml ./data/src/main/res/values/secret.xml
fi

./gradlew clean runDomainUnitTests jacoco assembleDebug
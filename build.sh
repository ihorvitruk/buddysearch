#!/bin/bash

# Exit on error
set -e

if [ ! -f ./data/google-services.json ]; then
    echo "Using mock google-services.json"
    cp ./mock-google-services.json ./data/google-services.json
  fi

./gradlew build
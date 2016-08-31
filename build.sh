#!/bin/bash

# Exit on error
set -e

if [ ! -f ./data/google-services.json ]; then
    echo "Using mock google-services.json for data"
    cp ./mock-google-services.json ./data/google-services.json
  fi

  if [ ! -f ./presentation/google-services.json ]; then
    echo "Using mock google-services.json for presentation"
    cp ./mock-google-services.json ./presentation/google-services.json
  fi

./gradlew build
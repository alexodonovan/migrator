#!/usr/bin/env bash
mvn clean install && docker build -t migrator . && ./run.sh
#!/usr/bin/env bash

mkdir -p /tmp/dms

ENV=awsdev
if [[ ! -z "$1" ]] ; then
   ENV=$1
fi

echo --------------------------
echo Profile chosen: $ENV
echo --------------------------

export SPRING_PROFILES_ACTIVE=$ENV

docker run --rm --name ezdms-service -p 8090:8090 -p 5090:5090 \
       -e 'SPRING_APPLICATION_JSON={"app":{"user": "'${USER}'"}}' \
       -e SPRING_PROFILES_ACTIVE=$ENV \
       -e DEBUG=true \
       -e DEBUG_PORT=5090 \
       -v /tmp/dms:/tmp \
       twitter.local:5000/ezscan/ezdms-service

#!/bin/bash

# change working directory to script location
cd "$(dirname "$0")"

java \
  -Xms32m \
  -Xmx128m \
  -XX:+HeapDumpOnOutOfMemoryError \
  -Dfile.encoding=UTF-8 \
  -Dlog4j.configurationFile=file:config/log4j2.xml \
  -jar lib/recorder-0.1-uber.jar \
  --spring.config.location=config/application.properties
#!/bin/bash

# Maven 3 must be installed and be in path

RECORDER_DIR="../recorder"
RECORDER_FAT_JAR="../recorder/target"

BUILD_DIR_MAC="../build/recorder/mac"
BUILD_DIR_WIN="../build/recorder/win"

cd "$RECORDER_DIR"
mvn clean install

if (( $? != 0 ));
then
  echo "Failed to build recorder"
  exit 1
fi

echo "Packaging for MAC"
mkdir -p "$BUILD_DIR_MAC"
#cp

echo "Packaging for WIN"
mkdir -p "$BUILD_DIR"

#!/bin/bash

# This script builds the binaries and places them to be either uploaded or deployed.
# It is primarily used inside a docker container via scripts/package/build-dist.sh
# but it can also be used directly on a developer's machine

set -euxo pipefail

ACCESS=$1 # public or private
REVISION=$2

DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
ROOT="${DIR}/../.."

export AWS_ACCESS_KEY_ID
export AWS_SECRET_ACCESS_KEY

cd $ROOT

if [ "$ACCESS" == "public" ]; then
    sed -i "s|<artifactId>sdk-internal</artifactId>|<artifactId>sdk</artifactId>|" pom.xml
fi
mvn -s $ROOT/scripts/build/settings.xml versions:set -DnewVersion=$REVISION
shift
mvn -s $ROOT/scripts/build/settings.xml $@ # Expected arguments: "clean package" "clean deploy"

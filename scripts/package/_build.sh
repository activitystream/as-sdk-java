#!/bin/bash

set -euxo pipefail

REVISION=$1

DIR=$(cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd)
export ROOT="`cd "${DIR}/../.."; pwd`"

export APP="as-sdk-java"
export DIST_IMAGE="${APP}-dist"
DOCKER_SCRIPTS="$ROOT/scripts/package/docker"

echo "project root is ${ROOT}"

CACHE=" -v `cd ~/.m2 ; pwd`:/root/.m2 "

TS=$(date +"%s")
IMAGE_NAME=$DIST_IMAGE:$TS
docker build -f $DOCKER_SCRIPTS/Dockerfile.dist -t $IMAGE_NAME $ROOT
echo "now running dist container from image $IMAGE_NAME"
CONTAINER_NAME="dist_builder_$(date +"%s")"
set +e
docker run --rm -t --name $CONTAINER_NAME -e AWS_ACCESS_KEY_ID -e AWS_SECRET_ACCESS_KEY $CACHE $IMAGE_NAME $@
OUTCOME=$?
set -e
docker rmi $IMAGE_NAME
exit $OUTCOME
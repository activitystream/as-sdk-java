#!/bin/bash

set -euxo pipefail

REVISION=$1

DIR=$(cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd)
export ROOT="`cd "${DIR}/../.."; pwd`"

$ROOT/scripts/package/_build.sh $REVISION clean deploy -DdeployToMavenCentral=true
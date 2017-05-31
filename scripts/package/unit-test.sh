#!/bin/bash

set -euxo pipefail

DIR=$(cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd)
export ROOT="`cd "${DIR}/../.."; pwd`"

$ROOT/scripts/package/_build.sh 0 clean package
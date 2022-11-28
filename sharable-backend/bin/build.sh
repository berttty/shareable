#!/usr/bin/env bash




BASEDIR=$(cd "$(dirname "$0")"; cd .. ; pwd)

mvn clean package

export REGISTER=localhost:5000
export TAG=back-end
export VERSION=latest

docker build -t ${TAG}/${VERSION}  ${BASEDIR}/.

docker tag ${TAG}/${VERSION} ${REGISTER}/${TAG}/${VERSION}
docker push ${REGISTER}/${TAG}/${VERSION}

unset REGISTER
unset TAG
unset VERSION


#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

    CURRENT_PROFILE=$(curl -s http://localhost/profile)
    echo "> 엔진엑스 Current Profile : $CURRENT_PROFILE"

    echo "> 엔진엑스 Reload"
    sudo service nginx reload
}


#!/bin/bash

if [ "$1" == "" ] ; then
  echo "Missing parameter - client id."
  exit 2
fi

DATE=`date +"%Y%m%d"`
DIR="tigase-monitor_$1_$DATE"

rm -rf $DIR $DIR.tgz

mkdir $DIR
mkdir $DIR/etc
mkdir $DIR/libs
mkdir $DIR/sounds

#set -x

cp target/tigase-monitor*jar-with-dependencies.jar $DIR/libs/tigase-monitor.jar
cp sounds/* $DIR/sounds/
cp bin/monitor.sh $DIR/
cp etc/${1}-init.properties $DIR/etc/monitor.properties
cp ../client-licenses/${1}-monitor.licence $DIR/etc/monitor.licence

tar -czf $DIR.tgz $DIR

mv -f $DIR.tgz packs/

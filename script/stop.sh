#!/bin/bash
PID=`ps aux | grep browser-1.0-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
kill -9 $PID
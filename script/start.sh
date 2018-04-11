#!/bin/bash
home=$(cd "$(dirname "$0")"; pwd)/..
libdir=${home}/lib
for jar in `ls $libdir/*.jar`
    do
        CLASSPATH="${CLASSPATH}:""$jar"
    done
java -cp ${CLASSPATH} com.tone.gf.GFMainFrame
echo "browser start up"
exit 0
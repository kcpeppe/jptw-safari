#!/bin/sh

. ../setEnv.sh

$JAVA -classpath target/classes com.kodewerk.profile.GenerateData 25000000

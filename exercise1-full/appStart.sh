#!/bin/sh

. ../setEnv.sh
. ./jvm.conf
. ./compile.sh



export FLAGS="$COLLECTORS $MEMORY $GC_LOGGING"

echo $FLAGS
echo $APP_PROPS
$JAVA -version

$JAVA $FLAGS $APP_PROPS -cp target/classes com.kodewerk.profile.CheckIntegerTestHarness

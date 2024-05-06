#!/bin/sh

. ../setEnv.sh

export SOURCES="src/main/java/com/kodewerk/cheapdrink/*.java"
export EXPLODED="webapps/lab"

$JAVAC -d target/classes $SOURCES

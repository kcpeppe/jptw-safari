#!/bin/sh

. ../setEnv.sh

$JAVAC -d target/classes  src/main/java/com/kodewerk/profile/*.java

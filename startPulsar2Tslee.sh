#!/bin/bash

#Config-----------------------------------------------------------------------------------
VERSION=1.0.0.0
JAVA="/usr/java/jdk1.8.0_121/bin/java"
LIB="/mcom/pulsar2tslee/lib"
MEM="5G"
THREADS="9"
JMX_PORT="9000"
JMX_HOST=0.0.0.0
DEBUG_PORT="8765"

#-----------------------------------------------------------------------------------------

JAVA_DEFAULT_OPT="
                  -server 
                  -Xss256k 
                  -Xms$MEM
                  -Xmx$MEM
                  -XX:SurvivorRatio=6 
                  -XX:+UseG1GC
				 " 

JAVA_OPTIMAL_OPT="-XX:+UseCompressedOops 
                  -XX:MaxGCPauseMillis=200 
                  -XX:ParallelGCThreads=$THREADS
                  -XX:ConcGCThreads=$THREADS" 

JAVA_JMX_OPT="-Dcom.sun.management.jmxremote=true 
              -Dcom.sun.management.jmxremote.authenticate=false 
              -Dcom.sun.management.jmxremote.ssl=false 
              -Dcom.sun.management.jmxremote.port=$JMX_PORT
              -Djava.rmi.server.hostname=$JMX_HOST" 

#Exec-------------------------------------------------------------------------------------

if [ -e ./stop.sh ]; then
	./stop.sh
fi
			
export LD_LIBRARY_PATH=$LIB:$LD_LIBRARY_PATH

localCLassPath=Pulsar2Tslee-$VERSION.jar

for lib_ in $(ls -R $LIB/*.?ar); do
        localCLassPath="$lib_:$localCLassPath"
done
  
exec $JAVA -classpath "$localCLassPath" $JAVA_DEFAULT_OPT $JAVA_OPTIMAL_OPT $JAVA_JMX_OPT com.ats.dra.Main $1 $2



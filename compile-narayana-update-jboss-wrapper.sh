set -eux
JBOSS_HOME=$WILDFLY_HOME ./compile-narayana-update-jboss.sh justcompile
INIT_DIR=$PWD
for WILDFLY_DIR in $WILDFLY_HOME ~/Software/wfly_copies/*
do
  JBOSS_HOME=$WILDFLY_DIR ./compile-narayana-update-jboss.sh justupdate ""
  cd $INIT_DIR
  cp narayanatracing/target/narayanatracing-5.9.6.Final-SNAPSHOT.jar ${WILDFLY_DIR}/modules/system/layers/base/io/narayana/tracing/main/narayanatracing.jar
done

JBOSS_HOME=$WILDFLY_HOME ./compile-narayana-update-jboss.sh justcompile
for WILDFLY_DIR in $WILDFLY_HOME ~/Software/wfly_copies/*
  do JBOSS_HOME=$WILDFLY_DIR ./compile-narayana-update-jboss.sh justupdate
done

#!/bin/sh
# trying to compile narayana sources
# and then unpack full distro of narayana
# and copy narayana jar files to modules of jboss home
set -eux 
# Usage:
# 1. go to Naryana source code folder
# 2. export JBOSS_HOME=path/to/jboss
# 3. run this script
function updateModuleXml {
  local MODULE_PATH="$1"
  local FILE_TO_COPY_PART="$2"
 
  local FILE_TO_COPY_REGEXP="*${FILE_TO_COPY_PART}*.jar"
  local MODULE_XML="$MODULE_PATH/module.xml"
  local FILE_TO_COPY=`find -name "$FILE_TO_COPY_REGEXP" | grep -ve 'jbossxts-api' | grep -ve '-sources' | grep -ve '-tests' | grep -ve 'WEB-INF' | grep -ve '-javadoc'`
  local FILE_TO_COPY_BASENAME=`basename "$FILE_TO_COPY"` 
 
  [ "x$FILE_TO_COPY" = 'x' ] && echo "[ERROR] there is no file found for '$FILE_TO_COPY_PART'" && exit 2
 
  echo "Copying '$FILE_TO_COPY' to '$MODULE_PATH'"
  cp "$FILE_TO_COPY" "$MODULE_PATH"
 
  # backup module.xml file
  cp -b -f "$MODULE_XML" "$MODULE_PATH/module.xml.bkp"
 
  sed -i "/$FILE_TO_COPY_BASENAME/d" "$MODULE_XML"
 
  grep -qe "<!-- <resource.*${FILE_TO_COPY_PART}" "$MODULE_XML"
  [ $? -ne 0 ] && sed -i "s/\(<resource.*${FILE_TO_COPY_PART}.*jar.*\)/<!-- \1 -->/" "$MODULE_XML"
  sed -i "s|\(<resources>.*\)|\1\n        <resource-root path=\"$FILE_TO_COPY_BASENAME\"/>|" "$MODULE_XML"
  echo "File '$MODULE_XML' was updated with resource '$FILE_TO_COPY_BASENAME'"
}

function updateJboss {
  pushd "$PWD" > /dev/null
  cd narayana-full/target
  find -type d -name '*-full-*' -print0 | xargs -0 -I file rm -rf file
 
  rm -rf ./unzipped
  unzip -q *-full-*.zip -d unzipped
  [ $? -ne 0 ] && echo "Can't unzip -full-*.zip at $PWD" && exit 3
  cd unzipped
 
  TXN_JAR='idlj'
  if [ "x$1" = "xjacorb" ]; then TXN_JAR='-jacorb'; fi
  updateModuleXml "$JBOSS_HOME/modules/system/layers/base/org/jboss/jts/main" "$TXN_JAR"
  updateModuleXml "$JBOSS_HOME/modules/system/layers/base/org/jboss/jts/integration/main" "narayana-jts-integration"
  updateModuleXml "$JBOSS_HOME/modules/system/layers/base/org/jboss/xts/main" 'jbossxts'
  updateModuleXml "$JBOSS_HOME/modules/system/layers/base/org/jboss/xts/main" 'jbosstxbridge'
 
  popd > /dev/null
  pushd rts/at
  updateModuleXml "$JBOSS_HOME/modules/system/layers/base/org/jboss/narayana/rts/main" 'restat-api'
  updateModuleXml "$JBOSS_HOME/modules/system/layers/base/org/jboss/narayana/rts/main" 'restat-integration'
  updateModuleXml "$JBOSS_HOME/modules/system/layers/base/org/jboss/narayana/rts/main" 'restat-bridge'
  updateModuleXml "$JBOSS_HOME/modules/system/layers/base/org/jboss/narayana/rts/main" 'restat-util'
 
  popd > /dev/null
  pushd compensations
  updateModuleXml "$JBOSS_HOME/modules/system/layers/base/org/jboss/narayana/compensations/main" 'compensations'
 
  popd > /dev/null
}
 

if [ "x$1" == "x-h" ] || [ "x$1" == "xhelp" ] || [ "x$1" == "x--help" ]; then
  echo "Usage:"
  echo " 1. go to Naryana source code folder"
  echo " 2. export JBOSS_HOME=path/to/jboss"
  echo " 3. run this script: $0"
  echo
  echo "`basename $0` [-h|help] [skip|nocompile|soft]"
  echo " help  printing this help"
  echo " nocompile  not compiling the source codes"
  echo " justcompile  only compile, no copy source"
  echo "When JBOSS_HOME is not specified 'unset JBOSS_HOME' then only compilation is run"
  echo
  exit 0
fi

[ "x$JBOSS_HOME" = "x" ] || [ ! -e "$JBOSS_HOME" ] && echo "Property JBOSS_HOME:'$JBOSS_HOME' does not point to any existing directory. Skipping the module update phase." && exit 3
JBOSS_HOME=${JBOSS_HOME%/}

if [ "x$1" == "xjustupdate" ]; then
  updateJboss "$1"
  exit 0
fi

[ ! -e "$PWD/ArjunaCore" ] && echo "You are probably not at directory with Narayana sources" && exit 4
 
# when first argument of the script is skip then do not compile
if [ "x$1" != "xnocompile" ] && [ "x$1" != "xno-compile" ] && [ "x$1" != "xskip" ]; then
  rm -rf narayana-full/target
  ./build.sh clean install -Pcommunity -DskipTests -Didlj-enabled=true
  [ $? -ne 0 ] && echo "[ERROR] Compilation failed" && exit 1
fi
 
if [ "x$1" == "xjustcompile" ]; then
  exit 0
fi

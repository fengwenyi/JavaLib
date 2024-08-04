#!/bin/bash
version=`awk '/<version>[^<]+<\/version>/{gsub(/<version>|<\/version>/,"",$1);print $1;exit;}' pom.xml`
echo $version
# mvn clean deploy -P sonatype-oss-release -DskipTests
mvn clean deploy -P release
git tag -a $version -m "v$version"
git push origin $version
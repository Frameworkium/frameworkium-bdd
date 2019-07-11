#!/usr/bin/env bash

set -e

export OUTZIP=frameworkium-bdd-with-deps.zip

if [[ -f $OUTZIP ]]; then
    rm -v $OUTZIP
fi

# Download geckodriver
export GECKO_VER=0.24.0
if [[ ! -f geckodriver-v${GECKO_VER}-linux64.tar.gz ]]; then
    wget https://github.com/mozilla/geckodriver/releases/download/v${GECKO_VER}/geckodriver-v${GECKO_VER}-linux64.tar.gz
    wget https://github.com/mozilla/geckodriver/releases/download/v${GECKO_VER}/geckodriver-v${GECKO_VER}-win64.zip
fi

# Download chromedriver
export CHROME_DRIVER_VER=75.0.3770.90
if [[ ! -f chromedriver_linux64.zip ]]; then
    wget http://chromedriver.storage.googleapis.com/${CHROME_DRIVER_VER}/chromedriver_linux64.zip
    wget http://chromedriver.storage.googleapis.com/${CHROME_DRIVER_VER}/chromedriver_win32.zip
fi

export SELENIUM_MAJ_VER=3.141
export SELENIUM_VER=${SELENIUM_MAJ_VER}.59
export SELENIUM_JAR=selenium-server-standalone-${SELENIUM_VER}.jar
if [[ ! -f ${SELENIUM_JAR} ]]; then
    wget https://selenium-release.storage.googleapis.com/${SELENIUM_MAJ_VER}/${SELENIUM_JAR}
fi

export REPO=tmp-mvn-repo

#mvn dependency:sources dependency:resolve -Dclassifier=javadoc -Dmaven.repo.local=${REPO}
#mvn clean install -Dthreads=2 -Dheadless -Dbrowser=chrome -Dmaven.repo.local=${REPO}
#mvn allure:report -Dmaven.repo.local=${REPO}

zip -r $OUTZIP src/ ${REPO}/ \
    pom.xml README.md geckodriver-* chromedriver_* ${SELENIUM_JAR}

ls -lah $OUTZIP

if [[ $(find -maxdepth 1 -name "*.zip" -size +100M) == "./$OUTZIP" ]]; then
    echo "ok"
else
    echo "failed"
    exit 1
fi

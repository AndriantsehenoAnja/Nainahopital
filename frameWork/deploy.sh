#!/bin/bash

# Définition des variables
APP_NAME="NainaExtrat"
SRC_DIR="src/main/java"
WEB_DIR="src/main/webapp"
BUILD_DIR="build"
LIB_DIR="lib"
TOMCAT_WEBAPPS="/home/anja/Documents/ITU/Naina/s5/apache-tomcat-10.1.60/webapps"

# Nettoyage et création du répertoire temporaire
# rm -rf $BUILD_DIR
mkdir -p $BUILD_DIR/WEB-INF/classes

# copie de web.xml et cree lib
cp *.xml $WEB_DIR
cp *.xml $BUILD_DIR/WEB-INF
mkdir -p $BUILD_DIR/WEB-INF/$LIB_DIR
cp $LIB_DIR/*.jar  $BUILD_DIR/WEB-INF/$LIB_DIR

# Compilation des fichiers Java avec le JAR des Servlets
find $SRC_DIR -name "*.java" > sources.txt

CLASSPATH=""

for jar in $LIB_DIR/*.jar
do
    CLASSPATH="$CLASSPATH:$jar"
done

echo "Classpath : $CLASSPATH"

javac \
-cp "$CLASSPATH" \
-d $BUILD_DIR/WEB-INF/classes \
@sources.txt


# Copier les fichiers web (web.xml, JSP, etc.)
cp -r $WEB_DIR/* $BUILD_DIR/

# Générer le fichier .war dans le dossier build
cd $BUILD_DIR || exit
jar -cvf $APP_NAME.war * 
cd ..

# Déploiement dans Tomcat
cp -f $BUILD_DIR/$APP_NAME.war $TOMCAT_WEBAPPS/

echo ""

echo "Déploiement terminé. Redémarrez Tomcat si nécessaire."

echo ""

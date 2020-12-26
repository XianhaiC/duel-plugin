#!/usr/bin/bash

# compile the main class with the spigot jar
javac -cp ../../libraries/spigot/spigot-1.16.4.jar:/usr/share/java/sqlite-jdbc/sqlite-jdbc-3.27.2.1.jar src/com/eventhandlers/*.java src/com/commands/*.java src/com/*.java -d classes
# bundle together into an archive
jar cvf builds/DuelPlugin.jar -C classes .

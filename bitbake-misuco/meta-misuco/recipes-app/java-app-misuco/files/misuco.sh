#!/bin/sh
# Definition einer Lock-Datei im RAM-Verzeichnis
LOCKFILE="/tmp/misuco.lock"

# Versuche, die Lock-Datei exklusiv zu sperren. 
# -n (non-blocking) sorgt dafür, dass das Skript sofort beendet wird, wenn die App bereits läuft.

exec 9>"$LOCKFILE"
if ! flock -n 9; then
    echo "Applikation MISUCO läuft bereits. Breche ab."
    exit 1
fi

# Java-Applikation starten
exec java -jar /root/maven-jar-plugin-1.0-SNAPSHOT.jar &

# lautstaerke setzen
exec amixer -c Headphones set PCM 91%


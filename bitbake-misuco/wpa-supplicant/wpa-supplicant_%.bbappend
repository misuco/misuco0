# Fügt den lokalen "files"-Ordner an erster Stelle der Suchpfade hinzu
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Yocto erkennt die wpa_supplicant.conf-sane automatisch aus den Suchpfaden
# und überschreibt damit das Standard-Dokument von openembedded-core.

SUMMARY = "Custom systemd service for wpa_supplicant on wlan0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = "file://wpa_supplicant-wlan0.service"

# Name der Service-Datei, die aktiviert werden soll
SYSTEMD_SERVICE:${PN} = "wpa_supplicant-wlan0.service"

# Automatischen Start beim Booten aktivieren
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
    # Erstellt das Zielverzeichnis
    install -d ${D}${systemd_system_unitdir}
    
    # Nutzt ${UNPACKDIR} statt ${WORKDIR} für neuere Yocto-Versionen
    if [ -f ${UNPACKDIR}/wpa_supplicant-wlan0.service ]; then
        install -m 0644 ${UNPACKDIR}/wpa_supplicant-wlan0.service ${D}${systemd_system_unitdir}
    else
        install -m 0644 ${WORKDIR}/wpa_supplicant-wlan0.service ${D}${systemd_system_unitdir}
    fi
}

# Packt die Service-Datei in das finale Paket
FILES:${PN} += "${systemd_system_unitdir}/wpa_supplicant-wlan0.service"


SUMMARY = "Installation der misuco Java App und Konfiguration"
LICENSE = "CLOSED"

SRC_URI = " \
    file://asound.conf \
    file://misuco.desktop \
    file://maven-jar-plugin-1.0-SNAPSHOT.jar;unpack=false \
    file://misuco.sh \
"

S = "${UNPACKDIR}"

do_install() {
    # Zielverzeichnisse anlegen
    install -d ${D}${sysconfdir}
    install -d ${D}/root
    install -d ${D}${datadir}/applications

    # Dateien kopieren und Rechte setzen
    install -m 0644 ${UNPACKDIR}/asound.conf ${D}${sysconfdir}/asound.conf
    install -m 0644 ${UNPACKDIR}/misuco.desktop ${D}${datadir}/applications/misuco.desktop
    install -m 0755 ${UNPACKDIR}/misuco.sh ${D}/root/misuco.sh
    install -m 0755 ${UNPACKDIR}/maven-jar-plugin-1.0-SNAPSHOT.jar ${D}/root/maven-jar-plugin-1.0-SNAPSHOT.jar
}

# Dateien für das Paket freigeben (Syntax ab Yocto Kirkstone/später)
FILES:${PN} += " \
    ${sysconfdir}/asound.conf \
    ${datadir}/applications/misuco.desktop \
    /root/maven-jar-plugin-1.0-SNAPSHOT.jar \
    /root/misuco.sh \
"

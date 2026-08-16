# Yocto Linux Java11 misuco demo

von c1audio@x21.ch | 16. August 2026

Im folgenden wird kurzmöglichst beschrieben, wie ein Yocto-Linux für Raspberry Pi 3 gebaut werden kann und die Java Applikation “Misuco” darauf läuft.
Als Build-Umgebung wird ein Ubuntu-24 System verwendet:

~~~
c1@ai:~$ cat /etc/os-release
...
VERSION="24.04.4 LTS (Noble Numbat)"
...
~~~

Gemäss Anleitung i werden zuerst benötigte Pakete installiert:
sudo apt-get install build-essential chrpath cpio debianutils diffstat file gawk gcc git iputils-ping libacl1 libcrypt-dev locales python3 python3-git python3-jinja2 python3-pexpect python3-pip python3-subunit socat texinfo unzip wget xz-utils zstd

Hauptrepository klonen und initialisieren:
~~~
mkdir ~/yocto
cd ~/yocto
git clone https://git.openembedded.org/bitbake 
./bitbake/bin/bitbake-setup init
~~~

Repositories für Raspberry PI und Java Runtime klonen:
~~~
cd ~/yocto/bitbake-builds/poky-master/layers
git clone https://git.yoctoproject.org/meta-raspberrypi
git clone https://github.com/lucimber/meta-openjdk-temurin.git
~~~

Repository für Applikationsspezifische Dateien klonen und Dateien in die Build Umgebung kopieren:
~~~
cd ~/yocto
git clone https://github.com/misuco/misuco0.git 
cd misuco0
git checkout 104-yocto-raspi
cp bb/local.conf ../bitbake-builds/poky-master/layers/openembedded-core/build/conf/
cp -r bb/meta-misuco ../bitbake-builds/poky-master/layers/
cp -r bb/meta- wpa-supplicant* ../bitbake-builds/poky-master/layers/meta-raspberrypi/recipes-connectivity
~~~

Layers hinzufügen:
~~~
cd ~/yocto/ bitbake-builds/poky-master/layers
bitbake-layers add-layer meta-raspberrypi
bitbake-layers add-layer meta-openjdk-temurin
bitbake-layers add-layer meta-misuco
~~~

Build ausführen:
~~~
cd ~/yocto/bitbake-builds/poky-master/layers/openembedded-core/
source oe-init-build-env
bitbake core-image-sato
~~~

Generiertes image auf SD-Karte kopieren (Datum in wic.bz2 Datei sowie device Name sdX entsprechend anpassen)
~~~
cd ~/yocto/bitbake-builds/poky-master/layers/openembedded-core/build/tmp/deploy/images/
sudo bzip2 -dc core-image-sato-raspberrypi3.rootfs-20260816171909.wic.bz2 | sudo dd of=/dev/sdX bs=4M status=progress conv=fsync
~~~

Die SD-Karte kann nun ins Raspberry Pi eingesteckt werden und sollte booten.
Nach dem Booten erscheint der GNOME-Desktop und die Applikatio kann über ein Icon gestartet werden.

i https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html

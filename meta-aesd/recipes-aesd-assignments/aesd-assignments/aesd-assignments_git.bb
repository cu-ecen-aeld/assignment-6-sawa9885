# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-3-sawa9885.git;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "16c032b3f521c17adfbc663b8f1e1872b176d28a"

S = "${UNPACKDIR}/git/server"

FILES:${PN} += "${bindir}/aesdsocket ${sysconfdir}/init.d/aesdsocket"

TARGET_LDFLAGS += "-pthread"

inherit update-rc.d
INITSCRIPT_NAME = "aesdsocket"
INITSCRIPT_PARAMS = "defaults 99"

do_configure () {
	:
}

do_compile () {
	oe_runmake CC="${CC}" CFLAGS="${CFLAGS}" LDFLAGS="${LDFLAGS}"
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/aesdsocket ${D}${bindir}/aesdsocket
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/aesdsocket
}

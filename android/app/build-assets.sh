set -eu
BASEDIR=${JCMS_BASEDIR:-'/opt/jcms'}
APPNAME=${JCMS_WEBAPP:-'default'}
srcdir=${BASEDIR}/${APPNAME}
dstdir=src/main/assets/${APPNAME}
mkdir -p ${dstdir}
echo "rsync ${srcdir} -> ${dstdir}"
rsync -vax --delete-before ${srcdir}/ ${dstdir}/

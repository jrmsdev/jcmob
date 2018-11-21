set -eu
BASEDIR=${JCMS_BASEDIR:-'/opt/jcms'}
APPNAME=${JCMS_WEBAPP:-'default'}
srcdir=${BASEDIR}/${APPNAME}
dstdir=src/main/assets
set -x
mkdir -p ${dstdir}
rsync -vax --delete-before ${srcdir}/ ${dstdir}/

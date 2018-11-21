// gomobile bind -lang=java
package jcmob

import (
	"os"

	"github.com/jrmsdev/go-jcms/lib/jcms"
)

func Listen() string {
	return jcms.Listen()
}

func Serve() {
	jcms.Serve()
}

func Stop() {
	jcms.Stop()
}

func SetBaseDir(path string) {
	if err := os.Setenv("JCMS_BASEDIR", path); err != nil {
		panic(err)
	}
}

func SetDataDir(path string) {
	if err := os.Setenv("JCMS_DATADIR", path); err != nil {
		panic(err)
	}
}

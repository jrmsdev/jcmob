// gomobile bind -target=android -javapkg go

package jcmob

import (
	"os"

	"github.com/jrmsdev/go-jcms/lib/jcms"
)

var donec = make(chan string)

func Start() string {
	uri := jcms.Listen()
	go func() {
		jcms.Serve()
		donec <- "done"
	}()
	return uri
}

func Stop() {
	jcms.Stop()
}

func WaitDone() string {
	return <-donec
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

func SetAppName(name string) {
	if err := os.Setenv("JCMS_WEBAPP", name); err != nil {
		panic(err)
	}
}

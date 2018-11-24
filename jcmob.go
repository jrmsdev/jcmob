// gomobile bind -target=android -javapkg go

package jcmob

import (
	"os"

	"github.com/jrmsdev/go-jcms/lib/jcms"
)

var donec = make(chan string)

func Start() string {
	jcms.SetAssetManager(newAssetManager())
	uri := jcms.Listen("127.0.0.1:0")
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

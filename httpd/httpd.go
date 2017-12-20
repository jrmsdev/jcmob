package httpd

import (
    "log"
    "time"
    "net"
    "net/http"
)

var addr = "127.0.0.1:0"
var servemux = http.NewServeMux ()

var server = &http.Server{
	Addr:           addr,
	Handler:        servemux,
	ReadTimeout:    10 * time.Second,
	WriteTimeout:   10 * time.Second,
	MaxHeaderBytes: 1 << 20,
}

func Start () string {
	listener, err := net.Listen ("tcp4", addr)
	if err != nil {
		log.Fatalln (err)
	}
    go func() {
        serr := server.Serve (listener)
        if serr != nil {
            log.Fatalln (serr)
        }
    }()
	return listener.Addr ().String ()
}

func Stop () {
    server.Close ()
}

func HandleFunc (prefix string, fn func(http.ResponseWriter, *http.Request)) {
    servemux.HandleFunc (prefix, fn)
}

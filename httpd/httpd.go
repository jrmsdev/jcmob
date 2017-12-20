package httpd

import (
    "log"
    "time"
    "net"
    "net/http"
)

var Addr = "127.0.0.1:7666"
var servemux = http.NewServeMux ()

var server = &http.Server{
	Addr:           Addr,
	Handler:        servemux,
	ReadTimeout:    10 * time.Second,
	WriteTimeout:   10 * time.Second,
	MaxHeaderBytes: 1 << 20,
}

func Start () {
    listener, err := net.Listen ("tcp4", Addr)
    if err != nil {
        log.Fatalln (err)
    }
    err = server.Serve (listener)
    if err != nil {
        log.Fatalln (err)
    }
    //~ return listener.Addr ().String ()
}

func Stop () {
    server.Close ()
}

func HandleFunc (prefix string, fn func(http.ResponseWriter, *http.Request)) {
    servemux.HandleFunc (prefix, fn)
}

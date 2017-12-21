package httpd

import (
    "log"
    "time"
    "net"
    "net/http"
)

var addr = "127.0.0.1:7666"
var servemux = http.NewServeMux ()
var listener net.Listener

var server = &http.Server{
	Addr:           addr,
	Handler:        servemux,
	ReadTimeout:    10 * time.Second,
	WriteTimeout:   10 * time.Second,
	MaxHeaderBytes: 1 << 20,
}

func Listen () string {
    var err error
    listener, err = net.Listen ("tcp4", addr)
    if err != nil {
        log.Fatalln (err)
    }
    return "http://" + listener.Addr ().String () + "/"
}

func Serve () {
    if listener == nil {
        log.Fatalln ("nil listener... call httpd.Listen() first")
    }
    var err error
    err = server.Serve (listener)
    if err != nil {
        log.Fatalln (err)
    }
}

func Stop () {
    server.Close ()
}

func HandleFunc (prefix string, fn func(http.ResponseWriter, *http.Request)) {
    servemux.HandleFunc (prefix, fn)
}

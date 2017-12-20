package jcmob

import (
    "github.com/jrmsdev/jcmob/internal/httpd"
    _ "github.com/jrmsdev/jcmob/internal/webapps"
)

func Addr () string {
    return httpd.Addr
}

func Start () {
    println ("http://" + Addr () + "/")
    httpd.Start ()
}

func Stop () {
    httpd.Stop ()
}

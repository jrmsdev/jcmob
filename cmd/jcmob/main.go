package main

import (
    "github.com/zserge/webview"
    "github.com/jrmsdev/jcmob/jcmob"
)

func main () {
    println ("listen")
    uri := "http://" + jcmob.Listen () + "/"
    println ("listen uri:", uri)

    println ("serve")
    go func() {
        jcmob.Serve ()
    }()

    resize := true
    webview.Open ("JCMobile", uri, 800, 600, resize)

    println ("stop")
    jcmob.Stop ()
}

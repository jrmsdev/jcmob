package main

import (
    "github.com/zserge/webview"
    "github.com/jrmsdev/jcmob/jcmob"
)

func main () {
    println ("starting...")
    resize := true
    go func() {
        jcmob.Start ()
    }()
    webview.Open ("JCMobile", "http://127.0.0.1:7666/", 800, 600, resize)
}

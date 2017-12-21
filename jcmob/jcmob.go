// gomobile bind -lang=java
package jcmob

import (
    "github.com/jrmsdev/jcmob/internal/httpd"
    _ "github.com/jrmsdev/jcmob/internal/webapps"
)

func Listen () string {
    return httpd.Listen ()
}

func Serve () {
    httpd.Serve ()
}

func Stop () {
    httpd.Stop ()
}

// gomobile bind -lang=java
package jcmob

import (
    "github.com/jrmsdev/jcmob/internal/httpd"
    _ "github.com/jrmsdev/jcmob/internal/webapps"
)

func Start () string {
    return httpd.Start ()
}

func Stop () {
    httpd.Stop ()
}

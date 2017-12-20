package jcmob

import (
    "github.com/jrmsdev/jcmob/httpd"
    _ "github.com/jrmsdev/jcmob/webapps"
)

func Start () string {
	return httpd.Start ()
}

func Stop () {
    httpd.Stop ()
}

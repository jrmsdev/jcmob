package webapps

import (
    "net/http"
    "github.com/jrmsdev/jcmob/internal/httpd"
)

func init() {
    httpd.HandleFunc ("/", func(w http.ResponseWriter, r *http.Request) {
        w.Write([]byte("YEAH!!!"))
    })
}

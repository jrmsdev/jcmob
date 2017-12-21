// gomobile bind -lang=java
package jcmob

import (
    "github.com/jrmsdev/go-jcms/jcms"
)

func Listen () string {
    return jcms.Listen ()
}

func Serve () {
    jcms.Serve ()
}

func Stop () {
    jcms.Stop ()
}

package main

import (
	"fmt"

	"github.com/jrmsdev/jcmob"
)

func main() {
	defer jcmob.Stop()
	fmt.Println(jcmob.Start())
	fmt.Println(jcmob.WaitDone())
}

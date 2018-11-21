package jcmob

import (
	"io/ioutil"

	"golang.org/x/mobile/asset"
)

type assetManager struct{}

func newAssetManager() *assetManager {
	return &assetManager{}
}

func (m *assetManager) ReadFile(name string) ([]byte, error) {
	fh, err := asset.Open(name)
	if err != nil {
		return nil, err
	}
	defer fh.Close()
	return ioutil.ReadAll(fh)
}

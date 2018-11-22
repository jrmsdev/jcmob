package jcmob

import (
	"path/filepath"
	"io/ioutil"

	"golang.org/x/mobile/asset"
)

type assetManager struct{}

func newAssetManager() *assetManager {
	return &assetManager{}
}

func (m *assetManager) ReadFile(name ...string) ([]byte, error) {
	fh, err := asset.Open(filepath.Join(name...))
	if err != nil {
		return nil, err
	}
	defer fh.Close()
	return ioutil.ReadAll(fh)
}

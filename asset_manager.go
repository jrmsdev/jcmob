package jcmob

import (
	"os"
	"io/ioutil"
	"path/filepath"

	"github.com/jrmsdev/go-jcms/lib/jcms/api"
	"golang.org/x/mobile/asset"
)

type assetManager struct{}

func newAssetManager() *assetManager {
	return &assetManager{}
}

func (m *assetManager) Open(filename string) (api.AssetFile, error) {
	return asset.Open(filename)
}

func (m *assetManager) Stat(filename string) (os.FileInfo, error) {
	fn := filepath.Join("assets", filename)
	return os.Stat(fn)
}

func (m *assetManager) ReadFile(name string) ([]byte, error) {
	fh, err := asset.Open(name)
	if err != nil {
		return nil, err
	}
	defer fh.Close()
	return ioutil.ReadAll(fh)
}

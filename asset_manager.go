package jcmob

import (
	"os"
	"io/ioutil"
	"time"

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
	return newFileInfo(filename)
}

func (m *assetManager) ReadFile(name string) ([]byte, error) {
	fh, err := asset.Open(name)
	if err != nil {
		return nil, err
	}
	defer fh.Close()
	return ioutil.ReadAll(fh)
}

var modTime time.Time

func init() {
	modTime = time.Now()
}

type fileInfo struct{
	name string
	size int64
}

func newFileInfo(name string) (*fileInfo, error) {
	fh, err := asset.Open(name)
	if err != nil {
		return nil, err
	}
	buf, readErr := ioutil.ReadAll(fh)
	if readErr != nil {
		return nil, readErr
	}
	sz := int64(len(buf))
	buf = nil
	return &fileInfo{name, sz}, nil
}

func (i *fileInfo) Name() string {
	return i.name
}

func (i *fileInfo) Size() int64 {
	return i.size
}

func (i *fileInfo) Mode() os.FileMode {
	return os.ModeType
}

func (i *fileInfo) ModTime() time.Time {
	return modTime
}

func (i *fileInfo) IsDir() bool {
	return false
}

func (i *fileInfo) Sys() interface{} {
	return nil
}

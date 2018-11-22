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
var infoCache map[string]*fileInfo

func init() {
	modTime = time.Now()
	infoCache = make(map[string]*fileInfo)
}

type fileInfo struct{
	name string
	size int64
}

func newFileInfo(name string) (*fileInfo, error) {
	_, found := infoCache[name]
	if !found {
		fh, err := asset.Open(name)
		if err != nil {
			return nil, err
		}
		defer fh.Close()
		buf, readErr := ioutil.ReadAll(fh)
		if readErr != nil {
			return nil, os.ErrNotExist
		}
		infoCache[name] = &fileInfo{name, int64(len(buf))}
		buf = nil
	}
	return infoCache[name], nil
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

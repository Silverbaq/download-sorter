# download-sorter
This is a kotlin application, which will sort through a folder for movies and series, then copy them to separated folders of choice.

## Docker
The application is setup to be run in docker.
### Docker build 
`docker build -t download-sorter . `

### Docker run
```
sudo docker run -d \
    --name download-sorter \
    -v /DOWNLOAD-PATH/:/downloads \
    -v /MOVIES-PATH/:/movies \
    -v /SERIES-PATH/:/series \
    download-sorter
```
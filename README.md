#Stream HLS packets to Kafka


nginx docker command

docker run -d -p 1935:1935 -p 1936:1936 -p 8081:8080 -e PUID=$UID -e PGID=0 -e SSL_DOMAIN='rtmp.domain.loc' -v /your/local/assets/:/assets jamiephonic/rtmps-hls-server:latest



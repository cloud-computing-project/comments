# RSO: comments microservice

## Prerequisites

```bash
docker run -d --name rso-comments -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=comment -p 5432:5432 postgres:latest
```

## Run application in Docker

```bash
docker run -p 8082:8082 -e KUMULUZEE_CONFIG_ETCD_HOSTS=http://192.168.99.100:2379 amela/comments
```

## Travis status 
[![Build Status](https://travis-ci.org/cloud-computing-project/comments.svg?branch=master)](https://travis-ci.org/cloud-computing-project/comments)
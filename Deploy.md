# 概要
## 映画検索サービス
### localhost:8080/movie
* Java
* Spring boot
* Swagger (RESTFul)
* Elasticsearc
    - マスタ * 1
    - クラスタ * 3
* Kibana(localhost:5601)
* Load Balancer無し(時間のため)
* Cache無し(時間のため)


## ユーザー気に入りサービス
### localhost:8081/favorites

* Java
* Spring boot
* Swagger (RESTFul)
* Postgresql
* Flyway(DDL管理)
* Adminer(localhost:8088)
* Load Balancer無し(時間のため)
* Cache無し(時間のため)

### Basic Authアカウント
 username: admin  password: pwd

 username: bob password: alice

# Windows
Dockerインストール必要
## WSL2の場合
### 実行
Shellターミナルで
```
wsl -d docker-desktop
sysctl -w vm.max_map_count=262144
cd ${ProjectDir}/monstart
docker-compose up -d
```
### 終了
```
docker-compose down
```
発行します。

#  MacOsの場合
Dockerインストール必要
### 実行
Shellターミナルで
```
screen ~/Library/Containers/com.docker.docker/Data/vms/0/tty
Enter押下する
```

```
sysctl -w vm.max_map_count=262144
Ctrl a d押下する
```
```
cd ${ProjectDir}/monstart
docker-compose up -d
```
### 終了
```
docker-compose down
```
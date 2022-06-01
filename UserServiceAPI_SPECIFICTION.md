## 規模想定
省略

## キャパシティー見積もり
省略
### トラフィック
省略
### ストレージ
省略

## API定義
```
GET /favorites
POST /favorites/:id
```

## エンティティ
```
Movie
{
  "id": "string",
  "title": "string",
  "genres": [
    "string"
  ],
  "actors": [
    "string"
  ],
  "year": "2015"
}
```

```
User
{
    "username": "string",
    "password": "string",
    "enabled": boolean
}
```

```
Authorities
{
    "username": "string",
    "role": "string"
}
```

```
Favorite
{
    "username": "string",
    "movieId": "string"
}
```

## レスポンススキーマ
省略

## データベーススキーマ設計
Postgresql
```
User
{
    "username": "string", (PK)
    "password": "string", (Bcrypt暗号化)
    "enabled": boolean
}
```
```
Authorities
{   
    "username": "string",  (username + role = PK)
    "role": "string"      
}
```
```
Favorite
{   
    "id": int,  (PK)
    "username": "string", (username + movie_id = PK)
    "movie_id": "string"
}
```

### RDBにした理由
* 気に入り機能には一貫性を求めてもいると思います。
* 映画の詳細情報は映画検索サービスに回すので、パフォマンスの問題がなさそうです。
* ユーザーに対するサービスとして、今後は様々な機能を拡張することを視野に入れています。

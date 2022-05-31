## 規模想定
* ユーザー規模：1百万DAU
* クエリで映画を検索する頻度：一人当たり5回/day
* 特定の映画を検索する頻度：一人当たり5回/day
* 映画データ規模：500万件
* 映画データサイズ：300bytes/件

## キャパシティー見積もり
### トラフィック
* 映画検索/取得:
```
1M * (5 + 5) / (24時間 * 3600 秒) = ~115 QPS
```

### ストレージ
* 映画データ：
```
500M * 300bytes = 150GB
```

## API定義
```
GET /movies?search={search}
GET /movies/:id
GET /favorites
POST /favorites/:id
```
*詳細はSwagger API参考する*

## エンティティ
Movie
```
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
レスポンススキーマ
```
TODO
```

## データベーススキーマ設計
TODO
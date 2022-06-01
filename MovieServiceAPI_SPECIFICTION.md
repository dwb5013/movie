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

  Query parameters
    page (optional)
      何ページ目からを指定する
    pageSize (optional)
      ページサイズを指定する
    search (required)
      検査キーワード(全文検索)

  Responses
    200 成功、ペーロード有り
    successful operation
  
    204 成功、ペーロード無し
    No Movie Found

    405 パラメータキーがない 
    (/movies?search=) ==> 流行っている映画を返す
    (/movies) ==> 405返す
    Invalid input

```
```
GET /movies/:id

  Path parameters
    movieId (required)
      映画のIDを指定する

  Responses
    200 成功、ペーロード有り
    successful operation
  
    405 データ無し(ここはクエリではなく、直接リソースを請求するので、なかったらエラーとして返す)
    Invalid input
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

レスポンススキーマ
```
Movie
  id (optional)
    String
  title
    String
  year
    Integer
  cast
    array[String]
  genres
    array[String]
```

## データベーススキーマ設計
Elasticsearch
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
### NOSQLにした理由
* 映画の解説を全文検索することや複雑な検索条件で映画検索することが多いと気がします。
* 書き込みより読み込みの方が多いと思います。
* 理想的にはCQRSパターンでリードとライトを分離した方がいいと思います。
* 映画登録機能に対して、一貫性を求められてRDBにします。
* 映画検索機能に対して、パフォマンスの観点からJOIN文やB-Treeの全文検索を回避するため、NoSQLにします。
* RDB-NoSQLの間でデータを同期するため、logstashやPGSyncなどのミドルウェアが必要。
* スケーラビリティの観点から、NoSQLの方が拡張しやすいですが、データ同期処理で少し遅延があります。
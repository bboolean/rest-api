# rest-api

A super simple MongoDB REST API example that is JSON-based even though EDN might be better.

## Prerequisites

Please set up a local MongoDB server.

## Run

```
$ lein run
```

## Curl server

Read many
```
$ curl -X GET -H "Content-Type:application/json" http://localhost:8080/books
```

Read one
```
$ curl -X GET -H "Content-Type:application/json" http://localhost:8080/books/09e7a9023893b239823c8e8d
```

Create
```
$ curl -X POST -H "Content-Type:application/json" --data "{\"title\":\"The Joyful Tree Stump\", \"year\": 2005, \"rating\": 3}" http://localhost:8080/books
```

Update
```
$ curl -X PUT -H "Content-Type:application/json" --data "{\"title\":\"The Joyful Tree Stump\", \"year\": 2005, \"rating\": 5}" http://localhost:8080/books/09e7a9023893b239823c8e8d
```

Delete
```
$ curl -X DELETE -H "Content-Type:application/json" http://localhost:8080/books/09e7a9023893b239823c8e8d
```

## Tests

```
$ lein test
```

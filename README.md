# springboot-logback-logstash-demo
Collect Access Log and Running Log, Support SpringBoot 2.x

## logstash
### 下载安装Logstash
官网下载安装: [Logstash](https://www.elastic.co/guide/en/logstash/current/installing-logstash.html)

使用配置文件启动，logstash.conf：
```
input {
  tcp {
    port => 4560
    codec => json
  }
}
output {
  stdout {
    codec => rubydebug
  }
}

```

### 要求
- jdk 11+
- gradle 6+

### logstash输出结果部分展示

```
{
        "httpStatus" => "200",
    "requestMapping" => "/foo/person/{name}",
              "type" => "access",
       "requestHost" => "localhost:56529",
              "port" => 56528,
        "requestURI" => "/foo/person/zhangsan",
          "@version" => "1",
     "executionTime" => "16",
                "ip" => "127.0.0.1",
        "createTime" => "2020-09-29 10:55:34",
              "host" => "localhost",
        "@timestamp" => 2020-09-29T02:55:34.022Z,
               "pid" => "31890",
        "httpMethod" => "GET"
}
{
        "httpStatus" => "200",
    "requestMapping" => "/foo/upload",
     "requestParams" => "{\"name\":\"zhangsan\"}",
              "type" => "access",
       "requestHost" => "localhost:56529",
              "port" => 56528,
        "requestURI" => "/foo/upload",
          "@version" => "1",
     "executionTime" => "10",
                "ip" => "127.0.0.1",
        "createTime" => "2020-09-29 10:55:33",
              "host" => "localhost",
        "@timestamp" => 2020-09-29T02:55:33.925Z,
               "pid" => "31890",
        "httpMethod" => "POST"
}
{
         "level" => "INFO",
          "type" => "runningLog",
          "port" => 56521,
      "@version" => "1",
        "thread" => "http-nio-auto-1-exec-4",
    "createTime" => "2020-09-29 10:55:34",
          "host" => "localhost",
    "@timestamp" => 2020-09-29T02:55:34.012Z,
           "pid" => "31890",
       "message" => "query person zhangsan",
        "logger" => "com.snaildrum.demo.DemoApplication"
}


```




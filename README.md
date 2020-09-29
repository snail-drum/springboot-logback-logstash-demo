# springboot-logback-logstash-demo
Collect Access Log and Running Log, Support SpringBoot 2.x<br/>
使用logback收集springboot应用的运行日志和访问日志，并输出到logstash中

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

## 要求
- jdk 11+
- gradle 6+

## logstash输出结果部分展示

```
{
        "httpStatus" => "200",
    "requestMapping" => "/foo/bar",
     "requestParams" => "{\"name\":\"test\",\"age\":18}",
              "type" => "AccessLog",
       "requestHost" => "localhost:52271",
              "port" => 52267,
        "requestURI" => "/foo/bar",
          "@version" => "1",
     "executionTime" => "140",
                "ip" => "127.0.0.1",
        "createTime" => "2020-09-29 11:47:08",
              "host" => "localhost",
        "@timestamp" => 2020-09-29T03:47:08.820Z,
               "pid" => "42358",
        "httpMethod" => "POST"
}
{
         "level" => "INFO",
          "type" => "RunningLog",
          "port" => 52254,
      "@version" => "1",
        "thread" => "http-nio-auto-1-exec-3",
    "createTime" => "2020-09-29 11:47:08",
          "host" => "localhost",
    "@timestamp" => 2020-09-29T03:47:08.818Z,
           "pid" => "42358",
       "message" => "add person Person{name='test', age=18}",
        "logger" => "com.snaildrum.demo.DemoApplication"
}
{
         "level" => "INFO",
          "type" => "RunningLog",
          "port" => 52254,
      "@version" => "1",
        "thread" => "http-nio-auto-1-exec-4",
    "createTime" => "2020-09-29 11:47:08",
          "host" => "localhost",
    "@timestamp" => 2020-09-29T03:47:08.861Z,
           "pid" => "42358",
       "message" => "query person zhangsan",
        "logger" => "com.snaildrum.demo.DemoApplication"
}
{
        "httpStatus" => "200",
    "requestMapping" => "/foo/person/{name}",
              "type" => "AccessLog",
       "requestHost" => "localhost:52271",
              "port" => 52267,
        "requestURI" => "/foo/person/zhangsan",
          "@version" => "1",
     "executionTime" => "46",
                "ip" => "127.0.0.1",
        "createTime" => "2020-09-29 11:47:08",
              "host" => "localhost",
        "@timestamp" => 2020-09-29T03:47:08.891Z,
               "pid" => "42358",
        "httpMethod" => "GET"
}


```

## 参考

- [logback-access-spring-boot-starter](https://github.com/akihyro/logback-access-spring-boot-starter)
- [logstash-logback-encoder](https://github.com/logstash/logstash-logback-encoder)
- [logback-access](http://logback.qos.ch/access.html)



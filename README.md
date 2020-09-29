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

###要求
- jdk 11+
- gradle 6+




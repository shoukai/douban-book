# 豆瓣读书同步工具

### 介绍

利用豆瓣OpenAPI获取个人读书列表并转行为markdown格式。示例： [个人阅读列表](http://apframework.com/pages/read)

### 使用

需要maven环境

```
mvn exec:java -Dexec.mainClass="org.apframework.douban.book.Executor" -Dexec.args="个人豆瓣ID"
```

### 参考

* [如何将自己的豆瓣日记、书评、影评全部导出](https://www.zhihu.com/question/25868709)
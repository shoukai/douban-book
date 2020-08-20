# 豆瓣读书同步工具

### 简介

利用豆瓣OpenAPI获取个人读书列表并转行为markdown格式。示例： [个人阅读列表](http://apframework.com/pages/read)

![](http://skblog.duiduiche.com/ecfa7fc1b6fb3524f4ba409a17c555cd.jpg)

### 使用

需要maven环境

```
mvn exec:java -Dexec.mainClass="org.apframework.douban.book.Executor" -Dexec.args="个人豆瓣ID"
```

或者通过Executor主函数直接运行

### 说明

获取某个用户的所有图书收藏信息


```
GET  https://api.douban.com/v2/book/user/:name/collections
```

book-mark.cache 保存的内容为上次同步书籍id，如需要同步全部书籍，手动删除book-mark.cache文件

### 示例结果

得到结果格式如下

```
上次同步的书籍id是：25747852，如果同步全部书籍，请删除 book-mark.cache 文件
| --- | --- |
| 名称：[深入浅出大型网站架构设计](https://book.douban.com/subject/35093372/)<br>时间：2020-07-24<br>出版：2020-5<br>豆瓣：0.0/10<br>个人：3/5<br>Tags： | ![](https://img3.doubanio.com/mpic/s33662223.jpg) |
| 名称：[腾讯产品法](https://book.douban.com/subject/27205096/)<br>时间：2020-07-12<br>出版：2018-1<br>豆瓣：7.4/10<br>个人：3/5<br>Tags：产品经理；产品；互联网 | ![](https://img9.doubanio.com/mpic/s29616635.jpg) |
```

### 参考
* [豆瓣Api V2（测试版）](https://developers.douban.com/wiki/?title=api_v2)
* [如何将自己的豆瓣日记、书评、影评全部导出](https://www.zhihu.com/question/25868709)

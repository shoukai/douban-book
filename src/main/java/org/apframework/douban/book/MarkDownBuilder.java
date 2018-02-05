package org.apframework.douban.book;

import com.google.common.base.Joiner;

import java.util.List;

public class MarkDownBuilder {

    static String TEMPLATE = "| 名称：[%s](%s)<br>阅读：%s<br>出版：%s<br>评价：%s/%s<br>Tags：%s | ![](%s) |";

    public static String build(List<BookSummaryVO> summary) {
        Joiner joiner = Joiner.on("；").skipNulls();
        StringBuilder builder = new StringBuilder();
        builder.append("| 信息 | 封面 |").append("\n");
        builder.append("| --- | --- |").append("\n");
        summary.forEach(p -> {
            builder.append(
                    String.format(
                            TEMPLATE,
                            p.getTitle(),
                            p.getUrl(),
                            p.getUpdated(),
                            p.getPubdate(),
                            p.getAverageRating(),
                            p.getMaxRating(),
                            joiner.join(p.getTags()),
                            p.getImage()))
                    .append("\n");
        });
        return builder.toString();
    }

}

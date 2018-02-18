package org.apframework.douban.book;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import java.text.SimpleDateFormat;
import java.util.List;

public class MarkDownBuilder {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
    static String TEMPLATE = "| 名称：[%s](%s)<br>时间：%s<br>出版：%s<br>豆瓣：%s/%s<br>个人：%s<br>点评：%s<br>Tags：%s | ![](%s) |";

    public static String build(List<BookSummaryVO> summary) {
        Joiner joiner = Joiner.on("；").skipNulls();
        StringBuilder builder = new StringBuilder();
        builder.append("| --- | --- |").append("\n");
        summary.forEach(p -> {
            builder.append(
                    String.format(
                            TEMPLATE,
                            p.getTitle(),
                            p.getUrl(),
                            simpleDateFormat.format(p.getUpdateTime()),
                            p.getPubdate(),
                            p.getAverageRating(),
                            p.getMaxRating(),
                            Strings.isNullOrEmpty(p.getMineRating()) ? "未评分" : p.getMineRating() + "/5",
                            Strings.isNullOrEmpty(p.getComment()) ? "未评价" : p.getComment(),
                            joiner.join(p.getTags()),
                            p.getImage()))
                    .append("\n");
        });
        return builder.toString();
    }

}

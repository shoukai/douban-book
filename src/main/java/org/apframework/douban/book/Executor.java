package org.apframework.douban.book;

import com.google.common.base.Strings;

import java.util.List;
import java.util.stream.Collectors;

public class Executor {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("input user id of douban");
            System.exit(0);
        }
        String userId = args[0];

        BookDataRequest request = new BookDataRequest();
        List<BookSummaryVO> summaryList = request.request(userId);

        if (summaryList == null || summaryList.size() == 0) {
            System.out.println("未获取到书籍列表");
            return;
        }

        String lastId = BookMarkCacheUtil.getLastSyncId();

        BookMarkCacheUtil.saveLastSyncId(summaryList.get(0).getBookId());
        if (Strings.isNullOrEmpty(lastId)) {
            System.out.println(MarkDownBuilder.build(summaryList));
            return;
        }

        System.out.println("上次同步的书籍id是：" + lastId + "，如果同步全部书籍，请删除 " + BookMarkCacheUtil.BOOK_MARK_FILE_NAME + " 文件");
        int index = summaryList.indexOf(new BookSummaryVO(lastId));
        if (index == -1) {
            System.out.println(MarkDownBuilder.build(summaryList));
        } else {
            System.out.println(MarkDownBuilder.build(summaryList.stream().limit(index).collect(Collectors.toList())));
        }

    }

}

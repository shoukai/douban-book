package org.apframework.douban.book;

import java.util.List;

public class Executor {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("input user id of douban");
            System.exit(0);
        }
        String userId = args[0];

        BookDataRequest request = new BookDataRequest();
        List<BookSummaryVO> summaryList = request.request(userId);

        System.out.println(MarkDownBuilder.build(summaryList));
    }
}

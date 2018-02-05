package org.apframework.douban.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSummaryVO {

    private String updated;
    private String averageRating;
    private String maxRating;
    private String pubdate;
    private List<String> tags;
    private String image;
    private String title;
    private String url;

}

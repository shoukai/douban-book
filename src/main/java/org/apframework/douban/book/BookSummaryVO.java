package org.apframework.douban.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSummaryVO {

    public BookSummaryVO(String bookId) {
        this.bookId = bookId;
    }

    private String bookId;
    private Date updateTime;
    private String comment;
    private String mineRating;
    private String averageRating;
    private String maxRating;
    private String pubdate;
    private List<String> tags;
    private String image;
    private String title;
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookSummaryVO that = (BookSummaryVO) o;
        return Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bookId);
    }
}

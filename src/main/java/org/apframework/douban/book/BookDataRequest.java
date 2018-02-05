package org.apframework.douban.book;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.List;

public class BookDataRequest {

    static int BATCH_SIZE = 100;

    static String DOUBAN_REQUEST_URL_TEMPLATE = "https://api.douban.com/v2/book/user/%s/collections?status=read&start=%d&count=%d";

    private OkHttpClient client = new OkHttpClient();

    public List<BookSummaryVO> request(String userId) {
        List<BookSummaryVO> summaryList = Lists.newArrayList();
        try {
            int counter = 0;
            while (true) {
                List<BookSummaryVO> batch = requestItem(
                        String.format(DOUBAN_REQUEST_URL_TEMPLATE, userId, counter * BATCH_SIZE, (counter + 1) * BATCH_SIZE)
                );
                if (batch == null || batch.size() == 0) {
                    break;
                }
                summaryList.addAll(batch);
                if (batch.size() < BATCH_SIZE) {
                    break;
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return summaryList;
    }

    private List<BookSummaryVO> requestItem(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();


        Response response = client.newCall(request).execute();
        ResponseBody body = response.body();

        if (body == null || Strings.isNullOrEmpty(body.string())) {
            return null;
        }

        return decodeData(
                body.string()
        );
    }


    private static List<BookSummaryVO> decodeData(String data) {
        JSONObject object = JSONObject.parseObject(data);
        JSONArray array = object.getJSONArray("collections");
        if (array == null) {
            return null;
        }
        List<BookSummaryVO> summaryList = Lists.newArrayList();
        for (int i = 0; i < array.size(); i++) {
            JSONObject record = array.getJSONObject(i);
            summaryList.add(decodeRecord(record));
        }
        return summaryList;
    }

    public static BookSummaryVO decodeRecord(JSONObject record) {

        JSONObject book = record.getJSONObject("book");

        JSONArray tagArray = book.getJSONArray("tags");
        // 取前三个标签
        List<String> tags = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            if (tagArray.getJSONObject(i) != null) {
                tags.add(tagArray.getJSONObject(i).getString("name"));
            }
        }

        return new BookSummaryVO(
                record.getString("updated"),
                book.getJSONObject("rating").getString("average"),
                book.getJSONObject("rating").getString("max"),
                book.getString("pubdate"),
                tags,
                book.getString("image"),
                book.getString("title"),
                book.getString("url")
        );

    }

}

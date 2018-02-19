package org.apframework.douban.book;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BookMarkCacheUtil {

    static String BOOK_MARK_FILE_NAME = "book-mark.cache";

    public static String getLastSyncId() {
        Path cacheFilePath = getCacheFilePath();
        if (Files.exists(cacheFilePath)) {
            List<String> lines;
            try {
                lines = Files.readAllLines(cacheFilePath, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
            if (lines != null && lines.size() > 0) {
                return lines.get(0);
            }
        }
        return "";
    }

    public static void saveLastSyncId(String lastId) {
        Path cacheFilePath = getCacheFilePath();
        try {
            Files.write(cacheFilePath, lastId.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Path getCacheFilePath() {
        String dir = System.getProperty("user.dir");
        return Paths.get(dir + "/" + BOOK_MARK_FILE_NAME);
    }

}

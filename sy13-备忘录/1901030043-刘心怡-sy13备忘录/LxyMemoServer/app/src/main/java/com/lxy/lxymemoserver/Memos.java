package com.lxy.lxymemoserver;

import android.net.Uri;
import android.provider.BaseColumns;

public class Memos {
    public static final String AUTHORITY= "com.lxy.providers.lxymemoserver";
    public static final class Memo implements BaseColumns {
        public final static String _ID = "_id";
        public final static String TITLE = "title";
        public final static String DATE = "date";
        public final static String CONTEXT = "context";
        // 定义该Content提供服务的两个Uri
        public final static Uri DICT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/memos");
        public final static Uri WORD_CONTENT_URI = Uri.parse("content://"	+ AUTHORITY + "/memos");
    }
}

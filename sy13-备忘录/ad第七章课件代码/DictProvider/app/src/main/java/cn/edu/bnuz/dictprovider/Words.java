package cn.edu.bnuz.dictprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class Words {
    public static final String AUTHORITY= "cn.edu.bnuz.providers.dictprovider";
    //public static final String AUTHORITY= "cn.edu.bnuz.dictprovider";
    public static final class Word implements BaseColumns {
        public final static String _ID = "_id";
        public final static String WORD = "word";
        public final static String DETAIL = "detail";
        // 定义该Content提供服务的两个Uri
        public final static Uri DICT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/words");
        public final static Uri WORD_CONTENT_URI = Uri.parse("content://"	+ AUTHORITY + "/word");
    }
}

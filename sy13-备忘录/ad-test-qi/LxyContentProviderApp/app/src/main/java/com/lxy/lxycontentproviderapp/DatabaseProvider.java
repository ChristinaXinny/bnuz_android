package com.lxy.lxycontentproviderapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.projection.MediaProjection;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseProvider extends ContentProvider {


    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int WORDS = 1;
    private static final int WORD = 2;
    private MyDatabaseHelper dbOpenHelper;

    static {
        // 为UriMatcher注册两个Uri
        matcher.addURI(Words.AUTHORITY, "words", WORDS);
        matcher.addURI(Words.AUTHORITY, "word/#", WORD);
    }



    //
    //public static final int MEMO_DIR=0;
    //public static final int MEMO_ITEM=1;
    //public static final String AUTHORITY="com.lxy.lxycontentproviderapp.provider";
    //private static UriMatcher uriMatch;
    //private MyDatabaseHelper dbHelper;
    //
    //static {
    //    uriMatch=new UriMatcher(UriMatcher.NO_MATCH);
    //    uriMatch.addURI(AUTHORITY,"memo",MEMO_DIR);
    //    uriMatch.addURI(AUTHORITY,"memo/#",MEMO_ITEM);
    //}
    //
    //@Override
    //public boolean onCreate() {
    //    dbHelper = new MyDatabaseHelper(getContext(), "MemoStore.db", null, 1);
    //    return true;
    //}
    //
    //@Nullable
    //@Override
    //public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
    //    SQLiteDatabase db = dbHelper.getReadableDatabase();
    //    Cursor cursor = null;
    //    switch (uriMatch.match(uri)){
    //        case MEMO_DIR:
    //            cursor = db.query("Memo", projection, selection,selectionArgs,null, null, sortOrder);
    //            break;
    //        case MEMO_ITEM:
    //            String memoId=uri.getPathSegments().get(1);
    //            cursor=db.query("Memo",projection,"id=?",new String[]{},null,null,sortOrder);
    //            break;
    //        default:
    //
    //    }
    //    return cursor;
    //}
    //
    //@Nullable
    //@Override
    //public String getType(@NonNull Uri uri) {
    //    switch (uriMatch.match(uri)) {
    //        case MEMO_DIR:
    //            return "vnd.android.cursor.dir/vnd.com.lxy.lxycontentproviderapp.provider.memo";
    //        case MEMO_ITEM:
    //            return "vnd.android.cursor.item/vnd.com.lxy.lxycontentproviderapp.provider.memo";
    //        default:
    //            break;
    //    }
    //    return null;
    //}
    //
    //@Nullable
    //@Override
    //public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
    //    SQLiteDatabase db=dbHelper.getReadableDatabase();
    //    Uri uriReturn=null;
    //    switch (uriMatch.match(uri)){
    //        case MEMO_DIR:
    //
    //        case MEMO_ITEM:
    //            long newMemoId=db.insert("Memo",null,contentValues);
    //            uriReturn=Uri.parse("content://"+AUTHORITY+"/memo/"+newMemoId);
    //            break;
    //        default:
    //            break;
    //    }
    //    return uriReturn;
    //}
    //
    //@Override
    //public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
    //    SQLiteDatabase db=dbHelper.getReadableDatabase();
    //    int deleteRows=0;
    //    switch (uriMatch.match(uri)) {
    //        case MEMO_DIR:
    //            deleteRows = db.delete("Memo", selection, selectionArgs);
    //            break;
    //        case MEMO_ITEM:
    //            String memoId = uri.getPathSegments().get(1);
    //            deleteRows = db.delete("Memo", "id=?", new String[]{memoId});
    //            break;
    //        default:
    //            break;
    //    }
    //    return deleteRows;
    //}
    //
    //@Override
    //public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
    //    SQLiteDatabase db = dbHelper.getReadableDatabase();
    //    int updateRows=0;
    //    switch (uriMatch.match(uri)) {
    //        case MEMO_DIR:
    //            updateRows=db.update("Memo",values,selection,selectionArgs);
    //            break;
    //        case MEMO_ITEM:
    //            String memoId=uri.getPathSegments().get(1);
    //            updateRows=db.update("Memo",values,"id=?",new String[]{memoId});
    //            break;
    //        default:
    //            break;
    //    }
    //    return updateRows;
    //}
}

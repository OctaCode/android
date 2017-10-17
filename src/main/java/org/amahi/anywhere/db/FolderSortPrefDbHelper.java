package org.amahi.anywhere.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Performs CRUD operation on SQLite db provided by {@link FolderSortPrefDb FolderSortPrefDb}.
 */

public class FolderSortPrefDbHelper {

    private FolderSortPrefDb folderSortPrefDb;
    private SQLiteDatabase sqLiteDatabase;
    private static FolderSortPrefDbHelper folderSortPrefDbHelper;

    public static FolderSortPrefDbHelper init(Context context) {
        if (folderSortPrefDbHelper == null) folderSortPrefDbHelper = new FolderSortPrefDbHelper(context);
        return folderSortPrefDbHelper;
    }

    private FolderSortPrefDbHelper(Context context) {
        folderSortPrefDb = new FolderSortPrefDb(context);
        sqLiteDatabase = folderSortPrefDb.getWritableDatabase();
    }

    public boolean addNewSortPref(String filePath, String sortPref) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FolderSortPrefDb.KEY_FOLDER_PATH, filePath);
        contentValues.put(FolderSortPrefDb.KEY_SORT_PREF, sortPref);
        int id = (int) sqLiteDatabase.insert(FolderSortPrefDb.TABLE_NAME, null, contentValues);
        return id != -1;
    }
}

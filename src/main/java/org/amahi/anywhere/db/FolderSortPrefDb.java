package org.amahi.anywhere.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static org.amahi.anywhere.db.DbModel.DATABASE_NAME;
import static org.amahi.anywhere.db.DbModel.DATABASE_VERSION;

/**
 * SQLite db for maintaining sorting preference in a persistent database.
 * Query methods managed by {@link FolderSortPrefDbHelper FolderSortPrefDbHelper}.
 */

public class FolderSortPrefDb extends SQLiteOpenHelper {

    // column names
    static final String KEY_ID = "id";
    static final String KEY_FOLDER_PATH = "file_path";

    // Table name
    static final String TABLE_NAME = "FOLDER_SORT_PREF_TABLE";

    public FolderSortPrefDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_FOLDER_PATH + " VARCHAR(200) NOT NULL)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

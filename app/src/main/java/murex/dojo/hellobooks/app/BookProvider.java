package murex.dojo.hellobooks.app;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class BookProvider extends ContentProvider {
   // fields for my content provider
   static final String PROVIDER_NAME = "murex.dojo.hellobooks.app";
   static final String URL = "content://" + PROVIDER_NAME + "/friends";
   static final Uri CONTENT_URI = Uri.parse(URL);

   // fields for the database
   static final String ID = "id";
   static final String NAME = "name";

   DBHelper dbHelper;

   // database declarations
   private SQLiteDatabase database;
   static final String DATABASE_NAME = "app";
   static final String TABLE_NAME = "friends";
   static final int DATABASE_VERSION = 1;
   static final String CREATE_TABLE =
     " CREATE TABLE " + TABLE_NAME +
       " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
       " name TEXT NOT NULL);";


   // class that creates and manages the provider's database
   private static class DBHelper extends SQLiteOpenHelper {

      public DBHelper(Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
      }

      @Override
      public void onCreate(SQLiteDatabase db) {
         db.execSQL(CREATE_TABLE);
      }

      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         throw new UnsupportedOperationException("onUpgrade not supported");
      }
   }

   @Override
   public boolean onCreate() {
      Context context = getContext();
      dbHelper = new DBHelper(context);
      // permissions to be writable
      database = dbHelper.getWritableDatabase();

      return database != null;
   }

   @Override
   public Cursor query(Uri uri, String[] projection, String selection,
                       String[] selectionArgs, String sortOrder) {
      // TODO Auto-generated method stub
      SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
      // the TABLE_NAME to query on
      queryBuilder.setTables(TABLE_NAME);

      Cursor cursor = queryBuilder.query(database, projection, selection,
        selectionArgs, null, null, sortOrder);
      /**
       * register to watch a content URI for changes
       */
      cursor.setNotificationUri(getContext().getContentResolver(), uri);

      return cursor;
   }

   @Override
   public Uri insert(Uri uri, ContentValues values) {
      long row = database.insert(TABLE_NAME, "", values);

      // If record is added successfully
      if (row > 0) {
         Uri newUri = ContentUris.withAppendedId(CONTENT_URI, row);
         getContext().getContentResolver().notifyChange(newUri, null);
         return newUri;
      }
      throw new SQLException("Fail to add a new record into " + uri);
   }

   @Override
   public int update(Uri uri, ContentValues values, String selection,
                     String[] selectionArgs) {
      throw new UnsupportedOperationException("Update not supported");
   }

   @Override
   public int delete(Uri uri, String selection, String[] selectionArgs) {
      throw new UnsupportedOperationException("Delete not supported");

   }

   @Override
   public String getType(Uri uri) {
      throw new UnsupportedOperationException("GetType not supported");
   }


}
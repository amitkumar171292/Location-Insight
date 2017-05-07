package humpty.dumpty.MobileTracker.publish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CDatab extends SQLiteOpenHelper{
	public static final String DATABASE_CREATE = "CREATE TABLE " + CSchema.DATABASE_TABLE + "("
			+ CSchema.KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ CSchema.KEY_NUMBER + " NOT NULL UNIQUE, " 
			+ CSchema.KEY_NAME + ");";
			public CDatab(Context context) {
				super(context, CSchema.DATABASE_NAME,null,CSchema.DATABASE_VERSION);
				// TODO Auto-generated constructor stub
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				// TODO Auto-generated method stub
				db.execSQL(DATABASE_CREATE);
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVesion, int newVersion) {
				// TODO Auto-generated method stub
				db.execSQL("DROP TABLE IF EXISTS" + CSchema.DATABASE_TABLE);
				onCreate(db);
			}
			
			
			public void insertNum(String name,String num)
			{
				SQLiteDatabase db=this.getWritableDatabase();
				ContentValues cv=new ContentValues();
				cv.put(CSchema.KEY_NAME,name);
				cv.put(CSchema.KEY_NUMBER,num);
				db.insert(CSchema.DATABASE_TABLE,null,cv);
				db.close();
			}
			public Cursor readData() {
				SQLiteDatabase db=this.getReadableDatabase();
		        String[] allColumns = new String[] { CSchema.KEY_ROWID,CSchema.KEY_NAME,CSchema.KEY_NUMBER };
		        Cursor c = db.query(CSchema.DATABASE_TABLE, allColumns, null,
		                null, null, null, null);
		        if (c != null) {
		            c.moveToFirst();
		        }
		        return c;
		    }
			
			public boolean deleteRow(long id) 
		      {
				SQLiteDatabase db=this.getWritableDatabase();
		         return db.delete(CSchema.DATABASE_TABLE, CSchema.KEY_ROWID + "=" + id, null) > 0;
		       }

}
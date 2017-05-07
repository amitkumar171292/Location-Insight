package humpty.dumpty.MobileTracker.publish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CDatac extends SQLiteOpenHelper{
	public static final String DATABASE_CREATE = "CREATE TABLE " + CSchema1.DATABASE_TABLE + "("
			+ CSchema1.KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ CSchema1.KEY_NUMBER + " NOT NULL UNIQUE, " 
			+ CSchema1.KEY_NAME + ","
			+ CSchema1.KEY_TYPE + ");";
			public CDatac(Context context) {
				super(context, CSchema1.DATABASE_NAME,null,CSchema1.DATABASE_VERSION);
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
				db.execSQL("DROP TABLE IF EXISTS" + CSchema1.DATABASE_TABLE);
				onCreate(db);
			}
			
			public void insertNum(String name,String num,String type)
			{
				SQLiteDatabase db=this.getWritableDatabase();
				ContentValues cv=new ContentValues();
				cv.put(CSchema1.KEY_NAME,name);
				cv.put(CSchema1.KEY_NUMBER,num);
				cv.put(CSchema1.KEY_TYPE,type);
				db.insert(CSchema1.DATABASE_TABLE,null,cv);
				db.close();
			}
			public Cursor readData() {
				SQLiteDatabase db=this.getReadableDatabase();
		        String[] allColumns = new String[] { CSchema1.KEY_ROWID,CSchema1.KEY_TYPE,CSchema1.KEY_NAME,CSchema1.KEY_NUMBER };
		        Cursor c = db.query(CSchema1.DATABASE_TABLE, allColumns, null,
		                null, null, null, null);
		        if (c != null) {
		            c.moveToFirst();
		        }
		        return c;
		    }
			public Cursor readData(long id) {
				SQLiteDatabase db=this.getReadableDatabase();
		        String[] allColumns = new String[] { CSchema1.KEY_ROWID,CSchema1.KEY_NAME,CSchema1.KEY_NUMBER,CSchema1.KEY_TYPE };
		        Cursor c = db.query(CSchema1.DATABASE_TABLE, allColumns, CSchema1.KEY_ROWID+" = "+id,
		                null, null, null, null);
		        if (c != null) {
		            c.moveToFirst();
		        }
		        return c;
		    }
			public void del()
			{
				SQLiteDatabase db=this.getWritableDatabase();
				db.execSQL("DROP TABLE IF EXISTS " + CSchema1.DATABASE_TABLE);
				onCreate(db);
			}
			
			public int isDataAvailable()
		    {
				SQLiteDatabase db=this.getReadableDatabase();
		        int total = 0;
		        try
		        {
		            Cursor c = null;
		            c = db.rawQuery("select id from "+CSchema1.DATABASE_TABLE, null);

		            if(c.getCount() != 0)
		                total = c.getCount();

		            c.close();
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
		        }
		        return total; 
		    }
}

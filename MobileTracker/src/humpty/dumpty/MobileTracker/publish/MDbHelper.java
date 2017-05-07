package humpty.dumpty.MobileTracker.publish;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MDbHelper extends SQLiteOpenHelper {
	static String DATABASE_NAME="userdata";
	public static  String TABLE_NAME="user";
	public static  String KEY_FNAME="fname";
	public static  String KEY_QUE="que";
	public static  String KEY_ANS="ans";
	public static  String KEY_ID="id";
	public MDbHelper(Context context) 
	{
	super(context, DATABASE_NAME, null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
	String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_FNAME+" VARCHAR,"+KEY_QUE+" VARCHAR, "+KEY_ANS+" VARCHAR)";
	db.execSQL(CREATE_TABLE);
    }
    @Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
	onCreate(db);
    }
    }

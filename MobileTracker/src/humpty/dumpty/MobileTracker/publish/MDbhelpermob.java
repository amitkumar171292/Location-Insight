package humpty.dumpty.MobileTracker.publish;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MDbhelpermob extends SQLiteOpenHelper {
	static String DATABASE_NAME="userdata2";
	public static  String TABLE_NAME="user2";
	public static  String KEY_ID="id";
	public static  String KEY_NO1="mobno";
	public static  String KEY_NO2="sms";
    public static  String KEY_SIMNO="simno";
	public static  String KEY_STATUS="status";
	public MDbhelpermob(Context context) 
	{
	super(context, DATABASE_NAME, null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
	String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_NO1+" VARCHAR, "+KEY_NO2+" VARCHAR, "+KEY_SIMNO+" VARCHAR, "+KEY_STATUS+" VARCHAR)";
	db.execSQL(CREATE_TABLE);
    }
    @Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
	onCreate(db);
    }
    }

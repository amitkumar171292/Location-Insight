package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.ListView;

public class CDispCallL extends Activity{
	ListView lv;
	CDatac db;
	String name;
    String number;
    String typee,type;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cviewblack);
		Intent i=new Intent(this,CLoader.class);
        startActivity(i);
		String[] projection = new String[] {CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME,CallLog.Calls.TYPE};
        Uri contacts =  CallLog.Calls.CONTENT_URI;
        @SuppressWarnings("deprecation")
		Cursor managedCursor = managedQuery(contacts, projection, null, null, CallLog.Calls.DATE + " DESC");
        getColumnData(managedCursor);
        
	}
	
	
	 private void getColumnData(Cursor cur){
	      try{ 
	    	  
	         if (cur.moveToFirst()) {
	           
	           int nameColumn = cur.getColumnIndex(CallLog.Calls.CACHED_NAME);
	           int numberColumn = cur.getColumnIndex(CallLog.Calls.NUMBER);
	           int typ = cur.getColumnIndex(CallLog.Calls.TYPE);
	           do {
	               name = cur.getString(nameColumn);
	              if(name==null)
	            	   name="Unknown";
	               number = cur.getString(numberColumn);
	               type = cur.getString(typ);
	                if(type.equals("1"))
	                typee="Incoming Call";
	                else if(type.equals("2"))
	                typee="Outgoing Call";
	                else
	                	typee="Missed Call";
	               db=new CDatac(this);
	       		db.insertNum(name, number,typee);
	          } while (cur.moveToNext());
	        }
	        
	    }
	    finally{
	      cur.close();
	    }
	  }
	 
	 
	 
}

package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CDisplayLogs extends Activity implements OnItemClickListener {
	Button addmem_bt;
    ListView lv;
    CDatac db;
    TextView memID_tv, memName_tv,mem_num;
    String selectedFromList;String name;
    String number;
    int p;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.cviewblack);
        db = new CDatac(this);
        
        lv = (ListView) findViewById(R.id.l1);
        Cursor cursor = db.readData();
        if((cursor.getCount())==0)
            {
            	Toast.makeText(this,"No Call Logs...",Toast.LENGTH_LONG).show();
     	 		  Intent intent = new Intent(this,CMainS.class);
     	 	     	startActivity(intent);	
            }
        String[] from = new String[] { CSchema1.KEY_ROWID, CSchema1.KEY_NAME,CSchema1.KEY_NUMBER,CSchema1.KEY_TYPE};
        int[] to = new int[] { R.id.t1, R.id.t5,R.id.t6,R.id.t7};
        @SuppressWarnings("deprecation")
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.caddcalllog, cursor, from, to);

        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() 
        {
            @SuppressWarnings("unused")
			public void onNothingSelected(AdapterView<?> parentView) 
            {
            }
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				// TODO Auto-generated method stub
				Cursor c=db.readData(id);
		        getColumnDat(c);
		    }
			
        });   
    } 
    
    
    public void getColumnDat(Cursor cur){
	      try{
	         if (cur.moveToFirst()) 
	             {
	           
	                 int nameColumn = cur.getColumnIndexOrThrow(CSchema.KEY_NAME);
	                 int numberColumn = cur.getColumnIndex(CSchema.KEY_NUMBER);
	                 do {
	                      name = cur.getString(nameColumn);
	                      number = cur.getString(numberColumn);
	                    } while (cur.moveToNext());
	              }
	         
	          Intent i=new Intent(CDisplayLogs.this,CFirst.class);
	          i.putExtra("key1", name);
	          i.putExtra("key2", number);
	          startActivity(i);
	         }
	 
	     finally
	     { 
	      cur.close();
	      db.del();
	     }
	  }
    
    
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) 
	{
		 Cursor c=db.readData(p);
	        getColumnDat(c);
	}
	public void onBackPressed()
	{
	    super.onBackPressed();
		Intent intent = new Intent(this,CMainS.class);
		startActivity(intent);
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_HOME)) {
	        //do your stuff
	    	int pid = android.os.Process.myPid();
		    android.os.Process.killProcess(pid);
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}

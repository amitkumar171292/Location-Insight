package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class CDelRecord extends Activity{
	ListView lv;
    CDatab db;
    int p;
    public static long i;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.cviewblack);
	        db = new CDatab(this);
	        lv = (ListView) findViewById(R.id.l1);
	        Cursor cursor = db.readData();
	        if((cursor.getCount())==0)
	        {
	        	Toast.makeText(this,"BLACKlist Empty...",Toast.LENGTH_LONG).show();
	 	 		  Intent intent = new Intent(this,CMainS.class);
	 	 	     	startActivity(intent);	
	        }
	        String[] from = new String[] {CSchema.KEY_NAME,CSchema.KEY_NUMBER };
	        int[] to = new int[] {R.id.t5,R.id.t6 };

	        @SuppressWarnings("deprecation")
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.cremovecon, cursor, from, to);

	        adapter.notifyDataSetChanged();
	        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() 
        {
            
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
				i=id;
				// TODO Auto-generated method stub
				AlertDialog.Builder builder=new AlertDialog.Builder(CDelRecord.this);
				builder.setMessage("Are you sure you want to delete the contact from BLACKList")
				.setCancelable(false)
				.setPositiveButton("YES",new DialogInterface.OnClickListener() 
				{
					
					public void onClick(DialogInterface dialog, int which) 
					{
						Boolean c=db.deleteRow(CDelRecord.i);
						if(c)
						{
							Toast.makeText(CDelRecord.this,"Contact Removed from BLACKList", Toast.LENGTH_LONG).show();
							Intent i=getIntent();
							startActivity(i);
						}	
					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				}).show();
				
			}
        }); 
        
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

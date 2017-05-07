package humpty.dumpty.MobileTracker.publish;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class CDisplay extends Activity{
    ListView lv;
    CDatab db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    } // create method end
}

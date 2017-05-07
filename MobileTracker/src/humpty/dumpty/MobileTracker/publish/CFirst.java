package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CFirst extends Activity{
	TextView t,t1;
	CDatab db;
	CDatac dc;
	String name,num;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.caddman1);
		Intent i=getIntent();
		name=i.getStringExtra("key1");
		num=i.getStringExtra("key2");
		t=(TextView)findViewById(R.id.textView1);
		t1=(TextView)findViewById(R.id.textView2);
		t.setText(name);
		t1.setText(num);
	}
	
	public void onc(View v)
	{
		ImageView b=(ImageView)findViewById(R.id.b10);
		//b.setBackgroundColor(880);
		ins();	
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
	
	public void ins()
	{
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to add the contact in BLACKList")
		.setCancelable(false)
		.setPositiveButton("YES",new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int id) {
				db=new CDatab(CFirst.this);
				db.insertNum(name, num);
				Toast.makeText(CFirst.this,"Contact successfully added to BlackList...", Toast.LENGTH_LONG).show();
				onBackPressed();
			}
		})
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int id) {
				ImageView b=(ImageView)findViewById(R.id.b10);
				b.setBackgroundColor(000);
				dialog.cancel();
			}
		}).show();
	
	}
}

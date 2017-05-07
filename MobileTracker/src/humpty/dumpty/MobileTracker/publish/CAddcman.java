package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CAddcman extends Activity{
	EditText e1,e2;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.caddman);
	}
	public void onc(View v)
	{
		ImageView b=(ImageView)findViewById(R.id.b9);
		//b.setBackgroundColor(880);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		String name=e1.getText().toString();
		String num=e2.getText().toString();
		if(name.length()>=5 && num.length()==10)
		{
			Intent i = new Intent(CAddcman.this,CFirst.class);
			i.putExtra("key1",name);
			i.putExtra("key2",num);
			startActivity(i);
		}
		else
		{
			Toast.makeText(getApplicationContext(), "ENTER THE NUMBER OF 10 DIGITS AND NAME OF ATLEAST 5 CHARACTER", Toast.LENGTH_LONG).show();
		}
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

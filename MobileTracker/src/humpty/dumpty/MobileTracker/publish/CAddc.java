package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CAddc extends Activity{
	private final int PICK = 1;
	String name,cNumber;
	EditText t;
protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.caddnumber);
}

public void onc(View v)
{
	switch(v.getId())
	{
	case R.id.b6:
	{
		ImageView b=(ImageView)findViewById(R.id.b6);
		//b.setBackgroundColor(880);
		Intent intent = new Intent(CAddc.this,CAddcman.class);
		startActivity(intent);
	}
		break;
	case R.id.b7:
	{
		ImageView b=(ImageView)findViewById(R.id.b7);
		//b.setBackgroundColor(880);
		Intent intent = new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(intent, PICK);
	}
		break;
	case R.id.b8:
	{
		ImageView b=(ImageView)findViewById(R.id.b8);
		//b.setBackgroundColor(880);
		Intent intent = new Intent(this,CDispCallL.class);
		startActivity(intent);	
	}
		break;
}
}
@SuppressWarnings("deprecation")
public void onActivityResult(int reqCode, int resultCode, Intent data) {
	super.onActivityResult(reqCode, resultCode, data);
	switch (reqCode) {
	case (PICK):
		if (resultCode == Activity.RESULT_OK) {
			Uri contactData = data.getData();
			Cursor c = managedQuery(contactData, null, null, null, null);
			if (c.moveToFirst()) {
				String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

		         String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

		           if (hasPhone.equalsIgnoreCase("1")) {
		          Cursor phones = getContentResolver().query( 
		                       ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
		                       ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, 
		                       null, null);
		             phones.moveToFirst();
		             cNumber = phones.getString(phones.getColumnIndex("data1"));
		             //System.out.println("number is:"+cNumber);
		           }
		         name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
		         if(name.equalsIgnoreCase(cNumber))
	            	   name="Unknown";
			}
			Intent i=new Intent(this,CFirst.class);
			i.putExtra("key1", name);
			i.putExtra("key2", cNumber);
			startActivity(i);
		}
		break;
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
    	int pid = android.os.Process.myPid();
	    android.os.Process.killProcess(pid);
        return true;
    }
    return super.onKeyDown(keyCode, event);
}
}


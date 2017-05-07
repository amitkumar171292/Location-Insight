package humpty.dumpty.MobileTracker.publish;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class BScreenOnOff extends Activity {
EditText number,message;
ListView list;
ArrayList<String> numberlist=new ArrayList<String>();
ArrayAdapter<String> adapter;
SharedPreferences pref;
Editor edit;
static int count=0; 
static int start=0;
static int stop=0,save=0;
Intent i0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bbuttonmainscreen);
		pref=getSharedPreferences("numbers",Context.MODE_PRIVATE);
	 
		number=(EditText) findViewById(R.id.editText1);
		message=(EditText) findViewById(R.id.editText2);
		list=(ListView) findViewById(R.id.listView1);
		// Start AEScreenOnOffService Service
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,numberlist);
		list.setAdapter(adapter);
	    i0 = new Intent(); 
		i0.setAction("BAEScreenOnOffService");
		
		getData();
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
			try
			{
				edit=pref.edit();
				   
			numberlist.remove(pos);	
			edit.remove("number"+pos);
			edit.putInt("size",pref.getInt("size",0)-1);
			edit.commit();
			adapter.notifyDataSetChanged();
			
			}
			catch(Exception e)
			{
				
			}
			}
		});
	}	
	public void add(View v)
	{
		save=0;
		if(number.length()==0)
		{
			Toast.makeText(getApplicationContext(), "Enter phone number",Toast.LENGTH_LONG).show();
			return ;
		}
		else if(number.length()!=10)
		{
			Toast.makeText(getApplicationContext(), "Number must be 10 digits",Toast.LENGTH_LONG).show();
			return ;
			
		}
		numberlist.add(number.getText().toString());
		adapter.notifyDataSetChanged();
		
		
	}
	public void start(View v)
	{
		if(numberlist.size()==0)
		{
			Toast.makeText(getApplicationContext(), "No contact saved yet",Toast.LENGTH_LONG).show();
			return;
		}
		
		
		start++;
		if(start>1)
		{
			Toast.makeText(getApplicationContext(), "service already started press stop to stop the service",Toast.LENGTH_SHORT).show();
			
		}
		startService(i0);
		
		stop=0;
		
		if (start==1)
		{
		
		Toast.makeText(getApplicationContext(), "Event Started", Toast.LENGTH_LONG).show();
		
		}
	}
	public void saveall(View v)
	{
		if(message.getText().length()<5)
		{
			Toast.makeText(getApplicationContext(), "Message is to short",Toast.LENGTH_LONG).show();
			return;
		}
		save++;
		if(save>1)
		{
			Toast.makeText(getApplicationContext(), "settings already saved add another contact",Toast.LENGTH_SHORT).show();
			
		}
		edit=pref.edit();
		edit.putString("msg",message.getText().toString());
	    edit.putInt("size",numberlist.size());
	    
		for(int i=0;i<numberlist.size();i++)
		{
		edit.putString("number"+i,numberlist.get(i));
		
		}
		edit.commit();
		
		if(save==1)
		{
		Toast.makeText(getApplicationContext(), "your setting are saved",Toast.LENGTH_SHORT).show();
		}

	}

private void getData()
{
	int size=pref.getInt("size",0);
	if(size>0)
	{
		for(int i=0;i<size;i++)
		{
			numberlist.add(pref.getString("number"+i,"None"));
		}
		
		adapter.notifyDataSetChanged();
	}
}
public void stop(View v)
{
	
	stop++;
	if(stop>1)
	{
		Toast.makeText(getApplicationContext(), "service is stopped press start to startover",Toast.LENGTH_SHORT).show();
		
	}
	stopService(i0);
	start=0;
	if (stop==1)
	{
	Toast.makeText(getApplicationContext(), "Event stoped", Toast.LENGTH_LONG).show();
	
}
}
}
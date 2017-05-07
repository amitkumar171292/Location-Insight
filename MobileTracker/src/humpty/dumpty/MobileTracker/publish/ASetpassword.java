package humpty.dumpty.MobileTracker.publish;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ASetpassword extends Activity {
	EditText tv;
	//Button b;
	String count,answer;
	EditText pass,cpass;
	TextView t1,t2,t3;
	ImageView enter;
	String spassword,scpassword;
	//Button submit;
	SQLiteDatabase db; 
	Animation animTranslate;
	Animation animFadein;
	Typeface tf;
	private MDbHelper mHelper;
	private SQLiteDatabase dataBase;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.asetpassword);
	        pass=(EditText)findViewById(R.id.pass);
	        cpass=(EditText)findViewById(R.id.cpass);
	        //t1=(TextView)findViewById(R.id.textView051);
	        //t2=(TextView)findViewById(R.id.textView052);
	        //t3=(TextView)findViewById(R.id.textView053);
	        enter=(ImageView)findViewById(R.id.enter);
	        //set edit text view animation
	        animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
	        //set font 
	        tf = Typeface.createFromAsset(getAssets(),"fonts/ALGER.TTF");
	        tv=(EditText)findViewById(R.id.editText1);
			//b=(Button)findViewById(R.id.button1);
	        final Spinner sp=(Spinner)findViewById(R.id.spinner1);
			String[] Country={"What is your favorite teacher's name?","What is your school name?","What is your pet name?",
					"What is your girlfriend name?","Where is your hometown?"};
					sp.setPrompt("Security Question");
					ArrayAdapter<String>adapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,Country);
			sp.setAdapter(adapter);
			sp.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
					// TODO Auto-generated method stub
					count=sp.getItemAtPosition(arg2).toString();
					//Toast.makeText(getApplicationContext(), "YOU HAVE SELECTED: " +count, Toast.LENGTH_LONG).show();
				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
			
	        //animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
			}
     
	 public  void  enter(View arg0)
	 {
		
		 answer=tv.getText().toString();	
		 spassword=pass.getText().toString();
			scpassword=cpass.getText().toString();
			if(spassword.length()>0||scpassword.length()>0)
			{
				if(spassword.length()<=10&&scpassword.length()<=10)
				{
					if(spassword.equals(scpassword)&&answer.length()>0)
					{
						mHelper=new MDbHelper(this);
						saveData();
						//arg0.startAnimation(animTranslate);
						Intent inserts=new Intent(getApplicationContext(),AUsermainscreen.class);
						startActivity(inserts);
						finish();
					}
					else 
					{
						Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
					     findViewById(R.id.pass).startAnimation(shake);
					     Animation shake1 = AnimationUtils.loadAnimation(this, R.anim.shake1);
					     findViewById(R.id.cpass).startAnimation(shake1);
					     Toast.makeText(this, "Both Password Doesn't Match Or Security Question Doesn,t match", Toast.LENGTH_LONG).show();
					     pass.setText("");
					     cpass.setText("");
					     //t3.setText("Both Password Doesn't Match");
					     //t3.setVisibility(View.VISIBLE);
				   	      // start the animation
				   	     //t3.startAnimation(animFadein);
					}
				}
				else
				{
					Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
				     findViewById(R.id.pass).startAnimation(shake);
				     Animation shake1 = AnimationUtils.loadAnimation(this, R.anim.shake1);
				     findViewById(R.id.cpass).startAnimation(shake1);
				     Toast.makeText(this, "Maximum Length Should Be 10", Toast.LENGTH_LONG).show();
				     pass.setText("");
				     cpass.setText("");
				     //t3.setText("Maximum Length Should Be 10");
				     //t3.setVisibility(View.VISIBLE);
			   	      // start the animation
			   	     //t3.startAnimation(animFadein);	
				}
			}
			else
			{
				
				
		   	  AlertDialog.Builder alertBuilder=new AlertDialog.Builder(ASetpassword.this);
				alertBuilder.setTitle("Invalid Data");
				alertBuilder.setMessage("Please, Enter valid data");
				alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which)
				{
					dialog.cancel();
				}
				});
				alertBuilder.create().show();	
			}
			
	 }

	     /**
		 * save data into SQLite
		 */
		public void saveData()
		{
			
			mHelper = new MDbHelper(this);
			dataBase=mHelper.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put(MDbHelper.KEY_FNAME,scpassword);
			values.put(MDbHelper.KEY_QUE,count);
			values.put(MDbHelper.KEY_ANS,answer);
			dataBase.insert(MDbHelper.TABLE_NAME, null, values);
		    Toast.makeText(this, "Your Password Set Sucessfully", Toast.LENGTH_LONG).show();
		    Toast.makeText(this, "Your Security Password Set Sucessfully="+count+" "+answer, Toast.LENGTH_LONG).show();
			dataBase.close();
              }
		}
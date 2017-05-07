package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AUpdatepassword extends Activity{
	EditText pass,cpass;
	String spassword,scpassword;
	ImageView submit;
	public MDbHelper mHelper;
	public SQLiteDatabase dataBase;
	String id="1";
    public void onBackPressed() 
	{
		finish();
		//overridePendingTransition(R.anim.translation_right_to_left, 0);
		super.onBackPressed();
	}
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.aupdate);
	        pass=(EditText)findViewById(R.id.pass);
	        cpass=(EditText)findViewById(R.id.cpass);
	 }
	 public  void  enter( View v) 
	 {
			
		 spassword=pass.getText().toString();
			scpassword=cpass.getText().toString();
			if(spassword.length()>0||scpassword.length()>0 )
			{
				if(spassword.length()<=10&&scpassword.length()<=10)
				{	
					if(spassword.equals(scpassword))
						{
							mHelper=new MDbHelper(this);
							//saveData();
							dataBase=mHelper.getWritableDatabase();
							ContentValues values=new ContentValues();
							values.put(MDbHelper.KEY_FNAME,scpassword);	
								//update data into database
								dataBase.update(MDbHelper.TABLE_NAME, values,MDbHelper.KEY_ID +"="+id, null);
								Toast.makeText(this,"Password Updated Succesfully", Toast.LENGTH_LONG).show();
								dataBase.close();
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
					    
					Toast.makeText(this, "Both Password Doesn't match", Toast.LENGTH_LONG).show();
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
					AlertDialog.Builder alertBuilder=new AlertDialog.Builder(AUpdatepassword.this);
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
	 }


package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AForgetpass extends Activity {
	EditText a;
	TextView q;
    ImageView b;
    MDbHelper mHelper;
    SQLiteDatabase dataBase;
    String ans,answer,question,userId;
    //Main Method
    public void onBackPressed() 
	{
		finish();
		//overridePendingTransition(R.anim.translation_right_to_left, 0);
		super.onBackPressed();
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {	    
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.aforgetpass);
        q=(TextView)findViewById(R.id.textView2);
        a=(EditText)findViewById(R.id.editText2);
        b=(ImageView)findViewById(R.id.button1);
	    mHelper = new MDbHelper(this);
	    //show record 
		dataBase = mHelper.getWritableDatabase();
		Cursor mCursor = dataBase.rawQuery("SELECT * FROM "+ MDbHelper.TABLE_NAME, null);
        if (mCursor.moveToFirst())
        {
				do
				{
					userId=mCursor.getString(mCursor.getColumnIndex(MDbHelper.KEY_ID));
					question=mCursor.getString(mCursor.getColumnIndex(MDbHelper.KEY_QUE));
					answer=mCursor.getString(mCursor.getColumnIndex(MDbHelper.KEY_ANS));	              
				}
				while (mCursor.moveToNext());
		}
		mCursor.close();
		dataBase.close();
		 q.setText(question);
		 //Toast.makeText(this, "Your Security Password Set Sucessfully"+question+""+answer, Toast.LENGTH_LONG).show();
        }        
         
        //match password on click method
	     public void  checkpassword( View arg0) 
	     {
	    	 ans=a.getText().toString().trim();
	    	 if(answer.equals(ans))
	    	 {
	    		Toast.makeText(getApplicationContext(), "Answer Matches", Toast.LENGTH_LONG).show();
	    		Intent inserts=new Intent(getApplicationContext(),AUpdatepassword.class);
				startActivity(inserts);
			 }
		 
		 else
		 {
			 Toast.makeText(getApplicationContext(), "Answer Not Matches??? Please enter the correct ans", Toast.LENGTH_LONG).show();
		 }
	}
}

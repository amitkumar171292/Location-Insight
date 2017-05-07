package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

	public class SManageContacts extends Activity {

		SharedPreferences sharedpreferences;

		SmsManager smsManager;
		Editor se;
		EditText con1, con2, con3,con4;
		String defaultCon1, defaultCon2, defaultCon3,defaultCon4;
		String p,p1,p2,p3;
		String edCon1, edCon2, edCon3, edcon4;
		String Mes;
		ImageView saveContacts;

		int i = 0, j = 0, k = 0,l=0;

		Typeface tf, tfButton;

		@Override
		public void onBackPressed() {

			finish();
		//	overridePendingTransition(R.anim.translation_right_to_left, 0);

			super.onBackPressed();
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.saddcontact);
			 sharedpreferences = getSharedPreferences("CONTACTS", Context.MODE_APPEND);
		       se = sharedpreferences.edit();
			con1 = (EditText) findViewById(R.id.editText1);
			con2 = (EditText) findViewById(R.id.editText2);
			con3 = (EditText) findViewById(R.id.editText3);
			con4 = (EditText) findViewById(R.id.Message);


			defaultCon1 = sharedpreferences.getString("CONTACT1", "");
			defaultCon2 = sharedpreferences.getString("CONTACT2", "");
			defaultCon3 = sharedpreferences.getString("CONTACT3", "");
			defaultCon4 = sharedpreferences.getString("MESSAGE", "");
			con1.setText(defaultCon1.toString());
			con2.setText(defaultCon2.toString());
			con3.setText(defaultCon3.toString());
			con4.setText(defaultCon4.toString());
			

			saveContacts = (ImageView) findViewById(R.id.button1);
			
			//saveContacts.setTypeface(tfButton);
			

			con1.setOnFocusChangeListener(new OnFocusChangeListener() {

				public void onFocusChange(View v, boolean hasFocus) {

					if (hasFocus == true) {
						if (con1.getText().toString()
								.equals("Enter a valid number")) {
							con1.setTextColor(Color.BLACK);
							con1.setText("");
						}
					}

				}
			});
			con2.setOnFocusChangeListener(new OnFocusChangeListener() {

				public void onFocusChange(View v, boolean hasFocus) {

					if (hasFocus == true) {
						if (con2.getText().toString()
								.equals("Enter a valid number")) {
							con2.setTextColor(Color.BLACK);
							con2.setText("");
						}
					}

				}
			});
			con3.setOnFocusChangeListener(new OnFocusChangeListener() {

				public void onFocusChange(View v, boolean hasFocus) {

					if (hasFocus == true) {
						if (con3.getText().toString()
								.equals("Enter a valid number")) {
							con3.setTextColor(Color.BLACK);
							con3.setText("");
						}
					}

				}
			});
			con4.setOnFocusChangeListener(new OnFocusChangeListener() {

				public void onFocusChange(View v, boolean hasFocus) {

					if (hasFocus == true) {
						if (con4.getText().toString()
								.equals("Enter message")) {
							con4.setTextColor(Color.BLACK);
							con4.setText("");
						}
					}

				}
			});
		}

					

		public void Save(View v)
		{

			edCon1 = con1.getText().toString();
			edCon2 = con2.getText().toString();
			edCon3 = con3.getText().toString();
			Mes = con4.getText().toString();

			if (edCon1.length() != 10) {
				con1.setTextColor(Color.RED);
				con1.setText("Enter a valid number");
			} else {
				se.putString("CONTACT1", edCon1);
				i = 1;
			}
			if (edCon2.length() != 10) {
				con2.setTextColor(Color.RED);
				con2.setText("Enter a valid number");
			} else {
				se.putString("CONTACT2", edCon2);
				j = 1;
			}
			if (edCon3.length() != 10) {
				con3.setTextColor(Color.RED);
				con3.setText("Enter a valid number");
			} else {
				se.putString("CONTACT3", edCon3);
				k = 1;
			}
			
			if (Mes.length() == 0) {
				con4.setTextColor(Color.RED);
				con4.setText("Enter message");
			} else {
				se.putString("MESSAGE", Mes);
				l = 1;
			}
			
			if (i == 1 && j == 1 && k == 1 && l==1) {
				se.commit();
				Toast.makeText(getBaseContext(), " Saved",Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getBaseContext(),SShakemainscreen.class);
			}

			else if(l==0){
				Toast.makeText(getBaseContext(), "Enter Message",Toast.LENGTH_SHORT).show();
			}
			
			else {
				Toast.makeText(getBaseContext(), "Enter Valid Contact",Toast.LENGTH_SHORT).show();
			}

		
	}

	}


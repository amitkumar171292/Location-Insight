package humpty.dumpty.MobileTracker.publish;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MShow_user_mobile_details extends Activity {
	TextView s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14;
	String d="";
	int i;
	String ptype;
	Intent intent;
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mshow_user_mobile_details);
	
		TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		
		s2 = (TextView)findViewById(R.id.serial);
		s3 = (TextView)findViewById(R.id.oper);
		s4 = (TextView)findViewById(R.id.imei);
		s5 = (TextView)findViewById(R.id.softver);
		s6 = (TextView)findViewById(R.id.iso);
		s7 = (TextView)findViewById(R.id.netoperator);
		s8 = (TextView)findViewById(R.id.subid);
		s9 = (TextView)findViewById(R.id.voicenum);
		s10 = (TextView)findViewById(R.id.simiso);
		s11 = (TextView)findViewById(R.id.opeid);
		s12 = (TextView)findViewById(R.id.roaming);
		s13 = (TextView)findViewById(R.id.phtype);
		s14 = (TextView)findViewById(R.id.dstate);
		//----------------------------------------------------		
	    d=tm.getLine1Number();
		s2.setText(tm.getSimSerialNumber());
		s3.setText(tm.getSimOperatorName());
		s4.setText(tm.getDeviceId());
		s5.setText(tm.getDeviceSoftwareVersion());
		s6.setText(tm.getNetworkCountryIso());
		s7.setText(tm.getNetworkOperator());
		s8.setText(tm.getSubscriberId());
		s9.setText(tm.getVoiceMailNumber());
		s10.setText(tm.getSimCountryIso());
		s11.setText(tm.getSimOperator());
		s12.setText(""+tm.isNetworkRoaming());
		//-----------------------------------------------------
		 i = tm.getPhoneType();
		
		switch(i+1){
		case 1 :
			ptype= "NONE";
			break;
		case 2 :
			ptype = "GSM";
			break;
		case 3 :
			ptype = "CDMA";
			break;
		case 4 : 
			ptype = "SIP";
			break;
		default:
			ptype = "Unknown";
			break;
		}
		s13.setText(ptype);
		//-------------------------------------------------------
		 i = tm.getDataState();
	
		switch(i+1){
		case 1 :
			ptype= "Disconnected";
			break;
		case 2 :
			ptype = "Connecting";
			break;
		case 3 :
			ptype = "Connected";
			break;
		case 4 : 
			ptype = "Suspended";
			break;
		default:
			ptype = "Unknown";
			break;
		}
		s14.setText(ptype);
		//--------------------------------------------------------
		
	}
	

	
		
	}




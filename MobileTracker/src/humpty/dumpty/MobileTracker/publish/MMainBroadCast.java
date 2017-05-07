package humpty.dumpty.MobileTracker.publish;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class MMainBroadCast extends BroadcastReceiver implements LocationListener {
final SmsManager sms = SmsManager.getDefault();
public void onReceive(Context context, Intent intent) {
    try {
    	SharedPreferences  pref =context.getSharedPreferences("MyFile", Context.MODE_PRIVATE);  
        String no1 = pref.getString("no1", null);
        String sim = pref.getString("simno", null);
        String status = pref.getString("status", null);
        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); 
        String sim_sr=telephony.getSimSerialNumber();
        
        
        try
        {
        Thread.sleep(10000);
        if(status.equals("1"))
        {
        if((sim.equals(sim_sr)))
        {
        //Toast.makeText(context, "HI am on1" , Toast.LENGTH_LONG).show();
        MMainBroadCast bx=new MMainBroadCast();
       	bx.abortBroadcast();
        }
        else
        {
        int n=1;
        while (n==1)
        {
        	Thread.sleep(1000);
        	sms.sendTextMessage(no1, null, "SIM CHANGED.YOUR ANDROID PHONE CURRENTLY USED BY ME ", null, null);
        	n++;
        }
        }
        }
        else
        {
       
        MMainBroadCast bx=new MMainBroadCast();
       	bx.abortBroadcast();
        }
        }
        catch (Exception e)
        {
		}
        }
        catch (Exception e) {
	    
        }
        }
public void onLocationChanged(Location arg0) {
	// TODO Auto-generated method stub
	
}
public void onProviderDisabled(String provider) {
	// TODO Auto-generated method stub
	
}
public void onProviderEnabled(String provider) {
	// TODO Auto-generated method stub
	
}
public void onStatusChanged(String provider, int status, Bundle extras) {
	// TODO Auto-generated method stub
	
}


}

 
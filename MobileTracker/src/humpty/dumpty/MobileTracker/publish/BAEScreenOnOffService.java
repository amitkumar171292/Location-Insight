package humpty.dumpty.MobileTracker.publish;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.widget.Toast;


public class BAEScreenOnOffService extends Service {
	BroadcastReceiver mReceiver=null;
	SharedPreferences pref;
	Editor edit;
	int i=0,count=1;
	public static double lat=0.0,lng=0.0;
	Handler handle=new Handler();
        @Override
        public void onCreate() {
            super.onCreate();
            
            
            // Register receiver that handles screen on and screen off logic
            IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            mReceiver = new BAEScreenOnOffReceiver();
            registerReceiver(mReceiver, filter);
            pref=getSharedPreferences("numbers",Context.MODE_PRIVATE);
        }

        @Override
        public void onStart(Intent intent, int startId) { 
        	
        	boolean screenOn = false;
        	
        	try{
        		// Get ON/OFF values sent from receiver ( AEScreenOnOffReceiver.java ) 
                screenOn = intent.getBooleanExtra("screen_state", false);
               Log.e("count",i+"");
              if(i==0)
               handle.post(timer); 
        	  i++;
        	
        	}catch(Exception e){}
        	
             //  Toast.makeText(getBaseContext(), "Service on start :"+screenOn, 
        	        //Toast.LENGTH_SHORT).show();
        	
            if (!screenOn) {
                
            	// your code here
            	// Some time required to start any service
            	//Toast.makeText(getBaseContext(), "Screen on, ", Toast.LENGTH_LONG).show();
            	
            }
            else 
            {
                
            	
            	//Toast.makeText(getBaseContext(), "Screen off,", Toast.LENGTH_LONG).show();
            }
        }

		@Override
		public IBinder onBind(Intent intent) {
			
			return null;
		}
		
		@Override
		public void onDestroy() {
			
			Log.i("ScreenOnOff", "Service  distroy");
			if(mReceiver!=null)
			 unregisterReceiver(mReceiver);
				
		}
		private class GetCurrentAddress extends AsyncTask<Location,String,HashMap<String,String>>
		{

		@Override
		protected HashMap<String,String> doInBackground(Location... loc) {
			// TODO Auto-generated method stub
			double latitude=loc[0].getLatitude();
			double longitude=loc[0].getLongitude();
			 HashMap<String,String>  address= getAddress(getApplicationContext(), latitude, longitude);
			lat=latitude;
			lng=longitude;
			
			 return address;

		}

		@Override
		protected void onPostExecute(HashMap<String,String> result) {
		// TODO Auto-generated method stub
		//Toast.makeText(getApplicationContext(), result.get("city"),Toast.LENGTH_LONG).show();
		//Log.e("country",result.get("country"));
//		Log.e("city",result.get("city"));
	//area.setText(result.get("city")+" ,"+result.get("country"));
			 SmsManager sms = SmsManager.getDefault();
    		 i=0;
    		 if(pref.getInt("size", 0)>0)
    		 {
    			 for(int i=0;i<pref.getInt("size",0);i++) {
try
{
sms.sendTextMessage(pref.getString("number"+i,"none"), null,pref.getString("msg", "message goes here")+"http://maps.apple.com/maps?q="+lat+","+lng, null, null);
Toast.makeText(getApplicationContext(), "Message send to recipients and reset for next time",Toast.LENGTH_LONG).show();

}
catch(Exception e)
{
 Log.e("error",e.getMessage()+"");
}
}
    		 }
		}	

		}
			public  HashMap<String,String> getAddress(Context ctx, double latitude, double longitude) {
				 HashMap<String,String> result=new HashMap<String, String>();
				   
				try {
		            Geocoder geocoder = new Geocoder(ctx, Locale.getDefault());
		            List<Address>
		 addresses = geocoder.getFromLocation(latitude, longitude, 1);
		            if (addresses.size() > 0) {
		                Address address = addresses.get(0);
		     
		    String country=address.getCountryName();
		    String city=address.getLocality();
		    String region_code=address.getCountryCode();
		    String zipcode=address.getPostalCode();
		    double lat =address.getLatitude();
		    double lon= address.getLongitude();
		                result.put("city",city);
		                result.put("country",country);
		    result.put("zipcode",zipcode);
		     
		            }
		        } catch (IOException e) {
		            Log.e("tag", e.getMessage());
		        }
		 
		        return result;
		    }
Runnable timer=new Runnable() {
	
	public void run() {
		// TODO Auto-generated method stub
		
			if(count>=10)
			{
			   count=0;
			   if(i>=5)
			   {
				   Location loc=new BGetLocation(getApplicationContext()).getLocation();
					if(loc!=null)
						new GetCurrentAddress().execute(loc);
					else
						Toast.makeText(getApplicationContext(), "No Location found turn on your gps", Toast.LENGTH_LONG).show();
				handle.removeCallbacks(timer);	
			   }
			}
		
			
		count++;

	handle.postDelayed(timer, 1000);
	}

};		
}
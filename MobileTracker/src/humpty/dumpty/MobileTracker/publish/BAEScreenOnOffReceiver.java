package humpty.dumpty.MobileTracker.publish;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BAEScreenOnOffReceiver extends BroadcastReceiver {

	    private boolean screenOff;

	    @Override
	    public void onReceive(Context context, Intent intent) 
	    {
	    	
	        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
	        	
	            screenOff = true;
	            
	        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
	        	
	            screenOff = false;
	            
	        }
	        
	        //Toast.makeText(context, "BroadcastReceiver :"+screenOff, Toast.LENGTH_SHORT).show();
	        
	        // Send Current screen ON/OFF value to service
	        Intent i = new Intent(context, BAEScreenOnOffService.class);
	        i.putExtra("screen_state", screenOff);
	        context.startService(i);
	    }

	}

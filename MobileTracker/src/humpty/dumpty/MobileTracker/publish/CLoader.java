package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

public class CLoader extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cload);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
                public void run() {
                        startActivity(new Intent(CLoader.this, CDisplayLogs.class));
                        finish();
                }
        }, secondsDelayed * 5000);
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
            //do your stuff
        	int pid = android.os.Process.myPid();
    	    android.os.Process.killProcess(pid);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

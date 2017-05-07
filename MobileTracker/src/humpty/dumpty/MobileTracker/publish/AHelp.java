package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class AHelp extends Activity{

	Typeface tfTitle, tfSteps, tfDes;
	TextView head, step1, step2, step3, step4, step5, step6, step7, step8, step9, step10, step11, step12, step13,step14,step15,step16,step17,step18,step19, note, des1, des2, des3,des4, des5, des6, des7, des8, des9, des10, des11, des12,des13,des14,des15,des16;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aactivity_help);
		tfTitle = Typeface.createFromAsset(getAssets(), "fonts/credits.ttf");
		tfSteps = Typeface.createFromAsset(getAssets(), "fonts/subtitles.ttf");
		tfDes = Typeface.createFromAsset(getAssets(), "fonts/description.ttf");
		head = (TextView) findViewById(R.id.head);
		step1 = (TextView) findViewById(R.id.step1);
		step2 = (TextView) findViewById(R.id.step2);
		step3 = (TextView) findViewById(R.id.step3);
		step4 = (TextView) findViewById(R.id.step4);
		step5 = (TextView) findViewById(R.id.step5);
		step6 = (TextView) findViewById(R.id.step6);
		step7 = (TextView) findViewById(R.id.step7);
		step8 = (TextView) findViewById(R.id.step8);
		step9 = (TextView) findViewById(R.id.step9);
		step10 = (TextView) findViewById(R.id.step10);
		step11 = (TextView) findViewById(R.id.step11);
		step12 = (TextView) findViewById(R.id.step12);
		step13 = (TextView) findViewById(R.id.step13);
		step14 = (TextView) findViewById(R.id.step14);
		step15 = (TextView) findViewById(R.id.step15);
		step16 = (TextView) findViewById(R.id.step16);
		step17 = (TextView) findViewById(R.id.step17);
		step18 = (TextView) findViewById(R.id.step18);
		step19 = (TextView) findViewById(R.id.step19);
		
		note = (TextView) findViewById(R.id.note);
		des1 = (TextView) findViewById(R.id.des1);
		des2 = (TextView) findViewById(R.id.des2);
		des3 = (TextView) findViewById(R.id.des3);
		des4 = (TextView) findViewById(R.id.des4);
		des5 = (TextView) findViewById(R.id.des5);
		des6 = (TextView) findViewById(R.id.des6);
		des7 = (TextView) findViewById(R.id.des7);
		des8 = (TextView) findViewById(R.id.des8);
		des9 = (TextView) findViewById(R.id.des9);
		des10 = (TextView) findViewById(R.id.des10);
		des11 = (TextView) findViewById(R.id.des11);
		des12 = (TextView) findViewById(R.id.des12);
		des13 = (TextView) findViewById(R.id.des13);
		des14 = (TextView) findViewById(R.id.des14);
		des15 = (TextView) findViewById(R.id.des15);
		des16 = (TextView) findViewById(R.id.des16);
		note = (TextView) findViewById(R.id.note);
		head.setTypeface(tfTitle);
		step1.setTypeface(tfSteps);
		step2.setTypeface(tfSteps);
		step3.setTypeface(tfSteps);
		step4.setTypeface(tfSteps);
		step5.setTypeface(tfSteps);
		step6.setTypeface(tfSteps);
		step7.setTypeface(tfSteps);
		step8.setTypeface(tfSteps);
		step9.setTypeface(tfSteps);
		step10.setTypeface(tfSteps);
		step11.setTypeface(tfSteps);
		step12.setTypeface(tfSteps);
		step13.setTypeface(tfSteps);
		step14.setTypeface(tfSteps);
		step15.setTypeface(tfSteps);
		step16.setTypeface(tfSteps);
		step17.setTypeface(tfSteps);
		step18.setTypeface(tfSteps);
		step19.setTypeface(tfSteps);
		des1.setTypeface(tfDes);
		des2.setTypeface(tfDes);
		des3.setTypeface(tfDes);
		des4.setTypeface(tfDes);
		des5.setTypeface(tfDes);
		des6.setTypeface(tfDes);
		des7.setTypeface(tfDes);
		des8.setTypeface(tfDes);
		des9.setTypeface(tfDes);
		des10.setTypeface(tfDes);
		des11.setTypeface(tfDes);
		des12.setTypeface(tfDes);
		des13.setTypeface(tfDes);
		des14.setTypeface(tfDes);
		des15.setTypeface(tfDes);
		des16.setTypeface(tfDes);
		note.setTypeface(tfSteps);
	}

	@Override
	public void onBackPressed() {
		finish();
		//overridePendingTransition(R.anim.translation_left_to_right, 0);
	}

}

package humpty.dumpty.MobileTracker.publish;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class ACredits extends Activity {

	Typeface tfCredits, tfRoboto, tfMail;

	@Override
	public void onBackPressed() {
		finish();
		//overridePendingTransition(R.anim.translation_left_to_right, 0);
		super.onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aactivity_credits);

		TextView head, title, name1, email1, name2, email2, name3, email3,name4,email4;

		head = (TextView) findViewById(R.id.textView1);
		title = (TextView) findViewById(R.id.textView2);
		name1 = (TextView) findViewById(R.id.textView3);
		email1 = (TextView) findViewById(R.id.textView4);
		name2 = (TextView) findViewById(R.id.textView5);
		email2 = (TextView) findViewById(R.id.textView6);
		name3 = (TextView) findViewById(R.id.textView7);
		email3 = (TextView) findViewById(R.id.textView8);
		name4 = (TextView) findViewById(R.id.textView9);
		email4 = (TextView) findViewById(R.id.textView10);

		tfCredits = Typeface.createFromAsset(getAssets(), "fonts/credits.ttf");

		tfRoboto = Typeface.createFromAsset(getAssets(),
				"fonts/roboto_regular.ttf");

		tfMail = Typeface.createFromAsset(getAssets(), "fonts/mail.ttf");

		head.setTypeface(tfCredits);
		title.setTypeface(tfRoboto);
		name1.setTypeface(tfRoboto);
		//email1.setTypeface(tfMail);
		name2.setTypeface(tfRoboto);
		//email2.setTypeface(tfMail);
		name3.setTypeface(tfRoboto);
		name4.setTypeface(tfRoboto);

	}

}

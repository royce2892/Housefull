package com.movieroyce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class feed extends Activity implements OnClickListener {

	EditText fn, fm, fe, ff;

	Button fs,back;
	String type;
	TextView typet, title, typef;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.feed);
		typet = (TextView) findViewById(R.id.fr);
		title = (TextView) findViewById(R.id.title);
		back=(Button) findViewById(R.id.backfeed);
		typef=(TextView) findViewById(R.id.texttype);

		type = this.getIntent().getExtras().getString("type");
		if (type.contentEquals("err")) {
			typet.setBackgroundResource(R.drawable.t_e);
			typef.setText("Error");
			title.setText("Please inform us if you find any errors in the showtimes");

		} else if (type.contentEquals("fb")) {
			title.setText("We would love your valuable feedback.It takes just a minute.Thank You");
			typef.setText("Feedback");

			typet.setBackgroundResource(R.drawable.t_f);
		}

		fn = (EditText) findViewById(R.id.fn);
		fm = (EditText) findViewById(R.id.fm);
		fe = (EditText) findViewById(R.id.fe);
		ff = (EditText) findViewById(R.id.ff);
		fs = (Button) findViewById(R.id.fsum);
		fs.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.fsum:Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL,
				new String[] { "housefullapp@outlook.com" });
		// email.putExtra(Intent.EXTRA_CC, new String[]{ to});
		// email.putExtra(Intent.EXTRA_BCC, new String[]{to});
		email.putExtra(Intent.EXTRA_SUBJECT, type);
		email.putExtra(Intent.EXTRA_TEXT,
				fn.getText() + " having mailid " + fe.getText()
						+ " having mobile no " + fm.getText()
						+ "wrote the following feedback :" + ff.getText());

		// need this to prompts email client only
		email.setType("message/rfc822");

		startActivity(email);
		break;
		case R.id.backfeed:onBackPressed();
		break;
	}
}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent myint2 = new Intent(this,more.class);
		
		startActivity(myint2);

		
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	public boolean onClose() {
		// TODO Auto-generated method stub
		finish();
		return false;
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}
	}

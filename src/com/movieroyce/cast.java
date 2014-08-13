package com.movieroyce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class cast extends Activity implements OnClickListener {

	TextView ct, title;
	String link, name;
	databaseconnect db;
	Button p, r, back;
	RatingBar ratec;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.cast);
		db = new databaseconnect(this);
		db.open();
		ct = (TextView) findViewById(R.id.castt);
		title = (TextView) findViewById(R.id.titlec);
		back = (Button) findViewById(R.id.backc);
		r = (Button) findViewById(R.id.revc);
		p = (Button) findViewById(R.id.prevc);
		name = this.getIntent().getExtras().getString("nameid");
		if (name.length() > 30) {
			title.setTextSize((float) 15d);
		}
		title.setText(name);

		ratec = (RatingBar) findViewById(R.id.ratec);
		String res[] = db.getdetails(name);
		float k = Float.valueOf(res[3]);
		ratec.setRating(k);
		ct.setMovementMethod(new ScrollingMovementMethod());
		ct.setText(res[0].replace("\\n", System.getProperty("line.separator")));
		r.setOnClickListener(this);
		p.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent myint2 = new Intent(this, detail.class);
		myint2.putExtra("nameid", name);
		myint2.putExtra("index", this.getIntent().getExtras()
				.getString("index"));
		myint2.putExtra("lang", this.getIntent().getExtras().getString("lang"));
		startActivity(myint2);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.revc:
			Intent myint2 = new Intent(this, review.class);
			myint2.putExtra("nameid", name);
			myint2.putExtra("index",
					this.getIntent().getExtras().getString("index"));
			myint2.putExtra("lang",
					this.getIntent().getExtras().getString("lang"));
			startActivity(myint2);
			break;
		case R.id.prevc:
			Intent myint = new Intent(this, preview.class);
			myint.putExtra("nameid", name);
			myint.putExtra("index",
					this.getIntent().getExtras().getString("index"));
			myint.putExtra("lang",
					this.getIntent().getExtras().getString("lang"));
			startActivity(myint);
			break;
		case R.id.backc:
			onBackPressed();
			break;
		}
	}
}

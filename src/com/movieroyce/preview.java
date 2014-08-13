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

public class preview extends Activity implements OnClickListener{

	TextView pt,title;
	String link,name;
	databaseconnect db;
	Button r,c,back;
	RatingBar ratep;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.preview);
		pt=(TextView) findViewById(R.id.preview);
		title=(TextView) findViewById(R.id.titlep);
		r=(Button) findViewById(R.id.revp);
		c=(Button) findViewById(R.id.casp);
		ratep=(RatingBar) findViewById(R.id.ratep);
		back =(Button) findViewById(R.id.backp);

		db=new databaseconnect(this);
		db.open();
		name =this.getIntent().getExtras().getString("nameid");
		
		title.setText(name);
		String res[]=db.getdetails(name);
		Float k=Float.valueOf(res[3]);
		ratep.setRating(k);
		pt.setMovementMethod(new ScrollingMovementMethod());
		pt.setText(res[1]);
		r.setOnClickListener(this);
		c.setOnClickListener(this);
		back.setOnClickListener(this);

		
	
}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent myint2 = new Intent(this, detail.class);
		myint2.putExtra("nameid", name);
		myint2.putExtra("index",this.getIntent().getExtras().getString("index"));
		myint2.putExtra( "lang",this.getIntent().getExtras().getString("lang"));
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

//	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.revp:
		Intent myint2 = new Intent(this,review.class);
		myint2.putExtra("nameid",name);
		myint2.putExtra("index",this.getIntent().getExtras().getString("index"));
		myint2.putExtra( "lang",this.getIntent().getExtras().getString("lang"));
		startActivity(myint2);
		break;
		case R.id.casp:Intent myint = new Intent(this,cast.class);
		myint.putExtra("nameid",name);
		myint.putExtra("index",this.getIntent().getExtras().getString("index"));
		myint.putExtra( "lang",this.getIntent().getExtras().getString("lang"));
		startActivity(myint);
		break;
		case R.id.backp:onBackPressed();
		break;
		
		}
	}
	
	
}


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

public class review extends Activity implements OnClickListener{

	TextView rt,title;
	String link,name;
	databaseconnect db;
	Button p,c,back;
	RatingBar ratep;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.review);
		rt=(TextView) findViewById(R.id.review);
		title=(TextView) findViewById(R.id.titler);
		p=(Button) findViewById(R.id.prevr);
		c=(Button) findViewById(R.id.casr);
		back=(Button) findViewById(R.id.backr);

		db=new databaseconnect(this);
		db.open();
		name =this.getIntent().getExtras().getString("nameid");
		title.setText(name);
		ratep=(RatingBar) findViewById(R.id.rater);
		String res[]=db.getdetails(name);
		float k=Float.valueOf(res[3]);
		ratep.setRating(k);
		String revw=res[2].replaceAll("&quot;", "\"");
		rt.setMovementMethod(new ScrollingMovementMethod());
		rt.setText(revw);
		p.setOnClickListener(this);
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
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.prevr:Intent myint2 = new Intent(this,preview.class);
		myint2.putExtra("nameid",name);
		myint2.putExtra("index",this.getIntent().getExtras().getString("index"));
		myint2.putExtra( "lang",this.getIntent().getExtras().getString("lang"));
		startActivity(myint2);
		break;
		case R.id.casr:Intent myint = new Intent(this,cast.class);
		myint.putExtra("nameid",name);
		myint.putExtra("index",this.getIntent().getExtras().getString("index"));
		myint.putExtra( "lang",this.getIntent().getExtras().getString("lang"));
		startActivity(myint);
		break;
		case R.id.backr:onBackPressed();
		break;
		}
	}
	
}

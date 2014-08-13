package com.movieroyce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class list extends Activity implements OnClickListener{

	Button eng,mal,tam,hin,back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.list);
		eng=(Button) findViewById(R.id.eng);
		mal=(Button) findViewById(R.id.mal);
		tam=(Button) findViewById(R.id.hin);
		hin=(Button) findViewById(R.id.tam);
		back=(Button) findViewById(R.id.backl);
		eng.setOnClickListener(this);
		mal.setOnClickListener(this);
		hin.setOnClickListener(this);
		tam.setOnClickListener(this);
		back.setOnClickListener(this);
	/*	int imgID = getResources().getIdentifier("button_pressed", "drawable", getApplication().getPackageName());
		eng.setBackgroundResource(imgID);
		mal.setBackgroundResource(imgID);
		hin.setBackgroundResource(imgID);
		tam.setBackgroundResource(imgID);*/


	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent myint2 = new Intent(this, index.class);

		
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
		switch (v.getId())
		{
		case R.id.mal:Intent myint2 = new Intent(this,movielist.class);
		myint2.putExtra("index",0);
		myint2.putExtra("lang","malayalam");
		startActivity(myint2);
		break;
		case R.id.eng:Intent myint1 = new Intent(this,movielist.class);
		myint1.putExtra("index",0);
		myint1.putExtra("lang","english");
		startActivity(myint1);
		break;
		case R.id.hin:Intent myint = new Intent(this,movielist.class);
		myint.putExtra("index",0);
		myint.putExtra("lang","hindi");
		startActivity(myint);
		break;
		case R.id.tam:Intent myint3 = new Intent(this,movielist.class);
		myint3.putExtra("index",0);
		myint3.putExtra("lang","tamil");
		startActivity(myint3);
		break;
		case R.id.backl:onBackPressed();
		break;
	}



	}
	}

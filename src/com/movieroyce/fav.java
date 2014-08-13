package com.movieroyce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class fav extends Activity implements OnClickListener{

	Button bm[] = new Button[6];
	Button nxt,prev,back;
	String namesdb[]=new String[50];
	databaseconnect db;
	
	int start,end;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.mallu_list);
		db = new databaseconnect(this);
		db.open();
		back=(Button) findViewById(R.id.backml);
		start =this.getIntent().getExtras().getInt("index");
		back.setOnClickListener(this);
		
		end=start+6;
		bm[0] = (Button) findViewById(R.id.bm1);
		bm[1] = (Button) findViewById(R.id.bm2);
		bm[2] = (Button) findViewById(R.id.bm3);
		bm[3] = (Button) findViewById(R.id.bm4);
		bm[4] = (Button) findViewById(R.id.bm5);
		bm[5] = (Button) findViewById(R.id.bm6);
		
		nxt = (Button) findViewById(R.id.nxt);
		prev = (Button) findViewById(R.id.prev);
		
		namesdb = db.getfavmovie();
		
		for (int i = 0; start+i < namesdb.length && i<6; i++) {
			bm[i].setText(namesdb[start+i]);
			bm[i].setOnClickListener(this);
		}
		if (start==0)
			prev.setBackgroundResource(0);
		nxt.setOnClickListener(this);
		int i=namesdb.length;
		int flag=0;
	//	Toast.makeText(this, i+"", Toast.LENGTH_SHORT).show();
		for(int j=5;j>=i;j--)
		{
			bm[j].setBackgroundResource(0);
			flag=1;
		}
		if(flag==1)
			nxt.setBackgroundResource(0);
			
		
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
		switch (v.getId()) {
		case R.id.bm1:
			call(namesdb[start],start);
			break;
		case R.id.bm2:
			call(namesdb[start+1],start);
			break;
		case R.id.bm3:
			call(namesdb[start+2],start);
			break;
		case R.id.bm4:
			call(namesdb[start+3],start);
			break;
		case R.id.bm5:
			call(namesdb[start+4],start);
			break;
		case R.id.bm6:
			call(namesdb[start+5],start);
			break;
		case R.id.backml:onBackPressed();break;
		case R.id.nxt:
			Intent myint2 = new Intent(this,fav.class);
		
		myint2.putExtra("index",end);
		startActivity(myint2);
			break;

		}

	}

	public void call(String name,int start) {

		Intent myint = new Intent(this, detail.class);
		myint.putExtra("nameid", name);
		myint.putExtra("index","NA");
		myint.putExtra("lang","NA");

		startActivity(myint);
	}

		

	}

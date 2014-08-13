package com.movieroyce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class movielist extends Activity implements OnClickListener{

	Button bm[] = new Button[6];
	Button nxt,prev,back;
	String namesdb[]=new String[50];
	databaseconnect db;
	String lang;
	int start,index,end;
	TextView mname;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.mallu_list);
		mname=(TextView) findViewById(R.id.mname);
		 back=(Button) findViewById(R.id.backml);
		 back.setOnClickListener(this);
		db = new databaseconnect(this);
		db.open();
		
		start=this.getIntent().getExtras().getInt("index");
		 lang =this.getIntent().getExtras().getString("lang");
	//	 mname.setText(lang);
		 
//		 Toast.makeText(this, start+"", Toast.LENGTH_SHORT).show();
		// Log.i("info",start+"");
		
		if(!db.isdata(lang))
		{
			Toast.makeText(this, "No data in Database", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		end=start+6;
		bm[0] = (Button) findViewById(R.id.bm1);
		bm[1] = (Button) findViewById(R.id.bm2);
		bm[2] = (Button) findViewById(R.id.bm3);
		bm[3] = (Button) findViewById(R.id.bm4);
		bm[4] = (Button) findViewById(R.id.bm5);
		bm[5] = (Button) findViewById(R.id.bm6);
		
		nxt = (Button) findViewById(R.id.nxt);
		prev = (Button) findViewById(R.id.prev);
		if(start==0)
		{
			prev.setBackgroundResource(0);
			prev.setText("");
		}
		else
			prev.setOnClickListener(this);

		
		namesdb = db.getmovie("",lang);
		int i,j;
		for (i = 0,j=namesdb.length-1; start+i < namesdb.length && i<6; i++,j--) {
			bm[i].setText(namesdb[j-start]);
			bm[i].setOnClickListener(this);
		}
		int flag=0;
		 i=namesdb.length;
	//	Log.i("info",i+"");
		//	Toast.makeText(this, i+"", Toast.LENGTH_SHORT).show();
			for( j=5;j>=i-start;j--)
			{
				bm[j].setBackgroundResource(0);
				flag=1;
			}
		if(flag==1)
		{
			nxt.setBackgroundResource(0);
			nxt.setText("");
		}
		else
			nxt.setOnClickListener(this);
}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent myint2 = new Intent(this, list.class);
		
		startActivity(myint2);
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
			call(bm[0].getText().toString(),start,lang);
			break;
		case R.id.bm2:
			call(bm[1].getText().toString(),start,lang);
			break;
		case R.id.bm3:
			call(bm[2].getText().toString(),start,lang);
			break;
		case R.id.bm4:
			call(bm[3].getText().toString(),start,lang);
			break;
		case R.id.bm5:
			call(bm[4].getText().toString(),start,lang);
			break;
		case R.id.bm6:
			call(bm[5].getText().toString(),start,lang);
			break;
		
		case R.id.nxt:
			
			Intent myint2 = new Intent(this,movielist.class);
			
		myint2.putExtra("index",end);
		myint2.putExtra("lang",lang);
		startActivity(myint2);
		break;
		case R.id.prev:
			if(start==0)
			{
				prev.setBackgroundResource(0);
			}
			else
			{
			 myint2 = new Intent(this,movielist.class);
			
		myint2.putExtra("index",start-6);
		myint2.putExtra("lang",lang);
		startActivity(myint2);}
			break;
		case R.id.backml:onBackPressed();
		break;

		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	public void call(String name,int start2,String lang) {

		Intent myint = new Intent(this, detail.class);
		myint.putExtra("nameid", name);
		myint.putExtra("index", start2);
		myint.putExtra("lang", lang);

		startActivity(myint);
	}

		

	}

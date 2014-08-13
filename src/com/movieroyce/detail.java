package com.movieroyce;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class detail extends Activity  implements OnClickListener{
	
	String name,link,lang;
	Button pre,re,cas,sht,fav,back;
	int index;
	TextView mname;
	RatingBar rated;
	databaseconnect db;
	Boolean bl;
	@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);

	  db=new databaseconnect(this);
	db.open();
	requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	setContentView(R.layout.detail);
	name =this.getIntent().getExtras().getString("nameid");
	index =this.getIntent().getExtras().getInt("index");
	 lang =this.getIntent().getExtras().getString("lang");
	 mname=(TextView) findViewById(R.id.mname);
	 mname.setText(name);
	 back=(Button) findViewById(R.id.back);
	pre=(Button) findViewById(R.id.preview);
	re=(Button) findViewById(R.id.review);
	cas=(Button) findViewById(R.id.cast);
	sht=(Button) findViewById(R.id.sht);
	fav=(Button) findViewById(R.id.fav);
	rated=(RatingBar) findViewById(R.id.rated);
	
	String res[]=db.getdetails(name);
	float k=Float.valueOf(res[3]);	
	rated.setRating(k);
	
	back.setOnClickListener(this);
	pre.setOnClickListener(this);
	re.setOnClickListener(this);
	cas.setOnClickListener(this);
	sht.setOnClickListener(this);
	fav.setOnClickListener(this);
	bl=db.isfav(name);
	if(bl==true)
		fav.setText("Remove from favourites");
	else
		fav.setText("Add to favourites");
}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent myint2 = new Intent(this, movielist.class);

		myint2.putExtra("nameid", name);
		myint2.putExtra("index", index);
		myint2.putExtra("lang", lang);
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
		case R.id.preview:Intent myint2 = new Intent(this,preview.class);
		myint2.putExtra("nameid",name);
		myint2.putExtra("index",this.getIntent().getExtras().getInt("index"));
		myint2.putExtra( "lang",this.getIntent().getExtras().getString("lang"));
		startActivity(myint2);
		break;
		case R.id.review:myint2 = new Intent(this,review.class);
		myint2.putExtra("nameid",name);
		myint2.putExtra("index",this.getIntent().getExtras().getInt("index"));
		myint2.putExtra( "lang",this.getIntent().getExtras().getString("lang"));
		startActivity(myint2);
		break;
		case R.id.cast:myint2 = new Intent(this,cast.class);
		myint2.putExtra("nameid",name);
		myint2.putExtra("index",this.getIntent().getExtras().getInt("index"));
		myint2.putExtra( "lang",this.getIntent().getExtras().getString("lang"));
		startActivity(myint2);
		break;
		case R.id.sht:myint2 = new Intent(this,sht.class);
		myint2.putExtra("nameid",name);
		myint2.putExtra("index",this.getIntent().getExtras().getInt("index"));
		myint2.putExtra( "lang",this.getIntent().getExtras().getString("lang"));
		startActivity(myint2);
		break;
		case R.id.fav://code for adding as a favorite
			
			Boolean i=db.isfav(name);
			if(i==true)
			{
				db.remfav(name);
				Toast.makeText(this, "Removed from favourites", Toast.LENGTH_SHORT).show();
			}
			else
			{
				db.addtofav(name);
				Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
			}
				
		break;
		case R.id.back:onBackPressed();
		break;
		
		}
		
	}
}
	


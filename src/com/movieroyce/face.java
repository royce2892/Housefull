package com.movieroyce;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

public class face extends Activity implements OnClickListener{

	WebView wb;
	ImageView img;
	Button bk;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.face);
		img=(ImageView) findViewById(R.id.social);
		wb=(WebView) findViewById(R.id.web);
		bk=(Button) findViewById(R.id.backface);
		bk.setOnClickListener(this);
		String type = this.getIntent().getExtras().getString("type");
		 wb.setWebViewClient(new WebViewClient() {
	            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
	              
	            }
	        });
		if(type.equals("fb"))
		{
			img.setBackgroundResource(R.drawable.t_fb);
			wb.getSettings().setJavaScriptEnabled(true);

			wb.loadUrl("http://www.facebook.com/housefullapp");
		}
		else
		{
			img.setBackgroundResource(R.drawable.t_tw);
			wb.getSettings().setJavaScriptEnabled(true);
			wb.loadUrl("https://twitter.com/housefullapp");
			
		}

		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent myint2 = new Intent(this, more.class);
		startActivity(myint2);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case  R.id.backface:onBackPressed();
		break;
		}
	}

}

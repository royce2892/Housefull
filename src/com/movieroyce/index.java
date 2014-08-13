package com.movieroyce;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class index extends Activity implements OnClickListener {

	Button mov, sht, hlp, abo, ex;
	String names[] = new String[50];
	String[] mname = new String[50];
	String namesdb[] = new String[50];
	String[] addr = new String[50];
	ImageView img;
	int spos[] = new int[12];
	int epos[] = new int[12];
	databaseconnect db;
	String txt;
	static String theatre[] = new String[100];
	static String movie[] = new String[100];
	static String timing[] = new String[100];
	int s = 0, e = 0;
	 ProgressDialog dialog;
	int x=0;
	int y=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.index);
	     dialog = ProgressDialog.show(this, "", "Loading. Please wait...", true);

		db = new databaseconnect(this);

		db.open();
		
		mov = (Button) findViewById(R.id.mov);
		sht = (Button) findViewById(R.id.sht);
		hlp = (Button) findViewById(R.id.fav);
		abo = (Button) findViewById(R.id.more);
		mov.setOnClickListener(this);
		sht.setOnClickListener(this);
		hlp.setOnClickListener(this);
		abo.setOnClickListener(this);
	/*	int imgID = getResources().getIdentifier("button_pressed", "drawable",
				getApplication().getPackageName());
		mov.setBackgroundResource(imgID);
		sht.setBackgroundResource(imgID);
		hlp.setBackgroundResource(imgID);
		abo.setBackgroundResource(imgID);*/
		


	/*	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);*/
		
	//	db.drop();
		
		
			 ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    if( activeNetworkInfo == null || !activeNetworkInfo.isConnected())
	    {
	    	Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
	    	dialog.dismiss();
	    }
	    else {
        	loaddata ld = new loaddata();
       // 	db.drop();
			ld.setPriority(Thread.MAX_PRIORITY);
			 ld.start();
	    }
		
		}

	//}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mov:
			Intent myint = new Intent(this, list.class);
			startActivity(myint);

			break;
		case R.id.sht:
			myint = new Intent(this, sht.class);
			startActivity(myint);
			break;
		case R.id.more:
			myint = new Intent(this, more.class);
			startActivity(myint);
			break;
		
		case R.id.fav:
			// db.open();
			if (db.getfavmovie() == null) {
				Toast.makeText(this, "No movies added", Toast.LENGTH_SHORT)
						.show();

			} else {
				myint = new Intent(this, fav.class);
				myint.putExtra("index", 0);
				startActivity(myint);
			}
			break;

		}

	}

	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	public void onBackPressed() {
		// TODO Auto-generated method stub
	//	super.onBackPressed();
		new AlertDialog.Builder(this)
				.setMessage("Are you sure you want to exit?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								finish();
							}
						}).setNegativeButton("No", null).show();
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



	/*
	 * @SuppressWarnings("deprecation") protected Dialog onCreateDialog(int id)
	 * { switch (id) { case 1: mProgressDialog = new ProgressDialog(this);
	 * mProgressDialog.setMessage("Loading stuff..");
	 * mProgressDialog.setCancelable(true); return mProgressDialog; } return
	 * super.onCreateDialog(id); }
	 */
	public class loaddata extends Thread {

		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String tind=null;
			try {
				Document doci = Jsoup.connect(
						"http://www.dialblood.com/roycee/index.html").get();
				if (doci!=null)
				{
				String indd=doci+"";
				int st,en;
				
				st=indd.indexOf("<i>");
				en=indd.indexOf("</i>");
				st=st+3;
				tind=indd.substring(st,en);}


				
			} catch (IOException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			}
	//		if(tind!="null" && !tind.equals((db.getloaded()+"")))
	//		{
				
				
			String lang[] = { "malayalam", "hindi", "tamil", "english" };
			for (int c = 0; c < 4; c++) {
				
					Document doc = null;
					try {
						doc = Jsoup.connect(
								"http://www.dialblood.com/roycee/" + lang[c]
										+ ".html").get();
					} catch (IOException e5) {
						// TODO Auto-generated catch block
						e5.printStackTrace();
					}
					if(doc!=null)
					{
					Elements links = doc.select("a[href]");
					String txt=doc+"";
					int s=txt.indexOf("<b>");
					int e=txt.indexOf("</b>",s);
					s=s+3;
					String more=txt.substring(s, e);
					int add=Integer.parseInt(more);
					s=0;e=0;
					int before;
					if(db.getmovie("", lang[c])==null)
					{
						before=0;
					}
					else
					{
						before=db.getmovie("", lang[c]).length;
					}
					Log.i("info",before+"");
					for (int i = before; i < add;i++) {
						names[i] = links.get(i).toString();
						s = names[i].indexOf("=\"");
						s = s + 2;
						e = names[i].indexOf("\">", s);
						addr[i] = names[i].substring(s, e);
						System.out.println(addr[i]);
						s = names[i].indexOf("html");
						s = s + 6;
						e = names[i].indexOf("</a", s);
						mname[i] = names[i].substring(s, e);
						System.out.println(mname[i]);
						// bm[i].setText(mname[i]);
						Document docp = null;
						try {
							docp = Jsoup.connect(
									"http://www.dialblood.com/roycee/" + addr[i])
									.get();
						} catch (IOException e5) {
							// TODO Auto-generated catch block
							e5.printStackTrace();
						}
						txt = docp + "";
						int s1 = txt.indexOf("div id=\"preview\"");
						int e1 = txt.indexOf("</div>", s1);
						s1 = s1 + 18;
						String prev = txt.substring(s1, e1);
						System.out.println(prev);
						int s2 = txt.indexOf("div id=\"review\"");
						int e2 = txt.indexOf("</div>", s2);
						s2 = s2 + 17;
						String rev = txt.substring(s2, e2);
						System.out.println(rev);
						int s3 = txt.indexOf("div id=\"cast\"");
						int e3 = txt.indexOf("</div>", s3);
						s3 = s3 + 15;
						String cast = txt.substring(s3, e3);
						System.out.println(cast);
						int s4 = txt.indexOf("div id=\"rating\"");
						int e4 = txt.indexOf("</div>", s4);
						s4 = s4 + 17;
						String rate = txt.substring(s4, e4);
						System.out.println(rate);
						
						db.insert(lang[c], mname[i], prev, rev, cast, rate);

					}
				}
			}
			String dis[]=new String[14];
			if(tind!=null )
				if(!tind.equals((db.getloaded()+"")))
			{
					db.drop();
				dis[0]="Trivandrum";
	        	dis[1]="Kochi";
				dis[2]="Calicut";
				dis[3]="Trichur";
				dis[4]="Kollam";
				dis[5]="Kottayam";
				dis[6]="Allepey";
				dis[7]="Wayanad";
				dis[8]="Pathanamthitta";
				dis[9]="Malapuram";
				dis[10]="Kasargod";
				dis[11]="Kannur";
				dis[12]="Palakkad";
				dis[13]="Idukki";
			
			//Log.i("in loop", "in loop");
			Document doc=null;
			for(int jj=0;jj<14;jj++)
			try {
				doc = Jsoup.connect(
						"http://www.dialblood.com/roycee/showtimes/"+dis[jj]+".html")
						.get();
			
				if(doc!=null)
				{
				txt = doc + "";
				int h = 0, j = 0;
				int ss=0,ee=0;
				ss=txt.indexOf("<b>");
				e=ee=txt.indexOf("</b>");
				ss+=3;
				int count=Integer.parseInt(txt.substring(ss, ee));

				for (int i = 0; i < count; i++) {
					j = txt.indexOf("</div>", h);
					s = txt.indexOf("<b>", e);
					e = txt.indexOf("</b>", s);
					if (s > j)

						h = j + 5;

					h = txt.indexOf("<div ", h);

				//	Log.i("info",txt.substring(txt.indexOf("\"", h) + 1,txt.indexOf("\">", h)));
					String pls = txt.substring(txt.indexOf("\"", h) + 1,
							txt.indexOf("\">", h));
					
					s = s + 3;
					theatre[i] = txt.substring(s, e);
					System.out.print(theatre[i]);
					System.out.println(e);
					s = txt.indexOf("<i>", e);
					e = txt.indexOf("</i>", s);
					s = s + 3;
					movie[i] = txt.substring(s, e);
					System.out.print("   " + movie[i]);
					s = txt.indexOf("<u>", e);
					e = txt.indexOf("</u>", s);
					s = s + 3;
					timing[i] = txt.substring(s, e);
					System.out.print("   " + timing[i]);
					System.out.println();

					db.insertshowtimes(theatre[i], dis[jj], pls, movie[i],
							timing[i]);

				}
			}
			}
			 catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			 
			 db.setloaded(tind);
			}
			dialog.dismiss();	
			}
		
	}
	}
		
	

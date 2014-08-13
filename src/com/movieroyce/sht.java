package com.movieroyce;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class sht extends Activity implements OnClickListener,android.view.View.OnKeyListener {
	int s=0,e=0;
	 String txt;
	    static String theatre[]=new String[100];
	    static String movie[]=new String[100];
	    static String timing[]=new String[100];
	    databaseconnect db;
	    EditText ss;
	    ListView ls;
		LinearLayout lay;
		Spinner sp;
		Button back;


		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub 
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setContentView(R.layout.sht);
			lay = (LinearLayout) findViewById(R.id.lay);
			ss = (EditText) findViewById(R.id.search);
		//	ls = (ListView) findViewById(R.id.list);
	       ss.setOnKeyListener( (android.view.View.OnKeyListener) this);
	       ss.setFocusable(true);
	        back=(Button) findViewById(R.id.backsht);
	        back.setOnClickListener(this);
	       
	       
		//	Spinner spinner = (Spinner)findViewById(R.id.spinnermov);
			db = new databaseconnect(this);
			db.open();
		
			sp=(Spinner) findViewById(R.id.dist);
			
			
			
			
		
	/*		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
			.permitAll().build();
	StrictMode.setThreadPolicy(policy);	*/
		
		if(!db.isdatashow())
		{
			Toast.makeText(this, "No data in Database", Toast.LENGTH_SHORT).show();
			finish();
		}
		}

		
		
	 
	    
	    	private void showResults(String newText) {
	    		final LinearLayout l=(LinearLayout) findViewById(R.id.linearlay);
l.removeAllViewsInLayout();
final TextView num=(TextView) findViewById(R.id.num);
num.setText("No Results\n");
	    		// TODO Auto-generated method stub
if(newText.length()<=0)
	 return;
	    		//Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();
	    		Cursor cursor=db.getcursor(newText);
	    		String from[]={datahelp.column_film};

	    		int to[]={R.id.nameres};

	    		 @SuppressWarnings("deprecation")
				final
				SimpleCursorAdapter customers = new SimpleCursorAdapter(this,R.layout.srchres, cursor, from, to);
	             
	    		final ListView lv;
	    		lv=(ListView) findViewById(R.id.list);
				lv.setAdapter(customers);
				
				lv.setOnItemClickListener(new OnItemClickListener() {
		                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		                    // Get the cursor, positioned to the corresponding row in the result set
		                   
		                    
		                    Cursor cursor1 = (Cursor) lv.getItemAtPosition(position);
		                   
		                    
			                    // Get the state's capital from this row in the database.
		                    
		                    String film = cursor1.getString(cursor1.getColumnIndexOrThrow(datahelp.column_film));
		                    customers.getCursor().close();
		                    customers.notifyDataSetChanged();
		                    ArrayList<String[]> shows=new ArrayList<String[]>();
		                    shows.clear();
		                    shows=db.getshowtimedetails(film,sp.getSelectedItem().toString());
		                    if(shows.size()==0)
		                    {
		                    //	Log.i("info","noresults");
		                    	num.setText("No Results\n");
		                    	l.removeAllViewsInLayout();
		                    	//Toast.makeText(getParent(), "No Theatre Showing Film", Toast.LENGTH_SHORT).show();
		                    	return;
		                    }

	                    	l.removeAllViewsInLayout();
		                    /*LinearLayout f=(LinearLayout) findViewById(R.id.lin);
		                    
		                    f.removeView(l);
		                    l=new LinearLayout(getLayoutInflater().getContext());
		                    l.setId(R.id.linearlay);
		                    
		                    f.addView(l);*/
		                    num.setText(shows.size()+" results");
		                 //   Log.i("info","results");
		                    for(int i=0;i<shows.size();i++)
		                    {	
		                   //   Log.i("info","layout");
		                      
		                	  group gr=new group(getLayoutInflater().getContext(), (i+1)+"",shows.get(i)[0], shows.get(i)[2], shows.get(i)[3]);
		                	   l.addView(gr);
		                	   
		                	   
		                	   
		                   }
		                    shows.clear();
		                    //Check if the Layout already exists
		                   /* LinearLayout customerLayout = (LinearLayout)findViewById(R.id.customerLayout));
		                    if(customerLayout == null){
		                        //Inflate the Customer Information View
		                        LinearLayout leftLayout = (LinearLayout)findViewById(R.id.rightLayout);
		                        View customerInfo = getLayoutInflater().inflate(R.layout.customerinfo, leftLayout, false);
		                        leftLayout.addView(customerInfo);
		                    }
		 */
		                    //Get References to the TextViews
		                    //TextView tnameText = (TextView) findViewById(R.id.tname);
		                    //tnameText.setText(teatname);
		                 /*   nameText = (TextView) findViewById(R.id.name);
		                    addressText = (TextView) findViewById(R.id.address);
		                    cityText = (TextView) findViewById(R.id.city);
		                    stateText = (TextView) findViewById(R.id.state);
		                    zipCodeText = (TextView) findViewById(R.id.zipCode);
		                
		                    // Update the parent class's TextView
		                    customerText.setText(customer);
		                    nameText.setText(name);
		                    addressText.setText(address);
		                    cityText.setText(city);
		                    stateText.setText(state);
		                    zipCodeText.setText(zipCode);
		            */
		              //      ss.setQuery("",true);
		                }
		            });

	    		}
	        
	
		@Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			finish();
		}


		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			onBackPressed();
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
			Intent myint2 = new Intent(this,index.class);
		
			startActivity(myint2);

		}





		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			showResults(ss.getText().toString());
			return false;
		}

		
		}

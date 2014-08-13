package com.movieroyce;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


public class group extends LinearLayout{
	
	public group(Context context,String num,String name,String place,String time) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater inflator=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflator.inflate(R.layout.shows, group.this, true);
		TextView numb=(TextView) findViewById(R.id.number);
		numb.setText(num+". ");
		TextView tnameText = (TextView) findViewById(R.id.tname);
        tnameText.setText(name+" ");
        TextView pla = (TextView) findViewById(R.id.tplace);
        pla.setText(place+" ");
        TextView timing = (TextView) findViewById(R.id.timing);
        timing.setText(time);
	}
	
}

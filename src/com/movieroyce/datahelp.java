package com.movieroyce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class datahelp extends SQLiteOpenHelper{
	public datahelp(Context context) {
		super(context, "movie.db", null, 1);
		// TODO Auto-generated constructor stub
	}
	public static String column_movie_name="movie_name";
	public static String column_preview="preview";
	public static String column_cast="cast";
	public static String column_review="review";
	public static String column_rating="rating";
	public static String column_fav="fav";
	public static String column_language="language";
	public static String table1="details";
	public static String table2="showtimes";
	public static String column_theatre_name="theatre_name";
	public static String column_district="district";
	public static String column_place="place";
	public static String column_timing="timing";
	public static String column_film="film";
	public static String table3="datac";

	public static String column_valid="val";
	public static String column_mindex="mindex";
	public static String column_tindex="tindex";

	String createtable1="create table "+table1+"("+ column_movie_name+" text,"+column_language+" text,"+
			column_fav+" text ,"+column_preview+" text ,"+column_cast+" text"+", "+column_review+" text, "+ column_rating+" text);";
	
	String createtable2="create table "+table2+"("+ column_theatre_name+" text,"+
			column_district+" text ,"+column_place+" text"+", "+column_film+" text, "+ column_timing+" text);";
	String createtable3="create table "+table3+ " ( " +column_valid+" text);";
	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		database.execSQL(createtable1);
		database.execSQL(createtable2);
		database.execSQL(createtable3);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("drop table "+table1);
		db.execSQL("drop table "+table2);
		db.execSQL("drop table "+table3);

		onCreate(db);
	}

}

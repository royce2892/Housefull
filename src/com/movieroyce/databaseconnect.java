package com.movieroyce;

import java.util.ArrayList;
import com.movieroyce.datahelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class databaseconnect {
	private SQLiteDatabase db;
	private datahelp dbhelp;
	private final Context con;

	public databaseconnect(Context context) {
		// TODO Auto-generated constructor stub
		this.con = context;
		dbhelp = new datahelp(con);
	}

	public void open() {
		db = dbhelp.getWritableDatabase();
	}

	public void close() {
		dbhelp.close();
	}

	public void insert(String lang, String name, String preview, String review,
			String cast, String rate) {
		ContentValues conv = new ContentValues();
		conv.put(datahelp.column_movie_name, name);
		conv.put(datahelp.column_preview, preview);
		conv.put(datahelp.column_language, lang);
		conv.put(datahelp.column_review, review);
		conv.put(datahelp.column_cast, cast);
		conv.put(datahelp.column_rating, rate);
		conv.put(datahelp.column_fav, "0");
		db.insert(datahelp.table1, null, conv);
	}

	public void insertshowtimes(String name, String dist, String place,
			String film, String time) {
		ContentValues conv = new ContentValues();
		conv.put(datahelp.column_theatre_name, name);
		conv.put(datahelp.column_district, dist);
		conv.put(datahelp.column_place, place);
		conv.put(datahelp.column_film, film);
		conv.put(datahelp.column_timing, time);
		db.insert(datahelp.table2, null, conv);
	}

	public String[] getdetails(String name) {
		String results[] = new String[4];
		String[] wherearg = { name };
		Cursor cursor = db.query(datahelp.table1, null,
				datahelp.column_movie_name + "=? ", wherearg, null, null, null);
		cursor.moveToFirst();
		if (cursor.isAfterLast())
			return null;
		results[0] = cursor.getString(cursor
				.getColumnIndex(datahelp.column_cast));
		results[1] = cursor.getString(cursor
				.getColumnIndex(datahelp.column_preview));
		results[2] = cursor.getString(cursor
				.getColumnIndex(datahelp.column_review));
		results[3] = cursor.getString(cursor
				.getColumnIndex(datahelp.column_rating));

		cursor.close();
		return results;
	}

	public ArrayList<String[]> getshowtimedetails(String name, String dist) {
		ArrayList<String[]> res = new ArrayList<String[]>();
		res.clear();
		Cursor cursor = db.query(datahelp.table2, null, datahelp.column_film
				+ " like '" + name + "%' and " + datahelp.column_district
				+ "='" + dist + "'", null, null, null, null);
		// cursor.moveToFirst();
		// if(cursor.isAfterLast())
		// return null;
		while (cursor.moveToNext()) {
			String results[] = new String[4];
			results[0] = cursor.getString(cursor
					.getColumnIndex(datahelp.column_theatre_name));
			results[1] = cursor.getString(cursor
					.getColumnIndex(datahelp.column_district));
			results[2] = cursor.getString(cursor
					.getColumnIndex(datahelp.column_place));
			results[3] = cursor.getString(cursor
					.getColumnIndex(datahelp.column_timing));
			//Log.i("info", results[0] + results[2] + results[3]);
			res.add(results);
		}
		cursor.close();
		return res;
	}

	public void drop() {
		// db.delete(datahelp.table1, null, null);
		db.delete(datahelp.table2, null, null);
		// SQLiteDatabase.deleteDatabase(new File("movie.db"));
	}

	/*
	 * public void update(String name,Float rating) { ContentValues conv=new
	 * ContentValues(); String [] wherearg={name}; Float rat=getrate(name); int
	 * co=getcount(name); rating=(rating+rat*co)/(co+1);
	 * conv.put(datahelp.column_rating,rating);
	 * conv.put(datahelp.column_count,co+1); long
	 * insertid=db.update(datahelp.table, conv,
	 * datahelp.column_name+"=? ",wherearg ); }
	 */
	public Float getrate(String name) {
		String[] wherearg = { name };
		Cursor cursor = db.query(datahelp.table1, null,
				datahelp.column_movie_name + "=? ", wherearg, null, null, null);
		cursor.moveToFirst();
		if (cursor.isAfterLast())
			return (float) 0.0;
		Float x = cursor
				.getFloat(cursor.getColumnIndex(datahelp.column_rating));
		cursor.close();
		return x;
	}

	public String[] getmovie(String match, String lan) {
		int i = 0;
		Cursor cursor = db.query(datahelp.table1, null,
				datahelp.column_movie_name + " like '" + match + "%' and "
						+ datahelp.column_language + " like '%" + lan + "%' ",
				null, null, null, null);
		String results[] = new String[cursor.getCount()];
		cursor.moveToFirst();
		if (cursor.isAfterLast())
			return null;
		while (!cursor.isAfterLast()) {
			results[i++] = cursor.getString(cursor
					.getColumnIndex(datahelp.column_movie_name));
			cursor.moveToNext();
		}
		cursor.close();
		return results;
	}

	public void addtofav(String name) {
		ContentValues conv = new ContentValues();
		conv.put(datahelp.column_fav, "1");
		String[] wherearg = { name };
		db.update(datahelp.table1, conv, datahelp.column_movie_name + "=? ",
				wherearg);
	}

	public void remfav(String name) {
		ContentValues conv = new ContentValues();
		conv.put(datahelp.column_fav, "0");
		String[] wherearg = { name };
		db.update(datahelp.table1, conv, datahelp.column_movie_name + "=? ",
				wherearg);
	}

	public Boolean isfav(String name) {
		String[] wherearg = { name };
		Cursor cursor = db.query(datahelp.table1, null,
				datahelp.column_movie_name + "=?", wherearg, null, null, null);
		cursor.moveToFirst();
		if (cursor.getString(cursor.getColumnIndex(datahelp.column_fav))
				.equals("1"))
			return true;
		return false;
	}

	public String[] getfavmovie() {
		int i = 0;
		Cursor cursor = db.query(datahelp.table1, null, datahelp.column_fav
				+ "= '1'", null, null, null, null);
		String results[] = new String[cursor.getCount()];
		cursor.moveToFirst();
		if (cursor.isAfterLast())
			return null;
		while (!cursor.isAfterLast()) {
			results[i++] = cursor.getString(cursor
					.getColumnIndex(datahelp.column_movie_name));
			cursor.moveToNext();
		}
		cursor.close();
		return results;
	}

	public Cursor getcursor(String match) {
		// Cursor mCursor= db.query(datahelp.table1, null,
		// datahelp.column_movie_name+ " like '"+match+"%'", null, null, null,
		// null);
		Cursor mCursor = db.rawQuery("select distinct "+datahelp.column_film+" , "+datahelp.column_valid +" as _id from "
				+ datahelp.table2 + " , "+datahelp.table3+" where " + datahelp.column_film
				+ " like '" + match + "%'", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public Boolean isdata(String match) {
		Cursor cursor = db.query(datahelp.table1, null,
				datahelp.column_language + " like '" + match + "%'", null,
				null, null, null);
		cursor.moveToFirst();
		if (cursor.isAfterLast())
			return false;
		return true;
	}

	public Boolean isdatashow() {
		Cursor cursor = db.query(datahelp.table2, null, null, null, null, null,
				null);
		cursor.moveToFirst();
		if (cursor.isAfterLast() )
			return false;
		return true;
	}

	public int getloaded() {
		Cursor cursor=db.query(datahelp.table3, null, null, null, null, null, null);
		cursor.moveToFirst();
		if(cursor.isAfterLast())
		{
			insertloaded();
			return 0;
		}
		else
			return cursor.getInt(0);
	}

	public void setloaded(String tind) {
	ContentValues conv=new ContentValues();
		conv.put(datahelp.column_valid,tind);
		db.update(datahelp.table3, conv,null,null );
		}

	public void insertloaded() {
		ContentValues conv=new ContentValues();
		conv.put(datahelp.column_valid,0);
		db.insert(datahelp.table3, null,conv);
		}

	public String[] getshowtimebydist(String dist) {
		String results[] = new String[4];
		String[] wherearg = { dist };
		Cursor cursor = db.query(datahelp.table2, null,
				datahelp.column_district + "=?", wherearg, null, null, null);
		cursor.moveToFirst();
		if (cursor.isAfterLast())
			return null;
		results[0] = cursor.getString(cursor
				.getColumnIndex(datahelp.column_theatre_name));
		results[1] = cursor.getString(cursor
				.getColumnIndex(datahelp.column_district));
		results[2] = cursor.getString(cursor
				.getColumnIndex(datahelp.column_place));
		results[3] = cursor.getString(cursor
				.getColumnIndex(datahelp.column_timing));
		cursor.close();
		return results;
	}
}

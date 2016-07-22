package nutech.call.blocker;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BlacklistDAO {

	// SQLiteDatabase and DatabaseHelper objects  to access SQLite database
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;

	// Constructor initiates the DatabaseHelper to make sure, database creation is done
	public BlacklistDAO(Context context) {
		dbHelper = new DatabaseHelper(context);
		open();
	}

	private void open() throws SQLException {
		
		// Opens the database connection to provide the access 
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		
		// Close it, once done
		dbHelper.close();
	}
	
	public Blacklist create(final Blacklist blackList) {
		
		// Steps to insert data into db (instead of using raw SQL query)
		// first, Create an object of ContentValues 
		final ContentValues values = new ContentValues();
		
		// second, put the key-value pair into it
		values.put("phone_number", blackList.phoneNumber);
		
		// thirst. insert the object into the database
		final long id = database.insert(DatabaseHelper.TABLE_BLACKLIST , null, values);
		
		// set the primary key to object and return back
		blackList.id = id;
		return blackList;
	}

	public void delete(final Blacklist blackList) {
		
		// Way to delete a record from database
		database.delete(DatabaseHelper.TABLE_BLACKLIST, "phone_number = '" + blackList.phoneNumber + "'", null);
	}
	
	public List<Blacklist> getAllBlacklist() {
		
		// Steps to fetch all records from a database table 
		// first, create the desired object
		final List<Blacklist> blacklistNumbers = new ArrayList<Blacklist>();
		
		// second, Query the database and set the result into Cursor
		final Cursor cursor = database.query(DatabaseHelper.TABLE_BLACKLIST, new String[]{"id","phone_number"}, null, null, null, null, null);
		
		// Move the Cursor pointer to the first
		cursor.moveToFirst();
		
		//Iterate over the cursor
	    while (!cursor.isAfterLast()) {
			final Blacklist number = new Blacklist();
			
			// Fetch the desired value from the Cursor by column index
			number.id = cursor.getLong(0);
			number.phoneNumber = cursor.getString(1);
			
			// Add the object filled with appropriate data into the list
			blacklistNumbers.add(number);
			
			// Move the Cursor pointer to next for the next record to fetch
			cursor.moveToNext();
	    }
		return blacklistNumbers;
	}
}

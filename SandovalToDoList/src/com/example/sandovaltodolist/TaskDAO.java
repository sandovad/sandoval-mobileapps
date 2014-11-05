package com.example.sandovaltodolist;

import android.database.sqlite.SQLiteDatabase;

public class TaskDAO {
	//fields 
	private SQLiteDatabase database; 
	private TasksSQLiteHelper dbHelper; 
	private String[] allColumns = {
			TasksSQLiteHelper.COLUMN_ID, 
			TasksSQLiteHelper.COLUMN_PRIORITY, 
			TasksSQLiteHelper.COLUMN_DATE, 
			TasksSQLiteHelper.COLUMN_TASK, 
			TasksSQLiteHelper.COLUMN_COMPLETED}; 
}//end class


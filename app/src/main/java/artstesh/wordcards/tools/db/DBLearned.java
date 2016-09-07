package artstesh.wordcards.tools.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBLearned
{
	public static final String WORD_COLUMN = "word";
	public static final String LEARNED_TABLE = "learned_table";
	public static String CREATE_TABLE = "create table "
			+ LEARNED_TABLE + " (" + BaseColumns._ID
			+ " integer primary key autoincrement, " + WORD_COLUMN
			+ " text unique  not null); ";


}



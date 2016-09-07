package artstesh.wordcards.tools.db;

import android.provider.BaseColumns;

public class DBLearning
{
	public static final String WORD_COLUMN = "word";
	public static final String RATING = "range";
	public static final String AVG_TIME = "avg_time";
	public static final String TRYING_COUNT = "trying_count";
	public static final String LEARNING_TABLE = "learning_table";
	public static String CREATE_TABLE = "create table "
			+ LEARNING_TABLE + " (" + BaseColumns._ID
			+ " integer primary key autoincrement, " + WORD_COLUMN
			+ " text unique not null, " + RATING + " int, " + AVG_TIME + " int, "
			+ TRYING_COUNT + " int); ";


}

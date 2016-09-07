package artstesh.wordcards.tools.db;


import android.provider.BaseColumns;

public class DBDictionary
{
	public static final String WORD_COLUMN = "word";
	public static final String TRANSCRIPTION_COLUMN = "transcription";
	public static final String TRANSLATION_COLUMN = "translation";
	public static final String DATA_FILE = "Dic.txt";
	public static final String DIC_TABLE = "dic_table";
	public static String CREATE_TABLE = "create table "
			+ DIC_TABLE + " (" + BaseColumns._ID
			+ " integer primary key autoincrement, " + WORD_COLUMN
			+ " text unique not null, "  + TRANSLATION_COLUMN + " text not null," + TRANSCRIPTION_COLUMN + " text ); ";


}

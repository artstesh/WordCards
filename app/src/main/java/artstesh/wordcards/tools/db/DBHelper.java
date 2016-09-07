package artstesh.wordcards.tools.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.io.IOException;
import java.io.InputStream;

//Base interface for SQLite
public class DBHelper extends SQLiteOpenHelper implements BaseColumns
{
	public static final String DATABASE_NAME = "dictionary";
	public static final int DATABASE_VERSION = 1;

	Context dbContext;
	private static String CREATE_TABLE;

	// конструктор суперкласса
	public DBHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		dbContext = context;
	}

	public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
	{
		super(context, name, factory, version);
		dbContext = context;
	}


	//Заполняем базу данныx


	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// создаем таблицу с полями
		System.out.println("Start tables crearing");
		db.execSQL(DBLearning.CREATE_TABLE);
		db.execSQL(DBLearned.CREATE_TABLE);
		db.execSQL(DBDictionary.CREATE_TABLE);
		fillData(db);
		System.out.println("Table created");
	}



	@Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// Удаляем старую таблицу и создаём новую
		db.execSQL("DROP TABLE IF IT EXISTS " + DBDictionary.DIC_TABLE);
		// Создаём новую таблицу
		onCreate(db);
	}

	private void fillData(SQLiteDatabase db)
	{
		InputStream input = null;
		try
		{
			input = dbContext.getAssets().open(DBDictionary.DATA_FILE);
			Dictionary.fullDictionary(input, db);
		} catch(IOException ignored) {}


	}
}





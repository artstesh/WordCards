package artstesh.wordcards.tools.db;


import android.content.ContentValues;
import android.database.Cursor;

import java.io.FileNotFoundException;

import artstesh.wordcards.MainActivity;

public class LearningFiller
{

	public static void addWords(String[] words)
	{
		try
		{
		MainActivity.database.beginTransaction();

		for(int z = 0; z < words.length; z++)
		{
			System.out.println("try to add " + words[z]);
			if(!check(words[z]))
			{
				ContentValues contentValues = new ContentValues();
				contentValues.put("word", words[z].toLowerCase());
				contentValues.put("range", 10);
				contentValues.put("avg_time", 5);
				contentValues.put("trying_count", 0);
				MainActivity.database.insert(DBLearning.LEARNING_TABLE, null, contentValues);
				if(z % 100 == 0)
				{
					MainActivity.database.setTransactionSuccessful();
					MainActivity.database.endTransaction();
					MainActivity.database.beginTransaction();
				}
			}
		}
			MainActivity.database.setTransactionSuccessful();

		} catch (Exception ignored)
		{
		}
		finally
		{
			MainActivity.database.endTransaction();
		}
	}

	public static boolean checkLearning(String word, String table)
	{
		String selection = "word=?";
		String[] selectionArgs = new String[]{word};
		Cursor cursor = MainActivity.database.query(table, null,
				selection, selectionArgs, null, null, null);
		cursor.moveToFirst();
		boolean flag = cursor.getCount() > 0;
		cursor.close();
		return flag;
	}

	public static boolean check(String word)
	{
		return checkLearning(word,DBLearned.LEARNED_TABLE) || checkLearning(word,DBLearning.LEARNING_TABLE);
	}
}

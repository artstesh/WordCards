package artstesh.wordcards.tools.db;

import android.database.Cursor;

import java.util.ArrayList;

import artstesh.wordcards.MainActivity;
import artstesh.wordcards.tools.Word;

/**
  													***
 * translating from database
 */
public class GetFromDB
{
	public static ArrayList<Word> words = new ArrayList<Word>();

	public static String[] translateWord(String word)
	{

		String query = selectWhereWord(DBDictionary.DIC_TABLE, DBDictionary.WORD_COLUMN, word);
		Cursor cursor = MainActivity.database.rawQuery(query, null);
		cursor.moveToFirst();
		String[] msd = new String[2];

		msd[0] = cursorGetText(cursor, DBDictionary.TRANSCRIPTION_COLUMN);
		msd[1] = cursorGetText(cursor, DBDictionary.TRANSLATION_COLUMN);
		msd[1] = msd[1].equalsIgnoreCase("") ? formatWord(word) : msd[1];
		cursor.close();
		return msd;
	}

	public static boolean isWordInDB(String word, String table, String column)
	{
		String query = selectWhereWord(table, column, word);
		Cursor cursor = MainActivity.database.rawQuery(query, null);
		System.out.println("Count words!!!! - " + cursor.getCount());
		return cursor.getCount()>0;
	}

	private static String cursorGetText(Cursor cursor, String columnName)
	{
		return cursor.getCount() > 0 ? cursor.getString(cursor.getColumnIndex(columnName)) : "";
	}

	private static String selectWhereWord(String table, String column, String word)
	{
		return "SELECT * FROM " + table + " WHERE " + column + " = \"" + word + "\"";
	}

	public static int countWordsIn(String table)
	{
		String query = "SELECT * FROM " + table;
		Cursor cursor = MainActivity.database.rawQuery(query, null);
		cursor.moveToFirst();
		int res = cursor.getCount();
		cursor.close();
		return res;
	}

	public static ArrayList<Word> get300words()
	{
		String query = "SELECT * FROM " + DBLearning.LEARNING_TABLE;

		Cursor cursor = MainActivity.database.rawQuery(query, null);
		cursor.moveToFirst();

		for(int i = 0; i < cursor.getCount() && i < 301; i++)
		{
			String name = cursor.getString(cursor.getColumnIndex(DBLearning.WORD_COLUMN));
			int rating = cursor.getInt(cursor.getColumnIndex(DBLearning.RATING));
			int tryings = cursor.getInt(cursor.getColumnIndex(DBLearning.TRYING_COUNT));
			String[] trans = translateWord(name);
			Word word = new Word(name, trans[0], trans[1], rating, tryings);
			words.add(word);
			System.out.println(word);
			cursor.moveToNext();
		}
		cursor.close();

		return words;
	}

	/*Отсекаем окончания, суффиксы и проч. и пытаемся перевести "голое" слово*/
	private static String formatWord(String modifWord)
	{
		int len = modifWord.length();
		String end1 = len <= 3 ? "qwerty" : modifWord.substring(len - 1, len);
		String end2 = len <= 5 ? "qwerty" : modifWord.substring(len - 2, len);
		String end3 = len <= 5 ? "qwerty" : modifWord.substring(len - 3, len);

		if (end3.equalsIgnoreCase("ies"))
		{
			modifWord =  (modifWord.substring(0, len - 3) + "y");
		}
		else if (end3.equalsIgnoreCase("ing"))
		{
			modifWord =  (modifWord.substring(0, len - 3) + "e");
		}

		else if (end2.equalsIgnoreCase("ed") || end2.equalsIgnoreCase("er") ||
					end2.substring(0, 1).equalsIgnoreCase(end2.substring(1)))
		{
			modifWord =  (modifWord.substring(0, len - 1));
		}

		else if (end2.equalsIgnoreCase("'s") || end2.equalsIgnoreCase("st") ||
					end2.equalsIgnoreCase("ly"))
		{
			modifWord =  (modifWord.substring(0, len - 2));
		}

		else if (end1.equalsIgnoreCase("s") || end1.equalsIgnoreCase("e") && len > 3)
		{
			modifWord =  (modifWord.substring(0, len - 1));
		}
		else
		{
			modifWord =  "-----";
			return modifWord;
		}
		return translateWord(modifWord)[1];
	}


}

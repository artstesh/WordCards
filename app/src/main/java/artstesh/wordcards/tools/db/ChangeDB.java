package artstesh.wordcards.tools.db;


import android.content.ContentValues;
import android.database.Cursor;
import artstesh.wordcards.MainActivity;
import artstesh.wordcards.tools.Word;

public class ChangeDB
{
	public static void changeWord(String prevName)
	{
		ContentValues contentValues = DBSupplier.makeContValues(
									DBDictionary.WORD_COLUMN, MainActivity.word.getName(),
									DBDictionary.TRANSCRIPTION_COLUMN, MainActivity.word.getTranscriprion(),
									DBDictionary.TRANSLATION_COLUMN, MainActivity.word.getTranslation());
		GetFromDB.isWordInDB(prevName,DBDictionary.DIC_TABLE,DBDictionary.WORD_COLUMN);
		//MainActivity.database.update(DBDictionary.DIC_TABLE, contentValues, DBDictionary.WORD_COLUMN + " = " + "\"" + prevName + "\"", null);
		MainActivity.database.update(DBDictionary.DIC_TABLE, contentValues, DBDictionary.WORD_COLUMN + " = ?", new String[]{prevName});
		ContentValues contentValues2 = DBSupplier.makeContValues(DBLearning.WORD_COLUMN, MainActivity.word.getName());
		MainActivity.database.update(DBLearning.LEARNING_TABLE, contentValues2, DBLearning.WORD_COLUMN + " = " + "\"" + prevName + "\"", null);
	}

	public static void addWord(Word word)
	{
		if(!GetFromDB.isWordInDB(word.getName(), DBDictionary.DIC_TABLE, DBDictionary.WORD_COLUMN))
		{
			ContentValues contentValues = DBSupplier.makeContValues(
													DBDictionary.WORD_COLUMN, word.getName(),
													DBDictionary.TRANSCRIPTION_COLUMN, word.getTranscriprion(),
													DBDictionary.TRANSLATION_COLUMN, word.getTranslation());
			MainActivity.database.insert(DBDictionary.DIC_TABLE, null, contentValues);
		}

		if(!GetFromDB.isWordInDB(word.getName(), DBLearning.LEARNING_TABLE, DBLearning.WORD_COLUMN))
		{
			ContentValues contentValues = DBSupplier.makeContValues(
													DBLearning.WORD_COLUMN, word.getName(),
													DBLearning.RATING, 10,
													DBLearning.AVG_TIME, 5,
													DBLearning.TRYING_COUNT, 0);
			MainActivity.database.insert(DBLearning.LEARNING_TABLE, null, contentValues);
		}
	}

	public static void addLearnedWord(Word word)
	{
		if(GetFromDB.isWordInDB(word.getName(), DBDictionary.DIC_TABLE, DBDictionary.WORD_COLUMN))
		{
			deleteWord(DBDictionary.DIC_TABLE, DBDictionary.WORD_COLUMN, word);
		}
		if(GetFromDB.isWordInDB(word.getName(), DBLearning.LEARNING_TABLE, DBLearning.WORD_COLUMN))
		{
			deleteWord(DBLearning.LEARNING_TABLE, DBLearning.WORD_COLUMN, word);
		}
		ContentValues contentValues = DBSupplier.makeContValues(DBLearned.WORD_COLUMN, word.getName());
		MainActivity.database.insert(DBLearned.LEARNED_TABLE, null, contentValues);
	}

	public static void setRating(Word word, int rating)
	{
		ContentValues contentValues = DBSupplier.makeContValues(
												DBLearning.RATING, rating,
												DBLearning.TRYING_COUNT, 0);
		MainActivity.database.update(DBLearning.LEARNING_TABLE, contentValues, DBDictionary.WORD_COLUMN + " = ?", new String[] {word.getName()});
	}

	public static void setTryings(Word word, int tryings)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(DBLearning.TRYING_COUNT, tryings);

		MainActivity.database.update(DBLearning.LEARNING_TABLE, contentValues, DBDictionary.WORD_COLUMN + " = ?", new String[]{word.getName()});
	}

	public static void deleteWord(String table, String column, Word word)
	{
		int flag = MainActivity.database.delete(table, column + " = ?", new String[]{word.getName()});
		System.out.println(flag + " delete !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
}

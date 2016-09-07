package artstesh.wordcards.tools;


import java.util.ArrayList;
import java.util.Collections;

import artstesh.wordcards.tools.db.ChangeDB;
import artstesh.wordcards.tools.db.DBDictionary;
import artstesh.wordcards.tools.db.DBLearning;
import artstesh.wordcards.tools.db.GetFromDB;

public class RandomWord
{
	ArrayList<Word> words300 = new ArrayList<Word>();

	public RandomWord()
	{
		words300 = GetFromDB.get300words();
	}


	public Word getRandomWord()
	{
		//Collections.shuffle(words300);
		if(words300.size()<1) {return new Word();}
		int rand = (int) (Math.random()*words300.size());
		int stoper = 10;
		int tryings = 0;
		int randomRating = (int) (Math.random()*stoper);
		while((words300.get(rand).getRating()) < randomRating)
		{
			if(isLearnedWord(words300.get(rand))) {words300.remove(rand);}
			tryings++;
			if(tryings>10) {stoper--;}
			rand = (int) (Math.random()*words300.size());
			randomRating = (int) (Math.random()*stoper);
		}

		return words300.get(rand);
	}

	private boolean isLearnedWord(Word word)
	{
		if(word.getRating() < 1)
		{
			ChangeDB.addLearnedWord(word);
			return true;
		}
		return false;
	}

	public void deleteWord(Word word)
	{
		System.out.println("Words300 size = " + words300.size());
		if(words300.size() > 30 && words300.contains(word))
		{
			words300.remove(word);
			System.out.println("Words300 size = " + words300.size());
		}
	}

	public void addWord300(Word word)
	{
		words300.add(word);
	}

	public void delWordFromDB(Word word)
	{
		ChangeDB.deleteWord(DBDictionary.DIC_TABLE, DBDictionary.WORD_COLUMN, word);
		ChangeDB.deleteWord(DBLearning.LEARNING_TABLE, DBLearning.WORD_COLUMN, word);
		words300.remove(word);
	}
}

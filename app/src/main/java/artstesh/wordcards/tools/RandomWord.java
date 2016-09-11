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
	WordsFactory factory = WordsFactory.getInstance();

	public RandomWord()
	{
		words300 = GetFromDB.get300words();
	}


	public Word getRandomWord()
	{
		if(words300.size()<1) {return new Word();}
		int stoper = 10, tryings = 0;
		factory.setWord(words300.get(getRandInt(words300.size())));
		while(factory.getWord().getRating() < getRandInt(stoper))
		{
			if(isLearnedWord(factory.getWord())) {words300.remove(factory.getWord());}
			tryings++;
			if(tryings>10) {stoper--;}
			factory.setWord(words300.get(getRandInt(words300.size())));
		}
		return factory.getWord();
	}

	private int getRandInt(int num)
	{
		long str = System.currentTimeMillis();
		int rest  = (int) (Math.random()*System.currentTimeMillis()%num);
		System.out.println(System.currentTimeMillis() - str);
		return rest;
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

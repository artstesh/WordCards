package artstesh.wordcards.tools;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by artstesh on 04.09.2016.
 */
public class RandomWordTest
{
	ArrayList<Word> words300 = new ArrayList<Word>();
	WordsFactory factory = WordsFactory.getInstance();
	int randomInt;

	@Test
	public void testRefresh30() throws Exception
	{

	}

	@Test
	public void testGetWords() throws Exception
	{

	}

	@Test
	public void testGetRandomWord() throws Exception
	{
		testAddWord300();
		int stoper = 10, tryings = 0;
		factory.setWord(words300.get(getRandInt(words300.size())));
		while(factory.getWord().getRating() < getRandInt(stoper))
		{
			tryings++;
			if(tryings>10) {stoper--;}
			factory.setWord(words300.get(getRandInt(words300.size())));
		}
		System.out.println(factory.getWord());
	}


	public int getRandInt(int num)
	{
		long str = System.currentTimeMillis();
		int rest  = (int) (Math.random()*System.currentTimeMillis()%num);
		System.out.println(System.currentTimeMillis() - str);
		return rest;
	}

	@Test
	public void testDeleteWord() throws Exception
	{

	}

	@Test
	public void testAddWord300() throws Exception
	{
		words300.add(new Word("december","","decabr"));
		words300.add(new Word("december2","","decabr2"));
		words300.add(new Word("december3","","decabr3"));
		words300.add(new Word("december4","","decabr4"));
		words300.add(new Word("december5","","decabr5"));
		words300.add(new Word("december6","","decabr6"));
		words300.add(new Word("december7","","decabr7"));
		words300.add(new Word("december8","","decabr8"));
		words300.add(new Word("december9","","decabr9"));
		words300.add(new Word("december10","","decabr10"));
		words300.add(new Word("december11","","decabr11"));
		words300.add(new Word("december12","","decabr12"));
		words300.add(new Word("december13","","decabr13"));
		words300.add(new Word("december14","","decabr14"));
		words300.add(new Word("december15","","decabr15"));
		words300.add(new Word("december16","","decabr16"));
		words300.add(new Word("december17","","decabr17"));
		words300.add(new Word("december18","","decabr18"));
		words300.add(new Word("december19","","decabr19"));
	}
}
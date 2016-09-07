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
		int rand = (int) (Math.random()*words300.size());
		int stoper = 10;
		int tryings = 0;
		int randomRating = (int) (Math.random()*stoper);
		while((words300.get(rand).getRating()) < randomRating)
		{
			tryings++;
			if(tryings>10) {stoper--;}
			rand = (int) (Math.random()*words300.size());
			randomRating = (int) (Math.random()*stoper);
		}
		System.out.println(words300.get(rand).toString());
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
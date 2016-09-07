package artstesh.wordcards.tools;


import java.util.HashMap;
import java.util.TreeMap;

import artstesh.wordcards.tools.db.LearningFiller;

public class TextParter
{
	private static TextParter textParter;
	private String text = "";
	private TreeMap<String, Integer> wordsCount = new TreeMap<String, Integer>();

	public static TextParter getTextParter(String text)
	{
		textParter = new TextParter(text);
		return textParter;
	}

	public static TextParter getTextParter()
	{
		return textParter;
	}

	public TextParter(String text)
	{
		this.text = text;
		wordsCount.clear();
		countWords();
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public TreeMap<String, Integer> getWordsCount()
	{
		return wordsCount;
	}

	public void setWordsCount(TreeMap<String, Integer> wordsCount)
	{
		this.wordsCount = wordsCount;
	}

	private void countWords()
	{
		text = text.replaceAll("\\n", " ");
		text = text.replaceAll("[.,?!:]+",""); //destroy all thash at the end of a sentence
		String[] mass = text.split(" ");
		for(String word : mass)
		{
			word = word.toLowerCase();
			if(!word.matches(".*\\d+.*") && word.matches("\\w.*\\w") && !LearningFiller.check(word)) //no digits, begin and ands with a letter
			{
				if(wordsCount.containsKey(word))
				{
					int temp = wordsCount.get(word) + 1;
					wordsCount.put(word, temp);
				}
				else
				{
					wordsCount.put(word, 1);
				}
			}
		}
	}
}

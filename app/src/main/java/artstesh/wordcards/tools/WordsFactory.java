package artstesh.wordcards.tools;

/**
 * Created by artstesh on 30.08.2016.
 */
public class WordsFactory
{
	private Word word;

	public Word getWord()
	{
		if(word == null) {word =  new Word();}
		return word;
	}

	public Word getWord(String name, String transcriprion, String translation)
	{
		word = new Word(name, transcriprion, translation);
		return word;
	}

	public Word getWord(String name, String transcriprion, String translation, int rating, int tryings)
	{
		word =  new Word(name, transcriprion, translation, rating, tryings);
		return word;
	}


}

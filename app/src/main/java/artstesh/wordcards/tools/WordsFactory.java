package artstesh.wordcards.tools;

/**
 * Created by artstesh on 30.08.2016.
 */
public class WordsFactory
{
	private Word word;

	public Word getWord()
	{
		return word;
	}

	public void setWord(Word word)
	{
		this.word = word;
	}

	public Word getNewWord()
	{
		if(word == null) {word =  new Word();}
		return word;
	}

	public Word getNewWord(String name, String transcriprion, String translation)
	{
		word = new Word(name, transcriprion, translation);
		return word;
	}

	public Word getNewWord(String name, String transcriprion, String translation, int rating, int tryings)
	{
		word =  new Word(name, transcriprion, translation, rating, tryings);
		return word;
	}


}

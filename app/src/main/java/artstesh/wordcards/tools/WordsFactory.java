package artstesh.wordcards.tools;


public class WordsFactory
{
	private Word word;
	private static WordsFactory factory;

	private WordsFactory(Word word)
	{
		this.word = word;
	}

	private WordsFactory()
	{
		word = null;
	}

	public static WordsFactory getInstance()
	{
		if(factory == null)
		{
			factory = new WordsFactory();
		}
		return factory;
	}

	public static WordsFactory getInstance(Word word)
	{
		if(factory == null)
		{
			factory = new WordsFactory(word);
		}
		factory.setWord(word);
		return factory;
	}

	public Word getWord()
	{
		if(word == null) {word =  new Word();}
		return word;
	}

	public void setWord(Word word)
	{
		this.word = word;
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

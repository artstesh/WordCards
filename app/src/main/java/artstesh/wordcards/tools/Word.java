package artstesh.wordcards.tools;


public class Word
{
	private String name;
	private String transcriprion;
	private String translation;
	int rating = 10;
	int tryings = 0;

	public String toString()
	{
		return name + " " + transcriprion + " " + translation + " rating - " + rating +
				" tryings - " + tryings;
	}

	public Word()
	{
		this.name = "";
		this.transcriprion = "";
		this.translation = "";
	}

	public Word(String name, String transcriprion, String translation)
	{
		this.name = name;
		this.transcriprion = transcriprion;
		this.translation = translation;
	}

	public Word(String name, String transcriprion, String translation, int rating, int tryings)
	{
		this.name = name;
		this.transcriprion = transcriprion;
		this.translation = translation;
		this.rating = rating;
		this.tryings = tryings;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTranscriprion()
	{
		return transcriprion;
	}

	public void setTranscriprion(String transcriprion)
	{
		this.transcriprion = transcriprion;
	}

	public String getTranslation()
	{
		return translation;
	}

	public void setTranslation(String translation)
	{
		this.translation = translation;
	}

	public int getRating()
	{
		return rating;
	}

	public void setRating(int rating)
	{
		this.rating = rating;
	}

	public int getTryings()
	{
		return tryings;
	}

	public void setTryings(int tryings)
	{
		this.tryings = tryings;
	}
}

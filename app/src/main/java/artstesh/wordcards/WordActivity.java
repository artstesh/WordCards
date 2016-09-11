package artstesh.wordcards;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;

import artstesh.wordcards.tools.WordsFactory;


public class WordActivity extends ActionBarActivity
{
	EditText etWord;
	WordsFactory factory = WordsFactory.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newword);
		etWord = (EditText) findViewById(R.id.etWord);
		etWord.setText(factory.getWord().getName());
		MainActivity.layoutCount = 0;
		System.out.println(factory.getWord().getName());
	}
}

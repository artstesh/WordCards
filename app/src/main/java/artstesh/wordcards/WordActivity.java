package artstesh.wordcards;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;


public class WordActivity extends ActionBarActivity
{
	EditText etWord;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newword);
		etWord = (EditText) findViewById(R.id.etWord);
		etWord.setText(MainActivity.word.getName());
		MainActivity.layoutCount = 0;
		System.out.println(MainActivity.word.getName());
	}
}

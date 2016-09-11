package artstesh.wordcards;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import artstesh.wordcards.tools.Word;
import artstesh.wordcards.tools.WordsFactory;
import artstesh.wordcards.tools.db.ChangeDB;

public class ChangeWordActivity extends ActionBarActivity implements OnClickListener
{

	EditText etWord;
	EditText etTransc;
	EditText etTransl;
	Button btnOK;
	Button btnDelete;
	Button btnIknow;
	WordsFactory factory;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_word);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		etWord = (EditText) findViewById(R.id.etNewWord);
		etTransc = (EditText) findViewById(R.id.etNewTranscript);
		etTransl = (EditText) findViewById(R.id.etNewTransl);
		btnOK = (Button) findViewById(R.id.btnOK);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnIknow = (Button) findViewById(R.id.btnIknow);

		btnOK.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		btnIknow.setOnClickListener(this);

		factory = WordsFactory.getInstance();
		etWord.setText(factory.getWord().getName());
		etTransc.setText(factory.getWord().getTranscriprion());
		etTransl.setText(factory.getWord().getTranslation());

	}

	@Override
	 public void onClick(View v)
	{
		if(v.getId() == R.id.btnOK)
		{
			String prevName = factory.getWord().getName();
			factory.getWord().setName(String.valueOf(etWord.getText()));
			factory.getWord().setTranscriprion(String.valueOf(etTransc.getText()));
			factory.getWord().setTranslation(String.valueOf(etTransl.getText()));
			System.out.println("!!! - " + factory.getWord().toString());
			ChangeDB.changeWord(prevName);
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.btnDelete)
		{
			MainActivity.randomWord.delWordFromDB(factory.getWord());
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.btnIknow)
		{
			ChangeDB.addLearnedWord(factory.getWord());
			MainActivity.randomWord.deleteWord(factory.getWord());
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}
}

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
import artstesh.wordcards.tools.db.ChangeDB;

public class ChangeWordActivity extends ActionBarActivity implements OnClickListener
{

	EditText etWord;
	EditText etTransc;
	EditText etTransl;
	Button btnOK;
	Button btnDelete;


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

		btnOK.setOnClickListener(this);
		btnDelete.setOnClickListener(this);

		etWord.setText(MainActivity.word.getName());
		etTransc.setText(MainActivity.word.getTranscriprion());
		etTransl.setText(MainActivity.word.getTranslation());

	}

	@Override
	 public void onClick(View v)
	{
		if(v.getId() == R.id.btnOK)
		{
			String prevName = MainActivity.word.getName();
			MainActivity.word.setName(String.valueOf(etWord.getText()));
			MainActivity.word.setTranscriprion(String.valueOf(etTransc.getText()));
			MainActivity.word.setTranslation(String.valueOf(etTransl.getText()));
			System.out.println("!!! - " + MainActivity.word.toString());
			ChangeDB.changeWord(prevName);
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.btnDelete)
		{
			MainActivity.randomWord.delWordFromDB(MainActivity.word);
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}
}

package artstesh.wordcards;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import artstesh.wordcards.tools.Word;
import artstesh.wordcards.tools.db.ChangeDB;

public class NewWord extends ActionBarActivity implements View.OnClickListener
{

	EditText etWord;
	EditText etTransc;
	EditText etTransl;
	Button btnOK;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newword);

		etWord = (EditText) findViewById(R.id.etNewWord);
		etTransc = (EditText) findViewById(R.id.etNewTranscript);
		etTransl = (EditText) findViewById(R.id.etNewTransl);
		btnOK = (Button) findViewById(R.id.btnOK);
		btnOK.setOnClickListener(this);

		Intent intent = getIntent();

		if(intent.hasExtra("word"))
		{
			etWord.setText(intent.getStringExtra("word"));
		}
	}

	@Override
	public void onClick(View v)
	{
		if(v.getId() == R.id.btnOK)
		{
			System.out.println(etWord.getText() + " " + etTransc.getText() + " " + etTransl.getText());
			ChangeDB.addWord(new Word(String.valueOf(etWord.getText()), String.valueOf(etTransc.getText()), String.valueOf(etTransl.getText())));
			MainActivity.randomWord.addWord300(new Word(String.valueOf(etWord.getText()), String.valueOf(etTransc.getText()), String.valueOf(etTransl.getText())));
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}
}

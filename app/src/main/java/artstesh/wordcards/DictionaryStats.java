package artstesh.wordcards;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import artstesh.wordcards.tools.db.DBLearned;
import artstesh.wordcards.tools.db.DBLearning;
import artstesh.wordcards.tools.db.GetFromDB;

public class DictionaryStats extends ActionBarActivity
{
	TextView tvLearning;
	TextView tvLearned;
	EditText editText;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dictionary_stats);

		tvLearned = (TextView) findViewById(R.id.tvLearned);
		tvLearning = (TextView) findViewById(R.id.tvLearning);

		int temp = GetFromDB.countWordsIn(DBLearning.LEARNING_TABLE);
		tvLearning.setText(String.valueOf(temp));

		temp = GetFromDB.countWordsIn(DBLearned.LEARNED_TABLE);
		tvLearned.setText(String.valueOf(temp));
	}
}

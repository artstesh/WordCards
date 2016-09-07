package artstesh.wordcards;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import artstesh.wordcards.tools.Sorter;
import artstesh.wordcards.tools.TextParter;
import artstesh.wordcards.tools.db.LearningFiller;

public class TextStats extends ActionBarActivity implements View.OnClickListener
{
	ListView statsList;
	Button btnOK;
	TextParter textParter;
	String[] mass;
	TreeMap<String, Integer> stats = new TreeMap<String, Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_stats);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		statsList = (ListView) findViewById(R.id.statsList);
		btnOK = (Button) findViewById(R.id.btnOK);
		btnOK.setOnClickListener(this);

		textParter = TextParter.getTextParter();
		stats = textParter.getWordsCount();

		mass = new String[stats.size()];
		Integer[] nums = new Integer[stats.size()];
		int i = 0;
		for(Map.Entry<String, Integer> map : stats.entrySet())
		{
				mass[i] = map.getKey();
				nums[i++] = map.getValue();
		}


		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
								android.R.layout.simple_list_item_1, Sorter.sort(nums,mass));
		statsList.setAdapter(adapter);
	}

	@Override
	public void onClick(View v)
	{
		if(v.getId() == R.id.btnOK)
		{
			LearningFiller.addWords(mass);
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}
}

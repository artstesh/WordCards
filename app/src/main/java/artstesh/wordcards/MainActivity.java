package artstesh.wordcards;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import artstesh.wordcards.tools.RandomWord;
import artstesh.wordcards.tools.WordsFactory;
import artstesh.wordcards.tools.db.ChangeDB;
import artstesh.wordcards.tools.db.DBHelper;

public class MainActivity extends ActionBarActivity implements View.OnTouchListener, View.OnClickListener
{
	public static int layoutCount = 0;
	ViewFlipper flipper;
	public static DBHelper dbHelper;
	public static SQLiteDatabase database;
	public static ContentValues cv;
	public static RandomWord randomWord;
	public static WordsFactory factory = WordsFactory.getInstance();
	static float fromPosition;
	Button btnChange;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		System.out.println("LAYOUT WAS CREATED");
		System.out.println("factory1 " + factory.getWord());
		if(factory.getWord().getName().length()< 1 && !getIntent().hasExtra("name"))
		{
			dbHelper = new DBHelper(this, "dictionary", null, 1);
			database = dbHelper.getWritableDatabase();
			cv = new ContentValues();
			randomWord = new RandomWord();
		}
		else if(getIntent().hasExtra("name"))
		{
			System.out.println("Intent has EXTRA~!!!!!!!!!!!!!!");
			randomWord = new RandomWord();
		}

		factory = WordsFactory.getInstance(randomWord.getRandomWord());
		// Устанавливаем listener касаний, для последующего перехвата жестов
		LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
		mainLayout.setOnTouchListener(this);

		// Получаем объект ViewFlipper
		flipper = (ViewFlipper) findViewById(R.id.flipper);

		// Создаем View и добавляем их в уже готовый flipper
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		int layouts[] = new int[]{R.layout.activity_word,R.layout.activity_translate/*,R.layout.activity_nextword*/};
		for(int layout : layouts)
		{
			flipper.addView(inflater.inflate(layout,null));
		}
		EditText name = (EditText) flipper.getChildAt(0).findViewById(R.id.etWord);
		System.out.println("factory1.5 " + factory.getWord());


		System.out.println("factory2 " + factory.getWord());

		btnChange = (Button) flipper.getChildAt(0).findViewById(R.id.btnChange);
		btnChange.setOnClickListener(this);
		System.out.println("factory3 " + factory.getWord());
		name.setText(factory.getWord().getName());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if(id == R.id.action_settings)
		{
			Intent intent = new Intent(this, NewWord.class);
			startActivity(intent);
			return true;
		}
		if(id == R.id.add_text)
		{
			Intent intent = new Intent(this, AddText.class);
			startActivity(intent);
			return true;
		}
		if(id == R.id.learn_stats)
		{
			Intent intent = new Intent(this, DictionaryStats.class);
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{

		switch (event.getAction())
		{
			case MotionEvent.ACTION_DOWN: // Пользователь нажал на экран, т.е. начало движения
				// fromPosition - координата по оси X начала выполнения операции
				fromPosition = event.getX();
				break;
			case MotionEvent.ACTION_UP: // Пользователь отпустил экран, т.е. окончание движения
				float toPosition = event.getX();


				if (fromPosition > toPosition && (fromPosition - toPosition) > 10)
				{
					if(layoutCount == 0)
					{
						factory.getWord().setTryings(0);
						ChangeDB.setTryings(factory.getWord(),0);
						System.out.println("Move LEFT" + factory.getWord().toString());
						EditText name = (EditText) flipper.getChildAt(1).findViewById(R.id.etTranslate);
						name.setText(factory.getWord().getTranslation());
						layoutCount = 1;
						flipper.showNext();
					}
					else if(layoutCount == 1)
					{
						factory.setWord(randomWord.getRandomWord());
						layoutCount = 0;
						EditText name = (EditText) flipper.getChildAt(0).findViewById(R.id.etWord);
						System.out.println("Move LEFT" + factory.getWord().getName());
						name.setText(factory.getWord().getName());
						EditText transcription = (EditText) flipper.getChildAt(0).findViewById(R.id.etTranscriprion);
						transcription.setText(factory.getWord().getTranscriprion());

						flipper.showNext();
						//flipper.showNext();
					}
				}
				else if (fromPosition < toPosition  && (fromPosition - toPosition) < -10)
				{
					if(layoutCount == 1)
					{
						layoutCount = 0;
						System.out.println("previous");
						flipper.showPrevious();
					}
					else
					{
						factory.getWord().setTryings(factory.getWord().getTryings() + 1);
						ChangeDB.setTryings(factory.getWord(), factory.getWord().getTryings());
						if(factory.getWord().getTryings() > 4)
						{
							factory.getWord().setTryings(0);
							factory.getWord().setRating(factory.getWord().getRating() - 1);
							ChangeDB.setRating(factory.getWord(), factory.getWord().getRating());
							randomWord.deleteWord(factory.getWord());
						}
						System.out.println("Move RIGHT" + factory.getWord().toString());
						factory.setWord(randomWord.getRandomWord());
						EditText name = (EditText) flipper.getChildAt(0).findViewById(R.id.etWord);
						name.setText(factory.getWord().getName());
						layoutCount = 0;
						flipper.showNext();
						flipper.showNext();

					}
				}
			default:
				break;
		}

		return true;
	}

	@Override
	public void onClick(View v)
	{
		Intent intent;
		if(factory.getWord().getTranslation().equalsIgnoreCase("-----"))
		{
			intent = new Intent(this, NewWord.class);
			intent.putExtra("word",factory.getWord().getName());
		}
		else
		{
			intent = new Intent(this, ChangeWordActivity.class);
		}
		startActivity(intent);
	}
}

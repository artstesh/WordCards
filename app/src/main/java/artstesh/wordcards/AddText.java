package artstesh.wordcards;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import artstesh.wordcards.tools.TextParter;

public class AddText extends ActionBarActivity implements View.OnClickListener
{

	Button btnOK;
	EditText etAddText;
	TextParter textParter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_text);
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		btnOK = (Button) findViewById(R.id.btnOK);
		etAddText = (EditText) findViewById(R.id.etAddText);
		btnOK.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		if(v.getId() == R.id.btnOK)
		{
			String text = String.valueOf(etAddText.getText());
			if(text.length() > 0)
			{
				textParter = TextParter.getTextParter(text);
				Intent intent = new Intent(this, TextStats.class);
				intent.putExtra("name", "vasya");
				startActivity(intent);
			}
		}
	}
}

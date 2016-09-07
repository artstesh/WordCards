package artstesh.wordcards.tools.db;

import android.content.ContentValues;

import artstesh.wordcards.MainActivity;

/**
 * Created by artstesh on 02.09.2016.
 */
public class DBSupplier
{
	public static ContentValues makeContValues(Object... args)
	{
		ContentValues contentValues = new ContentValues();
		for(int i = 0; i < args.length; i+=2)
		{
			contentValues.put(args[i].toString(), args[i+1].toString());
		}
		return contentValues;
	}
}

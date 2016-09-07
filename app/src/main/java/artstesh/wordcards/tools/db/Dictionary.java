package artstesh.wordcards.tools.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import artstesh.wordcards.MainActivity;


//Filling database
public class Dictionary
{
    static boolean flagger = true;

    public static void fullDictionary(InputStream is, SQLiteDatabase database)
    {
        long startTime = System.currentTimeMillis();
        System.out.println("Start filling database");

        while (flagger)
        {
            try
            {
                Cursor cursor = database.query(DBDictionary.DIC_TABLE, null, null, null, null, null, null);
                System.out.println(cursor);


                if(cursor.getCount() < 10)
                {

                    int size = is.available();
                    System.out.println(size);
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();

                    String str_data = new String(buffer);

                    String[] temp = str_data.split(" ");

                    database.beginTransaction();

                    for(int z = 0; z < temp.length; z += 2)
                    {

                        ContentValues contentValues = new ContentValues();
                        contentValues.put(DBDictionary.WORD_COLUMN, temp[z].toLowerCase());
                        contentValues.put(DBDictionary.TRANSCRIPTION_COLUMN, "");
                        contentValues.put(DBDictionary.TRANSLATION_COLUMN, temp[z + 1].toLowerCase());
                        database.insert(DBDictionary.DIC_TABLE, null, contentValues);
                        if(z % 1000 == 0)
                        {
                            database.setTransactionSuccessful();
                            database.endTransaction();
                            database.beginTransaction();
                        }
                    }
                    database.setTransactionSuccessful();
                    flagger = false;
                }
                cursor.close();
            } catch (FileNotFoundException e)
            {
                break;
            } catch (Exception ignored)
            {
            }
            finally
            {
                database.endTransaction();
            }
        }

        long time = System.currentTimeMillis() - startTime;
        System.out.println("!!!!!!!!!!!!!!!!!!!" + time);
    }
}

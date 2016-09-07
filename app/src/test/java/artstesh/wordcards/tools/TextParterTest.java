package artstesh.wordcards.tools;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;


public class TextParterTest
{
	@Test
	public void partText()
	{
		String text = "We’d like to remind you that the second writing assignment is due this week on August 29 at 23:00 UTC, so you need to adjust this due date to your personal timezone. You should post your assignment to the correct link, which is located in the 'Writing Assignments' tab, not on the discussion board.\n" +
				"\n" +
				"Remember, there are three -basic steps to the assessment: submitting a blog post, peer-reviewing five blog posts, and completing your self-review. You must complete all three steps to see feedback and receive a mark. The timer on the Home Page changes to reflect the three due dates of the three steps. You can still also post your work to the thread 'Sharing blogs' on the discussion board for more feedback.";
		TextParter parter = new TextParter(text);
		TreeMap<String,Integer> hashMap = parter.getWordsCount();

		for(Map.Entry<String,Integer> map : hashMap.entrySet())
		{
			System.out.println(map.getKey() + " " + map.getValue());
		}
	}
}
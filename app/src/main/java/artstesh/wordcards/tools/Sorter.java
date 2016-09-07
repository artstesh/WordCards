package artstesh.wordcards.tools;


public class Sorter
{
	public static String[] sort(Integer[] nums, String[] mass)
	{
		for(int i = nums.length-1; i >= 0; i--)
		{
			for(int c = 1; c < nums.length; c++)
			{
				if(nums[c] > nums[c-1])
				{
					int temp = nums[c];
					String string = mass[c];
					nums[c] = nums[c-1];
					mass[c] = mass[c-1];
					nums[c-1] = temp;
					mass[c-1] = string;
				}
			}
		}
		String[] temp = new String[mass.length];
		for(int i = 0; i < mass.length; i++)
		{
			temp[i] = mass[i] + " - " + nums[i];
		}
		return temp;
	}
}

package common;

public class Utils {

	public static boolean isNumber(String string)
	{
		for(char c : string.toCharArray())
		{
			if(!Character.isDigit(c))
			{
				return false;
			}
		}
		return true;
	}
}

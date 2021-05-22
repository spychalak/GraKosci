package kosci.si;

public class PointsCalculator {

	public PointsCalculator ()
	{
	}
	
	public static double AveragePointsOfDstCategory(Category cat, int[] dices,
			Integer[] points) {
		boolean isGeneralBonus = dices.length == 5 &&
			points[Category.GENERAL.getRowIndex()] > 0;
		for(int i=1; i<5; ++i)
			isGeneralBonus &= (dices[i] == dices[0]);
		switch(cat) {
		case FULL_HOUSE:
			return (isGeneralBonus?100.0:0.0) + 25.0;
		case SMALL_STRAIGHT:
			return (isGeneralBonus?100.0:0.0) + 30.0;
		case BIG_STRAIGHT:
			return (isGeneralBonus?100.0:0.0) + 40.0;
		case GENERAL:
			return 50.0;
		case CHANCE:
			return (isGeneralBonus?100.0:0.0) + 5.0 * 3.5;
		case THREE_OF_A_KIND:
		case FOUR_OF_A_KIND: {
			double p = (double)(dices.length * dices[0]) +
				(double)(5-dices.length) * 3.5;
	
			if(isGeneralBonus)
				p += 100.0;
			return p;
			}
		default: {
			double p = (double)(dices.length * dices[0]);
			if(isGeneralBonus && dices[0]-1 == cat.getRowIndex())
				p += 100.0;
			return p;
			}
		}
	}

	public static int getPoints(int[] currentDices, Category cat) {

		if (cat == Category.THREE_OF_A_KIND)
		{
			return checkXOfKind(currentDices,3);
		}
		else if (cat == Category.FOUR_OF_A_KIND)
		{
			return checkXOfKind(currentDices,4);
		}
		else if (cat == Category.FULL_HOUSE)
		{
			return checkFullHouse(currentDices);
		}
		else if (cat == Category.SMALL_STRAIGHT)
		{
			return checkStraight(currentDices,4);
		}
		else if (cat == Category.BIG_STRAIGHT)
		{
			return checkStraight(currentDices,5);
		}
		else if (cat == Category.GENERAL)
		{
			return checkGeneral(currentDices);
		}
		else if (cat == Category.CHANCE)
		{
			return checkChance(currentDices);
		}

		return countValues(currentDices, cat.getRowIndex()+1);

	}

	private static int checkStraight(int[] currentDices, int straightLength) {

		int[] vals = {0,0,0,0,0,0};

		for (int i = 0; i < currentDices.length; i++)
		{
			vals[currentDices[i]-1]++;
		}

		int str = 0;

		for (int i=0; i<vals.length;i++) {
			if(vals[i] != 0)
			{
				str++;
			}
			else
				str = 0;

			if (str == straightLength)
			{
				break;
			}
		}

		if (str == 5 && straightLength == 5) return 40;
		else if (str == 4 && straightLength == 4) return 30;

		return 0;

	}

	private static int checkFullHouse(int[] currentDices) {

		int[] vals = {0,0,0,0,0,0};

		for (int i = 0; i < currentDices.length; i++)
		{
			vals[currentDices[i]-1]++;
		}

		for (int i=0; i<vals.length;i++)
		{
			if (vals[i] == 3)
			{
				for (int j=0; j<vals.length;j++)
				{
					if (vals[j] == 2)
					{
						return 25;
					}
				}
			}
		}
		return 0;
	}

	private static int checkXOfKind(int[] currentDices, int X) {
		int[] vals = {0,0,0,0,0,0};

		int result = 0;
		for (int i = 0; i < currentDices.length; i++)
		{
			result += currentDices[i];
			vals[currentDices[i]-1]++;
		}

		for (int i=0; i<vals.length;i++)
		{
			if (vals[i] >= X)
			{
				return result;
			}
		}
		return 0;
	}

	private static int checkGeneral(int[] currentDices) {
		int to_check = currentDices[0];

		for (int i = 1; i < currentDices.length; i++) {
			if (currentDices[i] != to_check)
			{
				return 0;
			}
		}
		return 50;
	}

	private static int countValues(int[] currentDices, int val) {
		int result = 0;
		for (int i = 0; i < currentDices.length; i++)
		{
			if (currentDices[i] == val)
			{
				result += currentDices[i];
			}
		}
		return result;
	}

	private static int checkChance(int[] currentDices) {
		int result = 0;
		for (int i = 0; i < currentDices.length; i++)
		{
			result += currentDices[i];
		}
		return result;
	}

}

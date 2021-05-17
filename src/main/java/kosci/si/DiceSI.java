package kosci.si;

public interface DiceSI {
	boolean[] Reroll(int[] dices, boolean[] availableCategories);
	Category ChooseCategory(int[] dices, boolean[] availableCategories);
}

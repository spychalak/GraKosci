package kosci.si;

public interface DiceSI {
	boolean[] Reroll(int[] dices, Integer[] categories);
	Category ChooseCategory(int[] dices, Integer[] categories);
	void Restart();
}

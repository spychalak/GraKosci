package kosci.si;

import java.util.Random;

public class RandomStrategy implements DiceSI {
	private Random random = new Random();
	private boolean[] rerolls = new boolean[5];

	public RandomStrategy() {
	}

	@Override public boolean[] Reroll(int[] dices,
		boolean[] availableCategories) {
		for(int i=0; i<5; ++i)
			rerolls[i] = random.nextBoolean();
		return rerolls;
	}

	@Override public Category ChooseCategory(int[] dices,
		boolean[] availableCategories) {
		int availableCategoriesCount = 0;
		for(boolean b : availableCategories) {
			if(!b)
				++availableCategoriesCount;
		}
		int r = random.nextInt(availableCategoriesCount);
		Category category = null;
		for(int i=0; i<availableCategories.length; ++i) {
			if(!availableCategories[i]) {
				if(r == 0)
					category = Category.getByRowIndex(i);
				--r;
			}
		}
		if(category == null)
			throw new ArithmeticException("Random bound returned bad value");

		return category;
	}

	@Override public void Restart() {

	}
}

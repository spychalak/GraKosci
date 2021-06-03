package kosci.si;

import java.util.Random;

public class RandomStrategy implements DiceSI {
    private Random random = new Random();
    private boolean[] rerolls = new boolean[5];

    public RandomStrategy() {
    }

    @Override
    public boolean[] Reroll(int[] dices, Integer[] categories) {
        for (int i = 0; i < 5; ++i)
            rerolls[i] = random.nextBoolean();
        return rerolls;
    }

    @Override
    public Category ChooseCategory(int[] dices,
                                   Integer[] categories) {
        int availableCategoriesCount = 0;
        for (Integer b : categories) {
            if (b == null)
                ++availableCategoriesCount;
        }
        int r = random.nextInt(availableCategoriesCount);
        Category category = null;
        for (int i = 0; i < categories.length; ++i) {
            if (categories[i] == null) {
                if (r == 0)
                    category = Category.getByRowIndex(i);
                --r;
            }
        }

        return category;
    }

    @Override
    public void Restart() {

    }
}

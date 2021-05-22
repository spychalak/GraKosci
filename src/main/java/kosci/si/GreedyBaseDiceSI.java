package kosci.si;

import java.util.Random;

public class GreedyBaseDiceSI implements DiceSI {

    private Random random = new Random();
    private boolean[] rerolls = new boolean[5];

    @Override
    public Category ChooseCategory(int[] dices, boolean[] availableCategories) {
        Category choose = Category.CHANCE;
        int max = 0;
        for(int i = 0; i < 13; i++) {
            if(availableCategories[i]) {
                int currentIterationPoints = PointsCalculator.getPoints(dices, Category.getByRowIndex(i));
                if(currentIterationPoints >= max) {
                    choose = Category.getByRowIndex(i);
                    max = currentIterationPoints;
                }
            }
        }
        return choose;
    }
    @Override
    public boolean[] Reroll(int[] dices, boolean[] availableCategories) {
        for(int i=0; i<5; ++i)
            rerolls[i] = random.nextBoolean();
        return rerolls;
    }

    @Override
    public void Restart() {

    }
}

package kosci.si;

import java.util.Random;

public class GreedyBaseDiceSI implements DiceSI {

    private Random random = new Random();
    private boolean[] rerolls = new boolean[5];

    @Override
    public Category ChooseCategory(int[] dices, Integer[] categories) {
        Category choose = null;
        int max = -1;
        for(int i = 0; i < 13; i++) {
            if(categories[i] == null) {
                if(max < 0) {
                    choose = Category.getByRowIndex(i);
                    max = 0;
                }
                int currentIterationPoints = PointsCalculator.getPoints(dices, Category.getByRowIndex(i));
                if(currentIterationPoints != 0) {
                    if (currentIterationPoints >= max) {
                        choose = Category.getByRowIndex(i);
                        max = currentIterationPoints;
                    }
                }
            }
        }
        return choose;
    }
    @Override
    public boolean[] Reroll(int[] dices, Integer[] categories) {
        for(int i=0; i<5; ++i)
            rerolls[i] = random.nextBoolean();
        return rerolls;
    }

    @Override
    public void Restart() {

    }
}

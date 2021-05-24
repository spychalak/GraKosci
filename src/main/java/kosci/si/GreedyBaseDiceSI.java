package kosci.si;

import java.util.Random;

public class GreedyBaseDiceSI implements DiceSI {
	
    private Random random = new Random();
    private boolean[] rerolls = new boolean[5];

    @Override
    public Category ChooseCategory(int[] dices, Integer[] categories) {
        Category choose = Category.CHANCE;
        int max = 0;
        for(int i = 12; i >= 0; --i) {
            if(categories[i] == null) {
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
    public boolean[] Reroll(int[] dices, Integer[] categories) {
        for(int i=0; i<5; ++i)
            rerolls[i] = random.nextBoolean();
        return rerolls;
    }

    @Override
    public void Restart() {

    }
}

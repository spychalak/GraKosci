package kosci.si;

import java.util.Random;

public class GreedyBaseDiceSI implements DiceSI {
	
    private Random random = new Random();
    private boolean[] rerolls = new boolean[5];

    @Override
    public Category ChooseCategory(int[] dices, Integer[] categories) {
        Category choose = Category.CHANCE;
        int max = -1;
        for(int j = 12; j >= 0; --j) {
			int i=j;
			if(j == Category.THREE_OF_A_KIND.getRowIndex())
				i = Category.FOUR_OF_A_KIND.getRowIndex();
			else if(j == Category.FOUR_OF_A_KIND.getRowIndex())
				i = Category.THREE_OF_A_KIND.getRowIndex();
            if(categories[i] == null) {
                int currentIterationPoints = PointsCalculator.getPoints(dices, Category.getByRowIndex(i));
                if(max < 0) {
                    choose = Category.getByRowIndex(i);
                    max = currentIterationPoints;
                } else if(currentIterationPoints >= max) {
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

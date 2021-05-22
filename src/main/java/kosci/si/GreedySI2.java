package kosci.si;

import java.util.Random;

public class GreedySI2 implements DiceSI {

    private Random random = new Random();
    private boolean[] rerolls = new boolean[5];

    @Override public boolean[] Reroll(int[] dices,
                                      Integer[] categories) {
        
        boolean[] b = new boolean[5];
        int[][][] sets = HandsetsGenerator.allSets;

        for (int i=0; i<12; i++)
        {
            for (int j=0; j<sets[i].length; j++)
            {
                Probabilities.RerollDicesStruct prob = Probabilities.ProbabilityOfHandFrom(dices,Category.getByRowIndex(i),sets[i][j]);
                System.out.println(prob.probability);
            }
            System.out.println("------------------");
        }
        return b;

    }

    @Override public Category ChooseCategory(int[] dices,
                                             Integer[] categories) {
        Category choose = Category.CHANCE;
        int max = 0;
        for(int i = 0; i < 13; i++) {
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

    @Override public void Restart() {

    }
}

package kosci.si;

import java.util.Random;

public class GreedySI2 implements DiceSI {

    private Random random = new Random();
    private boolean[] rerolls = new boolean[5];

    @Override public boolean[] Reroll(int[] dices,
                                      boolean[] availableCategories) {


        int [][][] u = HandsetsGenerator.GenerateAllValidSets();
        boolean [] r = new boolean[5];


        for (int i=0; i<5; i++)
        {System.out.print(dices[i]+ " ");}

        System.out.println("\n");

        for (int i =6 ; i < 12; i++)
        {
            for (int j=0; j < u[i].length; j++)
            {
                int[] uklad = u[i][j];
                double res = Probabilities.ProbabilityOfHandFrom(dices,Category.getByRowIndex(i),uklad);

                System.out.println(res);

            }
            System.out.println(" -------- ");
        }

            return r;
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

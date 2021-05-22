package kosci.si;

import java.util.Arrays;

public class Main3 {

    public static void main(String[] args) {

     //   int testsCount = 1000000;
        long sum = 0;
        //for(int i=0; i<testsCount; ++i) {
            DiceSI si = new GreedySI2();

            Game game = new Game();
            for (int roundNo = 0; roundNo < game.getRoundsCount(); roundNo++) {
                int[] dices = game.throwDices();
                System.out.println("Drew: " + Arrays.toString(dices));
                boolean[] rethrow = si.Reroll(dices, game.getGameStatus().getCatPoints());
                dices = game.rethrowDices(rethrow);
                System.out.println("Drew: " + Arrays.toString(dices));
                rethrow = si.Reroll(dices, game.getGameStatus().getCatPoints());
                dices = game.rethrowDices(rethrow);
                System.out.println("Drew: " + Arrays.toString(dices));
                Category category = si.ChooseCategory(dices, game.getGameStatus().getCatPoints());
                game.chooseCategory(category);
                game.printTable();
            }

        System.out.println(game.getResult());
        //    sum += (long)game.getResult();
       // }
      //  System.out.println("Average result: " + ((double)sum/(double)testsCount));
    }

}

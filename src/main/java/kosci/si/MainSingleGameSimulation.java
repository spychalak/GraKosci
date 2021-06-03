package kosci.si;

import java.util.Arrays;

public class MainSingleGameSimulation {

    public static void main(String[] args) {

        DiceSI si = new GreedyOptimised1();

        Game game = new Game();
        for (int roundNo = 0; roundNo < game.getRoundsCount(); roundNo++) {
            int[] dices = game.throwDices();
            System.out.println("Drew: \t\t" + Arrays.toString(dices));
            boolean[] rethrow = si.Reroll(dices, game.getGameStatus().getCatPoints());
            System.out.println("Reroll?: \t" + printRerolls(rethrow) + "\n");
            dices = game.rethrowDices(rethrow);
            System.out.println("Drew: \t\t" + Arrays.toString(dices));
            rethrow = si.Reroll(dices, game.getGameStatus().getCatPoints());
            System.out.println("Reroll?: \t" + printRerolls(rethrow) + "\n");
            dices = game.rethrowDices(rethrow);
            System.out.println("Drew: \t\t" + Arrays.toString(dices) + "\n");
            Category category = si.ChooseCategory(dices, game.getGameStatus().getCatPoints());
            game.chooseCategory(category);
            game.printTable();

        }

        System.out.println("RESULT: " + game.getResult());

    }

    public static String printRerolls(boolean[] rethrows) {

        String str = " ";

        for (boolean b : rethrows) {
            if (b) {
               str += "X  ";
            } else {
                str += "   ";
            }

        }
        return str;
    }

}

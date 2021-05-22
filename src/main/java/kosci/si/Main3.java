package kosci.si;

public class Main3 {

    public static void main(String[] args) {


        DiceSI si = new GreedySI2();


        Game game = new Game();
        for (int roundNo = 0; roundNo < game.getRoundsCount(); roundNo++) {
            int[] dices = game.throwDices();

            boolean[] rethrow = si.Reroll(dices, game.getGameStatus().getUsedCategories());
            dices = game.rethrowDices(rethrow);
            rethrow = si.Reroll(dices, game.getGameStatus().getUsedCategories());
            dices = game.rethrowDices(rethrow);
            Category category = si.ChooseCategory(dices, game.getGameStatus().getUsedCategories());
            game.chooseCategory(category);
        }
        System.out.println(game.getResult());


    }

}

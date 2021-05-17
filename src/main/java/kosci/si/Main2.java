package kosci.si;

import java.util.Arrays;

public class Main2 {
	public static void main(String[] args) {
		DiceSI si = new RandomStrategy();

		Game game = new Game();
		for (int roundNo = 0; roundNo < game.getRoundsCount(); roundNo++) {
			int[] dices = game.throwDices();

			boolean[] rethrow = si.Reroll(dices, game.getGameStatus().getUsedCategories());
			dices = game.rethrowDices(rethrow);
			rethrow = si.Reroll(dices, game.getGameStatus().getUsedCategories());
			dices = game.rethrowDices(rethrow);
			Category category = si.ChooseCategory(dices, game.getGameStatus().getUsedCategories());
			game.chooseCategory(category);
			game.printTable();
			System.out.println("Points: " + game.getResult());
		}
	}
}

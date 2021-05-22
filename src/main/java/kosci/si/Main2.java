package kosci.si;

public class Main2 {
	public static void main(String[] args) {
		int testsCount = 100000;
		long sum = 0;
		for(int i=0; i<testsCount; ++i) {
			DiceSI si = new GreedySI1();

			Game game = new Game();
			for (int roundNo = 0; roundNo < game.getRoundsCount(); roundNo++) {
				int[] dices = game.throwDices();

				boolean[] rethrow = si.Reroll(dices, game.getGameStatus().getCatPoints());
				dices = game.rethrowDices(rethrow);
				rethrow = si.Reroll(dices, game.getGameStatus().getCatPoints());
				dices = game.rethrowDices(rethrow);
				Category category = si.ChooseCategory(dices, game.getGameStatus().getCatPoints());
				game.chooseCategory(category);
			}
			sum += (long)game.getResult();
		}
		System.out.println("Average result: " + ((double)sum/(double)testsCount));
	}
}


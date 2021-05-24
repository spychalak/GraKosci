package kosci.si;

public class Main2 {
	public static void main(String[] args) {
		int testsCount = 100000;
		long sum = 0;
		int maxGeneral = 0;
		int maxPoints = 0;
		
		DiceSI si = new GreedyOptimised1();
		for(int i=0; i<testsCount; ++i) {
			si.Restart();

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
			int s = game.getGameStatus().getCatPoints()[Category.GENERAL.getRowIndex()];
			if(s > maxGeneral)
				maxGeneral = s;
			if(maxPoints < game.getResult())
				maxPoints = game.getResult();
		}
		System.out.println("Average result: " + ((double)sum/(double)testsCount));
		System.out.println("Max general: " + maxGeneral);
		System.out.println("Max points: " + maxPoints);
	}
}


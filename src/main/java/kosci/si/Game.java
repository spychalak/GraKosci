package kosci.si;

import java.util.Random;

public class Game {
	public static final int DICE_COUNT = 5;
	private final Random rng = new Random(); // FIXME: ziarno

	private final int[] currentDices = new int[DICE_COUNT];

	private final GameStatus gameStatus = new GameStatus();

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public int getRoundsCount() {
		return Category.values().length;
	}

	public int[] throwDices() {
		for (int i = 0; i < DICE_COUNT; i++) {
			currentDices[i] = draw();
		}

		return currentDices.clone();
	}

	public int[] rethrowDices(boolean[] rethrow) {
		for(int i=0; i<rethrow.length; ++i)
			if(rethrow[i])
				currentDices[i] = draw();
		return currentDices.clone();
	}

	public int[] rethrowDices(int[] indexes) {
		for (int index : indexes) {
			currentDices[index] = draw();
		}

		return currentDices.clone();
	}
	
	private int draw() {
		return rng.nextInt(6) + 1;
	}

	// TODO: if category is already in use, then choose best pointing category
	public void chooseCategory(Category cat) {
		if (gameStatus.isCategoryAlreadyUsed(cat)) {
			throw new IllegalStateException("placki"); // TODO; napis?
		}

		int pointsAchieved = PointsCalculator.getPoints(currentDices, cat);

		if (gameStatus.isCategoryAlreadyUsed(Category.GENERAL)) {
			if (gameStatus.getCategoryPoints(Category.GENERAL) >= 50) // DRUGI I DALSZE GENERAŁY
			{
				int checkingNextGeneral = PointsCalculator.getPoints(currentDices, Category.GENERAL);

				if (checkingNextGeneral == 50) {
					if (cat.getRowIndex() < 6) {
						if (pointsAchieved == 0) {
							gameStatus.setCategoryPoints(pointsAchieved, cat);
							return;
						}
					}

					gameStatus.setCategoryPoints(gameStatus.getCategoryPoints(Category.GENERAL) + 100, Category.GENERAL);

					if (cat == Category.BIG_STRAIGHT) {
						gameStatus.setCategoryPoints(40, cat);
						return;
					}

					if (cat == Category.SMALL_STRAIGHT) {
						gameStatus.setCategoryPoints(30, cat);
						return;
					}

					if (cat == Category.FULL_HOUSE) {
						gameStatus.setCategoryPoints(25, cat);
						return;
					}
				}

			}
		}
		gameStatus.setCategoryPoints(pointsAchieved, cat);
	}

	public int getResult() {
		int result = 0;
		int subresult = 0;

		for (int i = 0; i < Category.values().length; i++) {
			if (gameStatus.isCategoryAlreadyUsed(Category.getByRowIndex(i))) {
				result += gameStatus.getCategoryPoints(Category.getByRowIndex(i));
				if (i < 6) {
					subresult += gameStatus.getCategoryPoints(Category.getByRowIndex(i));
				}
			}
		}

		System.out.println("SUBRESULT: " + subresult);

		if (subresult >= 63) {
			result += 35;
		}

		return result;
	}

	public void printTable() {
		for (int i = 0; i < Category.values().length; i++) {
		   Category cat = Category.getByRowIndex(i);
			System.out.print(cat);
			if (!gameStatus.isCategoryAlreadyUsed(cat))
			{
				System.out.print(" :EMPTY");
			}
			else
			{
				System.out.print(" : " +  gameStatus.getCategoryPoints(cat));
			}
			System.out.print("\t\t");
			if (i == 5) {
				System.out.print("\n");}
		}
		System.out.print("\n\n");
	}

}

package kosci.si;

import java.util.Arrays;
import java.util.Random;

public class Game {
    public static final int DICE_COUNT = 5;
    private final Random rng = new Random();

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
        Arrays.sort(currentDices);
        return currentDices;
    }

    public int[] rethrowDices(boolean[] rethrow) {
        for (int i = 0; i < rethrow.length; ++i) {
            if (rethrow[i])
                currentDices[i] = draw();
        }
        Arrays.sort(currentDices);
        return currentDices;
    }

    public int[] rethrowDices(int[] indexes) {
        for (int index : indexes) {
            currentDices[index] = draw();
        }
        return currentDices;
    }

    private int draw() {
        return rng.nextInt(6) + 1;
    }

    public void chooseCategory(Category cat) {
        if (gameStatus.isCategoryAlreadyUsed(cat)) {
            throw new IllegalStateException("Category " + cat + " is already in use");
        }

        int pointsAchieved = PointsCalculator.getPoints(currentDices, cat);

        if (gameStatus.isCategoryAlreadyUsed(Category.GENERAL)) {
            if (gameStatus.getCategoryPoints(Category.GENERAL) >= 50) // DRUGI I DALSZE GENERAŁY
            {
                int checkingNextGeneral = PointsCalculator.getPoints(currentDices, Category.GENERAL); // sprawdzenie czy uklad jest generalem

                // jesli w gornej czesci tabeli w przypadku generala wybieramy kategorie dajaca 0p, to bonus nie przysluguje
                if (checkingNextGeneral == 50) {
                    if (cat.getRowIndex() < 6) {
                        if (pointsAchieved == 0) {
                            gameStatus.setCategoryPoints(pointsAchieved, cat);
                            return;
                        }
                    }

                    // jesli uklad jest generalem (yahtzee), to mozemy uzyc ktorejkolwiek z kategorii z dolu tabeli jako jokera

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
        int result = 0; // laczny wynik
        int subresult = 0; // gorna czesc tabeli

        for (int i = 0; i < Category.values().length; i++) {
            if (gameStatus.isCategoryAlreadyUsed(Category.getByRowIndex(i))) {
                result += gameStatus.getCategoryPoints(Category.getByRowIndex(i));
                if (i < 6) {
                    subresult += gameStatus.getCategoryPoints(Category.getByRowIndex(i));
                }
            }
        }

        // bonus za gorna czesc tabeli
        if (subresult >= 63) {
            result += 35;
        }

        return result;
    }

    public void printTable() {
        for (int i = 0; i < Category.values().length; i++) {
            Category cat = Category.getByRowIndex(i);
            System.out.print(cat);
            if (!gameStatus.isCategoryAlreadyUsed(cat)) {
                System.out.print(" :EMPTY"); // kategoria niewykorzystana
            } else {
                System.out.print(" : " + gameStatus.getCategoryPoints(cat)); // kategoria wykorzystana
            }
            System.out.print("\t\t");
            if (i == 5) {
                System.out.print("\n");
            }
        }
        System.out.print("\n\n\n");
    }

}

package kosci.si;

import java.util.Arrays;
import java.util.Scanner;

public class MainHumanControl {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Game game = new Game();
        for (int roundNo = 0; roundNo < game.getRoundsCount(); roundNo++) {
            System.out.println("Round " + (roundNo + 1));

            int[] dices = game.throwDices();


            System.out.println("Drew: " + Arrays.toString(dices));

            System.out.print("How many dices you want to rethrow (0 = none): ");

            int rethrowsNo = scanner.nextInt();

            int[] indices = new int[rethrowsNo];

            for (int i = 0; i < rethrowsNo; i++) {
                indices[i] = scanner.nextInt();
            }

            if (rethrowsNo != 0) {
                dices = game.rethrowDices(indices);
            }

            System.out.println("Drew: " + Arrays.toString(dices));

            System.out.print("How many dices you want to rethrow (0 = none): ");

            rethrowsNo = scanner.nextInt();

            indices = new int[rethrowsNo];

            for (int i = 0; i < rethrowsNo; i++) {
                indices[i] = scanner.nextInt();
            }

            if (rethrowsNo != 0) {
                dices = game.rethrowDices(indices);
            }

            System.out.println("Drew: " + Arrays.toString(dices));

            System.out.print("Choose category: ");
            int category = scanner.nextInt();

            game.chooseCategory(Category.getByRowIndex(category));

            game.printTable();
            System.out.println("Points: " + game.getResult());
        }

    }
}

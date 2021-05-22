package kosci.si;

public class HandsetsGenerator {
	public static int[][][] allSets = GenerateAllValidSets();
	
    public static int[][][] GenerateAllValidSets() {
        int[][][] ret = new int[13][][];

        //3 OR MORE ONES-SIXES
        GenerateUpperTable(ret);

        //THREE_OF_A_KIND
        GenerateThreeOfKind(ret);

        //FOUR_OF_A_KIND
        GenrateFourOfKind(ret);

        // FULL HOUSE
        GenerateFullHouse(ret);

        //SMALL STRAIGHT
        GenerateSmallStraight(ret);

        //BIG STRAIGHT
        GenerateBigStraight(ret);

        //GENERAL
        GenerateGeneral(ret);

        return ret;
    }

    private static void GenerateUpperTable(int[][][] ret) {

        for (int i=0; i<6; i++)
        {
            ret[i] = new int [3][];
            for (int j=0; j<=2; j++)
            {
                ret[i][j] = new int [j+3];
                int value = i+1;
                for (int k=0; k<j+3 ;k++)
                {
                    ret[i][j][k] = value;
                }
            }
        }

    }

    private static void GenerateFullHouse(int[][][] ret) {
        int idx = 0;
        ret[Category.FULL_HOUSE.getRowIndex()] = new int[30][5];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                if (i < j) {
                    for (int k = 0; k < 3; k++) {
                        int value = i + 1;
                        ret[Category.FULL_HOUSE.getRowIndex()][idx][k] = value;
                    }
                    for (int k = 3; k < 5; k++) {
                        int value = j + 1;
                        ret[Category.FULL_HOUSE.getRowIndex()][idx][k] = value;
                    }
                    idx++;
                } else if (i > j) {
                    for (int k = 0; k < 2; k++) {
                        int value = j + 1;
                        ret[Category.FULL_HOUSE.getRowIndex()][idx][k] = value;
                    }
                    for (int k = 2; k < 5; k++) {
                        int value = i + 1;
                        ret[Category.FULL_HOUSE.getRowIndex()][idx][k] = value;
                    }
                    idx++;
                }

            }
        }
    }

    private static void GenerateBigStraight(int[][][] ret) {
        ret[Category.BIG_STRAIGHT.getRowIndex()] = new int[2][];
        for (int i = 0; i < 2; i++) {
            ret[Category.BIG_STRAIGHT.getRowIndex()][i] = new int[5];
            for (int j = 0; j < 5; j++) {
                int value = i + j + 1;
                ret[Category.BIG_STRAIGHT.getRowIndex()][i][j] = value;
            }
        }
    }

    private static void GenerateSmallStraight(int[][][] ret) {
        ret[Category.SMALL_STRAIGHT.getRowIndex()] = new int[3][];
        for (int i = 0; i < 3; i++) {
            ret[Category.SMALL_STRAIGHT.getRowIndex()][i] = new int[4];
            for (int j = 0; j < 4; j++) {
                int value = i + j + 1;
                ret[Category.SMALL_STRAIGHT.getRowIndex()][i][j] = value;
            }
        }
    }

    private static void GenerateGeneral(int[][][] ret) {
        ret[Category.GENERAL.getRowIndex()] = new int[6][];
        for (int i = 0; i < 6; i++) {
            ret[Category.GENERAL.getRowIndex()][i] = new int[5];
            int value = i + 1;
            for (int j = 0; j < 5; j++) {
                ret[Category.GENERAL.getRowIndex()][i][j] = value;
            }
        }
    }

    private static void GenrateFourOfKind(int[][][] ret) {
        ret[Category.FOUR_OF_A_KIND.getRowIndex()] = new int[6][];
        for (int i = 0; i < 6; i++) {
            ret[Category.FOUR_OF_A_KIND.getRowIndex()][i] = new int[4];
            int value = i + 1;
            for (int j = 0; j < 4; j++) {
                ret[Category.FOUR_OF_A_KIND.getRowIndex()][i][j] = value;
            }
        }
    }

    private static void GenerateThreeOfKind(int[][][] ret) {
        ret[Category.THREE_OF_A_KIND.getRowIndex()] = new int[6][];

        for (int i = 0; i < 6; i++) {
            ret[Category.THREE_OF_A_KIND.getRowIndex()][i] = new int[3];
            int value = i + 1;
            for (int j = 0; j < 3; j++) {
                ret[Category.THREE_OF_A_KIND.getRowIndex()][i][j] = value;
            }
        }
    }
}


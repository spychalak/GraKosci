package kosci.si;

public enum Category {
    ONES(0),
    TWOS(1),
    THREES(2),
    FOURS(3),
    FIVES(4),
    SIXES(5),
    THREE_OF_A_KIND(6),
    FOUR_OF_A_KIND(7),
    FULL_HOUSE(8),
    SMALL_STRAIGHT(9),
    BIG_STRAIGHT(10),
    GENERAL(11),
    CHANCE(12);

    private final int rowIndex;

    Category(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public static Category getByRowIndex(int rowIndex) {
        for (Category c : values()) {
            if (rowIndex == c.getRowIndex()) {
                return c;
            }
        }

        throw new IllegalArgumentException("Category for given rowIndex doesn't exist.");
    }
}

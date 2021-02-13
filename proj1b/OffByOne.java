public class OffByOne implements CharacterComparator {

    /**Return true for characters that are different by exactly one.
     * Otherwise, false.*/
    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == 1 || y - x == 1) {
            return true;
        }
        return false;
    }
}

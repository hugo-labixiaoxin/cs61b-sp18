public class Palindrome {

    /**Turn the word into a deque type*/
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    /**Judge whether the word is palindrome or not*/
    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else if (word.charAt(0) == word.charAt(word.length() - 1)) {
            return isPalindrome(cuttingword(word));
        }
        return false;
    }

    /**Cut the first and the last letter of the current word*/
    private String cuttingword (String word) {
        String result = "";
        for (int i = 1; i < word.length() - 1; i += 1) {
            result += word.charAt(i);
        }
        return result;
    }

    public boolean isPalindrome (String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        } else if (cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))) {
            return isPalindrome(cuttingword(word), cc);
        }
        return false;
    }
}

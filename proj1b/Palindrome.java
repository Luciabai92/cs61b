public class Palindrome {
    /** convert String to Deque */
    public Deque<Character> wordToDeque(String word){
        Deque<Character> p = new ArrayDeque<Character>();
        for(int i = 0; i< word.length(); i++){
            p.addLast(word.charAt(i));
        }
        return p;
    }

    /** check if palindrome */
    private boolean isPalindrome(Deque<Character> DequeWord){
        if(DequeWord.size()<=1){
            return true;
        }
        return DequeWord.removeLast() == DequeWord.removeFirst() && isPalindrome(DequeWord);
    }

    public boolean isPalindrome(String word){
        Deque<Character> DequeWord = wordToDeque(word);
            return isPalindrome(DequeWord);
    }

    /** check if off-by-1 palindrome */
    /** overload */
    public boolean isPalindrome(Deque<Character> DequeWord, CharacterComparator cc){
        if(DequeWord.size()<=1){
            return true;
        }
        return cc.equalChars(DequeWord.removeLast(), DequeWord.removeFirst()) && isPalindrome(DequeWord,cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> DequeWord = wordToDeque(word);
        return isPalindrome(DequeWord,cc);
    }
}

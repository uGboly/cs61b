public class Palindrome{
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new ArrayDeque<Character>();

        for (int i = 0; i < word.length(); i++ ) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }


    private boolean isPalindrome(Deque<Character> dq){
        if(dq.isEmpty() || dq.size()==1){
            return true;
        }
        Character c1 = dq.removeFirst();
        Character c2 = dq.removeLast();

        return isPalindrome(dq) && (c1.equals(c2));
    }

    public boolean isPalindrome(String word){
        return isPalindrome(wordToDeque(word));
    }


    private boolean isPalindrome(Deque<Character> dq, CharacterComparator cc){
        if(dq.isEmpty() || dq.size()==1){
            return true;
        }
        Character c1 = dq.removeFirst();
        Character c2 = dq.removeLast();

        return isPalindrome(dq) && (cc.equalChars(c1,c2));
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        return isPalindrome(wordToDeque(word),cc);
    }
}

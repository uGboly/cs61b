public class OffByN implements CharacterComparator{
    int n;

    OffByN(int N){
        n=N;
    }

    @Override
    public boolean equalChars(char x, char y){
        if (x - y == n){
            return true;
        }
        return false;
    }
}

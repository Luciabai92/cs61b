public class OffByN implements CharacterComparator{
    public int OffBy;

    public OffByN(int N){
        OffBy= N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if(Math.abs(x-y)==OffBy){
            return true;
        }else{
            return false;
        }
    }
}

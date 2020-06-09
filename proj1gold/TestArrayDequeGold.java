import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeGold {
    private static String message = "";

    //test add
    private static void add(StudentArrayDeque<Integer> sad, ArrayDequeSolution<Integer> ads, Integer i){
        Double random = StdRandom.uniform();
        if(random > 0.5){
            sad.addFirst(i);
            ads.addFirst(i);
            message += "\naddFirst(" + i + ")";
        }else{
            sad.addLast(i);
            ads.addLast(i);
            message += "\naddLast(" + i + ")";
        }
    }

    //test remove
    private static void remove(StudentArrayDeque<Integer> sad, ArrayDequeSolution<Integer> ads){
        Double random = StdRandom.uniform();
        Integer actual;
        Integer expected;

        if(random > 0.5){
            actual = sad.removeFirst();
            expected = ads.removeFirst();
            message += "\nremoveFirst()";
        }else{
            actual = sad.removeLast();
            expected = ads.removeLast();
            message += "\nremoveLast()";
        }

        assertEquals(message, expected, actual);
    }

    @Test
    public void Randomized(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<Integer>();

        for (int i = 0; i < 100; i+=1){
            if(sad.isEmpty()){
                //add if empty.
                add(sad,ads,i);
            }else{
                Double random = StdRandom.uniform();
                //use random number randomly add or remove.
                if(random > 0.5){
                    add(sad,ads,i);
                }else{
                    remove(sad,ads);
                }
            }
        }

    }
}

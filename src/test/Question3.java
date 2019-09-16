package test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

public class Question3 {

    @Test(description = "Question 3")
    @Parameters("nthSmallestNumberInput")
    public void nthSmallestNumber(String nthSmallestNumberInput) {
        Random rand = new Random();

        SortedSet set = new TreeSet();
        while (set.size() != 500) {
            int num = rand.nextInt(1000);
            set.add(num);
        }
        Integer index = Integer.valueOf(nthSmallestNumberInput);
        Integer cnt = 0;

        Iterator it2 = set.iterator();
        System.out.println("\n\nFinal set");
        while (it2.hasNext()) {
            System.out.print(it2.next() + " ");
        }

        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (cnt == index - 1) {
                System.out.println("\n\nThe " + nthSmallestNumberInput + " smallest element is: " + it.next());
                break;
            } else {
                it.next();
                cnt++;
            }

        }

    }


}

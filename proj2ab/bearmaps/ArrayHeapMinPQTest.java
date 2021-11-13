package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import edu.princeton.cs.algs4.Stopwatch;

public class ArrayHeapMinPQTest {
    @Test
    public void cotainsTest() {
        ExtrinsicMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.add(1, 1);
        pq.add(2, 2);
        pq.add(3, 3);
        pq.add(100, 100);
        pq.add(999, 999);
        pq.add(-1, -1);

        assertTrue(pq.contains(1));
        assertTrue(pq.contains(2));
        assertTrue(pq.contains(3));
        assertTrue(pq.contains(100));
        assertTrue(pq.contains(999));
        assertTrue(pq.contains(-1));
        for (int i = 101; i < 190; i++) {
            pq.add(i, i);
            assertTrue(pq.contains(i));
        }
    }

    @Test
    public void getSmallestTest() {
        ExtrinsicMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.add(2, 2);
        assertEquals(2, (int)pq.getSmallest());
        pq.add(3, 4);
        assertEquals(2, (int)pq.getSmallest());
        pq.add(1, 0);
        assertEquals(1, (int)pq.getSmallest());
        pq.add(-999, -1);
        assertEquals(-999, (int)pq.getSmallest());

    }

    @Test
    public void removeSmallestTest() {
        ExtrinsicMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.add(1, 1);
        pq.add(2, 2);
        pq.add(3, 3);
        pq.add(100, 100);
        pq.add(999, 999);
        pq.add(-1, -1);

        assertEquals(-1, (int)pq.removeSmallest());
        assertEquals(1, (int)pq.removeSmallest());
        assertEquals(2, (int)pq.removeSmallest());
        assertEquals(3, (int)pq.removeSmallest());
        assertEquals(100, (int)pq.removeSmallest());
        assertEquals(999, (int)pq.removeSmallest());

        for (int i = 101; i < 1000; i++) {
            pq.add(i, i);
        }

        for (int i = 101; i < 190; i++) {

            assertEquals(i, (int)pq.removeSmallest());
        }

    }

    @Test
    public void changePrioritytest() {
        ExtrinsicMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.add(1, 1);
        pq.add(2, 2);
        pq.changePriority(2, -1);
        assertEquals(2, (int)pq.getSmallest());

        pq.changePriority(1, -2);
        assertEquals(1, (int)pq.getSmallest());

        pq.add(100, 100);
        pq.add(999, 999);
        pq.add(-1, -1);
        pq.changePriority(100, -90);
        assertEquals(100, (int)pq.getSmallest());
    }

    @Test
    public void timeEfficiencyTest() {
        ExtrinsicMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        ExtrinsicMinPQ<Integer> naivePq = new NaiveMinPQ<>();

        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < 100000; i += 1) {
            pq.add(i,-i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 100; i += 1) {
            naivePq.add(i, -i);
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");


    }
}

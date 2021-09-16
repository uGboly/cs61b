package hw3.hash;

import java.util.*;

public class OomageTestUtility {

    /* utility function that returns true if the given oomages
     * have hashCodes that would distribute them fairly evenly across
     * M buckets. To do this, convert each oomage's hashcode in the
     * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
     * and ensure that no bucket has fewer than N / 50
     * Oomages and no bucket has more than N / 2.5 Oomages.
     */
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for (Oomage o : oomages){
            int bucket = (o.hashCode() & 0x7FFFFFFF) % M;
            if (m.containsKey(M)) {
                m.replace(bucket, m.get(bucket)+1);
            }else {
                m.put(bucket, 1);
            }
        }

        for (int i : m.values()){
            if ((i * 2.5 > M) || (i * 50 < M)) {
                return false;
            }
        }
        return true;
    }
}

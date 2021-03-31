package hw3.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        HashMap<Integer, ArrayList<Oomage>> bucket = new HashMap<>();
        for (int i = 0; i < M; i += 1){
            bucket.put(i, new ArrayList<>());
        }

        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            bucket.get(bucketNum).add(o);
        }

        for (int bN : bucket.keySet()) {
            if (bucket.get(bN).size() >= (oomages.size() / 50) && bucket.get(bN).size() <= (oomages.size() / 2.5)) {
                return true;
            }
            break;
        }
        return false;
    }
}

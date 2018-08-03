package Util;

import java.util.*;

public class PriorityQueue<T> extends ArrayList<T> {

    Comparator<T> comparator;
    public PriorityQueue(Comparator<T> comparator) {
        super();
        this.comparator = comparator;
    }

    public T getHighestPriority() {
        T largest = this.get(0);
        for (T t : this) {
            if (comparator.compare(t, largest) > 0) {
                largest = t;
            }
        }
        this.remove(largest);
        return largest;
    }
}

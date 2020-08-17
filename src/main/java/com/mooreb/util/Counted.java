package com.mooreb.util;

import java.util.Objects;

public class Counted<T> implements Comparable<Counted> {
    private final T t;
    private final long count;
    private final long totalNumEntries;
    private final long numNullEntries;

    public Counted(final T t, final long count, final long totalNumEntries, final long numNullEntries) {
        this.t = t;
        this.count = count;
        this.totalNumEntries = totalNumEntries;
        this.numNullEntries = numNullEntries;
    }

    public T getT() {
        return t;
    }

    public long getCount() {
        return count;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counted<?> counted = (Counted<?>) o;
        return count == counted.count &&
                Objects.equals(t, counted.t);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t, count);
    }

    @Override
    public String toString() {
        final double percent = ((0.0+count)/totalNumEntries)*100.0;
        final double percentNonNull = ((0.0+count)/(totalNumEntries-numNullEntries))*100.0;
        final StringBuilder sb = new StringBuilder("Counted{");
        sb.append("t=").append(t);
        sb.append(", count=").append(count);
        sb.append(", totalNumEntries=").append(totalNumEntries);
        if(numNullEntries > 0) {
            sb.append(", numNullEntries=").append(numNullEntries);
        }
        sb.append(", ").append(String.format("%6.2f%%", percent)).append(" of all");
        if((null != t) && (numNullEntries > 0)) {
            sb.append(", ").append(String.format("%6.2f%%", percentNonNull)).append(" of non-null");
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Counted o) {
        return Long.compare(o.count, count);
    }
}

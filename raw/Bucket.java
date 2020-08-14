package com.mooreb.util;

import java.util.Objects;

public class Bucket<T extends Comparable<T>> {
    private final T low;
    private final T high;
    private final boolean hasLower;
    private final boolean hasUpper;
    private final boolean inclusiveLower;
    private final boolean inclusiveUpper;

    public static <R extends Comparable<R>> Bucket<R> catchAllLeft(R high, boolean inclusiveUpper) {
        if(null == high) throw new IllegalArgumentException("high cannot be null");
        final boolean shouldBeUnused = true;
        return new Bucket<R>(null, high, false, true, shouldBeUnused, inclusiveUpper);
    }

    public static <R extends Comparable<R>> Bucket<R> catchAllRight(R low, boolean inclusiveLower) {
        if(null == low) throw new IllegalArgumentException("low cannot be null");
        final boolean shouldBeUnused = true;
        return new Bucket<R>(low, null, true, false, inclusiveLower, shouldBeUnused);
    }

    public static <R extends Comparable<R>> Bucket<R> middle(final R low, final R high, final boolean inclusiveLower, final boolean inclusiveUpper) {
        if(null == low) throw new IllegalArgumentException("low cannot be null");
        if(null == high) throw new IllegalArgumentException("high cannot be null");
        return new Bucket<R>(low, high, true, true, inclusiveLower, inclusiveUpper);
    }

    private Bucket(final T low, final T high, final boolean hasLower, final boolean hasUpper, final boolean inclusiveLower, final boolean inclusiveUpper) {
        if(!hasLower && !hasUpper) {
            throw new IllegalArgumentException("must have at least one upper or lower bound");
        }
        this.low = low;
        this.high = high;
        this.hasLower = hasLower;
        this.hasUpper = hasUpper;
        this.inclusiveLower = inclusiveLower;
        this.inclusiveUpper = inclusiveUpper;
    }

    /*
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * a.compareTo(b) returns -1 when a < b
     * a.compareTo(b) returns +1 when a > b
     */


    // package scope
    boolean belongsInThisBucket(T observation) {
        if(null == observation) throw new IllegalArgumentException("observation cannot be null");
        int lowerCmp;
        if(hasLower) {
            lowerCmp = low.compareTo(observation);
            if(!inclusiveLower && (0 == lowerCmp)) return false;
        }
        else {
            // say that the observation is higher than the (non-existent) low end of the bucket
            // this bucket is a catch-all bucket for observations less than the high end of the bucket
            lowerCmp = -1;
        }

        if(lowerCmp > 0) return false;

        int upperCmp;
        if(hasUpper) {
            upperCmp = observation.compareTo(high);
            if(!inclusiveUpper && (0 == upperCmp)) return false;
        }
        else {
            // say that the observation is lower than the (non-existent) high end of the bucket
            // this bucket is a catch-all bucket for observations greater than the low end of the bucket
            upperCmp = -1;
        }

        if(upperCmp > 0) return false;

        // at this point we know that the observation is higher than the low and lower than the high
        return true;
    }

    public String reify(long count) {
        final StringBuilder sb = new StringBuilder();
        if(hasLower) {
            sb.append(low);
            if(inclusiveLower) {
                sb.append(" <= ");
            }
            else {
                sb.append(" <  ");
            }
        }
        else {
            int numSpaces = high.toString().length();
            numSpaces += 4; // for the comparison operator
            sb.append(numSpaces(numSpaces));
        }
        sb.append(" x ");
        if(hasUpper) {
            if(inclusiveUpper) {
                sb.append(" <= ");
            }
            else {
                sb.append(" <  ");
            }
            sb.append(high);
        }
        else {
            int numSpaces = low.toString().length();
            numSpaces += 4; // for the comparison operator
            sb.append(numSpaces(numSpaces));
        }
        sb.append(": ");
        sb.append(String.format("%10d", count));
        return sb.toString();
    }

    private String numSpaces(int n) {
        final StringBuilder sb = new StringBuilder(n);
        for(int i=0; i<n; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public T getLow() {
        return low;
    }

    public T getHigh() {
        return high;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket<?> bucket = (Bucket<?>) o;
        return hasLower == bucket.hasLower &&
                hasUpper == bucket.hasUpper &&
                inclusiveLower == bucket.inclusiveLower &&
                inclusiveUpper == bucket.inclusiveUpper &&
                Objects.equals(low, bucket.low) &&
                Objects.equals(high, bucket.high);
    }

    @Override
    public int hashCode() {
        return Objects.hash(low, high, hasLower, hasUpper, inclusiveLower, inclusiveUpper);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bucket{");
        sb.append("low=").append(low);
        sb.append(", high=").append(high);
        sb.append(", hasLower=").append(hasLower);
        sb.append(", hasUpper=").append(hasUpper);
        sb.append(", inclusiveLower=").append(inclusiveLower);
        sb.append(", inclusiveUpper=").append(inclusiveUpper);
        sb.append('}');
        return sb.toString();
    }
}

package neetcode150.common;

/** Interval type used by the LintCode versions of Meeting Rooms (252, 253). */
public class Interval {
    public int start;
    public int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + start + "," + end + ")";
    }
}

/**
 * Created by Mohammad Haider (haidmoham), with the help of Hassan Almas (halmas94).
 */
import java.util.*;

public class SegmentTree {
    SegmentTree left, right;
    int val;
    int del = 0;
    Interval range;

    public static final int IDENTITY = Integer.MAX_VALUE;

    public static int combine(int x, int y) {
        return Math.min(x, y);
    }

    public SegmentTree(int l, int r) {
        this(new Interval(l, r));
    }

    public SegmentTree(Interval i) {
        range = i;
        if (range.length() > 1) {
            left = new SegmentTree(range.leftInterval());
            right = new SegmentTree(range.rightInterval());
        }
    }

    public int rangeQuery(int l, int r) {
        Interval range = new Interval(l, r);
        return rangeQuery(range);
    }

    public void propagate() {
        if (this.range.length() > 1) {
            this.left.del += this.del;
            this.right.del += this.del;
            this.del = 0;
            this.val = combine(this.left.getVal(), this.right.getVal());
        }
    }

    public int getVal() {
        return this.val + this.del * this.range.length();
    }

    public int rangeQuery(Interval i) {
        if (!this.range.intersects(i)) {
            return IDENTITY;
        } else if (this.range.contained(i)) {
            return this.getVal();
        } else {
            return combine(this.left.rangeQuery(i), this.right.rangeQuery(i));
        }
    }

    public void increment(int delta, Interval i) {
        propagate();
        if (!this.range.intersects(i)) {
            return;
        } else if (this.range.contained(i)) {
            this.del += delta;
        } else {
            this.left.increment(delta, i);
            this.right.increment(delta, i);
            combine(this.left.rangeQuery(i), this.right.rangeQuery(i));
        }
    }

    // public void update(int i, int val) {
    // if (range.length() == 1) {
    // this.val = val;
    // } else {
    // if (this.range.leftInterval().containsPoint(i)) {
    // this.left.update(i, val);
    // } else {
    // this.right.update(i, val);
    // }
    // this.val = combine(this.left.getVal(), this.right.getVal());
    // }
    // }

    private static class Interval {
        final int left, right;

        public Interval(int l, int r) {
            left = l;
            right = r;
        }

        public int midPoint() {
            return (left + right) / 2;
        }

        public int length() {
            return right - left + 1;
        }

        public boolean containsPoint(int i) {
            return this.left <= i && i <= this.right;
        }

        public Interval leftInterval() {
            return new Interval(left, midPoint());
        }

        public Interval rightInterval() {
            return new Interval(midPoint() + 1, right);
        }

        public boolean contained(Interval i) {
            return i.left <= this.left && this.right <= i.right;
        }

        public boolean intersects(Interval i) {
            return !(i.right < this.left || this.right < i.left);
        }
    }
}
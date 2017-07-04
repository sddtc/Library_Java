package sddtc.library.java.hardmode;

import org.junit.Assert;
import org.junit.Test;
import sddtc.library.java.object.Interval;

import java.util.ArrayList;
import java.util.List;

public class FastSolutionTest {

    private FastSolutions fastSolutions = new FastSolutions();

    @Test
    /* Given  [1,3],[2,6],[8,10],[15,18]
     * return [1,6],[8,10],[15,18] */
    public void should_merge_four_intervals_to_three_intervals() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(15, 18));

        List<Interval> result = fastSolutions.merge(intervals);

        Assert.assertEquals(new Interval(1, 6), result.get(0));
        Assert.assertEquals(new Interval(8, 10), result.get(1));
        Assert.assertEquals(new Interval(15,18), result.get(2));
    }

    @Test
    /* Given [1,4],[0,0]
     * return [0,0],[1,4] */
    public void should_merge_two_intervals() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(0, 0));

        List<Interval> result = fastSolutions.merge(intervals);

        Assert.assertEquals(new Interval(0,0), result.get(0));
        Assert.assertEquals(new Interval(1,4), result.get(1));
    }
}

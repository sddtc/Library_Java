package sddtc.library.java.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TweeterTest {

    @Test
    public void tweeter() {
        Twitter twitter = new Twitter();
        // User 1 posts a new tweet (id = 5).
        twitter.postTweet(1, 5);
        // User 1's news feed should return a list with 1 tweet id -> [5].
        List<Integer> feeds = twitter.getNewsFeed(1);
        Assert.assertEquals(5, feeds.get(0).intValue());

        // User 1 follows user 2.
        twitter.follow(1, 2);
        // User 2 posts a new tweet (id = 6).
        twitter.postTweet(2, 6);
        // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
        // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        List<Integer> feeds2 = twitter.getNewsFeed(1);
        for (int i = 0; i < feeds2.size(); i++) {
            System.out.print(feeds2.get(i) + " ");
        }

        Assert.assertEquals(2, feeds2.size());
        Assert.assertEquals(6, feeds2.get(0).intValue());
        Assert.assertEquals(5, feeds2.get(1).intValue());

        // User 1 unfollows user 2.
        twitter.unfollow(1, 2);
        // User 1's news feed should return a list with 1 tweet id -> [5],
        // since user 1 is no longer following user 2.
        List<Integer> feeds3 = twitter.getNewsFeed(1);

        Assert.assertEquals(5, feeds3.get(0).intValue());
    }
}

package ch.deathmar;

import junit.framework.TestCase;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class CountYusukeTest extends TestCase {
    public void testIsYusuke() throws Exception{
        assertTrue(CountYusuke.isYusuke("yusuke"));
        assertTrue(CountYusuke.isYusuke("Yusuke"));
        assertTrue(CountYusuke.isYusuke("ゆうすけ"));
        assertTrue(CountYusuke.isYusuke("ゆーすけ"));
        assertTrue(CountYusuke.isYusuke("ユウスケ"));
        assertTrue(CountYusuke.isYusuke(" 雄助"));
        assertTrue(CountYusuke.isYusuke("裕介yusukey"));
        assertTrue(CountYusuke.isYusuke("裕典"));
        assertTrue(CountYusuke.isYusuke("優介"));
        assertTrue(CountYusuke.isYusuke("遊助"));
        assertFalse(CountYusuke.isYusuke("yu-suke"));
        assertFalse(CountYusuke.isYusuke("yosuke"));
        assertFalse(CountYusuke.isYusuke("yamamoto"));
    }
    public void testCount() throws Exception{
//        Twitter twitter = new TwitterFactory("/test").getInstance();
//        assertTrue(1 == new CountYusuke().count(twitter).count);
    }
}
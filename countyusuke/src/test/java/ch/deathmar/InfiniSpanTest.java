package ch.deathmar;

import junit.framework.TestCase;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

public class InfiniSpanTest extends TestCase {
    public void testInfiniSpan() throws Exception {
        Cache<Object, Object> cache = new DefaultCacheManager(InfiniSpanTest.class.getResourceAsStream("/infinispan.xml")).getCache("xml-configured-cache");
        System.out.println(cache.get("a"));
        cache.put("a", "b");

    }
}

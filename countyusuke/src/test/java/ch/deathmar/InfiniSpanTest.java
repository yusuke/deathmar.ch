package ch.deathmar;

import junit.framework.TestCase;

public class InfiniSpanTest extends TestCase {
    public void testInfiniSpan() throws Exception {
        Store.putTemporal("a", "1");
        Store.putTemporal("b", "2");
        Store.putTemporal("c", "3");
        Store.putTemporal("d", "3");
        Store.putTemporal("e", "3");

        assertNotNull(Store.getTemporal("e"));
    }
}

package com.example.stopwatchapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class StopWatchTest {
    @Test
    public void testConstructor() {
        StopWatch stopWatch = new StopWatch("00:00:01");

//        stopWatch.tick();
        assertEquals("00:00:01", stopWatch.toString());
    }
}
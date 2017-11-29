package test;

import static org.junit.Assert.*;

import model.Solution;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by gwenli on 2017-09-30.
 */
public class SolutionTest {
private Solution s = new Solution();

    @Before
    public void runBefore() {
        ByteArrayInputStream in = new ByteArrayInputStream("+1 778 680 5738".getBytes());
        System.setIn(in);
    }

    @Test
    public void test(){

    }



}
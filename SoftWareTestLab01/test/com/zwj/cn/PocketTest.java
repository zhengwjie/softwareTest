package com.zwj.cn;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.junit.Test;

public class PocketTest {

	@Test
	public void test() {
		int trueArrays[]= {0,50,55,61,66,67,72,73,93};
		Pocket pocket=new Pocket();
		for(int i=0;i<9;++i)
		{
			assertTrue(pocket.judge(trueArrays[i]));
		}
	}
	@Test
	public void testFalse()
	{
		int falseArrays[]= {-1,95,69};
		Pocket pocket=new Pocket();
		for(int i=0;i<3;++i)
		{
			assertFalse(pocket.judge(falseArrays[i]));
		}
	}
}

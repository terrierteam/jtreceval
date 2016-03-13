package uk.ac.gla.terrier.jtreceval;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestItRuns 
{
	@Test public void testBasic()
	{
		assertEquals(0, new trec_eval().run(new String[]{"-h"}));
	}
}
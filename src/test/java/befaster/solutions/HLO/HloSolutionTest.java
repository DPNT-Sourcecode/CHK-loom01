package befaster.solutions.HLO;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

public class HloSolutionTest {
	private HelloSolution helloSolution;
	
	@Before
	public void setUp() {
		this.helloSolution = new HelloSolution();
	}
	
	@Test
    public void get_hello() {
        assertThat(helloSolution.hello("Tom"), equalTo("Hello, Tom!"));
    }
	
	 @Test(expected = RuntimeException.class)
    public void get_hello_for_wrong_input() {
    	helloSolution.hello(null);
    }
}

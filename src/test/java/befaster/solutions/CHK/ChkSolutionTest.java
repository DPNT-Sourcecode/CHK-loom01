package befaster.solutions.CHK;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

public class ChkSolutionTest {
	private CheckoutSolution checkoutSolution;
	
	@Before
	public void setUp() {
		this.checkoutSolution = new CheckoutSolution();
	}
	
	@Test
    public void checkout() {
        assertThat(helloSolution.hello("Tom"), equalTo("Hello, Tom!"));
    }
	
	 @Test(expected = RuntimeException.class)
    public void get_hello_for_wrong_input() {
    	helloSolution.hello(null);
    }
}

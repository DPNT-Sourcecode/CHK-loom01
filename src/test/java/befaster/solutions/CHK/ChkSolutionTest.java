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
        assertThat(checkoutSolution.checkout("ABCD"), equalTo(115));
    }
	
	@Test
    public void checkout_with_special_offer_for_A() {
        assertThat(checkoutSolution.checkout("AAAABCD"), equalTo(245));
    }
	
	@Test
    public void checkout_with_special_offer_for_A_and_B() {
        assertThat(checkoutSolution.checkout("AAAABBBCD"), equalTo(290));
    }
	
	@Test
    public void checkout_with_wrong_input() {
        assertThat(checkoutSolution.checkout("AAAABEBBCD"), equalTo(-1));
    }
}



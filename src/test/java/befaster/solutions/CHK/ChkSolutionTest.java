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
        assertThat(checkoutSolution.checkout("AAAAB2BBCD"), equalTo(-1));
    }
	
	@Test
    public void checkout_with_special_offer_for_A_and_B_2() {
        assertThat(checkoutSolution.checkout("AAAAAAAABBBCD"), equalTo(440));
    }
	
	@Test
    public void checkout_with_special_offer_for_A_2() {
        assertThat(checkoutSolution.checkout("AAAAABCD"), equalTo(265));
    }
	
	@Test
    public void checkout_with_free_item() {
        assertThat(checkoutSolution.checkout("ABBCDEE"), equalTo(195));
    }
	
	@Test
    public void checkout_with_2_free_items() {
        assertThat(checkoutSolution.checkout("ABBCDEEEE"), equalTo(245));
    }
	
	@Test
    public void checkout_with_self_free_item() {
        assertThat(checkoutSolution.checkout("AFFFF"), equalTo(80));
    }
	
	@Test
    public void checkout_with_2_self_free_items() {
        assertThat(checkoutSolution.checkout("AFFFFFF"), equalTo(90));
    }
	
	@Test
    public void checkout_with_group_offer() {
        assertThat(checkoutSolution.checkout("STXYZ"), equalTo(82));
    }
	
	@Test
    public void checkout_with_test_from_framework() {
        assertThat(checkoutSolution.checkout("ABCDEFGHIJKLMNOPQRSTUVW"), equalTo(795));
    }
	
	@Test
    public void checkout_with_test_from_framework_2() {
        assertThat(checkoutSolution.checkout("ZZZ"), equalTo(45));
    }
	
	@Test
    public void checkout_with_test_from_framework_3() {
        assertThat(checkoutSolution.checkout("STXSTX"), equalTo(90));
    }
	
	@Test
    public void checkout_with_test_from_framework_4() {
        assertThat(checkoutSolution.checkout("SSSZ"), equalTo(65));
    }
	
	@Test
    public void checkout_with_test_from_framework_5() {
        assertThat(checkoutSolution.checkout("ZZZS"), equalTo(65));
    }
}


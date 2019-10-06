package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
	
	private Map<String, Integer> itemsPriceMap = new HashMap<>();
	private Map<String, Map<Integer, Integer>> specialOffersMap = new HashMap<>();
	
	public CheckoutSolution() {
		itemsPriceMap.put("A", 50);
		itemsPriceMap.put("B", 30);
		itemsPriceMap.put("C", 20);
		itemsPriceMap.put("D", 15);
		
		Map<Integer, Integer> noOfItemsToPriceMap = new HashMap<Integer, Integer>();
		noOfItemsToPriceMap.put(3, 130);
		specialOffersMap.put("A", noOfItemsToPriceMap);
	}
	
    public Integer checkout(String skus) {
        
    }
}




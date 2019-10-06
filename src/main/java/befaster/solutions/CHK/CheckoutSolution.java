package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
	
	private Map<Character, Integer> itemsPriceMap = new HashMap<>();
	private Map<Character, Map<Integer, Integer>> specialOffersMap = new HashMap<>();
	
	public CheckoutSolution() {
		itemsPriceMap.put('A', 50);
		itemsPriceMap.put('B', 30);
		itemsPriceMap.put('C', 20);
		itemsPriceMap.put('D', 15);
		
		Map<Integer, Integer> noOfItemsToPriceMap = new HashMap<Integer, Integer>();
		noOfItemsToPriceMap.put(3, 130);
		specialOffersMap.put('A', noOfItemsToPriceMap);
		
		noOfItemsToPriceMap = new HashMap<Integer, Integer>();
		noOfItemsToPriceMap.put(2, 45);
		specialOffersMap.put('B', noOfItemsToPriceMap);
	}
	
    public Integer checkout(String skus) {
    	char[] skusArray = skus.toCharArray();
    	
    	Map<Character, Integer> skusToCountMap;
    	try {
    		skusToCountMap = groupSkusByCount(skusArray);
    	} catch (Exception e) {
    		return -1;
    	}
    	
    	int checkoutSum = 0;
    	
    	
    	return checkoutSum;
    }
    
    private Map<Character, Integer> groupSkusByCount(char[] itemSkus) throws Exception {
    	Map<Character, Integer> skusToCountMap = new HashMap<>();
    	
    	for (char sku : itemSkus) {
    		if (!itemsPriceMap.containsKey(sku)) {
    			throw new Exception("Inexistent item");
    		}
    		
    		if (!skusToCountMap.containsKey(sku)) {
    			skusToCountMap.put(sku, 0);
    		}
    		
    		skusToCountMap.put(sku, skusToCountMap.get(sku) + 1);
    	}
    	
    	return skusToCountMap;
    }
}

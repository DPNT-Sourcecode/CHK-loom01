package befaster.solutions.CHK;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {
	
	private Map<Character, Integer> itemsPriceMap = new HashMap<>();
	private Map<Character, List<CountToPrice>> specialOffersMap = new HashMap<>();
	private Map<Character, CountToSku> specialOffersFreeItemsMap = new HashMap<>();
	
	public CheckoutSolution() {
		itemsPriceMap.put('A', 50);
		itemsPriceMap.put('B', 30);
		itemsPriceMap.put('C', 20);
		itemsPriceMap.put('D', 15);
		
		List<CountToPrice> specialOffersList = new ArrayList<>();
		specialOffersList.add(new CountToPrice(3, 130)); 
		specialOffersList.add(new CountToPrice(5, 200));
		specialOffersMap.put('A', specialOffersList);
		
		specialOffersList = new ArrayList<>();
		specialOffersList.add(new CountToPrice(2, 45)); 
		specialOffersMap.put('B', specialOffersList);
		
		specialOffersFreeItemsMap.put('E', new CountToSku(2, 'B'));
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
    	for (Map.Entry<Character, Integer> skuToCount : skusToCountMap.entrySet()) {
    		checkoutSum += getPriceForItem(skuToCount.getKey(), skuToCount.getValue(), 0);
    	}
    	
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
    
    private Map<Character, Integer> eliminateFreeItems(Map<Character, Integer> skusToCountMap) {
    	for (Map.Entry<Character, Integer> skuToCount : skusToCountMap.entrySet()) {
    		if (specialOffersFreeItemsMap.containsKey(skuToCount.getKey())) {
    			CountToSku freeItem = specialOffersFreeItemsMap.get(skuToCount.getKey());
    			skusToCountMap.get(sku)
    		}
    	}
    	
    	return skusToCountMap;
    }
    
    private int getPriceForItem(char sku, int noOfItems, int sum) {
    	if (specialOffersMap.containsKey(sku)) {
    		CountToPrice offerToApply = getOfferToApply(noOfItems, specialOffersMap.get(sku));
    		int specialOfferCount = offerToApply.getCount();
    		int specialOfferPrice = offerToApply.getPrice();
			
    		if (noOfItems >= specialOfferCount) {
				return getPriceForItem(sku, noOfItems - specialOfferCount, sum + specialOfferPrice);
			}
		}
    	
    	return sum + noOfItems * itemsPriceMap.get(sku);
    }
    
    private CountToPrice getOfferToApply(int noOfItems, List<CountToPrice> offers) {
    	CountToPrice offerToReturn = offers.get(0);
    	for (int i = 1; i < offers.size(); i++) {
    		if (noOfItems >= offers.get(i).count
    				&& offerToReturn.count < offers.get(i).count) {
    			offerToReturn = offers.get(i);
    		}
    	}
    	
    	return offerToReturn;
    }
    
    public class CountToPrice {
    	private int count;
    	private int price;
    	
    	public CountToPrice(int count, int price) {
    		this.count = count;
    		this.price = price;
    	}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}
    }
    
    public class CountToSku {
    	private int count;
    	private char sku;
    	
    	public CountToSku(int count, char sku) {
    		this.count = count;
    		this.sku = sku;
    	}
    	
    	public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public char getSku() {
			return sku;
		}

		public void setSku(char sku) {
			this.sku = sku;
		}
    }
}


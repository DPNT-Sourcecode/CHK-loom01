package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

Our price table and offers: 
+------+-------+------------------------+
| Item | Price | Special offers         |
+------+-------+------------------------+
| A    | 50    | 3A for 130, 5A for 200 |
| B    | 30    | 2B for 45              |
| C    | 20    |                        |
| D    | 15    |                        |
| E    | 40    | 2E get one B free      |
| F    | 10    | 2F get one F free      |
| G    | 20    |                        |
| H    | 10    | 5H for 45, 10H for 80  |
| I    | 35    |                        |
| J    | 60    |                        |
| K    | 80    | 2K for 150             |
| L    | 90    |                        |
| M    | 15    |                        |
| N    | 40    | 3N get one M free      |
| O    | 10    |                        |
| P    | 50    | 5P for 200             |
| Q    | 30    | 3Q for 80              |
| R    | 50    | 3R get one Q free      |
| S    | 30    |                        |
| T    | 20    |                        |
| U    | 40    | 3U get one U free      |
| V    | 50    | 2V for 90, 3V for 130  |
| W    | 20    |                        |
| X    | 90    |                        |
| Y    | 10    |                        |
| Z    | 50    |                        |
+------+-------+------------------------+

public class CheckoutSolution {
	
	private Map<Character, Integer> itemsPriceMap = new HashMap<>();
	private Map<Character, List<CountToPrice>> specialOffersMap = new HashMap<>();
	private Map<Character, CountToSku> specialOffersFreeItemsMap = new HashMap<>();
	
	public CheckoutSolution() {
		itemsPriceMap.put('A', 50);
		itemsPriceMap.put('B', 30);
		itemsPriceMap.put('C', 20);
		itemsPriceMap.put('D', 15);
		itemsPriceMap.put('E', 40);
		itemsPriceMap.put('F', 10);
		itemsPriceMap.put('G', 20);
		itemsPriceMap.put('H', 10);
		itemsPriceMap.put('I', 35);
		itemsPriceMap.put('J', 60);
		itemsPriceMap.put('K', 80);
		itemsPriceMap.put('L', 90);
		itemsPriceMap.put('M', 15);
		itemsPriceMap.put('N', 40);
		itemsPriceMap.put('O', 10);
		itemsPriceMap.put('P', 50);
		itemsPriceMap.put('Q', 30);
		itemsPriceMap.put('R', 50);
		itemsPriceMap.put('S', 30);
		itemsPriceMap.put('T', 20);
		itemsPriceMap.put('U', 40);
		itemsPriceMap.put('V', 50);
		itemsPriceMap.put('W', 20);
		itemsPriceMap.put('X', 90);
		itemsPriceMap.put('Y', 10);
		itemsPriceMap.put('Z', 50);
		
		List<CountToPrice> specialOffersList = new ArrayList<>();
		specialOffersList.add(new CountToPrice(3, 130)); 
		specialOffersList.add(new CountToPrice(5, 200));
		specialOffersMap.put('A', specialOffersList);
		
		specialOffersList = new ArrayList<>();
		specialOffersList.add(new CountToPrice(2, 45)); 
		specialOffersMap.put('B', specialOffersList);
		
		specialOffersList = new ArrayList<>();
		specialOffersList.add(new CountToPrice(5, 45)); 
		specialOffersList.add(new CountToPrice(10, 80)); 
		specialOffersMap.put('H', specialOffersList);
		
		specialOffersList = new ArrayList<>();
		specialOffersList.add(new CountToPrice(2, 150)); 
		specialOffersMap.put('K', specialOffersList);
		
		specialOffersFreeItemsMap.put('E', new CountToSku(2, 'B'));
		specialOffersFreeItemsMap.put('F', new CountToSku(2, 'F'));
	}
	
    public Integer checkout(String skus) {
    	char[] skusArray = skus.toCharArray();
    	
    	Map<Character, Integer> skusToCountMap;
    	try {
    		skusToCountMap = groupSkusByCount(skusArray);
    	} catch (Exception e) {
    		return -1;
    	}
    	
    	eliminateFreeItems(skusToCountMap);
    	
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
    
    private void eliminateFreeItems(Map<Character, Integer> items) {
    	for (Map.Entry<Character, Integer> item : items.entrySet()) {
    		if (specialOffersFreeItemsMap.containsKey(item.getKey())) {
    			CountToSku freeItem = specialOffersFreeItemsMap.get(item.getKey());
    			
    			if (items.containsKey(freeItem.sku)) {
    				int itemsToEliminate = getNoOfItemsToEliminate(item, freeItem);
    				items.put(freeItem.sku, items.get(freeItem.sku) - itemsToEliminate);
    			}
    		}
    	}
    }
    
    private int getNoOfItemsToEliminate(Map.Entry<Character, Integer> item, CountToSku offer) {
    	if (offer.sku != item.getKey()) {
			return item.getValue() / offer.count;
		}
    	
    	int itemsToEliminate = 0;
    	int itemCount = item.getValue();
    	while (itemCount >= offer.count + 1) {
    		itemsToEliminate += 1;
    		itemCount -= (offer.count + 1);
    	}
    	
    	return itemsToEliminate;
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





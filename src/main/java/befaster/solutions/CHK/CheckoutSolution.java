package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {
	
	private Map<Character, Integer> itemsPriceMap = new HashMap<>();
	private Map<Character, List<CountToPrice>> specialOffersMap = new HashMap<>();
	private Map<Character, CountToSku> specialOffersFreeItemsMap = new HashMap<>();
	private List<GroupOffer> groupOfferList = new ArrayList<>();
	
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
		itemsPriceMap.put('S', 20);
		itemsPriceMap.put('T', 20);
		itemsPriceMap.put('U', 40);
		itemsPriceMap.put('V', 50);
		itemsPriceMap.put('W', 20);
		itemsPriceMap.put('X', 17);
		itemsPriceMap.put('Y', 20);
		itemsPriceMap.put('Z', 21);
		
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
		
		specialOffersList = new ArrayList<>();
		specialOffersList.add(new CountToPrice(5, 200)); 
		specialOffersMap.put('P', specialOffersList);
		
		specialOffersList = new ArrayList<>();
		specialOffersList.add(new CountToPrice(3, 80)); 
		specialOffersMap.put('Q', specialOffersList);
		
		specialOffersList = new ArrayList<>();
		specialOffersList.add(new CountToPrice(2, 90)); 
		specialOffersList.add(new CountToPrice(3, 130)); 
		specialOffersMap.put('V', specialOffersList);
		
		specialOffersFreeItemsMap.put('E', new CountToSku(2, 'B'));
		specialOffersFreeItemsMap.put('F', new CountToSku(2, 'F'));
		specialOffersFreeItemsMap.put('N', new CountToSku(3, 'M'));
		specialOffersFreeItemsMap.put('R', new CountToSku(3, 'Q'));
		specialOffersFreeItemsMap.put('U', new CountToSku(3, 'U'));
		
		List<Character> groupOfferSkus = new ArrayList<>();
		groupOfferSkus.add('S');
		groupOfferSkus.add('T');
		groupOfferSkus.add('X');
		groupOfferSkus.add('Y');
		groupOfferSkus.add('Z');
		groupOfferList.add(new GroupOffer(3, 45, groupOfferSkus));
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
    	
    	/* try to apply group offers */
    	for (GroupOffer groupOffer : groupOfferList) {
	    	if (doesApplyGroupOffer(skusToCountMap, groupOffer)) {
	    		checkoutSum += groupOffer.getPrice();
	    		applyGroupOffer(skusToCountMap, groupOffer);
	    	}
    	}
    	
    	for (Map.Entry<Character, Integer> skuToCount : skusToCountMap.entrySet()) {
    		checkoutSum += getPriceForItem(skuToCount.getKey(), skuToCount.getValue(), 0);
    	}
    	
    	return checkoutSum;
    }
    
    private void applyGroupOffer(Map<Character, Integer> skusToCountMap, GroupOffer groupOffer) {
    	int totalCountOfProductsApplied = 0;
		for (char sku : groupOffer.getSkus()) {
			if (totalCountOfProductsApplied >= groupOffer.count) {
				return;
			}
			
			if (skusToCountMap.get(sku) > 0) {
				int countToApply =
						groupOffer.count - totalCountOfProductsApplied >= skusToCountMap.get(sku) ?
								skusToCountMap.get(sku)
								: groupOffer.count - totalCountOfProductsApplied;

				skusToCountMap.put(sku, skusToCountMap.get(sku) - countToApply);
				totalCountOfProductsApplied += countToApply;
			}
		}
		
	}

	private boolean doesApplyGroupOffer(Map<Character, Integer> productsMap, GroupOffer groupOffer) {
		int totalCountOfProducts = 0;
		for (char sku : groupOffer.getSkus()) {
			if (totalCountOfProducts >= groupOffer.count) {
				return true;
			}
			
			if (productsMap.containsKey(sku)) {
				totalCountOfProducts += productsMap.get(sku);
			}
		}
		
		return false;
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
    
    public class GroupOffer {
    	private int count;
    	private List<Character> skus;
    	private int price;
    	
    	public GroupOffer(int count, int price, List<Character> skus) {
    		this.count = count;
    		this.setSkus(skus);
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

		public List<Character> getSkus() {
			return skus;
		}

		public void setSkus(List<Character> skus) {
			this.skus = skus;
		}
    }
}








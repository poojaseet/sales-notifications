package com.pooja.snm.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.pooja.snm.Operation;
import com.pooja.snm.SalesUtils;
import com.pooja.snm.core.MessageProcessor;
import com.pooja.snm.core.MessageType;
import com.pooja.snm.core.ProcessCache;
import com.pooja.snm.model.Adjustment;
import com.pooja.snm.model.Product;
import com.pooja.snm.model.Sale;

public class MsgProcessorThreeImpl implements MessageProcessor {

	ProcessCache processCache;

	public MsgProcessorThreeImpl(ProcessCache processCache) {
		this.processCache = processCache;
	}

	public void process(String message) {
		List<String> tokens = SalesUtils.getTokens(MessageType.THREE.getPattern(), message);
		Operation operation = Operation.valueOf(tokens.get(1).toUpperCase());

		String productName = tokens.get(3);
		long saleValue = Long.valueOf(tokens.get(2));
		Map<String, List<Sale>> sales = processCache.getSales();
		Map<String, List<Adjustment>> adjustments = processCache.getAdjustments();

		if (sales.containsKey(productName)) {
			List<Sale> salesOfProduct = sales.get(productName);

			getAdjustments(operation, saleValue, salesOfProduct);

			List<Adjustment> productAdjustments = adjustments.get(productName);
			if (productAdjustments == null) {
				productAdjustments = new LinkedList<Adjustment>();
			}
			Adjustment adjustment = new Adjustment(saleValue, operation, new Product(productName));
			productAdjustments.add(adjustment);
			adjustments.put(productName, productAdjustments);

		}

	}

	private void getAdjustments(Operation operation, long saleValue, List<Sale> salesOfProduct) {
		switch (operation) {
		case ADD:
			salesOfProduct.forEach(sale -> sale.setProductValue(sale.getProductValue() + saleValue));			
			break;

		case SUBTRACT:
			salesOfProduct.forEach(sale -> sale.setProductValue(sale.getProductValue() - saleValue));
			break;
		case MULTIPLY:
			salesOfProduct.forEach(sale -> sale.setProductValue(Math.round(sale.getProductValue() * saleValue)));

			break;
		default:

		}
	}

}

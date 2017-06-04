package com.pooja.snm.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.pooja.snm.SalesUtils;
import com.pooja.snm.core.MessageProcessor;
import com.pooja.snm.core.MessageType;
import com.pooja.snm.core.ProcessCache;
import com.pooja.snm.model.Product;
import com.pooja.snm.model.Sale;

public class MsgProcessorOneImpl implements MessageProcessor {
	ProcessCache processCache;

	public MsgProcessorOneImpl(ProcessCache processCache) {
		this.processCache = processCache;
	}

	public void process(String message) {
		List<String> tokens = SalesUtils.getTokens(MessageType.ONE.getPattern(), message);
		String productName = tokens.get(1);
		long saleValue = Long.valueOf(tokens.get(2));
		Map<String, List<Sale>> sales = processCache.getSales();

		
		List<Sale> salesOfproduct = null;

		if (sales.containsKey(productName)) {
			salesOfproduct = sales.get(productName);

		} else {
			salesOfproduct = new LinkedList<Sale>();

		}
		salesOfproduct.add(new Sale(new Product(productName), saleValue, 1));
		sales.put(productName, salesOfproduct);
        
	}

}

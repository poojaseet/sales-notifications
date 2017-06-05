package com.pooja.snm.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.pooja.snm.SalesUtils;
import com.pooja.snm.core.MessageProcessor;
import com.pooja.snm.core.MessageType;
import com.pooja.snm.core.ProcessCache;
import com.pooja.snm.model.Product;
import com.pooja.snm.model.Sale;

public class MsgProcessorTwoImpl implements MessageProcessor {

	ProcessCache processCache;

	public MsgProcessorTwoImpl(ProcessCache processCache){
		this. processCache = processCache;
	}

	public void process(String message) {
		List<String> tokens = SalesUtils.getTokens(MessageType.TWO.getPattern(), message);
		if (CollectionUtils.isEmpty(tokens)) {
			return;
		}
		String productName = tokens.get(2);
		int quantity = Integer.valueOf(tokens.get(1));
		long saleValue = Long.valueOf(tokens.get(3));
		Map<String, List<Sale>> sales = processCache.getSales();

		List<Sale> salesOfproduct = null;

		if (sales.containsKey(productName)) {
			salesOfproduct = sales.get(productName);

		} else {
			salesOfproduct = new LinkedList<Sale>();
		}

		salesOfproduct.add(new Sale(new Product(productName), saleValue, quantity));
		sales.put(productName, salesOfproduct);


	}

}

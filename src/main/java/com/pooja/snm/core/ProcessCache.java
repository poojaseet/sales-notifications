package com.pooja.snm.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pooja.snm.model.Adjustment;
import com.pooja.snm.model.Sale;

public class ProcessCache {

	private final  Map<String, List<Sale>> sales;

	private  final Map<String, List<Adjustment>> adjustments;
		
	public ProcessCache(){
		sales = new HashMap<String, List<Sale>>();
		adjustments = new HashMap<String, List<Adjustment>>();
	}

	public Map<String, List<Sale>> getSales() {
		return sales;
	}

	public Map<String, List<Adjustment>> getAdjustments() {
		return adjustments;
	}
	

	
	
	
	

}

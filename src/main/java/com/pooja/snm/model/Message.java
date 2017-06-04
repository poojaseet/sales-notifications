package com.pooja.snm.model;

import java.util.List;

public interface Message {
	
	void process(String msg,List<String> salesList);

}

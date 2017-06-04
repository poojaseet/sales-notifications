package com.pooja.snm.core.report;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.commons.collections4.CollectionUtils;

import com.pooja.snm.SalesUtils;
import com.pooja.snm.core.ProcessCache;
import com.pooja.snm.model.Adjustment;
import com.pooja.snm.model.Sale;

public class ReportGenerator {

	private static ProcessCache processCache = ProcessCache.getInstance();

	private PrintStream outputChannel;

	public ReportGenerator(PrintStream output) {
		outputChannel = output;
	}

	public void generateIntervalReport() {
		Map<String, List<Sale>> sales = processCache.getSales();
		outputChannel.println("Interval Report generated");
		for (Map.Entry<String, List<Sale>> entry : CollectionUtils.emptyIfNull(sales.entrySet())) {
			String product = entry.getKey();

			long totalQuantity = entry.getValue().stream().mapToLong(x -> x.getQuantity()).sum();
			long totalValue = entry.getValue().stream().mapToLong(x -> x.getProductValue() * x.getQuantity()).sum();
			StringJoiner stringJoiner = new StringJoiner(SalesUtils.DELIMITER);
			stringJoiner.add(product);
			stringJoiner.add(String.valueOf(totalQuantity));
			stringJoiner.add(String.valueOf(totalValue));
			outputChannel.println(stringJoiner.toString());
		}

	}

	public void generateFinalReport() {
		Map<String, List<Adjustment>> adjustments = processCache.getAdjustments();
		outputChannel.println("Pausing to generate report");
		for (Map.Entry<String, List<Adjustment>> entry : adjustments.entrySet()) {
			String product = entry.getKey();
			StringJoiner stringJoiner = new StringJoiner(SalesUtils.DELIMITER);
			stringJoiner.add(product);
			for (Adjustment productAdjustment : CollectionUtils.emptyIfNull(entry.getValue())) {

				stringJoiner.add(String.valueOf(productAdjustment.getValue()));
				stringJoiner.add(productAdjustment.getOp().getOperation());
			}
			outputChannel.println(stringJoiner.toString());

		}

	}

}

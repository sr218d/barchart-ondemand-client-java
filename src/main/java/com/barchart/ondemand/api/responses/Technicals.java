package com.barchart.ondemand.api.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Technicals extends ResponseBase {

	@JsonProperty("results")
	private final List<Technical> results = new ArrayList<Technical>();

	public List<Technical> all() {
		if (results == null) {
			return new ArrayList<Technical>();
		}
		return results;
	}

	public Technical bySymbol(final String symbol) {
		if (symbol == null) {
			return null;
		}

		for (Technical p : all()) {
			if (p.getSymbol() != null && p.getSymbol().equalsIgnoreCase(symbol)) {
				return p;
			}
		}

		return null;
	}

}

package com.barchart.ondemand.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.barchart.ondemand.api.responses.EquitiesOptions;

public class EquitiesOptionsRequest implements OnDemandRequest<EquitiesOptions> {

	public enum EquitiesOptionsRequestType {
		CALLS, PUTS, ALL;

		public static String getValue(EquitiesOptionsRequestType field) {
			if (field == null) {
				return "";
			}

			switch (field) {
			case CALLS:
				return "Call";
			case PUTS:
				return "Puts";
			case ALL:
				return "";
			default:
				return "";
			}
		}
	}

	public enum EquitiesOptionsRequestOptionType {
		MONTHLY, WEEKLY, ALL;

		public static String getValue(EquitiesOptionsRequestOptionType field) {
			if (field == null) {
				return "";
			}

			switch (field) {
			case MONTHLY:
				return "Monthly";
			case WEEKLY:
				return "Weekly";
			case ALL:
				return "";
			default:
				return "";
			}
		}
	}

	private final String symbols;
	private final String fields;

	private final Map<String, Object> params = new HashMap<String, Object>();

	private EquitiesOptionsRequest(final Builder b) {

		this.symbols = StringUtils.join(b.underlying_symbols, ",");
		this.fields = StringUtils.join(b.fields, ",");

		final String type = EquitiesOptionsRequestType.getValue(b.type);
		final String optionType = EquitiesOptionsRequestOptionType.getValue(b.optionType);

		if (!type.isEmpty()) {
			params.put("type", b.type);
		}

		if (!optionType.isEmpty()) {
			params.put("optionType", b.optionType);
		}

	}

	@Override
	public String endpoint() {
		return "getEquityOptionsIntraday.json";
	}

	@Override
	public String name() {
		return "Equities Options";
	}

	@Override
	public Map<String, Object> parameters() {

		if (!symbols.isEmpty()) {
			params.put("underlying_symbols", symbols);
		}

		if (!fields.isEmpty()) {
			params.put("fields", fields);
		}

		return params;
	}

	public static class Builder {

		private String[] underlying_symbols = new String[] {};
		private EquitiesOptionsRequestType type;
		private EquitiesOptionsRequestOptionType optionType;
		private String[] fields = new String[] { "ask", "bid", "volatility", "theta", "gamma", "vega", "delta", "rho",
				"bidSize", "askSize", "bidDate", "askDate", "settlement", "lastTradeDate", "openInterest" };

		public Builder symbols(final String[] symbols) {
			this.underlying_symbols = symbols;
			return this;
		}

		public Builder fields(final String[] fields) {
			this.fields = fields;
			return this;
		}

		public OnDemandRequest<EquitiesOptions> build() {
			return new EquitiesOptionsRequest(this);
		}
	}

	@Override
	public Class<EquitiesOptions> responseType() {
		return EquitiesOptions.class;
	}

}

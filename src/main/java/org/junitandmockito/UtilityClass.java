package org.junitandmockito;

import java.math.BigDecimal;

public class UtilityClass {
	static int staticMethod(long value) {
		// Some complex logic is done here...
		throw new RuntimeException("I dont want to be executed. I will anyway be mocked out.");
	}

	private boolean firstParameter;
	private String secondParameter;
	private BigDecimal thirdParameter;

	public UtilityClass() {
		this(false, "DEBIT", new BigDecimal("19.00"));
	}

	public UtilityClass(String secondParameter, BigDecimal thirdParameter) {
		this(false, secondParameter, thirdParameter);
	}

	public UtilityClass(boolean firstParameter, String secondParameter, BigDecimal thirdParameter) {
		this.firstParameter = firstParameter;
		this.secondParameter = secondParameter;
		this.thirdParameter = thirdParameter;
	}

	public BigDecimal dummyMethod(String firstArgument, BigDecimal secondArgument) {
		// any arbitrary implementation
		return BigDecimal.ZERO;
	}
}
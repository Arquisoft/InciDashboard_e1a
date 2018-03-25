package com.uniovi.filter;

import javax.persistence.Embeddable;

/**
 * Filter with three kind of operations : ">", "==", "contains"
 * 
 * @author einye
 *
 */

@Embeddable
public class Filter {

	private String property, value;

	private FilterOperation operation;

	public Filter(String property, String value, FilterOperation operation) {
		super();
		this.property = property;
		this.value = value;
		this.operation = operation;
	}

	public boolean filter(String property, String value) {
		if (!property.equalsIgnoreCase(this.property))
			return false;
		switch (operation) {
		case CONTAINS:
			return contains(value);
		case GREATER_THAN:
			return gt(value);
		case EQUAL:
			return equals(value);
		default:
			return false;
		}
	}

	public boolean filter(String value) {
		switch (operation) {
		case CONTAINS:
			return contains(value);
		case GREATER_THAN:
			return gt(value);
		case EQUAL:
			return equals(value);
		default:
			return false;
		}
	}

	boolean contains(String value) {
		return value.toLowerCase().contains(this.value.toLowerCase());
	}

	boolean gt(String value) {
		return value.compareTo(this.value) > 0;
	}

	boolean equals(String value) {
		return value.compareTo(this.value) == 0;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FilterOperation getOperation() {
		return operation;
	}

	public void setOperation(FilterOperation operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "Filter [property=" + property + ", operation=" + operation + " value=" + value + "]";
	}

}

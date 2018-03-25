package com.uniovi.filter;

import javax.persistence.Embeddable;

@Embeddable
public enum FilterOperation {
	
	GREATER_THAN,
	EQUAL,
	CONTAINS

}

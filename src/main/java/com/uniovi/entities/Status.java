package com.uniovi.entities;

import javax.persistence.Embeddable;

@Embeddable
public enum Status {
	OPENED,
	CLOSED,
	ASSIGNED,
	CANCELLED
}

package com.uniovi.entities;

import javax.persistence.Embeddable;

 
public enum Status {
	OPENED,
	CLOSED,
	ASSIGNED,
	CANCELLED,
	IN_PROCESS
}

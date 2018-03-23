package entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Incidence {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Operator operator;

	private String name;
	private String description;
		
	private Map<String, Object> fields;

	@Enumerated(EnumType.STRING)
	private Status status = Status.OPEN;
	
	private Date expirationDate;


	
	private Incidence() {}



	public Incidence(Operator operator, String incidenceName, String description, Date expirationDate) {
		super();
		this.operator = operator;
		this.name = incidenceName;
		this.description = description;
		this.expirationDate = expirationDate;
	}



	@Override
	public String toString() {
		return "Incidence [operator=" + operator + ", incidenceName=" + name + ", description=" + description
				+ ", fields=" + fields + ", status=" + status + ", expirationDate=" + expirationDate + "]";
	}
	
	
	
	

}

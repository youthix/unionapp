package org.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "payratedetail")
public class PayrateBO  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "paydate")
	private Date paydate;
	
	@Column(name = "detail")
	@Type(type="text")
	private String detail;
	
	@Column(name = "creator")
	private String creator;
	
	@Column(name = "status")
	private String status;
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payid")
	private Integer payid;
}

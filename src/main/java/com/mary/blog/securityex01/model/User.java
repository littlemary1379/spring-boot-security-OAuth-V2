package com.mary.blog.securityex01.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM-Object Relation Mapping

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
	
	@Id //primary Key annotation
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	private String username;
	private String password;
	private String email;
	@CreationTimestamp
	private Timestamp createDate;
	private String role;
}

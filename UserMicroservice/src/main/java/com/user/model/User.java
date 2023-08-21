package com.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="userInfo")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int Id;
	private String username;
	private String email;
	private String password;
}

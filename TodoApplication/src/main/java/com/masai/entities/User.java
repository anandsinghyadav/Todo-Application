package com.masai.entities;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(nullable = false, length = 30)
	@NotBlank(message="Name can't be blank")
	private String name;
	@Size(min=7, max=20, message="username must between 7 and 20 digits")
	@Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]{2,4}$", message="our input doesn't match the required pattern:^[a-bA-Z0-9].@[0-9]{4}.[a-zA-Z0-9]{2,4}$")
	@Column(unique = true, nullable = false)
	private String username;
	private String password;
	@Column(name="dob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	@Column(length = 10, unique = true, nullable = false)
	private String mobileNumber;

	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Todos> todoList;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private Session session;
	

}

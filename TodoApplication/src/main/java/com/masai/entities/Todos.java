package com.masai.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Todos {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer todoId;
	private String work;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime workTime;
	@Enumerated(EnumType.STRING)
	private WorkStatus  status;
    
	@ManyToOne
	@JoinColumn(name= "user_id")
	@JsonIgnore
	private User user;
}

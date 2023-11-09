package com.masai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entities.Todos;
import com.masai.entities.User;

@Repository
public interface TodosRepository extends JpaRepository<Todos, Integer> {
	
	public List<Todos> findByUser(User user);

}

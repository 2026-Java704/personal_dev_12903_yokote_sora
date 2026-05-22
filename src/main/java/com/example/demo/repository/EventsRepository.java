package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Events;

public interface EventsRepository extends JpaRepository<Events, Integer> {

	List<Events> findByUserIdOrderByIdAsc(Integer id);
}
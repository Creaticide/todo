package com.testi.TodoApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.testi.TodoApp.entity.Priority;

public interface PriorityRepository extends CrudRepository<Priority, Long> {

}

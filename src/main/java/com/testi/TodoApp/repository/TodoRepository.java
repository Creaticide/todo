package com.testi.TodoApp.repository;

import java.sql.Date;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.testi.TodoApp.entity.Todo;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {
	public Iterable<Todo> findByDeadlineLessThan(Date date, Sort sort);
	public Iterable<Todo> findByDeadlineGreaterThanEqual(Date date, Sort sort);
}

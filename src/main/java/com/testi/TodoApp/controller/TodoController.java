package com.testi.TodoApp.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testi.TodoApp.entity.Todo;
import com.testi.TodoApp.repository.PriorityRepository;
import com.testi.TodoApp.repository.TodoRepository;

@Controller
public class TodoController {
	
	private TodoRepository todoRepository;
	private PriorityRepository priorityRepository;
	
	public TodoController(TodoRepository todoRepository, PriorityRepository priorityRepository) {
		this.todoRepository = todoRepository;
		this.priorityRepository = priorityRepository;
	}
	
	@RequestMapping("/")
	public String todoList(Model model) {
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		Sort sort = new Sort(Direction.ASC, "deadline");
		model.addAttribute("todos", this.todoRepository.findByDeadlineGreaterThanEqual(date, sort));
		return "todo_list";
	}
	
	@RequestMapping("/todos/expired")
	public String listExpired(Model model) {
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		Sort sort = new Sort(Direction.ASC, "deadline");
		model.addAttribute("todos", this.todoRepository.findByDeadlineLessThan(date, sort));
		return "todo_expired";
	}
	
	@RequestMapping("/todos/add")
	public String addForm(Model model) {
		model.addAttribute("todo", new Todo());
		model.addAttribute("priorities", this.priorityRepository.findAll());
		return "add_todo";
	}
	
	@PostMapping("/todos/add")
	public String addTodo(@ModelAttribute Todo todo) {
		this.todoRepository.save(todo);
		return "redirect:/";
	}
	
	@RequestMapping("/todos/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		model.addAttribute("todo", this.todoRepository.findById(id).get());
		model.addAttribute("priorities", this.priorityRepository.findAll());
		return "edit_todo";
	}
	
	@PostMapping("/todos/{id}/edit")
	public String editTodo(@ModelAttribute Todo todo) {
		this.todoRepository.save(todo);
		return "redirect:/";
	}
	
	@RequestMapping("/todos/{id}/done")
	public String setDone(@PathVariable Long id, Model model) {
		Optional<Todo> todo = this.todoRepository.findById(id);
		if (todo.isPresent()) {
			Todo t = todo.get();
			if (t.getIsDone()) {
				t.setIsDone(false);
			} else {
				t.setIsDone(true);				
			}
			this.todoRepository.save(t);
		}
		return "redirect:/";
	}
	
	@RequestMapping("/todos/{id}/delete")
	public String deleteTodo(@PathVariable Long id) {
		this.todoRepository.deleteById(id);
		return "redirect:/";
	}
	
}

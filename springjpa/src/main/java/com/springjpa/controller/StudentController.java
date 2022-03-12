package com.springjpa.controller;

import javax.validation.Valid;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.springjpa.entities.Student;
import com.springjpa.exception.RecordNotFoundException;
import com.springjpa.repository.StudentRepository;

//import antlr.collections.List;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentRepository repository;
	@PostMapping("/create-student")
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student std)
	{
		Student student=repository.save(std);
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}
	@GetMapping("/student-list")
	// @RequestMapping(value="create-student",method="POST")
	public List<Student> getAllStudents() {
		return (List<Student>) repository.findAll();
	}
	@GetMapping("/student-list/{stid}")
	public ResponseEntity<Student> getStudentById(@PathVariable int stid) throws RecordNotFoundException {
		if(repository.findById(stid).isPresent()) {
		Student student = repository.findById(stid).get();
		return new ResponseEntity<Student>(student,HttpStatus.OK);
		}
		else
		{
			throw new RecordNotFoundException("Student with Id: " + stid + " doesn't exist!!");
		}
		
	}

	@PutMapping("/update-student")
	public Student updateStudent(@RequestBody Student obj) {
		return repository.save(obj);
	}

	@DeleteMapping("/delete-student/{stid}")
	public void updateStudent(@PathVariable int stid) {

		 repository.deleteById(stid);
	}
}

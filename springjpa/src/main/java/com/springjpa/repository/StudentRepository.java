package com.springjpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.springjpa.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}

package com.java.service;

import com.java.bean.Person;
import org.springframework.data.domain.Page;

public interface PersonService {

    long count();

    Person save(Person person);

    void delete(Person person);

    Iterable<Person> getAll();

    java.util.List<Person> getByName(String name);

    Page<Person> pageQuery(Integer pageNo, Integer pageSize, String kw);

}

package com.java.service.impl;

import com.alibaba.fastjson.JSON;
import com.java.bean.Person;
import com.java.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceImplTest {

    @Autowired
    public PersonService personService;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void contextLoads() {
//        Person person = new Person("123456", "zlc", "西湖区", 18, "男", "hangzhou");
//        Person save = personService.save(person);
//        System.out.printf(JSON.toJSONString(save));
        Person person1 = new Person("98712", "张三", "余杭区", 11, "", "bj");
        IndexQuery indexQuery = new IndexQueryBuilder().withObject(person1).build();
        String index = elasticsearchTemplate.index(indexQuery);
        System.out.printf(index);
    }

    /**
     * 查询操作
     */
    @Test
    public void query() {
        Integer pageNo = 0;
        Integer pageSize = 5;
        String key = "zlc";
        Page<Person> personPage = personService.pageQuery(pageNo, pageSize, key);
        List<Person> content = personPage.getContent();
        content.forEach(x -> System.out.println(JSON.toJSONString(x)));
    }

    @Test
    public void count() {
        long count = personService.count();
        System.out.printf(count+"");
    }

    @Test
    public void save() {
        Person person = new Person();
        person.setAddress("大兴");
        person.setAge(10);
        person.setCity("Bj");
        person.setName("ZS");
        person.setSex("F");
        person.setSkuId("001");
        Person savePerson = personService.save(person);
        System.out.printf(JSON.toJSONString(savePerson));
    }

    @Test
    public void delete() {
        Person person = new Person();
        person.setAddress("大兴");
        person.setAge(10);
        person.setCity("Bj");
        person.setName("ZS");
        person.setSex("F");
        person.setSkuId("001");
        personService.delete(person);
    }

    @Test
    public void getAll() {
        Iterable<Person> iterable = personService.getAll();
        iterable.forEach(x -> System.out.println(JSON.toJSONString(x)));
    }

    @Test
    public void getByName() {
        List<Person> zlc = personService.getByName("zlc");
        zlc.forEach(x -> System.out.println(JSON.toJSONString(x)));
    }

    @Test
    public void matchQueryTest() {
    }

    @Test
    public void likeQueryTest() {
    }

    @Test
    public void rangeQueryTest() {
    }

    @Test
    public void boolQueryTest() {
    }

    @Test
    public void aggregationTest() {
    }

    @Test
    public void pageQuery() {
    }
}
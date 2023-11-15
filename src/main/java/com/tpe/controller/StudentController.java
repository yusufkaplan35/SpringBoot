package com.tpe.controller;


import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
        ***** SORU-1 :  @Controller yerine , @Component kullanirsam ne olur ??
        **    CEVAP-1 : Dispatcher , @Controller ile annote edilmis sınıfları tarar ve
        bunların içindeki @RequestMapping annotationlari algilamaya calisir. Dikkat :
        @Component ile annote edilen siniflar taranmayacaktir..

        Ayrica  @RequestMapping'i yalnızca sınıfları @Controller ile annote edilmis olan
        methodlar üzerinde/içinde kullanabiliriz ve @Component, @Service, @Repository vb.
        ile ÇALIŞMAZ…

        ***** SORU-2 : @RestController ile @Controller arasindaki fark nedir ??
        **   CEVAP-2 : @Controller, Spring MVC framework'ünün bir parçasıdır.genellikle HTML
        sayfalarının görüntülenmesi veya yönlendirilmesi gibi işlevleri gerçekleştirmek
        üzere kullanılır.
                       @RestController annotation'ı, @Controller'dan türetilmiştir ve RESTful
         web servisleri sağlamak için kullanılır.Bir sınıfın üzerine konulduğunda, tüm
         metodlarının HTTP taleplerine JSON gibi formatlarda cevap vermesini sağlar.

         ***** SORU-3 : Controller'dan direk Repo ya gecebilir miyim
         **   CEVAP-3: HAYIR, BusinessLogic ( kontrol ) katmani olan Service'i atlamamam gerekir.
 */

@RestController //Rest api için
//@Component sadece bean yapar ve handler mapping control sınıfına gönderemez
@RequestMapping("/students") //http://localhost:8080/students
public class StudentController {

    @Autowired
    private StudentService studentService;

    //!!! Get ALL STUDENTS
    @GetMapping //http://localhost:8080/students + GET
    public ResponseEntity<List<Student>> getAll (){  // ResponseEntity: status ile birlikte paket halinde cevap gönderir

        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(students); // 200 HTTP Status Code

    }

    // !!! Create new Student
    @PostMapping  // http://localhost:8080/students + POST + JSON
    public ResponseEntity<Map<String,String>> createStudent(@Valid @RequestBody Student student){
        // @Valid : parametreler valid mi kontrol eder, bu örenekte Student
        //objesi oluşturmak için  gönderilen fieldlar yani
        //name gibi özellikler düzgün set edilmiş mi ona bakar.
        // @RequestBody = gelen  requestin bodysindeki bilgiyi ,
        //Student objesine map edilmesini sağlıyor.

        studentService.createStudent(student);


        Map<String,String> map = new HashMap<>();
        map.put("message","Student is created successfully");
        map.put("status","true");

        return new ResponseEntity<>(map, HttpStatus.CREATED); // map + 201 Http Status Kod

    }

    //!!! Not: getStudentById RequestParam ******* -->birden çok parametreye göre istek olursa requestparam
    @GetMapping("/query") //http://localhost:8080/students/query?id=1
    public ResponseEntity<Student> getStudent(@RequestParam("id") Long id){
        Student student = studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }

    // Not: getStudentById PathVariable  -->tek parametre ise path
    @GetMapping("/{id}") //http://localhost:8080/students/1
    public ResponseEntity<Student> getStudentWithPath(@PathVariable("id") Long id){

        return ResponseEntity.ok(studentService.findStudent(id));

    }

    // Not deleteStudentById
    @DeleteMapping("/{id}") //http://localhost:8080/students/id
    public ResponseEntity<String> deleteStudentById(@PathVariable("id")Long id){
        studentService.deleteStudent(id);

        String message="Student is deleted successfully";
        return new ResponseEntity<>(message,HttpStatus.OK);
        // return  ResponseEntity.ok(message);
    }





}

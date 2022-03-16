//package com.rjdejesus.ems.restfulwebservice.EMSRestService.employee;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//@Configuration
//public class EmployeeConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
//        return args -> {
//            Employee mike = new Employee(
//                    "Hunt",
//                    "Mike",
//                    "mike.hunt@gmail.com",
//                    123_456_78L,
//                    LocalDate.of(2005, Month.AUGUST, 23)
//            );
//
//            Employee ethan = new Employee(
//                    "Gold",
//                    "Ethan",
//                    "ethan@gmail.com",
//                    987_654_32L,
//                    LocalDate.of(2004, Month.APRIL, 2)
//            );
//
//            Employee lucas = new Employee(
//                    "Brown",
//                    "Lucas",
//                    "lucas.brown@gmail.com",
//                    512_123_65L,
//                    LocalDate.of(2019, Month.JULY, 17)
//            );
//
//            Employee francine = new Employee(
//                    "Hill",
//                    "Francine",
//                    "francine.hill@gmail.com",
//                    123_456_78L,
//                    LocalDate.of(2021, Month.DECEMBER, 1)
//            );
//
//            Employee john = new Employee(
//                    "Doe",
//                    "John",
//                    "john.doe@gmail.com",
//                    123_456_78L,
//                    LocalDate.of(2015, Month.DECEMBER, 8)
//            );
//
//            Employee george = new Employee(
//                    "Dafo",
//                    "George",
//                    "dafo.george@gmail.com",
//                    123_656_78L,
//                    LocalDate.of(2018, Month.APRIL, 2)
//            );
//
//            Employee william = new Employee(
//                    "Green",
//                    "William",
//                    "william.green@gmail.com",
//                    888_321_99L,
//                    LocalDate.of(2017, Month.SEPTEMBER, 22)
//            );
//
//            Employee alexa = new Employee(
//                    "Jones",
//                    "Alexa",
//                    "alexa.jones@gmail.com",
//                    723_129_86L,
//                    LocalDate.of(2018, Month.NOVEMBER, 30)
//            );
//
//            Employee ezio = new Employee(
//                    "Auditore",
//                    "Ezio",
//                    "ezio.auditore@gmail.com",
//                    812_331_90L,
//                    LocalDate.of(2013, Month.NOVEMBER, 11)
//            );
//
//            repository.saveAll(
//                    List.of(mike, ethan, lucas, francine, john,
//                            george, william, alexa, ezio)
//            );
//        };
//    }
//}

package com.rest.webservices.restfulwebservices.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    // using URI
//    @GetMapping("/v1/person")
//    public PersonV1 getFirstVersionOfPersonV1() {
//        return new PersonV1("Bob Charlie");
//    }
//
//    @GetMapping("/v2/person")
//    public PersonV2 getFirstVersionOfPersonV2() {
//        return new PersonV2(new Name("Bob", "Charlie"));
//    }

    // using params
//    @GetMapping(path = "/person", params = "version=1")
//    public PersonV1 getFirstVersionOfPersonV1() {
//        return new PersonV1("Bob Charlie");
//    }
//
//    @GetMapping(path = "/person", params = "version=2")
//    public PersonV2 getFirstVersionOfPersonV2() {
//        return new PersonV2(new Name("Bob", "Charlie"));
//    }


    // using headers
    @GetMapping(path = "/person", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", params = "X-API-VERSION=2")
    public PersonV2 getFirstVersionOfPersonV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}

package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue doFiltering() {
        SomeBean someBean = new SomeBean("value1", "value2");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue doFilteringList() {
        List<SomeBean> beanList = new ArrayList<>();
        beanList.add(new SomeBean("value1", "value2"));
        beanList.add(new SomeBean("value3", "value4"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(beanList);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

//    @GetMapping("/filtering")
//    public MappingJacksonValue filtering() {
//        SomeBean someBean = new SomeBean("value1", "value2", "value3");
//        MappingJacksonValue mappingJacksonValue = filterBeans(someBean, "field1", "field2");
//
//        return mappingJacksonValue;
//    }
//
//    @GetMapping("/filtering-list")
//    public MappingJacksonValue filteringList() {
//        List<SomeBean> beans = Arrays.asList(new SomeBean("value1", "value2", "value3"),
//                new SomeBean("value4", "value5", "value6"));
//        MappingJacksonValue mappingJacksonValue = filterBeans(beans, "field1", "field3");
//
//        return mappingJacksonValue;
//    }
//
//    private <T> MappingJacksonValue filterBeans(T beans, String... propertyArrays) {
//        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(beans);
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(propertyArrays);
//        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
//        mappingJacksonValue.setFilters(filters);
//
//        return mappingJacksonValue;
//    }
//}
}

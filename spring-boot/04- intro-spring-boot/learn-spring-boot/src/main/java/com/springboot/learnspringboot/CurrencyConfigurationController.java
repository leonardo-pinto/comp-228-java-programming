package com.springboot.learnspringboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConfigurationController {

    @Autowired
    private CurrencyServiceConfiguration currencyServerConfiguration;

    @RequestMapping("/currency-configuration")
    public CurrencyServiceConfiguration getCurrencyServerConfiguration() {
        return this.currencyServerConfiguration;
    }

}

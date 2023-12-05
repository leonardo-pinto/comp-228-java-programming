package com.in28minutes.mockito.mockitodemo.business;

public class SomeBusinessImpl {
    private DataService service;

    public SomeBusinessImpl(DataService service) {
        this.service = service;
    }

    public int findTheGreatestFromAllData() {
        int[] data = service.retrieveAllData();
        int greatestValue = Integer.MIN_VALUE;
        for(int value: data) {
            if (value > greatestValue) {
                greatestValue = value;
            }
        }

        return greatestValue;
    }
}

interface DataService {
    int[] retrieveAllData();
}
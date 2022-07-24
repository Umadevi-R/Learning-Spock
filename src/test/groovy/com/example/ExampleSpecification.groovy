package com.example

import spock.lang.Specification
import com.example.exceptions.TooFewSidesException

class ExampleSpecification extends Specification{
    def "should be a simple assertion"() {
        expect:
            1 == 1
    }

    def "should demonstrate given-when-then"() {
        //Given Label- this part sets up the conditions required for the test
        given:
            def polygon = new Polygon(4)
        when:
            int sides = polygon.numberOfSides
        then:
            sides == 4
    }

    def "should expect Exceptions"() {
        when:
            new Polygon(0)
        then:
            def exception = thrown(TooFewSidesException)
            exception.numberOfSides == 0
    }

    def "should demonstrate Data Pipes Input: #sides"() {
        when:
            new Polygon(sides)
        then:
            def exception = thrown(TooFewSidesException)
            exception.numberOfSides == sides
        where:
            //The Inputs passed here are expected to throw exception
            sides << [0, 1, 2] //Passes a list of inputs to be tested
    }

    def "Valid Inputs that won't throw exception Input: #sides"() {
        when:
            def polygon = new Polygon(sides)
        then:
            polygon.numberOfSides == sides
        where:
            sides << [4,5,80,3]
    }

    def "Modifying the above test Input : #sides"() {
        expect:
            new Polygon(sides).numberOfSides == sides
        where:
            sides << [4,5,80,3]
    }

    def "Use data tables for calculating maximum"() {
        expect:
            Math.max(a,b) == max
        where:
            a | b || max
            1 | 3 || 3
            4 | 2 || 4
            5 | 5 || 5
    }
}

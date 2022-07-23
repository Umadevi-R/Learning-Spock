package com.example

import spock.lang.Specification

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
}

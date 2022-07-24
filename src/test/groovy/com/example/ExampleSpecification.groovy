package com.example

import spock.lang.Specification
import com.example.exceptions.TooFewSidesException
import spock.lang.Subject

class ExampleSpecification extends Specification{

    // setup method runs before every test method
    // cleanup method cleans after every test method
    // setupSpec runs once at the start
    // cleanupSpec runs once at the end
    void setupSpec() {
    }

    void setup() {
    }

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

    def "mock a concrete class"() {
        given:
            Renderer renderer = Mock()
            //Subject to specify which object is being tested
            @Subject
            def polygon = new Polygon(4, renderer)
        when:
            polygon.draw()
        then:
            4 * renderer.drawLine()
    }

    def "creating stub"() {
        given:
            Palette palette = Stub() // creates stub of concrete class
            palette.getPrimaryColour() >> Colour.Red// we use right shift to run the method and return enum value RED
            @Subject
            def renderer = new Renderer(palette)
        expect:
            renderer.getForegroundColour() == Colour.Red
    }

    def "creating stub with documentation and use of and"() {
        given: "a palette with red color as primary color"
            Palette palette = Stub() // creates stub of concrete class
            palette.getPrimaryColour() >> Colour.Red// we use right shift to run the method and return enum value RED
        and : "a renderer with red palette"
            @Subject
            def renderer = new Renderer(palette)
        expect: " renderer to use palette's primary color"
            renderer.getForegroundColour() == Colour.Red
    }

    def "should use a helper method"() {
        given:
            Renderer renderer = Mock()
            def shapeFactory = new ShapeFactory(renderer)
        when:
            def polygon = shapeFactory.createDefaultPolygon()
        then:
        // note when we use a helper method instead of below code we should use assert explicitly
        //    polygon.numberOfSides == 4
        //    polygon.renderer == renderer
            checkDefaultPolygon(polygon, renderer)
    }

    def "use of with"() {
        given:
            Renderer renderer = Mock()
            def shapeFactory = new ShapeFactory(renderer)
        when:
            def polygon = shapeFactory.createDefaultPolygon()
        then:
        // use of with
        // also if the first condition fails it won't check the next one
            with(polygon) {
                numberOfSides == 4
                renderer == renderer
            }
    }

    def "use of verifyAll to check all the conditions"() {
        given:
            Renderer renderer = Mock()
            def shapeFactory = new ShapeFactory(renderer)
        when:
            def polygon = shapeFactory.createDefaultPolygon()
        then:
            // use of verifyAll
            //checks all the specified conditions
            verifyAll(polygon) {
            numberOfSides == 4
            renderer == renderer
        }
    }

    private void checkDefaultPolygon(polygon, renderer) {
        assert polygon.numberOfSides == 4
        assert polygon.renderer == renderer

    }

    void cleanup() {
    }

    void  cleanupSpec() {
    }

}

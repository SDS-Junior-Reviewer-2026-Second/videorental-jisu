package com.videorental;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    static Movie regular;
    static Movie childrens;
    static Movie newRelease;

    @BeforeAll
    static void setup(){
        regular = new Movie("regular", Movie.REGULAR);
        childrens = new Movie("childrens", Movie.CHILDRENS);
        newRelease = new Movie("new release", Movie.NEW_RELEASE);
    }

    @Test
    void regularRentalTwoDays(){
        Customer customer = new Customer("test");
        Rental[] rentals = new Rental[]{
                new Rental(regular, 2)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();

        assertEquals("""
Rental Record for test
	2.0(regular)
Amount owed is 2.0
You earned 1 frequent renter pointers""", statement);
    }

    @Test
    void regularRentalThreeDays(){
        Customer customer = new Customer("test");
        Rental[] rentals = new Rental[]{
                new Rental(regular, 3)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();

        assertEquals("""
Rental Record for test
	3.5(regular)
Amount owed is 3.5
You earned 1 frequent renter pointers""", statement);
    }

    @Test
    void childrensRentalThreeDays(){
        Customer customer = new Customer("test");
        Rental[] rentals = new Rental[]{
                new Rental(childrens, 3)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();

        assertEquals("""
Rental Record for test
	1.5(childrens)
Amount owed is 1.5
You earned 1 frequent renter pointers""", statement);
    }

    @Test
    void childrensRentalFourDays(){
        Customer customer = new Customer("test");
        Rental[] rentals = new Rental[]{
                new Rental(childrens, 4)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();
        assertEquals("""
Rental Record for test
	3.0(childrens)
Amount owed is 3.0
You earned 1 frequent renter pointers""", statement);
    }

    @Test
    void newReleaseRentalOneDay(){
        Customer customer = new Customer("test");
        Rental[] rentals = new Rental[]{
                new Rental(newRelease, 1)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();

        assertEquals("""
Rental Record for test
	3.0(new release)
Amount owed is 3.0
You earned 1 frequent renter pointers""", statement);
    }

    @Test
    void newReleaseRentalTwoDays(){
        Customer customer = new Customer("test");
        Rental[] rentals = new Rental[]{
                new Rental(newRelease, 2)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();

        assertEquals("""
Rental Record for test
	6.0(new release)
Amount owed is 6.0
You earned 2 frequent renter pointers""", statement);
    }

    @Test
    void multipleRentals(){
        Customer customer = new Customer("test");
        Rental[] rentals = new Rental[]{
                new Rental(newRelease, 2),
                new Rental(childrens, 5),
                new Rental(regular, 1)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();

        assertEquals("""
Rental Record for test
	6.0(new release)
	4.5(childrens)
	2.0(regular)
Amount owed is 12.5
You earned 4 frequent renter pointers""", statement);
    }

}

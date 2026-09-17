package com.videorental;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerTest {

    private static final String NAME = "NAME_NOT_IMPORTANT";
    private static final String TITLE = "TITLE_NOT_IMPORTANT";

    private final Customer customer = new Customer(NAME);


    static Movie regular;
    static Movie childrens;
    static Movie newRelease;

    @BeforeAll
    static void setup(){
        regular = getMovie(Movie.REGULAR);
        childrens = getMovie(Movie.CHILDRENS);
        newRelease = getMovie(Movie.NEW_RELEASE);
    }

    private static Movie getMovie(int priceCode) {
        return switch (priceCode){
            case Movie.REGULAR -> new RegularMovie(TITLE);
            case Movie.NEW_RELEASE -> new NewReleaseMovie(TITLE);
            case Movie.CHILDRENS -> new ChildrensMovie(TITLE);
            default -> null;
        };
    }

    @Test
    void createCustomer(){
        assertNotNull(customer);
    }

    @Test
    void noRental(){
        String statement = customer.statement();

        assertEquals("""
Rental Record for NAME_NOT_IMPORTANT
Amount owed is 0.0
You earned 0 frequent renter pointers""", statement);
    }

    @Test
    void regularRentalTwoDays(){
        Rental[] rentals = new Rental[]{
                new Rental(regular, 2)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();

        assertEquals("""
Rental Record for NAME_NOT_IMPORTANT
	2.0(TITLE_NOT_IMPORTANT)
Amount owed is 2.0
You earned 1 frequent renter pointers""", statement);
    }

    @Test
    void regularRentalThreeDays(){
        Rental[] rentals = new Rental[]{
                new Rental(regular, 3)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();

        assertEquals("""
Rental Record for NAME_NOT_IMPORTANT
	3.5(TITLE_NOT_IMPORTANT)
Amount owed is 3.5
You earned 1 frequent renter pointers""", statement);
    }

    @Test
    void childrensRentalThreeDays(){
        Rental[] rentals = new Rental[]{
                new Rental(childrens, 3)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();

        assertEquals("""
Rental Record for NAME_NOT_IMPORTANT
	1.5(TITLE_NOT_IMPORTANT)
Amount owed is 1.5
You earned 1 frequent renter pointers""", statement);
    }

    @Test
    void childrensRentalFourDays(){
        Rental[] rentals = new Rental[]{
                new Rental(childrens, 4)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();
        assertEquals("""
Rental Record for NAME_NOT_IMPORTANT
	3.0(TITLE_NOT_IMPORTANT)
Amount owed is 3.0
You earned 1 frequent renter pointers""", statement);
    }

    @Test
    void newReleaseRentalOneDay(){
        Rental[] rentals = new Rental[]{
                new Rental(newRelease, 1)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();

        assertEquals("""
Rental Record for NAME_NOT_IMPORTANT
	3.0(TITLE_NOT_IMPORTANT)
Amount owed is 3.0
You earned 1 frequent renter pointers""", statement);
    }

    @Test
    void newReleaseRentalTwoDays(){
        Rental[] rentals = new Rental[]{
                new Rental(newRelease, 2)
        };

        for (Rental rental: rentals){
            customer.addRental(rental);
        }

        String statement = customer.statement();

        assertEquals("""
Rental Record for NAME_NOT_IMPORTANT
	6.0(TITLE_NOT_IMPORTANT)
Amount owed is 6.0
You earned 2 frequent renter pointers""", statement);
    }

    @Test
    void multipleRentals(){
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
Rental Record for NAME_NOT_IMPORTANT
	6.0(TITLE_NOT_IMPORTANT)
	4.5(TITLE_NOT_IMPORTANT)
	2.0(TITLE_NOT_IMPORTANT)
Amount owed is 12.5
You earned 4 frequent renter pointers""", statement);
    }

}

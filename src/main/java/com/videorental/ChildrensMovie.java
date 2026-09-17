package com.videorental;

public class ChildrensMovie extends Movie {
    public ChildrensMovie(String title) {
        super(title, Movie.REGULAR);
    }

    @Override
    public double getChargeFor(int daysRented){
        double charge = 1.5;
        if (daysRented > 3)
            charge += (daysRented - 3) * 1.5;
        return charge;
    }
}
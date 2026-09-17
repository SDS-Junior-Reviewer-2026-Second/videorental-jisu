package com.videorental;

class Rental {
	private Movie movie;
	private int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public double getCharge() {
		return getMovie().getChargeFor(getDaysRented());
	}


	public int getFrequentRenterPoints() {
		// add frequent renter points
		int frequentRenterPoints = 0;
		// add bonus for a two day new release rental
		if ((getMovie() instanceof NewReleaseMovie) && getDaysRented() > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}
}
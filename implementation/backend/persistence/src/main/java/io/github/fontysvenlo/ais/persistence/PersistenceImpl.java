package io.github.fontysvenlo.ais.persistence;

import io.github.fontysvenlo.ais.persistence.api.CustomerRepository;
import io.github.fontysvenlo.ais.persistence.api.FlightRepository;
import io.github.fontysvenlo.ais.persistence.api.Persistence;
import io.github.fontysvenlo.ais.persistence.api.BookingRepository;

/**
 * Actual creator of storage services.
 */
class PersistenceImpl implements Persistence{
    private DBConfig config;

    PersistenceImpl(DBConfig config) {
        this.config = config;
    }

    /**
     * Get the implementation of the CustomerRepository.
     * @return the implementation of the CustomerRepository
     */
    @Override
    public CustomerRepository getCustomerRepository() {
        return new CustomerRepositoryImpl(config);
    }

    @Override
    public FlightRepository getFlightRepository() {
        return new FlightRepositoryImpl(config);  // Returns the implementation of FlightRepository
    }

    @Override
    public BookingRepository getBookingRepository() {
        return new BookingRepositoryImpl(config);
    }

}

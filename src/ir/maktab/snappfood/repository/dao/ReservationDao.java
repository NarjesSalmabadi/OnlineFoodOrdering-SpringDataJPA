package ir.maktab.snappfood.repository.dao;

import ir.maktab.snappfood.repository.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDao extends CrudRepository<Reservation, Integer> {

    @Query("select max(r.factorNumber) from Reservation r")
    Integer getMaxFactorNumber();
}

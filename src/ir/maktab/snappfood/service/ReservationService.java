package ir.maktab.snappfood.service;

import ir.maktab.snappfood.repository.dao.ReservationDao;
import ir.maktab.snappfood.repository.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    public Integer getMaxFactorNumber() {
        return reservationDao.getMaxFactorNumber();
    }

    public void addNewReservation(Reservation reservation) {
        if(Objects.nonNull(reservation))
            reservationDao.save(reservation);
    }
}

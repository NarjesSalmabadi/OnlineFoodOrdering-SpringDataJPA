package ir.maktab.snappfood.repository.dao;

import ir.maktab.snappfood.repository.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDao extends CrudRepository<Admin, Integer> {

    Optional<Admin> findByUsernameAndPassword(String username, String password);
}

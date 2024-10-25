package org.example.tz4krainet.Repository;

import org.example.tz4krainet.Models.TimeRecord;
import org.example.tz4krainet.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<TimeRecord, Integer> {

    List<TimeRecord> findByUser(Users users);
}

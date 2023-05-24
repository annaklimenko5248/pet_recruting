package app.service;

import app.model.OrderCandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderCandidateRepository extends JpaRepository<OrderCandidateEntity,Integer> {

}

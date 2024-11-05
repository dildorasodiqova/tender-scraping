package uz.simplex.tenderscraping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.simplex.tenderscraping.entity.Tender;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {
}

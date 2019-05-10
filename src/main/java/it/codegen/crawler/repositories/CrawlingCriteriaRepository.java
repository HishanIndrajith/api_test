package it.codegen.crawler.repositories;

import it.codegen.crawler.models.CrawlingCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlingCriteriaRepository extends JpaRepository<CrawlingCriteria, Long>{

}
package com.douglas.userapp.jobvacancy;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobVacancyRepository extends JpaRepository<JobVacancy, Long>{

	JobVacancy findByCode(Long code);
}

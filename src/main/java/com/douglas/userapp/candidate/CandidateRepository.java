package com.douglas.userapp.candidate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.userapp.jobvacancy.JobVacancy;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{

	Iterable<Candidate> findByVacancy(JobVacancy jobVacancy);
}

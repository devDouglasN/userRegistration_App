package com.douglas.userapp.jobvacancy;

import java.util.List;

import com.douglas.userapp.candidate.Candidate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "code")
@Entity
public class JobVacancy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	private String name;
	private String description;
	private String date;
	private String salary;

	@OneToMany(mappedBy = "vacancy", cascade = CascadeType.REMOVE)
	private List<Candidate> candidate;
}

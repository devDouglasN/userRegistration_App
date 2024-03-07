package com.douglas.userapp.jobvacancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.douglas.userapp.candidate.Candidate;
import com.douglas.userapp.candidate.CandidateRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class JobVacancyService {
	
	@Autowired
	private JobVacancyRepository jobVacancyRepository;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Transactional
    public String registerVacancy(@Valid  JobVacancy vacancy, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Verifique os campos...");
            return "redirect:/registerVacancy";
        }

        jobVacancyRepository.save(vacancy);
        attributes.addFlashAttribute("message", "Vaga cadastrada com sucesso!");
        return "redirect:/registerVacancy";
    }

    public Iterable<JobVacancy> listVacancies() {
        return jobVacancyRepository.findAll();
    }

    public JobVacancy detailsVacancy(Long code) {
        return jobVacancyRepository.findByCode(code);
    }

    @Transactional
    public String addCandidate(long code, @Valid Candidate candidate,
                               BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Verifique os campos");
            return "redirect:/{code}";
        }

        if (candidateRepository.findByRg(candidate.getRg()) != null) {
            attributes.addFlashAttribute("message_error", "RG duplicado");
            return "redirect:/{code}";
        }

        JobVacancy vacancy = jobVacancyRepository.findByCode(code);
        candidate.setVacancy(vacancy);
        candidateRepository.save(candidate);
        attributes.addFlashAttribute("message", "Candidato adicionado com sucesso!");
        return "redirect:/{code}";
    }

    @Transactional
    public String deleteVacancy(Long code) {
        JobVacancy vacancy = jobVacancyRepository.findByCode(code);
        jobVacancyRepository.delete(vacancy);
        return "redirect:/vacancies";
    }

    @Transactional
    public String deleteCandidate(String rg) {
        Candidate candidate = candidateRepository.findByRg(rg);
        candidateRepository.delete(candidate);
        return "redirect:/vacancies";
    }

    public JobVacancy editVacancy(Long code) {
        return jobVacancyRepository.findByCode(code);
    }

    @Transactional
    public String updateVacancy(@Valid JobVacancy vacancy, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "vacancy/update-vacancy";
        }
        jobVacancyRepository.save(vacancy);
        attributes.addFlashAttribute("message", "Vaga alterada com sucesso!");
        return "redirect:/vacancies";
    }
}

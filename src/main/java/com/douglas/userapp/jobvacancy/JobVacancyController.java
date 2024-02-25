package com.douglas.userapp.jobvacancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.douglas.userapp.candidate.Candidate;
import com.douglas.userapp.candidate.CandidateRepository;

import jakarta.validation.Valid;

@RestController
public class JobVacancyController {

	@Autowired
	private JobVacancyRepository jobVacancyRepository;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@RequestMapping(value = "/registerVacancy", method = RequestMethod.GET)
    public String form(){
        return "jobVacancy/formJobVacancy.html";
    }

    @RequestMapping(value = "/registerVacancy", method = RequestMethod.POST)
    public String form(@Valid JobVacancy jobVacancy, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            attributes.addFlashAttribute("message", "Verifique os campos...");
            return "redirect:/registerVacancy";
        }
        jobVacancyRepository.save(jobVacancy);
        attributes.addFlashAttribute("message", "Vaga cadastrada com sucesso!");

        return "redirect:/registerVacancy";
    }
    
    @RequestMapping("/vacancies")
    public ModelAndView listVacancies(){
        ModelAndView mv = new ModelAndView("vacancy/listVacancy");
        Iterable<JobVacancy>vacancies = jobVacancyRepository.findAll();
        mv.addObject("vacancies", vacancies);
        return mv;
    }
    
    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ModelAndView detailsVacancy(@PathVariable("code") Long code){
    	JobVacancy jobVacancy = jobVacancyRepository.findByCode(code);
        ModelAndView mv = new ModelAndView("vacancy/detailsVacancy");
        mv.addObject("jobVacancy", jobVacancy);

        Iterable<Candidate> candidates = candidateRepository.findByVacancy(jobVacancy);
        mv.addObject("candidates", candidates);

        return mv;
    }
}

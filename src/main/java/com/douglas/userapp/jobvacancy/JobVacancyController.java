package com.douglas.userapp.jobvacancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.douglas.userapp.candidate.Candidate;

import jakarta.validation.Valid;

@Controller
public class JobVacancyController {

	@Autowired
	private JobVacancyService jobVacancyService;

	@GetMapping("/registerVacancy")
    public String formVaga() {
        return "vacancy/formVacancy";
    }

    @PostMapping("/registerVacancy")
    public String registerVacancy(@Valid JobVacancy vacancy, BindingResult result, RedirectAttributes attributes) {
        return jobVacancyService.registerVacancy(vacancy, result, attributes);
    }

    @GetMapping("/vacancies")
    public ModelAndView listVacancy() {
        ModelAndView mv = new ModelAndView("vacancy/listVacancy");
        mv.addObject("vacancys", jobVacancyService.listVacancies());
        return mv;
    }

    @GetMapping("/{code}")
    public ModelAndView detailsVacancy(@PathVariable("code") Long code) {
        ModelAndView mv = new ModelAndView("vacancy/detailsVacancy");
        mv.addObject("vacancys", jobVacancyService.detailsVacancy(code));
        mv.addObject("candidates", jobVacancyService.detailsVacancy(code).getCandidate());
        return mv;
    }

    @PostMapping("/{code}")
    public String addCandidate(@PathVariable("code") Long code, @Valid Candidate candidate,
                               BindingResult result, RedirectAttributes attributes) {
        return jobVacancyService.addCandidate(code, candidate, result, attributes);
    }

    @GetMapping("/deleteVacancy")
    public String deleteVacancy(Long code) {
        return jobVacancyService.deleteVacancy(code);
    }

    @GetMapping("/deleteCandidate")
    public String deleteCandidate(String rg) {
        return jobVacancyService.deleteCandidate(rg);
    }

    @GetMapping("/edit-vacancy")
    public ModelAndView editVacancy(Long code) {
        ModelAndView mv = new ModelAndView("vacancy/update-vacancy");
        mv.addObject("vacancy", jobVacancyService.editVacancy(code));
        return mv;
    }

    @PostMapping("/edit-vacancy")
    public String updateVaga(@Valid JobVacancy vacancy, BindingResult result, RedirectAttributes attributes) {
        return jobVacancyService.updateVacancy(vacancy, result, attributes);
    }
}

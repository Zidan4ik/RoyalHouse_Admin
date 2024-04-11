package com.example.royalhouse.controler;

import com.example.royalhouse.entity.Request;
import com.example.royalhouse.service.serviceimp.ObjectServiceImp;
import com.example.royalhouse.service.serviceimp.RequestServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    public final RequestServiceImp requestService;
    public final ObjectServiceImp objectService;

    @GetMapping("")
    public ModelAndView viewRequests(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(required = false) String fullName,
                                     @RequestParam(required = false) String phone,
                                     @RequestParam(required = false) String email,
                                     @RequestParam(required = false) Boolean isReported) {
        ModelAndView model = new ModelAndView("requests/requests-view");
        Pageable paging = PageRequest.of(page, size);

        Page<Request> pageRequests = requestService.getAll(fullName, phone, email, null, paging);
        List<Request> requests = pageRequests.getContent();

        model.addObject("requests", requests);
        model.addObject("currentPage", page);
        model.addObject("pageRequests", pageRequests);

        model.addObject("fullName", fullName);
        model.addObject("phone", phone);
        model.addObject("email", email);
        model.addObject("isReported", isReported);
        return model;
    }

    @GetMapping("{id}/view")
    public ModelAndView viewRequest(@PathVariable(name = "id") long id) {
        ModelAndView model = new ModelAndView("requests/request-view");

        model.addObject("request", requestService.getById(id).get());
        return model;
    }

    @GetMapping("{id}/delete")
    public ModelAndView deleteRequest(@PathVariable(name = "id") long id) {
        ModelAndView model = new ModelAndView("redirect:/requests");
        requestService.deleteById(id);
        return model;
    }

    @ModelAttribute("countObjects")
    public int showCountObjects() {
        return objectService.getCountObjects();
    }

    @ModelAttribute("countRequests")
    public long showCountRequest() {
        return requestService.getCountRequests();
    }
}

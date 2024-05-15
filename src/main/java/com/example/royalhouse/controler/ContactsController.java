package com.example.royalhouse.controler;

import com.example.royalhouse.entity.Contact;
import com.example.royalhouse.entity.unifier.ContactsUnifier;
import com.example.royalhouse.mapper.MapperContacts;
import com.example.royalhouse.model.ContactsDTOEdit;

import com.example.royalhouse.service.serviceimp.ContactServiceImp;
import com.example.royalhouse.service.serviceimp.EmailRequestServiceImp;
import com.example.royalhouse.service.serviceimp.ObjectServiceImp;
import com.example.royalhouse.service.serviceimp.RequestServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ContactsController {
    private final ContactServiceImp contactService;
    private final EmailRequestServiceImp emailRequestService;
    private final ObjectServiceImp objectService;
    private final RequestServiceImp requestService;

    @GetMapping("/contacts/edit")
    public ModelAndView viewContacts() {
        ModelAndView model = new ModelAndView();

        Optional<Contact> contactById = contactService.getById(1l);
        if (contactById.isEmpty()) {
            model.addObject("contacts", new ContactsDTOEdit());
        } else {
            ContactsDTOEdit dto = MapperContacts.toDTOEdit(contactById.get(), emailRequestService.getAll());
            model.addObject("contacts", dto);
        }
        model.setViewName("contact/contacts");
        return model;
    }

    @PostMapping("/contacts/edit")
    public ModelAndView editContacts(@ModelAttribute(name = "contacts") ContactsDTOEdit dto) {
        ModelAndView model = new ModelAndView();

        Optional<Contact> contactBD = contactService.getById(1l);
        if (!contactBD.isEmpty()) {
            if ((!contactBD.get().getPassword().equals(dto.getPassword())) &&
                    (!dto.getRepeatPassword().equals(dto.getNewPassword())) &&
                    (!dto.getRepeatPassword().isEmpty() && !dto.getNewPassword().isEmpty())) {
                if (!dto.getRepeatPassword().equals(dto.getNewPassword())) {
                    model.addObject("errorSimilarities", "Повторний пароль не вірний");
                }
                if (!contactBD.get().getPassword().equals(dto.getPassword())) {
                    model.addObject("errorCurrentPassword", "Поточний пароль не вірний");
                }
                model.setViewName("contact/contacts");
                return model;
            }
            if (!contactBD.get().getPassword().equals(dto.getPassword())) {
                model.addObject("errorCurrentPassword", "Поточний пароль не вірний");
                model.setViewName("contact/contacts");
                return model;
            }
            if (!dto.getRepeatPassword().isEmpty()) {
                dto.setPassword(dto.getNewPassword());
            }
        } else {
            if (!dto.getRepeatPassword().equals(dto.getNewPassword())) {
                model.addObject("errorSimilarities", "Повторний пароль не вірний");
                model.addObject("errorCurrentPassword", "Пароля не існує");
                model.setViewName("contact/contacts");
                return model;
            }
            if (dto.getRepeatPassword().isEmpty() && dto.getNewPassword().isEmpty()) {
                model.addObject("errorSimilarities", "Пароль не можуть бути без значення");
                model.addObject("errorCurrentPassword", "Пароля не існує");
                model.setViewName("contact/contacts");
                return model;
            } else {
                dto.setPassword(dto.getNewPassword());
            }
        }
        ContactsUnifier unifier = MapperContacts.toEntityEdit(dto);
        contactService.save(unifier.getContact());
        emailRequestService.saveAll(unifier.getEmails());
        model.setViewName("redirect:/admin/requests");
        return model;
    }

    @ModelAttribute("countObjects")
    public int showCountObjects() {
        return objectService.getCountObjects();
    }

    @ModelAttribute("countRequests")
    public int showCountRequest() {
        return requestService.getRequestsByReportedFalse().size();
    }
    @ModelAttribute("isActiveContacts")
    public boolean toActiveContacts(){
        return true;
    }
}

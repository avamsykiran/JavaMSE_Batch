package com.cts.webmvcdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cts.webmvcdemo.entities.Contact;
import com.cts.webmvcdemo.services.ContactService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contacts")
public class ContactsController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/list")
	public ModelAndView showContactsListAction() {
		return new ModelAndView("contacts/list-page", "contacts", contactService.getAll());
	}

	@GetMapping("/del")
	public String delContactAction(@RequestParam("cid") int contactId) {
		contactService.deleteById(contactId);
		return "redirect:/contacts/list";
	}

	@GetMapping("/add")
	public ModelAndView showContactFormAction() {
		return new ModelAndView("contacts/contact-form-page", "contact", new Contact());
	}

	@PostMapping("/add")
	public ModelAndView addContactAction(@ModelAttribute("contact") @Valid Contact contact,
			BindingResult bindingResult) {

		ModelAndView mv = null;
		if (bindingResult.hasErrors()) {
			mv = new ModelAndView("contacts/contact-form-page", "contact", contact);
		} else {
			contactService.add(contact);
			mv = new ModelAndView("redirect:/contacts/list");
		}

		return mv;
	}

	@GetMapping("/edit")
	public ModelAndView showContactFormActionForEdit(@RequestParam("cid") int contactId) {
		return new ModelAndView("contacts/contact-form-page", "contact", contactService.getById(contactId));
	}

	@PostMapping("/edit")
	public ModelAndView updateContactAction(@ModelAttribute("contact") @Valid Contact contact,
			BindingResult bindingResult) {
		ModelAndView mv = null;

		if (bindingResult.hasErrors()) {
			mv = new ModelAndView("contacts/contact-form-page", "contact", contact);
		} else {
			contactService.update(contact);
			mv = new ModelAndView("redirect:/contacts/list");
		}

		return mv;
	}
}

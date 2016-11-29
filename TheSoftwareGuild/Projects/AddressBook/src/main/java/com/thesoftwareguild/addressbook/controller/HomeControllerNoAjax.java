/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbook.controller;

import com.thesoftwareguild.addressbook.dao.AddressBookDao;
import com.thesoftwareguild.addressbook.model.Address;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author bretthanson
 */
@Controller
public class HomeControllerNoAjax {

    private AddressBookDao dao;

    @Inject
    public HomeControllerNoAjax(AddressBookDao dao) 
    {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayAddressBookNoAjax", method = RequestMethod.GET)
    public String displayAddressBookNoAjax(Model model) 
    {
        List<Address> aList = dao.getAllAddresses();
        model.addAttribute("addressBook", aList);
        
        return "displayAddressBookNoAjax";
    }

    @RequestMapping(value = "displayNewAddressFormNoAjax", method = RequestMethod.GET)
    public String displayNewAddressFormNoAjax() 
    {
        return "newAddressFormNoAjax";
    }

    @RequestMapping(value = "/addNewAddressNoAjax", method = RequestMethod.POST)
    public String addNewAddressNoAjax(HttpServletRequest req) 
    {
        String first = req.getParameter("first");
        String last = req.getParameter("last");
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");

        Address address = new Address();

        address.setFirst(first);
        address.setLast(last);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);

        dao.addAddress(address);

        return "redirect:displayAddressBookNoAjax";
    }

    @RequestMapping(value = "/deleteAddressNoAjax", method = RequestMethod.GET)
    public String deleteAddressNoAjax(HttpServletRequest req) 
    {
        int addressId = Integer.parseInt(req.getParameter("addressId"));
        dao.removeAddress(addressId);
        
        return "redirect:displayAddressBookNoAjax";
    }

    @RequestMapping(value = "/displayEditAddressFormNoAjax", method = RequestMethod.GET)
    public String displayEditAddressFormNoAjax(HttpServletRequest req, Model model) 
    {

        int addressId = Integer.parseInt(req.getParameter("addressId"));
        Address address = dao.getAddressById(addressId);
        model.addAttribute("address", address);

        return "editAddressFormNoAjax";
    }

    @RequestMapping(value = "/editAddressNoAjax", method = RequestMethod.POST)
    public String editAddressNoAjax(@Valid @ModelAttribute("address") Address address, BindingResult result) 
    {
        if (result.hasErrors()) 
        {
            return "editAddressFormNoAjax";
        }
        dao.updateAddress(address);
        
        return "redirect:displayAddressBookNoAjax";
    }
}

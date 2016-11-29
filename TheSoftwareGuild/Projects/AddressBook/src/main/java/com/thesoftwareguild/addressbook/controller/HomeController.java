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
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author bretthanson
 */
@Controller
public class HomeController {

    private AddressBookDao dao;

    @Inject
    public HomeController(AddressBookDao dao) 
    {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() 
    {
        return "home";
    }

// RETRIEVING AN ADDRESS
    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    @ResponseBody public Address getAddress(@PathVariable("id") int id) 
    {
        return dao.getAddressById(id);
    }

// CREATING AN ADDRESS
    @RequestMapping(value = "/address", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody public Address createAddress(@Valid @RequestBody Address address) 
    {
        dao.addAddress(address);
        return address;
    }

// DELETING AN ADDRESS
    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public void deleteAddress(@PathVariable("id") int id) 
    {
        dao.removeAddress(id);
    }

// UPDATING AN ADDRESS
    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putAddress(@PathVariable("id") int id, @Valid @RequestBody Address address) 
    {
        address.setAddressId(id);
        dao.updateAddress(address);
    }

// RETRIEVING ALL ADDRESSES
    @RequestMapping(value = "/addresses", method = RequestMethod.GET)
    @ResponseBody public List<Address> getAllAddresses() 
    {
        return dao.getAllAddresses();
    }
}

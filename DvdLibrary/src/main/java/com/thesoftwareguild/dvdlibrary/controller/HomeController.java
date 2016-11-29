/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrary.controller;

import com.thesoftwareguild.dvdlibrary.dao.DvdLibraryDao;
import com.thesoftwareguild.dvdlibrary.model.Dvd;
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

    private DvdLibraryDao dao;

    @Inject
    public HomeController(DvdLibraryDao dao) 
    {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() 
    {
        return "home";
    }

// RETRIEVING A DVD
    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.GET)
    @ResponseBody public Dvd getDvd(@PathVariable("id") int id) 
    {
        return dao.getDvdById(id);
    }

// CREATING A DVD
    @RequestMapping(value = "/dvd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody public Dvd createDvd(@Valid @RequestBody Dvd dvd) 
    {
        dao.addDvd(dvd);
        return dvd;
    }

// DELETING A DVD
    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public void deleteDvd(@PathVariable("id") int id) 
    {
        dao.removeDvd(id);
    }

// UPDATING A DVD
    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putDvd(@PathVariable("id") int id, @Valid @RequestBody Dvd dvd) 
    {
        dvd.setDvdId(id);
        dao.updateDvd(dvd);
    }

// RETRIEVING ALL DVDS
    @RequestMapping(value = "/dvds", method = RequestMethod.GET)
    @ResponseBody public List<Dvd> getAllDvds() 
    {
        return dao.getAllDvds();
    }
}

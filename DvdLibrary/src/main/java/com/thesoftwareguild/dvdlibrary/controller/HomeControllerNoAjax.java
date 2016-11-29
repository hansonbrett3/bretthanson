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

    private DvdLibraryDao dao;

    @Inject
    public HomeControllerNoAjax(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayDvdLibraryNoAjax", method = RequestMethod.GET)
    public String displayDvdLibraryNoAjax(Model model) {
        List<Dvd> dList = dao.getAllDvds();
        model.addAttribute("dvdLibrary", dList);

        return "displayDvdLibraryNoAjax";
    }

    @RequestMapping(value = "displayNewDvdFormNoAjax", method = RequestMethod.GET)
    public String displayNewDvdFormNoAjax() {
        return "newDvdFormNoAjax";
    }

    @RequestMapping(value = "/addNewDvdNoAjax", method = RequestMethod.POST)
    public String addNewDvdNoAjax(HttpServletRequest req) {
        String title = req.getParameter("title");
        String released = req.getParameter("released");
        String mpaa = req.getParameter("mpaa");
        String director = req.getParameter("director");
        String studio = req.getParameter("studio");
        String rating = req.getParameter("rating");
        String note = req.getParameter("note");

        Dvd dvd = new Dvd();

        dvd.setTitle(title);
        dvd.setReleased(released);
        dvd.setMpaa(mpaa);
        dvd.setDirector(director);
        dvd.setStudio(studio);
        dvd.setRating(rating);
        dvd.setNote(note);

        dao.addDvd(dvd);

        return "redirect:displayDvdLibraryNoAjax";
    }

    @RequestMapping(value = "/deleteDvdNoAjax", method = RequestMethod.GET)
    public String deleteDvdNoAjax(HttpServletRequest req) {

        int dvdId = Integer.parseInt(req.getParameter("dvdId"));

        dao.removeDvd(dvdId);

        return "redirect:displayDvdLibraryNoAjax";
    }

    @RequestMapping(value = "/displayEditDvdFormNoAjax", method = RequestMethod.GET)
    public String displayEditDvdFormNoAjax(HttpServletRequest req, Model model) {

        int dvdId = Integer.parseInt(req.getParameter("dvdId"));

        Dvd dvd = dao.getDvdById(dvdId);

        model.addAttribute("dvd", dvd);

        return "editDvdFormNoAjax";
    }

    @RequestMapping(value = "/editDvdNoAjax", method = RequestMethod.POST)
    public String editDvdNoAjax(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult result) 
    {
        if (result.hasErrors()) {
            return "editDvdFormNoAjax";
        }
        
        dao.updateDvd(dvd);
        return "redirect:displayDvdLibraryNoAjax";
    }
}

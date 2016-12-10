/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrary.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author bretthanson
 */
public class Dvd {

    private int dvdId;
    @NotEmpty(message = "You must supply a value for Title.")
    @Length(max = 50, message = "Title must be no more than 50 characters in length.")
    private String title;
    @NotEmpty(message = "You must supply a value for Release Date.")
    @Length(max = 4, message = "Release Date must be no more than 4 characters in length (Ex. 1999).")
    private String released;
    @NotEmpty(message = "You must supply a value for MPAA Rating.")
    @Length(max = 5, message = "MPAA Rating must be no more than 5 characters in length.")
    private String mpaa;
    @NotEmpty(message = "You must supply a value for Director.")
    @Length(max = 25, message = "Director must be no more than 25 characters in length.")
    private String director;
    @NotEmpty(message = "You must supply a value for Studio.")
    @Length(max = 30, message = "Studio must be no more than 30 characters in length.")
    private String studio;
    @NotEmpty(message = "You must supply a value for User Rating.")
    @Length(max = 30, message = "User Rating must be no more than 30 characters in length.")
    private String rating;
    @NotEmpty(message = "You must supply a value for User Note.")
    @Length(max =50, message = "User Note must be no more than 50 characters in length.")
    private String note;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.dvdId;
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + Objects.hashCode(this.released);
        hash = 79 * hash + Objects.hashCode(this.mpaa);
        hash = 79 * hash + Objects.hashCode(this.director);
        hash = 79 * hash + Objects.hashCode(this.studio);
        hash = 79 * hash + Objects.hashCode(this.rating);
        hash = 79 * hash + Objects.hashCode(this.note);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (this.dvdId != other.dvdId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.released, other.released)) {
            return false;
        }
        if (!Objects.equals(this.mpaa, other.mpaa)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        return true;
    }

    public int getDvdId() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getMpaa() {
        return mpaa;
    }

    public void setMpaa(String mpaa) {
        this.mpaa = mpaa;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

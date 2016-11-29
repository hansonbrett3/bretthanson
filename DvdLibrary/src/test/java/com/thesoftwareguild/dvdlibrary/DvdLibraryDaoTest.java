/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrary;

import com.thesoftwareguild.dvdlibrary.dao.DvdLibraryDao;
import com.thesoftwareguild.dvdlibrary.dao.SearchTerm;
import com.thesoftwareguild.dvdlibrary.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author bretthanson
 */
public class DvdLibraryDaoTest {

    private DvdLibraryDao dao;

    public DvdLibraryDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = (DvdLibraryDao) ctx.getBean("dvdLibraryDao");
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from dvds");
//        dao = ctx.getBean("dvdLibraryDao", DvdLibraryDao.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteDvd() {
        Dvd nd = new Dvd();
        nd.setTitle("First Title");
        nd.setReleased("1111");
        nd.setMpaa("G");
        nd.setDirector("One Director");
        nd.setStudio("One Studio");
        nd.setRating("One");
        nd.setNote("First Note");

        dao.addDvd(nd);

        Dvd fromDb = dao.getDvdById(nd.getDvdId());

        assertEquals(fromDb.getDvdId(), nd.getDvdId());
        assertEquals(fromDb.getDirector(), nd.getDirector());
        assertEquals(fromDb.getMpaa(), nd.getMpaa());
        assertEquals(fromDb.getRating(), nd.getRating());

        dao.removeDvd(nd.getDvdId());

        assertNull(dao.getDvdById(nd.getDvdId()));
    }

    @Test
    public void addUpdateDvd() {
        Dvd nd = new Dvd();
        nd.setTitle("First Title");
        nd.setReleased("1111");
        nd.setMpaa("G");
        nd.setDirector("One Director");
        nd.setStudio("One Studio");
        nd.setRating("One");
        nd.setNote("First Note");

        dao.addDvd(nd);

        nd.setDirector("George");

        dao.updateDvd(nd);

        Dvd fromDb = dao.getDvdById(nd.getDvdId());

        assertEquals(fromDb.getDvdId(), nd.getDvdId());
        assertEquals(fromDb.getDirector(), nd.getDirector());
        assertEquals(fromDb.getMpaa(), nd.getMpaa());
        assertEquals(fromDb.getRating(), nd.getRating());

    }

    @Test
    public void getAllDvds() {
        Dvd nd = new Dvd();
        nd.setTitle("First Title");
        nd.setReleased("1111");
        nd.setMpaa("G");
        nd.setDirector("One Director");
        nd.setStudio("One Studio");
        nd.setRating("One");
        nd.setNote("First Note");

        dao.addDvd(nd);

        Dvd nd2 = new Dvd();
        nd2.setTitle("Second Title");
        nd2.setReleased("2222");
        nd2.setMpaa("PG");
        nd2.setDirector("Two Directors");
        nd2.setStudio("Two Studios");
        nd2.setRating("Two");
        nd2.setNote("Second Note");

        dao.addDvd(nd2);

        List<Dvd> dList = dao.getAllDvds();
        assertEquals(dList.size(), 2);
    }

//    @Test
//    public void searchDvds() {
//        Dvd nd = new Dvd();
//        nd.setTitle("First Title");
//        nd.setReleased("1111");
//        nd.setMpaa("G");
//        nd.setDirector("One Director");
//        nd.setStudio("One Studio");
//        nd.setRating("One");
//        nd.setNote("First Note");
//
//        dao.addDvd(nd);
//
//        Dvd nd2 = new Dvd();
//        nd2.setTitle("Second Title");
//        nd2.setReleased("2222");
//        nd2.setMpaa("PG");
//        nd2.setDirector("Three Directors");
//        nd2.setStudio("Two Studios");
//        nd2.setRating("Two");
//        nd2.setNote("Second Note");
//
//        dao.addDvd(nd2);
//
//        Dvd nd3 = new Dvd();
//        nd3.setTitle("Third Title");
//        nd3.setReleased("3333");
//        nd3.setMpaa("PG-13");
//        nd3.setDirector("Three Directors");
//        nd3.setStudio("Three Studios");
//        nd3.setRating("Three");
//        nd3.setNote("Third Note");
//
//        dao.addDvd(nd3);
//
//        Map<SearchTerm, String> criteria = new HashMap<>();
//
//        criteria.put(SearchTerm.DIRECTOR, "One Director");
//        List<Dvd> dList = dao.searchDvds(criteria);
//        assertEquals(1, dList.size());
//        assertEquals(nd, dList.get(0));
//
//        criteria.put(SearchTerm.DIRECTOR, "Three Directors");
//        dList = dao.searchDvds(criteria);
//        assertEquals(2, dList.size());
//
//        criteria.put(SearchTerm.RATING, "Three");
//        dList = dao.searchDvds(criteria);
//        assertEquals(1, dList.size());
//        assertEquals(nd3, dList.get(0));
//
//        criteria.put(SearchTerm.STUDIO, "Three Studios");
//        dList = dao.searchDvds(criteria);
//        assertEquals(1, dList.size());
//        assertEquals(nd3, dList.get(0));
//
//        criteria.put(SearchTerm.TITLE, "Fourth Title");
//        dList = dao.searchDvds(criteria);
//        assertEquals(0, dList.size());
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbook;

import com.thesoftwareguild.addressbook.dao.AddressBookDao;
import com.thesoftwareguild.addressbook.dao.SearchTerm;
import com.thesoftwareguild.addressbook.model.Address;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author bretthanson
 */
public class AddressBookDaoTest {

    private AddressBookDao dao;

    public AddressBookDaoTest() {
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
        dao = (AddressBookDao) ctx.getBean("addressBookDao");
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from addresses");
//        dao = ctx.getBean("addressBookDao", AddressBookDao.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteAddress() {
        Address na = new Address();
        na.setFirst("Jane");
        na.setLast("Doe");
        na.setStreet("111 First Street");
        na.setCity("Cleveland");
        na.setState("OH");
        na.setZip("44118");

        dao.addAddress(na);

        Address fromDb = dao.getAddressById(na.getAddressId());
        assertEquals(fromDb, na);
        dao.removeAddress(na.getAddressId());
        assertNull(dao.getAddressById(na.getAddressId()));
    }

    @Test
    public void addUpdateAddress() {
        Address na = new Address();
        na.setFirst("Jane");
        na.setLast("Doe");
        na.setStreet("111 First Street");
        na.setCity("Cleveland");
        na.setState("OH");
        na.setZip("44118");

        dao.addAddress(na);

        na.setCity("Chicago");

        dao.updateAddress(na);

        Address fromDb = dao.getAddressById(na.getAddressId());

        assertEquals(fromDb, na);
    }

    @Test
    public void getAllAddresses() {
        Address na = new Address();
        na.setFirst("Jane");
        na.setLast("Doe");
        na.setStreet("111 First Street");
        na.setCity("Cleveland");
        na.setState("OH");
        na.setZip("44118");

        dao.addAddress(na);

        Address na2 = new Address();
        na2.setFirst("Tony");
        na2.setLast("Smith");
        na2.setStreet("222 Second Street");
        na2.setCity("Chicago");
        na2.setState("IL");
        na2.setZip("55555");

        dao.addAddress(na2);

        List<Address> aList = dao.getAllAddresses();
        assertEquals(aList.size(), 2);

    }

//    @Test
//    public void searchAddresses() {
//        Address na = new Address();
//        na.setFirst("Jane");
//        na.setLast("Doe");
//        na.setStreet("111 First Street");
//        na.setCity("Cleveland");
//        na.setState("OH");
//        na.setZip("44118");
//
//        dao.addAddress(na);
//
//        Address na2 = new Address();
//        na2.setFirst("Tony");
//        na2.setLast("Smith");
//        na2.setStreet("222 Second Street");
//        na2.setCity("Chicago");
//        na2.setState("IL");
//        na2.setZip("55555");
//
//        dao.addAddress(na2);
//
//        Address na3 = new Address();
//        na3.setFirst("Brett");
//        na3.setLast("Doe");
//        na3.setStreet("333 Third Street");
//        na3.setCity("Knoxville");
//        na3.setState("TN");
//        na3.setZip("33333");
//
//        dao.addAddress(na3);
//        
//        Map<SearchTerm, String> criteria = new HashMap<>();
//        criteria.put(SearchTerm.LAST, "Smith");
//        List<Address> aList = dao.searchAddresses(criteria);
//        assertEquals(1, aList.size());
//        assertEquals(na2, aList.get(0));
//        
//        criteria.put(SearchTerm.LAST, "Doe");
//        aList = dao.searchAddresses(criteria);
//        assertEquals(2, aList.size());
//        
//        criteria.put(SearchTerm.CITY, "Cleveland");
//        aList = dao.searchAddresses(criteria);
//        assertEquals(1, aList.size());
//        assertEquals(na, aList.get(0));
//        
//        criteria.put(SearchTerm.CITY, "Knoxville");
//        aList = dao.searchAddresses(criteria);
//        assertEquals(1, aList.size());
//        assertEquals(na3, aList.get(0));
//        
//        criteria.put(SearchTerm.CITY, "New York City");
//        aList = dao.searchAddresses(criteria);
//        assertEquals(0, aList.size());
//    }
}
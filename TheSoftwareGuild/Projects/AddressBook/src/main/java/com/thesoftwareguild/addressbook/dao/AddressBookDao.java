/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbook.dao;

import com.thesoftwareguild.addressbook.model.Address;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bretthanson
 */
public interface AddressBookDao {
    
    public Address addAddress(Address address);
    
    public void removeAddress(int addressId);
    
    public void updateAddress(Address address);
    
    public List<Address> getAllAddresses();
    
    public Address getAddressById(int addressId);
    
    public List<Address> searchAddresses(Map<SearchTerm, String> criteria); 
    
    public List<Address> searchAddressesByFirst(String first);
        
    public List<Address> searchAddressByLast(String last);
    
    
    
}


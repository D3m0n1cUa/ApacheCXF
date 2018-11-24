package com.sergii.galkin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "nif", "name", "address", "phone" })
@XmlRootElement(name = "Person")
public class Person {

    private String nif;
    private String name;
    private String address;
    private String phone;

    public Person() {

    }

    public Person(String nif, String name, String address, String phone) {
	super();
	this.nif = nif;
	this.name = name;
	this.address = address;
	this.phone = phone;
    }

    public String getNif() {
	return nif;
    }

    public void setNif(String nif) {
	this.nif = nif;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

}

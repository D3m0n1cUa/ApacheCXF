package com.sergii.galkin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "nif", "name", "address", "phone" })
@XmlRootElement(name = "PersonType")
@Entity
@Table(name = "PERSONS")
public class Person implements Serializable {

    @Id
    @Column(name = "NIF")
    private String nif;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
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

    @Override
    public String toString() {
	return "Person [nif=" + nif + ", name=" + name + ", address=" + address + ", phone=" + phone + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nif == null) ? 0 : nif.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Person other = (Person) obj;
	if (nif == null) {
	    if (other.nif != null)
		return false;
	} else if (!nif.equals(other.nif))
	    return false;
	return true;
    }

}

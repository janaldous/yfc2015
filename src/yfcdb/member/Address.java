package yfcdb.member;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Jat Torres
 * @version 21.02.2018
 */
@Embeddable
public class Address {

	@Column(name="address_line_1")
    private String address1;
	
	@Column(name="address_line_2")
    private String address2;
    
    @Column(name="address_city")
    private String city;
    
    @Column(name="address_province")
    private String province;
    
    @Column(name="address_postcode")
    private String postalCode;

    public Address() {}

    public Address(String address1, String address2, String city, 
    		String province, String postalCode) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
    
	public String getFullAddress() {
    	return address1 + ", " + address2 + ", " + city + ", "
    			+ province + " " + postalCode;
    }
}

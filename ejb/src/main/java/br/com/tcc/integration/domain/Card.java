package br.com.tcc.integration.domain;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="card")
@XmlAccessorType(XmlAccessType.FIELD)
public class Card implements Serializable {

	private static final long serialVersionUID = -311894583898572867L;

	private int id;
	private String number;

	public Card(){}
	
	public Card(int id, String cardNumber) {
		this.id = id;
		this.number = cardNumber;
	}

	public int getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "CardDomain [id=" + id + ", number=" + number + "]";
	}

}

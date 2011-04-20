package br.com.tcc.integration.domain;
import java.io.Serializable;


public class CardDomain implements Serializable {

	private static final long serialVersionUID = -311894583898572867L;

	private final int id;
	private final String number;

	public CardDomain(int id, String cardNumber) {
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

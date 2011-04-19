package br.com.tcc.integration.hornetq;
import java.io.Serializable;


public class CardModel implements Serializable {

	private static final long serialVersionUID = -311894583898572867L;

	private final int id;
	private final String number;

	public CardModel(int id, String cardNumber) {
		this.id = id;
		this.number = cardNumber;
	}

	public int getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

}

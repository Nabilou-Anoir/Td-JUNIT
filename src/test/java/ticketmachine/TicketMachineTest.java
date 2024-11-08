package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de
	// l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		// Les montants ont été correctement additionnés
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
	}
	@Test
	//n'imprime pas si pas assez d'argent
	void nimprimePassiBalanceInssufisance(){
		// Given : une machine vierge(initialisation dans @Beforeach)
		//when:On met pas assez d'argent
		machine.insertMoney(PRICE-1);
		//Then
		assertFalse(machine.printTicket(),"pas assez d'argent: on ne doit pas imprimer");
	}
@Test
	void refundReturnsCorrectAmount() {
		machine.insertMoney(30);
		int refundedAmount = machine.refund();
		assertEquals(30, refundedAmount, "Le montant remboursé est incorrect");
	}
	@Test
	void refundBalanceAZero() {
		machine.insertMoney(40);
		machine.refund();
		assertEquals(0, machine.getBalance(), "La balance n'est pas remise à zéro après remboursement");
	}
	@Test
	void pasDeNegativeAmount() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			machine.insertMoney(-10);
		});
		assertEquals("Amount must be positive", exception.getMessage(), "L'exception pour montant négatif n'a pas le bon message");
	}
	@Test
	void PasTiketNegativeAmount() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new TicketMachine(-PRICE);
		});
		assertEquals("Ticket price must be positive", exception.getMessage(), "L'exception pour prix de ticket négatif n'a pas le bon message");
	}

}

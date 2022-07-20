import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TicketManagerTest {

    Ticket item1 = new Ticket(1, 23_683, "SVO", "IST",595);
    Ticket item2 = new Ticket(2, 94_896, "LED", "LHR",540);
    Ticket item3 = new Ticket(3, 158_201, "LED", "JFK",990);
    Ticket item4 = new Ticket(4, 83_146, "LED", "ZRH",565);
    Ticket item5 = new Ticket(5, 84_381, "LED", "BUD",455);
    Ticket item6 = new Ticket(6, 50_665, "LED", "BUD",1_790);
    Ticket item7 = new Ticket(7, 52_265, "LED", "BUD",1_570);
    Ticket item8 = new Ticket(8, 196_620, "LED", "DXB",505);

    @Test
    public void shouldAddAndRemoveTickets() {
        TicketManager manager = new TicketManager(new TicketRepository ());
        manager.addNewTicket(item1);
        manager.addNewTicket(item2);
        manager.addNewTicket(item3);
        manager.removeTicketById(item3.getId());

        Ticket[] expected = {item1, item2};

        assertArrayEquals(expected, manager.getSavedTickets());
    }

    @Test
    public void shouldFindAndSortTickets() {
        TicketManager manager = new TicketManager(new TicketRepository ());
        manager.addNewTicket(item1);
        manager.addNewTicket(item2);
        manager.addNewTicket(item3);
        manager.addNewTicket(item4);
        manager.addNewTicket(item5);
        manager.addNewTicket(item6);
        manager.addNewTicket(item7);
        manager.addNewTicket(item8);

        Ticket[] expected = {item6, item7, item5};

        assertArrayEquals(expected, manager.findAll("LED", "BUD"));
    }
    @Test
    public void shouldFindAndSortTicketsIfRepoIsEmpty() {
        TicketManager manager = new TicketManager(new TicketRepository ());


        Ticket[] expected = {};

        assertArrayEquals(expected, manager.findAll("LED", "BUD"));
    }
    @Test
    public void shouldFindAndSortTicketsIfRepoHasOneItem() {
        TicketManager manager = new TicketManager(new TicketRepository ());
        manager.addNewTicket(item8);

        Ticket[] expected = {item8};

        assertArrayEquals(expected, manager.findAll("LED", "DXB"));
    }

    @Test
    public void shouldFindAndSortTicketsIfRepoHasOneItemButItDoesNotQualify () {
        TicketManager manager = new TicketManager(new TicketRepository ());
        manager.addNewTicket(item1);

        Ticket[] expected = {};

        assertArrayEquals(expected, manager.findAll("LED", "BUD"));
    }
}

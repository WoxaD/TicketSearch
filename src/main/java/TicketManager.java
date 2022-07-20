import java.util.Arrays;

public class TicketManager {

    private final TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void addNewTicket(Ticket newTicket) {
        repository.add (newTicket);
    }

    public void removeTicketById(int id) {
        repository.removeById(id);
    }

    public Ticket[] getSavedTickets() {
        return repository.getSavedTickets();
    }

    public Ticket [] findAll(String departureAirport, String arrivalAirport) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.getSavedTickets()) {
            if (matches(ticket, departureAirport, arrivalAirport)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
                Arrays.sort(result);
            }
        }
        return result;
    }

    public boolean matches(Ticket ticket, String departureAirport, String arrivalAirport) {

        if (ticket.getDepartureAirport().contains(departureAirport)) {
            return ticket.getArrivalAirport().contains(arrivalAirport);
        }
        return false;
    }
}

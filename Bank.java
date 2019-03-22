import java.util.Map;
import java.util.HashMap;

public class Bank {
    private Map<String, Client> accounts;

    Bank() {
        accounts = new HashMap<String, Client>();
        Client client1 = new Client("Didier", "2801", 1000);
        Client client2 = new Client("Niek", "1464", 1500);
        Client client3 = new Client("Ricardo", "2190", 1750);
        accounts.put("1", client1);
        accounts.put("2", client2);
        accounts.put("3", client3);

    }

    public Client get(String rekeningnummer) {
        if (accounts.containsKey(rekeningnummer)) {
            return accounts.get(rekeningnummer);
        } else {
            return null;
        }
    }
}
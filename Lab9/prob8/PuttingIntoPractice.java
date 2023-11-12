package prob8;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PuttingIntoPractice{
    public static void main(String ...args){    
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        
        List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);
		
		List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300), 
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),	
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );	
        
        
		// Query 1: Find all transactions from year 2011 and sort them by value (small to high).
		List<Transaction> result = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()))
                .collect(Collectors.toList());
		System.out.println("1. Transactions from year 2011:");
        result.forEach(System.out::println);
        
        // Query 2: What are all the unique cities where the traders work?            
        List<String> uniqueCities = traders.stream()
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("2. Unique cities where the traders work:");
        System.out.println("Unique Cities: " + uniqueCities);
       
        // Query 3: Find all traders from Cambridge and sort them by name.
        List<Trader> cambridgeTraders = traders.stream()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
                .collect(Collectors.toList());
        System.out.println("3. All traders from Cambridge and sort them by name:");
        System.out.println("Traders from Cambridge sorted by name: " + cambridgeTraders);
         
        // Query 4: Return a string of all traders names sorted alphabetically.
        String sortedNames = traders.stream()
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println("4. All traders names sorted alphabetically:");
        System.out.println("Sorted Trader Names: " + sortedNames);

        // Query 5: Are there any trader based in Milan?
        boolean anyTraderInMilan = traders.stream()
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println("5. Are there any trader based in Milan?:");
        if (anyTraderInMilan) {
            System.out.println("There is at least one trader based in Milan.");
        } else {
            System.out.println("There are no traders based in Milan.");
        }
   
        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        transactions.stream()
        .filter(transaction -> transaction.getTrader().getCity().equals("Milan"))
        .forEach(transaction -> transaction.getTrader().setCity("Cambridge"));
        System.out.println("6. Update all transactions:");
        transactions.forEach(System.out::println);
        
        // Query 7: What's the highest value in all the transactions?
        int highestValue = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max()
                .orElse(0);
        System.out.println("7. What's the highest value in all the transactions?:");
        System.out.println("The highest value in all transactions is: " + highestValue);

    }
}

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class PerformanceComparisonMain {
	  public static void main(String[] args) {
	    
			long currentTime=System.currentTimeMillis();

			long sum = Stream.iterate(0, x -> x + 1)
							.limit(100000)
							.map(i ->(int)Math.sqrt(i))
							.map(number->performComputation(number))
							.reduce(0,Integer::sum);

			System.out.println("[NO PARALLEL] Resultado -> " + sum);
			long endTime=System.currentTimeMillis();
			System.out.println("[NO PARALLEL] Time taken to complete: "+ (endTime - currentTime) / 1000 + " segundos");

			currentTime=System.currentTimeMillis();

			sum = Stream.iterate(0, x -> x + 1)
							.limit(100000)
							.parallel()
							.map(i ->(int)Math.sqrt(i))
							.map(number->performComputation(number))
							.reduce(0,Integer::sum);

			System.out.println("[PARALLEL] Resultado -> " + sum);
			endTime=System.currentTimeMillis();
			System.out.println("[PARALLEL] Time taken to complete: "+ (endTime - currentTime) / 1000 + " segundos");


		  List<List<Integer>> listaDeListas = List.of(List.of(7, 1, 2),
				  List.of(2, 1, 2, 3),
				  List.of(1, 5, 2, 3, 4),
				  List.of(1, 2, 6, 3, 4, 5));

		  listaDeListas.stream()
				  //.parallel()
				  .peek(list -> {
					  System.out.print("Voy a procesar la lista - > ");
					  System.out.println(list);
				  })
				  .flatMap(Collection::stream)
				  .distinct()
				  .map(n -> n * 2)
				  .peek(n -> {
					  System.out.print("Voy a procesar el numero - > ");
					  System.out.println(n);
				  })
				  .sorted((x, y) -> y - x)
				  .forEach(System.out::println);
	  }
	  
	  public static int performComputation(int number)
	  {
	    int sum=0;
	    for (int i = 1; i < 100000; i++) {
	      int div=(number/i);
	      sum+=div;
	      
	    }
	    return sum;
	  }
	}
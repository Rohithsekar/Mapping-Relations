package com.example.mapping;

import com.example.mapping.entity.Sim;
import com.example.mapping.entity.SimHistory;
import com.example.mapping.entity.SimProvider;
import com.example.mapping.many_to_one_bidirectional.Movie;
import com.example.mapping.many_to_one_bidirectional.Ticket;
import com.example.mapping.many_to_one_unidirectional.Author;
import com.example.mapping.many_to_one_unidirectional.Book;
import com.example.mapping.one_to_many_bidirectional.Account;
import com.example.mapping.one_to_many_bidirectional.Customer;
import com.example.mapping.one_to_many_unidirectional.Person;
import com.example.mapping.one_to_many_unidirectional.Vehicle;
import com.example.mapping.one_to_one_bidirectional.Employee;
import com.example.mapping.one_to_one_bidirectional.EmployeeAddress;
import com.example.mapping.one_to_one_unidirectional.Address;
import com.example.mapping.one_to_one_unidirectional.Order;
import com.example.mapping.repository.*;
import com.example.mapping.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static com.example.mapping.constants.Constants.ADMIN;

@SpringBootApplication
	public class MappingApplication implements CommandLineRunner {
		@Autowired
		private OrderRepository orderRepository;

		@Autowired
		private EmployeeRepository employeeRepository;

		@Autowired
		private PersonRepository personRepository;

		@Autowired
		private BookRepository bookRepository;

		@Autowired
		private AuthorRepository authorRepository;

		@Autowired
		private CustomerRepository customerRepository;

		@Autowired
		private TicketRepository ticketRepository;

		@Autowired
		private MovieRepository movieRepository;

		@Autowired
		private SimRepository simRepository;


		public void testOneToOneUnidirectionalMapping(){
			/**
			 * Uni-directional means we can navigate between associated entity objects in only one direction.
			 * If you have an on-to-one unidirectional mapping between order and address entity, we can
			 * get the address entity from an order entity since the unidirectional relation is established
			 * there. We can't navigate from an address entity to an order entity because it lacks the
			 * reference to the order entity in its class.
			 */
			// create Order object
			Order order = new Order();

			order.setOrderTrackingNumber("1000");
			order.setStatus("COMPLETED");
			order.setTotalPrice(new BigDecimal(2000));
			order.setTotalQuantity(5);

			Address billingAddress = new Address();
			billingAddress.setStreet("Kambar Street");
			billingAddress.setCity("Madurai");
			billingAddress.setState("Tamil Nadu");
			billingAddress.setCountry("India");
			billingAddress.setZipCode("6047");

			//establishing the uni-directional nature
			order.setBillingAddress(billingAddress);

			// save both order and address ( Cascade type - ALL)
			orderRepository.save(order);

		}

		public void testOneToOneBidirectionalMapping(){

			/**
			 * When they refer to bidirectional, they are talking about the Java code and how you can
			 * navigate from both sides of the relationship, not about the database representation of
			 * foreign key columns. The database representation is a result of how you configure the JPA
			 * mapping in your entity classes.
			 *
			 * The bidirectional nature allows you to navigate from both sides. For example, after saving
			 * the entities, you can get an Employee and retrieve their associated EmployeeAddress, or you
			 * can get an EmployeeAddress and retrieve the associated Employee.
			 *
			 * However, in the database, only one table (EmployeeAddress in this case) will have the foreign
			 * key column (employee_id). The concept of bidirectionality is about how the entities are connected
			 * in Java code and how you can navigate between them, not about having foreign key columns
			 * in both tables in the database.
			 */

			// create employee object
			Employee employee = new Employee();

			employee.setName("Adam");


			EmployeeAddress employeeAddress = new EmployeeAddress();
			employeeAddress.setState("Tamil Nadu");
			employeeAddress.setCountry("India");

			//Establishing the bidirectional nature
			employee.setEmployeeAddress(employeeAddress);
			employeeAddress.setEmployee(employee);
			// save both employee and employeeAddress  Cascade type - ALL)
			employeeRepository.save(employee);


		}

		public void testOneToManyUniDirectionalMapping(){
			// create Order object
			Person person = new Person();

			Vehicle car = new Vehicle();
			car.setCountry("US");
			car.setState("Texas");

			// add car to person
			Set<Vehicle> vehicles = new HashSet<>();
			vehicles.add(car);



			Vehicle motorCycle = new Vehicle();
			motorCycle.setState("Texas");
			motorCycle.setCountry("US");

			// add orderItem2 to order
			vehicles.add(motorCycle);

			person.setName("John");
			person.setVehicles(vehicles);

			personRepository.save(person);
		}

		public void testDeleteOneToManyUniDirectionalMapping(){

			/**Deleting the "one" side entity object of the uni-directional OneToMany relation results in disappearance
			of the referencing column value in the "many" side entity table in the SQL database.
			 */

			personRepository.deleteById(1L);
		}

		public void testManyToOneUniDirectionalMapping(){
			Author author = new Author();
			author.setName("Leo Tolstoy");

			Book book1 = new Book();
			book1.setTitle("War and Peace");
			book1.setYear(1867);
			book1.setAuthor(author);

			Book book2 = new Book();
			book2.setTitle("Anna Karenina");
			book2.setYear(1877);
			book2.setAuthor(author);

			// Create a list and add both books to it
			List<Book> books = new ArrayList<>();
			books.add(book1);
			books.add(book2);

			bookRepository.saveAll(books);

		}

		public void testDeleteManyToOneUniDirectionalMapping(){
			/**Deleting the "one" side entity object of the uni-directional ManyToOne relation results in
			 * DataIntegrityViolationException since the many-side table in the database holds the foreign key
			 * reference to the one side of the relationship and hibernate does not allow deletion of the one-side
			 * entity in such case
			 */
			authorRepository.deleteById(1L);
		}

		public void testOneToManyBiDirectionalMapping(){

			// create Order object
			Customer customer = new Customer();
			customer.setName("Abraham");

			Account account1 = new Account();
			account1.setCustomer(customer);

			Account account2 = new Account();
			account2.setCustomer(customer);

			customer.getAccounts().add(account1);
			customer.getAccounts().add(account2);

			customerRepository.save(customer);
		}

		public void testDeleteOneToManyBiDirectionalMapping(){
			customerRepository.deleteById(1L);
		}

		public void testManyToOneBiDirectionalMapping(){

			Movie movie = new Movie();
			movie.setName("Titanic");

			Ticket ticket1 = new Ticket();
			ticket1.setLocalDateTime(LocalDateTime.now());
			ticket1.setSeatNumber("H10");
			ticket1.setMovie(movie);

			Ticket ticket2 = new Ticket();
			ticket2.setLocalDateTime(LocalDateTime.now());
			ticket2.setSeatNumber("H11");
			ticket2.setMovie(movie);

			movie.getTickets().add(ticket1);
			movie.getTickets().add(ticket2);

			Set<Ticket> ticketSet = new HashSet<>();
			ticketSet.add(ticket1);
			ticketSet.add(ticket2);

			ticketRepository.saveAll(ticketSet);
		}

		public void testDeleteManyToOneBiDirectionalMapping(){
			movieRepository.deleteById(1L);

		}


	//Establishing the uni-directional one-to-many relation between the sim and simHistory entities.
	//We can only navigate to the simHistory with its associated sim entity.However, we cannot
	//navigate to the sim entity via simHistory entity since it lacks reference to the sim entity in its
	//entity class.
		public void testSimWorkFlowMapping(){

			SimProvider simProvider = new SimProvider();
			simProvider.setSimType("Airtel");
			simProvider.setIsActive(1);

			long currentTimeStamp = Instant.now().getEpochSecond();

			simProvider.setCreatedAt(currentTimeStamp);
			simProvider.setCreatedBy(ADMIN);


			Sim sim = new Sim();
			sim.setSimNumber(Utility.generateRandomTenDigitNumber());
			sim.setSimProvider(simProvider);
			sim.setPurchasedFrom("Velachery");
			// Get today's date using java.util.Calendar
			Calendar calendar = Calendar.getInstance();
			Date todayDate = calendar.getTime();
			sim.setPurchasedDate(todayDate);
			sim.setPurchasedPrice(2000D);
			sim.setIsActive(1);
			sim.setIsRecharged(1);
			//Establishing the uni-directional one-to-one relation between the sim and simProvider entities.
			//We can only navigate to the simProvider with its associated sim entity.However, we cannot
			//navigate to the sim entity via simProvider entity since it lacks reference to the sim entity in its
			//entity class.
			sim.setCreatedAt(simProvider.getCreatedAt());
			sim.setCreatedBy(simProvider.getCreatedBy());
			simRepository.save(sim);



			SimHistory simHistory = new SimHistory();
			sim.getSimHistorySet().add(simHistory);
			simHistory.setSimProviderID(sim.getSimProvider().getId());
			simHistory.setSimNumber(sim.getSimNumber());
			simHistory.setImeiNumber(Utility.generateRandom16DigitNumber());
			simHistory.setPurchasedDate(sim.getPurchasedDate());
			simHistory.setPurchasedFrom(sim.getPurchasedFrom());
			simHistory.setPurchasedPrice(sim.getPurchasedPrice());
			simHistory.setIsRecharged(sim.getIsRecharged());
			simHistory.setIsActive(sim.getIsActive());
			// Set the createdAt and createdBy fields for the SimHistory entity from the Sim object
			simHistory.setCreatedAt(sim.getCreatedAt());
			simHistory.setCreatedBy(sim.getCreatedBy());


			simRepository.save(sim);
		}

		public static void main(String[] args) {
			SpringApplication.run(MappingApplication.class, args);
		}


		@Override
		public void run(String... args) throws Exception {
			// Call the method here, which will be executed after Spring context initialization
//			testOneToOneUnidirectionalMapping();
//			testOneToOneBidirectionalMapping();
//			testOneToManyUniDirectionalMapping();
//			testDeleteOneToManyUniDirectionalMapping();
//			testManyToOneUniDirectionalMapping();
//			testDeleteManyToOneUniDirectionalMapping();
//			testOneToManyBiDirectionalMapping();
//			testDeleteOneToManyBiDirectionalMapping();
//			testManyToOneBiDirectionalMapping();
//			testDeleteManyToOneBiDirectionalMapping();
			testSimWorkFlowMapping();

		}
	}

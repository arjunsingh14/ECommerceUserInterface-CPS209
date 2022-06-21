/**
 * Name: Arjun Singh Bhandal
 * Student ID: 501088530
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;





/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{
	//ArrayList<Product>  products = new ArrayList<Product>();
	TreeMap<String, Product> products = new TreeMap<String, Product>();
	ArrayList<Customer> customers = new ArrayList<Customer>();	
	Map<String, Integer> stats = new HashMap<String, Integer>();

	ArrayList<ProductOrder> orders   			= new ArrayList<ProductOrder>();
	ArrayList<ProductOrder> shippedOrders = new ArrayList<ProductOrder>();

	// These variables are used to generate order numbers, customer id's, product id's 
	int orderNumber = 500;
	int customerId = 900;
	int productId = 700;

	// General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
	String errMsg = null;

	// Random number generator
	Random random = new Random();
	


	//File reading for ArrayList products

	/*
	private ArrayList<Product> createProduct() throws IOException{
			File inputFile = new File("products.txt");
			ArrayList<Product> products = new ArrayList<Product>();
			Scanner in = new Scanner(inputFile);
			while (in.hasNextLine()){
				String category = in.nextLine();
				String name = in.nextLine();
				String price = in.nextLine();
				String stock = in.nextLine();
				String additional = in.nextLine();
				Scanner priceDouble = new Scanner(price);
				double actualPrice = priceDouble.nextDouble();
				if (category.equalsIgnoreCase("books")){
					Scanner stocks = new Scanner (stock);
					Scanner works = new Scanner (additional);
					int paperbackStock = stocks.nextInt();
					int hardcoverStock = stocks.nextInt();
					works.useDelimiter(":");
					String title = works.next();
					String author = works.next();
					int year = works.nextInt();
					products.add(new Book("Book", generateProductId(), actualPrice, paperbackStock, hardcoverStock, title, author, year));
				}
				else{
					Scanner stocks = new Scanner (stock);
					int stockInt = stocks.nextInt();
					if (category.equalsIgnoreCase("computers")){
						products.add(new Product(name, generateProductId(), actualPrice, stockInt, Product.Category.COMPUTERS));

					}
					else if (category.equalsIgnoreCase("furniture")){
						products.add(new Product(name, generateProductId(), actualPrice, stockInt, Product.Category.FURNITURE));

					}
					else if (category.equalsIgnoreCase("general")){
						products.add(new Product(name, generateProductId(), actualPrice, stockInt, Product.Category.GENERAL));

					}
				}
			}
			
			
			
			
			return products;
			
		
	}
	*/


	/**
	 * Reads the file products.txt and goes through the products.txt format
	 * to create new products based on the given information and store them in
	 * an ArrayList products
	 * @return products
	 * @throws IOException if the file does not exist
	 */

	private TreeMap<String, Product> createProduct() throws IOException{
			File inputFile = new File("products.txt");
			TreeMap<String, Product> products = new TreeMap<String,Product>();
			Scanner in = new Scanner(inputFile);
			//Goes through products.txt line by line
			while (in.hasNextLine()){
				String id = generateProductId();
				String category = in.nextLine();
				String name = in.nextLine();
				String price = in.nextLine();
				String stock = in.nextLine();
				String additional = in.nextLine();
				Scanner priceDouble = new Scanner(price);
				double actualPrice = priceDouble.nextDouble();
				//In case it is a book, reads paperback stock, hardcover stock and the fifth line
				if (category.equalsIgnoreCase("books")){
					Scanner stocks = new Scanner (stock);
					Scanner works = new Scanner (additional);
					int paperbackStock = stocks.nextInt();
					int hardcoverStock = stocks.nextInt();
					works.useDelimiter(":");
					String title = works.next();
					String author = works.next();
					int year = works.nextInt();
					
					products.put(id, new Book(name, id, actualPrice, paperbackStock, hardcoverStock, title, author, year));
					stats.put(id, 0);
				}
				//If not a book, does not get the fifth line
				else{
					Scanner stocks = new Scanner (stock);
					int stockInt = stocks.nextInt();
					;
					if (category.equalsIgnoreCase("computers")){
						products.put(id, new Product(name, id, actualPrice, stockInt, Product.Category.COMPUTERS));
						stats.put(id, 0);

					}
					else if (category.equalsIgnoreCase("furniture")){
						products.put(id, new Product(name, id, actualPrice, stockInt, Product.Category.FURNITURE));
						stats.put(id, 0);
					}
					else if (category.equalsIgnoreCase("general")){
						products.put(id, new Product(name, id, actualPrice, stockInt, Product.Category.GENERAL));
						stats.put(id, 0);
					}
					else if (category.equalsIgnoreCase("clothing")){
						products.put(id, new Product(name, id, actualPrice, stockInt, Product.Category.CLOTHING));
						stats.put(id, 0);
					}
				}
			}
			
			
			
			in.close();
			return products;
			
		
	}
	public ECommerceSystem()
	{
		// NOTE: do not modify or add to these objects!! - the TAs will use for testing
		// If you do the class Shoes bonus, you may add shoe products

		// Create some products
		try {
			products = createProduct();
			
		} catch (IOException e) {
			//catches exception if raised
			e.printStackTrace();
		}
		// products.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
		// products.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
		// products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney", 2021));
		// products.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
		// products.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
		// products.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
		// products.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast", 1997));
		// products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive", 1963));
		// products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "How to Teach Programming", "T. McInerney", 2001));
		// products.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));
		// products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn More", "T. McInerney", 2022));
		// int[][] stockCounts = {{4, 2}, {3, 5}, {1, 4,}, {2, 3}, {4, 2}};
		// products.add(new Shoes("Prada", generateProductId(), 595.0, stockCounts));

		// Create some customers
		customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin", new Cart(new ArrayList<CartItem>())));
		customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin", new Cart(new ArrayList<CartItem>())));
		customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine", new Cart(new ArrayList<CartItem>())));
		customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach", new Cart(new ArrayList<CartItem>())));
	}

	private String generateOrderNumber()
	{
		return "" + orderNumber++;
	}

	private String generateCustomerId()
	{
		return "" + customerId++;
	}

	private String generateProductId()
	{
		return "" + productId++;
	}

	public String getErrorMessage()
	{
		return errMsg;
	}

	// public void printAllProducts()
	// {
	// 	for (Product p : products)
	// 		p.print();
	// }


	/**
	 * Iterates through the TreeMap products and prints
	 * every product
	 */
	public void printAllProducts()
	{
		for (String key : products.keySet())
			products.get(key).print();
	}
	public void printCart(String customerID) {
		int index = customers.indexOf(new Customer(customerID));
		if (index == -1)
		{
			throw new UnknownCustomerException("Invalid Customer");
		}
		Customer cust = customers.get(index);
		for (CartItem cart : cust.getCart().getCartItems())
			cart.print();	
	}
	// public void printAllBooks()
	// {
	// 	for (Product p : products)
	// 	{
	// 		if (p.getCategory() == Product.Category.BOOKS)
	// 			p.print();
	// 	}
	// }

	/**
	 * Iterates through the TreeMap products and 
	 * prints all objects that are Books
	 */
	public void printAllBooks()
	{
		for (String key: products.keySet())
		{
			if (products.get(key).getCategory() == Product.Category.BOOKS)
				products.get(key).print();
		}
	}

	// public ArrayList<Book> booksByAuthor(String author)
	// {
	// 	ArrayList<Book> books = new ArrayList<Book>();
	// 	for (Product p : products)
	// 	{
	// 		if (p.getCategory() == Product.Category.BOOKS)
	// 		{
	// 			Book book = (Book) p;
	// 			if (book.getAuthor().equals(author))
	// 				books.add(book);
	// 		}
	// 	}
	// 	return books;
	// }
	
	/**
	 * Given an author's name, print all the books associates with tha author
	 * @param author
	 * @return books
	 */
	public ArrayList<Book> booksByAuthor(String author)
	{
		ArrayList<Book> books = new ArrayList<Book>();
		for (String key : products.keySet())
		{
			if (products.get(key).getCategory() == Product.Category.BOOKS)
			{
				Book book = (Book) products.get(key);
				if (book.getAuthor().equals(author))
					books.add(book);
			}
		}
		return books;
	}

	public void printAllOrders()
	{
		for (ProductOrder o : orders)
			o.print();
	}

	public void printAllShippedOrders()
	{
		for (ProductOrder o : shippedOrders)
			o.print();
	}

	public void printCustomers()
	{
		for (Customer c : customers)
			c.print();
	}
	/*
	 * Given a customer id, print all the current orders and shipped orders for them (if any)
	 */
	public void printOrderHistory(String customerId)
	{
		// Make sure customer exists
		int index = customers.indexOf(new Customer(customerId));
		if (index == -1)
		{
			throw new UnknownCustomerException("Customer does not exist");
		}	
		System.out.println("Current Orders of Customer " + customerId);
		for (ProductOrder order: orders)
		{
			if (order.getCustomer().getId().equals(customerId))
				order.print();
		}
		System.out.println("\nShipped Orders of Customer " + customerId);
		for (ProductOrder order: shippedOrders)
		{
			if (order.getCustomer().getId().equals(customerId))
				order.print();
		}
	}

	// public String orderProduct(String productId, String customerId, String productOptions)
	// {
	// 	// Get customer
	// 	int index = customers.indexOf(new Customer(customerId));
	// 	if (index == -1)
	// 	{
	// 		throw new UnknownCustomerException("Customer does not exist");
	// 	}
	// 	Customer customer = customers.get(index);

	// 	// Get product 
	// 	index = products.indexOf(new Product(productId));
	// 	if (index == -1)
	// 	{
	// 		throw new UnknownProductException("Product does not exist");
	// 	}
	// 	Product product = products.get(index);

	// 	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
	// 	if (!product.validOptions(productOptions))
	// 	{
	// 		throw new InvalidProductException("Invalid product option");
	// 	}
	// 	// Is it in stock?
	// 	if (product.getStockCount(productOptions) == 0)
	// 	{
	// 		throw new NoStockException("No stock");
	// 	}
	// 	// Create a ProductOrder
	// 	ProductOrder order = new ProductOrder(generateOrderNumber(), product, customer, productOptions);
	// 	product.reduceStockCount(productOptions);

	// 	// Add to orders and return
	// 	orders.add(order);

	// 	return order.getOrderNumber();
	// }







	/**
	 * Creates an ProductOrder object if productId, customerId and productOptions are valid.
	 * Adds the ProductOrderObject to the ArrayList orders as well as updates the respective
	 * key-value in the HashMap stats
	 * @throws UnknownCustomerException
	 * @throws UnknownProductException
	 * @throws InvalidProductExcetion
	 * @param productId
	 * @param customerId
	 * @param productOptions
	 * @return the newly generated order number
	 */
	public String orderProduct(String productId, String customerId, String productOptions)
	{
		// Get customer
		int index = customers.indexOf(new Customer(customerId));
		if (index == -1)
		{
			throw new UnknownCustomerException("Customer does not exist");
		}
		Customer customer = customers.get(index);

		// Get product 
		
		if(products.get(productId) == null){
			throw new UnknownProductException("Invalid product");
		}
		Product product = products.get(productId);
		// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
		if (!product.validOptions(productOptions))
		{
			throw new InvalidProductException("Invalid product option");
		}
		// Is it in stock?
		if (product.getStockCount(productOptions) == 0)
		{
			throw new NoStockException("No stock");
		}
		// Create a ProductOrder
		ProductOrder order = new ProductOrder(generateOrderNumber(), product, customer, productOptions);
		product.reduceStockCount(productOptions);

		// Add to orders and return
		stats.put(productId, stats.get(productId)+1);
		orders.add(order);

		return order.getOrderNumber();
	}

	/*
	 * Create a new Customer object and add it to the list of customers
	 */

	public void createCustomer(String name, String address)
	{
		// Check to ensure name is valid
		if (name == null || name.equals(""))
		{
			throw new InvalidCustNameException("Please add a name");
		}
		// Check to ensure address is valid
		if (address == null || address.equals(""))
		{
			throw new InvalidAddressException("Please add an address");
		}
		Customer customer = new Customer(generateCustomerId(), name, address, new Cart(new ArrayList<CartItem>()));
		customers.add(customer);
	}
	/**
	 * Adds order specificied by OrderNumber to shippedOrders and
	 * removes it from shippedOrders
	 * @param orderNumber
	 */
	public void shipOrder(String orderNumber)
	{
		// Check if order number exists
		int index = orders.indexOf(new ProductOrder(orderNumber,null,null,""));
		if (index == -1)
		{
			throw new InvalidOrderNumberException("Invalid order number");
		}
		ProductOrder order = orders.get(index);
		orders.remove(index);
		shippedOrders.add(order);
		;
	}

	/*
	 * Cancel a specific order based on order number
	 */
	public void cancelOrder(String orderNumber)
	{
		// Check if order number exists
		int index = orders.indexOf(new ProductOrder(orderNumber,null,null,""));
		if (index == -1)
		{
			throw new InvalidOrderNumberException("Invalid order number");
		}
		ProductOrder order = orders.get(index);
		Product prod = order.getProduct();
		stats.put(prod.getId(), stats.get(prod.getId()) - 1);
		orders.remove(index);
	}

	//Sort products by increasing price
	public void sortByPrice()
	{

		ArrayList <Product> temp = new ArrayList<Product>();
		for (String key: products.keySet()){
			temp.add(products.get(key));
		}
		Collections.sort(temp, new PriceComparator());
		for (Product p:temp){
			p.print();
		}
	}

	private class PriceComparator implements Comparator<Product>
	{
		public int compare(Product a, Product b)
		{
			if (a.getPrice() > b.getPrice()) return 1;
			if (a.getPrice() < b.getPrice()) return -1;	
			return 0;
		}
	}

	// Sort products alphabetically by product name
	public void sortByName()
	{
		ArrayList <Product> temp = new ArrayList<Product>();
		for (String key: products.keySet()){
			temp.add(products.get(key));
		}
		Collections.sort(temp, new NameComparator());
		for (Product p:temp){
			p.print();
		}
	}

	private class NameComparator implements Comparator<Product>
	{
		public int compare(Product a, Product b)
		{
			return a.getName().compareTo(b.getName());
		}
	}


	/**
	 * Adds a valid product to a valid customer's cart
	 * @param customerID
	 * @param productOptions
	 * @param productID
	 */
	public void addToCart(String customerID, String productOptions, String productID){
		int index = customers.indexOf(new Customer(customerID));
		if (index == -1)
		{
			throw new UnknownCustomerException("Customer does not exist");
		}
		Customer customer = customers.get(index);
		// Get product 
		if(products.get(productID) == null){
			throw new UnknownProductException("Invalid product");
		}
		Product product = products.get(productID);

		// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
		if (!product.validOptions(productOptions))
		{
			throw new InvalidProductException("Product option does not exist");
		}
		// Is it in stock?
		if (product.getStockCount(productOptions) == 0)
		{
			throw new NoStockException("No stock available");
		}
		Cart reference = customer.getCart();
		CartItem item = new CartItem(product, productOptions);
		reference.addToCart(item);

	}

	/**
	 * Removes a valid Product that is in a Customer's cart from the cart
	 * @param productID
	 * @param customerID
	 */
	public void remCartItem (String productID, String customerID){
		int index = customers.indexOf(new Customer(customerID));
		if (index == -1)
		{
			throw new UnknownCustomerException("Customer does not exist");
		}
		
		Customer customer = customers.get(index);
		Cart reference = customer.getCart();
		int i = reference.remCart(productID);
		if (i == 0){
			throw new UnknownProductException("Invalid product/product not in cart");
		}
		
	}

	/**
	 * Takes all the products in a valid customer's cart
	 * and adds it to the ArrayList orders and then removing it from
	 * the customer's cart
	 * @param customerID
	 */
	public void orderItems (String customerID) {
		int index = customers.indexOf(new Customer(customerID));
		if (index == -1)
		{
			throw new UnknownCustomerException("Customer does not exist"); 
		}
		Customer customer = customers.get(index);
		Cart reference = customer.getCart();
		index = 0;
		for (CartItem item:reference.getCartItems()){
			orderProduct(item.getProduct().getId(), customerID, item.getOption());
		}
		reference.setCart(new ArrayList<CartItem>());
	}
	// Sort products alphabetically by product name
	public void sortCustomersByName()
	{
		Collections.sort(customers);
	}

	public String printOptions (String id) {
		Product obj = products.get(id);
		if (obj.getCategory()==Product.Category.BOOKS){
			return "Options: [Paperback, Hardcover, EBook]";
		}
		return "No need to enter product option";

	}

	//Sorts and prints the products that have been orderer from most to least
	public void printStats(){
		final Map<String, Integer> sortedByCount = stats.entrySet()
            .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		for (String key : sortedByCount.keySet()){
			System.out.println("Product name:" + products.get(key).getName() + ", ID: " + key + ", Ordered: " + stats.get(key));

		}
	}
}



//Custom exceptions
class InvalidCustNameException extends RuntimeException {
	public InvalidCustNameException() {}
	public InvalidCustNameException(String message){
		super(message);
	}
}
class UnknownProductException extends RuntimeException {
	public UnknownProductException() {}
	public UnknownProductException(String message){
		super(message);
	}
}
class InvalidProductException extends RuntimeException {
	public InvalidProductException() {}
	public InvalidProductException(String message){
		super(message);
	}
}
class UnknownCustomerException extends RuntimeException {
	public UnknownCustomerException() {}
	public UnknownCustomerException(String message){
		super(message);
	}
}
class NoStockException extends RuntimeException {
	public NoStockException() {}
	public NoStockException(String message){
		super(message);
	}
}
class InvalidAddressException extends RuntimeException {
	public InvalidAddressException() {}
	public InvalidAddressException(String message){
		super(message);
	}
}
class InvalidOrderNumberException extends RuntimeException {
	public InvalidOrderNumberException() {}
	public InvalidOrderNumberException(String message){
		super(message);
	}
}
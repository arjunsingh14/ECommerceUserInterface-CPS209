/**
 * Name: Arjun Singh Bhandal
 * Student ID: 501088530
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions

		while (scanner.hasNextLine())
		{
			try {
				
				String action = scanner.nextLine();
	
				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
	
				else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
				{
					amazon.printAllProducts(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
				{
					amazon.printAllBooks(); 
				}
				else if (action.equalsIgnoreCase("BOOKSBYAUTHOR"))	// ship an order to a customer
				{
					String author = "";
	
					System.out.print("Author: ");
					if (scanner.hasNextLine())
						author = scanner.nextLine();
	
					ArrayList<Book> books = amazon.booksByAuthor(author);
					Collections.sort(books);
					for (Book book: books)
						book.print();
				}
				else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
				{
					amazon.printCustomers();	
				}
				else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
				{
					amazon.printAllOrders();	
				}
				else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
				{
					amazon.printAllShippedOrders();	
				}
				else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
				{
					String name = "";
					String address = "";
	
					System.out.print("Name: ");
					if (scanner.hasNextLine())
						name = scanner.nextLine();
	
					System.out.print("\nAddress: ");
					if (scanner.hasNextLine())
						address = scanner.nextLine();
	
					amazon.createCustomer(name, address);
					
				}
				else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
				{
					String orderNumber = "";
	
					System.out.print("Order Number: ");
					if (scanner.hasNextLine())
						orderNumber = scanner.nextLine();
	
					amazon.shipOrder(orderNumber);
					
				}
				else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer
				{
					String customerId = "";
	
					System.out.print("Customer Id: ");
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();
	
					// Prints all current orders and all shipped orders for this customer
					amazon.printOrderHistory(customerId);
					
				}
				else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
				{
					String productId = "";
					String customerId = "";
	
					System.out.print("Product Id: ");
					if (scanner.hasNextLine())
						productId = scanner.nextLine();
	
					System.out.print("\nCustomer Id: ");
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();
	
					String orderNumber = amazon.orderProduct(productId, customerId, "");
					System.out.println("Order #" + orderNumber);
					
				}
				else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
				{
					String productId = "";
					String customerId = "";
					String format = "";
	
					System.out.print("Product Id: ");
					if (scanner.hasNextLine())
						productId = scanner.nextLine();
	
					System.out.print("\nCustomer Id: ");
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();
	
					System.out.print("\nFormat [Paperback Hardcover EBook]: ");
					if (scanner.hasNextLine())
						format = scanner.nextLine();
	
					String orderNumber = amazon.orderProduct(productId, customerId, format);
					if (orderNumber != null)
					{
						System.out.println("Order #" + orderNumber);
					}
					else
					{
						System.out.println(amazon.getErrorMessage());
					}
				}
				else if (action.equalsIgnoreCase("ORDERSHOES")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
				{
					String productId = "";
					String customerId = "";
					String sizeColor = "";
	
					System.out.print("Product Id: ");
					if (scanner.hasNextLine())
						productId = scanner.nextLine();
	
					System.out.print("\nCustomer Id: ");
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();
	
					System.out.print("\nSize (6, 7, 8, 9, 10) and Color (Black or Brown): ");
					if (scanner.hasNextLine())
						sizeColor = scanner.nextLine();
	
					String orderNumber = amazon.orderProduct(productId, customerId, sizeColor);
					if (orderNumber != null)
					{
						System.out.println("Order #" + orderNumber);
					}
					else
					{
						System.out.println(amazon.getErrorMessage());
					}
				}
				else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
				{
					String orderNumber = "";
	
					System.out.print("Order Number: ");
					if (scanner.hasNextLine())
						orderNumber = scanner.nextLine();
	
					amazon.cancelOrder(orderNumber);
					
				}
				else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort and prints products by price
				{
					amazon.sortByPrice();
				}
				else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort and prints products by name (alphabetic)
				{
					amazon.sortByName();
				}
				else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
				{
					amazon.sortCustomersByName();
				}
				else if (action.equalsIgnoreCase("ADDTOCART")){ //adds product to a customer's cart
					String productid = "";
					String customerID = "";
					String productOptions = "";
	
					System.out.print("Product Id: ");
					if (scanner.hasNextLine())
						productid = scanner.nextLine();
					System.out.print("Customer Id: ");
					if (scanner.hasNextLine())
						customerID = scanner.nextLine();
					System.out.println(amazon.printOptions(productid));
					System.out.print("Product options: ");
					if (scanner.hasNextLine())
						productOptions = scanner.nextLine();
					amazon.addToCart(customerID, productOptions, productid);
				}
				else if (action.equalsIgnoreCase("REMCARTITEM")){// removes a product from a user's cart
					String productid = "";
					String customerID = "";
	
					System.out.print("Product Id: ");
					if (scanner.hasNextLine())
						productid = scanner.nextLine();
					System.out.print("Customer Id: ");
					if (scanner.hasNextLine())
						customerID = scanner.nextLine();
					amazon.remCartItem(productid, customerID);
				}
				else if (action.equalsIgnoreCase("PRINTCART")){// prints all the products in a user's cart
					String customerID = "";
					System.out.print("Customer Id: ");
					if (scanner.hasNextLine())
						customerID = scanner.nextLine();
					amazon.printCart(customerID);
				}
				else if (action.equalsIgnoreCase("ORDERITEMS")){ //empties a user's cart after adding all products to orders
					String customerID = "";
					System.out.print("Customer Id: ");
					if (scanner.hasNextLine())
						customerID = scanner.nextLine();
					amazon.orderItems(customerID);
				}
				else if (action.equalsIgnoreCase("STATS")){ //prints the most ordered products to least
					amazon.printStats();
				}
				System.out.print("\n>");
			} catch (InvalidCustNameException | InvalidAddressException 
			| InvalidProductException | NoStockException | 
			InvalidOrderNumberException | UnknownCustomerException | UnknownProductException exception) {
				System.out.println(exception.getMessage());
			
			}
			 
		}
	}
}

/**
 * Name: Arjun Singh Bhandal
 * Student ID: 501088530
 */

/*
CartItem is a class that stores references to a Product Object as well as a valid product option
of said Product object
*/
public class CartItem {
    //Product object reference
    private Product prodObj;
    //Product option reference
    private String productOptions;

    //constructor
    public CartItem(Product prodObj, String productOptions){
        this.prodObj = prodObj;
        this.productOptions = productOptions;
    }
    //Returns a reference to the product object
    public Product getProduct(){
        return prodObj;
    }
    //Returns the product option variable
    public String getOption (){
        return productOptions;
    }
    //defined in Product.java
    public void print(){
        prodObj.print();
    }
}

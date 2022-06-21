/**
 * Name: Arjun Singh Bhandal
 * Student ID: 501088530
 */

import java.util.ArrayList;

/*
Cart Class stores an ArrayList of cartItems
*/

public class Cart {
    private ArrayList <CartItem> cartItems;

    public Cart (ArrayList<CartItem> arrayList){
        this.cartItems = arrayList;
    }
    public ArrayList<CartItem> getCartItems (){
        return cartItems;
    }
    /**
     * Adds a CartItem item to the ArrayList cartItems
     * @param item Cart item
     */
    public void addToCart(CartItem item){
        cartItems.add(item);
    }
    /**
     * Removes the product from Consumers cart
     * @param productID
     * @return 1 if the productID is valid, 0 if it is not valid
     */
    public int remCart(String productID) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getProduct().getId().equals(productID)){
                cartItems.remove(i);
                return 1;
            }
        }
        return 0;
    }
    /**
     * Used to set the ArrayList cartItems to newCart
     * @param newCart
     */
    public void setCart(ArrayList<CartItem> newCart){
        cartItems = newCart;
    }
}

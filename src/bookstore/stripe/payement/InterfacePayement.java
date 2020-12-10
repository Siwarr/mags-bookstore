/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.stripe.payement;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

/**
 *
 * @author aymen
 */
public interface InterfacePayement {
    public Customer getCurrentCustomer(String m)throws StripeException;
    public void AjoutCard(Customer a,String m,String l,String k,String j)throws StripeException;
    public void AjoutCustomer(String k)throws StripeException;
    public void Payement(Customer a,String s)throws StripeException;
}

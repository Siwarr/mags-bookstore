/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.stripe.payement;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.model.Token;
import com.stripe.param.CustomerRetrieveParams;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aymen
 */
public class ServicePayement implements InterfacePayement{

    public ServicePayement() {
        Stripe.apiKey="sk_test_51HvqgEHGXBE0KO4HmTBtSwRtoXoThxk7Mpg23BohIlGTYgSBlmdlMV7bEaqq5n4ExfykCCYQY1IQ5nooHaJXInpr00wZIqWcZi";
    }
    
    
    @Override
    public Customer getCurrentCustomer(String m) throws StripeException {
        Map<String,Object> paramCust=new HashMap<String,Object>();
        paramCust.put("email", m);
        CustomerCollection customers=Customer.list(paramCust);
        return customers.getData().get(0);
    }
    
    @Override
    public void AjoutCard(Customer a,String m,String l,String k,String j) throws StripeException {
        Map<String,Object> cardParam=new HashMap<String,Object>();
        cardParam.put("number", m);
        cardParam.put("exp_month", l);
        cardParam.put("exp_year", k);
        cardParam.put("cvc", j);
        
        Map<String,Object> tokenParam=new HashMap<String,Object>();
        tokenParam.put("card", cardParam);
        
        Token token=Token.create(tokenParam);
        Map<String,Object> sources=new HashMap<String,Object>();
        sources.put("source", token.getId());
        CustomerRetrieveParams params = CustomerRetrieveParams.builder()
        .addExpand("sources").build();
        a = Customer.retrieve(a.getId(), params, null);
        a.getSources().create(sources);
    }
    
    @Override
    public void AjoutCustomer(String k) throws StripeException {
        Map<String,Object> customerParameter=new HashMap<String,Object>();
        customerParameter.put("email", k);
        Customer newCustomer =Customer.create(customerParameter);
    }

    public void AfficherCustomer(Customer a) throws StripeException {
        Customer c = Customer.retrieve(a.getId());
            System.out.println(c);
    }

    @Override
    public void Payement(Customer a,String s) throws StripeException {
        Map<String,Object> chargeParam=new HashMap<String,Object>();
        chargeParam.put("amount",s);//amount*100 so we can convert it 
        chargeParam.put("currency", "usd");
        chargeParam.put("customer", a.getId());
        //chargeParam.put("source", card.gerId()); //payement with specific c if i have many cards
        Charge.create(chargeParam);
    }

}

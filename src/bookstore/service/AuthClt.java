package bookstore.service;

import bookstore.entites.Client;

public class AuthClt {
	private Client client;
    private static AuthClt instance;

    private AuthClt() {
    }

    public static AuthClt getInstance() {
        if(instance==null)
            instance=new AuthClt();
        return instance;
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client c) {
        this.client = c;
    }
}

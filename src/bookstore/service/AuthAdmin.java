package bookstore.service;

import bookstore.entites.Admin;

public class AuthAdmin {
	private Admin admin;
    private static AuthAdmin instance;

    private AuthAdmin() {
    }

    public static AuthAdmin getInstance() {
        if(instance==null)
            instance=new AuthAdmin();
        return instance;
    }
    
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin a) {
        this.admin=a;
    }
}

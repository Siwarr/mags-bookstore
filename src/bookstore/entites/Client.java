package bookstore.entites;

public class Client {
	private String id;
    private String name;
    private String tel;
    private String email;
    private String pwd;

    public Client() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Client(String id, String name, String tel, String email, String pwd, String prenom) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", tel=" + tel + ", email=" + email + ", pwd=" + pwd
				+ "]";
	}

}

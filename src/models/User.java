package models;



public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private Platform platform;

    public User(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
       
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", role=" + role + "]";
    }


    public User(String username,String password,String role,Platform platform){
        this.username = username;
        this.password = password;
        this.role = role;
        this.platform = platform;

    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;

    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getRole(){
        return role;
    }
    public int getPlatformId() {
        return platform.getPlatformId();
    }

   
    
}

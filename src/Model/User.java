/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Well's-Comp
 */
public class User {
    private String id_user;
    private String username;
    private String password;
    private String id_role;

    public User(String id, String username, String password, String id_role) {
        setId_user(id);
        setUsername(username);
        setPassword(password);
        setId_role(id_role);
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_user() {
        return id_user;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }  

    public String getId_role() {
        return id_role;
    }

    public void setId_role(String id_role) {
        this.id_role = id_role;
    }
    
}

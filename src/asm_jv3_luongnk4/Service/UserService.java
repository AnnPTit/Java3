/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm_jv3_luongnk4.Service;

import asm_jv3_luongnk4.Model.User;
import asm_jv3_luongnk4.Repository.UserRepository;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserService {

    UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public List<User> layDanhSachUser() {
        return this.userRepository.layDanhSachUser();
    }

    public User layUser(String Name) {
        return this.userRepository.layUser(Name);
    }

}

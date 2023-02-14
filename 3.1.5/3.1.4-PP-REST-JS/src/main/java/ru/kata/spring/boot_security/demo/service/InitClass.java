package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitClass {

    private final UserService userService;
    private final RoleService roleService;

    public InitClass(UserService userRepository, RoleService roleRepository) {
        this.userService = userRepository;
        this.roleService = roleRepository;
    }


    @PostConstruct
    public void init() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        roleService.save(roleAdmin);
        Role roleUser = new Role("ROLE_USER");
        roleService.save(roleUser);

        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(roleAdmin);
        roleSet1.add(roleUser);
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(roleAdmin);
        Set<Role> roleSet3 = new HashSet<>();
        roleSet3.add(roleUser);

        userService.saveUser(new User(1L, "Ivan", "Ivanov", 35, "111", "111", roleSet1));
        userService.saveUser(new User(2L, "Sergei", "Prokofiev", 45, "admin@admin.ru", "admin", roleSet2));
        userService.saveUser(new User(3L, "Andrei", "Andreev", 55, "333", "333", roleSet3));


    }
}


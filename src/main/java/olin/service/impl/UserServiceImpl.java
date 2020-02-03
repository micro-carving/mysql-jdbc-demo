package olin.service.impl;


import olin.dao.UserDao;
import olin.dao.impl.UserDaoImpl;
import olin.entity.User;
import olin.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    public List<User> getUsers() {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUsers();
    }
}

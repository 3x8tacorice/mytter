package com.example.mytter.model;


import com.example.mytter.model.domain.User;
import com.example.mytter.model.repository.UserRepository;
import com.example.mytter.model.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService target = new UserService();

    User nobi, sune;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        User nobi = new User(1, "Nobita");
        User sune = new User(3, "Suneo");
    }

    @Test
    public void findAllUsersTest(){

        List<User> allUsers = Arrays.asList(nobi,sune);

//        userRepository = mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(allUsers);

        List<User> actual = target.findAllUsers();
        List<User> expect = allUsers;

        assertThat(actual, is(expect));
    }

    @Test
    public void findUserByIdTest(){
        User nobi = new User(1, "Nobita");

        when(userRepository.findById(1).orElse(null)).thenReturn(nobi);

        User actual = target.findUserById(1);
        User expect = nobi;

        assertThat(actual, is(expect));
    }
}

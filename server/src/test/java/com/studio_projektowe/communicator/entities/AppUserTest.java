package com.studio_projektowe.communicator.entities;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AppUserTest {
    private AppUser testedObject;

    final static String username = "testUser";
    final static String login = "testLogin";
    final static String password = "testPass";
    final static String email = "test@email.com";
    final static String role = "USER_ROLE";
    final static String personalData = "born in Cracov";
    final static Boolean enabled = true;

    @BeforeTest
    public void setValues(){testedObject = new AppUser(username,login,password,email,role,personalData,enabled);}

    @Test
    public void checkCreatedObject(){
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getUsername()).isEqualTo(username);
        assertThat(testedObject.getLogin()).isEqualTo(login);
        assertThat(testedObject.getPassword()).isEqualTo(password);
        assertThat(testedObject.getEmail()).isEqualTo(email);
        assertThat(testedObject.getRole()).isEqualTo(role);
        assertThat(testedObject.getPersonalData()).isEqualTo(personalData);
        assertThat(testedObject.getEnabled()).isEqualTo(enabled);
    }
}

package com.capgemini.cappay;

import com.capgemini.cappay.dto.AccountDto;
import com.capgemini.cappay.dto.UserDto;
import com.capgemini.cappay.exception.CapPayException;
import com.capgemini.cappay.model.Account;
import com.capgemini.cappay.model.User;
import com.capgemini.cappay.service.AccountService;
import com.capgemini.cappay.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Test
    public void shouldCreateNewAccountTest() throws CapPayException {
        UserDto userDto = new UserDto("Name Test");
        User user = userService.save(userDto);

        AccountDto accountDto = new AccountDto();
        accountDto.setUserId(user.getId());
        accountDto.setAccountType("PP");

        Account account = accountService.save(accountDto);
        assertEquals(1, account.getId());
    }
}

package vlad.gurabatov.REST.service.impl;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vlad.gurabatov.REST.entity.User;
import vlad.gurabatov.REST.repository.UserRepository;
import vlad.gurabatov.REST.service.Impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void getGetAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(getUsers());
        List<User> users = userService.getAllUsers();
        Assertions.assertNotNull(users);
        Assertions.assertEquals(getUsers(), users);
    }

    @Test
    public void getUsersByName() {
        String name = "Vlad";
        Mockito.when(userRepository.findAll()).thenReturn(getUsers());
        List<User> users = userService.getUsersByName(name);
        Assertions.assertNotNull(users);
        Assertions.assertEquals(1, users.size());
    }

    private List<User> getUsers() {
        User firstUser = new User();
        User secondUser = new User();
        firstUser.setId(1L);
        firstUser.setName("Vlad");
        firstUser.setSurname("Vladislav");
        firstUser.setLastName("Vladislavovich");
        firstUser.setBirthday(LocalDate.now());
        firstUser.setEmail("vlad@gmail.com");
        secondUser.setId(2L);
        secondUser.setName("Anton");
        secondUser.setSurname("Antonovich");
        secondUser.setLastName("Antonov");
        secondUser.setBirthday(LocalDate.of(1995, 1, 1));
        secondUser.setEmail("anton@gmail.com");
        return List.of(firstUser, secondUser);
    }
}

package vlad.gurabatov.REST.configuration;

import org.springframework.batch.item.ItemProcessor;
import vlad.gurabatov.REST.entity.DTO.UserDTO;
import vlad.gurabatov.REST.entity.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class UserProcessor implements ItemProcessor<UserDTO, User>{
    @Override
    public User process(UserDTO userDTO) throws Exception {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setLastName(userDTO.getLastName());
        user.setBirthday(LocalDate.parse(userDTO.getBirthDay()));
        user.setEmail(userDTO.getEmail());
        return user;
    }
}

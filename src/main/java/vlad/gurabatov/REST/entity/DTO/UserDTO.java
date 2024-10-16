package vlad.gurabatov.REST.entity.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String surname;
    private String lastName;
    private String email;
    private String password;
    private String birthDay;
}

package vlad.gurabatov.REST.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import vlad.gurabatov.REST.entity.model.UserModelAssembler;
import vlad.gurabatov.REST.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService service;
    private final UserModelAssembler assembler;
}

package com.utfpr.prova.controller;

import com.utfpr.prova.model.User;
import com.utfpr.prova.model.dto.UserDTO;
import com.utfpr.prova.model.mapper.UserMapper;
import com.utfpr.prova.model.service.UserService;
import com.utfpr.prova.security.JwtUser;
import com.utfpr.prova.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping()
    public List<User>  findAll() {
        return this.userService.findAll();
    }

    @PostMapping("/novo")
    public ResponseEntity<Response<UserDTO>> save(@Valid @RequestBody UserDTO dto, BindingResult result) {

        Response<UserDTO> response = new Response<>();
        if (result.hasErrors()) {
            response.setErrors(result);
            return ResponseEntity.badRequest().body(response);
        }

        if (dto.getId() != null) {
            Optional<User> o = userService.findById(dto.getId());
            if (o.isPresent()) {
                response.addError("Usuário já cadastrado.");
                return ResponseEntity.badRequest().body(response);
            }
        }

        User user = userMapper.toEntity(dto);
        user = userService.save(user);

        dto = userMapper.toDTO(user);
        response.setData(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<UserDTO>> findById(@PathVariable("id") Long id) {
        Optional<User> userOptional = userService.findById(id);
        Response<UserDTO> response = new Response<>();

        if (userOptional.isPresent()) {
            UserDTO userDTO = userMapper.toDTO(userOptional.get());
            response.setData(userDTO);
            return ResponseEntity.ok(response);
        }

        response.addError("Usuário não encontrado " + id);
        return ResponseEntity.badRequest().body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody UserDTO dto, BindingResult result) {

        JwtUser currentUser = (JwtUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Response<UserDTO> response = new Response<>();
        if (result.hasErrors()) {
            response.setErrors(result);
            return ResponseEntity.badRequest().body(response);
        }

        Optional<User> o = userService.findById(id);
        if (!o.isPresent()) {
            response.addError("Usuário não encontrado");
            return ResponseEntity.badRequest().body(response);
        }
        if (!currentUser.getId().equals(id)) {
            response.addError("Você pode autorizar apenas seu próprio usuário.");
            return ResponseEntity.badRequest().body(response);
        }
        dto.setId(id);
        User user = userMapper.toEntity(dto);
        userService.save(user);
        response.setData(dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
        Response<String> response = new Response<>();
        Optional<User> o = userService.findById(id);

        if (!o.isPresent()) {
            response.addError("Erro ao remover, registro não encontrado para o id " + id);
            return ResponseEntity.badRequest().body(response);
        }

        JwtUser currentUser = (JwtUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!currentUser.getId().equals(id)) {
            response.addError("Você pode deletar apenas seu próprio usuário.");
            return ResponseEntity.badRequest().body(response);
        }

        this.userService.deleteById(id);
        response.setData("Usuário deletado com sucesso");
        return ResponseEntity.ok(response);
    }
}
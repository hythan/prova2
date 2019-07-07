package com.utfpr.prova.model.mapper;

import com.utfpr.prova.model.User;
import com.utfpr.prova.model.dto.UserDTO;
import com.utfpr.prova.util.PasswordUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    public User toEntity(UserDTO dto){
        User user = mapper.map(dto, User.class);
        user.setPassword(PasswordUtil.generateBCrypt(user.getPassword()));
        return user;
    }

    public UserDTO toDTO(User entity){
        UserDTO userDTO = mapper.map(entity, UserDTO.class);
        return userDTO;
    }

}

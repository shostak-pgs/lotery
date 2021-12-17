package org.java.training.helpdesk.service;

import org.java.training.helpdesk.dto.UserDto;
import org.java.training.helpdesk.entity.enums.Genre;
import org.java.training.helpdesk.entity.enums.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommonService {
    private static final  String GENRE = "genres";
    private static final  String TAGS = "tags";

    private final UserService userService;

    public CommonService(UserService userService) {
        this.userService = userService;
    }

    public Map<String, Object> getFixedVales(){
        Map<String, Object> map = new HashMap<>() ;
        map.put(GENRE, Arrays.stream(Genre.values())
                .filter(genre-> !genre.getStringValue().equals("")).collect(Collectors.toList()));
        map.put(TAGS, Arrays.stream(Tag.values())
                .filter(tag-> !tag.getStringValue().equals("")).collect(Collectors.toList()));
        return  map;
    }

    public UserDto runLatery() {
        List<UserDto> users = new ArrayList<>(userService.getUsers());
        Integer id = getRandomId(0, users.size());
        return users.get(id);
    }

    public int getRandomId(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
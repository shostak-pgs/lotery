package org.java.training.helpdesk.service;

import org.java.training.helpdesk.entity.enums.Genre;
import org.java.training.helpdesk.entity.enums.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommonService {
    private static final  String GENRE = "genres";
    private static final  String TAGS = "tags";

    public Map<String, Object> getFixedVales(){
        Map<String, Object> map = new HashMap<>() ;
        map.put(GENRE, Arrays.stream(Genre.values())
                .filter(genre-> !genre.getStringValue().equals("")).collect(Collectors.toList()));
        map.put(TAGS, Arrays.stream(Tag.values())
                .filter(tag-> !tag.getStringValue().equals("")).collect(Collectors.toList()));
        return  map;
    }
}
package org.boardpj.commons.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.boardpj.entities.Configs;
import org.boardpj.repositories.ConfigsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigSaveService {

   private  final ConfigsRepository repository;

    public <T> void save(String code, T t){

        Configs configs = repository.findById(code).orElseGet(Configs::new); //있으면 기존값 사용, 없으면 생성

        ObjectMapper om = new ObjectMapper(); //JSON parsing작업
        String value=null;
        try {
           value = om.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        configs.setCode(code);
        configs.setValue(value);

        repository.saveAndFlush(configs);

    }
}

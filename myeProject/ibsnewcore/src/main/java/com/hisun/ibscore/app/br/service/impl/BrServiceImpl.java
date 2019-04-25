package com.hisun.ibscore.app.br.service.impl;

import com.hisun.ibscore.app.br.domain.Br;
import com.hisun.ibscore.app.br.mapper.BrMapper;
import com.hisun.ibscore.app.br.service.BrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//加了@Service注解以后就会自动注册到Spring容器里面了 ，以前做的都是有Spring的配置文件  现在都不会再有了
@Service
public class BrServiceImpl implements BrService {


    //自动注入  相当于Spring帮你注入了 BrMapper  brMapper = new  BrMapper（）；
    @Autowired
    private BrMapper brMapper;
    public void add(Br br){
        //之后简单的调用了这些方法
       brMapper.insert(br);
    }

    public void delete(int br){
       brMapper.delete(br);
    }

    public void update(Br br){
        brMapper.update(br);
    }

    public Object findByKey(int br){

        return brMapper.findByKey(br);
    }

    public Object findList(){
          return "aa";
    }
}

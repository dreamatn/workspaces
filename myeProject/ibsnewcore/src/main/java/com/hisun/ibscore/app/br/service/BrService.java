package com.hisun.ibscore.app.br.service;

import com.hisun.ibscore.app.br.domain.Br;

public interface BrService {



    public void add(Br br);

    public void delete(int br);

    public void update(Br br);

    public Object findByKey(int br);

    public Object findList();
}

package com.hisun.ibscore.app.bp.service;

import com.hisun.ibscore.app.bp.domain.User;

import java.util.List;

public interface UserService {

    public void add(User user);

    public void delete(String tlr);

    public void update(User user);

    public Object findByKey(String tlr);

    public Object findList();

}

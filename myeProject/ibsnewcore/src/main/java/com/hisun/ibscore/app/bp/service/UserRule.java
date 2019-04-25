package com.hisun.ibscore.app.bp.service;

import com.hisun.ibscore.app.bp.domain.User;

public interface UserRule {
    void check(User user) throws Exception;
}

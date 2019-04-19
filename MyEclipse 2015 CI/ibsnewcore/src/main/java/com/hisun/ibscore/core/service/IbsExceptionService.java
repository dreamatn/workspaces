package com.hisun.ibscore.core.service;

import com.hisun.ibscore.core.exception.IbsException;

public interface IbsExceptionService {

    void process(String msgId)  throws Exception;

    void saveAuthArea(String msgId,String msgDesc)  throws Exception;

}

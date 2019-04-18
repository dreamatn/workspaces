package com.hisun.ibscore.core.exception;

public class IbsException extends RuntimeException {
    private     String      msgType;
    private     String      msgId;
    private     String      msgDesc;

    public IbsException(String msgType, String msgId, String msgDesc) {
        this.msgType = msgType;
        this.msgId = msgId;
        this.msgDesc = msgDesc;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgDesc() {
        return msgDesc;
    }

    public void setMsgDesc(String msgDesc) {
        this.msgDesc = msgDesc;
    }
}

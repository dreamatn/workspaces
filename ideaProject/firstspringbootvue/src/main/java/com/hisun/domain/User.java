package com.hisun.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String versionNo;
    private boolean compInd;
    private boolean encrInd;
    private boolean tranInd;
    private String reqType;
    private String resendFlg;
    private String reqChnl;
    private String reqChnlJrn;
    private String reqChnlDate;
    private String reqChnlTime;
    private String chnlTrId;
    private String trId;
    private String trBank;
    private String trBr;
    private String tlld;
    private String username;
    private String password;


//    private Map<String,Integer> inpData = new HashMap<String, Integer>();
    private InpData inpData;
//    public User(){
//
//    }

}
package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4991 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_TLR_MAINTAIN = "BP-S-TLR-MAINTAIN   ";
    String K_OUTPUT_FMT = "BP557";
    String CPN_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_S_AMTL_MAINTAIN = "BP-S-AMTL-MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTLRM BPCSTLRM = new BPCSTLRM();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCSAMTL BPCSAMTL = new BPCSAMTL();
    SCCGWA SCCGWA;
    BPB4900_AWA_4900 BPB4900_AWA_4900;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLT BPRTLT;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4991 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4900_AWA_4900>");
        BPB4900_AWA_4900 = (BPB4900_AWA_4900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STAFNO);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR_BR);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.EFF_DATE);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.EXP_DATE);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR_CNAM);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR_ENAM);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR_TYPE);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR_LVL);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TX_LVL);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.AUTH_LVL);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TRM_TYPE);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.KPSW_FLG);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW1);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW2);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW3);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW4);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW5);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW6);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW7);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW8);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW9);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW10);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW11);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW12);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TELE);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.PST_ADDR);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.SEN_CUS);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.SEN_GL);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.SUP_FLG);
        B010_CHECK_INPUT();
        B020_UPDATE_TLR_RECORD();
        B030_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4900_AWA_4900.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BANK_NOTFND;
            WS_FLD_NO = BPB4900_AWA_4900.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCSAMTL);
        BPCSAMTL.BR = BPB4900_AWA_4900.TLR_BR;
        BPCSAMTL.MACH_NO = BPB4900_AWA_4900.TLR;
        BPCSAMTL.EFF_DATE = BPB4900_AWA_4900.EFF_DATE;
        BPCSAMTL.FUNC = 'I';
        S000_CALL_BPZSAMTL();
        if (BPCSTLRM.TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_MODIFY_ONESEL;
            WS_FLD_NO = BPB4900_AWA_4900.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4900_AWA_4900.STAFNO.trim().length() == 0) {
        }
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BNK);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.ATTR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.ABBR);
    }
    public void B020_UPDATE_TLR_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTLRM);
        BPCSTLRM.FUNC = 'U';
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSTLRM();
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTLRQ);
        R100_TRANS_DATA_OUTPUT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTLRQ;
        SCCFMT.DATA_LEN = 835;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSTLRM.TLR = BPCSAMTL.TLR_NO;
        BPCSTLRM.STAFNO = BPB4900_AWA_4900.STAFNO;
        BPCSTLRM.TLR_BR = BPB4900_AWA_4900.TLR_BR;
        BPCSTLRM.TLR_TKS = BPB4900_AWA_4900.TLR_TKS;
        BPCSTLRM.EFF_DATE = BPB4900_AWA_4900.EFF_DATE;
        BPCSTLRM.EXP_DATE = BPB4900_AWA_4900.EXP_DATE;
        BPCSTLRM.TLR_CNAME = BPB4900_AWA_4900.TLR_CNAM;
        BPCSTLRM.TLR_ENAME = BPB4900_AWA_4900.TLR_ENAM;
        BPCSTLRM.TLR_TYPE = BPB4900_AWA_4900.TLR_TYPE.charAt(0);
        BPCSTLRM.TLR_LVL = BPB4900_AWA_4900.TLR_LVL;
        BPCSTLRM.TX_LVL = BPB4900_AWA_4900.TX_LVL;
        BPCSTLRM.AUTH_LVL = BPB4900_AWA_4900.AUTH_LVL;
        BPCSTLRM.AUTH_REG = BPB4900_AWA_4900.AUTH_REG;
        BPCSTLRM.CBR_SIGN = BPB4900_AWA_4900.CBR_SIGN;
        BPCSTLRM.TRM_TYPE = BPB4900_AWA_4900.TRM_TYPE;
        BPCSTLRM.TLR_STSW = " ";
        BPCSTLRM.TX_MODE = BPB4900_AWA_4900.TX_MODE;
        BPCSTLRM.PRT_IP = BPB4900_AWA_4900.PRT_IP;
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW1;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(1);
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW2;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(2 + 1 - 1);
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW3;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 3 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(3 + 1 - 1);
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW4;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 4 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(4 + 1 - 1);
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW5;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 5 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(5 + 1 - 1);
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW6;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 6 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(6 + 1 - 1);
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW7;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 7 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(7 + 1 - 1);
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW8;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 8 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(8 + 1 - 1);
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW9;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 9 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(9 + 1 - 1);
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW10;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 10 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(10 + 1 - 1);
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW11;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 11 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(11 + 1 - 1);
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW12;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 12 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(12 + 1 - 1);
        BPCSTLRM.TELE = BPB4900_AWA_4900.TELE;
        BPCSTLRM.PST_ADDRESS = BPB4900_AWA_4900.PST_ADDR;
        BPCSTLRM.SIGN_TRM_FLG = BPB4900_AWA_4900.SG_TRM_F;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            BPCSTLRM.TRM_INFO.TRM_NUM[WS_I-1] = BPB4900_AWA_4900.TRM_INFO[WS_I-1].TRM_NUM;
        }
        BPCSTLRM.PSW_TYP = BPB4900_AWA_4900.PSW_TYP;
        BPCSTLRM.IDEN_DEV_ID = BPB4900_AWA_4900.DEV_ID;
        BPCSTLRM.ACC_SEN_CUS = BPB4900_AWA_4900.SEN_CUS;
        BPCSTLRM.ACC_SEN_GL = BPB4900_AWA_4900.SEN_GL;
        BPCSTLRM.SUPER_FLG = BPB4900_AWA_4900.SUP_FLG;
    }
    public void R100_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCOTLRQ.TLR = BPB4900_AWA_4900.TLR;
        BPCOTLRQ.TLR_BR = BPB4900_AWA_4900.TLR_BR;
        BPCOTLRQ.STAF_NO = BPB4900_AWA_4900.STAFNO;
        BPCOTLRQ.TLR_CN_NM = BPB4900_AWA_4900.TLR_CNAM;
        BPCOTLRQ.TLR_EN_NM = BPB4900_AWA_4900.TLR_ENAM;
        BPCOTLRQ.EFF_DT = BPB4900_AWA_4900.EFF_DATE;
        BPCOTLRQ.EXP_DT = BPB4900_AWA_4900.EXP_DATE;
        BPCOTLRQ.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOTLRQ.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCOTLRQ.TLR_TYP = BPB4900_AWA_4900.TLR_TYPE.charAt(0);
        BPCOTLRQ.TLR_LVL = BPB4900_AWA_4900.TLR_LVL;
        BPCOTLRQ.TX_LVL = BPB4900_AWA_4900.TX_LVL;
        BPCOTLRQ.ATH_LVL = BPB4900_AWA_4900.AUTH_LVL;
        BPCOTLRQ.SIGN_STS = BPRTLT.SIGN_STS;
        BPCOTLRQ.TLR_STS = BPRTLT.TLR_STS;
        BPCOTLRQ.SIGN_DT = BPRTLT.SIGN_DT;
        BPCOTLRQ.SIGN_TIMES = BPRTLT.SIGN_TIMES;
        BPCOTLRQ.TRM_TYP = BPB4900_AWA_4900.TRM_TYPE;
        BPCOTLRQ.SIGN_TRM = BPRTLT.SIGN_TRM;
        BPCOTLRQ.TLR_STSW = BPCSTLRM.TLR_STSW;
        BPCOTLRQ.TELE = BPCSTLRM.TELE;
        BPCOTLRQ.PST_ADDRESS = BPCSTLRM.PST_ADDRESS;
        BPCOTLRQ.LAST_JRN = BPRTLT.LAST_JRN;
        BPCOTLRQ.ACC_VCH_NO = BPRTLT.ACC_VCH_NO;
        BPCOTLRQ.ACC_SEN_CUS = BPB4900_AWA_4900.SEN_CUS;
        BPCOTLRQ.ACC_SEN_GL = BPB4900_AWA_4900.SEN_GL;
        BPCOTLRQ.SUPER_FLG = BPB4900_AWA_4900.SUP_FLG;
    }
    public void S000_CALL_BPZSTLRM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_TLR_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSTLRM;
        SCCCALL.ERR_FLDNO = BPB4900_AWA_4900.STAFNO_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_ORG_STATION, BPCPRGST);
        CEP.TRC(SCCGWA, BPCPRGST.RC.RC_CODE);
        CEP.TRC(SCCGWA, BPCPRGST.FLAG);
        CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            WS_FLD_NO = BPB4900_AWA_4900.TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSAMTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_AMTL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSAMTL;
        SCCCALL.ERR_FLDNO = BPB4900_AWA_4900.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, BPCSAMTL.RC.RC_CODE);
        if (BPCSAMTL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSAMTL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4900 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_TLR_MAINTAIN = "BP-S-TLR-MAINTAIN   ";
    String K_OUTPUT_FMT = "BP556";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTLRM BPCSTLRM = new BPCSTLRM();
    BPCO4900 BPCO4900 = new BPCO4900();
    SCCGWA SCCGWA;
    BPB4900_AWA_4900 BPB4900_AWA_4900;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCOWA SCCOWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4900 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4900_AWA_4900>");
        BPB4900_AWA_4900 = (BPB4900_AWA_4900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.ID_TYP);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.ID_NO);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.CI_NO);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR_BR);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.EFF_DATE);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.EXP_DATE);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR_CNAM);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TELE);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.PST_ADDR);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR_TYPE);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR_LVL);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.REG_TYP);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.CRD_NO);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.NEW_BR);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW1);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW2);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW3);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW4);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW6);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.STW8);
        B010_CHECK_INPUT();
        B020_CREATE_TLR_RECORD();
        B030_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4900_AWA_4900.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BANK_NOTFND;
            WS_FLD_NO = BPB4900_AWA_4900.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.EFF_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPB4900_AWA_4900.EFF_DATE == 0 
            || BPB4900_AWA_4900.EFF_DATE == 0X00) {
            CEP.TRC(SCCGWA, "2014.06.25");
            BPB4900_AWA_4900.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.EFF_DATE);
    }
    public void B020_CREATE_TLR_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTLRM);
        BPCSTLRM.FUNC = 'A';
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSTLRM();
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO4900);
        R100_TRANS_DATA_OUTPUT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO4900;
        SCCFMT.DATA_LEN = 440;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR);
        BPCSTLRM.ID_TYP = BPB4900_AWA_4900.ID_TYP;
        BPCSTLRM.ID_NO = BPB4900_AWA_4900.ID_NO;
        BPCSTLRM.CI_NO = BPB4900_AWA_4900.CI_NO;
        BPCSTLRM.TLR = BPB4900_AWA_4900.TLR;
        BPCSTLRM.TLR_BR = BPB4900_AWA_4900.TLR_BR;
        BPCSTLRM.EFF_DATE = BPB4900_AWA_4900.EFF_DATE;
        BPCSTLRM.EXP_DATE = BPB4900_AWA_4900.EXP_DATE;
        BPCSTLRM.TLR_CNAME = BPB4900_AWA_4900.TLR_CNAM;
        BPCSTLRM.TLR_TYPE = BPB4900_AWA_4900.TLR_TYPE.charAt(0);
        BPCSTLRM.TLR_LVL = BPB4900_AWA_4900.TLR_LVL;
        BPCSTLRM.TLR_STSW = " ";
        BPCSTLRM.REG_TYP = BPB4900_AWA_4900.REG_TYP;
        BPCSTLRM.CRD_NO = BPB4900_AWA_4900.CRD_NO;
        BPCSTLRM.NEW_BR = BPB4900_AWA_4900.NEW_BR;
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
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW6;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 6 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(6 + 1 - 1);
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4900_AWA_4900.STW8;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSTLRM.TLR_STSW = BPCSTLRM.TLR_STSW.substring(0, 8 - 1) + JIBS_tmp_str[0] + BPCSTLRM.TLR_STSW.substring(8 + 1 - 1);
        BPCSTLRM.TELE = BPB4900_AWA_4900.TELE;
        BPCSTLRM.PST_ADDRESS = BPB4900_AWA_4900.PST_ADDR;
        BPCSTLRM.CHK_FLG = BPB4900_AWA_4900.CHK_FLG;
    }
    public void R100_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO4900);
        BPCO4900.ID_TYP = BPB4900_AWA_4900.ID_TYP;
        BPCO4900.ID_NO = BPB4900_AWA_4900.ID_NO;
        BPCO4900.CI_NO = BPB4900_AWA_4900.CI_NO;
        BPCO4900.TLR = BPCSTLRM.TLR;
        BPCO4900.TLR_BR = BPB4900_AWA_4900.TLR_BR;
        BPCO4900.TLR_CN_NM = BPB4900_AWA_4900.TLR_CNAM;
        BPCO4900.EFF_DT = BPB4900_AWA_4900.EFF_DATE;
        BPCO4900.EXP_DT = BPB4900_AWA_4900.EXP_DATE;
        BPCO4900.TLR_LVL = BPB4900_AWA_4900.TLR_LVL;
        BPCO4900.REG_TYP = BPB4900_AWA_4900.REG_TYP;
        BPCO4900.CRD_NO = BPB4900_AWA_4900.CRD_NO;
        BPCO4900.NEW_BR = BPCSTLRM.NEW_BR;
        BPCO4900.TLR_STSW = BPCSTLRM.TLR_STSW;
        BPCO4900.TELE = BPB4900_AWA_4900.TELE;
        BPCO4900.PST_ADDRESS = BPB4900_AWA_4900.PST_ADDR;
    }
    public void S000_CALL_BPZSTLRM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_TLR_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSTLRM;
        SCCCALL.ERR_FLDNO = BPB4900_AWA_4900.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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

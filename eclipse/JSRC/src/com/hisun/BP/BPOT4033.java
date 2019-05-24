package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4033 {
    int JIBS_tmp_int;
    char K_ERROR = 'E';
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSITD BPCSITD = new BPCSITD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    BPB4030_AWA_4030 BPB4030_AWA_4030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4033 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4030_AWA_4030>");
        BPB4030_AWA_4030 = (BPB4030_AWA_4030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ITD_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_ITD_TRANSFER_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAM();
        S000_CALL_BPZSITD();
    }
    public void R001_DELET_DATA_PARAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.DAT_PTR = BPRPRMT;
        BPRPRMT.KEY.TYP = "AMITD";
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        if (BPB4030_AWA_4030.BOOKFLG == null) BPB4030_AWA_4030.BOOKFLG = "";
        JIBS_tmp_int = BPB4030_AWA_4030.BOOKFLG.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) BPB4030_AWA_4030.BOOKFLG += " ";
        BPRPRMT.KEY.CD = BPB4030_AWA_4030.BOOKFLG + BPRPRMT.KEY.CD.substring(5);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        if (BPB4030_AWA_4030.PRODMOD == null) BPB4030_AWA_4030.PRODMOD = "";
        JIBS_tmp_int = BPB4030_AWA_4030.PRODMOD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPB4030_AWA_4030.PRODMOD += " ";
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 6 - 1) + BPB4030_AWA_4030.PRODMOD + BPRPRMT.KEY.CD.substring(6 + BPB4030_AWA_4030.PRODMOD.length() - 1);
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM1();
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '1';
        S000_CALL_BPZPRMM1();
    }
    public void S000_CALL_BPZPRMM1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            WS_FLD_NO = BPB4030_AWA_4030.PRODMOD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_TRANS_DATA_PARAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSITD);
        BPCSITD.DATA.KEY.REDEFINES15.BOOK_FLG = BPB4030_AWA_4030.BOOKFLG;
        BPCSITD.DATA.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSITD.DATA.KEY.REDEFINES15);
        BPCSITD.DATA.KEY.REDEFINES15.CNTR_TYPE = BPB4030_AWA_4030.PRODMOD;
        BPCSITD.DATA.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSITD.DATA.KEY.REDEFINES15);
        BPCSITD.INFO.FUNC = 'D';
    }
    public void S000_CALL_BPZSITD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-ITD", BPCSITD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

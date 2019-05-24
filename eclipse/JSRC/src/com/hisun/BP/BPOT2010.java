package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2010 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP112";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_S_PROD_MAINTAIN = "BP-S-PROD-MAINTAIN";
    String CPN_ITM_INQ = "BP-P-CHK-ACCT-CODE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT2010_WS_TEMP_KEY WS_TEMP_KEY = new BPOT2010_WS_TEMP_KEY();
    char WS_NUMB_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOPRDI BPCOPRDI = new BPCOPRDI();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCSBPRM BPCSBPRM = new BPCSBPRM();
    BPCSPRDM BPCSPRDM = new BPCSPRDM();
    BPCPQGLM BPCPQGLM = new BPCPQGLM();
    SCCGWA SCCGWA;
    BPB2010_AWA_2010 BPB2010_AWA_2010;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2010_AWA_2010>");
        BPB2010_AWA_2010 = (BPB2010_AWA_2010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2010_AWA_2010.FUNC);
        CEP.TRC(SCCGWA, BPB2010_AWA_2010.PARM_TP);
        CEP.TRC(SCCGWA, BPB2010_AWA_2010.CCY);
        CEP.TRC(SCCGWA, BPB2010_AWA_2010.CS_KIND);
        CEP.TRC(SCCGWA, BPB2010_AWA_2010.ACCT_CD);
        CEP.TRC(SCCGWA, BPB2010_AWA_2010.PL_CD);
        CEP.TRC(SCCGWA, BPB2010_AWA_2010.AC_SEQ);
        if (BPB2010_AWA_2010.FUNC != 'A') {
            WS_ERR_MSG = "" + BPB2010_AWA_2010.FUNC;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            WS_FLD_NO = BPB2010_AWA_2010.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB2010_AWA_2010.ACCT_CD != 0) {
            IBS.init(SCCGWA, BPCPQGLM);
            BPCPQGLM.DAT.INPUT.MSTNO = BPB2010_AWA_2010.ACCT_CD;
            S000_CALL_BPZPQGLM();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
                WS_FLD_NO = BPB2010_AWA_2010.ACCT_CD_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GLMST_MUST_INPUT;
            WS_FLD_NO = BPB2010_AWA_2010.ACCT_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB2010_AWA_2010.PL_CD != 0) {
            IBS.init(SCCGWA, BPCPQGLM);
            BPCPQGLM.DAT.INPUT.MSTNO = BPB2010_AWA_2010.ACCT_CD;
            S000_CALL_BPZPQGLM();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
                WS_FLD_NO = BPB2010_AWA_2010.ACCT_CD_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB2010_AWA_2010.EFF_DATE);
        CEP.TRC(SCCGWA, BPB2010_AWA_2010.EXP_DATE);
        if (BPB2010_AWA_2010.EFF_DATE >= BPB2010_AWA_2010.EXP_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            WS_FLD_NO = BPB2010_AWA_2010.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB2010_AWA_2010.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LESS_ACDATE;
            WS_FLD_NO = BPB2010_AWA_2010.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB2010_AWA_2010.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LESS_ACDATE;
            WS_FLD_NO = BPB2010_AWA_2010.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TX_LVL);
        if (BPCFTLRQ.INFO.TX_LVL == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_DIRECTOR_TLR;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B020_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSPRDM);
        BPCSPRDM.DATA_INFO.FUNC = 'A';
        BPCSPRDM.DATA_INFO.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        CEP.TRC(SCCGWA, BPCSPRDM);
        CEP.TRC(SCCGWA, BPB2010_AWA_2010.PARM_TP);
        CEP.TRC(SCCGWA, BPB2010_AWA_2010.PL_CD);
        S000_CALL_BPZSPRDM();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSPRDM.DATA_INFO.FUNC = BPB2010_AWA_2010.FUNC;
        BPCSPRDM.DATA_INFO.TYPE = BPB2010_AWA_2010.PARM_TP;
        BPCSPRDM.DATA_INFO.CCY = BPB2010_AWA_2010.CCY;
        BPCSPRDM.DATA_INFO.STAT = BPB2010_AWA_2010.STAT;
        BPCSPRDM.DATA_INFO.CS_KIND = BPB2010_AWA_2010.CS_KIND;
        BPCSPRDM.DATA_INFO.ACCT_CD = BPB2010_AWA_2010.ACCT_CD;
        BPCSPRDM.DATA_INFO.PL_CD = BPB2010_AWA_2010.PL_CD;
        BPCSPRDM.DATA_INFO.AC_SEQ = BPB2010_AWA_2010.AC_SEQ;
        BPCSPRDM.DATA_INFO.ACCT_LVL = BPB2010_AWA_2010.ACCT_LVL;
        BPCSPRDM.DATA_INFO.ACCT_TYP = BPB2010_AWA_2010.ACCT_TYP;
        BPCSPRDM.DATA_INFO.SUP_FLG = BPB2010_AWA_2010.SUP_FLG;
        BPCSPRDM.DATA_INFO.EFF_DATE = BPB2010_AWA_2010.EFF_DATE;
        BPCSPRDM.DATA_INFO.EXP_DATE = BPB2010_AWA_2010.EXP_DATE;
        BPCSPRDM.DATA_INFO.DESC = BPB2010_AWA_2010.DESC;
        BPCSPRDM.DATA_INFO.CDESC = BPB2010_AWA_2010.CDESC;
    }
    public void S000_CALL_BPZSPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_PROD_MAINTAIN, BPCSPRDM);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-GLMT", BPCPQGLM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

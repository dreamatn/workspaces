package com.hisun.DD;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSRCHQ {
    DBParm DDTMST_RD;
    int JIBS_tmp_int;
    brParm DDTCHQ_BR = new brParm();
    DBParm DDTCHQ_RD;
    String CPN_DD_S_SIGN_PROC = "DD-S-SIGN-PROC";
    String K_OUTPUT_FMT = "DD135";
    String K_HIS_CPB_NM = "DDCORCHQ";
    String K_CTL_PRM_TYP = "PDD04";
    String K_HIS_RMKS = "CHEQUE RLSE MAINTAIN";
    String WS_ERR_MSG = " ";
    short WS_CHQ_CNT = 0;
    short WS_CNT = 0;
    short WS_L_TCNT = 0;
    String WS_CHQ_STS = " ";
    long WS_SRCHQ_STR_CHQ_NO = 0;
    long WS_CHQ_STR_CHQ_NO = 0;
    long WS_STR_CHQ_NO = 0;
    long WS_END_CHQ_NO = 0;
    int WS_LEE = 0;
    int WS_LEN = 0;
    int WS_LEQ = 0;
    char WS_CHQ_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCORCHQ DDCORCHQ = new DDCORCHQ();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICQACAC CICQACAC = new CICQACAC();
    CICCUST CICCUST = new CICCUST();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDCSRCHQ DDCSRCHQ;
    public void MP(SCCGWA SCCGWA, DDCSRCHQ DDCSRCHQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSRCHQ = DDCSRCHQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSRCHQ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B015_GET_ACAC_INFO();
        B020_DISCARD_CHQB_PROC();
        B025_NFIN_TXN_HIS_PROC();
        B030_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSRCHQ.AC_NO);
        CEP.TRC(SCCGWA, DDCSRCHQ.CCY);
        CEP.TRC(SCCGWA, DDCSRCHQ.CCY_TYPE);
        if (DDCSRCHQ.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSRCHQ.STR_CHQ_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSRCHQ.END_CHQ_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSRCHQ.AC_NO;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_IS_LONG_HOVER;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'V' 
            || DDRMST.AC_STS == 'O') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_ACTIVE;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.AGR_NO = DDCSRCHQ.AC_NO;
        CICCUST.FUNC = 'A';
        S000_CALL_CIZCUST();
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (CICCUST.O_DATA.O_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_STS_STOP);
        }
    }
    public void B015_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        if (DDCSRCHQ.AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = DDCSRCHQ.AC_NO;
            CICQACAC.DATA.CCY_ACAC = DDCSRCHQ.CCY;
            CICQACAC.DATA.CR_FLG = DDCSRCHQ.CCY_TYPE;
            CICQACAC.FUNC = 'C';
            S000_CALL_CIZQACAC();
        }
    }
    public void B020_DISCARD_CHQB_PROC() throws IOException,SQLException,Exception {
        WS_LEN = 0;
        WS_LEN = DDCSRCHQ.STR_CHQ_NO.trim().length();
        CEP.TRC(SCCGWA, WS_LEN);
        if (DDCSRCHQ.STR_CHQ_NO == null) DDCSRCHQ.STR_CHQ_NO = "";
        JIBS_tmp_int = DDCSRCHQ.STR_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCSRCHQ.STR_CHQ_NO += " ";
        if (!IBS.isNumeric(DDCSRCHQ.STR_CHQ_NO.substring(0, WS_LEN))) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
        if (DDCSRCHQ.END_CHQ_NO.trim().length() > 0) {
            WS_LEE = 0;
            WS_LEE = DDCSRCHQ.END_CHQ_NO.trim().length();
            CEP.TRC(SCCGWA, WS_LEE);
            if (DDCSRCHQ.END_CHQ_NO == null) DDCSRCHQ.END_CHQ_NO = "";
            JIBS_tmp_int = DDCSRCHQ.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSRCHQ.END_CHQ_NO += " ";
            if (!IBS.isNumeric(DDCSRCHQ.END_CHQ_NO.substring(0, WS_LEE))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRCHQ.KEY.CHQ_TYP = DDCSRCHQ.CHQ_TYP;
        DDRCHQ.KEY.STR_CHQ_NO = DDCSRCHQ.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCSRCHQ.END_CHQ_NO;
        CEP.TRC(SCCGWA, DDRCHQ.KEY.AC);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.CHQ_TYP);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
        T000_STARTBR_DDTCHQ();
        T000_READNEXT_DDTCHQ();
        if (WS_CHQ_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_LEQ = 0;
        WS_LEQ = DDRCHQ.KEY.STR_CHQ_NO.trim().length();
        CEP.TRC(SCCGWA, WS_LEQ);
        if (WS_LEN != 16) {
            if (DDCSRCHQ.STR_CHQ_NO == null) DDCSRCHQ.STR_CHQ_NO = "";
            JIBS_tmp_int = DDCSRCHQ.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSRCHQ.STR_CHQ_NO += " ";
            if (DDCSRCHQ.STR_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_SRCHQ_STR_CHQ_NO = 0;
            else WS_SRCHQ_STR_CHQ_NO = Long.parseLong(DDCSRCHQ.STR_CHQ_NO.substring(0, WS_LEN));
            if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
            if (DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
            else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_LEN));
        } else {
            if (DDCSRCHQ.STR_CHQ_NO.trim().length() == 0) WS_SRCHQ_STR_CHQ_NO = 0;
            else WS_SRCHQ_STR_CHQ_NO = Long.parseLong(DDCSRCHQ.STR_CHQ_NO);
            if (DDRCHQ.KEY.STR_CHQ_NO.trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
            else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO);
        }
        CEP.TRC(SCCGWA, WS_SRCHQ_STR_CHQ_NO);
        CEP.TRC(SCCGWA, WS_CHQ_STR_CHQ_NO);
        while (WS_CHQ_FLG != 'N') {
            if (DDCSRCHQ.STR_CHQ_NO == null) DDCSRCHQ.STR_CHQ_NO = "";
            JIBS_tmp_int = DDCSRCHQ.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSRCHQ.STR_CHQ_NO += " ";
            if (DDCSRCHQ.STR_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_STR_CHQ_NO = 0;
            else WS_STR_CHQ_NO = Long.parseLong(DDCSRCHQ.STR_CHQ_NO.substring(0, WS_LEN));
            if (DDCSRCHQ.END_CHQ_NO == null) DDCSRCHQ.END_CHQ_NO = "";
            JIBS_tmp_int = DDCSRCHQ.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSRCHQ.END_CHQ_NO += " ";
            if (DDCSRCHQ.END_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_END_CHQ_NO = 0;
            else WS_END_CHQ_NO = Long.parseLong(DDCSRCHQ.END_CHQ_NO.substring(0, WS_LEN));
            CEP.TRC(SCCGWA, WS_SRCHQ_STR_CHQ_NO);
            CEP.TRC(SCCGWA, WS_CHQ_STR_CHQ_NO);
            WS_CNT = (short) (WS_SRCHQ_STR_CHQ_NO - WS_CHQ_STR_CHQ_NO + 1);
            WS_CHQ_CNT = (short) (WS_END_CHQ_NO - WS_STR_CHQ_NO + 1 + WS_CNT - 1);
            CEP.TRC(SCCGWA, WS_CHQ_CNT);
            CEP.TRC(SCCGWA, WS_CNT);
            for (WS_CNT = WS_CNT; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1));
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.TRC(SCCGWA, "IF");
                    if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                    JIBS_tmp_int = WS_CHQ_STS.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                    WS_CHQ_STS = WS_CHQ_STS.substring(0, WS_CNT - 1) + "5" + WS_CHQ_STS.substring(WS_CNT + 1 - 1);
                    WS_L_TCNT += 1;
                    T000_READUP_DDTCHQ();
                    if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                    JIBS_tmp_int = WS_CHQ_STS.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                    if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                    JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                    DDRCHQ.CHQ_STS = DDRCHQ.CHQ_STS.substring(0, WS_CNT - 1) + WS_CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1) + DDRCHQ.CHQ_STS.substring(WS_CNT + 1 - 1);
                    T000_REWRITE_DDTCHQ();
                } else {
                    CEP.TRC(SCCGWA, "ELSE");
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_STS_INVALID;
                    S000_ERR_MSG_PROC();
                }
                CEP.TRC(SCCGWA, WS_CNT);
                CEP.TRC(SCCGWA, WS_L_TCNT);
            }
            T000_READNEXT_DDTCHQ();
        }
        T000_ENDBR_DDTCHQ();
    }
    public void B025_NFIN_TXN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TOOL = DDCSRCHQ.AC_NO;
        BPCPNHIS.INFO.AC = DDCSRCHQ.AC_NO;
        BPCPNHIS.INFO.CCY = DDCSRCHQ.CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDCSRCHQ.CCY_TYPE;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P137";
        BPCPNHIS.INFO.TX_TYP = 'A';
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        DDCORCHQ.AC_NO = DDCSRCHQ.AC_NO;
        DDCORCHQ.CHQ_TYP = DDCSRCHQ.CHQ_TYP;
        DDCORCHQ.STR_CHQ_NO = DDCSRCHQ.STR_CHQ_NO;
        DDCORCHQ.END_CHQ_NO = DDCSRCHQ.END_CHQ_NO;
        DDCORCHQ.CCY = DDCSRCHQ.CCY;
        DDCORCHQ.CCY_TYPE = DDCSRCHQ.CCY_TYPE;
        DDCORCHQ.REMARKS = DDCSRCHQ.REMARKS;
        DDCORCHQ.D_CNT = WS_L_TCNT;
        CEP.TRC(SCCGWA, DDCORCHQ.D_CNT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCORCHQ;
        SCCFMT.DATA_LEN = 193;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_DDTCHQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSRCHQ.STR_CHQ_NO);
        WS_CHQ_FLG = 'N';
        CEP.TRC(SCCGWA, "11111111");
        IBS.init(SCCGWA, DDRCHQ);
        CEP.TRC(SCCGWA, "222222222");
        DDRCHQ.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRCHQ.KEY.CHQ_TYP = DDCSRCHQ.CHQ_TYP;
        DDRCHQ.KEY.STR_CHQ_NO = DDCSRCHQ.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCSRCHQ.END_CHQ_NO;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND CHQ_TYP = :DDRCHQ.KEY.CHQ_TYP "
            + "AND STR_CHQ_NO <= :DDRCHQ.KEY.END_CHQ_NO "
            + "AND END_CHQ_NO >= :DDRCHQ.KEY.STR_CHQ_NO";
        DDTCHQ_BR.rp.order = "STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
    }
    public void T000_READNEXT_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQ_FLG = 'Y';
        } else {
            WS_CHQ_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCHQ_BR);
    }
    public void T000_READUP_DDTCHQ() throws IOException,SQLException,Exception {
        DDTCHQ_RD = new DBParm();
        DDTCHQ_RD.TableName = "DDTCHQ";
        DDTCHQ_RD.upd = true;
        IBS.READ(SCCGWA, DDRCHQ, DDTCHQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DDTCHQ() throws IOException,SQLException,Exception {
        DDRCHQ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCHQ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCHQ_RD = new DBParm();
        DDTCHQ_RD.TableName = "DDTCHQ";
        IBS.REWRITE(SCCGWA, DDRCHQ, DDTCHQ_RD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT3510 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm AITHMIB_RD;
    brParm AITHMIB_BR = new brParm();
    DBParm BPTTLT_RD;
    String K_OUTPUT_FMT = "AI351";
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    double WS_STR_AMT = 0;
    double WS_END_AMT = 0;
    int WS_TOT_CNT = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    int WS_S = 0;
    int WS_LEN = 0;
    AIOT3510_WS_BR_AC WS_BR_AC = new AIOT3510_WS_BR_AC();
    AIOT3510_WS_STR_POS WS_STR_POS = new AIOT3510_WS_STR_POS();
    char WS_BR_FLG = ' ';
    char WS_FRZ_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    AICRMIB AICRMIB = new AICRMIB();
    AIRMIB AIRMIB = new AIRMIB();
    AIRHMIB AIRHMIB = new AIRHMIB();
    AICRHMIB AICRHMIB = new AICRHMIB();
    AICF351 AICF351 = new AICF351();
    BPRTLT BPRTLT = new BPRTLT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB3510_AWA_3510 AIB3510_AWA_3510;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT3510 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB3510_AWA_3510>");
        AIB3510_AWA_3510 = (AIB3510_AWA_3510) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_REC_PROC();
        B210_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB3510_AWA_3510.BOOK_FLG.trim().length() == 0 
            || AIB3510_AWA_3510.BR == 0 
            || AIB3510_AWA_3510.ITM.trim().length() == 0 
            || AIB3510_AWA_3510.SEQ == 0 
            || AIB3510_AWA_3510.CCY.trim().length() == 0 
            || AIB3510_AWA_3510.STR_DT == 0 
            || AIB3510_AWA_3510.END_DT == 0) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.MUST_INPUT);
        }
        if (AIB3510_AWA_3510.STR_DT > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_STRDATE_GT_ACDATE);
        }
        if (AIB3510_AWA_3510.END_DT != 0 
            && AIB3510_AWA_3510.END_DT < AIB3510_AWA_3510.STR_DT) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.END_DT_LESS_STRDT);
        }
        WS_BR_AC.WS_BR = AIB3510_AWA_3510.BR;
        WS_BR_AC.WS_CCY = AIB3510_AWA_3510.CCY;
        WS_BR_AC.WS_ITM = AIB3510_AWA_3510.ITM;
        WS_BR_AC.WS_SEQ = AIB3510_AWA_3510.SEQ;
        CEP.TRC(SCCGWA, WS_BR_AC);
        IBS.CPY2CLS(SCCGWA, AIB3510_AWA_3510.STR_POS, WS_STR_POS);
        if (AIB3510_AWA_3510.STR_POS.trim().length() > 0) {
            IBS.init(SCCGWA, AICRMIB);
            AICRMIB.FUNC = 'Q';
            AIRMIB.KEY.BR = WS_BR_AC.WS_BR;
            AIRMIB.KEY.ITM_NO = WS_BR_AC.WS_CCY;
            if (WS_BR_AC.WS_ITM.trim().length() == 0) AIRMIB.KEY.SEQ = 0;
            else AIRMIB.KEY.SEQ = Integer.parseInt(WS_BR_AC.WS_ITM);
            AIRMIB.KEY.CCY = "" + WS_BR_AC.WS_SEQ;
            JIBS_tmp_int = AIRMIB.KEY.CCY.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) AIRMIB.KEY.CCY = "0" + AIRMIB.KEY.CCY;
            S000_CALL_AIZRMIB();
        }
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRHMIB);
        WS_CNT = 0;
        IBS.init(SCCGWA, AICF351);
        WS_S = 0;
        AIRHMIB.KEY.BOOK_FLG = AIB3510_AWA_3510.BOOK_FLG;
        AIRHMIB.CCY = AIB3510_AWA_3510.CCY;
        AIRHMIB.KEY.AC = IBS.CLS2CPY(SCCGWA, WS_BR_AC);
        WS_STR_DT = AIB3510_AWA_3510.STR_DT;
        WS_END_DT = AIB3510_AWA_3510.END_DT;
        AIRHMIB.KEY.SET_NO = AIB3510_AWA_3510.GREF_NO;
        AIRHMIB.SIGN = AIB3510_AWA_3510.DRCR_FLG;
        WS_STR_AMT = AIB3510_AWA_3510.STR_AMT;
        WS_END_AMT = AIB3510_AWA_3510.END_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_STR_POS);
        if (JIBS_tmp_str[0].trim().length() > 0) {
            CEP.TRC(SCCGWA, WS_STR_POS.WS_DATE);
            CEP.TRC(SCCGWA, WS_STR_DT);
            CEP.TRC(SCCGWA, WS_END_DT);
            B310_FOUND_PROC();
            WS_STR_DT = WS_STR_POS.WS_DATE;
            CEP.TRC(SCCGWA, WS_STR_DT);
            IBS.init(SCCGWA, AIRHMIB);
            IBS.init(SCCGWA, AICRHMIB);
            AIRHMIB.KEY.SET_NO = WS_STR_POS.WS_SET_NO;
            AIRHMIB.KEY.SET_SEQ = WS_STR_POS.WS_SET_SEQ;
            AIRHMIB.KEY.BOOK_FLG = AIB3510_AWA_3510.BOOK_FLG;
            AIRHMIB.CCY = AIB3510_AWA_3510.CCY;
            AIRHMIB.KEY.AC = IBS.CLS2CPY(SCCGWA, WS_BR_AC);
            AIRHMIB.SIGN = AIB3510_AWA_3510.DRCR_FLG;
            T000_STARTBR_AITHMIB_BY_VAL();
        } else {
            B310_FOUND_PROC();
            IBS.init(SCCGWA, AIRHMIB);
            IBS.init(SCCGWA, AICRHMIB);
            AIRHMIB.KEY.BOOK_FLG = AIB3510_AWA_3510.BOOK_FLG;
            AIRHMIB.CCY = AIB3510_AWA_3510.CCY;
            AIRHMIB.KEY.AC = IBS.CLS2CPY(SCCGWA, WS_BR_AC);
            AIRHMIB.KEY.SET_NO = AIB3510_AWA_3510.GREF_NO;
            AIRHMIB.SIGN = AIB3510_AWA_3510.DRCR_FLG;
            T000_STARTBR_AITHMIB();
        }
        T000_READNEXT_AITHMIB();
        WS_CNT = 0;
        while (AICRHMIB.RETURN_INFO != 'N' 
            && WS_CNT <= 10) {
            CEP.TRC(SCCGWA, WS_CNT);
            IBS.init(SCCGWA, BPRTLT);
            BPRTLT.KEY.TLR = AIRHMIB.TR_TELLER;
            T100_READ_BPTTLT();
            if (((AIB3510_AWA_3510.TLR_TYP == '1' 
                && BPRTLT.TLR_TYP == 'T') 
                || (AIB3510_AWA_3510.TLR_TYP == '2' 
                && BPRTLT.TLR_TYP == 'S') 
                || AIB3510_AWA_3510.TLR_TYP == '0')) {
                WS_STR_POS.WS_DATE = AIRHMIB.KEY.TR_DATE;
                WS_STR_POS.WS_SET_NO = AIRHMIB.KEY.SET_NO;
                WS_STR_POS.WS_SET_SEQ = AIRHMIB.KEY.SET_SEQ;
                R100_TRANS_DATA_OUTPUT();
            }
            T000_READNEXT_AITHMIB();
        }
        T000_ENDBR_AITHMIB();
    }
    public void B210_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        if (AICRHMIB.RETURN_INFO == 'N') {
            AICF351.END_FLG = 'Y';
        }
        if (AICF351.END_POS == null) AICF351.END_POS = "";
        JIBS_tmp_int = AICF351.END_POS.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) AICF351.END_POS += " ";
        JIBS_tmp_str[0] = "" + WS_S;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<9-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        AICF351.END_POS = AICF351.END_POS.substring(0, 25 - 1) + JIBS_tmp_str[0] + AICF351.END_POS.substring(25 + 9 - 1);
        SCCFMT.DATA_PTR = AICF351;
        SCCFMT.DATA_LEN = 9370;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B310_FOUND_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_AITHMIB();
        T000_READNEXT_AITHMIB();
        while (AICRHMIB.RETURN_INFO != 'N') {
            IBS.init(SCCGWA, BPRTLT);
            BPRTLT.KEY.TLR = AIRHMIB.TR_TELLER;
            T100_READ_BPTTLT();
            if (((AIB3510_AWA_3510.TLR_TYP == '1' 
                && BPRTLT.TLR_TYP == 'T') 
                || (AIB3510_AWA_3510.TLR_TYP == '2' 
                && BPRTLT.TLR_TYP == 'S') 
                || AIB3510_AWA_3510.TLR_TYP == '0')) {
                WS_S += 1;
            }
            T000_READNEXT_AITHMIB();
        }
        T000_ENDBR_AITHMIB();
    }
    public void T000_SUMMARY_AITHMIB() throws IOException,SQLException,Exception {
        AITHMIB_RD = new DBParm();
        AITHMIB_RD.TableName = "AITHMIB";
        AITHMIB_RD.set = "WS-TOT-CNT=COUNT(*)";
        AITHMIB_RD.where = "AC = :AIRHMIB.KEY.AC "
            + "AND TR_DATE BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT "
            + "AND BOOK_FLG = :AIRHMIB.KEY.BOOK_FLG "
            + "AND ( ' ' = :AIRHMIB.KEY.SET_NO "
            + "OR SET_NO = :AIRHMIB.KEY.SET_NO ) "
            + "AND ( ' ' = :AIRHMIB.SIGN "
            + "OR SIGN = :AIRHMIB.SIGN ) "
            + "AND ( ( ' ' = :WS_STR_AMT "
            + "AND 0 = :WS_END_AMT ) "
            + "OR ( AMT BETWEEN :WS_STR_AMT "
            + "AND :WS_END_AMT ) )";
        IBS.GROUP(SCCGWA, AIRHMIB, this, AITHMIB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_TOT_CNT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "SUMMARY AITHMIB ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITHMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_AITHMIB() throws IOException,SQLException,Exception {
        AITHMIB_BR.rp = new DBParm();
        AITHMIB_BR.rp.TableName = "AITHMIB";
        AITHMIB_BR.rp.where = "AC = :AIRHMIB.KEY.AC "
            + "AND TR_DATE BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT "
            + "AND BOOK_FLG = :AIRHMIB.KEY.BOOK_FLG "
            + "AND ( ' ' = :AIRHMIB.KEY.SET_NO "
            + "OR SET_NO = :AIRHMIB.KEY.SET_NO ) "
            + "AND ( ' ' = :AIRHMIB.SIGN "
            + "OR SIGN = :AIRHMIB.SIGN ) "
            + "AND ( ( ' ' = :WS_STR_AMT "
            + "AND 0 = :WS_END_AMT ) "
            + "OR ( AMT BETWEEN :WS_STR_AMT "
            + "AND :WS_END_AMT ) )";
        AITHMIB_BR.rp.order = "AC,TR_DATE,SET_NO,SET_SEQ";
        IBS.STARTBR(SCCGWA, AIRHMIB, this, AITHMIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "OPEN AITHMIB ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITHMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_AITHMIB_BY_VAL() throws IOException,SQLException,Exception {
        AITHMIB_BR.rp = new DBParm();
        AITHMIB_BR.rp.TableName = "AITHMIB";
        AITHMIB_BR.rp.where = "AC = :AIRHMIB.KEY.AC "
            + "AND BOOK_FLG = :AIRHMIB.KEY.BOOK_FLG "
            + "AND ( ( ( ( SET_NO = :AIRHMIB.KEY.SET_NO "
            + "AND SET_SEQ > :AIRHMIB.KEY.SET_SEQ ) "
            + "OR ( SET_NO > :AIRHMIB.KEY.SET_NO ) ) "
            + "AND TR_DATE = :WS_STR_DT ) "
            + "OR ( TR_DATE > :WS_STR_DT "
            + "AND TR_DATE <= :WS_END_DT ) ) "
            + "AND ( 0 = :AIRHMIB.SIGN "
            + "OR SIGN = :AIRHMIB.SIGN ) "
            + "AND ( ( ' ' = :WS_STR_AMT "
            + "AND 0 = :WS_END_AMT ) "
            + "OR ( AMT BETWEEN :WS_STR_AMT "
            + "AND :WS_END_AMT ) )";
        AITHMIB_BR.rp.order = "AC,TR_DATE,SET_NO,SET_SEQ";
        IBS.STARTBR(SCCGWA, AIRHMIB, this, AITHMIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "OPEN AITHMIB ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITHMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_AITHMIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRHMIB, this, AITHMIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRHMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRHMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITHMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_AITHMIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITHMIB_BR);
    }
    public void T100_READ_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.where = "TLR = :BPRTLT.KEY.TLR";
        IBS.READ(SCCGWA, BPRTLT, this, BPTTLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
            S100_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void R100_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_STR_POS);
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, AIRHMIB.KEY.SET_NO);
        CEP.TRC(SCCGWA, AIRHMIB.KEY.SET_SEQ);
        AICF351.DATA[WS_CNT-1].DT = AIRHMIB.KEY.TR_DATE;
        WS_STR_POS.WS_DATE = AIRHMIB.KEY.TR_DATE;
        AICF351.DATA[WS_CNT-1].SYSDT = AIRHMIB.REQFM_DT;
        AICF351.DATA[WS_CNT-1].TM = AIRHMIB.TR_TIME;
        AICF351.DATA[WS_CNT-1].BR = AIRHMIB.BR;
        AICF351.DATA[WS_CNT-1].ITM = AIRHMIB.ITM;
        AICF351.DATA[WS_CNT-1].AC_SEQ = AIRHMIB.SEQ;
        AICF351.DATA[WS_CNT-1].CCY = AIRHMIB.CCY;
        AICF351.DATA[WS_CNT-1].SIGN = AIRHMIB.SIGN;
        AICF351.DATA[WS_CNT-1].AMT_S = AIRHMIB.AMT;
        AICF351.DATA[WS_CNT-1].AFTAMT = AIRHMIB.AFT_AMT;
        AICF351.DATA[WS_CNT-1].TX_CD = AIRHMIB.TR_CODE;
        AICF351.DATA[WS_CNT-1].JRNNO = AIRHMIB.KEY.SET_NO;
        WS_STR_POS.WS_SET_NO = AIRHMIB.KEY.SET_NO;
        AICF351.DATA[WS_CNT-1].NUM = AIRHMIB.KEY.SET_SEQ;
        WS_STR_POS.WS_SET_SEQ = AIRHMIB.KEY.SET_SEQ;
        AICF351.DATA[WS_CNT-1].TR_BR = AIRHMIB.TR_BR;
        AICF351.DATA[WS_CNT-1].TLR_NO = AIRHMIB.TR_TELLER;
        AICF351.DATA[WS_CNT-1].RMK_60 = AIRHMIB.DESC;
        AICF351.DATA[WS_CNT-1].TRM_NO = AIRHMIB.TM_NO;
        AICF351.DATA[WS_CNT-1].CHNL_NO = AIRHMIB.CHNL_NO;
        AICF351.DATA[WS_CNT-1].RVS_NO = AIRHMIB.RVS_NO;
        AICF351.DATA[WS_CNT-1].RVS_SEQ = AIRHMIB.RVS_SEQ;
        AICF351.DATA[WS_CNT-1].CAN_FLG = AIRHMIB.EC_IND;
        AICF351.DATA[WS_CNT-1].ODE_FLG = AIRHMIB.ODE_FLG;
        AICF351.DATA[WS_CNT-1].EXREF_NO = AIRHMIB.OTHSYS_KEY;
        AICF351.TOD_REC_NUM = WS_CNT;
        AICF351.END_POS = IBS.CLS2CPY(SCCGWA, WS_STR_POS);
        CEP.TRC(SCCGWA, AIB3510_AWA_3510.STR_POS);
        CEP.TRC(SCCGWA, WS_TOT_CNT);
        if (AIB3510_AWA_3510.STR_POS.trim().length() == 0) {
            if (AICF351.END_POS == null) AICF351.END_POS = "";
            JIBS_tmp_int = AICF351.END_POS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) AICF351.END_POS += " ";
            JIBS_tmp_str[0] = "" + WS_TOT_CNT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<9-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            AICF351.END_POS = AICF351.END_POS.substring(0, 25 - 1) + JIBS_tmp_str[0] + AICF351.END_POS.substring(25 + 9 - 1);
        }
        if (AICF351.END_POS == null) AICF351.END_POS = "";
        JIBS_tmp_int = AICF351.END_POS.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) AICF351.END_POS += " ";
        CEP.TRC(SCCGWA, AICF351.END_POS.substring(25 - 1, 25 + 9 - 1));
        CEP.TRC(SCCGWA, AICF351.END_POS);
        AICF351.END_FLG = 'N';
        AICF351.DATA[WS_CNT-1].THEIR_AC = AIRHMIB.THEIR_AC;
        AICF351.DATA[WS_CNT-1].PAY_MAN = AIRHMIB.PAY_MAN;
        AICF351.DATA[WS_CNT-1].PAY_BR = AIRHMIB.PAY_BR;
        AICF351.DATA[WS_CNT-1].PAY_BKNM = AIRHMIB.PAY_BKNM;
        AICF351.DATA[WS_CNT-1].POST_NARR = AIRHMIB.POST_NARR;
    }
    public void S000_CALL_AIZRMIB() throws IOException,SQLException,Exception {
        AICRMIB.POINTER = AIRMIB;
        AICRMIB.REC_LEN = 634;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIB", AICRMIB);
        if (AICRMIB.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICRMIB.RC);
        }
    }
    public void S100_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

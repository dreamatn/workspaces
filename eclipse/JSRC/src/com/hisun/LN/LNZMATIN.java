package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZMATIN {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    DBParm LNTSCHT_RD;
    brParm LNTSCHT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char PRINCIPAL = 'P';
    char PRINCIINT = 'I';
    short PAGE_ROW = 20;
    LNZMATIN_WS_VARIABLES WS_VARIABLES = new LNZMATIN_WS_VARIABLES();
    LNZMATIN_WS_OUT_RECODE WS_OUT_RECODE = new LNZMATIN_WS_OUT_RECODE();
    LNZMATIN_WS_COND_FLG WS_COND_FLG = new LNZMATIN_WS_COND_FLG();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCMTSCH LNCMTSCH = new LNCMTSCH();
    LNCCMSCH LNCCMSCH = new LNCCMSCH();
    LNRCONT LNRCONT = new LNRCONT();
    LNRSCHT LNRSCHT = new LNRSCHT();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRPLPI_WS_DB_VARS WS_DB_VARS = new LNRPLPI_WS_DB_VARS();
    SCCGWA SCCGWA;
    LNCMATIN LNCMATIN;
    SCCGBPA_BP_AREA BP_AREA;
    SCCGSCA_SC_AREA SC_AREA;
    public void MP(SCCGWA SCCGWA, LNCMATIN LNCMATIN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCMATIN = LNCMATIN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZMATIN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (LNCMATIN.COMM_DATA.FUNC == 'I') {
            B100_INQUIRE_PROCESS();
            if (pgmRtn) return;
        } else if (LNCMATIN.COMM_DATA.FUNC == 'A') {
            B100_ADD_PROCESS();
            if (pgmRtn) return;
            B100_INQUIRE_PROCESS();
            if (pgmRtn) return;
        } else if (LNCMATIN.COMM_DATA.FUNC == 'M') {
            B300_MODIFY_PROCESS();
            if (pgmRtn) return;
            B100_INQUIRE_PROCESS();
            if (pgmRtn) return;
        } else if (LNCMATIN.COMM_DATA.FUNC == 'D') {
            B400_DELETE_PROCESS();
            if (pgmRtn) return;
            B100_INQUIRE_PROCESS();
            if (pgmRtn) return;
        } else if (LNCMATIN.COMM_DATA.FUNC == 'C') {
            B500_VERIFY_PROCESS();
            if (pgmRtn) return;
            B100_INQUIRE_PROCESS();
            if (pgmRtn) return;
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCMATIN.COMM_DATA.FUNC);
        if (LNCMATIN.COMM_DATA.FUNC != 'I' 
            && LNCMATIN.COMM_DATA.FUNC != 'A' 
            && LNCMATIN.COMM_DATA.FUNC != 'M' 
            && LNCMATIN.COMM_DATA.FUNC != 'D' 
            && LNCMATIN.COMM_DATA.FUNC != 'C') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, LNCMATIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_INQUIRE_PROCESS() throws IOException,SQLException,Exception {
        if (LNCMATIN.COMM_DATA.CTA_NO.compareTo(SPACE) <= 0) {
            LNCMATIN.COMM_DATA.CTA_NO = " ";
        }
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (LNCMATIN.COMM_DATA.PAGE_NUM == 0) {
            WS_VARIABLES.WS_DATE.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.WS_DATE.CURR_PAGE = (short) LNCMATIN.COMM_DATA.PAGE_NUM;
        }
        WS_VARIABLES.WS_DATE.LAST_PAGE = 'N';
        if (LNCMATIN.COMM_DATA.PAGE_ROW == 0) {
            WS_VARIABLES.WS_DATE.PAGE_ROW = PAGE_ROW;
        } else {
            if (LNCMATIN.COMM_DATA.PAGE_ROW > PAGE_ROW) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAGE_ROW, LNCMATIN.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) LNCMATIN.COMM_DATA.PAGE_ROW;
            }
        }
        WS_VARIABLES.WS_DATE.NEXT_START_NUM = ( ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATE.NEXT_START_NUM;
        IBS.init(SCCGWA, LNRSCHT);
        T000_STARTBR_LNTSCHT();
        if (pgmRtn) return;
        T000_READNEXT_LNTSCHT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            for (WS_VARIABLES.WS_DATE.IDX = 1; (WS_VARIABLES.WS_DATE.IDX <= WS_VARIABLES.WS_DATE.PAGE_ROW) 
                && SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_VARIABLES.WS_DATE.IDX += 1) {
                R000_OUTPUT_DATA_PROC();
                if (pgmRtn) return;
                T000_READNEXT_LNTSCHT();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.IDX - 1;
                WS_VARIABLES.WS_DATE.TOTAL_NUM = ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW + WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) WS_VARIABLES.WS_DATE.BAL_CNT;
            } else {
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.TOTAL_NUM % WS_VARIABLES.WS_DATE.PAGE_ROW;
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = (short) ((WS_VARIABLES.WS_DATE.TOTAL_NUM - WS_VARIABLES.WS_DATE.BAL_CNT) / WS_VARIABLES.WS_DATE.PAGE_ROW);
                if (WS_VARIABLES.WS_DATE.BAL_CNT != 0) {
                    WS_VARIABLES.WS_DATE.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_VARIABLES.WS_DATE.TOTAL_PAGE = 1;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_DATE.PAGE_ROW = 0;
        }
        T000_ENDBR_LNTSCHT();
        if (pgmRtn) return;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATE.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATE.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATE.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_ROW = WS_VARIABLES.WS_DATE.PAGE_ROW;
        R000_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B100_ADD_PROCESS() throws IOException,SQLException,Exception {
        if (LNCMATIN.COMM_DATA.CTA_NO.compareTo(SPACE) <= 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_CONT_NO_MUST_INPUT, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNCMATIN.COMM_DATA.REPY_TYP > SPACE) 
            && (LNCMATIN.COMM_DATA.REPY_TYP != LNCMATIN.COMM_DATA.LIST_TYP)) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_LN5732, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCMATIN.COMM_DATA.LIST_TYP == PRINCIPAL 
            && LNCMATIN.COMM_DATA.REPY_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAY_P_AMT_ZERO, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCMATIN.COMM_DATA.LIST_TYP == PRINCIINT 
            && LNCMATIN.COMM_DATA.REPY_AMT != 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAY_I_AMT_NOT, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCMTSCH);
        LNCMTSCH.COMM_DATA.FUNC = 'A';
        LNCMTSCH.COMM_DATA.CTA_NO = LNCMATIN.COMM_DATA.CTA_NO;
        LNCMTSCH.COMM_DATA.TRAN_SEQ = LNCMATIN.COMM_DATA.TRAN_SEQ;
        LNCMTSCH.COMM_DATA.REPY_TYP = LNCMATIN.COMM_DATA.REPY_TYP;
        LNCMTSCH.COMM_DATA.LIST_TYP = LNCMATIN.COMM_DATA.LIST_TYP;
        LNCMTSCH.COMM_DATA.TERM = LNCMATIN.COMM_DATA.TERM_NO;
        LNCMTSCH.COMM_DATA.VAL_DT = LNCMATIN.COMM_DATA.VAL_DAT;
        LNCMTSCH.COMM_DATA.DUE_DT = LNCMATIN.COMM_DATA.DUE_DAT;
        LNCMTSCH.COMM_DATA.RATE = LNCMATIN.COMM_DATA.RATE;
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.RATE);
        LNCMTSCH.COMM_DATA.AMOUNT = LNCMATIN.COMM_DATA.REPY_AMT;
        LNCMTSCH.COMM_DATA.REMARK = LNCMATIN.COMM_DATA.RMK;
        S000_CALL_LNZMTSCH();
        if (pgmRtn) return;
    }
    public void B300_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        if (LNCMATIN.COMM_DATA.CTA_NO.compareTo(SPACE) <= 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_CONT_NO_MUST_INPUT, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCMATIN.COMM_DATA.LIST_TYP == PRINCIINT 
            && LNCMATIN.COMM_DATA.REPY_AMT != 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAY_I_AMT_NOT, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCMTSCH);
        LNCMTSCH.COMM_DATA.FUNC = 'M';
        LNCMTSCH.COMM_DATA.CTA_NO = LNCMATIN.COMM_DATA.CTA_NO;
        LNCMTSCH.COMM_DATA.TRAN_SEQ = LNCMATIN.COMM_DATA.TRAN_SEQ;
        LNCMTSCH.COMM_DATA.REPY_TYP = LNCMATIN.COMM_DATA.REPY_TYP;
        LNCMTSCH.COMM_DATA.LIST_TYP = LNCMATIN.COMM_DATA.LIST_TYP;
        LNCMTSCH.COMM_DATA.TERM = LNCMATIN.COMM_DATA.TERM_NO;
        LNCMTSCH.COMM_DATA.DUE_DT = LNCMATIN.COMM_DATA.DUE_DAT;
        LNCMTSCH.COMM_DATA.AMOUNT = LNCMATIN.COMM_DATA.REPY_AMT;
        LNCMTSCH.COMM_DATA.REMARK = LNCMATIN.COMM_DATA.RMK;
        S000_CALL_LNZMTSCH();
        if (pgmRtn) return;
    }
    public void B400_DELETE_PROCESS() throws IOException,SQLException,Exception {
        if (LNCMATIN.COMM_DATA.CTA_NO.compareTo(SPACE) <= 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_CONT_NO_MUST_INPUT, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    IBS.init(SCCGWA, LNCMTSCH);
    LNCMTSCH.COMM_DATA.FUNC = 'D';
    LNCMTSCH.COMM_DATA.CTA_NO = LNCMATIN.COMM_DATA.CTA_NO;
    LNCMTSCH.COMM_DATA.TRAN_SEQ = LNCMATIN.COMM_DATA.TRAN_SEQ;
    LNCMTSCH.COMM_DATA.REPY_TYP = LNCMATIN.COMM_DATA.REPY_TYP;
    LNCMTSCH.COMM_DATA.LIST_TYP = LNCMATIN.COMM_DATA.LIST_TYP;
    LNCMTSCH.COMM_DATA.TERM = LNCMATIN.COMM_DATA.TERM_NO;
    LNCMTSCH.COMM_DATA.VAL_DT = LNCMATIN.COMM_DATA.VAL_DAT;
    LNCMTSCH.COMM_DATA.DUE_DT = LNCMATIN.COMM_DATA.DUE_DAT;
    LNCMTSCH.COMM_DATA.RATE = LNCMATIN.COMM_DATA.RATE;
    LNCMTSCH.COMM_DATA.AMOUNT = LNCMATIN.COMM_DATA.REPY_AMT;
    LNCMTSCH.COMM_DATA.REMARK = LNCMATIN.COMM_DATA.RMK;
    S000_CALL_LNZMTSCH();
    if (pgmRtn) return;
    public void B500_VERIFY_PROCESS() throws IOException,SQLException,Exception {
        if (LNCMATIN.COMM_DATA.CTA_NO.compareTo(SPACE) <= 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCMATIN.COMM_DATA.CTA_NO;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRSCHT);
        if (LNCMATIN.COMM_DATA.CTA_NO.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNCMATIN.COMM_DATA.CTA_NO);
        LNRSCHT.KEY.SUB_CTA_NO = "" + 0;
        JIBS_tmp_int = LNRSCHT.KEY.SUB_CTA_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNRSCHT.KEY.SUB_CTA_NO = "0" + LNRSCHT.KEY.SUB_CTA_NO;
        LNRSCHT.KEY.TRAN_SEQ = LNCMATIN.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = 'P';
        T000_READ_LNTSCHT();
        if (pgmRtn) return;
        LNRSCHT.KEY.TYPE = 'I';
        T000_READ_LNTSCHT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCCMSCH);
        LNCCMSCH.COMM_DATA.SEQ_NO = LNCMATIN.COMM_DATA.TRAN_SEQ;
        LNCCMSCH.COMM_DATA.CTA_NO = LNCMATIN.COMM_DATA.CTA_NO;
        LNCCMSCH.COMM_DATA.REPY_TYP = LNCMATIN.COMM_DATA.REPY_TYP;
        LNCCMSCH.COMM_DATA.PROD_CD = LNCMATIN.COMM_DATA.PRD_TYP;
        LNCCMSCH.COMM_DATA.CCY = LNCMATIN.COMM_DATA.CCY;
        LNCCMSCH.COMM_DATA.PRIN_AMT = LNCMATIN.COMM_DATA.PRIN_AMT;
        LNCCMSCH.COMM_DATA.OS_BAL = 0;
        LNCCMSCH.COMM_DATA.OS_BAL = LNCMATIN.COMM_DATA.OS_BAL;
        LNCCMSCH.COMM_DATA.CI_CNM = LNCMATIN.COMM_DATA.CI_CNM;
        S000_CALL_SVR_LNZCMSCH();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATE.IDX-1]);
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATE.IDX-1].O_TYPE = LNRSCHT.KEY.TYPE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATE.IDX-1].O_TERM = LNRSCHT.KEY.TERM;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATE.IDX-1].O_VAL_DT = LNRSCHT.VAL_DTE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATE.IDX-1].O_DUE_DT = LNRSCHT.DUE_DTE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATE.IDX-1].O_ALL_IN_RATE = LNRSCHT.ALL_IN_RATE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATE.IDX-1].O_AMOUNT = LNRSCHT.AMOUNT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATE.IDX-1].O_REMARK = LNRSCHT.REMARK;
        WS_VARIABLES.WS_DATE.NEXT_START_NUM += 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATE.NEXT_START_NUM;
    }
    public void R000_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = "LN310";
            SCCFMT.DATA_PTR = WS_OUT_RECODE;
            SCCFMT.DATA_LEN = 4294;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.CONT_NFND, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTSCHT() throws IOException,SQLException,Exception {
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.eqWhere = "TRAN_SEQ,CONTRACT_NO,SUB_CTA_NO,TYPE";
        LNTSCHT_RD.fst = true;
        LNTSCHT_RD.order = "TERM DESC";
        IBS.READ(SCCGWA, LNRSCHT, LNTSCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            && LNRSCHT.DUE_DTE != LNRCONT.MAT_DATE) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_PLAN_NOT_CPLT, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTSCHT() throws IOException,SQLException,Exception {
        LNRSCHT.KEY.SUB_CTA_NO = LNCMATIN.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.TRAN_SEQ = LNCMATIN.COMM_DATA.TRAN_SEQ;
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.upd = true;
        LNTSCHT_BR.rp.where = "CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, LNCMATIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_LNTSCHT() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, LNCMATIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSCHT_BR);
    }
    public void S000_CALL_LNZMTSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-MAINTIAN-SCHT", LNCMTSCH, true);
    }
    public void S000_CALL_SVR_LNZCMSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CONFIRM-SCHE", LNCCMSCH, true);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_TEMP_VARIABLE.FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCMATIN.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCMATIN=");
            CEP.TRC(SCCGWA, LNCMATIN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

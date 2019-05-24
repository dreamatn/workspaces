package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSPPRM {
    LNZSPPRM_WS_OUT_INFO WS_OUT_INFO;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTPPRP_RD;
    brParm LNTPPRP_BR = new brParm();
    DBParm LNTAPRD_RD;
    DBParm LNTEXTN_RD;
    DBParm LNTCONT_RD;
    DBParm LNTAGRE_RD;
    brParm LNTCONT_BR = new brParm();
    DBParm LNTICTL_RD;
    boolean pgmRtn = false;
    short Q_MAX_CNT = 1000;
    short PAGE_ROW = 20;
    LNZSPPRM_WS_VARIABLES WS_VARIABLES = new LNZSPPRM_WS_VARIABLES();
    LNZSPPRM_WS_OUT_RECODE WS_OUT_RECODE = new LNZSPPRM_WS_OUT_RECODE();
    LNZSPPRM_WS_COND_FLG WS_COND_FLG = new LNZSPPRM_WS_COND_FLG();
    LNCXQ28 LNCXQ28 = new LNCXQ28();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    LNRPPRP LNRPPRP = new LNRPPRP();
    LNCRPPRP LNCRPPRP = new LNCRPPRP();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNCSPREQ LNCSPREQ = new LNCSPREQ();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNREXTN LNREXTN = new LNREXTN();
    LNRCONT LNRCONT = new LNRCONT();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNCPAIPM LNCPAIPM = new LNCPAIPM();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRAGRE_WS_DB_VARS WS_DB_VARS = new LNRAGRE_WS_DB_VARS();
    SCCGWA SCCGWA;
    LNCSPPRM LNCSPPRM;
    public void MP(SCCGWA SCCGWA, LNCSPPRM LNCSPPRM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPPRM = LNCSPPRM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSPPRM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        LNCRPPRP.RETURN_INFO = 'F';
        LNCSPPRM.RC.RC_MMO = "LN";
        LNCSPPRM.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT();
        if (pgmRtn) return;
        if (LNCSPPRM.FUNC == 'I') {
            B010_PROC_QUERY();
            if (pgmRtn) return;
        } else if (LNCSPPRM.FUNC == 'A') {
            B020_PROC_ADD();
            if (pgmRtn) return;
        } else if (LNCSPPRM.FUNC == 'M') {
            B030_PROC_UPDATE();
            if (pgmRtn) return;
        } else if (LNCSPPRM.FUNC == 'D') {
            B040_PROC_DELETE();
            if (pgmRtn) return;
        } else if (LNCSPPRM.FUNC == 'B') {
            B050_PROC_BROWSE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRPPRP.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPPRP);
        IBS.init(SCCGWA, LNCRPPRP);
        CEP.TRC(SCCGWA, LNCSPPRM.COMM_DATA.CONT_NO);
        CEP.TRC(SCCGWA, LNCSPPRM.COMM_DATA.VAL_DT);
        LNRPPRP.KEY.CONTRACT_NO = LNCSPPRM.COMM_DATA.CONT_NO;
        LNRPPRP.KEY.VAL_DT = LNCSPPRM.COMM_DATA.VAL_DT;
        WS_VARIABLES.WS_DATE.CI_NO = LNCSPPRM.COMM_DATA.CI_NO;
        WS_VARIABLES.WS_DATE.CI_CNM = LNCSPPRM.COMM_DATA.CI_CNM;
        WS_VARIABLES.WS_DATE.PROD_CD = LNCSPPRM.COMM_DATA.PROD_CD;
        WS_VARIABLES.WS_DATE.CCY = LNCSPPRM.COMM_DATA.CCY;
        WS_VARIABLES.WS_DATE.LN_PRIN = LNCSPPRM.COMM_DATA.LN_PRIN;
        WS_VARIABLES.WS_DATE.LN_BAL = LNCSPPRM.COMM_DATA.LN_BAL;
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSPPRM.COMM_DATA.CONT_NO;
        T000_READ_CONT_PROC();
        if (pgmRtn) return;
        if (LNRCONT.DOMI_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_BR_NMARCH, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCPCFTA.BR_GP[1-1].BR = LNRCONT.DOMI_BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        if (LNCPCFTA.FTA_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_NO_TRAN_FTA_NOT_SAME, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    CEP.TRC(SCCGWA, LNRPPRP.KEY.CONTRACT_NO);
    CEP.TRC(SCCGWA, LNRPPRP.KEY.VAL_DT);
    public void B010_PROC_QUERY() throws IOException,SQLException,Exception {
        LNCRPPRP.FUNC = 'I';
        S000_CALL_LNZRPPRP();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNRPPRP.KEY.CONTRACT_NO;
        R000_GET_INFO();
        if (pgmRtn) return;
        R010_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B020_PROC_ADD() throws IOException,SQLException,Exception {
        R000_CHECK_CMMT();
        if (pgmRtn) return;
        R000_CHECK_DATA();
        if (pgmRtn) return;
        R000_CHECK_EXTN();
        if (pgmRtn) return;
        WS_DB_VARS.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        R000_CHECK_GROUP();
        if (pgmRtn) return;
        if (WS_VARIABLES.WS_DATE.TOTAL_NUM != 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_EXISTS_RECORD, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_PREPARE_DATA();
        if (pgmRtn) return;
        LNCRPPRP.FUNC = 'A';
        S000_CALL_LNZRPPRP();
        if (pgmRtn) return;
        R010_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B030_PROC_UPDATE() throws IOException,SQLException,Exception {
        R000_CHECK_DATA();
        if (pgmRtn) return;
        LNCRPPRP.FUNC = 'R';
        S000_CALL_LNZRPPRP();
        if (pgmRtn) return;
        R000_PREPARE_DATA();
        if (pgmRtn) return;
        LNCRPPRP.FUNC = 'U';
        S000_CALL_LNZRPPRP();
        if (pgmRtn) return;
        R010_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B040_PROC_DELETE() throws IOException,SQLException,Exception {
        LNCRPPRP.FUNC = 'R';
        S000_CALL_LNZRPPRP();
        if (pgmRtn) return;
        LNCRPPRP.FUNC = 'D';
        S000_CALL_LNZRPPRP();
        if (pgmRtn) return;
        R010_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B050_PROC_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (LNCSPPRM.COMM_DATA.PAGE_NUM == 0) {
            WS_VARIABLES.WS_DATE.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.WS_DATE.CURR_PAGE = (short) LNCSPPRM.COMM_DATA.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
        WS_VARIABLES.WS_DATE.LAST_PAGE = 'N';
        if (LNCSPPRM.COMM_DATA.PAGE_ROW == 0) {
            WS_VARIABLES.WS_DATE.PAGE_ROW = PAGE_ROW;
            WS_OUT_INFO = new LNZSPPRM_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        } else {
            if (LNCSPPRM.COMM_DATA.PAGE_ROW > PAGE_ROW) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAGE_ROW, LNCSPPRM.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) LNCSPPRM.COMM_DATA.PAGE_ROW;
                WS_OUT_INFO = new LNZSPPRM_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            }
        }
        WS_VARIABLES.WS_DATE.NEXT_START_NUM = ( ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATE.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
        if (LNCSPPRM.COMM_DATA.CONT_NO.trim().length() == 0) {
            if (LNCSPPRM.COMM_DATA.BOOK_BR != 0) {
                B050_01_PROC_BROWSE();
                if (pgmRtn) return;
            } else {
                B050_02_PROC_BROWSE();
                if (pgmRtn) return;
            }
        } else {
            B050_03_PROC_BROWSE();
            if (pgmRtn) return;
        }
    }
    public void B050_01_PROC_BROWSE() throws IOException,SQLException,Exception {
        B050_STARTBR_BY_BR_PROC();
        if (pgmRtn) return;
        B050_READNEXT_BY_BR_PROC();
        if (pgmRtn) return;
        if (WS_COND_FLG.FOUND_FLG != 'N') {
            WS_VARIABLES.WS_DATE.IDX = 0;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            while (WS_COND_FLG.FOUND_FLG != 'N') {
                IBS.init(SCCGWA, LNCCLNQ);
                LNCCLNQ.DATA.CONT_NO = LNRPPRP.KEY.CONTRACT_NO;
                R000_GET_INFO();
                if (pgmRtn) return;
                if (WS_VARIABLES.WS_DATE.BOOK_BR == LNCSPPRM.COMM_DATA.BOOK_BR) {
                    WS_VARIABLES.WS_DATE.TOTAL_NUM += 1;
                    if (WS_VARIABLES.WS_DATE.TOTAL_NUM >= WS_VARIABLES.WS_DATE.NEXT_START_NUM 
                        && WS_VARIABLES.WS_DATE.IDX < WS_VARIABLES.WS_DATE.PAGE_ROW) {
                        B000_WRITE_OUTPUT();
                        if (pgmRtn) return;
                    }
                }
                B050_READNEXT_BY_BR_PROC();
                if (pgmRtn) return;
            }
            WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.TOTAL_NUM % WS_VARIABLES.WS_DATE.PAGE_ROW;
            WS_VARIABLES.WS_DATE.TOTAL_PAGE = (short) ((WS_VARIABLES.WS_DATE.TOTAL_NUM - WS_VARIABLES.WS_DATE.BAL_CNT) / WS_VARIABLES.WS_DATE.PAGE_ROW);
            if (WS_VARIABLES.WS_DATE.BAL_CNT != 0) {
                WS_VARIABLES.WS_DATE.TOTAL_PAGE += 1;
            }
            if (WS_VARIABLES.WS_DATE.CURR_PAGE == WS_VARIABLES.WS_DATE.TOTAL_PAGE) {
                WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
                if (WS_VARIABLES.WS_DATE.BAL_CNT != 0) {
                    WS_VARIABLES.WS_DATE.PAGE_ROW = (short) WS_VARIABLES.WS_DATE.BAL_CNT;
                    WS_OUT_INFO = new LNZSPPRM_WS_OUT_INFO();
                    WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
                }
            }
        } else {
            WS_VARIABLES.WS_DATE.TOTAL_PAGE = 1;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_DATE.PAGE_ROW = 0;
            WS_OUT_INFO = new LNZSPPRM_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        }
        B050_END_BROWSE_PROC();
        if (pgmRtn) return;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATE.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATE.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATE.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.WS_DATE.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        R020_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B050_02_PROC_BROWSE() throws IOException,SQLException,Exception {
        B050_START_BROWSE_PROC();
        if (pgmRtn) return;
        B050_READ_NEXT_PROC();
        if (pgmRtn) return;
        if (WS_COND_FLG.FOUND_FLG != 'N') {
            WS_VARIABLES.WS_DATE.IDX = 0;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            while (WS_VARIABLES.WS_DATE.IDX != WS_VARIABLES.WS_DATE.PAGE_ROW 
                && WS_COND_FLG.FOUND_FLG != 'N') {
                IBS.init(SCCGWA, LNCCLNQ);
                LNCCLNQ.DATA.CONT_NO = LNRPPRP.KEY.CONTRACT_NO;
                R000_GET_INFO();
                if (pgmRtn) return;
                B000_WRITE_OUTPUT();
                if (pgmRtn) return;
                WS_VARIABLES.WS_DATE.NEXT_START_NUM += 1;
                WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATE.NEXT_START_NUM;
                B050_READ_NEXT_PROC();
                if (pgmRtn) return;
            }
            if (WS_COND_FLG.FOUND_FLG == 'N') {
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.IDX;
                WS_VARIABLES.WS_DATE.TOTAL_NUM = ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW + WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_OUT_INFO = new LNZSPPRM_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            } else {
                WS_DB_VARS.VAL_DATE = LNCSPPRM.COMM_DATA.VAL_DT;
                R000_GROUP_ALL();
                if (pgmRtn) return;
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
            WS_OUT_INFO = new LNZSPPRM_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        }
        B050_END_BROWSE_PROC();
        if (pgmRtn) return;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATE.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATE.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATE.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.WS_DATE.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        R020_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B050_03_PROC_BROWSE() throws IOException,SQLException,Exception {
        B050_STARTBR_BY_KEY_PROC();
        if (pgmRtn) return;
        B050_READ_NEXT_PROC();
        if (pgmRtn) return;
        if (WS_COND_FLG.FOUND_FLG != 'N') {
            WS_VARIABLES.WS_DATE.IDX = 0;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            while (WS_VARIABLES.WS_DATE.IDX != WS_VARIABLES.WS_DATE.PAGE_ROW 
                && WS_COND_FLG.FOUND_FLG != 'N') {
                IBS.init(SCCGWA, LNCCLNQ);
                LNCCLNQ.DATA.CONT_NO = LNRPPRP.KEY.CONTRACT_NO;
                R000_GET_INFO();
                if (pgmRtn) return;
                B000_WRITE_OUTPUT();
                if (pgmRtn) return;
                WS_VARIABLES.WS_DATE.NEXT_START_NUM += 1;
                WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATE.NEXT_START_NUM;
                B050_READ_NEXT_PROC();
                if (pgmRtn) return;
            }
            if (WS_COND_FLG.FOUND_FLG == 'N') {
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.IDX;
                WS_VARIABLES.WS_DATE.TOTAL_NUM = ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW + WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_OUT_INFO = new LNZSPPRM_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            } else {
                LNRPPRP.KEY.CONTRACT_NO = LNCSPPRM.COMM_DATA.CONT_NO;
                WS_DB_VARS.VAL_DATE = LNCSPPRM.COMM_DATA.VAL_DT;
                R000_CHECK_GROUP();
                if (pgmRtn) return;
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
            WS_OUT_INFO = new LNZSPPRM_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        }
        B050_END_BROWSE_PROC();
        if (pgmRtn) return;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATE.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATE.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATE.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.WS_DATE.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        R020_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void R000_CHECK_DATA() throws IOException,SQLException,Exception {
        WS_VARIABLES.INT_MODE_FLAG = LNCSPPRM.COMM_DATA.INT_MODE;
        if ((WS_VARIABLES.INT_MODE_FLAG != '0' 
            && WS_VARIABLES.INT_MODE_FLAG != '1' 
            && WS_VARIABLES.INT_MODE_FLAG != '2')) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_G_INT_MODE, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCSPPRM.COMM_DATA.CONT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (!LNRICTL.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_LOAN_STS, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSPPRM.COMM_DATA.VAL_DT >= LNRICTL.IC_CAL_DUE_DT) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_VAL_DATE, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_CTA_KIND_PPRM, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSPPRM.COMM_DATA.ADJ_FLG == '3' 
            && LNCSPPRM.COMM_DATA.SUB_TERM <= 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCPAIPM);
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCSPPRM.COMM_DATA.CONT_NO;
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
        if (LNRAPRD.PAY_MTH == '4') {
            LNCPAIPM.FUNC = '5';
            LNCPAIPM.REC_DATA.KEY.CONTRACT_NO = LNCSPPRM.COMM_DATA.CONT_NO;
            S000_CALL_LNZPAIPM();
            if (pgmRtn) return;
            WS_VARIABLES.WS_DATE.PHS_TOT_TERM = LNCPAIPM.REC_DATA.PHS_TOT_TERM;
            WS_VARIABLES.WS_DATE.PHS_CAL_TERM = LNCPAIPM.REC_DATA.PHS_CAL_TERM;
            WS_VARIABLES.WS_DATE.INST_TERM = (short) (WS_VARIABLES.WS_DATE.PHS_TOT_TERM - WS_VARIABLES.WS_DATE.PHS_CAL_TERM);
            if (WS_VARIABLES.WS_DATE.INST_TERM < LNCSPPRM.COMM_DATA.SUB_TERM) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_FIX_TERM_BIG, LNCSPPRM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCSPPRM.COMM_DATA.CONT_NO;
        R000_GET_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSPPRM.COMM_DATA.CONT_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCSPREQ);
        LNCSPREQ.CTA = LNCSPPRM.COMM_DATA.CONT_NO;
        LNCSPREQ.SUB_TRAN = 'N';
        LNCSPREQ.TR_VAL_DTE = LNCSPPRM.COMM_DATA.VAL_DT;
        LNCSPREQ.TOT_P_AMT = LNCSPPRM.COMM_DATA.PAY_PRIN;
        LNCSPREQ.INT_MODE = LNCSPPRM.COMM_DATA.INT_MODE;
        LNCSPREQ.PC_RATE = LNCSPPRM.COMM_DATA.PC_RATE;
        if (LNCSPPRM.COMM_DATA.PC_RATE == 0) {
            LNCSPREQ.PC_AMT = LNCSPPRM.COMM_DATA.PC_AMT;
        }
        LNCSPREQ.TOT_AMT = LNCSPPRM.COMM_DATA.PAY_AMT;
        LNCSPREQ.FUN_CODE = 'I';
        S000_CALL_LNZSPREQ();
        if (pgmRtn) return;
        WS_VARIABLES.WS_DATE.PAY_PRIN = LNCSPREQ.TOT_P_AMT;
        WS_VARIABLES.WS_DATE.PAY_INT = LNCSPREQ.TOT_I_AMT;
        WS_VARIABLES.WS_DATE.PC_RATE = LNCSPREQ.PC_RATE;
        WS_VARIABLES.WS_DATE.PC_AMT = LNCSPREQ.PC_AMT;
        WS_VARIABLES.WS_DATE.PAY_AMT = LNCSPREQ.TOT_AMT;
        WS_VARIABLES.WS_DATE.HRG_AMT = LNCSPREQ.HRG_AMT;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.PAY_PRIN);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.PAY_INT);
        CEP.TRC(SCCGWA, LNCSPREQ.PC_RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.PC_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.PAY_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.HRG_AMT);
        if (WS_VARIABLES.WS_DATE.PAY_PRIN != 0) {
            if (WS_VARIABLES.WS_DATE.PAY_PRIN > LNCCLNQ.DATA.TOT_BAL) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_NT_PRIN, LNCSPPRM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (WS_VARIABLES.WS_DATE.PAY_PRIN < LNCCLNQ.DATA.TOT_BAL) {
                if (LNCAPRDM.REC_DATA.PAY_MTH == '0' 
                    || LNCAPRDM.REC_DATA.PAY_MTH == '1' 
                    || LNCAPRDM.REC_DATA.PAY_MTH == '2') {
                    LNCSPPRM.COMM_DATA.ADJ_FLG = '1';
                } else {
                    if (LNCSPPRM.COMM_DATA.ADJ_FLG == ' ') {
                        IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_ADJE_FLG, LNCSPPRM.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
            if (WS_VARIABLES.WS_DATE.PAY_PRIN == LNCCLNQ.DATA.TOT_BAL 
                && LNCSPPRM.COMM_DATA.INT_MODE != '1') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MST_INT_MODE, LNCSPPRM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (LNCSPPRM.COMM_DATA.INT_MODE != '1') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_INT_TY, LNCSPPRM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (LNCSPPRM.COMM_DATA.PAY_INT != 0 
            && LNCSPPRM.COMM_DATA.PAY_INT != WS_VARIABLES.WS_DATE.PAY_INT) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.AMT_NOT_EQUAL, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSPPRM.COMM_DATA.PAY_AMT != 0 
            && LNCSPPRM.COMM_DATA.PAY_AMT != WS_VARIABLES.WS_DATE.PAY_AMT) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.AMT_NOT_EQUAL, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSPPRM.COMM_DATA.HRG_AMT != 0) {
            WS_VARIABLES.WS_DATE.HRG_AMT = LNCSPPRM.COMM_DATA.HRG_AMT;
        }
    }
    public void R000_CHECK_CMMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSPPRM.COMM_DATA.CONT_NO;
        T000_READ_CONT_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCSPPRM.COMM_DATA.CONT_NO;
        if (LNRAGRE.KEY.CONTRACT_NO.trim().length() > 0) {
            T000_READ_LNTAGRE();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_EXTN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSPPRM.COMM_DATA.CONT_NO;
        LNREXTN.KEY.VAL_DT = LNCSPPRM.COMM_DATA.VAL_DT;
        T000_READ_LNTEXTN();
        if (pgmRtn) return;
        if (WS_COND_FLG.EXTN_FLAG == 'Y' 
            && LNREXTN.KEY.STATUS == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PPRP_YUAN_QI, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_PREPARE_DATA() throws IOException,SQLException,Exception {
        LNRPPRP.PAY_P_AMT = WS_VARIABLES.WS_DATE.PAY_PRIN;
        LNRPPRP.INT_MODE = LNCSPPRM.COMM_DATA.INT_MODE;
        LNRPPRP.PAY_I_AMT = WS_VARIABLES.WS_DATE.PAY_INT;
        LNRPPRP.PC_RATE = WS_VARIABLES.WS_DATE.PC_RATE;
        LNRPPRP.PC_AMT = WS_VARIABLES.WS_DATE.PC_AMT;
        LNRPPRP.PAY_TOT_AMT = WS_VARIABLES.WS_DATE.PAY_AMT;
        LNRPPRP.HRG_AMT = WS_VARIABLES.WS_DATE.HRG_AMT;
        LNRPPRP.TERM_ADJ_FLG = LNCSPPRM.COMM_DATA.ADJ_FLG;
        LNRPPRP.SUB_TERM = LNCSPPRM.COMM_DATA.SUB_TERM;
        LNRPPRP.STATUS = LNCSPPRM.COMM_DATA.STATUS;
        LNRPPRP.REMARK = LNCSPPRM.COMM_DATA.REMARK;
    }
    public void R000_GET_INFO() throws IOException,SQLException,Exception {
        LNCCLNQ.DATA.SUB_CONT_NO = "" + 0;
        JIBS_tmp_int = LNCCLNQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCLNQ.DATA.SUB_CONT_NO = "0" + LNCCLNQ.DATA.SUB_CONT_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        WS_VARIABLES.WS_DATE.BOOK_BR = LNCCLNQ.DATA.DOMI_BR;
        WS_VARIABLES.WS_DATE.CI_NO = LNCCLNQ.DATA.CI_NO;
        WS_VARIABLES.WS_DATE.CI_CNM = LNCCLNQ.DATA.CI_CNAME;
        WS_VARIABLES.WS_DATE.PROD_CD = LNCCLNQ.DATA.PROD_CD;
        WS_VARIABLES.WS_DATE.CCY = LNCCLNQ.DATA.CCY;
        WS_VARIABLES.WS_DATE.LN_PRIN = LNCCLNQ.DATA.PRIN;
        WS_VARIABLES.WS_DATE.LN_BAL = LNCCLNQ.DATA.TOT_BAL;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.BOOK_BR);
    }
    public void S000_CALL_LNZRPPRP() throws IOException,SQLException,Exception {
        LNCRPPRP.REC_PTR = LNRPPRP;
        LNCRPPRP.REC_LEN = 1282;
        IBS.CALLCPN(SCCGWA, "LN-SRC-LNZRPPRP", LNCRPPRP);
        if (LNCRPPRP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPPRP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 1505;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPREQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-PREPAY-CALC", LNCSPREQ, true);
        if (LNCSPREQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSPREQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        T000_01_GROUP_REC_PROC();
        if (pgmRtn) return;
        WS_VARIABLES.WS_DATE.TOTAL_NUM = WS_DB_VARS.CNT;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.TOTAL_NUM);
    }
    public void R000_CHECK_GROUP() throws IOException,SQLException,Exception {
        T000_02_GROUP_REC_PROC();
        if (pgmRtn) return;
        WS_VARIABLES.WS_DATE.TOTAL_NUM = WS_DB_VARS.CNT;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.TOTAL_NUM);
    }
    public void B050_STARTBR_BY_BR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_BR_PROC();
        if (pgmRtn) return;
    }
    public void B050_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B050_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_PROC();
        if (pgmRtn) return;
    }
    public void B050_READNEXT_BY_BR_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BY_BR_PROC();
        if (pgmRtn) return;
    }
    public void B050_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B050_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_01_GROUP_REC_PROC() throws IOException,SQLException,Exception {
        LNTPPRP_RD = new DBParm();
        LNTPPRP_RD.TableName = "LNTPPRP";
        LNTPPRP_RD.set = "WS-CNT=COUNT(*)";
        LNTPPRP_RD.where = "VAL_DT >= :WS_DB_VARS.VAL_DATE";
        IBS.GROUP(SCCGWA, LNRPPRP, this, LNTPPRP_RD);
    }
    public void T000_02_GROUP_REC_PROC() throws IOException,SQLException,Exception {
        LNTPPRP_RD = new DBParm();
        LNTPPRP_RD.TableName = "LNTPPRP";
        LNTPPRP_RD.set = "WS-CNT=COUNT(*)";
        LNTPPRP_RD.where = "CONTRACT_NO = :LNRPPRP.KEY.CONTRACT_NO "
            + "AND VAL_DT >= :WS_DB_VARS.VAL_DATE";
        IBS.GROUP(SCCGWA, LNRPPRP, this, LNTPPRP_RD);
    }
    public void T000_STARTBR_BY_BR_PROC() throws IOException,SQLException,Exception {
        LNTPPRP_BR.rp = new DBParm();
        LNTPPRP_BR.rp.TableName = "LNTPPRP";
        LNTPPRP_BR.rp.where = "VAL_DT >= :LNRPPRP.KEY.VAL_DT";
        LNTPPRP_BR.rp.order = "VAL_DT,CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRPPRP, this, LNTPPRP_BR);
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTPPRP_BR.rp = new DBParm();
        LNTPPRP_BR.rp.TableName = "LNTPPRP";
        LNTPPRP_BR.rp.where = "VAL_DT >= :LNRPPRP.KEY.VAL_DT";
        LNTPPRP_BR.rp.order = "VAL_DT,CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRPPRP, this, LNTPPRP_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTPPRP_BR.rp = new DBParm();
        LNTPPRP_BR.rp.TableName = "LNTPPRP";
        LNTPPRP_BR.rp.where = "CONTRACT_NO = :LNRPPRP.KEY.CONTRACT_NO "
            + "AND VAL_DT >= :LNRPPRP.KEY.VAL_DT";
        LNTPPRP_BR.rp.order = "VAL_DT,CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRPPRP, this, LNTPPRP_BR);
    }
    public void T000_READNEXT_BY_BR_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPPRP, this, LNTPPRP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        } else {
            WS_COND_FLG.FOUND_FLG = 'N';
        }
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPPRP, this, LNTPPRP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        } else {
            WS_COND_FLG.FOUND_FLG = 'N';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPPRP_BR);
    }
    public void B000_WRITE_OUTPUT() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_DATE.IDX += 1;
        IBS.init(SCCGWA, WS_OUT_INFO);
        WS_OUT_INFO.O_CI_NO = WS_VARIABLES.WS_DATE.CI_NO;
        WS_OUT_INFO.O_CI_CNM = WS_VARIABLES.WS_DATE.CI_CNM;
        WS_OUT_INFO.O_PROD_CD = WS_VARIABLES.WS_DATE.PROD_CD;
        WS_OUT_INFO.O_CCY = WS_VARIABLES.WS_DATE.CCY;
        WS_OUT_INFO.O_LN_PRIN = WS_VARIABLES.WS_DATE.LN_PRIN;
        WS_OUT_INFO.O_LN_BAL = WS_VARIABLES.WS_DATE.LN_BAL;
        WS_OUT_INFO.O_CONT_NO = LNRPPRP.KEY.CONTRACT_NO;
        WS_OUT_INFO.O_VAL_DT = LNRPPRP.KEY.VAL_DT;
        WS_OUT_INFO.O_PAY_PRIN = LNRPPRP.PAY_P_AMT;
        WS_OUT_INFO.O_INT_MODE = LNRPPRP.INT_MODE;
        WS_OUT_INFO.O_PAY_INT = LNRPPRP.PAY_I_AMT;
        WS_OUT_INFO.O_PC_RATE = LNRPPRP.PC_RATE;
        WS_OUT_INFO.O_PC_AMT = LNRPPRP.PC_AMT;
        WS_OUT_INFO.O_PAY_AMT = LNRPPRP.PAY_TOT_AMT;
        WS_OUT_INFO.O_HRG_AMT = LNRPPRP.HRG_AMT;
        WS_OUT_INFO.O_ADJ_FLG = LNRPPRP.TERM_ADJ_FLG;
        WS_OUT_INFO.O_PRE_PERD = LNRPPRP.PRE_PERD;
        WS_OUT_INFO.O_PRE_UNIT = LNRPPRP.PRE_PERD_UNIT;
        WS_OUT_INFO.O_STATUS = LNRPPRP.STATUS;
        WS_OUT_INFO.O_REMARK = LNRPPRP.REMARK;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_CONT_NO);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_VAL_DT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_CI_NO);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_CI_CNM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PROD_CD);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_CCY);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_LN_PRIN);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_LN_BAL);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PAY_PRIN);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_INT_MODE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PAY_INT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PC_RATE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PC_AMT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PAY_AMT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_HRG_AMT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_ADJ_FLG);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PRE_PERD);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PRE_UNIT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_STATUS);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_REMARK);
    }
    public void R010_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCXQ28);
        IBS.init(SCCGWA, SCCFMT);
        LNCXQ28.CONT_NO = LNRPPRP.KEY.CONTRACT_NO;
        LNCXQ28.VAL_DT = LNRPPRP.KEY.VAL_DT;
        LNCXQ28.CI_NO = WS_VARIABLES.WS_DATE.CI_NO;
        LNCXQ28.CI_CNM = WS_VARIABLES.WS_DATE.CI_CNM;
        LNCXQ28.PROD_CD = WS_VARIABLES.WS_DATE.PROD_CD;
        LNCXQ28.CCY = WS_VARIABLES.WS_DATE.CCY;
        LNCXQ28.LN_PRIN = WS_VARIABLES.WS_DATE.LN_PRIN;
        LNCXQ28.LN_BAL = WS_VARIABLES.WS_DATE.LN_BAL;
        LNCXQ28.PAY_PRIN = LNRPPRP.PAY_P_AMT;
        LNCXQ28.INT_MODE = LNRPPRP.INT_MODE;
        LNCXQ28.PAY_INT = LNRPPRP.PAY_I_AMT;
        LNCXQ28.PC_RATE = LNRPPRP.PC_RATE;
        LNCXQ28.PC_AMT = LNRPPRP.PC_AMT;
        LNCXQ28.PAY_AMT = LNRPPRP.PAY_TOT_AMT;
        LNCXQ28.HRG_AMT = LNRPPRP.HRG_AMT;
        LNCXQ28.ADJ_FLG = LNRPPRP.TERM_ADJ_FLG;
        LNCXQ28.SUB_TERM = LNRPPRP.SUB_TERM;
        LNCXQ28.STATUS = LNRPPRP.STATUS;
        LNCXQ28.REMARK = LNRPPRP.REMARK;
        SCCFMT.FMTID = "LN430";
        CEP.TRC(SCCGWA, SCCFMT.FMTID);
        CEP.TRC(SCCGWA, LNCXQ28.CONT_NO);
        CEP.TRC(SCCGWA, LNCXQ28.VAL_DT);
        CEP.TRC(SCCGWA, LNCXQ28.CI_NO);
        CEP.TRC(SCCGWA, LNCXQ28.CI_CNM);
        CEP.TRC(SCCGWA, LNCXQ28.PROD_CD);
        CEP.TRC(SCCGWA, LNCXQ28.CCY);
        CEP.TRC(SCCGWA, LNCXQ28.LN_PRIN);
        CEP.TRC(SCCGWA, LNCXQ28.LN_BAL);
        CEP.TRC(SCCGWA, LNCXQ28.PAY_PRIN);
        CEP.TRC(SCCGWA, LNCXQ28.INT_MODE);
        CEP.TRC(SCCGWA, LNCXQ28.PAY_INT);
        CEP.TRC(SCCGWA, LNCXQ28.PC_RATE);
        CEP.TRC(SCCGWA, LNCXQ28.PC_AMT);
        CEP.TRC(SCCGWA, LNCXQ28.PAY_AMT);
        CEP.TRC(SCCGWA, LNCXQ28.HRG_AMT);
        CEP.TRC(SCCGWA, LNCXQ28.ADJ_FLG);
        CEP.TRC(SCCGWA, LNCXQ28.STATUS);
        CEP.TRC(SCCGWA, LNCXQ28.REMARK);
        SCCFMT.DATA_PTR = LNCXQ28;
        SCCFMT.DATA_LEN = 571;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R020_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LNZ01";
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 14144;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPAIPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PAIP-MAINT", LNCPAIPM);
        if (LNCPAIPM.RC.RC_RTNCODE != 0) {
            LNCSPPRM.RC.RC_MMO = LNCPAIPM.RC.RC_APP;
            LNCSPPRM.RC.RC_CODE = LNCPAIPM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        LNTAPRD_RD.eqWhere = "CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
    }
    public void T000_READ_LNTEXTN() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.where = "CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO "
            + "AND VAL_DT >= :LNREXTN.KEY.VAL_DT";
        IBS.READ(SCCGWA, LNREXTN, this, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.EXTN_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.EXTN_FLAG = 'N';
        } else {
        }
    }
    public void T000_READ_CONT_PROC() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_CONT_NOTFND, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_AGRE_NOTFND, LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_STARTBR_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.where = "FATHER_CONTRACT = :LNRCONT.FATHER_CONTRACT";
        IBS.STARTBR(SCCGWA, LNRCONT, this, LNTCONT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B200_READNEXT_LNTCONT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRCONT, this, LNTCONT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.CONT_FLAG = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B200_ENDBR_LNTCONT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTCONT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.ICTL_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.ICTL_FLAG = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTICTL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSPPRM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCSPPRM=");
            CEP.TRC(SCCGWA, LNCSPPRM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

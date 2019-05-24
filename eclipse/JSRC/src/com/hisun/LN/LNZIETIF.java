package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZIETIF {
    DBParm LNTAGRE_RD;
    DBParm LNTICTL_RD;
    DBParm LNTCONT_RD;
    DBParm LNTPLPI_RD;
    DBParm LNTBALZ_RD;
    brParm LNTSETL_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    LNZIETIF_WS_OUT_RECODE WS_OUT_RECODE = new LNZIETIF_WS_OUT_RECODE();
    LNZIETIF_WS_PAGE_INFO WS_PAGE_INFO = new LNZIETIF_WS_PAGE_INFO();
    LNZIETIF_WS_VARIABLES WS_VARIABLES = new LNZIETIF_WS_VARIABLES();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRPLPI LNRPLPI = new LNRPLPI();
    CICQACR CICQACR = new CICQACR();
    CICCUST CICCUST = new CICCUST();
    LNRCONT LNRCONT = new LNRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    LNRAGRE LNRAGRE = new LNRAGRE();
    CICQCIAC CICQCIAC = new CICQCIAC();
    LNRSETL LNRSETL = new LNRSETL();
    LNRBALZ LNRBALZ = new LNRBALZ();
    SCCGWA SCCGWA;
    LNCIETIF LNCIETIF;
    public void MP(SCCGWA SCCGWA, LNCIETIF LNCIETIF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIETIF = LNCIETIF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZIETIF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_OUT_RECODE);
        IBS.init(SCCGWA, WS_PAGE_INFO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LNN01";
        LNCIETIF.RC.RC_MMO = "LN";
        LNCIETIF.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_GET_PAGE_INFO();
        if (pgmRtn) return;
        B300_FUNC_QUERY_CI_NO();
        if (pgmRtn) return;
        B800_OUTPUT_DATA_BEGIN();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCIETIF.CI_NO);
        if (LNCIETIF.CI_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_PAGE_INFO() throws IOException,SQLException,Exception {
        WS_PAGE_INFO.ROW_FLG = 'N';
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        WS_PAGE_INFO.CURR_PAGE = 1;
        WS_PAGE_INFO.LAST_PAGE = 'N';
        WS_PAGE_INFO.PAGE_ROW = 10;
        WS_PAGE_INFO.NEXT_START_NUM = ( ( WS_PAGE_INFO.CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW ) + 1;
    }
    public void B300_FUNC_QUERY_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCIAC);
        CICQCIAC.FUNC = '1';
        CICQCIAC.DATA.CI_NO = LNCIETIF.CI_NO;
        CICQCIAC.DATA.FRM_APP = "DD";
        S000_CALL_CIZQCIAC();
        if (pgmRtn) return;
        for (WS_VARIABLES.CNT = 1; CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_VARIABLES.CNT-1].ENTY_NO.trim().length() != 0 
            && WS_VARIABLES.CNT <= 99; WS_VARIABLES.CNT += 1) {
            CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_VARIABLES.CNT-1].ENTY_NO);
            IBS.init(SCCGWA, LNRSETL);
            LNRSETL.AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_VARIABLES.CNT-1].ENTY_NO;
            LNRSETL.KEY.SETTLE_TYPE = '4';
            T000_STARTBR_SETL();
            if (pgmRtn) return;
            WS_VARIABLES.SETL_FLAG = 'Y';
            T000_READNEXT_SETL();
            if (pgmRtn) return;
            while (WS_VARIABLES.SETL_FLAG != 'N' 
                && WS_VARIABLES.CNT <= WS_PAGE_INFO.PAGE_ROW) {
                CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
                T000_01_GET_AGRE_NO();
                if (pgmRtn) return;
                T000_02_GET_ICTL_RATE();
                if (pgmRtn) return;
                T000_03_GET_CONT_DT();
                if (pgmRtn) return;
                T000_04_GET_PLPI_INFO();
                if (pgmRtn) return;
                T000_05_GET_BAL();
                if (pgmRtn) return;
                R000_OUTPUT_DATA();
                if (pgmRtn) return;
                T000_READNEXT_SETL();
                if (pgmRtn) return;
            }
            T000_ENDBR_SETL();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC, true);
        if (CICQCIAC.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQCIAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_01_GET_AGRE_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_DRAW_NO = LNRAGRE.DRAW_NO;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PA_NO = LNRAGRE.PAPER_NO;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_DRAW_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_PA_NO);
        T000_PNO_CIZCUST();
        if (pgmRtn) return;
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTAGRE_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTAGRE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTAGRE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_PNO_CIZCUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = LNRAGRE.PAPER_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        WS_VARIABLES.WS_D_DETAIL_DD.D_CI_NM = CICCUST.O_DATA.O_CI_NM;
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST, true);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_02_GET_ICTL_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG);
        if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG == 'F') {
            WS_VARIABLES.WS_D_DETAIL_DD.O_RATE = LNRICTL.CUR_RAT;
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.O_RATE);
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTICTL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_03_GET_CONT_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG);
        if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG == 'F') {
            WS_VARIABLES.START_DATE = LNRCONT.START_DATE;
            WS_VARIABLES.MAT_DATE = LNRCONT.MAT_DATE;
        }
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_04_GET_PLPI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = 'P';
        CEP.TRC(SCCGWA, LNRPLPI.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
        T000_READ_LNTPLPI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
        CEP.TRC(SCCGWA, LNRPLPI.PRIN_AMT);
        WS_VARIABLES.WS_D_DETAIL_DD.D_DUE_DT = LNRPLPI.DUE_DT;
        WS_VARIABLES.WS_D_DETAIL_DD.D_PRIN_AMT = LNRPLPI.PRIN_AMT;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNRPLPI.KEY.REPY_MTH = 'C';
        } else {
            LNRPLPI.KEY.REPY_MTH = 'I';
        }
        T000_READ_LNTPLPI();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.I_DUE_DT = LNRPLPI.DUE_DT;
        LNRPLPI.DUE_REPY_AMT -= LNRPLPI.PRIN_AMT;
        WS_VARIABLES.WS_D_DETAIL_DD.D_I_AMT = LNRPLPI.DUE_REPY_AMT;
        CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_I_AMT);
    }
    public void T000_READ_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.where = "CONTRACT_NO = :LNRPLPI.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRPLPI.KEY.REPY_MTH";
        LNTPLPI_RD.fst = true;
        LNTPLPI_RD.order = "VAL_DT";
        IBS.READ(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTPLPI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_05_GET_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        T000_READ_LNZBALZ();
        if (pgmRtn) return;
        WS_VARIABLES.WS_D_DETAIL_DD.D_BAL = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05 + LNRBALZ.LOAN_BALL06;
    }
    public void T000_READ_LNZBALZ() throws IOException,SQLException,Exception {
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_DRAW_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_PA_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.O_RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.START_DATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.MAT_DATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_DUE_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_PRIN_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.I_DUE_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.D_I_AMT);
        WS_PAGE_INFO.TOTAL_NUM += 1;
        if (WS_PAGE_INFO.PAGE_IDX < WS_PAGE_INFO.PAGE_ROW 
            && WS_PAGE_INFO.TOTAL_NUM >= WS_PAGE_INFO.NEXT_START_NUM) {
            WS_PAGE_INFO.ROW_FLG = 'Y';
            WS_PAGE_INFO.PAGE_IDX += 1;
            WS_PAGE_INFO.NEXT_START_NUM += 1;
            R000_WRITE_QUEUE_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void R000_WRITE_QUEUE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1]);
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].ENTY_NO_OUT = LNRSETL.KEY.CONTRACT_NO;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_DRAW_NO_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_DRAW_NO;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_PA_NO_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_PA_NO;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_BAL_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_BAL;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].O_RATE_OUT = WS_VARIABLES.WS_D_DETAIL_DD.O_RATE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_CI_NM_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_CI_NM;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].START_DATE_OUT = WS_VARIABLES.START_DATE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].MAT_DATE_OUT = WS_VARIABLES.MAT_DATE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_DUE_DT_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_DUE_DT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_PRIN_AMT_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_PRIN_AMT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].I_DUE_DT_OUT = WS_VARIABLES.WS_D_DETAIL_DD.I_DUE_DT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_I_AMT_OUT = WS_VARIABLES.WS_D_DETAIL_DD.D_I_AMT;
    }
    public void B800_OUTPUT_DATA_BEGIN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.BAL_CNT);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.ROW_FLG);
        if (WS_PAGE_INFO.ROW_FLG == 'Y') {
            WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.TOTAL_NUM % WS_PAGE_INFO.PAGE_ROW;
            WS_PAGE_INFO.TOTAL_PAGE = (short) ((WS_PAGE_INFO.TOTAL_NUM - WS_PAGE_INFO.BAL_CNT) / WS_PAGE_INFO.PAGE_ROW);
            if (WS_PAGE_INFO.BAL_CNT != 0) {
                WS_PAGE_INFO.TOTAL_PAGE += 1;
            }
            if (WS_PAGE_INFO.TOTAL_PAGE <= WS_PAGE_INFO.CURR_PAGE) {
                WS_PAGE_INFO.TOTAL_PAGE = WS_PAGE_INFO.CURR_PAGE;
                WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.PAGE_IDX;
                WS_PAGE_INFO.TOTAL_NUM = ( WS_PAGE_INFO.CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW + WS_PAGE_INFO.BAL_CNT;
                WS_PAGE_INFO.LAST_PAGE = 'Y';
                if (WS_PAGE_INFO.BAL_CNT != 0) {
                    WS_PAGE_INFO.PAGE_ROW = (short) WS_PAGE_INFO.BAL_CNT;
                }
            }
        } else {
            WS_PAGE_INFO.TOTAL_PAGE = 1;
            WS_PAGE_INFO.TOTAL_NUM = 0;
            WS_PAGE_INFO.LAST_PAGE = 'Y';
            WS_PAGE_INFO.PAGE_ROW = 0;
        }
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        if (WS_PAGE_INFO.TOTAL_NUM == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_PAGE_INFO.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_PAGE_INFO.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_PAGE_INFO.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_PAGE_INFO.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_PAGE_INFO.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        SCCFMT.FMTID = "LN813";
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 4475;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_SETL() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.where = "AC = :LNRSETL.AC "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE";
        IBS.STARTBR(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_READNEXT_SETL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSETL, this, LNTSETL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.SETL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.SETL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTSETL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_SETL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSETL_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCIETIF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCIETIF=");
            CEP.TRC(SCCGWA, LNCIETIF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

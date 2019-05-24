package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class IBZSQRI {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm IBTINRH_BR = new brParm();
    brParm IBTINTH_BR = new brParm();
    brParm IBTINSH_BR = new brParm();
    DBParm IBTMST_RD;
    boolean pgmRtn = false;
    short K_360_DAYS = 360;
    short K_365_DAYS = 365;
    short K_366_DAYS = 366;
    short K_30_DAYS = 30;
    short K_30E_DAYS = 31;
    short K_Q_MAX_CNT = 5000;
    String K_IBTMST = "IBTMST ";
    String K_IBTINRH = "IBTINRH ";
    String K_IBTINTH = "IBTINTH ";
    String K_IBTINSH = "IBTINSH ";
    String K_OUTPUT_FMT = "IBC10";
    short WS_I = 0;
    double WS_TOT_INT = 0;
    double WS_BUD_INT1 = 0;
    double WS_ROUND_INT = 0;
    IBZSQRI_WS_OUTPUT_RATE WS_OUTPUT_RATE = new IBZSQRI_WS_OUTPUT_RATE();
    IBZSQRI_WS_OUTPUT_INT WS_OUTPUT_INT = new IBZSQRI_WS_OUTPUT_INT();
    IBZSQRI_WS_OUTPUT_SETTL WS_OUTPUT_SETTL = new IBZSQRI_WS_OUTPUT_SETTL();
    int WS_SQL_STR_DTE = 0;
    int WS_SQL_END_DTE = 0;
    double WS_SQL_TOT_INT = 0;
    char WS_IBTINRH_FLG = ' ';
    char WS_IBTINTH_FLG = ' ';
    char WS_IBTINSH_FLG = ' ';
    char WS_TXNBR_FLAG = ' ';
    char WS_TABLE_REC = ' ';
    char WS_MST_REC_FLG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    IBRINRH IBRINRH = new IBRINRH();
    IBRINTH IBRINTH = new IBRINTH();
    IBCOQINT IBCOQINT = new IBCOQINT();
    IBCQINF IBCQINF = new IBCQINF();
    BPCPRMR BPCPRMR = new BPCPRMR();
    IBCPMORG IBCPMORG = new IBCPMORG();
    IBRINSH IBRINSH = new IBRINSH();
    IBRMST IBRMST = new IBRMST();
    AICPQMIB AICPQMIB = new AICPQMIB();
    IBCRODE IBCRODE = new IBCRODE();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    IBCSQRI IBCSQRI;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, IBCSQRI IBCSQRI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCSQRI = IBCSQRI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZSQRI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CEP.TRC(SCCGWA, IBCSQRI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCSQRI.FUNC);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (IBCSQRI.FUNC == 'R') {
            B020_INQ_AC_RATE();
            if (pgmRtn) return;
        } else if (IBCSQRI.FUNC == 'I') {
            B030_INQ_AC_INT();
            if (pgmRtn) return;
        } else if (IBCSQRI.FUNC == 'S') {
            B040_INQ_AC_SETTL();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID SQRI-FUNC(" + IBCSQRI.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (IBCSQRI.FUNC != 'R' 
            && IBCSQRI.FUNC != 'I' 
            && IBCSQRI.FUNC != 'S') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID SQRI-FUNC(" + IBCSQRI.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if ((IBCSQRI.NOS_CDE.trim().length() == 0 
            || IBCSQRI.CCY.trim().length() == 0) 
            && IBCSQRI.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
        CEP.TRC(SCCGWA, IBCSQRI.BR);
        if (IBCSQRI.BR == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.BR_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, IBCSQRI.STR_DTE);
        CEP.TRC(SCCGWA, IBCSQRI.END_DTE);
        if (IBCSQRI.END_DTE == 0) {
            IBCSQRI.END_DTE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if ((IBCSQRI.STR_DTE > SCCGWA.COMM_AREA.AC_DATE 
            || IBCSQRI.END_DTE > SCCGWA.COMM_AREA.AC_DATE) 
            && IBCSQRI.FUNC != 'R') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IPT_DATE_GE_ACDATE);
        }
        if (IBCSQRI.STR_DTE > IBCSQRI.END_DTE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SDATE_GT_EDATE);
        }
        if (IBCSQRI.FUNC == 'I') {
            if (IBCSQRI.STR_DTE == 0) {
                if (IBRMST.L_INTS_DT != 0) {
                    IBCSQRI.STR_DTE = IBRMST.L_INTS_DT;
                } else {
                    IBCSQRI.STR_DTE = IBRMST.EFF_DATE;
                }
            } else {
                if ((IBRMST.L_INTS_DT != 0 
                    && IBCSQRI.STR_DTE != IBRMST.L_INTS_DT) 
                    && (IBRMST.L_INTS_DT == 0 
                    && IBCSQRI.STR_DTE != IBRMST.EFF_DATE)) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INV_START_DT);
                }
            }
        } else {
            if (IBCSQRI.STR_DTE == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SDATE_MUST_INPUT);
            }
        }
        B010_01_CHECK_AC_EXIST();
        if (pgmRtn) return;
        B010_02_CHECK_BR();
        if (pgmRtn) return;
    }
    public void B010_01_CHECK_AC_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        if (IBCSQRI.AC_NO.trim().length() > 0) {
            IBCQINF.INPUT_DATA.AC_NO = IBCSQRI.AC_NO;
        } else {
            IBCQINF.INPUT_DATA.NOSTRO_CD = IBCSQRI.NOS_CDE;
            IBCQINF.INPUT_DATA.CCY = IBCSQRI.CCY;
        }
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
    }
    public void B010_02_CHECK_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
            if (IBCQINF.OUTPUT_DATA.POST_ACT_CTR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.VIL_TXN_BR);
            }
        } else {
            if (IBCQINF.OUTPUT_DATA.SWR_BR == '0') {
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
                }
            } else if (IBCQINF.OUTPUT_DATA.SWR_BR == '1') {
                if (BPCPORUP.DATA_INFO.TYP == null) BPCPORUP.DATA_INFO.TYP = "";
                JIBS_tmp_int = BPCPORUP.DATA_INFO.TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) BPCPORUP.DATA_INFO.TYP += " ";
                if (BPCPQORG.TYP == null) BPCPQORG.TYP = "";
                JIBS_tmp_int = BPCPQORG.TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) BPCPQORG.TYP += " ";
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR 
                    && (!BPCPORUP.DATA_INFO.TYP.substring(0, 1).equalsIgnoreCase("0") 
                    || BPCPQORG.TYP.substring(0, 1).equalsIgnoreCase("0"))) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
                }
            } else if (IBCQINF.OUTPUT_DATA.SWR_BR == '2') {
                if (BPCPQORG.TYP == null) BPCPQORG.TYP = "";
                JIBS_tmp_int = BPCPQORG.TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) BPCPQORG.TYP += " ";
                if (BPCPORUP.DATA_INFO.TYP == null) BPCPORUP.DATA_INFO.TYP = "";
                JIBS_tmp_int = BPCPORUP.DATA_INFO.TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) BPCPORUP.DATA_INFO.TYP += " ";
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR 
                    && (!BPCPQORG.TYP.substring(0, 1).equalsIgnoreCase("0") 
                    || BPCPORUP.DATA_INFO.TYP.substring(0, 1).equalsIgnoreCase("0"))) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
                }
            } else {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        }
    }
    public void B020_INQ_AC_RATE() throws IOException,SQLException,Exception {
        R000_WRITE_MPAG_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBRINRH);
        IBRINRH.KEY.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        WS_SQL_STR_DTE = IBCSQRI.STR_DTE;
        WS_SQL_END_DTE = IBCSQRI.END_DTE;
        T000_STARTBR_IBTINRH();
        if (pgmRtn) return;
        T000_READNEXT_IBTINRH();
        if (pgmRtn) return;
        if (WS_IBTINRH_FLG == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NO_REC);
        }
        while (WS_IBTINRH_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, WS_OUTPUT_RATE);
            WS_OUTPUT_RATE.WS_L1_DATE = IBRINRH.KEY.VALUE_DATE;
            WS_OUTPUT_RATE.WS_L1_RATE_MTH = IBRINRH.RATE_MTH;
            WS_OUTPUT_RATE.WS_L1_RATE_TYPE = IBRINRH.RATE_TYPE;
            WS_OUTPUT_RATE.WS_L1_RATE_TERM = IBRINRH.RATE_TERM;
            WS_OUTPUT_RATE.WS_L1_RATE_PCT = IBRINRH.RATE_PCT;
            WS_OUTPUT_RATE.WS_L1_RATE_SPREAD = IBRINRH.RATE_SPREAD;
            if (IBRINRH.RATE_MTH == '1') {
                WS_OUTPUT_RATE.WS_L1_INT_RATE = IBRINRH.INT_RATE;
                WS_OUTPUT_RATE.WS_L1_CRY_RATE = IBRINRH.INT_RATE;
            } else {
                WS_OUTPUT_RATE.WS_L1_CRY_RATE = IBRINRH.INT_RATE;
            }
            CEP.TRC(SCCGWA, IBRINRH.INT_RATE);
            if (IBRINRH.CALR_STD == K_360_DAYS) {
                WS_OUTPUT_RATE.WS_L1_CALR_STD = "01";
            } else if (IBRINRH.CALR_STD == K_365_DAYS) {
                WS_OUTPUT_RATE.WS_L1_CALR_STD = "02";
            } else if (IBRINRH.CALR_STD == K_366_DAYS) {
                WS_OUTPUT_RATE.WS_L1_CALR_STD = "03";
            }
            WS_OUTPUT_RATE.WS_L1_OD_RATE = IBRINRH.OD_RATE;
            WS_OUTPUT_RATE.WS_L1_UPD_DATE = IBRINRH.UPD_DATE;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_RATE);
            SCCMPAG.DATA_LEN = 80;
            SCCMPAG.FUNC = 'D';
            B_MPAG();
            if (pgmRtn) return;
            T000_READNEXT_IBTINRH();
            if (pgmRtn) return;
        }
        T000_ENDBR_IBTINRH();
        if (pgmRtn) return;
    }
    public void B030_INQ_AC_INT() throws IOException,SQLException,Exception {
        R000_WRITE_MPAG_TITLE();
        if (pgmRtn) return;
        WS_TOT_INT = 0;
        IBS.init(SCCGWA, IBRINTH);
        IBRINTH.KEY.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        WS_SQL_STR_DTE = IBCSQRI.STR_DTE;
        WS_SQL_END_DTE = IBCSQRI.END_DTE;
        T000_STARTBR_IBTINTH();
        if (pgmRtn) return;
        T000_READNEXT_IBTINTH();
        if (pgmRtn) return;
        if (WS_IBTINTH_FLG == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NO_REC);
        }
        while (WS_IBTINTH_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, WS_OUTPUT_INT);
            WS_OUTPUT_INT.WS_L2_DATE = IBRINTH.KEY.INT_DATE;
            WS_OUTPUT_INT.WS_L2_VAL_BAL = IBRINTH.END_BAL;
            if (IBRINTH.END_BAL >= 0) {
                WS_ROUND_INT = IBRINTH.INT;
                B031_ROUND_PROC();
                if (pgmRtn) return;
                WS_OUTPUT_INT.WS_L2_INT = IBCRODE.INT_AMT;
                WS_OUTPUT_INT.WS_L2_RATE = IBRINTH.INT_RATE;
            } else {
                WS_ROUND_INT = IBRINTH.OD_BUD_INT;
                B031_ROUND_PROC();
                if (pgmRtn) return;
                WS_OUTPUT_INT.WS_L2_OD_INT = IBCRODE.INT_AMT;
                WS_OUTPUT_INT.WS_L2_OD_RATE = IBRINTH.INT_RATE;
            }
            WS_ROUND_INT = IBRINTH.BUD_INT;
            B031_ROUND_PROC();
            if (pgmRtn) return;
            WS_TOT_INT = IBCRODE.INT_AMT;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_INT);
            SCCMPAG.DATA_LEN = 85;
            SCCMPAG.FUNC = 'D';
            B_MPAG();
            if (pgmRtn) return;
            T000_READNEXT_IBTINTH();
            if (pgmRtn) return;
        }
        T000_ENDBR_IBTINTH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBCOQINT);
        IBCOQINT.INFO.TOT_INT = WS_TOT_INT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOQINT;
        SCCFMT.DATA_LEN = 36;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B031_ROUND_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCRODE);
        IBCRODE.INT_AMT = WS_ROUND_INT;
        IBCRODE.CCY = IBCQINF.INPUT_DATA.CCY;
        S000_CALL_IBZRODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCRODE.INT_AMT);
    }
    public void B040_INQ_AC_SETTL() throws IOException,SQLException,Exception {
        R000_WRITE_MPAG_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBRINSH);
        IBRINSH.KEY.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        WS_SQL_STR_DTE = IBCSQRI.STR_DTE;
        WS_SQL_END_DTE = IBCSQRI.END_DTE;
        T000_STARTBR_IBTINSH();
        if (pgmRtn) return;
        T000_READNEXT_IBTINSH();
        if (pgmRtn) return;
        if (WS_IBTINSH_FLG == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NO_REC);
        }
        while (WS_IBTINSH_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, WS_OUTPUT_SETTL);
            WS_OUTPUT_SETTL.WS_L3_AC_NO = IBRINSH.KEY.AC_NO;
            WS_OUTPUT_SETTL.WS_L3_DEAL_DATE = IBRINSH.KEY.DEAL_DATE;
            WS_OUTPUT_SETTL.WS_L3_JRN_NO = IBRINSH.JRN_NO;
            WS_OUTPUT_SETTL.WS_L3_SEQ = IBRINSH.KEY.SEQ;
            WS_OUTPUT_SETTL.WS_L3_INTS_DATE = IBRINSH.INTS_DATE;
            WS_ROUND_INT = IBRINSH.ESET_AMT;
            B031_ROUND_PROC();
            if (pgmRtn) return;
            WS_OUTPUT_SETTL.WS_L3_ESET_AMT = IBCRODE.INT_AMT;
            WS_OUTPUT_SETTL.WS_L3_ASET_AMT = IBRINSH.ASET_AMT;
            WS_OUTPUT_SETTL.WS_L3_REV_FLG = IBRINSH.REV_FLG;
            WS_OUTPUT_SETTL.WS_L3_AUTH_TLR = IBRINSH.AUTH_TLR;
            WS_OUTPUT_SETTL.WS_L3_TL_ID = IBRINSH.CRT_TLR;
            WS_OUTPUT_SETTL.WS_L3_SETT_AC_NO = IBRINSH.SETT_AC_NO;
            WS_OUTPUT_SETTL.WS_L3_SETT_TYPE = IBRINSH.SETT_TYPE;
            B040_01_GET_NAME();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_SETTL);
            SCCMPAG.DATA_LEN = 403;
            SCCMPAG.FUNC = 'D';
            B_MPAG();
            if (pgmRtn) return;
            T000_READNEXT_IBTINSH();
            if (pgmRtn) return;
        }
        T000_ENDBR_IBTINSH();
        if (pgmRtn) return;
    }
    public void B040_01_GET_NAME() throws IOException,SQLException,Exception {
        if (IBRINSH.SETT_AC_TYPE == 'I') {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = IBRINSH.SETT_AC_NO;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            WS_OUTPUT_SETTL.WS_L3_SETT_AC_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
        }
        if (IBRINSH.SETT_AC_TYPE == 'N') {
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.AC_NO = IBRINSH.SETT_AC_NO;
            S000_CALL_IBZQINF();
            if (pgmRtn) return;
            WS_OUTPUT_SETTL.WS_L3_SETT_AC_NAME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        }
    }
    public void R000_WRITE_MPAG_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        if (IBCSQRI.FUNC == 'R') {
            SCCMPAG.MAX_COL_NO = 80;
        } else if (IBCSQRI.FUNC == 'I') {
            SCCMPAG.MAX_COL_NO = 85;
        } else if (IBCSQRI.FUNC == 'S') {
            SCCMPAG.MAX_COL_NO = 403;
        } else {
        }
        SCCMPAG.SCR_ROW_CNT = 20;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_LINK_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZRODE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-ROUND-INT", IBCRODE);
        if (IBCRODE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCRODE.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_STARTBR_IBTINRH() throws IOException,SQLException,Exception {
        IBTINRH_BR.rp = new DBParm();
        IBTINRH_BR.rp.TableName = "IBTINRH";
        IBTINRH_BR.rp.where = "AC_NO = :IBRINRH.KEY.AC_NO "
            + "AND ( VALUE_DATE >= :WS_SQL_STR_DTE "
            + "AND VALUE_DATE <= :WS_SQL_END_DTE )";
        IBTINRH_BR.rp.order = "VALUE_DATE";
        IBS.STARTBR(SCCGWA, IBRINRH, this, IBTINRH_BR);
    }
    public void T000_READNEXT_IBTINRH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRINRH, this, IBTINRH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IBTINRH_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IBTINRH_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_IBTINRH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, IBTINRH_BR);
    }
    public void T000_STARTBR_IBTINTH() throws IOException,SQLException,Exception {
        IBTINTH_BR.rp = new DBParm();
        IBTINTH_BR.rp.TableName = "IBTINTH";
        IBTINTH_BR.rp.where = "AC_NO = :IBRINTH.KEY.AC_NO "
            + "AND INT_DATE >= :WS_SQL_STR_DTE "
            + "AND INT_DATE <= :WS_SQL_END_DTE";
        IBTINTH_BR.rp.order = "INT_DATE";
        IBS.STARTBR(SCCGWA, IBRINTH, this, IBTINTH_BR);
    }
    public void T000_READNEXT_IBTINTH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRINTH, this, IBTINTH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IBTINTH_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IBTINTH_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_IBTINTH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, IBTINTH_BR);
    }
    public void T000_STARTBR_IBTINSH() throws IOException,SQLException,Exception {
        IBTINSH_BR.rp = new DBParm();
        IBTINSH_BR.rp.TableName = "IBTINSH";
        IBTINSH_BR.rp.where = "DEAL_DATE >= :WS_SQL_STR_DTE "
            + "AND DEAL_DATE <= :WS_SQL_END_DTE "
            + "AND AC_NO = :IBRINSH.KEY.AC_NO";
        IBTINSH_BR.rp.order = "DEAL_DATE";
        IBS.STARTBR(SCCGWA, IBRINSH, this, IBTINSH_BR);
    }
    public void T000_READNEXT_IBTINSH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRINSH, this, IBTINSH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IBTINSH_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IBTINSH_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_IBTINSH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, IBTINSH_BR);
    }
    public void T000_REAT_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MST_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MST_REC_FLG = 'N';
        } else {
        }
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

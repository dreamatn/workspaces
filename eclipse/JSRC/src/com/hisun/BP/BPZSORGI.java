package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import com.hisun.LN.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSORGI {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm BPTORGL_RD;
    DBParm BPTPREAC_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPX01";
    String K_APTP1_DD = "DD0069";
    String K_APTP2_LN = "LN0069";
    String K_APTP3_BP = "BP0069";
    BPZSORGI_WS_PARM_DATA1 WS_PARM_DATA1 = new BPZSORGI_WS_PARM_DATA1();
    BPZSORGI_WS_PARM_DATA2 WS_PARM_DATA2 = new BPZSORGI_WS_PARM_DATA2();
    BPZSORGI_WS_PARM_DATA3 WS_PARM_DATA3 = new BPZSORGI_WS_PARM_DATA3();
    BPZSORGI_WS_PARM_DATA4 WS_PARM_DATA4 = new BPZSORGI_WS_PARM_DATA4();
    BPZSORGI_WS_PARM_DATA5 WS_PARM_DATA5 = new BPZSORGI_WS_PARM_DATA5();
    int WS_BRAN_BR_NEW = 0;
    int WS_BRAN_BR_OLD = 0;
    int WS_I = 0;
    String WS_VIL_TYP_NEW = " ";
    String WS_VIL_TYP_OLD = " ";
    String WS_AREA_CD_NEW = " ";
    String WS_AREA_CD_OLD = " ";
    char WS_ATTR_OLD = ' ';
    char WS_ATTR_NEW = ' ';
    int WS_SUPR_BR_OLD = 0;
    String WS_FX_BUSI = " ";
    String WS_ACO_AC = " ";
    String WS_ACO_AC_T = " ";
    String WS_CCY = " ";
    String WS_CCY_AC = " ";
    int WS_OPN_BR = 0;
    int WS_TR_BRANCH = 0;
    char WS_ACAC_STS = ' ';
    char WS_PQORG_ATTR_NEW = ' ';
    short WS_CNT = 0;
    char WS_INT_BR_FLG_OLD = ' ';
    char WS_INT_BR_FLG_NEW = ' ';
    char WS_ORGL_FLG = ' ';
    char WS_PREAC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRORGI BPRORGI = new BPRORGI();
    BPRORGL BPRORGL = new BPRORGL();
    CICQACRI CICQACRI = new CICQACRI();
    BPRPREAC BPRPREAC = new BPRPREAC();
    SCCFMT SCCFMT = new SCCFMT();
    BPCRORGI BPCRORGI = new BPCRORGI();
    BPCOORGI BPCOORGI = new BPCOORGI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    DDCUQCAC DDCUQCAC = new DDCUQCAC();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    SCCBSP SCCBSP = new SCCBSP();
    SCCBSPS SCCBSPS = new SCCBSPS();
    CICACCU CICACCU = new CICACCU();
    BPCRORGL BPCRORGL = new BPCRORGL();
    CICQACAC CICQACAC = new CICQACAC();
    DCCUBRIQ DCCUBRIQ = new DCCUBRIQ();
    IBCQINF IBCQINF = new IBCQINF();
    DDCIMMST DDCIMMST = new DDCIMMST();
    BPCRORLB BPCRORLB = new BPCRORLB();
    LNCSIQIF LNCSIQIF = new LNCSIQIF();
    CICQCIAC CICQCIAC = new CICQCIAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSORGI BPCSORGI;
    public void MP(SCCGWA SCCGWA, BPCSORGI BPCSORGI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSORGI = BPCSORGI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSORGI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSORGI.DATA.FUNC == 'P' 
            || BPCSORGI.DATA.FUNC == 'O') {
            B001_CHECK_LIST_FIRST();
            if (pgmRtn) return;
        }
        if (BPCSORGI.DATA.FUNC == 'A') {
            B044_CHECK_LIST1();
            if (pgmRtn) return;
        }
        B010_WRITE_BPTORGI();
        if (pgmRtn) return;
        B010_WRITE_BPTORGL();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B001_CHECK_LIST_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = BPCSORGI.DATA.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        B044_CHECK_LIST1();
        if (pgmRtn) return;
        B044_CHECK_LIST2();
        if (pgmRtn) return;
    }
    public void B044_CHECK_LIST1() throws IOException,SQLException,Exception {
        if (BPCSORGI.DATA.OLD_BR == BPCSORGI.DATA.NEW_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OLD_NEW_BR_SAME);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCSORGI.DATA.NEW_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCSORGI.DATA.OLD_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = BPCSORGI.DATA.NEW_BR;
        CEP.TRC(SCCGWA, BPCSORGI.DATA.NEW_BR);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_BRAN_BR_NEW = BPCPQORG.BRANCH_BR;
        WS_VIL_TYP_NEW = BPCPQORG.VIL_TYP;
        WS_FX_BUSI = BPCPQORG.FX_BUSI;
        WS_PQORG_ATTR_NEW = BPCPQORG.ATTR;
        WS_INT_BR_FLG_NEW = BPCPQORG.INT_BR_FLG;
        if (BPCPQORG.ATTR == '0' 
            || BPCPQORG.ATTR == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NEW_BR_ATTR_ACCOUNT, BPCSORGI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSORGI.DATA.INCO_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPCSORGI.DATA.INCO_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INCO_DT_ERROR);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = BPCSORGI.DATA.OLD_BR;
        CEP.TRC(SCCGWA, BPCSORGI.DATA.OLD_BR);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_BRAN_BR_OLD = BPCPQORG.BRANCH_BR;
        WS_VIL_TYP_OLD = BPCPQORG.VIL_TYP;
        WS_ATTR_OLD = BPCPQORG.ATTR;
        WS_SUPR_BR_OLD = BPCPQORG.SUPR_BR;
        WS_INT_BR_FLG_OLD = BPCPQORG.INT_BR_FLG;
        CEP.TRC(SCCGWA, WS_VIL_TYP_NEW);
        CEP.TRC(SCCGWA, WS_VIL_TYP_OLD);
        if (!WS_VIL_TYP_NEW.equalsIgnoreCase(WS_VIL_TYP_OLD)) {
        }
        if (WS_INT_BR_FLG_OLD != WS_INT_BR_FLG_NEW) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INT_SAME);
        }
        if (BPCSORGI.DATA.TR_BRANCH != 0) {
            WS_TR_BRANCH = BPCSORGI.DATA.TR_BRANCH;
        } else {
            WS_TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.OLD_BR);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.TR_BRANCH);
        CEP.TRC(SCCGWA, WS_TR_BRANCH);
        if (WS_ATTR_OLD == '3') {
            if (WS_TR_BRANCH == BPCSORGI.DATA.OLD_BR 
                || WS_TR_BRANCH == WS_SUPR_BR_OLD) {
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR6);
            }
        } else {
            if (WS_TR_BRANCH == BPCSORGI.DATA.OLD_BR) {
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR6);
            }
        }
    }
    public void B044_CHECK_LIST2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSORGI.DATA.AC_TYP);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.CCY);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.CCY_TYPE);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.SEQ);
        if (BPCSORGI.DATA.AC_TYP == '2') {
            if ((BPCSORGI.DATA.CCY.trim().length() == 0 
                || BPCSORGI.DATA.CCY_TYPE == ' ') 
                && BPCSORGI.DATA.SEQ == ' ') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
            }
        }
        if (BPCSORGI.DATA.AC_TYP == '1') {
            if (BPCSORGI.DATA.CCY.trim().length() > 0 
                || BPCSORGI.DATA.CCY_TYPE != ' ' 
                || BPCSORGI.DATA.SEQ != 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR15);
            }
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = BPCSORGI.DATA.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CNTRCT_TYP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB") 
            && BPCSORGI.DATA.AC_TYP == '2') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR9);
        }
        CEP.TRC(SCCGWA, WS_PQORG_ATTR_NEW);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB") 
            && WS_PQORG_ATTR_NEW == '3') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR13);
        }
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CNTRCT_TYP);
        if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("IBDD")) {
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.AC_NO = BPCSORGI.DATA.AC;
            S000_CALL_IBZQINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_ATTR);
            if (IBCQINF.OUTPUT_DATA.AC_ATTR == 'L') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR11);
            }
        }
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = BPCSORGI.DATA.AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            if (DDCIMMST.DATA.AC_STS_WORD.substring(61 - 1, 61 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR12);
            }
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("LN")) {
            if (BPCSORGI.DATA.AC_TYP == '1') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR16);
            } else {
                CEP.TRC(SCCGWA, "*** CALL CICQCIAC ***");
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'B';
                CICQACAC.DATA.AGR_NO = BPCSORGI.DATA.AC;
                CICQACAC.DATA.BV_NO = BPCSORGI.DATA.BV_NO;
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                S000_CALL_LNZSIQIF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNCSIQIF.STS);
                if (LNCSIQIF.STS == 'M' 
                    || LNCSIQIF.STS == 'D' 
                    || LNCSIQIF.STS == 'C') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR17);
                }
            }
        }
        CEP.TRC(SCCGWA, BPCSORGI.DATA.AC_TYP);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.OLD_BR);
        if (BPCSORGI.DATA.AC_TYP == '1' 
            && BPCSORGI.DATA.OLD_BR != CICQACRI.O_DATA.O_OPN_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR1);
        }
        IBS.init(SCCGWA, DCCPACTY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("CI3011")) {
            IBS.init(SCCGWA, CICACCU);
            DCCPACTY.INPUT.AC = BPCSORGI.DATA.AC;
            S000_CALL_DCZPACTY();
            if (pgmRtn) return;
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                CICACCU.DATA.AGR_NO = DCCPACTY.OUTPUT.N_CARD_NO;
            } else if (DCCPACTY.OUTPUT.AC_STD_FLG == '0' 
                    && DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, DCCUSPAC);
                DCCUSPAC.FUNC.AC = DCCPACTY.INPUT.AC;
                S000_CALL_DCZUSPAC();
                if (pgmRtn) return;
                CICACCU.DATA.AGR_NO = DCCUSPAC.OUTPUT.STD_AC;
            } else if (DCCPACTY.OUTPUT.AC_STD_FLG == '0' 
                    && !DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("TD")) {
                CICACCU.DATA.AGR_NO = DCCPACTY.OUTPUT.STD_AC;
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_NOT_EXIST, BPCSORGI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
            if (CICACCU.DATA.AGR_NO.trim().length() > 0) {
                S000_CALL_CIZACCU_SECOND();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.STS);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.AC);
        if (CICACCU.DATA.STS == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_CLOSE);
        }
        B030_GET_ACO_NO_Q();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSORGI.DATA.AC_TYP);
        CEP.TRC(SCCGWA, WS_ACO_AC);
        CEP.TRC(SCCGWA, WS_ACO_AC_T);
        CEP.TRC(SCCGWA, WS_CCY_AC);
        CEP.TRC(SCCGWA, WS_OPN_BR);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.OLD_BR);
        CEP.TRC(SCCGWA, WS_FX_BUSI);
        if (BPCSORGI.DATA.AC_TYP == '2') {
            B020_GET_ACO_NO();
            if (pgmRtn) return;
            B030_GET_ACO_NO_Q();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCSORGI.DATA.AC_TYP);
            CEP.TRC(SCCGWA, WS_ACO_AC);
            CEP.TRC(SCCGWA, WS_ACO_AC_T);
            CEP.TRC(SCCGWA, WS_CCY);
            CEP.TRC(SCCGWA, WS_OPN_BR);
            CEP.TRC(SCCGWA, BPCSORGI.DATA.OLD_BR);
            CEP.TRC(SCCGWA, WS_FX_BUSI);
            if (WS_ACAC_STS == '1') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_CLOSE);
            }
            if (WS_OPN_BR != BPCSORGI.DATA.OLD_BR) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR3);
            }
            if (!WS_CCY.equalsIgnoreCase("156") 
                && WS_FX_BUSI.equalsIgnoreCase("00")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR4);
            }
            if (WS_ACO_AC.equalsIgnoreCase(WS_ACO_AC_T)) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR7);
            }
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CNTRCT_TYP);
            if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("IBTD") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("021") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("031") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("035") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("036") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("043") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("044") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("045")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR8);
            }
        }
        IBS.init(SCCGWA, BPRORGL);
        BPRORGL.TX_TOOL = BPCSORGI.DATA.AC;
        BPRORGL.TX_FLG = 'O';
        BPCRORLB.INFO.POINTER = BPRORGL;
        BPCRORLB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRORLB.RC);
        BPCRORLB.FUNC = 'S';
        S000_CALL_BPZRORLB();
        if (pgmRtn) return;
        while (BPCRORLB.RC.RC_CODE == 0) {
            BPCRORLB.RETURN_INFO = ' ';
            IBS.init(SCCGWA, BPCRORLB.RC);
            BPCRORLB.FUNC = 'R';
            S000_CALL_BPZRORLB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRORGL.AC_ORGI_TYP);
            CEP.TRC(SCCGWA, BPRORGL.ACO_AC);
            CEP.TRC(SCCGWA, BPCSORGI.DATA.AC_TYP);
            CEP.TRC(SCCGWA, WS_ACO_AC);
            CEP.TRC(SCCGWA, BPCRORLB.RC.RC_CODE);
            if (BPCRORLB.RC.RC_CODE == 0) {
                if (BPRORGL.AC_ORGI_TYP == BPCSORGI.DATA.AC_TYP 
                    && BPRORGL.AC_ORGI_TYP == '2' 
                    && !BPRORGL.ACO_AC.equalsIgnoreCase(WS_ACO_AC)) {
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR5);
                }
            }
        }
        BPCRORLB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRORLB.RC);
        BPCRORLB.FUNC = 'E';
        S000_CALL_BPZRORLB();
        if (pgmRtn) return;
        BPRPREAC.KEY.AC = BPCSORGI.DATA.AC;
        BPRPREAC.RM_CR_FLG = 'Y';
        T000_READ_BPTPREAC();
        if (pgmRtn) return;
        if (WS_PREAC_FLG == 'Y') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR10);
        }
    }
    public void B010_WRITE_BPTORGI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGI);
        IBS.init(SCCGWA, BPCRORGI);
        BPCRORGI.INFO.FUNC = 'A';
        CEP.TRC(SCCGWA, BPCSORGI.DATA.FUNC);
        if (BPCSORGI.DATA.FUNC == 'O') {
            BPRORGI.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
            if (BPCSORGI.DATA.TX_JRN == 0) {
                BPRORGI.KEY.MN_JRN = SCCGWA.COMM_AREA.JRN_NO;
            } else {
                BPRORGI.KEY.MN_JRN = BPCSORGI.DATA.TX_JRN;
            }
            BPRORGI.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
            BPRORGI.CI_NO = CICACCU.DATA.CI_NO;
            BPRORGI.TX_TOOL = BPCSORGI.DATA.AC;
            BPRORGI.INCO_OLD_BR = BPCSORGI.DATA.OLD_BR;
            BPRORGI.INCO_NEW_BR = BPCSORGI.DATA.NEW_BR;
            BPRORGI.ORGI_TYP = BPCSORGI.DATA.ORG_TYP;
            BPRORGI.ORGI_FLG = '0';
            BPRORGI.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRORGI.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRORGI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRORGI.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRORGI.SUP_TLR = SCCGWA.COMM_AREA.SUP1_ID;
            BPRORGI.CCY = BPCSORGI.DATA.CCY;
            BPRORGI.CCY_TYPE = BPCSORGI.DATA.CCY_TYPE;
            BPRORGI.SEQ = BPCSORGI.DATA.SEQ;
            BPRORGI.ACO_AC = WS_ACO_AC;
            BPRORGI.INCO_DATE = BPCSORGI.DATA.INCO_DT;
            BPRORGI.AC_ORGI_TYP = BPCSORGI.DATA.AC_TYP;
            CEP.TRC(SCCGWA, BPCSORGI.DATA.AC);
            CEP.TRC(SCCGWA, BPCSORGI.DATA.OLD_BR);
            CEP.TRC(SCCGWA, BPCSORGI.DATA.NEW_BR);
            S000_CALL_BPZRORGI();
            if (pgmRtn) return;
        }
        if (BPCSORGI.DATA.FUNC == 'A') {
            BPRORGI.KEY.AC_DT = BPCSORGI.DATA.AC_DT;
            BPRORGI.KEY.MN_JRN = SCCGWA.COMM_AREA.JRN_NO;
            BPRORGI.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
            BPRORGI.INCO_OLD_BR = BPCSORGI.DATA.OLD_BR;
            BPRORGI.INCO_NEW_BR = BPCSORGI.DATA.NEW_BR;
            BPRORGI.ORGI_TYP = '1';
            BPRORGI.ORGI_FLG = '0';
            BPRORGI.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRORGI.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRORGI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRORGI.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRORGI.SUP_TLR = SCCGWA.COMM_AREA.SUP1_ID;
            CEP.TRC(SCCGWA, BPCSORGI.DATA.AC);
            CEP.TRC(SCCGWA, BPCSORGI.DATA.OLD_BR);
            CEP.TRC(SCCGWA, BPCSORGI.DATA.NEW_BR);
            S000_CALL_BPZRORGI();
            if (pgmRtn) return;
        }
        if (BPCSORGI.DATA.FUNC == 'P') {
            IBS.init(SCCGWA, BPCRORGI);
            BPCRORGI.INFO.FUNC = 'Q';
            BPRORGI.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRORGI.KEY.MN_JRN = BPCSORGI.DATA.TX_JRN;
            S000_CALL_BPZRORGI();
            if (pgmRtn) return;
            if (BPCRORGI.RETURN_INFO == 'F') {
            } else {
                IBS.init(SCCGWA, BPCRORGI);
                BPCRORGI.INFO.FUNC = 'A';
                BPRORGI.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRORGI.KEY.MN_JRN = BPCSORGI.DATA.TX_JRN;
                BPRORGI.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
                BPRORGI.CI_NO = CICACCU.DATA.CI_NO;
                BPRORGI.INCO_OLD_BR = BPCSORGI.DATA.OLD_BR;
                BPRORGI.ORGI_TYP = BPCSORGI.DATA.ORG_TYP;
                BPRORGI.ORGI_FLG = '0';
                BPRORGI.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRORGI.UPT_TLR = BPCSORGI.DATA.TR_TLR;
                BPRORGI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRORGI.CRT_TLR = BPCSORGI.DATA.TR_TLR;
                BPRORGI.SUP_TLR = SCCGWA.COMM_AREA.SUP1_ID;
                BPRORGI.INCO_DATE = BPCSORGI.DATA.INCO_DT;
                BPRORGI.AC_ORGI_TYP = BPCSORGI.DATA.AC_TYP;
                S000_CALL_BPZRORGI();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_WRITE_BPTORGL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGL);
        IBS.init(SCCGWA, BPCRORGL);
        BPCRORGL.INFO.FUNC = 'A';
        if (BPCSORGI.DATA.FUNC == 'O' 
            || BPCSORGI.DATA.FUNC == 'P') {
            WS_I += 1;
            CEP.TRC(SCCGWA, WS_I);
            BPRORGL.KEY.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
            if (BPCSORGI.DATA.TX_JRN == 0) {
                BPRORGL.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
            } else {
                BPRORGL.KEY.TX_JRN = BPCSORGI.DATA.TX_JRN;
            }
            if (BPCSORGI.DATA.TX_SEQ == 0) {
                BPRORGL.KEY.TX_SEQ = WS_I;
            } else {
                BPRORGL.KEY.TX_SEQ = BPCSORGI.DATA.TX_SEQ;
            }
            BPRORGL.AC_DT = BPCSORGI.DATA.INCO_DT;
            BPRORGL.AC_ORGI_TYP = BPCSORGI.DATA.AC_TYP;
            BPRORGL.CI_NO = CICACCU.DATA.CI_NO;
            BPRORGL.TX_TOOL = BPCSORGI.DATA.AC;
            BPRORGL.CCY = BPCSORGI.DATA.CCY;
            BPRORGL.CCY_TYPE = BPCSORGI.DATA.CCY_TYPE;
            BPRORGL.SEQ = BPCSORGI.DATA.SEQ;
            BPRORGL.ACO_AC = WS_ACO_AC;
            BPRORGL.INCO_OLD_BR = BPCSORGI.DATA.OLD_BR;
            BPRORGL.INCO_NEW_BR = BPCSORGI.DATA.NEW_BR;
            BPRORGL.TX_FLG = 'O';
            BPRORGL.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
            BPRORGL.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
            if (BPCSORGI.DATA.FUNC == 'P') {
                BPRORGL.UPT_TLR = BPCSORGI.DATA.TR_TLR;
            } else {
                BPRORGL.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
            }
            BPRORGL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            if (BPCSORGI.DATA.FUNC == 'P') {
                BPRORGL.CRT_TLR = BPCSORGI.DATA.TR_TLR;
            } else {
                BPRORGL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            }
            BPRORGL.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
            JIBS_tmp_int = BPRORGL.TS.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPRORGL.TS = "0" + BPRORGL.TS;
        }
        CEP.TRC(SCCGWA, BPCSORGI.DATA.AC);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.OLD_BR);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.NEW_BR);
        S000_CALL_BPZRORGL();
        if (pgmRtn) return;
    }
    public void B020_GET_ACO_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "************************");
        CEP.TRC(SCCGWA, BPCSORGI.DATA.AC);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.SEQ);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.CCY);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.CCY_TYPE);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = BPCSORGI.DATA.AC;
        CICQACAC.DATA.AGR_SEQ = BPCSORGI.DATA.SEQ;
        CICQACAC.DATA.CCY_ACAC = BPCSORGI.DATA.CCY;
        CICQACAC.DATA.CR_FLG = BPCSORGI.DATA.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR2);
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_OPN_BR_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS);
        WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        WS_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        WS_OPN_BR = CICQACAC.O_DATA.O_ACAC_DATA.O_OPN_BR_ACAC;
        WS_ACAC_STS = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS;
    }
    public void B030_GET_ACO_NO_Q() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "*********************TMP");
        IBS.init(SCCGWA, CICQACAC);
        CEP.TRC(SCCGWA, BPCSORGI.DATA.AC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = BPCSORGI.DATA.AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.RC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        WS_ACO_AC_T = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        WS_CCY_AC = CICQACAC.O_DATA.O_ACR_DATA.O_CCY_ACR;
    }
    public void B030_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOORGI);
        BPCOORGI.AC = BPCSORGI.DATA.AC;
        BPCOORGI.OLD_BR = BPCSORGI.DATA.OLD_BR;
        BPCOORGI.NEW_BR = BPCSORGI.DATA.NEW_BR;
        BPCOORGI.ORG_TYP = BPCSORGI.DATA.ORG_TYP;
        BPCOORGI.FILLE1 = BPCSORGI.DATA.TITLE_FILLE1;
        BPCOORGI.FILLE2 = BPCSORGI.DATA.TITLE_FILLE2;
        BPCOORGI.FILLE3 = BPCSORGI.DATA.TITLE_FILLE3;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOORGI;
        SCCFMT.DATA_LEN = 135;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_BPTORGL() throws IOException,SQLException,Exception {
        BPTORGL_RD = new DBParm();
        BPTORGL_RD.TableName = "BPTORGL";
        BPTORGL_RD.where = "TX_TOOL = :BPRORGL.TX_TOOL "
            + "AND TX_FLG = :BPRORGL.TX_FLG";
        BPTORGL_RD.fst = true;
        IBS.READ(SCCGWA, BPRORGL, this, BPTORGL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ORGL_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ORGL_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_BPTPREAC() throws IOException,SQLException,Exception {
        BPTPREAC_RD = new DBParm();
        BPTPREAC_RD.TableName = "BPTPREAC";
        BPTPREAC_RD.where = "AC = :BPRPREAC.KEY.AC "
            + "AND RM_CR_FLG = :BPRPREAC.RM_CR_FLG";
        BPTPREAC_RD.fst = true;
        IBS.READ(SCCGWA, BPRPREAC, this, BPTPREAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PREAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PREAC_FLG = 'N';
        } else {
        }
    }
    public void S000_CALL_BPZRORGI() throws IOException,SQLException,Exception {
        BPCRORGI.INFO.POINTER = BPRORGI;
        BPCRORGI.INFO.LEN = 190;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ORGI", BPCRORGI);
        if (BPCRORGI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCRORGI.RC);
        }
    }
    public void S000_CALL_BPZRORGL() throws IOException,SQLException,Exception {
        BPCRORGL.INFO.POINTER = BPRORGL;
        BPCRORGL.INFO.LEN = 245;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ORGL", BPCRORGL);
        if (BPCRORGL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCRORGL.RC);
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = BPCSORGI.DATA.AC;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, IBCQINF.RC);
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
    }
    public void S000_CALL_BPZRORLB() throws IOException,SQLException,Exception {
        BPCRORLB.INFO.LEN = 245;
        IBS.CALLCPN(SCCGWA, "BP-R-BRW-ORG-RGL", BPCRORLB);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRORLB.RC);
        if (BPCRORLB.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            CEP.ERR(SCCGWA, BPCRORLB.RC);
        }
    }
    public void S000_CALL_SCZBSPS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-S-GET-BSP-INF", SCCBSPS);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("CI3011")) {
            CEP.ERR(SCCGWA, CICACCU.RC);
        }
    }
    public void S000_CALL_CIZACCU_SECOND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICACCU.RC);
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY, true);
        if (DCCPACTY.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCPACTY.RC);
        }
    }
    public void S000_CALL_DDZUQCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-CHK-BR-ACTYPE", DDCUQCAC);
        CEP.TRC(SCCGWA, DDCUQCAC.RC);
        if (DDCUQCAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUQCAC.RC);
        }
    }
    public void S000_CALL_DCZUSPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-STD-AC", DCCUSPAC);
    }
    public void S000_CALL_DCZUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void S000_CALL_LNZSIQIF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSIQIF);
        LNCSIQIF.CONTRACT_NO = WS_ACO_AC;
        IBS.CALLCPN(SCCGWA, "LN-INQ-INF", LNCSIQIF);
        CEP.TRC(SCCGWA, LNCSIQIF.RC.RC_CODE);
        if (LNCSIQIF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, LNCSIQIF.RC);
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        CEP.TRC(SCCGWA, CICQCIAC.RC);
        if (CICQCIAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQCIAC.RC);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCSORGI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCSORGI=");
            CEP.TRC(SCCGWA, BPCSORGI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

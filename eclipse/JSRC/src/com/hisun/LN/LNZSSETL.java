package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.AI.*;
import com.hisun.DD.*;
import com.hisun.IB.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSSETL {
    int JIBS_tmp_int;
    LNZSSETL_WS_OUT_INFO WS_OUT_INFO;
    DBParm LNTSETL_RD;
    brParm LNTSETL_BR = new brParm();
    brParm LNTCONT_BR = new brParm();
    DBParm LNTCONT_RD;
    DBParm LNTICTL_RD;
    DBParm LNTAGRE_RD;
    DBParm LNTAPRD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char CON_ATTR_D = 'D';
    char CON_ATTR_C = 'C';
    int MAX_COL = 99;
    int MAX_ROW = 99;
    int COL_CNT = 18;
    String FMT_ID = "LN510";
    String P_INQ_ORG = "BP-P-INQ-ORG";
    LNZSSETL_WS_VARIABLES WS_VARIABLES = new LNZSSETL_WS_VARIABLES();
    LNZSSETL_WS_OUT_RECODE WS_OUT_RECODE = new LNZSSETL_WS_OUT_RECODE();
    LNZSSETL_WS_COND_FLG WS_COND_FLG = new LNZSSETL_WS_COND_FLG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTCALF BPCTCALF = new BPCTCALF();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    BPCFAMTA BPCFAMTA = new BPCFAMTA();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCEX BPCEX = new BPCEX();
    BPRTRT BPRTRT = new BPRTRT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNRSETL LNRSETL = new LNRSETL();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    CICACCU CICACCU = new CICACCU();
    LNCICONT LNCICONT = new LNCICONT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNCDARLM LNCDARLM = new LNCDARLM();
    LNCRDARL LNCRDARL = new LNCRDARL();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCRFACT LNCRFACT = new LNCRFACT();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    LNRAGRE LNRAGRE = new LNRAGRE();
    AICPQITM AICPQITM = new AICPQITM();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    AICPQMIB AICPQMIB = new AICPQMIB();
    IBCQINF IBCQINF = new IBCQINF();
    CICQACRI CICQACRI = new CICQACRI();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNRAPRD_WS_DB_VARS WS_DB_VARS = new LNRAPRD_WS_DB_VARS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    SCCAWAC SCCAWAC;
    LNCSSETL LNCSSETL;
    public void MP(SCCGWA SCCGWA, LNCSSETL LNCSSETL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSSETL = LNCSSETL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSSETL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, WS_VARIABLES.WS_FMT_OUT);
        WS_COND_FLG.MSG_FLG = 'Y';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSSETL.CONTRACT_ATTR);
        if (WS_COND_FLG.CONTRACT_ATTR == 'I') {
            B010_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (WS_COND_FLG.CONTRACT_ATTR == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
            B010_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (WS_COND_FLG.CONTRACT_ATTR == 'D') {
            B030_DELETE_RECORD_PROC();
            if (pgmRtn) return;
            B010_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (WS_COND_FLG.CONTRACT_ATTR == 'M') {
            B040_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
            B010_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCSSETL.CONTRACT_ATTR == 'M') {
            B010_AC_TYP();
            if (pgmRtn) return;
        }
        if ((LNCSSETL.CONTRACT_ATTR == 'M' 
            || LNCSSETL.CONTRACT_ATTR == 'D')) {
            B010_STSW_CHECK();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSSETL.CONTRACT_ATTR);
        WS_COND_FLG.CONTRACT_ATTR = LNCSSETL.CONTRACT_ATTR;
        if ((WS_COND_FLG.CONTRACT_ATTR != 'A' 
            && WS_COND_FLG.CONTRACT_ATTR != 'M' 
            && WS_COND_FLG.CONTRACT_ATTR != 'D' 
            && WS_COND_FLG.CONTRACT_ATTR != 'I')) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CONT_ATTR;
            if (WS_COND_FLG.CONTRACT_ATTR == ' ') WS_VARIABLES.FLD_NO = 0;
            else WS_VARIABLES.FLD_NO = Short.parseShort(""+WS_COND_FLG.CONTRACT_ATTR);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSSETL.AC_TYP.equalsIgnoreCase("05") 
            && (LNCSSETL.SETTLE_TYPE != '1' 
            && LNCSSETL.SETTLE_TYPE != '2' 
            && LNCSSETL.SETTLE_TYPE != 'B')) {
            CEP.TRC(SCCGWA, "SSETL-SETTLE-TYPE ERRO:");
            CEP.TRC(SCCGWA, LNCSSETL.SETTLE_TYPE);
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_NOTMATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSSETL.SETTLE_TYPE == '3' 
            && !LNCSSETL.AC_TYP.equalsIgnoreCase("04")) {
            CEP.TRC(SCCGWA, "SSETL-SETTLE-TYPE ERRO:");
            CEP.TRC(SCCGWA, LNCSSETL.SETTLE_TYPE);
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_NOTMATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSSETL.CI_TYPE != 'B' 
            && LNCSSETL.CI_TYPE != 'P' 
            && LNCSSETL.CI_TYPE != 'C' 
            && LNCSSETL.CI_TYPE != 'E' 
            && LNCSSETL.CI_TYPE != 'T' 
            && LNCSSETL.CI_TYPE != ' ') {
            CEP.TRC(SCCGWA, "SSETL-CI-TYPE ERRO:");
            CEP.TRC(SCCGWA, LNCSSETL.CI_TYPE);
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_EXCEED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!LNCSSETL.AC_TYP.equalsIgnoreCase("09") 
            && !LNCSSETL.AC_TYP.equalsIgnoreCase("01") 
            && !LNCSSETL.AC_TYP.equalsIgnoreCase("02") 
            && !LNCSSETL.AC_TYP.equalsIgnoreCase("03") 
            && !LNCSSETL.AC_TYP.equalsIgnoreCase("04") 
            && !LNCSSETL.AC_TYP.equalsIgnoreCase("05") 
            && !LNCSSETL.AC_TYP.equalsIgnoreCase("06") 
            && LNCSSETL.AC_TYP.trim().length() > 0) {
            CEP.TRC(SCCGWA, LNCSSETL.AC_TYP);
            CEP.TRC(SCCGWA, "SSETL-AC-TYP ERRO:");
            CEP.TRC(SCCGWA, LNCSSETL.AC_TYP);
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_EXCEED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSSETL.SETTLE_TYPE == 'C' 
            && LNCSSETL.CI_TYPE != 'C' 
            && LNCSSETL.CI_TYPE != 'P') {
            CEP.TRC(SCCGWA, "SSETL-CI-TYPE ERRO:");
            CEP.TRC(SCCGWA, LNCSSETL.CI_TYPE);
            CEP.TRC(SCCGWA, "SSETL-SETTLE-TYPE ERRO:");
            CEP.TRC(SCCGWA, LNCSSETL.SETTLE_TYPE);
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_NOMATCH_TYP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSSETL.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = LNCSSETL.CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        LNRSETL.KEY.CI_TYPE = LNCSSETL.CI_TYPE;
        LNRSETL.KEY.CCY = LNCSSETL.CCY;
        LNRSETL.KEY.SETTLE_TYPE = LNCSSETL.SETTLE_TYPE;
        R000_CHECK_GROUP_SEQ();
        if (pgmRtn) return;
        T000_READ_LNTSETL_MWHD_FLG();
        if (pgmRtn) return;
        if (LNCSSETL.MWHD_AC_FLG != ' ') {
            CEP.TRC(SCCGWA, LNCSSETL.AC_DATA[1-1].STL_AC);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
                && WS_COND_FLG.CONTRACT_ATTR == 'A') {
                if (LNCSSETL.AC_DATA[1-1].STL_AC.trim().length() == 0 
                    && LNCSSETL.AC_DATA[2-1].STL_AC.trim().length() == 0 
                    && LNCSSETL.AC_DATA[3-1].STL_AC.trim().length() == 0 
                    && LNCSSETL.AC_DATA[4-1].STL_AC.trim().length() == 0 
                    && LNCSSETL.MWHD_AC_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "2222222222222");
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_MWHD_AC_NOT_MATCH_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, "111111111111");
                if ((LNCSSETL.AC_DATA[1-1].STL_AC.trim().length() > 0 
                    || LNCSSETL.AC_DATA[2-1].STL_AC.trim().length() > 0 
                    || LNCSSETL.AC_DATA[3-1].STL_AC.trim().length() > 0 
                    || LNCSSETL.AC_DATA[4-1].STL_AC.trim().length() > 0) 
                    && LNCSSETL.MWHD_AC_FLG == 'N') {
                    CEP.TRC(SCCGWA, "NNNNNNNNNNNNNNNNN");
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_MWHD_AC_NOT_MATCH_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (WS_COND_FLG.CONTRACT_ATTR == 'A' 
                    && LNCSSETL.MWHD_AC_FLG == 'N') {
                    CEP.TRC(SCCGWA, "1111");
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_MWHD_AC_NOT_MATCH_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if ((WS_COND_FLG.CONTRACT_ATTR == 'D' 
                    || WS_COND_FLG.CONTRACT_ATTR == 'I') 
                    && WS_DB_VARS.CNT5 > 1 
                    && LNCSSETL.MWHD_AC_FLG == 'N') {
                    CEP.TRC(SCCGWA, "2222");
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_MWHD_AC_NOT_MATCH_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if ((WS_COND_FLG.CONTRACT_ATTR == 'D' 
                    || WS_COND_FLG.CONTRACT_ATTR == 'I') 
                    && WS_DB_VARS.CNT5 <= 1 
                    && LNCSSETL.MWHD_AC_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "3333");
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_MWHD_AC_NOT_MATCH_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (WS_COND_FLG.CONTRACT_ATTR == 'M') {
                    if ((LNCSSETL.AC_DATA[1-1].STL_AC.trim().length() > 0 
                        || LNCSSETL.AC_DATA[2-1].STL_AC.trim().length() > 0 
                        || LNCSSETL.AC_DATA[3-1].STL_AC.trim().length() > 0 
                        || LNCSSETL.AC_DATA[4-1].STL_AC.trim().length() > 0) 
                        && LNCSSETL.MWHD_AC_FLG == 'N') {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_MWHD_AC_NOT_MATCH_AC;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (LNCSSETL.MWHD_AC_FLG == ' ') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
                && (WS_COND_FLG.CONTRACT_ATTR == 'A' 
                || WS_COND_FLG.CONTRACT_ATTR == 'M')) {
                if (LNCSSETL.AC_DATA[1-1].STL_AC.trim().length() == 0 
                    && LNCSSETL.AC_DATA[2-1].STL_AC.trim().length() == 0 
                    && LNCSSETL.AC_DATA[3-1].STL_AC.trim().length() == 0 
                    && LNCSSETL.AC_DATA[4-1].STL_AC.trim().length() == 0) {
                    LNCSSETL.MWHD_AC_FLG = 'N';
                } else {
                    LNCSSETL.MWHD_AC_FLG = 'Y';
                }
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (WS_COND_FLG.CONTRACT_ATTR == 'A') {
                    LNCSSETL.MWHD_AC_FLG = 'Y';
                }
                if ((WS_COND_FLG.CONTRACT_ATTR == 'D' 
                    || WS_COND_FLG.CONTRACT_ATTR == 'I') 
                    && WS_DB_VARS.CNT5 > 1) {
                    LNCSSETL.MWHD_AC_FLG = 'Y';
                }
                if ((WS_COND_FLG.CONTRACT_ATTR == 'D' 
                    || WS_COND_FLG.CONTRACT_ATTR == 'I') 
                    && WS_DB_VARS.CNT5 <= 1) {
                    LNCSSETL.MWHD_AC_FLG = 'N';
                }
                if (WS_COND_FLG.CONTRACT_ATTR == 'M') {
                    if (LNCSSETL.AC_DATA[1-1].STL_AC.trim().length() == 0 
                        && LNCSSETL.AC_DATA[2-1].STL_AC.trim().length() == 0 
                        && LNCSSETL.AC_DATA[3-1].STL_AC.trim().length() == 0 
                        && LNCSSETL.AC_DATA[4-1].STL_AC.trim().length() == 0) {
                        LNCSSETL.MWHD_AC_FLG = 'N';
                    } else {
                        LNCSSETL.MWHD_AC_FLG = 'Y';
                    }
                }
            }
        }
        if (LNCSSETL.AC_TYP.equalsIgnoreCase("02") 
            && LNCSSETL.AC_FLG == '0' 
            && LNCSSETL.AC.trim().length() > 0) {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.COA_FLG = "1";
            if (LNCSSETL.AC == null) LNCSSETL.AC = "";
            JIBS_tmp_int = LNCSSETL.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) LNCSSETL.AC += " ";
            AICPQITM.INPUT_DATA.NO = LNCSSETL.AC.substring(10 - 1, 10 + 10 - 1);
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            if (AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6")) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_IA_AC_OUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        for (WS_VARIABLES.CNT = 1; WS_VARIABLES.CNT <= 4; WS_VARIABLES.CNT += 1) {
            if (LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC.trim().length() > 0 
                && LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC_FLG == '0' 
                && LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP.equalsIgnoreCase("02")) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.COA_FLG = "1";
                WS_VARIABLES.S_AC = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC;
                if (WS_VARIABLES.S_AC == null) WS_VARIABLES.S_AC = "";
                JIBS_tmp_int = WS_VARIABLES.S_AC.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_VARIABLES.S_AC += " ";
                AICPQITM.INPUT_DATA.NO = WS_VARIABLES.S_AC.substring(10 - 1, 10 + 10 - 1);
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
                if (AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6")) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_IA_AC_OUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (LNCSSETL.SETTLE_TYPE != 'D' 
            && LNCSSETL.AC_TYP.equalsIgnoreCase("01") 
            && LNCSSETL.AC.trim().length() > 0 
            && LNCSSETL.AC_FLG == '0') {
            IBS.init(SCCGWA, DDCIMCCY);
            DDCIMCCY.DATA[1-1].AC = LNCSSETL.AC;
            DDCIMCCY.DATA[1-1].CCY = LNCSSETL.CCY;
            DDCIMCCY.DATA[1-1].CCY_TYPE = LNCSSETL.CCY_TYP;
            CEP.TRC(SCCGWA, "111");
            CEP.TRC(SCCGWA, LNCSSETL.CCY);
            S000_CALL_DDZIMCCY();
            if (pgmRtn) return;
            if (!DDCIMCCY.DATA[1-1].CCY.equalsIgnoreCase(LNCSSETL.CCY)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CCY_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCIMCCY.DATA[1-1].CCY_TYPE != LNCSSETL.CCY_TYP) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.ERR_CCY_TYP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        for (WS_VARIABLES.CNT = 1; WS_VARIABLES.CNT <= 4; WS_VARIABLES.CNT += 1) {
            if (LNCSSETL.SETTLE_TYPE != 'D' 
                && LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP.equalsIgnoreCase("01") 
                && LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC.trim().length() > 0 
                && LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC_FLG == '0') {
                IBS.init(SCCGWA, DDCIMCCY);
                DDCIMCCY.DATA[1-1].AC = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC;
                DDCIMCCY.DATA[1-1].CCY = LNCSSETL.CCY;
                DDCIMCCY.DATA[1-1].CCY_TYPE = LNCSSETL.CCY_TYP;
                CEP.TRC(SCCGWA, "222");
                S000_CALL_DDZIMCCY();
                if (pgmRtn) return;
                if (!DDCIMCCY.DATA[1-1].CCY.equalsIgnoreCase(LNCSSETL.CCY)) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CCY_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DDCIMCCY.DATA[1-1].CCY_TYPE != LNCSSETL.CCY_TYP) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.ERR_CCY_TYP;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (LNCSSETL.SETTLE_TYPE != 'D' 
            && LNCSSETL.AC_TYP.equalsIgnoreCase("02") 
            && LNCSSETL.AC.trim().length() > 0 
            && LNCSSETL.AC_FLG == '0') {
            if (LNCSSETL.AC == null) LNCSSETL.AC = "";
            JIBS_tmp_int = LNCSSETL.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) LNCSSETL.AC += " ";
            if (!LNCSSETL.AC.substring(7 - 1, 7 + 3 - 1).equalsIgnoreCase(LNCSSETL.CCY)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CCY_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        for (WS_VARIABLES.CNT = 1; WS_VARIABLES.CNT <= 4; WS_VARIABLES.CNT += 1) {
            if (LNCSSETL.SETTLE_TYPE != 'D' 
                && LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP.equalsIgnoreCase("02") 
                && LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC.trim().length() > 0 
                && LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC_FLG == '0') {
                WS_VARIABLES.AC = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC;
                if (WS_VARIABLES.AC == null) WS_VARIABLES.AC = "";
                JIBS_tmp_int = WS_VARIABLES.AC.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_VARIABLES.AC += " ";
                if (!WS_VARIABLES.AC.substring(7 - 1, 7 + 3 - 1).equalsIgnoreCase(LNCSSETL.CCY)) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CCY_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (LNCSSETL.AC_TYP.equalsIgnoreCase("03") 
            && LNCSSETL.AC.trim().length() > 0 
            && LNCSSETL.AC_FLG == '0') {
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.AC_NO = LNCSSETL.AC;
            S000_CALL_IBZQINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.CCY);
            if (!IBCQINF.INPUT_DATA.CCY.equalsIgnoreCase(LNCSSETL.CCY)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_IB_CCY_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        for (WS_VARIABLES.CNT = 1; WS_VARIABLES.CNT <= 4; WS_VARIABLES.CNT += 1) {
            if (LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP.equalsIgnoreCase("03") 
                && LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC.trim().length() > 0 
                && LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC_FLG == '0') {
                IBS.init(SCCGWA, IBCQINF);
                IBCQINF.INPUT_DATA.AC_NO = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC;
                S000_CALL_IBZQINF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.CCY);
                CEP.TRC(SCCGWA, LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC);
                if (!IBCQINF.INPUT_DATA.CCY.equalsIgnoreCase(LNCSSETL.CCY)) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_IB_CCY_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_AC_TYP() throws IOException,SQLException,Exception {
        if (LNCSSETL.AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            WS_VARIABLES.STL_ACTYP = " ";
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = LNCSSETL.AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
                WS_VARIABLES.STL_ACTYP = "03";
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("033")) {
                    WS_VARIABLES.STL_ACTYP = "09";
                } else {
                    WS_VARIABLES.STL_ACTYP = "01";
                }
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                WS_VARIABLES.STL_ACTYP = "05";
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                WS_VARIABLES.STL_ACTYP = "02";
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
                WS_VARIABLES.STL_ACTYP = "09";
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_AC_TYP_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNCSSETL.AC_TYP.trim().length() == 0) {
                LNCSSETL.AC_TYP = WS_VARIABLES.STL_ACTYP;
            } else {
                CEP.TRC(SCCGWA, LNCSSETL.AC_TYP);
                CEP.TRC(SCCGWA, WS_VARIABLES.STL_ACTYP);
                if ((LNCSSETL.AC_TYP.equalsIgnoreCase("04") 
                    || LNCSSETL.AC_TYP.equalsIgnoreCase("09")) 
                    && ((WS_VARIABLES.STL_ACTYP.equalsIgnoreCase("04") 
                    || WS_VARIABLES.STL_ACTYP.equalsIgnoreCase("09")))) {
                } else {
                    if (!LNCSSETL.AC_TYP.equalsIgnoreCase(WS_VARIABLES.STL_ACTYP)) {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.ERR_AC_TYP;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        for (WS_VARIABLES.CNT = 1; WS_VARIABLES.CNT <= 4; WS_VARIABLES.CNT += 1) {
            if (LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC.trim().length() > 0) {
                IBS.init(SCCGWA, CICQACRI);
                WS_VARIABLES.STL_ACTYP = " ";
                CICQACRI.FUNC = 'A';
                CICQACRI.DATA.AGR_NO = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC;
                S000_CALL_CIZQACRI();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
                if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
                    WS_VARIABLES.STL_ACTYP = "03";
                } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                    if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("033")) {
                        WS_VARIABLES.STL_ACTYP = "09";
                    } else {
                        WS_VARIABLES.STL_ACTYP = "01";
                    }
                } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                    WS_VARIABLES.STL_ACTYP = "05";
                } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                    WS_VARIABLES.STL_ACTYP = "02";
                } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
                    WS_VARIABLES.STL_ACTYP = "09";
                } else {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_AC_TYP_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP.trim().length() == 0) {
                    LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP = WS_VARIABLES.STL_ACTYP;
                } else {
                    if ((LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP.equalsIgnoreCase("04") 
                        || LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP.equalsIgnoreCase("09")) 
                        && ((WS_VARIABLES.STL_ACTYP.equalsIgnoreCase("04") 
                        || WS_VARIABLES.STL_ACTYP.equalsIgnoreCase("09")))) {
                    } else {
                        CEP.TRC(SCCGWA, LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP);
                        CEP.TRC(SCCGWA, WS_VARIABLES.STL_ACTYP);
                        if (!LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP.equalsIgnoreCase(WS_VARIABLES.STL_ACTYP)) {
                            WS_VARIABLES.ERR_MSG = ERROR_MSG.ERR_AC_TYP;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
        }
    }
    public void B010_STSW_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
        WS_VARIABLES.CTL_STSW = LNRICTL.CTL_STSW;
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        CEP.TRC(SCCGWA, WS_VARIABLES.CTL_STSW.substring(4 - 1, 4 + 1 - 1));
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || WS_VARIABLES.CTL_STSW.substring(48 - 1, 48 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
        LNRAPRD.DUE_AUTO_FLG = '4';
        if (LNCSSETL.PAGE_NUM == 0) {
            WS_VARIABLES.WS_DATE.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.WS_DATE.CURR_PAGE = (short) LNCSSETL.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
        WS_VARIABLES.WS_DATE.LAST_PAGE = 'N';
        if (LNCSSETL.PAGE_ROW == 0) {
            WS_VARIABLES.WS_DATE.PAGE_ROW = 20;
            WS_OUT_INFO = new LNZSSETL_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        } else {
            WS_VARIABLES.WS_DATE.PAGE_ROW = (short) LNCSSETL.PAGE_ROW;
            WS_OUT_INFO = new LNZSSETL_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        }
        WS_VARIABLES.WS_DATE.NEXT_START_NUM = ( ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATE.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
        IBS.init(SCCGWA, LNCRSETL);
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        B015_BROWSE_CHECK_PROC();
        if (pgmRtn) return;
        T000_STARTBR_LNTSETL_ATTR();
        if (pgmRtn) return;
        T000_READNEXT_LNTSETL_ATTR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            for (WS_VARIABLES.WS_DATE.IDX = 1; WS_VARIABLES.WS_DATE.IDX <= WS_VARIABLES.WS_DATE.PAGE_ROW 
                && SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_VARIABLES.WS_DATE.IDX += 1) {
                CEP.TRC(SCCGWA, "SHI");
                IBS.init(SCCGWA, WS_OUT_INFO);
                WS_OUT_INFO.O_CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
                WS_OUT_INFO.O_CI_TYPE = LNRSETL.KEY.CI_TYPE;
                WS_OUT_INFO.O_PART_BK = LNRSETL.KEY.PART_BK;
                WS_OUT_INFO.O_CCY = LNRSETL.KEY.CCY;
                WS_OUT_INFO.O_SETTLE_TYPE = LNRSETL.KEY.SETTLE_TYPE;
                WS_OUT_INFO.O_MWHD_AC_FLG = LNRSETL.MWHD_AC_FLG;
                WS_OUT_INFO.O_AC_TYP = LNRSETL.AC_TYP;
                WS_OUT_INFO.O_AC = LNRSETL.AC;
                WS_OUT_INFO.O_CCY_TYP = LNRSETL.CCY_TYP;
                WS_OUT_INFO.O_AC_FLG = LNRSETL.AC_FLG;
                WS_OUT_INFO.O_REMARK = LNRSETL.REMARK;
                if (LNRSETL.AC_TYP.equalsIgnoreCase("01") 
                    && LNRSETL.AC_FLG == '0') {
                    IBS.init(SCCGWA, CICACCU);
                    CICACCU.DATA.AGR_NO = LNRSETL.AC;
                    CICACCU.DATA.ENTY_TYP = '1';
                    S000_CALL_CIZACCU();
                    if (pgmRtn) return;
                    WS_OUT_INFO.O_AC_NM = CICACCU.DATA.AC_CNM;
                }
                if (LNRSETL.AC_TYP.equalsIgnoreCase("02") 
                    && LNRSETL.AC_FLG == '0') {
                    IBS.init(SCCGWA, AICPQMIB);
                    AICPQMIB.INPUT_DATA.AC = LNRSETL.AC;
                    S000_CALL_AIZPQMIB();
                    if (pgmRtn) return;
                    WS_OUT_INFO.O_AC_NM = AICPQMIB.OUTPUT_DATA.CHS_NM;
                    if (LNRSETL.KEY.SETTLE_TYPE == '2') {
                        LNRAPRD.DUE_AUTO_FLG = '1';
                    }
                }
                if (LNRSETL.AC_TYP.equalsIgnoreCase("03") 
                    && LNRSETL.AC_FLG == '0') {
                    IBS.init(SCCGWA, IBCQINF);
                    IBCQINF.INPUT_DATA.NOSTRO_CD = LNRSETL.AC;
                    IBCQINF.INPUT_DATA.CCY = LNRSETL.KEY.CCY;
                    S000_CALL_IBZQINF();
                    if (pgmRtn) return;
                    WS_OUT_INFO.O_AC_NM = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
                }
                if ((LNRSETL.AC_TYP.equalsIgnoreCase("05") 
                    || LNRSETL.AC_TYP.equalsIgnoreCase("06")) 
                    && LNRSETL.AC_FLG == '0') {
                    IBS.init(SCCGWA, CICACCU);
                    CICACCU.DATA.AGR_NO = LNRSETL.AC;
                    CICACCU.DATA.ENTY_TYP = '1';
                    S000_CALL_CIZACCU();
                    if (pgmRtn) return;
                    WS_OUT_INFO.O_AC_NM = CICACCU.DATA.AC_CNM;
                }
                WS_VARIABLES.WS_DATE.NEXT_START_NUM += 1;
                WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATE.NEXT_START_NUM;
                T000_READNEXT_LNTSETL_ATTR();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.IDX - 1;
                WS_VARIABLES.WS_DATE.TOTAL_NUM = ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW + WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_OUT_INFO = new LNZSSETL_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            } else {
                LNRSETL.KEY.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
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
            WS_OUT_INFO = new LNZSSETL_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        }
        T000_ENDBR_LNTSETL();
        if (pgmRtn) return;
        T000_REWRITE_LNTAPRD();
        if (pgmRtn) return;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATE.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATE.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATE.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_ROW = WS_VARIABLES.WS_DATE.PAGE_ROW;
        R020_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        R000_GET_SETL_INFO();
        if (pgmRtn) return;
        if (LNCSSETL.CI_TYPE == 'B' 
            && LNCSSETL.SETTLE_TYPE == '2') {
            if ((LNCSSETL.CCY.trim().length() > 0) 
                && !LNCSSETL.CCY.equalsIgnoreCase(LNRCONT.CCY)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CCY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        R000_READ_SETL_AC();
        if (pgmRtn) return;
        LNRSETL.REMARK = LNCSSETL.REMARK;
        LNRSETL.CRT_DATE = SCCGWA.COMM_AREA.TR_DATE;
        LNRSETL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRSETL.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        LNRSETL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRSETL.KEY.PART_BK = LNCSSETL.PART_BK;
        LNRSETL.MWHD_AC_FLG = LNCSSETL.MWHD_AC_FLG;
        if (LNCSSETL.AC.trim().length() > 0) {
            LNRSETL.AC = LNCSSETL.AC;
            LNRSETL.CCY_TYP = LNCSSETL.CCY_TYP;
            LNRSETL.AC_FLG = LNCSSETL.AC_FLG;
            LNRSETL.AC_TYP = LNCSSETL.AC_TYP;
            LNRSETL.KEY.SEQ_NO = 0;
        }
        R000_ADD_SETL_AC();
        if (pgmRtn) return;
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        if (LNCSSETL.AC.trim().length() > 0) {
            LNRSETL.AC = LNCSSETL.AC;
            LNRSETL.KEY.SEQ_NO = 0;
            R000_READ_SETL_AC();
            if (pgmRtn) return;
            T000_DELETE_LNTSETL();
            if (pgmRtn) return;
        }
        for (WS_VARIABLES.CNT = 1; WS_VARIABLES.CNT <= 4; WS_VARIABLES.CNT += 1) {
            if (LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC.trim().length() > 0) {
                LNRSETL.KEY.SEQ_NO = (short) WS_VARIABLES.CNT;
                LNRSETL.AC = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC;
                R000_READ_SETL_AC();
                if (pgmRtn) return;
                T000_DELETE_LNTSETL();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        LNCRCONT.FUNC = 'I';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRCONT.CCY);
        R010_MWHD_AC_FLG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRSETL.MWHD_AC_FLG);
    }
    public void B040_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        R000_MODIFY_CHECK_PROC();
        if (pgmRtn) return;
        WS_VARIABLES.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        R000_READ_SETL_AC();
        if (pgmRtn) return;
        R000_DEL_SETL_AC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSSETL.AC_TYP);
        CEP.TRC(SCCGWA, LNRSETL.AC_FLG);
        R000_ADD_SETL_AC();
        if (pgmRtn) return;
    }
    public void B015_BROWSE_CHECK_PROC() throws IOException,SQLException,Exception {
        if (LNRSETL.KEY.CONTRACT_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_MUST_INPUT;
            CEP.TRC(SCCGWA, WS_VARIABLES.ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        LNCRCONT.FUNC = 'I';
        CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B025_ADD_CHECK_PROC() throws IOException,SQLException,Exception {
        if (LNRSETL.MWHD_AC_FLG == ' ' 
            || LNRSETL.MWHD_AC_FLG == 'N') {
            B025_CHECK_LNTSETL();
            if (pgmRtn) return;
        } else {
            B025_CHECK_LNTSETL();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (!LNRICTL.CTL_STSW.substring(0, 1).equalsIgnoreCase("1") 
            && !LNRICTL.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_CTA_STS_NOT_VALID;
            CEP.TRC(SCCGWA, WS_VARIABLES.ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCQCCY.DATA.CCY = LNRSETL.KEY.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
    }
    public void R000_DELETE_CHECK_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        LNRSETL.KEY.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        LNRSETL.KEY.CI_TYPE = LNCSSETL.CI_TYPE;
        LNRSETL.KEY.CCY = LNCSSETL.CCY;
        LNRSETL.KEY.SETTLE_TYPE = LNCSSETL.SETTLE_TYPE;
        LNRSETL.KEY.SEQ_NO = 0;
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (!LNRICTL.CTL_STSW.substring(0, 1).equalsIgnoreCase("1") 
            && !LNRICTL.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_CTA_STS_NOT_VALID;
            CEP.TRC(SCCGWA, WS_VARIABLES.ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B055_QUERY_CHECK_PROC() throws IOException,SQLException,Exception {
        if (LNRSETL.MWHD_AC_FLG == ' ' 
            || LNRSETL.MWHD_AC_FLG == 'N') {
            B045_CHECK_LNTSETL();
            if (pgmRtn) return;
        } else {
            B045_CHECK_LNTSETL();
            if (pgmRtn) return;
        }
    }
    public void B025_CHECK_LNTSETL() throws IOException,SQLException,Exception {
        T010_READ_LNTSETL();
        if (pgmRtn) return;
    }
    public void B025_CHECK_LNTDARL() throws IOException,SQLException,Exception {
    }
    public void B045_CHECK_LNTSETL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        T020_READ_LNTSETL();
        if (pgmRtn) return;
    }
    public void R000_CHECK_GROUP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.set = "WS-CNT2=COUNT(*)";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO";
        IBS.GROUP(SCCGWA, LNRSETL, this, LNTSETL_RD);
        WS_VARIABLES.WS_DATE.TOTAL_NUM = WS_DB_VARS.CNT2;
    }
    public void R000_CHECK_GROUP_SEQ() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.set = "WS-CNT5=COUNT(*)";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = :LNRSETL.KEY.CI_TYPE "
            + "AND CCY = :LNRSETL.KEY.CCY "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE";
        IBS.GROUP(SCCGWA, LNRSETL, this, LNTSETL_RD);
    }
    public void R020_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = FMT_ID;
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 11519;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_ADD_SETL_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = WS_VARIABLES.CONTRACT_NO;
        LNRSETL.KEY.CCY = LNCSSETL.CCY;
        LNRSETL.MWHD_AC_FLG = LNCSSETL.MWHD_AC_FLG;
        LNRSETL.REMARK = LNCSSETL.REMARK;
        LNRSETL.CRT_DATE = LNCSSETL.CRT_DATE;
        LNRSETL.CRT_TLR = LNCSSETL.CRT_TLR;
        if (LNCSSETL.AC_DATA[1-1].STL_AC.trim().length() == 0) {
            LNRSETL.KEY.CI_TYPE = LNCSSETL.CI_TYPE;
            LNRSETL.KEY.PART_BK = LNCSSETL.PART_BK;
            LNRSETL.KEY.SETTLE_TYPE = LNCSSETL.SETTLE_TYPE;
            LNRSETL.KEY.SEQ_NO = WS_VARIABLES.SEQ_NO;
            LNRSETL.AC_TYP = LNCSSETL.AC_TYP;
            LNRSETL.AC = LNCSSETL.AC;
            LNRSETL.CCY_TYP = LNCSSETL.CCY_TYP;
            LNRSETL.AC_FLG = LNCSSETL.AC_FLG;
            CEP.TRC(SCCGWA, LNRSETL.KEY.CI_TYPE);
            CEP.TRC(SCCGWA, LNRSETL.KEY.PART_BK);
            CEP.TRC(SCCGWA, LNRSETL.KEY.SETTLE_TYPE);
            CEP.TRC(SCCGWA, LNRSETL.AC_TYP);
            CEP.TRC(SCCGWA, LNRSETL.AC);
            CEP.TRC(SCCGWA, LNRSETL.CCY_TYP);
            CEP.TRC(SCCGWA, LNRSETL.AC_FLG);
            CEP.TRC(SCCGWA, LNRSETL.KEY.SEQ_NO);
            T000_WRITE_LNTSETL();
            if (pgmRtn) return;
        } else {
            for (WS_VARIABLES.CNT = 1; WS_VARIABLES.CNT <= 4 
                && LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC.trim().length() != 0; WS_VARIABLES.CNT += 1) {
                LNRSETL.KEY.CI_TYPE = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_CI_TP;
                LNRSETL.KEY.PART_BK = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_PAR_BK;
                LNRSETL.KEY.SETTLE_TYPE = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_STL_TP;
                LNRSETL.AC_TYP = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP;
                LNRSETL.AC = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC;
                LNRSETL.CCY_TYP = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_CCY_TYP;
                LNRSETL.AC_FLG = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC_FLG;
                LNRSETL.KEY.SEQ_NO = (short) (WS_VARIABLES.SEQ_NO + WS_VARIABLES.CNT);
                CEP.TRC(SCCGWA, LNRSETL.KEY.CI_TYPE);
                CEP.TRC(SCCGWA, LNRSETL.KEY.PART_BK);
                CEP.TRC(SCCGWA, LNRSETL.KEY.SETTLE_TYPE);
                CEP.TRC(SCCGWA, LNRSETL.AC_TYP);
                CEP.TRC(SCCGWA, LNRSETL.AC);
                CEP.TRC(SCCGWA, LNRSETL.CCY_TYP);
                CEP.TRC(SCCGWA, LNRSETL.AC_FLG);
                CEP.TRC(SCCGWA, LNRSETL.KEY.SEQ_NO);
                T000_WRITE_LNTSETL();
                if (pgmRtn) return;
            }
        }
        R010_MWHD_AC_FLG();
        if (pgmRtn) return;
    }
    public void R000_READ_SETL_AC() throws IOException,SQLException,Exception {
        LNRSETL.KEY.CONTRACT_NO = WS_VARIABLES.CONTRACT_NO;
        LNRSETL.KEY.CI_TYPE = LNCSSETL.CI_TYPE;
        LNRSETL.KEY.CCY = LNCSSETL.CCY;
        LNRSETL.KEY.SETTLE_TYPE = LNCSSETL.SETTLE_TYPE;
        if (WS_COND_FLG.CONTRACT_ATTR != 'D') {
            LNRSETL.AC = LNCSSETL.AC;
        }
        if (WS_COND_FLG.CONTRACT_ATTR == 'D' 
            && LNRSETL.AC.trim().length() > 0) {
            T000_READ_LNTSETL_B();
            if (pgmRtn) return;
        }
        if (WS_COND_FLG.CONTRACT_ATTR == 'I') {
            CEP.TRC(SCCGWA, "TX-INQ");
            if (LNCSSETL.AC.trim().length() == 0 
                && LNCSSETL.AC_DATA[1-1].STL_AC.trim().length() == 0 
                && LNCSSETL.AC_DATA[2-1].STL_AC.trim().length() == 0 
                && LNCSSETL.AC_DATA[3-1].STL_AC.trim().length() == 0 
                && LNCSSETL.AC_DATA[4-1].STL_AC.trim().length() == 0) {
                T000_READ_LNTSETL();
                if (pgmRtn) return;
                if (WS_COND_FLG.REC_PACT_FLG == 'N') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_SETL_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                T000_STARTBR_LNTSETL_MOR_ATR();
                if (pgmRtn) return;
                T000_READNEXT_LNTSETL();
                if (pgmRtn) return;
                while (WS_COND_FLG.REC_PACT_FLG != 'N') {
                    if (LNRSETL.KEY.SEQ_NO == 0) {
                        WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC_TYP = LNRSETL.AC_TYP;
                        WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC = LNRSETL.AC;
                        WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC_FLG = LNRSETL.AC_FLG;
                        if (LNRSETL.AC_TYP.equalsIgnoreCase("01") 
                            && LNRSETL.AC_FLG == '0') {
                            IBS.init(SCCGWA, CICACCU);
                            CICACCU.DATA.AGR_NO = LNRSETL.AC;
                            CICACCU.DATA.ENTY_TYP = '1';
                            S000_CALL_CIZACCU();
                            if (pgmRtn) return;
                            WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC_NM = CICACCU.DATA.AC_CNM;
                        }
                        if (LNRSETL.AC_TYP.equalsIgnoreCase("02") 
                            && LNRSETL.AC_FLG == '0') {
                            IBS.init(SCCGWA, AICPQMIB);
                            AICPQMIB.INPUT_DATA.AC = LNRSETL.AC;
                            S000_CALL_AIZPQMIB();
                            if (pgmRtn) return;
                            WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC_NM = AICPQMIB.OUTPUT_DATA.CHS_NM;
                        }
                        if (LNRSETL.AC_TYP.equalsIgnoreCase("03") 
                            && LNRSETL.AC_FLG == '0') {
                            IBS.init(SCCGWA, IBCQINF);
                            IBCQINF.INPUT_DATA.NOSTRO_CD = LNRSETL.AC;
                            IBCQINF.INPUT_DATA.CCY = LNRSETL.KEY.CCY;
                            S000_CALL_IBZQINF();
                            if (pgmRtn) return;
                            WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC_NM = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
                        }
                        if ((LNRSETL.AC_TYP.equalsIgnoreCase("05") 
                            || LNRSETL.AC_TYP.equalsIgnoreCase("06")) 
                            && LNRSETL.AC_FLG == '0') {
                            IBS.init(SCCGWA, CICACCU);
                            CICACCU.DATA.AGR_NO = LNRSETL.AC;
                            CICACCU.DATA.ENTY_TYP = '1';
                            S000_CALL_CIZACCU();
                            if (pgmRtn) return;
                            WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC_NM = CICACCU.DATA.AC_CNM;
                        }
                    } else {
                        WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[LNRSETL.KEY.SEQ_NO-1].FMT_OUT_AC_TYP2 = LNRSETL.AC_TYP;
                        WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[LNRSETL.KEY.SEQ_NO-1].FMT_OUT_AC2 = LNRSETL.AC;
                        WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[LNRSETL.KEY.SEQ_NO-1].FMT_OUT_AC_FLG2 = LNRSETL.AC_FLG;
                        if (LNRSETL.AC_TYP.equalsIgnoreCase("01") 
                            && LNRSETL.AC_FLG == '0') {
                            IBS.init(SCCGWA, CICACCU);
                            CICACCU.DATA.AGR_NO = LNRSETL.AC;
                            CICACCU.DATA.ENTY_TYP = '1';
                            S000_CALL_CIZACCU();
                            if (pgmRtn) return;
                            WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[LNRSETL.KEY.SEQ_NO-1].WS_FMT_OUT_AC_NM2 = CICACCU.DATA.AC_CNM;
                        }
                        if (LNRSETL.AC_TYP.equalsIgnoreCase("02") 
                            && LNRSETL.AC_FLG == '0') {
                            IBS.init(SCCGWA, AICPQMIB);
                            AICPQMIB.INPUT_DATA.AC = LNRSETL.AC;
                            S000_CALL_AIZPQMIB();
                            if (pgmRtn) return;
                            WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[LNRSETL.KEY.SEQ_NO-1].WS_FMT_OUT_AC_NM2 = AICPQMIB.OUTPUT_DATA.CHS_NM;
                        }
                        if (LNRSETL.AC_TYP.equalsIgnoreCase("03") 
                            && LNRSETL.AC_FLG == '0') {
                            IBS.init(SCCGWA, IBCQINF);
                            IBCQINF.INPUT_DATA.NOSTRO_CD = LNRSETL.AC;
                            IBCQINF.INPUT_DATA.CCY = LNRSETL.KEY.CCY;
                            S000_CALL_IBZQINF();
                            if (pgmRtn) return;
                            WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[LNRSETL.KEY.SEQ_NO-1].WS_FMT_OUT_AC_NM2 = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
                        }
                        if ((LNRSETL.AC_TYP.equalsIgnoreCase("05") 
                            || LNRSETL.AC_TYP.equalsIgnoreCase("06")) 
                            && LNRSETL.AC_FLG == '0') {
                            IBS.init(SCCGWA, CICACCU);
                            CICACCU.DATA.AGR_NO = LNRSETL.AC;
                            CICACCU.DATA.ENTY_TYP = '1';
                            S000_CALL_CIZACCU();
                            if (pgmRtn) return;
                            WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[LNRSETL.KEY.SEQ_NO-1].WS_FMT_OUT_AC_NM2 = CICACCU.DATA.AC_CNM;
                        }
                    }
                    T000_READNEXT_LNTSETL();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "XXXX111XXX");
                }
                T000_ENDBR_LNTSETL();
                if (pgmRtn) return;
            } else {
                if (LNCSSETL.AC.trim().length() > 0) {
                    T000_READ_LNTSETL_A();
                    if (pgmRtn) return;
                    if (WS_COND_FLG.REC_SETL_A_FLG == 'N') {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_SETL_NOTFND;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC_TYP = LNRSETL.AC_TYP;
                    WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC = LNRSETL.AC;
                    WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC_FLG = LNRSETL.AC_FLG;
                    if (LNCSSETL.AC_TYP.equalsIgnoreCase("01") 
                        && LNCSSETL.AC_FLG == '0') {
                        IBS.init(SCCGWA, CICACCU);
                        CICACCU.DATA.AGR_NO = LNCSSETL.AC;
                        CICACCU.DATA.ENTY_TYP = '1';
                        S000_CALL_CIZACCU();
                        if (pgmRtn) return;
                        WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC_NM = CICACCU.DATA.AC_CNM;
                    }
                }
                for (WS_VARIABLES.CNT3 = 1; WS_VARIABLES.CNT3 <= 4; WS_VARIABLES.CNT3 += 1) {
                    if (LNCSSETL.AC_DATA[WS_VARIABLES.CNT3-1].STL_AC.trim().length() > 0) {
                        LNRSETL.AC = LNCSSETL.AC_DATA[WS_VARIABLES.CNT3-1].STL_AC;
                        T000_READ_LNTSETL_A();
                        if (pgmRtn) return;
                        if (WS_COND_FLG.REC_SETL_A_FLG == 'N') {
                            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_SETL_NOTFND;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                        WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[WS_VARIABLES.CNT3-1].FMT_OUT_AC_TYP2 = LNRSETL.AC_TYP;
                        WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[WS_VARIABLES.CNT3-1].FMT_OUT_AC2 = LNRSETL.AC;
                        WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[WS_VARIABLES.CNT3-1].FMT_OUT_AC_FLG2 = LNRSETL.AC_FLG;
                        if (LNRSETL.AC_TYP.equalsIgnoreCase("01") 
                            && LNRSETL.AC_FLG == '0') {
                            IBS.init(SCCGWA, CICACCU);
                            CICACCU.DATA.AGR_NO = LNRSETL.AC;
                            CICACCU.DATA.ENTY_TYP = '1';
                            S000_CALL_CIZACCU();
                            if (pgmRtn) return;
                            WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[WS_VARIABLES.CNT3-1].WS_FMT_OUT_AC_NM2 = CICACCU.DATA.AC_CNM;
                        }
                        if (LNRSETL.AC_TYP.equalsIgnoreCase("02") 
                            && LNRSETL.AC_FLG == '0') {
                            IBS.init(SCCGWA, AICPQMIB);
                            AICPQMIB.INPUT_DATA.AC = LNRSETL.AC;
                            S000_CALL_AIZPQMIB();
                            if (pgmRtn) return;
                            WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[WS_VARIABLES.CNT3-1].WS_FMT_OUT_AC_NM2 = AICPQMIB.OUTPUT_DATA.CHS_NM;
                        }
                        if (LNRSETL.AC_TYP.equalsIgnoreCase("03") 
                            && LNRSETL.AC_FLG == '0') {
                            IBS.init(SCCGWA, IBCQINF);
                            IBCQINF.INPUT_DATA.NOSTRO_CD = LNRSETL.AC;
                            IBCQINF.INPUT_DATA.CCY = LNRSETL.KEY.CCY;
                            S000_CALL_IBZQINF();
                            if (pgmRtn) return;
                            WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[WS_VARIABLES.CNT3-1].WS_FMT_OUT_AC_NM2 = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
                        }
                        if ((LNRSETL.AC_TYP.equalsIgnoreCase("05") 
                            || LNRSETL.AC_TYP.equalsIgnoreCase("06")) 
                            && LNRSETL.AC_FLG == '0') {
                            IBS.init(SCCGWA, CICACCU);
                            CICACCU.DATA.AGR_NO = LNRSETL.AC;
                            CICACCU.DATA.ENTY_TYP = '1';
                            S000_CALL_CIZACCU();
                            if (pgmRtn) return;
                            WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[WS_VARIABLES.CNT3-1].WS_FMT_OUT_AC_NM2 = CICACCU.DATA.AC_CNM;
                        }
                    }
                }
            }
        }
        if (WS_COND_FLG.CONTRACT_ATTR == 'A' 
            || WS_COND_FLG.CONTRACT_ATTR == 'M') {
            T000_READ_LNTSETL();
            if (pgmRtn) return;
        }
        if (WS_COND_FLG.CONTRACT_ATTR == 'A' 
            && WS_COND_FLG.MSG_FLG == 'Y') {
            if (WS_COND_FLG.REC_PACT_FLG == 'Y') {
                if (LNCSSETL.SETTLE_TYPE == '1' 
                    && WS_COND_FLG.CONTRACT_ATTR == 'A') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_TYP_TWO_ONLY_INQ;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                R000_CHECK_GROUP_SEQ();
                if (pgmRtn) return;
                if (WS_DB_VARS.CNT5 >= 5) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_SETL_AC_GREATER_FIVE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (LNCSSETL.SETTLE_TYPE == '2') {
                    LNRSETL.AC = LNCSSETL.AC;
                    T000_READ_LNTSETL_A();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, LNRSETL.AC);
                    if (WS_COND_FLG.REC_SETL_A_FLG == 'N') {
                    } else {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_SETL_EXIST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    for (WS_VARIABLES.CNT = 1; WS_VARIABLES.CNT <= 4; WS_VARIABLES.CNT += 1) {
                        if (LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC.trim().length() > 0) {
                            LNRSETL.AC = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC;
                            T000_READ_LNTSETL_A();
                            if (pgmRtn) return;
                            if (WS_COND_FLG.REC_SETL_A_FLG == 'Y') {
                                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_SETL_EXIST;
                                S000_ERR_MSG_PROC();
                                if (pgmRtn) return;
                            }
                        }
                    }
                } else {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_AC_TYP_NOT_SUP_MANY;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (WS_COND_FLG.CONTRACT_ATTR == 'I' 
            && WS_COND_FLG.MSG_FLG == 'Y') {
            if (WS_COND_FLG.REC_SETL_A_FLG == 'N') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_NOT_F_SETL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (WS_COND_FLG.CONTRACT_ATTR == 'M' 
            && WS_COND_FLG.MSG_FLG == 'Y') {
            if (WS_COND_FLG.REC_PACT_FLG == 'N') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_SETL_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_COND_FLG.REC_PACT_FLG == 'Y') {
                if (LNCSSETL.SETTLE_TYPE == '1' 
                    && WS_COND_FLG.CONTRACT_ATTR == 'M') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_TYP_TWO_ONLY_INQ;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (WS_COND_FLG.CONTRACT_ATTR == 'D' 
            && WS_COND_FLG.MSG_FLG == 'Y') {
            if (WS_COND_FLG.REC_SETL_A_FLG == 'N') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_SETL_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_COND_FLG.REC_SETL_A_FLG == 'Y') {
                if (LNCSSETL.SETTLE_TYPE == '1' 
                    && WS_COND_FLG.CONTRACT_ATTR == 'D') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_TYP_TWO_ONLY_INQ;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_DEL_SETL_AC() throws IOException,SQLException,Exception {
        LNRSETL.KEY.CONTRACT_NO = WS_VARIABLES.CONTRACT_NO;
        LNRSETL.KEY.CCY = LNCSSETL.CCY;
        if (LNCSSETL.AC_DATA[1-1].STL_AC.trim().length() == 0) {
            LNRSETL.KEY.PART_BK = LNCSSETL.PART_BK;
            LNRSETL.KEY.CI_TYPE = LNCSSETL.CI_TYPE;
            LNRSETL.KEY.SETTLE_TYPE = LNCSSETL.SETTLE_TYPE;
            LNRSETL.AC = LNCSSETL.AC;
            T000_READ_UPDATE_LNTSETL();
            if (pgmRtn) return;
            WS_VARIABLES.SEQ_NO = LNRSETL.KEY.SEQ_NO;
            T000_DELETE_LNTSETL();
            if (pgmRtn) return;
        } else {
            LNRSETL.KEY.CI_TYPE = LNCSSETL.CI_TYPE;
            LNRSETL.KEY.SETTLE_TYPE = '2';
            T000_READ_LNTSETL();
            if (pgmRtn) return;
            WS_VARIABLES.SEQ_NO = LNRSETL.KEY.SEQ_NO;
        }
    }
    public void R000_DEL_SETL_CONT_AC() throws IOException,SQLException,Exception {
        WS_COND_FLG.MSG_FLG = 'N';
        LNRCONT.KEY.CONTRACT_NO = WS_VARIABLES.CONTRACT_NO;
        CEP.TRC(SCCGWA, "CONTRACT-NO");
        CEP.TRC(SCCGWA, WS_VARIABLES.CONTRACT_NO);
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        if (WS_COND_FLG.CONT_FLG == 'Y' 
            && LNRCONT.FATHER_CONTRACT.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CONT-FOUND");
            LNRSETL.KEY.CONTRACT_NO = LNRCONT.FATHER_CONTRACT;
            CEP.TRC(SCCGWA, "CONT-FATHER-CONTRACT");
            CEP.TRC(SCCGWA, WS_VARIABLES.CONTRACT_NO);
            T000_STARTBR_LNTCONT_MOR_ATR();
            if (pgmRtn) return;
            T000_READNEXT_LNTCONT();
            if (pgmRtn) return;
            WS_VARIABLES.CNT4 = 1;
            while (WS_COND_FLG.CONT_FLG != 'N' 
                && WS_VARIABLES.CNT4 <= 500) {
                WS_VARIABLES.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
                R000_READ_SETL_AC();
                if (pgmRtn) return;
                if (WS_COND_FLG.REC_PACT_FLG == 'Y') {
                    R000_DEL_SETL_AC();
                    if (pgmRtn) return;
                    R000_GET_SETL_INFO();
                    if (pgmRtn) return;
                    R000_ADD_SETL_AC();
                    if (pgmRtn) return;
                }
                T000_READNEXT_LNTCONT();
                if (pgmRtn) return;
                WS_VARIABLES.CNT4 += 1;
                CEP.TRC(SCCGWA, WS_VARIABLES.CNT4);
            }
            T000_ENDBR_LNTCONT();
            if (pgmRtn) return;
        }
        WS_COND_FLG.MSG_FLG = 'Y';
    }
    public void R000_DEL_SETL_CMMT_AC() throws IOException,SQLException,Exception {
        WS_COND_FLG.MSG_FLG = 'N';
        LNRSETL.KEY.CONTRACT_NO = WS_VARIABLES.CONTRACT_NO;
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        T000_STARTBR_LNTCONT_MOR_ATR();
        if (pgmRtn) return;
        T000_READNEXT_LNTCONT();
        if (pgmRtn) return;
        while (WS_COND_FLG.CONT_FLG != 'N') {
            WS_VARIABLES.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
            CEP.TRC(SCCGWA, WS_VARIABLES.CONTRACT_NO);
            R000_READ_SETL_AC();
            if (pgmRtn) return;
            if (WS_COND_FLG.REC_PACT_FLG == 'Y') {
                R000_DEL_SETL_AC();
                if (pgmRtn) return;
                R000_GET_SETL_INFO();
                if (pgmRtn) return;
                R000_ADD_SETL_AC();
                if (pgmRtn) return;
            }
            T000_READNEXT_LNTCONT();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTCONT();
        if (pgmRtn) return;
        WS_COND_FLG.MSG_FLG = 'Y';
    }
    public void R000_MODIFY_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        LNCRCONT.FUNC = 'I';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void R000_FMT_DATA_OUTPUT() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_FMT_OUT.FMT_OUT_CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        if (LNCSSETL.CONTRACT_ATTR == 'M') {
            IBS.init(SCCGWA, LNRAGRE);
            LNRAGRE.KEY.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
            T000_READ_LNTAGRE();
            if (pgmRtn) return;
            WS_VARIABLES.WS_FMT_OUT.FMT_OUT_CONTRACT_NO = LNRAGRE.DRAW_NO;
        }
        WS_VARIABLES.WS_FMT_OUT.FMT_OUT_PART_BK = LNRSETL.KEY.PART_BK;
        WS_VARIABLES.WS_FMT_OUT.FMT_OUT_CI_TYPE = LNRSETL.KEY.CI_TYPE;
        WS_VARIABLES.WS_FMT_OUT.FMT_OUT_CCY = LNRSETL.KEY.CCY;
        WS_VARIABLES.WS_FMT_OUT.FMT_OUT_SETTLE_TYPE = LNRSETL.KEY.SETTLE_TYPE;
        CEP.TRC(SCCGWA, LNRSETL.MWHD_AC_FLG);
        WS_VARIABLES.WS_FMT_OUT.FMT_OUT_MWHD_AC_FLG = LNRSETL.MWHD_AC_FLG;
        WS_VARIABLES.WS_FMT_OUT.FMT_OUT_REMARK = LNRSETL.REMARK;
        if (WS_COND_FLG.CONTRACT_ATTR != 'I') {
            WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC_TYP = LNCSSETL.AC_TYP;
            WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC = LNCSSETL.AC;
            WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC_FLG = LNCSSETL.AC_FLG;
            WS_VARIABLES.WS_FMT_OUT.FMT_OUT_AC_NM = LNCSSETL.AC_NM;
            CEP.TRC(SCCGWA, LNRSETL.REMARK);
            if (LNCSSETL.MWHD_AC_FLG == 'Y') {
                for (WS_VARIABLES.CNT = 1; WS_VARIABLES.CNT <= 4; WS_VARIABLES.CNT += 1) {
                    CEP.TRC(SCCGWA, LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP);
                    CEP.TRC(SCCGWA, LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC);
                    CEP.TRC(SCCGWA, LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC_FLG);
                    CEP.TRC(SCCGWA, LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC_NM);
                    WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[WS_VARIABLES.CNT-1].FMT_OUT_AC_TYP2 = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_ACTYP;
                    WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[WS_VARIABLES.CNT-1].FMT_OUT_AC2 = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC;
                    WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[WS_VARIABLES.CNT-1].FMT_OUT_CCY_TYP2 = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_CCY_TYP;
                    WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[WS_VARIABLES.CNT-1].FMT_OUT_AC_FLG2 = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC_FLG;
                    WS_VARIABLES.WS_FMT_OUT.WS_FMT_OUT_AC_DATA[WS_VARIABLES.CNT-1].WS_FMT_OUT_AC_NM2 = LNCSSETL.AC_DATA[WS_VARIABLES.CNT-1].STL_AC_NM;
                }
            }
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LN500";
        SCCFMT.DATA_PTR = WS_VARIABLES.WS_FMT_OUT;
        SCCFMT.DATA_LEN = 1620;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_SETL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSSETL.CONTRACT_NO;
        LNCRCONT.FUNC = 'I';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRCONT.CCY);
        if (WS_COND_FLG.CONTRACT_ATTR == 'A') {
            LNRSETL.CRT_DATE = SCCGWA.COMM_AREA.TR_DATE;
            LNRSETL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            LNRSETL.CRT_DATE = WS_VARIABLES.SETL_CRT_DATE;
            LNRSETL.CRT_TLR = WS_VARIABLES.SETL_CRT_TLR;
            LNRSETL.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
            LNRSETL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
    }
    public void R000_CHECK_MWHD_AC_FLG() throws IOException,SQLException,Exception {
        WS_COND_FLG.MWHD_AC_FLG = 'N';
        CEP.TRC(SCCGWA, LNCSSETL.MWHD_AC_FLG);
        if (LNCSSETL.MWHD_AC_FLG == ' ') {
            if (LNCSSETL.AC_DATA[1-1].STL_AC.trim().length() > 0 
                || LNCSSETL.AC_DATA[2-1].STL_AC.trim().length() > 0 
                || LNCSSETL.AC_DATA[3-1].STL_AC.trim().length() > 0 
                || LNCSSETL.AC_DATA[4-1].STL_AC.trim().length() > 0) {
                WS_COND_FLG.MWHD_AC_FLG = 'Y';
            }
        }
        if (LNCSSETL.MWHD_AC_FLG == 'Y') {
            if (LNCSSETL.AC_DATA[1-1].STL_AC.trim().length() == 0 
                && LNCSSETL.AC_DATA[2-1].STL_AC.trim().length() == 0 
                && LNCSSETL.AC_DATA[3-1].STL_AC.trim().length() == 0 
                && LNCSSETL.AC_DATA[4-1].STL_AC.trim().length() == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_MWHD_AC_NOT_MATCH_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_COND_FLG.MWHD_AC_FLG = 'Y';
        }
        if (LNCSSETL.MWHD_AC_FLG == 'N') {
            CEP.TRC(SCCGWA, "3333333333333");
            if (LNCSSETL.AC_DATA[1-1].STL_AC.trim().length() > 0 
                || LNCSSETL.AC_DATA[2-1].STL_AC.trim().length() > 0 
                || LNCSSETL.AC_DATA[3-1].STL_AC.trim().length() > 0 
                || LNCSSETL.AC_DATA[4-1].STL_AC.trim().length() > 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_MWHD_AC_NOT_MATCH_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_COND_FLG.MWHD_AC_FLG = 'N';
        }
    }
    public void R010_MWHD_AC_FLG() throws IOException,SQLException,Exception {
        R000_CHECK_GROUP_SEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_DB_VARS.CNT5);
        T000_STARTBR_LNTSETL_MOR_ATR();
        if (pgmRtn) return;
        T000_READNEXT_LNTSETL_FLG();
        if (pgmRtn) return;
        if (WS_COND_FLG.REC_PACT_FLG == 'N' 
            && WS_COND_FLG.CONTRACT_ATTR == 'D') {
            LNRSETL.MWHD_AC_FLG = 'N';
            if (LNCSSETL.SETTLE_TYPE == '2' 
                && LNCSSETL.CI_TYPE == 'B' 
                && (LNCSSETL.CCY.trim().length() > 0) 
                && LNCSSETL.CCY.equalsIgnoreCase(LNRCONT.CCY)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_RECO_LESS_THEN_ONE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        while (WS_COND_FLG.REC_PACT_FLG != 'N') {
            T000_UPDATE_LNTSETL_FLG();
            if (pgmRtn) return;
            if (WS_DB_VARS.CNT5 > 1) {
                LNRSETL.MWHD_AC_FLG = 'Y';
            } else {
                LNRSETL.MWHD_AC_FLG = 'N';
            }
            T000_REWRITE_LNTSETL_FLG();
            if (pgmRtn) return;
            T000_READNEXT_LNTSETL_FLG();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTSETL();
        if (pgmRtn) return;
    }
    public void B040_NFIANCE_HIS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.FMT_ID = "LNCSSETL";
        BPCPNHIS.INFO.FMT_ID_LEN = 1699;
        BPCPNHIS.INFO.REF_NO = LNCSSETL.CONTRACT_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.OLD_DAT_PT = LNCSSETL;
        BPCPNHIS.INFO.NEW_DAT_PT = LNCSSETL;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 1304;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RETURN_INFO != 'F') {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 1505;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        CEP.TRC(SCCGWA, BPCQCCY.RC);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            CEP.TRC(SCCGWA, WS_VARIABLES.ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCMMT() throws IOException,SQLException,Exception {
        LNCRCMMT.REC_PTR = LNRCMMT;
        LNCRCMMT.REC_LEN = 1835;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCMMT", LNCRCMMT);
        if (LNCRCMMT.RETURN_INFO != 'F') {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCMMT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void T010_READ_LNTSETL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRSETL.KEY.CI_TYPE);
        CEP.TRC(SCCGWA, LNRSETL.KEY.CCY);
        CEP.TRC(SCCGWA, LNRSETL.KEY.SETTLE_TYPE);
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.READ(SCCGWA, LNRSETL, LNTSETL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_RECO_EXIST;
            CEP.TRC(SCCGWA, WS_VARIABLES.ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T020_READ_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.READ(SCCGWA, LNRSETL, LNTSETL_RD);
        CEP.TRC(SCCGWA, "AAAAAAAA");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_SETL_NOTFND;
            CEP.TRC(SCCGWA, WS_VARIABLES.ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_LNTSETL() throws IOException,SQLException,Exception {
        LNRSETL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRSETL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.WRITE(SCCGWA, LNRSETL, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_DUPKEY, LNCRSETL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_LNTSETL() throws IOException,SQLException,Exception {
        LNRSETL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRSETL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.REWRITE(SCCGWA, LNRSETL, LNTSETL_RD);
    }
    public void T000_READ_LNTSETL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRSETL.KEY.CI_TYPE);
        CEP.TRC(SCCGWA, LNRSETL.KEY.CCY);
        CEP.TRC(SCCGWA, LNRSETL.KEY.SETTLE_TYPE);
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = :LNRSETL.KEY.CI_TYPE "
            + "AND CCY = :LNRSETL.KEY.CCY "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE";
        LNTSETL_RD.fst = true;
        LNTSETL_RD.order = "SEQ_NO DESC";
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.REC_PACT_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.REC_PACT_FLG = 'Y';
        }
    }
    public void T000_READ_LNTSETL_A() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = :LNRSETL.KEY.CI_TYPE "
            + "AND CCY = :LNRSETL.KEY.CCY "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE "
            + "AND AC = :LNRSETL.AC";
        LNTSETL_RD.fst = true;
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.REC_SETL_A_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.REC_SETL_A_FLG = 'Y';
        }
    }
    public void T000_READ_LNTSETL_B() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = :LNRSETL.KEY.CI_TYPE "
            + "AND CCY = :LNRSETL.KEY.CCY "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE "
            + "AND AC = :LNRSETL.AC";
        LNTSETL_RD.upd = true;
        LNTSETL_RD.fst = true;
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.REC_SETL_A_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.REC_SETL_A_FLG = 'Y';
        }
    }
    public void T000_READ_UPDATE_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.upd = true;
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = :LNRSETL.KEY.CI_TYPE "
            + "AND CCY = :LNRSETL.KEY.CCY "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE "
            + "AND PART_BK = :LNRSETL.KEY.PART_BK";
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.REC_PACT_FLG = 'N';
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_NOT_F_SETL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.REC_PACT_FLG = 'Y';
        }
    }
    public void T000_DELETE_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.DELETE(SCCGWA, LNRSETL, LNTSETL_RD);
    }
    public void T000_STARTBR_LNTSETL_ATTR() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_READNEXT_LNTSETL_ATTR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSETL, this, LNTSETL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_COND_FLG.REC_PACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_COND_FLG.REC_PACT_FLG = 'N';
        }
    }
    public void T000_STARTBR_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        IBS.STARTBR(SCCGWA, LNRSETL, LNTSETL_BR);
    }
    public void T000_STARTBR_LNTCONT_MOR_ATR() throws IOException,SQLException,Exception {
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.where = "FATHER_CONTRACT = :LNRSETL.KEY.CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRCONT, this, LNTCONT_BR);
    }
    public void T000_STARTBR_LNTSETL_MOR_ATR() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = :LNRSETL.KEY.CI_TYPE "
            + "AND CCY = :LNRSETL.KEY.CCY "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE";
        IBS.STARTBR(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.CONT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.CONT_FLG = 'N';
        }
    }
    public void T000_READNEXT_LNTCONT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRCONT, this, LNTCONT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.CONT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.CONT_FLG = 'N';
        }
    }
    public void T000_READNEXT_LNTSETL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSETL, this, LNTSETL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.REC_PACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.REC_PACT_FLG = 'N';
        }
    }
    public void T000_READNEXT_LNTSETL_FLG() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSETL, this, LNTSETL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_COND_FLG.REC_PACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_COND_FLG.REC_PACT_FLG = 'N';
        }
    }
    public void T000_UPDATE_LNTSETL_FLG() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.upd = true;
        IBS.READ(SCCGWA, LNRSETL, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_COND_FLG.REC_PACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_COND_FLG.REC_PACT_FLG = 'N';
        }
    }
    public void T000_REWRITE_LNTSETL_FLG() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.REWRITE(SCCGWA, LNRSETL, LNTSETL_RD);
    }
    public void T000_ENDBR_LNTSETL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSETL_BR);
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.where = "CONTRACT_NO = :LNRICTL.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_LNTSETL_MWHD_FLG() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = :LNRSETL.KEY.CI_TYPE "
            + "AND CCY = :LNRSETL.KEY.CCY "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE";
        LNTSETL_RD.fst = true;
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
    }
    public void T000_ENDBR_LNTCONT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTCONT_BR);
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void T000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        LNTAPRD_RD.upd = true;
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
    }
    public void T000_REWRITE_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.REWRITE(SCCGWA, LNRAPRD, LNTAPRD_RD);
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
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

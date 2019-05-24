package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.TD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUMCID {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm DCTCIDEP_BR = new brParm();
    DBParm DCTCIDEP_RD;
    boolean pgmRtn = false;
    String WS_SQL_AC_NO = "                                ";
    String WS_VIA_AC = "                                ";
    char WS_SQL_PROC_STS = ' ';
    int WS_TOT_COUNT = 0;
    String WS_SQL_OVR_NO = "                                                  ";
    short K_MAX_COLS = 20;
    short K_MAX_ROWS = 20;
    String K_OUTPUT_FMT_B = "SCZ01";
    String K_OUTPUT_FMT_I = "DC611";
    String K_OUTPUT_FMT_A = "DD612";
    String K_OUTPUT_FMT_U = "DC413";
    String K_OUTPUT_FMT_D = "DC414";
    char K_CARD_F_AC = '2';
    char K_PUBLIC_CUSTOM = '2';
    String K_PRDPR_TYPE = "PRDPR";
    String K_COMP_DEP_CODE = "IBS028";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CCI_INQ_ACCU = "CI-INQ-ACCU";
    String CPN_U_DDZIMMST = "DD-I-NFIN-M-MST";
    String CDD_I_NFIN_M_CCY = "DD-I-NFIN-M-CCY";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String CBP_P_QUERY_PRDT_INFO = "BP-P-QUERY-PRDT-INFO";
    String CCI_MAIN_AGT = "CI-MAIN-AGT";
    String CPN_U_BPZPFHIS = "BP-PROC-FHIS";
    DCZUMCID_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new DCZUMCID_WS_BROWSE_OUTPUT();
    DCZUMCID_WS_DETAIL_OUTPUT WS_DETAIL_OUTPUT = new DCZUMCID_WS_DETAIL_OUTPUT();
    DCZUMCID_WS_ADD_OUTPUT WS_ADD_OUTPUT = new DCZUMCID_WS_ADD_OUTPUT();
    String WS_MSG_ID = "      ";
    String WS_FMT_CDE = "     ";
    int WS_CNT = 0;
    String WS_PROD_CDE = "          ";
    char WS_PROC_STS = ' ';
    char WS_AC_STS = ' ';
    String WS_AC_STS_WORD = " ";
    int WS_START_DT = 0;
    int WS_END_DT = 0;
    String WS_C400_I_AC_NO = "                                ";
    String WS_AC_NO = "                                ";
    String WS_C400_I_PROD_CD = "          ";
    String WS_OVR_NO = " ";
    double WS_TD_TXN_AMT = 0;
    double WS_PAYING_INT = 0;
    double WS_DD_BAL = 0;
    String WS_COM_PROD_DESC = "                                                            ";
    String WS_PROD_TD = "          ";
    String WS_PROD_DD = "          ";
    double WS_TRIG_AMT = 0;
    DCZUMCID_WS_TRIG_AMT1 WS_TRIG_AMT1 = new DCZUMCID_WS_TRIG_AMT1();
    String WS_ACAC_NO = "                     ";
    String WS_CHECK_AC_NO = "                                ";
    String WS_CI_NO = "            ";
    String WS_LNK_ACAC_NO = "                     ";
    String WS_SIGNED_ACAC_NO = "                     ";
    char WS_TBL_FLAG = ' ';
    char WS_TBL_FLAG1 = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICACCU CICACCU = new CICACCU();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    CICMAGT CICMAGT = new CICMAGT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCUMCID DCCOLD = new DCCUMCID();
    DDCUCCAL DDCUCCAL = new DDCUCCAL();
    DCRIAACR DCRIAACR = new DCRIAACR();
    TDCTZZD TDCTZZD = new TDCTZZD();
    TDCMACO TDCMACO = new TDCMACO();
    TDCACE TDCACE = new TDCACE();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCRICPRD DCRICPRD = new DCRICPRD();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    DDCIMCYY DDCIMCYY = new DDCIMCYY();
    DCRCIDEP DCRCIDEP = new DCRCIDEP();
    SCCGWA SCCGWA;
    DCCUMCID DCCUMCID;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DCCUMCID DCCUMCID) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUMCID = DCCUMCID;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUMCID return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        WS_TBL_FLAG = 'Y';
        IBS.init(SCCGWA, DCCUMCID.O_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCUMCID.IO_AREA.FUNC_M == 'B' 
            || DCCUMCID.IO_AREA.FUNC_M == 'A') {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = DCCUMCID.IO_AREA.AC_NO;
            WS_AC_NO = DCCUMCID.IO_AREA.AC_NO;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            if (CICQACRI.O_DATA.O_AGR_NO.trim().length() > 0) {
                DCCUMCID.IO_AREA.AC_NO = CICQACRI.O_DATA.O_AGR_NO;
            }
        }
        if (DCCUMCID.IO_AREA.FUNC_M == 'B') {
            B100_PLAN_BROWSE();
            if (pgmRtn) return;
        } else if (DCCUMCID.IO_AREA.FUNC_M == 'I') {
            B200_PLAN_INQ();
            if (pgmRtn) return;
        } else if (DCCUMCID.IO_AREA.FUNC_M == 'M'
            || DCCUMCID.IO_AREA.FUNC_M == 'S') {
            B300_PLAN_MOD();
            if (pgmRtn) return;
        } else if (DCCUMCID.IO_AREA.FUNC_M == 'D') {
            B400_PLAN_DEL();
            if (pgmRtn) return;
        } else if (DCCUMCID.IO_AREA.FUNC_M == 'A') {
            B500_PLAN_ADD();
            if (pgmRtn) return;
        } else {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUMCID.IO_AREA.FUNC_M == 'A' 
            || DCCUMCID.IO_AREA.FUNC_M == 'M' 
            || DCCUMCID.IO_AREA.FUNC_M == 'S' 
            || DCCUMCID.IO_AREA.FUNC_M == 'D') {
            B600_REC_HIS();
            if (pgmRtn) return;
        }
    }
    public void B100_PLAN_BROWSE() throws IOException,SQLException,Exception {
        B100_10_INPUT_CHECK();
        if (pgmRtn) return;
        WS_SQL_AC_NO = DCCUMCID.IO_AREA.AC_NO;
        WS_SQL_PROC_STS = DCCUMCID.IO_AREA.PROC_STS;
        WS_SQL_OVR_NO = DCCUMCID.IO_AREA.OVR_NO;
        T000_STARTBR_DCTCIDEP();
        if (pgmRtn) return;
        T100_READNEXT_DCTCIDEP();
        if (pgmRtn) return;
        C200_OUTPUT_INI();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            WS_C400_I_PROD_CD = DCRCIDEP.PROD_CODE;
            C400_20_GET_PROD_PARA();
            if (pgmRtn) return;
            C300_OUTPUT_LIST();
            if (pgmRtn) return;
            T100_READNEXT_DCTCIDEP();
            if (pgmRtn) return;
        }
        T200_ENDBR_DCTCIDEP();
        if (pgmRtn) return;
    }
    public void B100_10_INPUT_CHECK() throws IOException,SQLException,Exception {
    }
    public void B110_CHECK_QACAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        WS_ACAC_NO = " ";
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = WS_CHECK_AC_NO;
        if (DCCUMCID.IO_AREA.FUNC_M == 'A') {
            CICQACAC.DATA.CCY_ACAC = DCCUMCID.IO_AREA.CCY;
            CICQACAC.DATA.CR_FLG = DCCUMCID.IO_AREA.CCY_TYP;
        } else {
            CICQACAC.DATA.CCY_ACAC = DCRCIDEP.CCY;
            CICQACAC.DATA.CR_FLG = DCRCIDEP.CCY_TYPE;
        }
        CEP.TRC(SCCGWA, CICQACAC.DATA.CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CR_FLG);
        if (CICQACAC.DATA.CCY_ACAC.trim().length() == 0) {
            CICQACAC.DATA.CCY_ACAC = "156";
        }
        if (CICQACAC.DATA.CR_FLG == ' ') {
            CICQACAC.DATA.CR_FLG = '1';
        }
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
    }
    public void B200_PLAN_INQ() throws IOException,SQLException,Exception {
        if (DCCUMCID.IO_AREA.OVR_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_OVR_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCIDEP);
        DCRCIDEP.KEY.OVR_NO = DCCUMCID.IO_AREA.OVR_NO;
        T300_READ_DCTCIDEP();
        if (pgmRtn) return;
        WS_FMT_CDE = K_OUTPUT_FMT_I;
        C000_DETAIL_OUTPUT();
        if (pgmRtn) return;
    }
    public void B300_PLAN_MOD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCIDEP);
        DCRCIDEP.KEY.OVR_NO = DCCUMCID.IO_AREA.OVR_NO;
        T400_READ_DCTCIDEP_UPD();
        if (pgmRtn) return;
        if (DCCUMCID.IO_AREA.FUNC_M == 'M') {
            if (DCRCIDEP.PROC_STS != '1') {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CIDEP_MUST_NML;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            C500_INPUT_CHECK();
            if (pgmRtn) return;
            WS_C400_I_AC_NO = DCRCIDEP.DD_AC;
            WS_C400_I_PROD_CD = DCRCIDEP.PROD_CODE;
            C400_CHECK_CUS_AC_INFO();
            if (pgmRtn) return;
            DCRCIDEP.MRM_AMT = DCCUMCID.IO_AREA.MRM_AMT;
            DCRCIDEP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCIDEP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCIDEP.DD_AC = DCCUMCID.IO_AREA.AC_NO;
            DCRCIDEP.PROCS_DATE = DCCUMCID.IO_AREA.PROCS_DT;
            DCRCIDEP.PROCL_DATE = DCCUMCID.IO_AREA.PROCL_DT;
            DCRCIDEP.SMR = DCCUMCID.IO_AREA.SMR;
            DCRCIDEP.LINK_AC_NO = DCCUMCID.IO_AREA.LNK_AC;
        } else {
            if (DCRCIDEP.PROC_STS == '3') {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_STS_OVER;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCUMCID.IO_AREA.CHG_STS == DCRCIDEP.PROC_STS) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_STS_SAME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            DCRCIDEP.PROC_STS = DCCUMCID.IO_AREA.CHG_STS;
            DCRCIDEP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCIDEP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        IBS.CLONE(SCCGWA, DCCUMCID, DCCOLD);
        DCCOLD.IO_AREA.PROC_STS = DCRCIDEP.PROC_STS;
        DCCOLD.IO_AREA.MRM_AMT = DCRCIDEP.MRM_AMT;
        T500_REWRITE_DCTCIDEP();
        if (pgmRtn) return;
    }
    public void B400_PLAN_DEL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.AC_NO);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.OVR_NO);
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.DATA.KEY.AC_NO = DCCUMCID.IO_AREA.AC_NO;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMMST.DATA.OPEN_DP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (DDCIMMST.DATA.OPEN_DP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_BRANCH_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICACCU);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.AC_NO);
        CICACCU.DATA.AGR_NO = DCCUMCID.IO_AREA.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        IBS.init(SCCGWA, DCRCIDEP);
        DCRCIDEP.DD_AC = DCCUMCID.IO_AREA.AC_NO;
        DCRCIDEP.KEY.OVR_NO = DCCUMCID.IO_AREA.OVR_NO;
        T400_READ_DCTCIDEP_UPD();
        if (pgmRtn) return;
        if (DCRCIDEP.PROC_STS == '3') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_STS_OVER;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, DCCUMCID, DCCOLD);
        DCCOLD.IO_AREA.PROCL_DT = DCRCIDEP.PROCL_DATE;
        DCCOLD.IO_AREA.PROC_STS = DCRCIDEP.PROC_STS;
        DCRCIDEP.PROCL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCIDEP.PROC_STS = '3';
        DCRCIDEP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCIDEP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCIDEP.DD_AC = DCCUMCID.IO_AREA.AC_NO;
        T500_REWRITE_DCTCIDEP();
        if (pgmRtn) return;
        WS_CHECK_AC_NO = " ";
        WS_CHECK_AC_NO = DCCUMCID.IO_AREA.AC_NO;
        B110_CHECK_QACAC();
        if (pgmRtn) return;
        WS_SIGNED_ACAC_NO = WS_ACAC_NO;
        CEP.TRC(SCCGWA, DCRCIDEP.LINK_AC_NO);
        WS_CHECK_AC_NO = " ";
        WS_CHECK_AC_NO = DCRCIDEP.LINK_AC_NO;
        B110_CHECK_QACAC();
        if (pgmRtn) return;
        WS_LNK_ACAC_NO = WS_ACAC_NO;
        B410_CANCEL_DD_STSW();
        if (pgmRtn) return;
        B420_REMOVE_OVR_STSW();
        if (pgmRtn) return;
    }
    public void B500_PLAN_ADD() throws IOException,SQLException,Exception {
        C500_INPUT_CHECK();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.DATA.KEY.AC_NO = DCCUMCID.IO_AREA.AC_NO;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
        B800_CHECK_ONLY();
        if (pgmRtn) return;
        WS_C400_I_AC_NO = DCCUMCID.IO_AREA.AC_NO;
        C400_CHECK_CUS_AC_INFO();
        if (pgmRtn) return;
        B510_GET_OVR_NO();
        if (pgmRtn) return;
        B520_SET_DD_STSW();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRCIDEP);
        DCRCIDEP.KEY.OVR_NO = WS_OVR_NO;
        DCRCIDEP.DD_AC = DCCUMCID.IO_AREA.AC_NO;
        DCRCIDEP.PROD_CODE = DCCUMCID.IO_AREA.PROD_CDE;
        DCRCIDEP.CCY = "156";
        DCRCIDEP.CCY_TYPE = '1';
        DCRCIDEP.PROC_STS = '1';
        DCRCIDEP.MRM_AMT = DCCUMCID.IO_AREA.MRM_AMT;
        DCRCIDEP.PROCS_DATE = DCCUMCID.IO_AREA.PROCS_DT;
        DCRCIDEP.PROCL_DATE = DCCUMCID.IO_AREA.PROCL_DT;
        DCRCIDEP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCIDEP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCIDEP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCIDEP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCIDEP.SMR = DCCUMCID.IO_AREA.SMR;
        DCRCIDEP.LINK_AC_NO = DCCUMCID.IO_AREA.LNK_AC;
        T700_WRITE_DCTCIDEP();
        if (pgmRtn) return;
        C100_ADD_OUTPUT();
        if (pgmRtn) return;
    }
    public void B510_GET_OVR_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CICMAGT.DATA.CI_NO = CICACCU.DATA.CI_NO;
        CICMAGT.DATA.AGT_TYP = K_COMP_DEP_CODE;
        CICMAGT.DATA.AGT_STS = 'N';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DCCUMCID.IO_AREA.AC_NO;
        CICMAGT.FUNC = 'A';
        IBS.CALLCPN(SCCGWA, CCI_MAIN_AGT, CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_OVR_NO = " ";
        WS_OVR_NO = CICMAGT.DATA.AGT_NO;
    }
    public void B410_CANCEL_DD_STSW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_SIGNED_ACAC_NO);
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'S';
        DDCIMCYY.DATA.SET_FLG = 'F';
        DDCIMCYY.DATA.KEY.AC = WS_SIGNED_ACAC_NO;
        DDCIMCYY.DATA.STS_CD = "44";
        S000_CALL_DDZIMCYY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_LNK_ACAC_NO);
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'S';
        DDCIMCYY.DATA.SET_FLG = 'F';
        DDCIMCYY.DATA.KEY.AC = WS_LNK_ACAC_NO;
        DDCIMCYY.DATA.STS_CD = "44";
        S000_CALL_DDZIMCYY();
        if (pgmRtn) return;
    }
    public void B420_REMOVE_OVR_STSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'D';
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CICMAGT.DATA.CI_NO = CICACCU.DATA.CI_NO;
        CICMAGT.DATA.ENTY_NO = DCCUMCID.IO_AREA.AC_NO;
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.AGT_TYP = K_COMP_DEP_CODE;
        CICMAGT.DATA.AGT_NO = DCCUMCID.IO_AREA.OVR_NO;
        CICMAGT.DATA.AGT_LVL = 'A';
        CICMAGT.DATA.AGT_STS = 'C';
        IBS.CALLCPN(SCCGWA, CCI_MAIN_AGT, CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B520_SET_DD_STSW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_SIGNED_ACAC_NO);
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'S';
        DDCIMCYY.DATA.SET_FLG = 'O';
        DDCIMCYY.DATA.KEY.AC = WS_SIGNED_ACAC_NO;
        DDCIMCYY.DATA.STS_CD = "44";
        S000_CALL_DDZIMCYY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_LNK_ACAC_NO);
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'S';
        DDCIMCYY.DATA.SET_FLG = 'O';
        DDCIMCYY.DATA.KEY.AC = WS_LNK_ACAC_NO;
        DDCIMCYY.DATA.STS_CD = "44";
        S000_CALL_DDZIMCYY();
        if (pgmRtn) return;
    }
    public void B600_REC_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (DCCUMCID.IO_AREA.FUNC_M == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.AC = DCCUMCID.IO_AREA.AC_NO;
        } else {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.AC = DCRCIDEP.DD_AC;
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = " ";
        BPCPNHIS.INFO.TX_RMK = DCCUMCID.IO_AREA.SMR;
        if (DCCUMCID.IO_AREA.CCY.trim().length() == 0) {
            BPCPNHIS.INFO.CCY = "156";
        } else {
            BPCPNHIS.INFO.CCY = DCCUMCID.IO_AREA.CCY;
        }
        if (DCCUMCID.IO_AREA.CCY_TYP == ' ') {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = DCCUMCID.IO_AREA.CCY_TYP;
        }
        if (BPCPNHIS.INFO.TX_TYP == 'M') {
            BPCPNHIS.INFO.OLD_DAT_PT = DCCOLD;
        }
        BPCPNHIS.INFO.NEW_DAT_PT = DCCUMCID;
        BPCPNHIS.INFO.FMT_ID = "DCZUMCID";
        BPCPNHIS.INFO.FMT_ID_LEN = 869;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B800_CHECK_ONLY() throws IOException,SQLException,Exception {
        WS_SQL_AC_NO = DCCUMCID.IO_AREA.AC_NO;
        WS_SQL_PROC_STS = '1';
        T000_STARTBR_DCTCIDEP();
        if (pgmRtn) return;
        T100_READNEXT_DCTCIDEP();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CIDEP_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T200_ENDBR_DCTCIDEP();
        if (pgmRtn) return;
    }
    public void C000_DETAIL_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_DETAIL_OUTPUT);
        WS_DETAIL_OUTPUT.WS_DTL_AC_NO = DCRCIDEP.DD_AC;
        DCCUMCID.IO_AREA.AC_NO = DCRCIDEP.DD_AC;
        if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
            WS_DETAIL_OUTPUT.WS_DTL_CI_NAME = CICACCU.DATA.AC_ENM;
            DCCUMCID.IO_AREA.AC_NAME = CICACCU.DATA.AC_ENM;
        } else {
            WS_DETAIL_OUTPUT.WS_DTL_CI_NAME = CICACCU.DATA.AC_CNM;
            DCCUMCID.IO_AREA.AC_NAME = CICACCU.DATA.AC_CNM;
        }
        WS_DETAIL_OUTPUT.WS_DTL_PROD_CODE = DCRCIDEP.PROD_CODE;
        DCCUMCID.IO_AREA.PROD_CDE = DCRCIDEP.PROD_CODE;
        WS_DETAIL_OUTPUT.WS_DTL_CCY = DCRCIDEP.CCY;
        DCCUMCID.IO_AREA.CCY = DCRCIDEP.CCY;
        WS_DETAIL_OUTPUT.WS_DTL_CCY_TYPE = DCRCIDEP.CCY_TYPE;
        DCCUMCID.IO_AREA.CCY_TYP = DCRCIDEP.CCY_TYPE;
        WS_DETAIL_OUTPUT.WS_DTL_MRM_AMT = DCRCIDEP.MRM_AMT;
        DCCUMCID.IO_AREA.MRM_AMT = DCRCIDEP.MRM_AMT;
        WS_DETAIL_OUTPUT.WS_DTL_PROCS_DATE = DCRCIDEP.PROCS_DATE;
        DCCUMCID.IO_AREA.PROCS_DT = DCRCIDEP.PROCS_DATE;
        WS_DETAIL_OUTPUT.WS_DTL_PROCL_DATE = DCRCIDEP.PROCL_DATE;
        DCCUMCID.IO_AREA.PROCL_DT = DCRCIDEP.PROCL_DATE;
        WS_DETAIL_OUTPUT.WS_DTL_PROC_STS = DCRCIDEP.PROC_STS;
        DCCUMCID.IO_AREA.PROC_STS = DCRCIDEP.PROC_STS;
        DCCUMCID.IO_AREA.LNK_AC = DCRCIDEP.LINK_AC_NO;
        WS_DETAIL_OUTPUT.WS_DTL_LNK_AC = DCRCIDEP.LINK_AC_NO;
        DCCUMCID.IO_AREA.OVR_NO = DCRCIDEP.KEY.OVR_NO;
        WS_DETAIL_OUTPUT.WS_DTL_OVR_NO = DCRCIDEP.KEY.OVR_NO;
        DCCUMCID.IO_AREA.SMR = DCRCIDEP.SMR;
        WS_DETAIL_OUTPUT.WS_DTL_SMR = DCRCIDEP.SMR;
        CEP.TRC(SCCGWA, WS_DETAIL_OUTPUT);
    }
    public void C100_ADD_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_ADD_OUTPUT);
        WS_ADD_OUTPUT.WS_ADD_OVR_NO = DCRCIDEP.KEY.OVR_NO;
        WS_ADD_OUTPUT.WS_ADD_DD_AC = DCRCIDEP.DD_AC;
        WS_ADD_OUTPUT.WS_ADD_PROD_CODE = DCRCIDEP.PROD_CODE;
        WS_ADD_OUTPUT.WS_ADD_CCY = DCRCIDEP.CCY;
        WS_ADD_OUTPUT.WS_ADD_CCY_TYPE = DCRCIDEP.CCY_TYPE;
        WS_ADD_OUTPUT.WS_ADD_LINK_AC_NO = DCRCIDEP.LINK_AC_NO;
        WS_ADD_OUTPUT.WS_ADD_PROCS_DATE = DCRCIDEP.PROCS_DATE;
        WS_ADD_OUTPUT.WS_ADD_PROCL_DATE = DCRCIDEP.PROCL_DATE;
        WS_ADD_OUTPUT.WS_ADD_CRT_DT = DCRCIDEP.CRT_DATE;
        WS_ADD_OUTPUT.WS_ADD_SMR = DCRCIDEP.SMR;
        CEP.TRC(SCCGWA, WS_ADD_OUTPUT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_A;
        CEP.TRC(SCCGWA, SCCFMT.FMTID);
        SCCFMT.DATA_PTR = WS_ADD_OUTPUT;
        SCCFMT.DATA_LEN = 272;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void C200_OUTPUT_INI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = K_MAX_COLS;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void C300_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BROWSE_OUTPUT);
        WS_BROWSE_OUTPUT.WS_B_OVR_NO = DCRCIDEP.KEY.OVR_NO;
        WS_BROWSE_OUTPUT.WS_B_PROD_CODE = DCRCIDEP.PROD_CODE;
        WS_BROWSE_OUTPUT.WS_B_PROD_DES = WS_COM_PROD_DESC;
        WS_BROWSE_OUTPUT.WS_STS = DCRCIDEP.PROC_STS;
        WS_BROWSE_OUTPUT.WS_B_CRT_DT = DCRCIDEP.CRT_DATE;
        WS_BROWSE_OUTPUT.WS_B_DD_AC = DCRCIDEP.DD_AC;
        WS_BROWSE_OUTPUT.WS_B_CCY = DCRCIDEP.CCY;
        WS_BROWSE_OUTPUT.WS_B_CCY_TYPE = DCRCIDEP.CCY_TYPE;
        WS_BROWSE_OUTPUT.WS_B_PROCS_DATE = DCRCIDEP.PROCS_DATE;
        WS_BROWSE_OUTPUT.WS_B_PROCL_DATE = DCRCIDEP.PROCL_DATE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROWSE_OUTPUT);
        SCCMPAG.DATA_LEN = 182;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void C400_CHECK_CUS_AC_INFO() throws IOException,SQLException,Exception {
        C400_10_CHECK_CUS();
        if (pgmRtn) return;
        C400_30_CHECK_AC_STS();
        if (pgmRtn) return;
    }
    public void C400_10_CHECK_CUS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CEP.TRC(SCCGWA, WS_C400_I_AC_NO);
        CICACCU.DATA.AGR_NO = WS_C400_I_AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        if (DCCUMCID.IO_AREA.FUNC_M == 'A' 
            || DCCUMCID.IO_AREA.FUNC_M == 'M') {
            if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
            JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
            if (CICACCU.DATA.CI_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CUSTOM_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
            JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
            if (CICACCU.DATA.CI_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CUSTOM_FORBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
            JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
            if (CICACCU.DATA.CI_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CUSTOM_DIED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCRICPRD.CI_TYP == '2') {
            if (CICACCU.DATA.CI_TYP != '2' 
                && CICACCU.DATA.CI_TYP != '3') {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CI_TYP_NOT_SPT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.LNK_AC);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DCCUMCID.IO_AREA.LNK_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, WS_CI_NO);
        if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(WS_CI_NO)) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CI_MST_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void C400_20_GET_PROD_PARA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, WS_C400_I_PROD_CD);
        BPCPQPRD.PRDT_CODE = WS_C400_I_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_BASE_CDE_NULL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, DCRICPRD);
        BPRPRMT.KEY.TYP = K_PRDPR_TYPE;
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = "999999999" + BPRPRMT.KEY.CD.substring(9);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        if (BPCPQPRD.PARM_CODE == null) BPCPQPRD.PARM_CODE = "";
        JIBS_tmp_int = BPCPQPRD.PARM_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCPQPRD.PARM_CODE += " ";
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 10 - 1) + BPCPQPRD.PARM_CODE + BPRPRMT.KEY.CD.substring(10 + 10 - 1);
        BPRPRMT.CDESC = " ";
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRICPRD);
        WS_PROD_DD = DCRICPRD.PROD_DD;
        WS_COM_PROD_DESC = DCRICPRD.PROD_DSC;
        CEP.TRC(SCCGWA, WS_PROD_DD);
        CEP.TRC(SCCGWA, WS_COM_PROD_DESC);
    }
    public void C400_30_CHECK_AC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMMST);
        CEP.TRC(SCCGWA, WS_C400_I_AC_NO);
        DDCIMMST.DATA.KEY.AC_NO = WS_C400_I_AC_NO;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
        if (DDCIMMST.DATA.AC_STS != 'N') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_AC_STS_NOTNML;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCIMMST.DATA.OPEN_DP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (DDCIMMST.DATA.OPEN_DP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_BRANCH_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.LNK_AC);
        CEP.TRC(SCCGWA, DCRCIDEP.LINK_AC_NO);
        IBS.init(SCCGWA, DDCIMMST);
        if (DCCUMCID.IO_AREA.FUNC_M == 'A') {
            DDCIMMST.DATA.KEY.AC_NO = DCCUMCID.IO_AREA.LNK_AC;
        } else {
            DDCIMMST.DATA.KEY.AC_NO = DCRCIDEP.LINK_AC_NO;
        }
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
        if (DDCIMMST.DATA.AC_STS != 'N') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_AC_STS_NOTNML;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void C500_INPUT_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.FUNC_M);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.AC_NO);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.PROD_CDE);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.PROCS_DT);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.PROCL_DT);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.MRM_AMT);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.CCY);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.CCY_TYP);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.LNK_AC);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.POST_OPT);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.DEP_RATE);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.OD_RATE);
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.SMR);
        CEP.TRC(SCCGWA, DCRCIDEP.PROD_CODE);
        if (DCCUMCID.IO_AREA.FUNC_M == 'A') {
            WS_C400_I_PROD_CD = DCCUMCID.IO_AREA.PROD_CDE;
        } else {
            WS_C400_I_PROD_CD = DCRCIDEP.PROD_CODE;
        }
        C400_20_GET_PROD_PARA();
        if (pgmRtn) return;
        WS_TRIG_AMT = DCCUMCID.IO_AREA.MRM_AMT;
        IBS.CPY2CLS(SCCGWA, WS_TRIG_AMT+"", WS_TRIG_AMT1);
        if (DCRICPRD.INT_MTH == '1') {
            if (WS_TRIG_AMT1.WS_REMAINDER != 0) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MRM_MUST_WAN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCRICPRD.INT_MTH == '2') {
            JIBS_tmp_str[0] = "" + WS_TRIG_AMT1.WS_REMAINDER;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (!JIBS_tmp_str[0].substring(2 - 1, 2 + 5 - 1).equalsIgnoreCase("0")) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MRM_MUST_QIAN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCRICPRD.INT_MTH == '3') {
            JIBS_tmp_str[0] = "" + WS_TRIG_AMT1.WS_REMAINDER;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (!JIBS_tmp_str[0].substring(3 - 1, 3 + 4 - 1).equalsIgnoreCase("0")) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MRM_MUST_BAI;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCRICPRD.INT_MTH == '4') {
            JIBS_tmp_str[0] = "" + WS_TRIG_AMT1.WS_REMAINDER;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (!JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("0")) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MRM_MUST_ZZZ;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_CHECK_AC_NO = " ";
        WS_CHECK_AC_NO = DCCUMCID.IO_AREA.AC_NO;
        B110_CHECK_QACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'I';
        DDCIMCYY.DATA.KEY.AC = WS_ACAC_NO;
        WS_SIGNED_ACAC_NO = WS_ACAC_NO;
        CEP.TRC(SCCGWA, DDCIMCYY.DATA.KEY.AC);
        S000_CALL_DDZIMCYY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMCYY.DATA.STS_WORD);
        if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
        JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
        if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
        JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
        if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
        JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
        if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
        JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
        if (DDCIMCYY.DATA.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIMCYY.DATA.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIMCYY.DATA.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIMCYY.DATA.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_AC_STS_ABNORMAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUMCID.IO_AREA.FUNC_M == 'A') {
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1")) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_GROUP_NOT_ADD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD.substring(50 - 1, 50 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMCYY.DATA.STS_WORD.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMCYY.DATA.STS_WORD.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMCYY.DATA.STS_WORD.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMCYY.DATA.STS_WORD.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMCYY.DATA.STS_WORD.substring(55 - 1, 55 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMCYY.DATA.STS_WORD.substring(56 - 1, 56 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMCYY.DATA.STS_WORD.substring(57 - 1, 57 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMCYY.DATA.STS_WORD.substring(58 - 1, 58 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMCYY.DATA.STS_WORD.substring(59 - 1, 59 + 1 - 1).equalsIgnoreCase("1")) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_AC_ALR_SIGNED3;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DCCUMCID.IO_AREA.LNK_AC);
        WS_CHECK_AC_NO = " ";
        WS_CHECK_AC_NO = DCCUMCID.IO_AREA.LNK_AC;
        B110_CHECK_QACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'I';
        DDCIMCYY.DATA.KEY.AC = WS_ACAC_NO;
        WS_LNK_ACAC_NO = WS_ACAC_NO;
        CEP.TRC(SCCGWA, DDCIMCYY.DATA.KEY.AC);
        S000_CALL_DDZIMCYY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMCYY.DATA.STS_WORD);
        if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
        JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
        if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
        JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
        if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
        JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
        if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
        JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
        if (DDCIMCYY.DATA.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIMCYY.DATA.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIMCYY.DATA.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIMCYY.DATA.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_AC_STS_ABNORMAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DDZIMMST, DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_I_NFIN_M_CCY, DDCIMCCY);
        CEP.TRC(SCCGWA, DDCIMCCY.RC.RC_CODE);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DDCIMCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF ", DCCPACTY);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CCI_INQ_ACCU, CICACCU);
        if (CICACCU.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMCYY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-ZIMCYY-AC", DDCIMCYY);
        CEP.TRC(SCCGWA, DDCIMCYY.RC.RC_CODE);
        if (DDCIMCYY.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DDCIMCYY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPFHIS, BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CBP_P_QUERY_PRDT_INFO, BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_MSG_ID, DCCUMCID.O_AREA.MSG_ID);
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTCIDEP() throws IOException,SQLException,Exception {
        DCTCIDEP_BR.rp = new DBParm();
        DCTCIDEP_BR.rp.TableName = "DCTCIDEP";
        DCTCIDEP_BR.rp.where = "DD_AC = :WS_SQL_AC_NO "
            + "AND ( :WS_SQL_PROC_STS = ' ' "
            + "OR PROC_STS = :WS_SQL_PROC_STS ) "
            + "AND ( :WS_SQL_OVR_NO = ' ' "
            + "OR OVR_NO = :WS_SQL_OVR_NO )";
        IBS.STARTBR(SCCGWA, DCRCIDEP, this, DCTCIDEP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCIDEP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T100_READNEXT_DCTCIDEP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCIDEP, this, DCTCIDEP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCIDEP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T200_ENDBR_DCTCIDEP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCIDEP_BR);
    }
    public void T300_READ_DCTCIDEP() throws IOException,SQLException,Exception {
        DCTCIDEP_RD = new DBParm();
        DCTCIDEP_RD.TableName = "DCTCIDEP";
        IBS.READ(SCCGWA, DCRCIDEP, DCTCIDEP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_NO_RSLT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCIDEP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T400_READ_DCTCIDEP_UPD() throws IOException,SQLException,Exception {
        DCTCIDEP_RD = new DBParm();
        DCTCIDEP_RD.TableName = "DCTCIDEP";
        DCTCIDEP_RD.upd = true;
        IBS.READ(SCCGWA, DCRCIDEP, DCTCIDEP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_NO_RSLT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCIDEP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T500_REWRITE_DCTCIDEP() throws IOException,SQLException,Exception {
        DCTCIDEP_RD = new DBParm();
        DCTCIDEP_RD.TableName = "DCTCIDEP";
        IBS.REWRITE(SCCGWA, DCRCIDEP, DCTCIDEP_RD);
    }
    public void T700_WRITE_DCTCIDEP() throws IOException,SQLException,Exception {
        DCTCIDEP_RD = new DBParm();
        DCTCIDEP_RD.TableName = "DCTCIDEP";
        IBS.WRITE(SCCGWA, DCRCIDEP, DCTCIDEP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCIDEP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            if (WS_MSG_ID == null) WS_MSG_ID = "";
            JIBS_tmp_int = WS_MSG_ID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSG_ID += " ";
            WS_MSG_ID = "SC" + WS_MSG_ID.substring(2);
            if (WS_MSG_ID == null) WS_MSG_ID = "";
            JIBS_tmp_int = WS_MSG_ID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSG_ID += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_MSG_ID = WS_MSG_ID.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_MSG_ID.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
        if (DCCUMCID.O_AREA.MSG_ID.RC != 0) {
            CEP.TRC(SCCGWA, "DCCUMCID=");
            CEP.TRC(SCCGWA, DCCUMCID);
        }
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZPOSCS {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DCTCDFEE_RD;
    DBParm DCTPOSRD_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_POSCS_ID_NO_6 = " ";
    String WS_CARD_OWN_IDNO_6 = " ";
    String WS_CUST_NAME = " ";
    short WS_LEN = 0;
    String WS_CARD_NO = " ";
    int WS_UPD_DATE = 0;
    int WS_TOT_VOL = 0;
    int WS_K = 0;
    int WS_L = 0;
    int WS_M = 0;
    DCZPOSCS_WS_IDNO_ARRAY1[] WS_IDNO_ARRAY1 = new DCZPOSCS_WS_IDNO_ARRAY1[6];
    DCZPOSCS_WS_IDNO_ARRAY2[] WS_IDNO_ARRAY2 = new DCZPOSCS_WS_IDNO_ARRAY2[6];
    String WS_CHECK_ID_TYP = " ";
    String WS_CHECK_ID_NO = " ";
    String WS_FEE_PROD = " ";
    DCZPOSCS_WS_OUTPUT WS_OUTPUT = new DCZPOSCS_WS_OUTPUT();
    char WS_TBL_FLAG = ' ';
    char WS_HAVE_CARD_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRPOSRD DCRPOSRD = new DCRPOSRD();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    CICMCNT CICMCNT = new CICMCNT();
    BPCFFCON BPCFFCON = new BPCFFCON();
    DCRCDFEE DCRCDFEE = new DCRCDFEE();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCPOSCS DCCPOSCS;
    SCCGANS SCCGANS;
    CICCASHE SCCPFMCE;
    public DCZPOSCS() {
        for (int i=0;i<6;i++) WS_IDNO_ARRAY1[i] = new DCZPOSCS_WS_IDNO_ARRAY1();
        for (int i=0;i<6;i++) WS_IDNO_ARRAY2[i] = new DCZPOSCS_WS_IDNO_ARRAY2();
    }
    public void MP(SCCGWA SCCGWA, DCCPOSCS DCCPOSCS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCPOSCS = DCCPOSCS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZPOSCS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGANS = (SCCGANS) GWA_BP_AREA.STAT_AREA_PTR;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0263010")) {
            SCCPFMCE = (CICCASHE) SCCGWA.COMM_AREA.PERFORMACE_PTR;
            SCCPFMCE.CUST_FLAG = 'Y';
        }
        CEP.TRC(SCCGWA, SCCGANS.REQ_REF);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_CARD_INFO();
        if (pgmRtn) return;
        B002_CHECK_CUST_INFO();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
        } else {
            B600_REVERSE_CHECK();
            if (pgmRtn) return;
        }
        B200_DRAWING_PROCESS();
        if (pgmRtn) return;
        B300_GEN_VCH_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPOSCS.INPUT.TXN_TYP);
        if (DCCPOSCS.INPUT.TXN_TYP.equalsIgnoreCase("18")) {
            if (DCCPOSCS.INPUT.FEE_CODE.trim().length() == 0 
                && DCCPOSCS.INPUT.FEE_AMT == 0) {
                B082_GET_PRE_FEE_CODE();
                if (pgmRtn) return;
                if (BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_CODE.trim().length() > 0) {
                    B083_WRITE_FEE_TABLE();
                    if (pgmRtn) return;
                    B084_GET_FEE_TOT_VOL();
                    if (pgmRtn) return;
                    B085_GET_FEE_CODE_AMT();
                    if (pgmRtn) return;
                    B086_FEE_PROCESS();
                    if (pgmRtn) return;
                }
            } else {
                B081_FEE_PROCESS();
                if (pgmRtn) return;
            }
        } else {
            B900_FEE_PROCESS();
            if (pgmRtn) return;
        }
        B700_DCRPOSRD_PROCESS();
        if (pgmRtn) return;
        B500_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B001_CHECK_CARD_INFO() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("0263010") 
            && !JIBS_tmp_str[0].equalsIgnoreCase("0263019")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_APPLY_CODE_MISMATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCPOSCS.INPUT.TRK2_DAT.trim().length() == 0 
            && DCCPOSCS.INPUT.TRK3_DAT.trim().length() == 0) {
            WS_HAVE_CARD_FLG = 'N';
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.FUNC = '1';
            DCCPACTY.INPUT.AC = DCCPOSCS.INPUT.CARD_NO;
            DCCPACTY.INPUT.SEQ = DCCPOSCS.INPUT.SEQ;
            CEP.TRC(SCCGWA, DCCPOSCS.INPUT.CARD_NO);
            S000_CALL_DCZPACTY();
            if (pgmRtn) return;
            if (DCCPACTY.OUTPUT.N_CARD_NO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCPACTY.OUTPUT.STD_AC.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NO_CUR_ACCOUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_CARD_NO = DCCPACTY.OUTPUT.N_CARD_NO;
        } else {
            WS_HAVE_CARD_FLG = 'Y';
            WS_CARD_NO = DCCPOSCS.INPUT.CARD_NO;
        }
        CEP.TRC(SCCGWA, DCCPOSCS.INPUT.CARD_NO);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.N_CARD_NO);
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = WS_CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.CARD_OWN_CINO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.ADSC_TYPE == 'P') {
            WS_CHECK_ID_TYP = DCCUCINF.CARD_OWN_IDTYP;
            WS_CHECK_ID_NO = DCCUCINF.CARD_OWN_IDNO;
        } else {
            WS_CHECK_ID_TYP = DCCUCINF.CARD_HLDR_IDTYP;
            WS_CHECK_ID_NO = DCCUCINF.CARD_HLDR_IDNO;
        }
        CEP.TRC(SCCGWA, DCCUCINF.PSW_FLG);
        CEP.TRC(SCCGWA, DCCUCINF.DD_PROD_CD);
        if (DCCUCINF.PSW_FLG == 'N') {
            if (DCCPOSCS.INPUT.PSW.trim().length() > 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_NOT_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B002_CHECK_CUST_INFO() throws IOException,SQLException,Exception {
        if (DCCPOSCS.INPUT.ID_INFO_CHK_FLG == 'Y') {
            CEP.TRC(SCCGWA, DCCPOSCS.INPUT.ID_LEN_CHK_FLG);
            if (DCCPOSCS.INPUT.ID_LEN_CHK_FLG != 'L' 
                && DCCPOSCS.INPUT.ID_LEN_CHK_FLG != 'S') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ID_CHK_FLG_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCPOSCS.INPUT.ID_TYP.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_ID_TYPE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCPOSCS.INPUT.ID_NO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_ID_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCPOSCS.INPUT.CUS_NAME_CHK_FLG == 'Y') {
            if (DCCPOSCS.INPUT.CUS_NAME.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CI_NAME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCPOSCS.INPUT.TEL_CHK_FLG == 'Y') {
            if (DCCPOSCS.INPUT.TEL.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CONT_TEL_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCPOSCS.INPUT.ID_INFO_CHK_FLG == 'Y') {
            if (!DCCPOSCS.INPUT.ID_TYP.equalsIgnoreCase(WS_CHECK_ID_TYP)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IDTYP_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCPOSCS.INPUT.ID_LEN_CHK_FLG == 'L') {
                if (!DCCPOSCS.INPUT.ID_NO.equalsIgnoreCase(WS_CHECK_ID_NO)) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IDNO_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                B003_GET_ID_LAST_SIXLEN();
                if (pgmRtn) return;
            }
        }
        if (DCCPOSCS.INPUT.CUS_NAME_CHK_FLG == 'Y') {
            if (DCCUCINF.CARD_OWN_CNM.trim().length() > 0) {
                WS_CUST_NAME = DCCUCINF.CARD_OWN_CNM;
            } else {
                WS_CUST_NAME = DCCUCINF.CARD_OWN_ENM;
            }
            if (!DCCPOSCS.INPUT.CUS_NAME.equalsIgnoreCase(WS_CUST_NAME)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_NAME_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCPOSCS.INPUT.TEL_CHK_FLG == 'Y') {
            IBS.init(SCCGWA, CICMCNT);
            CICMCNT.FUNC = 'T';
            CICMCNT.CI_NO = DCCUCINF.CARD_OWN_CINO;
            CICMCNT.DATA.SINGLE_DATA.S_CNT_TYPE = "15";
            S000_CALL_CIZMCNT();
            if (pgmRtn) return;
            if (!DCCPOSCS.INPUT.TEL.equalsIgnoreCase(CICMCNT.DATA.SINGLE_DATA.S_CNT_INFO)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TEL_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B003_GET_ID_LAST_SIXLEN() throws IOException,SQLException,Exception {
        WS_L = 6;
        for (WS_K = 30; WS_K != 0 
            && WS_L != 0; WS_K += -1) {
            if (DCCPOSCS.INPUT.ID_NO == null) DCCPOSCS.INPUT.ID_NO = "";
            JIBS_tmp_int = DCCPOSCS.INPUT.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) DCCPOSCS.INPUT.ID_NO += " ";
            if (DCCPOSCS.INPUT.ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("0") 
                || DCCPOSCS.INPUT.ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("1") 
                || DCCPOSCS.INPUT.ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("2") 
                || DCCPOSCS.INPUT.ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("3") 
                || DCCPOSCS.INPUT.ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("4") 
                || DCCPOSCS.INPUT.ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("5") 
                || DCCPOSCS.INPUT.ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("6") 
                || DCCPOSCS.INPUT.ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("7") 
                || DCCPOSCS.INPUT.ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("8") 
                || DCCPOSCS.INPUT.ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("9")) {
                if (DCCPOSCS.INPUT.ID_NO == null) DCCPOSCS.INPUT.ID_NO = "";
                JIBS_tmp_int = DCCPOSCS.INPUT.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCPOSCS.INPUT.ID_NO += " ";
                WS_IDNO_ARRAY1[WS_L-1].WS_IDNO_AR1 = DCCPOSCS.INPUT.ID_NO.substring(WS_K - 1, WS_K + 1 - 1).charAt(0);
                WS_L = WS_L - 1;
                CEP.TRC(SCCGWA, WS_L);
            }
        }
        if (WS_L == 1) {
            WS_IDNO_ARRAY1[1-1].WS_IDNO_AR1 = '0';
        }
        CEP.TRC(SCCGWA, WS_IDNO_ARRAY1[1-1].WS_IDNO_AR1);
        CEP.TRC(SCCGWA, WS_IDNO_ARRAY1[2-1].WS_IDNO_AR1);
        CEP.TRC(SCCGWA, WS_IDNO_ARRAY1[3-1].WS_IDNO_AR1);
        CEP.TRC(SCCGWA, WS_IDNO_ARRAY1[4-1].WS_IDNO_AR1);
        CEP.TRC(SCCGWA, WS_IDNO_ARRAY1[5-1].WS_IDNO_AR1);
        CEP.TRC(SCCGWA, WS_IDNO_ARRAY1[6-1].WS_IDNO_AR1);
        WS_L = 6;
        for (WS_K = 30; WS_K != 0 
            && WS_L != 0; WS_K += -1) {
            if (WS_CHECK_ID_NO == null) WS_CHECK_ID_NO = "";
            JIBS_tmp_int = WS_CHECK_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_ID_NO += " ";
            if (WS_CHECK_ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("0") 
                || WS_CHECK_ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("1") 
                || WS_CHECK_ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("2") 
                || WS_CHECK_ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("3") 
                || WS_CHECK_ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("4") 
                || WS_CHECK_ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("5") 
                || WS_CHECK_ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("6") 
                || WS_CHECK_ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("7") 
                || WS_CHECK_ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("8") 
                || WS_CHECK_ID_NO.substring(WS_K - 1, WS_K + 1 - 1).equalsIgnoreCase("9")) {
                if (WS_CHECK_ID_NO == null) WS_CHECK_ID_NO = "";
                JIBS_tmp_int = WS_CHECK_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_CHECK_ID_NO += " ";
                WS_IDNO_ARRAY2[WS_L-1].WS_IDNO_AR2 = WS_CHECK_ID_NO.substring(WS_K - 1, WS_K + 1 - 1).charAt(0);
                WS_L = WS_L - 1;
                CEP.TRC(SCCGWA, WS_L);
            }
        }
        if (WS_L == 1) {
            WS_IDNO_ARRAY2[1-1].WS_IDNO_AR2 = '0';
        }
        CEP.TRC(SCCGWA, WS_IDNO_ARRAY2[1-1].WS_IDNO_AR2);
        CEP.TRC(SCCGWA, WS_IDNO_ARRAY2[2-1].WS_IDNO_AR2);
        CEP.TRC(SCCGWA, WS_IDNO_ARRAY2[3-1].WS_IDNO_AR2);
        CEP.TRC(SCCGWA, WS_IDNO_ARRAY2[4-1].WS_IDNO_AR2);
        CEP.TRC(SCCGWA, WS_IDNO_ARRAY2[5-1].WS_IDNO_AR2);
        CEP.TRC(SCCGWA, WS_IDNO_ARRAY2[6-1].WS_IDNO_AR2);
        for (WS_M = 1; WS_M <= 6; WS_M += 1) {
            if (WS_IDNO_ARRAY1[WS_M-1].WS_IDNO_AR1 != WS_IDNO_ARRAY2[WS_M-1].WS_IDNO_AR2) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IDNO_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCPOSCS.INPUT.TRK2_DAT);
        CEP.TRC(SCCGWA, DCCPOSCS.INPUT.TRK3_DAT);
        IBS.init(SCCGWA, DCCPFTCK);
        DCCPFTCK.VAL.CARD_NO = WS_CARD_NO;
        DCCPFTCK.VAL.REGN_TYP = DCCPOSCS.INPUT.G_TYP;
        CEP.TRC(SCCGWA, DCCPOSCS.INPUT.TXN_TYP);
        if (DCCPOSCS.INPUT.TXN_TYP.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXN_TYPE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCCPFTCK.VAL.TXN_TYPE = DCCPOSCS.INPUT.TXN_TYP;
        DCCPFTCK.VAL.TXN_CCY = DCCPOSCS.INPUT.TXN_CCY;
        DCCPFTCK.VAL.TXN_AMT = DCCPOSCS.INPUT.AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0263019")) {
            DCCPFTCK.CANCEL_FLG = 'Y';
        }
        DCCPFTCK.TRK2_DAT = DCCPOSCS.INPUT.TRK2_DAT;
        DCCPFTCK.TRK3_DAT = DCCPOSCS.INPUT.TRK3_DAT;
        S000_CALL_DCZPFTCK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPOSCS.INPUT.CVN2);
        IBS.init(SCCGWA, DCCPCDCK);
        DCCPCDCK.OLD_CARD_NO = DCCPOSCS.INPUT.CARD_NO;
        DCCPCDCK.CARD_NO = WS_CARD_NO;
        DCCPCDCK.CARD_CVN2 = DCCPOSCS.INPUT.CVN2;
        if (DCCPOSCS.INPUT.PSW.trim().length() == 0) {
            if (DCCPOSCS.INPUT.TRK2_DAT.trim().length() == 0 
                && DCCPOSCS.INPUT.TRK3_DAT.trim().length() == 0) {
                DCCPCDCK.FUNC_CODE = 'N';
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            } else {
                DCCPCDCK.FUNC_CODE = 'T';
                DCCPCDCK.TRK2_DAT = DCCPOSCS.INPUT.TRK2_DAT;
                DCCPCDCK.TRK3_DAT = DCCPOSCS.INPUT.TRK3_DAT;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            }
        } else {
            if (DCCPOSCS.INPUT.TRK2_DAT.trim().length() == 0 
                && DCCPOSCS.INPUT.TRK3_DAT.trim().length() == 0) {
                DCCPCDCK.FUNC_CODE = 'P';
                DCCPCDCK.CARD_PSW = DCCPOSCS.INPUT.PSW;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            } else {
                DCCPCDCK.FUNC_CODE = 'B';
                DCCPCDCK.CARD_PSW = DCCPOSCS.INPUT.PSW;
                DCCPCDCK.TRK2_DAT = DCCPOSCS.INPUT.TRK2_DAT;
                DCCPCDCK.TRK3_DAT = DCCPOSCS.INPUT.TRK3_DAT;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            }
        }
        if (DCCPOSCS.INPUT.FEE_CODE.trim().length() == 0 
            && DCCPOSCS.INPUT.FEE_AMT != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FEE_CODE_SYN_FEE_AMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_DRAWING_PROCESS() throws IOException,SQLException,Exception {
        if (WS_HAVE_CARD_FLG == 'Y') {
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.FUNC = '2';
            DCCPACTY.INPUT.AC = WS_CARD_NO;
            DCCPACTY.INPUT.SEQ = DCCPOSCS.INPUT.SEQ;
            S000_CALL_DCZPACTY();
            if (pgmRtn) return;
            if (DCCPACTY.OUTPUT.N_CARD_NO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCPACTY.OUTPUT.STD_AC.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NO_CUR_ACCOUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.AC = DCCPACTY.OUTPUT.STD_AC;
        DDCUDRAC.CARD_NO = WS_CARD_NO;
        DDCUDRAC.TX_AMT = DCCPOSCS.INPUT.AMT;
        DDCUDRAC.CCY = DCCPOSCS.INPUT.TXN_CCY;
        DDCUDRAC.CCY_TYPE = '1';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.RLT_REF_NO = DCCPOSCS.INPUT.MERCH_CD;
        DDCUDRAC.RLT_AC = DCCPOSCS.INPUT.MERCH_AC;
        DDCUDRAC.RLT_AC_NAME = DCCPOSCS.INPUT.MERCH_NM;
        if (DCCPOSCS.INPUT.TX_MMO.trim().length() == 0) {
            DDCUDRAC.TX_MMO = "018";
        } else {
            DDCUDRAC.TX_MMO = DCCPOSCS.INPUT.TX_MMO;
        }
        DDCUDRAC.REMARKS = "POS PURCHASE";
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B300_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        if (DCCPOSCS.INPUT.TX_MMO.trim().length() == 0) {
            BPCPOEWA.DATA.NARR_CD = "GTQ";
        } else {
            BPCPOEWA.DATA.NARR_CD = DCCPOSCS.INPUT.TX_MMO;
        }
        BPCPOEWA.DATA.CNTR_TYPE = DCCPOSCS.INPUT.PRDMO_CD;
        BPCPOEWA.DATA.PROD_CODE = DCCPOSCS.INPUT.PROD_CD;
        BPCPOEWA.DATA.EVENT_CODE = DCCPOSCS.INPUT.EVENT_CD;
        if (DCCPOSCS.INPUT.ACCT_BR == 199999) {
            BPCPOEWA.DATA.BR_OLD = DCCUCINF.ISSU_BR;
            BPCPOEWA.DATA.BR_NEW = DCCUCINF.ISSU_BR;
        } else {
            BPCPOEWA.DATA.BR_OLD = DCCPOSCS.INPUT.ACCT_BR;
            BPCPOEWA.DATA.BR_NEW = DCCPOSCS.INPUT.ACCT_BR;
        }
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DCCPOSCS.INPUT.TXN_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = DCCPOSCS.INPUT.AMT;
        BPCPOEWA.DATA.AC_NO = DCCPACTY.OUTPUT.STD_AC;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B700_DCRPOSRD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPOSRD);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.VCH_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        DCRPOSRD.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        if (SCCGWA.COMM_AREA.VCH_NO.trim().length() == 0) DCRPOSRD.KEY.JRNNO = 0;
        else DCRPOSRD.KEY.JRNNO = Long.parseLong(SCCGWA.COMM_AREA.VCH_NO);
        CEP.TRC(SCCGWA, DCRPOSRD.KEY.AC_DT);
        CEP.TRC(SCCGWA, DCRPOSRD.KEY.JRNNO);
        DCRPOSRD.AC_NO = DCCPACTY.OUTPUT.STD_AC;
        DCRPOSRD.TR_AMT = DCCPOSCS.INPUT.AMT;
        DCRPOSRD.CARD_NO = WS_CARD_NO;
        DCRPOSRD.TR_TM = SCCGWA.COMM_AREA.TR_TIME;
        DCRPOSRD.TR_DT = SCCGWA.COMM_AREA.TR_DATE;
        DCRPOSRD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPOSRD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPOSRD.TR_CCY = DCCPOSCS.INPUT.TXN_CCY;
        DCRPOSRD.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRPOSRD.TR_FLG = 'N';
        DCRPOSRD.LNK_FLG = 'N';
        DCRPOSRD.TR_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        DCRPOSRD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRPOSRD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTPOSRD();
        if (pgmRtn) return;
    }
    public void B500_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_OUTPUT.WS_O_CARD_NO = DCCPOSCS.INPUT.CARD_NO;
        WS_OUTPUT.WS_O_SEQ = DCCPOSCS.INPUT.SEQ;
        WS_OUTPUT.WS_O_TXN_CCY = DCCPOSCS.INPUT.TXN_CCY;
        WS_OUTPUT.WS_O_AMT = DCCPOSCS.INPUT.AMT;
        WS_OUTPUT.WS_O_G_TYP = DCCPOSCS.INPUT.G_TYP;
        WS_OUTPUT.WS_O_MERCH_CD = DCCPOSCS.INPUT.MERCH_CD;
        WS_OUTPUT.WS_O_MERCH_AC = DCCPOSCS.INPUT.MERCH_AC;
        WS_OUTPUT.WS_O_MERCH_NM = DCCPOSCS.INPUT.MERCH_NM;
        WS_OUTPUT.WS_O_RMK = DCCPOSCS.INPUT.RMK;
        WS_OUTPUT.WS_O_CRO_BANK_FLG = DCCPOSCS.INPUT.CRO_BANK_FLG;
        WS_OUTPUT.WS_ISSU_BR = DCCUCINF.ISSU_BR;
        SCCFMT.FMTID = "DC310";
        CEP.TRC(SCCGWA, WS_OUTPUT);
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 2844;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B600_REVERSE_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPFTCK);
        DCCPFTCK.VAL.CARD_NO = WS_CARD_NO;
        DCCPFTCK.VAL.REGN_TYP = DCCPOSCS.INPUT.G_TYP;
        CEP.TRC(SCCGWA, DCCPOSCS.INPUT.TXN_TYP);
        if (DCCPOSCS.INPUT.TXN_TYP.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXN_TYPE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCCPFTCK.VAL.TXN_TYPE = DCCPOSCS.INPUT.TXN_TYP;
        DCCPFTCK.VAL.TXN_CCY = DCCPOSCS.INPUT.TXN_CCY;
        DCCPFTCK.VAL.TXN_AMT = DCCPOSCS.INPUT.AMT;
        DCCPFTCK.TRK2_DAT = DCCPOSCS.INPUT.TRK2_DAT;
        DCCPFTCK.TRK3_DAT = DCCPOSCS.INPUT.TRK3_DAT;
        S000_CALL_DCZPFTCK();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRPOSRD);
        DCRPOSRD.KEY.AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        DCRPOSRD.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        CEP.TRC(SCCGWA, DCRPOSRD.KEY.AC_DT);
        CEP.TRC(SCCGWA, DCRPOSRD.KEY.JRNNO);
        T000_READ_DCTPOSRD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.TRC(SCCGWA, "NOT FND");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_CONSUME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCRPOSRD.TR_FLG);
        if (DCRPOSRD.TR_FLG == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_POS_CONSU_CANCEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCRPOSRD.LNK_FLG);
        if (DCRPOSRD.TR_FLG == 'R' 
            && DCRPOSRD.LNK_FLG == 'Y') {
            DCRPOSRD.KEY.AC_DT = DCRPOSRD.LNK_AC_DT;
            DCRPOSRD.KEY.JRNNO = DCRPOSRD.LNK_JRNNO;
            CEP.TRC(SCCGWA, DCRPOSRD.LNK_AC_DT);
            CEP.TRC(SCCGWA, DCRPOSRD.LNK_JRNNO);
            CEP.TRC(SCCGWA, DCRPOSRD.KEY.AC_DT);
            CEP.TRC(SCCGWA, DCRPOSRD.KEY.JRNNO);
            T000_READ_DCTPOSRD();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
                && DCRPOSRD.TR_FLG == 'N') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_POS_CONSU_CANCEL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DCRPOSRD);
        DCRPOSRD.KEY.AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        DCRPOSRD.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        T000_READ_UPDATE_DCTPOSRD();
        if (pgmRtn) return;
        DCRPOSRD.TR_FLG = 'C';
        DCRPOSRD.CANCEL_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRPOSRD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPOSRD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTPOSRD();
        if (pgmRtn) return;
    }
    public void B081_FEE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = WS_CARD_NO;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = DCCUCINF.CARD_OWN_CINO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CARD_PSBK_NO);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.AUH_FLG);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.SVR_CD);
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT = 1;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].TO_ACCT_CEN = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_FLG = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE = DCCPOSCS.INPUT.FEE_CODE;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY = '4';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC = WS_CARD_NO;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_BAS = DCCPOSCS.INPUT.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_AMT = DCCPOSCS.INPUT.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS = DCCPOSCS.INPUT.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT = DCCPOSCS.INPUT.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = DCCPOSCS.INPUT.FEE_AMT;
        if (DCCPACTY.INPUT.CCY.trim().length() == 0) {
            BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = "156";
            BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = "156";
        } else {
            BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = DCCPACTY.INPUT.CCY;
            BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = DCCPACTY.INPUT.CCY;
        }
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_CNT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].TO_ACCT_CEN);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_FLG);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_BAS);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY);
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
    }
    public void B082_GET_PRE_FEE_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = WS_CARD_NO;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = DCCUCINF.CARD_OWN_CINO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CARD_PSBK_NO);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.AUH_FLG);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.SVR_CD);
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'T';
        if (DCCPOSCS.INPUT.CRO_BANK_FLG == '1') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '1';
        } else {
            BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        }
        if (DCCPOSCS.INPUT.G_TYP == '1') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "03";
        } else {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "02";
        }
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.PROD_CODE = DCCUCINF.PROD_CD;
        WS_FEE_PROD = DCCUCINF.PROD_CD;
        if ((WS_FEE_PROD.equalsIgnoreCase("CAD21000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD11000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD11100") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD12000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD22000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD41000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD51000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD55000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD10000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD28000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD34000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD16000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD16100") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD58000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD53000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD37000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD38000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD52000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD35000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD56000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD42000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD39000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD00000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD43000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD54000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD12100") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD66000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD50000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD20000"))) {
            BPCTCALF.INPUT_AREA.PROD_CODE = "CAD21000";
        } else {
            BPCTCALF.INPUT_AREA.PROD_CODE = " ";
        }
        BPCTCALF.INPUT_AREA.TX_CI = DCCUCINF.CARD_OWN_CINO;
        BPCTCALF.INPUT_AREA.TX_AMT = DCCPOSCS.INPUT.AMT;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_CODE);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_AMT);
    }
    public void B083_WRITE_FEE_TABLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDFEE);
        DCRCDFEE.KEY.CARD_NO = WS_CARD_NO;
        DCRCDFEE.KEY.FEE_CODE = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_CODE;
        T000_READ_DCTCDFEE();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.init(SCCGWA, DCRCDFEE);
            DCRCDFEE.KEY.CARD_NO = WS_CARD_NO;
            DCRCDFEE.KEY.FEE_CODE = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_CODE;
            T000_READ_UPD_DCTCDFEE();
            if (pgmRtn) return;
            B087_CHECK_MONTH();
            if (pgmRtn) return;
            DCRCDFEE.CUR_MTH_TOT_VOL = WS_TOT_VOL;
            DCRCDFEE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDFEE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, WS_TOT_VOL);
            T000_REWRITE_DCTCDFEE();
            if (pgmRtn) return;
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                DCRCDFEE.KEY.CARD_NO = WS_CARD_NO;
                DCRCDFEE.KEY.FEE_CODE = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_CODE;
                DCRCDFEE.CUR_MTH_TOT_VOL = 1;
                DCRCDFEE.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDFEE.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                DCRCDFEE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDFEE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_ADD_DCTCDFEE();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDFEE";
                SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
                B_DB_EXCP();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B084_GET_FEE_TOT_VOL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDFEE);
        DCRCDFEE.KEY.CARD_NO = WS_CARD_NO;
        DCRCDFEE.KEY.FEE_CODE = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_CODE;
        T000_READ_DCTCDFEE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDFEE.CUR_MTH_TOT_VOL);
    }
    public void B085_GET_FEE_CODE_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = WS_CARD_NO;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = DCCUCINF.CARD_OWN_CINO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CARD_PSBK_NO);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.AUH_FLG);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.SVR_CD);
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        if (DCCPOSCS.INPUT.CRO_BANK_FLG == '1') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '1';
        } else {
            BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        }
        if (DCCPOSCS.INPUT.G_TYP == '1') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "03";
        } else {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "02";
        }
        BPCTCALF.INPUT_AREA.TX_CNT = (short) DCRCDFEE.CUR_MTH_TOT_VOL;
        BPCTCALF.INPUT_AREA.PROD_CODE = DCCUCINF.PROD_CD;
        WS_FEE_PROD = DCCUCINF.PROD_CD;
        if ((WS_FEE_PROD.equalsIgnoreCase("CAD21000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD11000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD11100") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD12000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD22000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD41000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD51000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD55000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD10000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD28000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD34000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD16000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD16100") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD58000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD53000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD37000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD38000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD52000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD35000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD56000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD42000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD39000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD00000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD43000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD54000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD12100") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD66000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD50000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD20000"))) {
            BPCTCALF.INPUT_AREA.PROD_CODE = "CAD21000";
        } else {
            BPCTCALF.INPUT_AREA.PROD_CODE = " ";
        }
        BPCTCALF.INPUT_AREA.TX_CI = DCCUCINF.CARD_OWN_CINO;
        BPCTCALF.INPUT_AREA.TX_AMT = DCCPOSCS.INPUT.AMT;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_CODE);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_AMT);
    }
    public void B086_FEE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = WS_CARD_NO;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = DCCUCINF.CARD_OWN_CINO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CARD_PSBK_NO);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.AUH_FLG);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.SVR_CD);
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT = 1;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].TO_ACCT_CEN = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_FLG = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_CODE;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY = '4';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC = WS_CARD_NO;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_BAS = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_AMT;
        if (DCCPACTY.INPUT.CCY.trim().length() == 0) {
            BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = "156";
            BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = "156";
        } else {
            BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = DCCPACTY.INPUT.CCY;
            BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = DCCPACTY.INPUT.CCY;
        }
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_CNT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].TO_ACCT_CEN);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_FLG);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_BAS);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY);
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
    }
    public void B087_CHECK_MONTH() throws IOException,SQLException,Exception {
        WS_UPD_DATE = DCRCDFEE.UPDTBL_DATE;
        WS_TOT_VOL = DCRCDFEE.CUR_MTH_TOT_VOL;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + WS_UPD_DATE;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        if (JIBS_tmp_str[0].substring(0, 6).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 6))) {
            WS_TOT_VOL = WS_TOT_VOL + 1;
        } else {
            WS_TOT_VOL = 1;
        }
    }
    public void B900_FEE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = WS_CARD_NO;
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = DCCUCINF.CARD_OWN_CINO;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_CCY);
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.PROD_CODE = DCCUCINF.PROD_CD;
        WS_FEE_PROD = DCCUCINF.PROD_CD;
        if ((WS_FEE_PROD.equalsIgnoreCase("CAD91000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD92000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD90000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD96000"))) {
            BPCTCALF.INPUT_AREA.PROD_CODE = "CAD91000";
        } else {
            BPCTCALF.INPUT_AREA.PROD_CODE = " ";
        }
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_AMT = DCCPOSCS.INPUT.AMT;
        BPCTCALF.INPUT_AREA.TX_CI = DCCUCINF.CARD_OWN_CINO;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF       ", DCCUCINF);
        CEP.TRC(SCCGWA, DCCUCINF.RC);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC         ", DDCUDRAC);
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK      ", DCCPCDCK);
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK     ", DCCPFTCK);
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CON-CHG-INFO", BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF ", DCCPACTY);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMCNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CNT", CICMCNT);
        if (CICMCNT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMCNT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DCTCDFEE() throws IOException,SQLException,Exception {
        DCTCDFEE_RD = new DBParm();
        DCTCDFEE_RD.TableName = "DCTCDFEE";
        DCTCDFEE_RD.col = "CARD_NO, FEE_CODE, CUR_MTH_TOT_VOL, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRCDFEE, DCTCDFEE_RD);
    }
    public void T000_READ_UPD_DCTCDFEE() throws IOException,SQLException,Exception {
        DCTCDFEE_RD = new DBParm();
        DCTCDFEE_RD.TableName = "DCTCDFEE";
        DCTCDFEE_RD.col = "CARD_NO, FEE_CODE, CUR_MTH_TOT_VOL, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTCDFEE_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDFEE, DCTCDFEE_RD);
    }
    public void T000_ADD_DCTCDFEE() throws IOException,SQLException,Exception {
        DCTCDFEE_RD = new DBParm();
        DCTCDFEE_RD.TableName = "DCTCDFEE";
        IBS.WRITE(SCCGWA, DCRCDFEE, DCTCDFEE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDFEE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCDFEE() throws IOException,SQLException,Exception {
        DCTCDFEE_RD = new DBParm();
        DCTCDFEE_RD.TableName = "DCTCDFEE";
        DCTCDFEE_RD.col = "CUR_MTH_TOT_VOL, UPDTBL_DATE, UPDTBL_TLR";
        DCTCDFEE_RD.where = "CARD_NO = :DCRCDFEE.KEY.CARD_NO "
            + "AND FEE_CODE = :DCRCDFEE.KEY.FEE_CODE";
        IBS.REWRITE(SCCGWA, DCRCDFEE, this, DCTCDFEE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDFEE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTPOSRD() throws IOException,SQLException,Exception {
        DCTPOSRD_RD = new DBParm();
        DCTPOSRD_RD.TableName = "DCTPOSRD";
        DCTPOSRD_RD.col = "AC_DT, JRNNO, AC_NO, CARD_NO, TR_CD, TR_FLG, TR_DT, TR_TM, TR_EFF_DT, CANCEL_DT, TR_AMT, TR_CCY, TR_BR, TR_TLR, LNK_FLG, LNK_AC_DT, LNK_TR_CD, LNK_JRNNO, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRPOSRD, DCTPOSRD_RD);
    }
    public void T000_READ_UPDATE_DCTPOSRD() throws IOException,SQLException,Exception {
        DCTPOSRD_RD = new DBParm();
        DCTPOSRD_RD.TableName = "DCTPOSRD";
        DCTPOSRD_RD.col = "AC_DT, JRNNO, AC_NO, CARD_NO, TR_CD, TR_FLG, TR_DT, TR_TM, TR_EFF_DT, CANCEL_DT, TR_AMT, TR_CCY, TR_BR, TR_TLR, LNK_FLG, LNK_AC_DT, LNK_TR_CD, LNK_JRNNO, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTPOSRD_RD.upd = true;
        IBS.READ(SCCGWA, DCRPOSRD, DCTPOSRD_RD);
    }
    public void T000_UPDATE_DCTPOSRD() throws IOException,SQLException,Exception {
        DCTPOSRD_RD = new DBParm();
        DCTPOSRD_RD.TableName = "DCTPOSRD";
        DCTPOSRD_RD.col = "AC_NO, CARD_NO, TR_CD, TR_FLG, TR_DT, TR_TM, TR_EFF_DT, CANCEL_DT, TR_AMT, TR_CCY, TR_BR, TR_TLR, LNK_FLG, LNK_AC_DT, LNK_TR_CD, LNK_JRNNO, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTPOSRD_RD.where = "AC_DT = :DCRPOSRD.KEY.AC_DT "
            + "AND JRNNO = :DCRPOSRD.KEY.JRNNO";
        IBS.REWRITE(SCCGWA, DCRPOSRD, this, DCTPOSRD_RD);
    }
    public void T000_WRITE_DCTPOSRD() throws IOException,SQLException,Exception {
        DCTPOSRD_RD = new DBParm();
        DCTPOSRD_RD.TableName = "DCTPOSRD";
        IBS.WRITE(SCCGWA, DCRPOSRD, DCTPOSRD_RD);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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

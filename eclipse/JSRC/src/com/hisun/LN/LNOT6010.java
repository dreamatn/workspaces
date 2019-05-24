package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT6010 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_DT = 20991231;
    char K_RATE_N = 'N';
    char K_RATE_O = 'O';
    char K_RATE_L = 'L';
    char K_RATE_P = 'P';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_EXT_FLG = ' ';
    char WS_END_FLAG = ' ';
    char WS_HOLIDAY_O_FLG = ' ';
    LNOT6010_WS_CTY_CODE[] WS_CTY_CODE = new LNOT6010_WS_CTY_CODE[5];
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSEXT LNCSEXT = new LNCSEXT();
    LNCP17 LNCP17 = new LNCP17();
    LNC601 LNC601 = new LNC601();
    LNCBKRAT LNCBKRAT = new LNCBKRAT();
    LNCFRATE LNCFRATE = new LNCFRATE();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    LNB6010_AWA_6010 LNB6010_AWA_6010;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public LNOT6010() {
        for (int i=0;i<5;i++) WS_CTY_CODE[i] = new LNOT6010_WS_CTY_CODE();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            R000_OUTPUT_PROCESS();
            if (pgmRtn) return;
        }
        B100_SEND_MESSAGE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT6010 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB6010_AWA_6010>");
        LNB6010_AWA_6010 = (LNB6010_AWA_6010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_CTA_TRANSFER_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB6010_AWA_6010.FUNC == 'A' 
            && LNB6010_AWA_6010.EE_DATE == 0 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB6010_AWA_6010.EE_DATE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6010_AWA_6010.EE_DATE > K_MAX_DT 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_DT_MAX;
            WS_FLD_NO = LNB6010_AWA_6010.EE_DATE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B001_CHK_FTA_TYP();
        if (pgmRtn) return;
    }
    public void B001_CHK_FTA_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPCFTA);
        LNCPCFTA.BR_GP[1-1].BR = LNB6010_AWA_6010.BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        if (LNCPCFTA.FTA_FLG == 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FTA_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CTA_TRANSFER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSEXT);
        LNCSEXT.FUNC = LNB6010_AWA_6010.FUNC;
        LNCSEXT.CTA_TYP = LNB6010_AWA_6010.CTA_TYP;
        CEP.TRC(SCCGWA, LNCSEXT.CTA_TYP);
        LNCSEXT.CTA_NO = LNB6010_AWA_6010.CTA_NO;
        LNCSEXT.BR = LNB6010_AWA_6010.BR;
        LNCSEXT.CI_NO = LNB6010_AWA_6010.CI_NO;
        LNCSEXT.CI_CNM = LNB6010_AWA_6010.CI_CNM;
        LNCSEXT.PROD_CD = LNB6010_AWA_6010.PROD_CD;
        LNCSEXT.CCY = LNB6010_AWA_6010.CCY;
        LNCSEXT.P_AMT = LNB6010_AWA_6010.P_AMT;
        LNCSEXT.B_AMT = LNB6010_AWA_6010.B_AMT;
        LNCSEXT.OE_DATE = LNB6010_AWA_6010.OE_DATE;
        LNCSEXT.EE_DATE = LNB6010_AWA_6010.EE_DATE;
        LNCSEXT.VAL_DATE = LNB6010_AWA_6010.VAL_DATE;
        LNCSEXT.EXT_AMT = LNB6010_AWA_6010.EXT_AMT;
        LNCSEXT.REASON = LNB6010_AWA_6010.REASON;
        LNCSEXT.RAT_FLG = LNB6010_AWA_6010.RAT_MTH;
        LNCSEXT.NEXT_FLT_DT = LNB6010_AWA_6010.FLT_DAY;
        LNCSEXT.FLT_PERD_UN = LNB6010_AWA_6010.FLT_PDUT;
        LNCSEXT.FLT_PERD = LNB6010_AWA_6010.FLT_PERD;
        LNCSEXT.FLOAT_MTH = LNB6010_AWA_6010.FLT_MTH;
        LNCSEXT.FLT_FLG = LNB6010_AWA_6010.FLT_FLG;
        LNCSEXT.FLTFIX_D = LNB6010_AWA_6010.FLTFIX_D;
        LNCSEXT.CMP_METHOD = LNB6010_AWA_6010.COMP_MTH;
        LNCSEXT.IRAT_TYP = LNB6010_AWA_6010.IRAT_TYP;
        LNCSEXT.RAT_PERD = LNB6010_AWA_6010.RAT_PERD;
        LNCSEXT.RAT_VARI = LNB6010_AWA_6010.RAT_VARI;
        LNCSEXT.RAT_PECT = LNB6010_AWA_6010.RAT_PECT;
        LNCSEXT.RAT_REF = LNB6010_AWA_6010.RAT_REF;
        CEP.TRC(SCCGWA, LNB6010_AWA_6010.RAT_REF);
        CEP.TRC(SCCGWA, LNCSEXT.RAT_REF);
        LNCSEXT.ALL_IN_R = LNB6010_AWA_6010.ALL_IN_R;
        CEP.TRC(SCCGWA, LNB6010_AWA_6010.ALL_IN_R);
        CEP.TRC(SCCGWA, LNCSEXT.ALL_IN_R);
        LNCSEXT.PEN_RRAT = LNB6010_AWA_6010.PEN_RRAT;
        LNCSEXT.PEN_TYP = LNB6010_AWA_6010.PEN_TYP;
        LNCSEXT.PEN_RATT = LNB6010_AWA_6010.PEN_RATT;
        LNCSEXT.PEN_RATC = LNB6010_AWA_6010.PEN_RATC;
        LNCSEXT.PEN_SPR = LNB6010_AWA_6010.PEN_SPR;
        LNCSEXT.PEN_PCT = LNB6010_AWA_6010.PEN_PCT;
        LNCSEXT.PEN_IRAT = LNB6010_AWA_6010.PEN_IRAT;
        CEP.TRC(SCCGWA, LNB6010_AWA_6010.PEN_IRAT);
        CEP.TRC(SCCGWA, LNCSEXT.PEN_IRAT);
        LNCSEXT.CPND_USE = LNB6010_AWA_6010.CPND_USE;
        LNCSEXT.INT_MTH = LNB6010_AWA_6010.INT_MTH;
        LNCSEXT.CPND_RTY = LNB6010_AWA_6010.CPND_RTY;
        LNCSEXT.CPND_TYP = LNB6010_AWA_6010.CPND_TYP;
        LNCSEXT.CPNDRATT = LNB6010_AWA_6010.CPNDRATT;
        LNCSEXT.CPNDRATC = LNB6010_AWA_6010.CPNDRATC;
        LNCSEXT.CPND_SPR = LNB6010_AWA_6010.CPND_SPR;
        LNCSEXT.CPND_PCT = LNB6010_AWA_6010.CPND_PCT;
        LNCSEXT.OLC_PERU = LNB6010_AWA_6010.OLC_PERU;
        LNCSEXT.ABUS_RAT = LNB6010_AWA_6010.ABUS_RAT;
        LNCSEXT.ABUS_TYP = "" + LNB6010_AWA_6010.ABUS_TYP;
        JIBS_tmp_int = LNCSEXT.ABUS_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNCSEXT.ABUS_TYP = "0" + LNCSEXT.ABUS_TYP;
        LNCSEXT.ABUSRATT = LNB6010_AWA_6010.ABUSRATT.charAt(0);
        LNCSEXT.ABUSRATC = LNB6010_AWA_6010.ABUSRATC;
        LNCSEXT.ABUSSPR = LNB6010_AWA_6010.ABUSSPR;
        LNCSEXT.ABUSPCT = LNB6010_AWA_6010.ABUSPCT;
        LNCSEXT.ABUSIRAT = LNB6010_AWA_6010.ABUSIRAT;
        S000_CALL_LNZSEXT();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        if (LNB6010_AWA_6010.FUNC != 'A') {
            IBS.init(SCCGWA, LNCP17);
            IBS.init(SCCGWA, SCCFMT);
            LNCP17.CTA_TYP = LNCSEXT.CTA_TYP;
            LNCP17.CTA_NO = LNCSEXT.CTA_NO;
            LNCP17.BR = LNCSEXT.BR;
            LNCP17.CI_NO = LNCSEXT.CI_NO;
            LNCP17.CI_CNM = LNCSEXT.CI_CNM;
            LNCP17.PROD_CD = LNCSEXT.PROD_CD;
            LNCP17.CCY = LNCSEXT.CCY;
            LNCP17.P_AMT = LNCSEXT.P_AMT;
            LNCP17.B_AMT = LNCSEXT.B_AMT;
            LNCP17.OE_DATE = LNCSEXT.OE_DATE;
            LNCP17.EE_DATE = LNCSEXT.EE_DATE;
            LNCP17.VAL_DATE = LNCSEXT.VAL_DATE;
            LNCP17.EXT_AMT = LNCSEXT.EXT_AMT;
            LNCP17.REASON = LNCSEXT.REASON;
            SCCFMT.FMTID = "LNP17";
            SCCFMT.DATA_PTR = LNCP17;
            SCCFMT.DATA_LEN = 807;
            IBS.FMT(SCCGWA, SCCFMT);
        }
        if (LNB6010_AWA_6010.FUNC == 'A') {
            IBS.init(SCCGWA, LNC601);
            IBS.init(SCCGWA, SCCFMT);
            LNC601.BR = LNB6010_AWA_6010.BR;
            LNC601.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
            LNC601.SUP_NO = SCCGWA.COMM_AREA.SUP1_ID;
            LNC601.TR_DT = SCCGWA.COMM_AREA.AC_DATE;
            LNC601.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            LNC601.CTA_NO = LNB6010_AWA_6010.CTA_NO;
            LNC601.CCY = LNB6010_AWA_6010.CCY;
            LNC601.CCY_TYP = LNB6010_AWA_6010.CCY_TYP;
            LNC601.CI_NO = LNCSEXT.CI_NO;
            LNC601.CI_CNM = LNCSEXT.CI_CNM;
            LNC601.P_AMT = LNCSEXT.P_AMT;
            LNC601.B_AMT = LNCSEXT.B_AMT;
            LNC601.VAL_DATE = LNB6010_AWA_6010.VAL_DATE;
            LNC601.EE_DATE = LNB6010_AWA_6010.EE_DATE;
            LNC601.EXT_AMT = LNB6010_AWA_6010.EXT_AMT;
            LNC601.REASON = LNB6010_AWA_6010.REASON;
            LNC601.RAT_FLG = LNB6010_AWA_6010.RAT_MTH;
            LNC601.FLT_PERD_UN = LNB6010_AWA_6010.FLT_PDUT;
            LNC601.FLT_PERD = LNB6010_AWA_6010.FLT_PERD;
            LNC601.FLT_FLG = LNB6010_AWA_6010.FLT_FLG;
            LNC601.NEXT_FLT_DT = LNB6010_AWA_6010.FLT_DAY;
            LNC601.FLTFIX_DT = LNB6010_AWA_6010.FLTFIX_D;
            LNC601.VAR_MTH = LNCSEXT.FLOAT_MTH;
            LNC601.CMP_METHOD = LNCSEXT.CMP_METHOD;
            LNC601.IRAT_CD = LNB6010_AWA_6010.IRAT_TYP;
            LNC601.RAT_CLAS = LNB6010_AWA_6010.RAT_PERD;
            LNC601.RAT_VARI = LNB6010_AWA_6010.RAT_VARI;
            LNC601.RAT_PECT = LNB6010_AWA_6010.RAT_PECT;
            LNC601.REF_RAT = LNB6010_AWA_6010.RAT_REF;
            LNC601.COST_RAT = 0;
            LNC601.BASE_MEMO = " ";
            LNC601.PREM_RAT = 0;
            LNC601.ADDON_RAT = 0;
            LNC601.ALLIN_RAT = LNB6010_AWA_6010.ALL_IN_R;
            LNC601.PEN_TYP = LNCSEXT.PEN_TYP;
            LNC601.PEN_RRAT = LNCSEXT.PEN_RRAT;
            LNC601.PEN_RATT = LNCSEXT.PEN_RATT;
            LNC601.PEN_RATC = LNCSEXT.PEN_RATC;
            LNC601.PEN_SPR = LNCSEXT.PEN_SPR;
            LNC601.PEN_PCT = LNCSEXT.PEN_PCT;
            LNC601.PEN_IRAT = LNCSEXT.PEN_IRAT;
            LNC601.CPND_USE = LNCSEXT.CPND_USE;
            LNC601.INT_MTH = LNCSEXT.INT_MTH;
            LNC601.CPND_RTY = LNCSEXT.CPND_RTY;
            LNC601.CPND_TYP = LNCSEXT.CPND_TYP;
            LNC601.CPNDRATT = LNCSEXT.CPNDRATT;
            LNC601.CPNDRATC = LNCSEXT.CPNDRATC;
            LNC601.CPND_SPR = LNCSEXT.CPND_SPR;
            LNC601.CPND_PCT = LNCSEXT.CPND_PCT;
            LNC601.OLC_PERU = LNCSEXT.OLC_PERU;
            LNC601.ABUS_RAT = LNCSEXT.ABUS_RAT;
            LNC601.ABUS_TYP = LNCSEXT.ABUS_TYP;
            LNC601.ABUSRATT = LNCSEXT.ABUSRATT;
            LNC601.ABUSRATC = LNCSEXT.ABUSRATC;
            LNC601.ABUSSPR = LNCSEXT.ABUSSPR;
            LNC601.ABUSPCT = LNCSEXT.ABUSPCT;
            LNC601.ABUSIRAT = LNCSEXT.ABUSIRAT;
            SCCFMT.FMTID = "LN601";
            SCCFMT.DATA_PTR = LNC601;
            SCCFMT.DATA_LEN = 783;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void B100_SEND_MESSAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = LNB6010_AWA_6010.CTA_NO;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCWRMSG);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        SCCWRMSG.AC = LNB6010_AWA_6010.CTA_NO;
        SCCWRMSG.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        SCCWRMSG.AP_CODE = "26";
        CEP.TRC(SCCGWA, LNB6010_AWA_6010.EE_DATE);
        JIBS_tmp_str[0] = "" + LNB6010_AWA_6010.EE_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) SCCWRMSG.YEAR = 0;
        else SCCWRMSG.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + LNB6010_AWA_6010.EE_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) SCCWRMSG.MONTH = 0;
        else SCCWRMSG.MONTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + LNB6010_AWA_6010.EE_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) SCCWRMSG.DAY = 0;
        else SCCWRMSG.DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        CEP.TRC(SCCGWA, SCCWRMSG.AC);
        CEP.TRC(SCCGWA, SCCWRMSG.CI_NO);
        R000_CALL_SCZWRMSG();
        if (pgmRtn) return;
    }
    public void R000_CALL_SCZWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG, true);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSEXT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CONTRACT-EXT", LNCSEXT);
        CEP.TRC(SCCGWA, LNCSEXT.RC);
        CEP.TRC(SCCGWA, LNCSEXT.RC.RC_RTNCODE);
        if (LNCSEXT.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSEXT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZFRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-F-RATE-CAL", LNCFRATE);
        CEP.TRC(SCCGWA, LNCFRATE.RC.RC_RTNCODE);
        if (LNCFRATE.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCFRATE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

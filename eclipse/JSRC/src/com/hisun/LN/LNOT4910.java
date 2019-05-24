package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT4910 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    LNOT4910_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT4910_WS_TEMP_VARIABLE();
    LNOT4910_WS_MSG_INFO WS_MSG_INFO = new LNOT4910_WS_MSG_INFO();
    short WS_I = 0;
    char WS_STS_OLD = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCP62 LNCP62 = new LNCP62();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCIPART LNCIPART = new LNCIPART();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNCCNEV LNCCNEV = new LNCCNEV();
    SCCGWA SCCGWA;
    LNB4910_AWA_4910 LNB4910_AWA_4910;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_CODE);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        R000_OUTPUT_PROCESS();
        if (pgmRtn) return;
        B000_AUTH_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT4910 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB4910_AWA_4910>");
        LNB4910_AWA_4910 = (LNB4910_AWA_4910) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        R000_GET_ICIQ_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNB4910_AWA_4910.STS_CHG);
        if ((LNB4910_AWA_4910.STS_CHG == 'A') 
            && (LNCCLNQ.DATA.STS == 'B' 
            || LNCCLNQ.DATA.STS == 'W' 
            || LNCCLNQ.DATA.STS == 'X')) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_STS_IS_B;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNB4910_AWA_4910.STS_CHG == 'B') 
            && (LNCCLNQ.DATA.STS != 'B')) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_STS_MUST_IS_B;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_STS_OLD = LNCCLNQ.DATA.STS;
        R000_UPDATE_ICTL();
        if (pgmRtn) return;
        R000_GET_ICIQ_INFO();
        if (pgmRtn) return;
        B200_CTNR_PROCESS();
        if (pgmRtn) return;
    }
    public void B200_CTNR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "TOBADLN";
        if ((LNB4910_AWA_4910.STS_CHG == 'A')) {
            LNCCNEV.COMM_DATA.ACM_EVENT = "TB";
        } else {
            LNCCNEV.COMM_DATA.ACM_EVENT = "TN";
        }
        LNCCNEV.COMM_DATA.LN_AC = LNB4910_AWA_4910.CTA_NO;
        LNCCNEV.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEV.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEV.COMM_DATA.SUF_NO = "0" + LNCCNEV.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = LNB4910_AWA_4910.VAL_DTE;
        LNCCNEV.COMM_DATA.P_AMT = LNCCLNQ.DATA.TOT_BAL;
        LNCCNEV.COMM_DATA.RVS_IND = 'N';
        S000_CALL_LNZCNEV();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB4910_AWA_4910.CTA_NO);
        CEP.TRC(SCCGWA, LNB4910_AWA_4910.VAL_DTE);
        CEP.TRC(SCCGWA, LNB4910_AWA_4910.STS_CHG);
        CEP.TRC(SCCGWA, LNB4910_AWA_4910.RMK_L1);
        CEP.TRC(SCCGWA, LNB4910_AWA_4910.RMK_L2);
        CEP.TRC(SCCGWA, LNB4910_AWA_4910.RMK_L3);
        CEP.TRC(SCCGWA, LNB4910_AWA_4910.CTA_NO);
        if (LNB4910_AWA_4910.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB4910_AWA_4910.STS_CHG != 'A' 
            && LNB4910_AWA_4910.STS_CHG != 'B') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_STS_CHG_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCP62);
        LNCP62.CTA_NO = LNB4910_AWA_4910.CTA_NO;
        LNCP62.BOOK_BR = LNCCLNQ.DATA.DOMI_BR;
        LNCP62.CI_NO = LNCCLNQ.DATA.CI_NO;
        LNCP62.CI_SNM = LNCCLNQ.DATA.CI_SNAME;
        LNCP62.CITY_CD = LNCCLNQ.DATA.CITY_CD;
        LNCP62.CI_ENM = LNCCLNQ.DATA.CI_ENAME;
        LNCP62.CI_CNM = LNCCLNQ.DATA.CI_CNAME;
        LNCP62.PROD_CD = LNCCLNQ.DATA.PROD_CD;
        LNCP62.CCY = LNCCLNQ.DATA.CCY;
        LNCP62.AMT = LNCCLNQ.DATA.AMT;
        LNCP62.TOT_AMT = LNCCLNQ.DATA.TOT_AMT;
        LNCP62.VAL_DT = LNCCLNQ.DATA.VAL_DT;
        LNCP62.DUE_DT = LNCCLNQ.DATA.DUE_DT;
        LNCP62.STS_OLD = WS_STS_OLD;
        LNCP62.STS_NEW = LNCCLNQ.DATA.STS;
        LNCP62.RMK1 = LNB4910_AWA_4910.RMK_L1;
        LNCP62.RMK2 = LNB4910_AWA_4910.RMK_L2;
        LNCP62.RMK3 = LNB4910_AWA_4910.RMK_L3;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LNP62";
        SCCFMT.DATA_PTR = LNCP62;
        SCCFMT.DATA_LEN = 964;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B000_AUTH_PROCESS() throws IOException,SQLException,Exception {
    }
    public void R000_GET_ICIQ_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNB4910_AWA_4910.CTA_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
    }
    public void R000_UPDATE_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNB4910_AWA_4910.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        if (LNB4910_AWA_4910.STS_CHG == 'A') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                LNCICTLM.REC_DATA.CTL_STSW = "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(1);
            }
        } else {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 12 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(12 + 1 - 1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if ((LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0")) 
                && (LNCCLNQ.DATA.VAL_DT < LNB4910_AWA_4910.VAL_DTE)) {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 2 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(2 + 1 - 1);
            } else {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                LNCICTLM.REC_DATA.CTL_STSW = "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(1);
            }
        }
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCIPART.DATA);
        LNCIPART.DATA.CONTRACT_NO = LNB4910_AWA_4910.CTA_NO;
        LNCIPART.DATA.LEVEL = 'R';
        LNCIPART.DATA.FUNC = 'T';
        S000_CALL_LNZIPART();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= LNCIPART.DATA.CNT; WS_I += 1) {
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNB4910_AWA_4910.CTA_NO;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = LNCIPART.GROUP.get(WS_I-1).SEQ_NO;
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            LNCICTLM.FUNC = '2';
            if (LNB4910_AWA_4910.STS_CHG == 'A') {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    LNCICTLM.REC_DATA.CTL_STSW = "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(1);
                }
            } else {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if ((LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0")) 
                    && (LNCCLNQ.DATA.VAL_DT < LNB4910_AWA_4910.VAL_DTE)) {
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 2 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(2 + 1 - 1);
                } else {
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    LNCICTLM.REC_DATA.CTL_STSW = "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(1);
                }
            }
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIPART() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-PARTI-INQ", LNCIPART);
        if (LNCIPART.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIPART.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCNEV.RC);
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

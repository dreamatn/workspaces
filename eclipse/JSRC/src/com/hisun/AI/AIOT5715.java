package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5715 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_COUNT = 0;
    String WS_TMP_AC = " ";
    int WS_TMP_BBR = 0;
    String WS_GET_CCY = " ";
    AIOT5715_WS_OUTPUT WS_OUTPUT = new AIOT5715_WS_OUTPUT();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICRONA AICRONA = new AICRONA();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AIRONA AIRONA = new AIRONA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5800_AWA_5800 AIB5800_AWA_5800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5715 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5800_AWA_5800>");
        AIB5800_AWA_5800 = (AIB5800_AWA_5800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_TMP_AC = AIB5800_AWA_5800.AC;
        if (AIB5800_AWA_5800.AC.trim().length() > 0) {
            AICRONA.FUNC = 'B';
            AIRONA.KEY.OAC_NO = AIB5800_AWA_5800.AC;
            S000_CALL_AIZRONA();
            if (AIB5800_AWA_5800.AC == null) AIB5800_AWA_5800.AC = "";
            JIBS_tmp_int = AIB5800_AWA_5800.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AIB5800_AWA_5800.AC += " ";
            if (AIB5800_AWA_5800.AC == null) AIB5800_AWA_5800.AC = "";
            JIBS_tmp_int = AIB5800_AWA_5800.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AIB5800_AWA_5800.AC += " ";
            if ((AIB5800_AWA_5800.AC.substring(25 - 1, 25 + 1 - 1).trim().length() == 0 
                || AIB5800_AWA_5800.AC.substring(26 - 1, 26 + 1 - 1).trim().length() > 0) 
                && AICRONA.RETURN_INFO == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.NOT_INVALID_ACCOUNT;
                S000_ERR_MSG_PROC();
            }
            if (AICRONA.RETURN_INFO == 'F') {
                WS_TMP_AC = AIRONA.AC_NO;
            }
        }
        IBS.init(SCCGWA, BPCPQORG);
        if (WS_TMP_AC == null) WS_TMP_AC = "";
        JIBS_tmp_int = WS_TMP_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_TMP_AC += " ";
        if (WS_TMP_AC.substring(0, 6).trim().length() == 0) BPCPQORG.BR = 0;
        else BPCPQORG.BR = Integer.parseInt(WS_TMP_AC.substring(0, 6));
        S000_CALL_BPZPQORG();
        WS_TMP_BBR = BPCPQORG.BBR;
        if (BPCPQORG.ATTR == '3') {
            if (BPCPQORG.BBR != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = WS_TMP_BBR;
                S000_CALL_BPZPQORG();
                if (BPCPQORG.ATTR == '2') {
                    if (WS_TMP_AC == null) WS_TMP_AC = "";
                    JIBS_tmp_int = WS_TMP_AC.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) WS_TMP_AC += " ";
                    JIBS_tmp_str[0] = "" + BPCPQORG.BBR;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    WS_TMP_AC = JIBS_tmp_str[0] + WS_TMP_AC.substring(6);
                }
            }
        }
        CEP.TRC(SCCGWA, WS_TMP_AC);
    }
    public void B020_QUERY_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.GL_BOOK);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.AC);
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.GL_BOOK = AIB5800_AWA_5800.GL_BOOK;
        AICPQMIB.INPUT_DATA.AC = WS_TMP_AC;
        WS_GET_CCY = WS_TMP_AC;
        if (WS_GET_CCY == null) WS_GET_CCY = "";
        JIBS_tmp_int = WS_GET_CCY.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_GET_CCY += " ";
        AICPQMIB.INPUT_DATA.CCY = WS_GET_CCY.substring(7 - 1, 7 + 3 - 1);
        AICPQMIB.INPUT_DATA.TX_FLG = AIB5800_AWA_5800.TX_FLG;
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.TX_FLG);
        S00_CALL_AIZPQMIB();
        R000_DATA_OUTPUT();
    }
    public void S00_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        CEP.TRC(SCCGWA, AICPQMIB.RC);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_AIZRONA() throws IOException,SQLException,Exception {
        AICRONA.POINTER = AIRONA;
        AICRONA.REC_LEN = 82;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-ONA", AICRONA);
        CEP.TRC(SCCGWA, AICRONA.RC);
        CEP.TRC(SCCGWA, AICRONA.RETURN_INFO);
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_CCY = AICPQMIB.INPUT_DATA.CCY;
        WS_OUTPUT.WS_OUT_CHS_NM = AICPQMIB.OUTPUT_DATA.CHS_NM;
        WS_OUTPUT.WS_OUT_ENG_NM = AICPQMIB.OUTPUT_DATA.ENG_NM;
        WS_OUTPUT.WS_OUT_STS = AICPQMIB.OUTPUT_DATA.STS;
        WS_OUTPUT.WS_OUT_AC_TYP = AICPQMIB.OUTPUT_DATA.AC_TYP;
        WS_OUTPUT.WS_OUT_CCY_LMT = AICPQMIB.OUTPUT_DATA.CCY_LMT;
        WS_OUTPUT.WS_OUT_BAL_DIR = AICPQMIB.OUTPUT_DATA.BAL_DIR;
        WS_OUTPUT.WS_OUT_BAL_RFLG = AICPQMIB.OUTPUT_DATA.BAL_RFLG;
        WS_OUTPUT.WS_OUT_AMT_DIR = AICPQMIB.OUTPUT_DATA.AMT_DIR;
        WS_OUTPUT.WS_OUT_DTL_FLG = AICPQMIB.OUTPUT_DATA.DTL_FLG;
        WS_OUTPUT.WS_OUT_RVS_TYP = AICPQMIB.OUTPUT_DATA.RVS_TYP;
        WS_OUTPUT.WS_OUT_RVS_KND = AICPQMIB.OUTPUT_DATA.RVS_KND;
        WS_OUTPUT.WS_OUT_RVS_EXP = AICPQMIB.OUTPUT_DATA.RVS_EXP;
        WS_OUTPUT.WS_OUT_RVS_UNIT = AICPQMIB.OUTPUT_DATA.RVS_UNIT;
        WS_OUTPUT.WS_OUT_AC_EXP = AICPQMIB.OUTPUT_DATA.AC_EXP;
        WS_OUTPUT.WS_OUT_MANUAL = AICPQMIB.OUTPUT_DATA.MANUAL_FLG;
        WS_OUTPUT.WS_OUT_ONL_FLG = AICPQMIB.OUTPUT_DATA.ONL_FLG;
        WS_OUTPUT.WS_OUT_CARD_FLG = AICPQMIB.OUTPUT_DATA.CARD_FLG;
        WS_OUTPUT.WS_OUT_HOT_FLG = AICPQMIB.OUTPUT_DATA.HOT_FLG;
        WS_OUTPUT.WS_OUT_DRLT_BAL = AICPQMIB.OUTPUT_DATA.DRLT_BAL;
        WS_OUTPUT.WS_OUT_CRLT_BAL = AICPQMIB.OUTPUT_DATA.CRLT_BAL;
        WS_OUTPUT.WS_OUT_BAL_CHK = AICPQMIB.OUTPUT_DATA.CHK_FLG;
        WS_OUTPUT.WS_OUT_APP1 = AICPQMIB.OUTPUT_DATA.APP1;
        WS_OUTPUT.WS_OUT_APP2 = AICPQMIB.OUTPUT_DATA.APP2;
        WS_OUTPUT.WS_OUT_APP3 = AICPQMIB.OUTPUT_DATA.APP3;
        WS_OUTPUT.WS_OUT_APP4 = AICPQMIB.OUTPUT_DATA.APP4;
        WS_OUTPUT.WS_OUT_APP5 = AICPQMIB.OUTPUT_DATA.APP5;
        WS_OUTPUT.WS_OUT_CLN_REN = AICPQMIB.OUTPUT_DATA.CLN_REN;
        WS_OUTPUT.WS_OUT_OPEN_DATE = AICPQMIB.OUTPUT_DATA.OPEN_DATE;
        WS_OUTPUT.WS_OUT_CLS_DATE = AICPQMIB.OUTPUT_DATA.CLS_DATE;
        WS_OUTPUT.WS_OUT_LAST_TX_DT = AICPQMIB.OUTPUT_DATA.LAST_TX_DT;
        WS_OUTPUT.WS_OUT_LBAL = AICPQMIB.OUTPUT_DATA.LBAL;
        WS_OUTPUT.WS_OUT_CBAL = AICPQMIB.OUTPUT_DATA.CBAL;
        WS_OUTPUT.WS_OUT_BR = AICPQMIB.INPUT_DATA.BR;
        WS_OUTPUT.WS_OUT_ITM = AICPQMIB.INPUT_DATA.ITM_NO;
        WS_OUTPUT.WS_OUT_CCY = AICPQMIB.INPUT_DATA.CCY;
        WS_OUTPUT.WS_OUT_SEQ = AICPQMIB.INPUT_DATA.SEQ;
        WS_OUTPUT.WS_OUT_AC = WS_TMP_AC;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_ITM);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_CCY);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_SEQ);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AI715";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 558;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

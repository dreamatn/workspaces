package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5523 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_I = 0;
    int WS_CNT = 0;
    char WS_RVS_TYP = ' ';
    AIOT5523_WS_AC_NO WS_AC_NO = new AIOT5523_WS_AC_NO();
    AIOT5523_WS_OUTPUT WS_OUTPUT = new AIOT5523_WS_OUTPUT();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    AICUPRVS AICUPRVS = new AICUPRVS();
    SCCFMT SCCFMT = new SCCFMT();
    AICRGINF AICRGINF = new AICRGINF();
    AIRGINF AIRGINF = new AIRGINF();
    AICUUPIA AICUUPIA = new AICUUPIA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5500_AWA_5500 AIB5500_AWA_5500;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT5523 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5500_AWA_5500>");
        AIB5500_AWA_5500 = (AIB5500_AWA_5500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_DEAL_RVS_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_DEAL_RVS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START");
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_DATA[1-1].RVS_NO1);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_DATA[1-1].AC);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_DATA[1-1].BR1);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_DATA[1-1].G_BAL);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.ITM1);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.SEQ1);
        WS_OUTPUT.WS_C_DATE = SCCGWA.COMM_AREA.TR_DATE;
        WS_OUTPUT.WS_C_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        WS_OUTPUT.WS_TLR_ID = SCCGWA.COMM_AREA.TL_ID;
        WS_OUTPUT.WS_SUS_TELLER = SCCGWA.COMM_AREA.SUP1_ID;
        for (WS_I = 1; WS_I <= 15 
            && AIB5500_AWA_5500.RVS_DATA[WS_I-1].RVS_NO1.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_DATA[WS_I-1].RVS_NO1);
            CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC);
            CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_DATA[WS_I-1].BR1);
            CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_DATA[WS_I-1].G_BAL);
            CEP.TRC(SCCGWA, AIB5500_AWA_5500.ITM1);
            CEP.TRC(SCCGWA, AIB5500_AWA_5500.SEQ1);
            CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_DATA[WS_I-1].STS1);
            IBS.init(SCCGWA, AICRGINF);
            IBS.init(SCCGWA, AIRGINF);
            AICRGINF.INFO.FUNC = 'Q';
            AIRGINF.KEY.RVS_NO = AIB5500_AWA_5500.RVS_DATA[WS_I-1].RVS_NO1;
            CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
            S000_CALL_AIZRGINF();
            if (pgmRtn) return;
            AIB5500_AWA_5500.RVS_DATA[WS_I-1].G_BAL = AIRGINF.G_BAL;
            CEP.TRC(SCCGWA, AIRGINF.G_BAL);
            WS_OUTPUT.WS_BSAIC_INFO[WS_I-1].WS_G_KND = AIRGINF.RVS_KND;
            CEP.TRC(SCCGWA, AIRGINF.RVS_KND);
            if (AIB5500_AWA_5500.RVS_DATA[WS_I-1].STS1 != 'N' 
                && AIB5500_AWA_5500.RVS_DATA[WS_I-1].STS1 != 'P') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_GRVS_STS_WRONG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.AC);
            IBS.init(SCCGWA, AICPQMIB);
            if (AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC == null) AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC = "";
            JIBS_tmp_int = AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC += " ";
            if (AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC.substring(0, 6).trim().length() == 0) WS_AC_NO.WS_BR = 0;
            else WS_AC_NO.WS_BR = Integer.parseInt(AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC.substring(0, 6));
            if (AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC == null) AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC = "";
            JIBS_tmp_int = AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC += " ";
            WS_AC_NO.WS_CCY = AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC.substring(7 - 1, 7 + 3 - 1);
            WS_AC_NO.WS_ITM = AIB5500_AWA_5500.ITM1;
            WS_AC_NO.WS_AC_SEQ = AIB5500_AWA_5500.SEQ1;
            AICPQMIB.INPUT_DATA.AC = IBS.CLS2CPY(SCCGWA, WS_AC_NO);
            if (AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC == null) AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC = "";
            JIBS_tmp_int = AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC += " ";
            AICPQMIB.INPUT_DATA.CCY = AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC.substring(7 - 1, 7 + 3 - 1);
            CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC);
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            if (AICPQMIB.OUTPUT_DATA.CARD_FLG == 'Y') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_8971;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICPQMIB.OUTPUT_DATA.MANUAL_FLG == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_AC_NOT_MANUL_POST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.AC);
            CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.RVS_TYP);
            WS_RVS_TYP = AICPQMIB.OUTPUT_DATA.RVS_TYP;
            WS_OUTPUT.WS_BSAIC_INFO[WS_I-1].WS_C_ITM = AIB5500_AWA_5500.ITM1;
            WS_OUTPUT.WS_BSAIC_INFO[WS_I-1].WS_C_CCY = AIRGINF.CCY;
            WS_OUTPUT.WS_BSAIC_INFO[WS_I-1].WS_G_BAL = AIRGINF.G_BAL;
            WS_OUTPUT.WS_BSAIC_INFO[WS_I-1].WS_G_RVS = AIB5500_AWA_5500.RVS_DATA[WS_I-1].RVS_NO1;
            WS_OUTPUT.WS_BSAIC_INFO[WS_I-1].WS_G_DATE = AIRGINF.FST_G_DT;
            CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.GL_BOOK);
            CEP.TRC(SCCGWA, WS_AC_NO.WS_BR);
            CEP.TRC(SCCGWA, WS_AC_NO);
            CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.AC);
            CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.CCY);
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC;
            if (AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC == null) AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC = "";
            JIBS_tmp_int = AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC += " ";
            AICPQMIB.INPUT_DATA.CCY = AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC.substring(7 - 1, 7 + 3 - 1);
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            WS_OUTPUT.WS_BSAIC_INFO[WS_I-1].WS_G_MIB = AICPQMIB.INPUT_DATA.AC;
            CEP.TRC(SCCGWA, "RVS SIGN");
            CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.RVS_TYP);
            if (AICPQMIB.OUTPUT_DATA.RVS_TYP != WS_RVS_TYP 
                && WS_RVS_TYP != 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.RVS_TYP_THE_SAME_RECORD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICPQMIB.OUTPUT_DATA.MANUAL_FLG == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_AC_NOT_MANUL_POST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, AICUUPIA);
            if (AICPQMIB.OUTPUT_DATA.RVS_TYP == 'D') {
                AICUUPIA.DATA.EVENT_CODE = "CR";
            } else {
                if (AICPQMIB.OUTPUT_DATA.RVS_TYP == 'C') {
                    AICUUPIA.DATA.EVENT_CODE = "DR";
                }
            }
            AICUUPIA.DATA.AC_NO = AIB5500_AWA_5500.RVS_DATA[WS_I-1].AC;
            AICUUPIA.DATA.CCY = AICPQMIB.INPUT_DATA.CCY;
            AICUUPIA.DATA.AMT = AIB5500_AWA_5500.RVS_DATA[WS_I-1].G_BAL;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.RVS_NO = AIB5500_AWA_5500.RVS_DATA[WS_I-1].RVS_NO1;
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
            IBS.init(SCCGWA, AICUUPIA);
            if (AICPQMIB.OUTPUT_DATA.RVS_TYP == 'D') {
                AICUUPIA.DATA.EVENT_CODE = "DR";
            } else {
                if (AICPQMIB.OUTPUT_DATA.RVS_TYP == 'C') {
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                }
            }
            AICUUPIA.DATA.AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO);
            AICUUPIA.DATA.CCY = AICPQMIB.INPUT_DATA.CCY;
            AICUUPIA.DATA.AMT = AIB5500_AWA_5500.RVS_DATA[WS_I-1].G_BAL;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        S000_OUTPUT_FOR_PRINTF();
        if (pgmRtn) return;
    }
    public void R00_MOVE_VAWR_DATA() throws IOException,SQLException,Exception {
        WS_CNT += 1;
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT == 1) {
            BPCOVAWR.FST_FLG = 'Y';
        } else {
            BPCOVAWR.FST_FLG = 'N';
        }
        CEP.TRC(SCCGWA, BPCOVAWR.FST_FLG);
        BPCOVAWR.PARTB.BOOK_FLG = AICPQMIB.INPUT_DATA.GL_BOOK;
        BPCOVAWR.PARTB.BR = AIB5500_AWA_5500.RVS_DATA[WS_I-1].BR1;
        BPCOVAWR.PARTB.CCY = AICPQMIB.INPUT_DATA.CCY;
        BPCOVAWR.PARTB.AMT = AIB5500_AWA_5500.RVS_DATA[WS_I-1].G_BAL;
        if (BPCOVAWR.PARTB.AMT > 0) {
            BPCOVAWR.PARTB.RED_FLG = 'B';
        } else {
            BPCOVAWR.PARTB.RED_FLG = 'R';
        }
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_DATA[1-1].RVS_NO1);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_DATA[2-1].RVS_NO1);
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, BPCOVAWR.PARTB.RVS_NO);
        BPCOVAWR.PARTB.TLR_ID = SCCGWA.COMM_AREA.TL_ID;
        BPCOVAWR.PARTB.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOVAWR.PARTB.TLR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPVAWR();
        if (pgmRtn) return;
    }
    public void S000_OUTPUT_FOR_PRINTF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AI523";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 1416;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_AIZRGINF() throws IOException,SQLException,Exception {
        AICRGINF.INFO.POINTER = AIRGINF;
        AICRGINF.INFO.LEN = 242;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GINF", AICRGINF);
        if (AICRGINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRGINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.AC);
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.CCY);
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUPRVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-PROC-RVS", AICUPRVS);
        CEP.TRC(SCCGWA, AICUPRVS.RC);
        if (AICUPRVS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUPRVS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPVAWR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-VWA-WRITE", BPCOVAWR);
        if (BPCOVAWR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOVAWR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA, true);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        WS_ERR_INFO = "AC = " + AICPQMIB.INPUT_DATA.AC;
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
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

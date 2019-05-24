package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPRTRT;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCWRMSG;

public class LNOT4000 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_X = 0;
    int WS_Y = 0;
    int WS_AC_NO = 0;
    String WS_CTL_STSW = " ";
    double WS_TOT_AMT = 0;
    int WS_DUE_DT = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSRPO LNCSRPO = new LNCSRPO();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCDDALN LNCDDALN = new LNCDDALN();
    LNCSRPOR LNCSRPOR = new LNCSRPOR();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    LNRICTL LNRICTL = new LNRICTL();
    LNRRCVD LNRRCVD = new LNRRCVD();
    SCCGWA SCCGWA;
    LNB4000_AWA_4000 LNB4000_AWA_4000;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTRT BPRTRT;
    SCCBATH SCCBATH;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT4000 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB4000_AWA_4000>");
        LNB4000_AWA_4000 = (LNB4000_AWA_4000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTRT = (BPRTRT) GWA_SC_AREA.TR_PARM_PTR;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        }
        IBS.init(SCCGWA, LNCSRPO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B040_CALL_REAPYR_SERVICE();
            if (pgmRtn) return;
        } else {
            B040_CALL_REAPY_SERVICE();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB4000_AWA_4000.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB4000_AWA_4000.CTA_NO_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_Y = 1; WS_Y <= 5; WS_Y += 1) {
            WS_TOT_AMT = WS_TOT_AMT + LNB4000_AWA_4000.MULT_AC[WS_Y-1].PAY_AMT;
        }
        if (WS_TOT_AMT != 0) {
            if (LNB4000_AWA_4000.TOT_AMT != WS_TOT_AMT) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_TOT_AMT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB4000_AWA_4000.TR_VALDT == 0) {
            LNB4000_AWA_4000.TR_VALDT = SCCGWA.COMM_AREA.AC_DATE;
        }
        for (WS_X = 1; WS_X <= 5; WS_X += 1) {
            if (!LNB4000_AWA_4000.MULT_AC[WS_X-1].STL_MTH.equalsIgnoreCase("08") 
                && LNB4000_AWA_4000.MULT_AC[WS_X-1].STL_MTH.trim().length() > 0) {
                if (LNB4000_AWA_4000.MULT_AC[WS_X-1].REC_AC.trim().length() == 0) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_DD_AC_NOT_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
            }
        }
        if (LNB4000_AWA_4000.TOT_AMT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB4000_AWA_4000.SUBS_FLG == ' ') {
            LNB4000_AWA_4000.SUBS_FLG = 'N';
        }
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNB4000_AWA_4000.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        WS_CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNB4000_AWA_4000.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "20180526TEST");
        CEP.TRC(SCCGWA, LNB4000_AWA_4000.SUBS_FLG);
    }
    public void B040_CALL_REAPYR_SERVICE() throws IOException,SQLException,Exception {
        LNCSRPOR.TXN_INFO.TXN_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNCSRPOR.TXN_INFO.JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        LNCSRPOR.COMM_DATA.CTA_NO = LNB4000_AWA_4000.CTA_NO;
        LNCSRPOR.COMM_DATA.TR_VAL_DTE = LNB4000_AWA_4000.TR_VALDT;
        LNCSRPOR.COMM_DATA.CCY = LNB4000_AWA_4000.CCY;
        LNCSRPOR.COMM_DATA.TOT_AMT = LNB4000_AWA_4000.TOT_AMT;
        LNCSRPOR.COMM_DATA.TOT_P_AMT = LNB4000_AWA_4000.TOT_PRIN;
        LNCSRPOR.COMM_DATA.TOT_I_AMT = LNB4000_AWA_4000.TOT_INT;
        LNCSRPOR.COMM_DATA.TOT_O_AMT = LNB4000_AWA_4000.TOT_PLC;
        LNCSRPOR.COMM_DATA.TOT_L_AMT = LNB4000_AWA_4000.TOT_ILC;
        LNCSRPOR.COMM_DATA.MLT_STL = LNB4000_AWA_4000.MLT_STL;
        LNCSRPOR.COMM_DATA.APT_REF = LNB4000_AWA_4000.APT_REF;
        LNCSRPOR.COMM_DATA.CUR_TRM = LNB4000_AWA_4000.CUR_TRM;
        LNCSRPOR.COMM_DATA.CLN_CUT = LNB4000_AWA_4000.CLN_CUT;
        LNCSRPOR.COMM_DATA.SUBS_FLG = LNB4000_AWA_4000.SUBS_FLG;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            LNCSRPOR.COMM_DATA.MMO = "12010003";
        } else {
            LNCSRPOR.COMM_DATA.MMO = "12010002";
        }
        for (WS_I = 1; WS_I <= 10 
            && LNB4000_AWA_4000.PART_INF[WS_I-1].SUB_C_NO.trim().length() != 0; WS_I += 1) {
            LNCSRPOR.COMM_DATA.PART_DATA[WS_I-1].SUB_C_NO = LNB4000_AWA_4000.PART_INF[WS_I-1].SUB_C_NO;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_I-1].SEQ_NO = LNB4000_AWA_4000.PART_INF[WS_I-1].SEQ_NO;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_I-1].BBR = LNB4000_AWA_4000.PART_INF[WS_I-1].SUB_BR;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_I-1].REL_TYP = LNB4000_AWA_4000.PART_INF[WS_I-1].RLA_TYP;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_I-1].PY_P_AMT = LNB4000_AWA_4000.PART_INF[WS_I-1].PY_P_AMT;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_I-1].PY_I_AMT = LNB4000_AWA_4000.PART_INF[WS_I-1].PY_I_AMT;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_I-1].PY_O_AMT = LNB4000_AWA_4000.PART_INF[WS_I-1].PY_O_AMT;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_I-1].PY_L_AMT = LNB4000_AWA_4000.PART_INF[WS_I-1].PY_L_AMT;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_I-1].PY_F_AMT = LNB4000_AWA_4000.PART_INF[WS_I-1].PY_F_AMT;
        }
        for (WS_I = 1; WS_I <= 5; WS_I += 1) {
            LNCSRPOR.COMM_DATA.ACAMT[WS_I-1].AC_FLG2 = LNB4000_AWA_4000.MULT_AC[WS_I-1].AC_FLG;
            LNCSRPOR.COMM_DATA.ACAMT[WS_I-1].STL_MTH2 = LNB4000_AWA_4000.MULT_AC[WS_I-1].STL_MTH;
            LNCSRPOR.COMM_DATA.ACAMT[WS_I-1].REC_AC2 = LNB4000_AWA_4000.MULT_AC[WS_I-1].REC_AC;
            LNCSRPOR.COMM_DATA.ACAMT[WS_I-1].CCY_TYP = LNB4000_AWA_4000.MULT_AC[WS_I-1].CCY_TYP;

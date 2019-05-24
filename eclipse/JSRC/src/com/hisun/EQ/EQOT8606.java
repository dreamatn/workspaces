package com.hisun.EQ;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPQORG;
import com.hisun.CI.CICCUST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class EQOT8606 {
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String K_BSZ_BANKID = "01";
    char K_DIV_CPN_FLG = '2';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_PRE_QTY = 0;
    double WS_CLPT = 0;
    int WS_LEN = 0;
    EQOT8606_WS_FILE_NAME WS_FILE_NAME = new EQOT8606_WS_FILE_NAME();
    char WS_PRT_MTH = ' ';
    char WS_ACT_FLG = ' ';
    char WS_DVCD_FLG = ' ';
    String WS_TRNSF_INAC = " ";
    String WS_TRNSF_OTAC = " ";
    int WS_BEGIN_DT = 0;
    int WS_END_DT = 0;
    char WS_TXN_TYP = ' ';
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQRACT EQRACT = new EQRACT();
    EQRDVCD EQRDVCD = new EQRDVCD();
    CICCUST CICCUST = new CICCUST();
    EQCO8606 EQCO8606 = new EQCO8606();
    EQCF8606 EQCF8606 = new EQCF8606();
    BPCPQORG BPCPQORG = new BPCPQORG();
    EQCRACT EQCRACT = new EQCRACT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EQCSDVC EQCSDVC;
    EQB0006_AWA_0006 EQB0006_AWA_0006;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQOT8606 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "EQB0006_AWA_0006>");
        EQB0006_AWA_0006 = (EQB0006_AWA_0006) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
        B030_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.EQ_BKID);
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.OWNER_BR);
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.CCY);
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.DIV_DT);
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.EQ_AC);
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.PRT_TYP);
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.PRT_MTH);
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.STA_MTH);
        if (EQB0006_AWA_0006.EQ_BKID.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQB0006_AWA_0006.OWNER_BR == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_OWNER_BR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = EQB0006_AWA_0006.OWNER_BR;
            CEP.TRC(SCCGWA, BPCPQORG.BR);
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
            if (BPCPQORG.VIL_TYP.equalsIgnoreCase(EQB0006_AWA_0006.EQ_BKID)) {
            } else {
                if (BPCPQORG.VIL_TYP.equalsIgnoreCase("00") 
                    && EQB0006_AWA_0006.EQ_BKID.equalsIgnoreCase("01")) {
                } else {
                    WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BANKID_MUST_SAME;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (EQB0006_AWA_0006.CCY.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CCY_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQB0006_AWA_0006.DIV_DT == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DIVDT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQB0006_AWA_0006.PRT_MTH == ' ') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_PRTMTH_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_PRT_MTH = EQB0006_AWA_0006.PRT_MTH;
            if ((WS_PRT_MTH != '0' 
                && WS_PRT_MTH != '1' 
                && WS_PRT_MTH != '2')) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_PRT_MTH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_FILE_NAME.WS_OUT_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_FILE_NAME.WS_FILE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, WS_FILE_NAME);

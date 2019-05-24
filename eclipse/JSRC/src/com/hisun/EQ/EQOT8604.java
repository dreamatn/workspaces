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

public class EQOT8604 {
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String K_BSZ_BANKID = "01";
    char K_DIV_CPN_FLG = '1';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_COUNT = 0;
    double WS_PRE_QTY = 0;
    double WS_CLPT = 0;
    int WS_LEN = 0;
    int WS_CNT = 0;
    EQOT8604_WS_FILE_NAME WS_FILE_NAME = new EQOT8604_WS_FILE_NAME();
    char WS_ACT_FLG = ' ';
    char WS_DVCD_FLG = ' ';
    char WS_EQ_TYP = ' ';
    String WS_EQ_CCY = " ";
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
    EQRDVC EQRDVC = new EQRDVC();
    EQRDVCD EQRDVCD = new EQRDVCD();
    CICCUST CICCUST = new CICCUST();
    EQCRDVC EQCRDVC = new EQCRDVC();
    EQCO8604 EQCO8604 = new EQCO8604();
    EQCF8604 EQCF8604 = new EQCF8604();
    BPCPQORG BPCPQORG = new BPCPQORG();
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
        CEP.TRC(SCCGWA, "EQOT8604 return!");
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
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.EQ_BKID);
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.OWNER_BR);
        if (EQB0006_AWA_0006.EQ_BKID.trim().length() == 0) {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT);
        } else {
            if (EQB0006_AWA_0006.OWNER_BR == 0) {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_OWNER_BR_MUST_INPUT);
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
                        CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_BANKID_MUST_SAME);
                    }
                }
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQB0006_AWA_0006.EQ_BKID);
        WS_FILE_NAME.WS_OUT_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_FILE_NAME.WS_FILE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, WS_FILE_NAME);
        IBS.init(SCCGWA, EQRDVCD);

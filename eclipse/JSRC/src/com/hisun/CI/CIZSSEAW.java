package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPRPRMT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZSSEAW {
    boolean pgmRtn = false;
    int K_ARR_MAX_CNT = 20;
    int WS_I = 0;
    String WS_LIST_STSW = " ";
    String WS_ID_TYPE = " ";
    String WS_ID_NO = " ";
    String WS_NM = " ";
    short WS_CTLW = 0;
    char WS_LIST1_FLG = ' ';
    char WS_LIST2_FLG = ' ';
    CIZSSEAW_WS_RTN WS_RTN = new CIZSSEAW_WS_RTN();
    char WS_BAS_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRLS1 CIRLS1 = new CIRLS1();
    CIRLS2 CIRLS2 = new CIRLS2();
    CICPLST CICPLST = new CICPLST();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSSEAW CICSSEAW;
    public void MP(SCCGWA SCCGWA, CICSSEAW CICSSEAW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSSEAW = CICSSEAW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSSEAW return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICSSEAW.RC.RC_MMO = "CI";
        CICSSEAW.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_STS_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_GET_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSSEAW.DATA.CI_NO;
        CEP.TRC(SCCGWA, CICSSEAW.DATA.CI_NO);
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (WS_BAS_FLG == 'Y') {
            CICSSEAW.DATA.STS_DATA.CI_STSW = CIRBAS.STSW;
            CICSSEAW.DATA.STS_DATA.VER_STSW = CIRBAS.VER_STSW;
            if (CIRBAS.CI_TYP == '1') {
                if (CICSSEAW.DATA.STS_DATA.VER_STSW == null) CICSSEAW.DATA.STS_DATA.VER_STSW = "";
                JIBS_tmp_int = CICSSEAW.DATA.STS_DATA.VER_STSW.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CICSSEAW.DATA.STS_DATA.VER_STSW += " ";
                if (CICSSEAW.DATA.STS_DATA.VER_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("0")) {
                    if (CICSSEAW.DATA.STS_DATA.VER_STSW == null) CICSSEAW.DATA.STS_DATA.VER_STSW = "";
                    JIBS_tmp_int = CICSSEAW.DATA.STS_DATA.VER_STSW.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) CICSSEAW.DATA.STS_DATA.VER_STSW += " ";
                    CICSSEAW.DATA.STS_DATA.VER_STSW = CICSSEAW.DATA.STS_DATA.VER_STSW.substring(0, 20 - 1) + "1" + CICSSEAW.DATA.STS_DATA.VER_STSW.substring(20 + 1 - 1);
                } else {
                    if (CICSSEAW.DATA.STS_DATA.VER_STSW == null) CICSSEAW.DATA.STS_DATA.VER_STSW = "";
                    JIBS_tmp_int = CICSSEAW.DATA.STS_DATA.VER_STSW.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) CICSSEAW.DATA.STS_DATA.VER_STSW += " ";
                    CICSSEAW.DATA.STS_DATA.VER_STSW = CICSSEAW.DATA.STS_DATA.VER_STSW.substring(0, 20 - 1) + "0" + CICSSEAW.DATA.STS_DATA.VER_STSW.substring(20 + 1 - 1);
                }
            }
            WS_ID_TYPE = CIRBAS.ID_TYPE;
            WS_ID_NO = CIRBAS.ID_NO;

package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCACAAC;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQGLM;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCQCNGL;
import com.hisun.BP.BPCUCNGM;
import com.hisun.DD.DDCIMMST;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CIZSINDD {
    boolean pgmRtn = false;
    String K_ADR_HOME = "114";
    String K_ADR_COMPANY = "115";
    String K_ADR_OFTEN = "112";
    String K_CNT_COMPANY = "12";
    String K_CNT_HOME = "11";
    String K_CNT_MOPH = "13";
    int K_STS_1 = 1;
    String K_OUTPUT_FMT_X = "CIX01";
    int K_MAX_ROW = 25;
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_O = 0;
    int WS_P = 0;
    int WS_PAGE_ROW = 0;
    short WS_RECORD_NUM = 0;
    int WS_CX = 0;
    int WS_SEQ = 0;
    String WS_CI_NM = " ";
    char WS_ACR_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCACAAC BPCACAAC = new BPCACAAC();
    BPCPQGLM BPCPQGLM = new BPCPQGLM();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    DDCIMMST DDCIMMST = new DDCIMMST();
    CICOINDD CICOINDD = new CICOINDD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    int WS_COUNT_NO = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSINDD CICSINDD;
    public void MP(SCCGWA SCCGWA, CICSINDD CICSINDD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSINDD = CICSINDD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSINDD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICOINDD.OUTPUT_TITLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSINDD.FUNC);
        CEP.TRC(SCCGWA, CICSINDD.INPUT_DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSINDD.INPUT_DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSINDD.INPUT_DATA.CI_NM);
        CEP.TRC(SCCGWA, CICSINDD.INPUT_DATA.CI_NO);
        CEP.TRC(SCCGWA, CICSINDD.INPUT_DATA.ENTY_NO);
        CEP.TRC(SCCGWA, CICSINDD.INPUT_DATA.PAGE_ROW);
        CEP.TRC(SCCGWA, CICSINDD.INPUT_DATA.PAGE_NUM);
        if (CICSINDD.FUNC == 'C') {
            B020_BROWSE_CITACR();
            if (pgmRtn) return;
        } else {
            WS_MSGID = CICMSG_ERROR_MSG.CI_FUNC_ERR;
            WS_ERR_INFO = "CIOT8260 FUNC(" + CICSINDD.FUNC + ")";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICOINDD.OUTPUT_TITLE.TOTAL_PAGE);
        CEP.TRC(SCCGWA, CICOINDD.OUTPUT_TITLE.TOTAL_NUM);
        CEP.TRC(SCCGWA, CICOINDD.OUTPUT_TITLE.CURR_PAGE);
        CEP.TRC(SCCGWA, CICOINDD.OUTPUT_TITLE.LAST_PAGE);
        CEP.TRC(SCCGWA, CICOINDD.OUTPUT_TITLE.CURR_PAGE_ROW);
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSINDD.INPUT_DATA.CI_NO;
        T000_READ_CITBAS_CHK_CI_TPY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP != '1') {
            WS_MSGID = CICMSG_ERROR_MSG.CI_TYPE_NP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }

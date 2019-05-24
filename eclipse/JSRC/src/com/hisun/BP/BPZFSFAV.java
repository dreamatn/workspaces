package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZFSFAV {
    boolean pgmRtn = false;
    String CPN_U_MAINTAIN_FFAV = "BP-F-U-MAINTAIN-FFAV";
    String CPN_I_GET_SEQ = "BP-S-GET-SEQ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    String WS_TXT = " ";
    String WS_REC = " ";
    double WS_AMT = 0;
    short WS_CNT = 0;
    short WS_J = 0;
    int WS_DATE = 0;
    char WS_FAV_KND = ' ';
    String WS_FAV_TYP = " ";
    char WS_COLL_FLG = ' ';
    char WS_CAL_MTH = ' ';
    char WS_CAL_CYC_1 = ' ';
    char WS_COL_TYPE = ' ';
    char WS_ARG_SPL = ' ';
    String WS_INP_PRFR_CODE = " ";
    BPZFSFAV_WS_TS_QUEUE WS_TS_QUEUE = new BPZFSFAV_WS_TS_QUEUE();
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFAVO BPCOFAVO = new BPCOFAVO();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPCOFFAV BPCOSFAV;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFFAV BPCOSFAV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSFAV = BPCOSFAV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSFAV return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOSFAV.OUTPUT_FMT);
        if (BPCOSFAV.FUNC == 'I') {
            B020_01_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSFAV.FUNC == 'A') {
            B020_02_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSFAV.FUNC == 'U') {
            B020_03_UPDATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSFAV.FUNC == 'D') {
            B020_04_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSFAV.FUNC == 'B') {
            B020_05_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B040_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DATA() throws IOException,SQLException,Exception {
        if (BPCOSFAV.FUNC == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSFAV.FUNC == 'A'
            || BPCOSFAV.FUNC == 'U') {

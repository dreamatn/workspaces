package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICQACRI;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPOT5811 {
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP058";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_CMZR_MAINTAIN = "BP-F-S-MAINTAIN-CMZR";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_NO = 0;
    short WS_CNT = 0;
    short WS_CNT1 = 0;
    char WS_INPUT_ENDED = ' ';
    char WS_ENTI_FLG = ' ';
    char WS_CMZ_FLG = ' ';
    char WS_CMZ_FLG1 = ' ';
    char WS_CMZ_FLG2 = ' ';
    char WS_TBL_FARM_FLAG = ' ';
    char CARD_FEE_FLG = ' ';
    String CMZR_OLD_FLG = " ";
    String AWA_NEW_FLG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCMZR BPCSCMZR = new BPCSCMZR();
    SCCCKDT SCCCKDT = new SCCCKDT();
    CICQACRI CICQACRI = new CICQACRI();
    BPRCMZR BPRCMZR = new BPRCMZR();
    BPCRCMZR BPCRCMZR = new BPCRCMZR();
    DDCUFEES DDCUFEES = new DDCUFEES();
    SCCGWA SCCGWA;
    BPB5811_AWA_5811 BPB5811_AWA_5811;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT5811 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5811_AWA_5811>");
        BPB5811_AWA_5811 = (BPB5811_AWA_5811) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_UPDATE_CMZ_PROT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5811_AWA_5811.CMZ_FLG);
        if (BPB5811_AWA_5811.CMZ_FLG == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMZ_FLG_MUST_INP;
            WS_FLD_NO = BPB5811_AWA_5811.CMZ_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_CMZ_FLG = BPB5811_AWA_5811.CMZ_FLG;
        CEP.TRC(SCCGWA, BPB5811_AWA_5811.CMZ_FLG);
        if (BPB5811_AWA_5811.CMZ_FLG == '1') {
            if (BPB5811_AWA_5811.CMZ_AC.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_CMZ_CI_MUST_INPU;
                WS_FLD_NO = BPB5811_AWA_5811.CI_NO_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPB5811_AWA_5811.CMZ_FLG);
        if (BPB5811_AWA_5811.CMZ_FLG == '2' 
            || BPB5811_AWA_5811.CMZ_FLG == '3') {
            if (BPB5811_AWA_5811.CMZ_AC.trim().length() == 0) {

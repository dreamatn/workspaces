package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPOT5810 {
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP058";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_CMZR_MAINTAIN = "BP-F-S-MAINTAIN-CMZR";
    String CPN_F_MAINTAIN_CMZR = "BP-F-R-MAINTAIN-CMZR";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_NO = 0;
    short WS_CNT = 0;
    short WS_CNT1 = 0;
    String WS_CINO = " ";
    short WS_I = 0;
    char WS_INPUT_ENDED = ' ';
    char WS_ENTI_FLG = ' ';
    char WS_CMZ_FLG = ' ';
    char WS_FLG1 = ' ';
    char WS_FLG2 = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCMZR BPCSCMZR = new BPCSCMZR();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACRI CICQACRI = new CICQACRI();
    BPCRCMZR BPCRCMZR = new BPCRCMZR();
    BPRCMZR BPRCMZR = new BPRCMZR();
    BPRCMZR BPRCMZRO = new BPRCMZR();
    DDCUFEES DDCUFEES = new DDCUFEES();
    SCCGWA SCCGWA;
    BPB5810_AWA_5810 BPB5810_AWA_5810;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT5810 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5810_AWA_5810>");
        BPB5810_AWA_5810 = (BPB5810_AWA_5810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5810_AWA_5810.FEE_INFO[1-1].FEE_CODE);
        CEP.TRC(SCCGWA, BPB5810_AWA_5810.FEE_INFO[2-1].FEE_CODE);
        CEP.TRC(SCCGWA, BPB5810_AWA_5810.FEE_INFO[20-1].FEE_CODE);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_CREATE_CMZ_PROT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5810_AWA_5810.CMZ_FLG);
        if (BPB5810_AWA_5810.CMZ_FLG == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMZ_FLG_MUST_INP;
            WS_FLD_NO = BPB5810_AWA_5810.CMZ_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_CMZ_FLG = BPB5810_AWA_5810.CMZ_FLG;
        CEP.TRC(SCCGWA, WS_CMZ_FLG);
        if (WS_CMZ_FLG == '1' 
            && BPB5810_AWA_5810.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_CMZ_CI_MUST_INPU;
            WS_FLD_NO = BPB5810_AWA_5810.CI_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB5810_AWA_5810.CMZ_FLG);
        if (BPB5810_AWA_5810.CMZ_FLG == '2' 
            || BPB5810_AWA_5810.CMZ_FLG == '3') {
            if (BPB5810_AWA_5810.CMZ_AC.trim().length() == 0) {

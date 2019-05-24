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
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class BPOT4022 {
    boolean pgmRtn = false;
    String CPN_S_MAINT_VCHS = "BP-S-MAINT-VCHS     ";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short K_MAX_CASE_SEQ = 99;
    String K_FMT_CD = "BPX01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCX220 BPCX220 = new BPCX220();
    BPCSMVHS BPCSMVHS = new BPCSMVHS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB4000_AWA_4000 BPB4000_AWA_4000;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT4022 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4000_AWA_4000>");
        BPB4000_AWA_4000 = (BPB4000_AWA_4000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
        B300_SET_RETURN_INFO();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        BPCSMVHS.FUNC = 'Q';
        BPCSMVHS.OUTPUT_FLG = 'N';
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.AP_MMO);
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.TR_CD);
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.CASE_NO);
        BPCSMVHS.AP_MMO = BPB4000_AWA_4000.AP_MMO;
        BPCSMVHS.TR_CD = BPB4000_AWA_4000.TR_CD;
        BPCSMVHS.CASE_NO = BPB4000_AWA_4000.CASE_NO;
        S000_CALL_BPZSMVHS();
        if (pgmRtn) return;
    }
    public void B300_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        SCCSUBS.TR_CODE = 4027;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
        BPCX220.AP_MMO = BPCSMVHS.AP_MMO;
        BPCX220.TR_CD = BPCSMVHS.TR_CD;
        BPCX220.CASE_NO = BPCSMVHS.CASE_NO;
        BPCX220.EFF_DATE = BPCSMVHS.EFF_DATE;
        BPCX220.EXP_DATE = BPCSMVHS.EXP_DATE;
        BPCX220.DATA_TEXT.DESC = BPCSMVHS.DATA_TEXT.DESC;
        BPCX220.DATA_TEXT.SORT_IND = BPCSMVHS.DATA_TEXT.SORT_IND;
        BPCX220.DATA_TEXT.CNT = BPCSMVHS.DATA_TEXT.CNT;

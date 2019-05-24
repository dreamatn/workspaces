package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSCIMD {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP058";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_FLD_NO = 0;
    char WS_CMZ_FLG = ' ';
    String WS_CI_NO = " ";
    String WS_CMZ_AC = " ";
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    char WS_ENTI_FLG = ' ';
    String WS_FEE_CODE = " ";
    String WS_CMZ_DESC = " ";
    char BPZSCIMD_FILLER9 = 0X02;
    char WS_CMZ_FLG1 = ' ';
    char WS_CMZ_FLG2 = ' ';
    double WS_CMZ_AMT = 0;
    double WS_CMZ_PCN = 0;
    int WS_CRT_DATE = 0;
    int WS_UPD_DATE = 0;
    String WS_CRT_TEL = " ";
    String WS_UPD_TEL = " ";
    String WS_SUP_TEL1 = " ";
    String WS_SUP_TEL2 = " ";
    String WS_CMZ_CCY = " ";
    String WK_CLT_CI_NO = " ";
    String WK_CLT_AC = " ";
    char WK_CLT_TYPE = ' ';
    int WK_EFF_DATE = 0;
    int WK_EXP_DATE = 0;
    String WK_CHG_AC = " ";
    String WK_CCY = " ";
    char WK_CLT_RANGE = ' ';
    char WK_CAL_CYCLE = ' ';
    short WK_PERIDO_CNT = 0;
    int WK_FIRST_CHG_DATE = 0;
    int WK_LATELY_CHG_DATE = 0;
    int WK_NEXT_CHG_DATE = 0;
    char WK_HOLI_METHOD = ' ';
    String WK_HLD_NO = " ";
    String WK_FEE_CODE1 = " ";
    String WK_FEE_CODE2 = " ";
    String WK_FEE_CODE3 = " ";
    String WK_FEE_CODE4 = " ";
    String WK_FEE_CODE5 = " ";
    String WK_FEE_CODE6 = " ";
    String WK_FEE_CODE7 = " ";
    String WK_FEE_CODE8 = " ";
    String WK_FEE_CODE9 = " ";
    String WK_FEE_CODE10 = " ";
    String WK_FEE_CODE11 = " ";
    String WK_FEE_CODE12 = " ";
    String WK_FEE_CODE13 = " ";
    String WK_FEE_CODE14 = " ";
    String WK_FEE_CODE15 = " ";
    String WK_FEE_CODE16 = " ";
    String WK_FEE_CODE17 = " ";
    String WK_FEE_CODE18 = " ";
    String WK_FEE_CODE19 = " ";
    String WK_FEE_CODE20 = " ";
    String WK_REMARK = " ";
    int WK_CREATE_DATE = 0;
    int WK_CREATE_TIME = 0;
    String WK_CREATE_TELL = " ";
    String WK_LAST_TELL = " ";
    String WK_SUP_TEL1 = " ";
    String WK_SUP_TEL2 = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRCMZR BPCRCMZR = new BPCRCMZR();
    BPCRFEAG BPCRFEAG = new BPCRFEAG();
    BPRCMZR BPRCMZR = new BPRCMZR();
    BPRFEAG BPRFEAG = new BPRFEAG();
    SCCGWA SCCGWA;
    BPCOCMZR BPCOCMZR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSCIMD BPCSCIMD;
    public void MP(SCCGWA SCCGWA, BPCSCIMD BPCSCIMD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCIMD = BPCSCIMD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCIMD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRCMZR);
        IBS.init(SCCGWA, BPRFEAG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B030_MODIFY_PROCESS_1();
        if (pgmRtn) return;
        B030_MODIFY_PROCESS_2();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS_1() throws IOException,SQLException,Exception {
        B070_CHECK_PROCESS();
        if (pgmRtn) return;
        BPRCMZR.KEY.CI_NO = BPCSCIMD.CI_NO_OLD;
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B070_START_BROWSE_1();
        if (pgmRtn) return;
        B080_READ_NEXT_1();
        if (pgmRtn) return;
        while (BPCRCMZR.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R000_TRANS_DATA_OUTPUT_1();
            if (pgmRtn) return;
            B060_READUPD_PROCESS_1();
            if (pgmRtn) return;
            B060_DELETE_PROCESS_1();
            if (pgmRtn) return;
            B021_TRANS_DATA_PARAMETER_1();
            if (pgmRtn) return;
            B060_CREATE_PROCESS_1();
            if (pgmRtn) return;
            B080_READ_NEXT_1();
            if (pgmRtn) return;
        }
        B090_END_BROWSE_1();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS_2() throws IOException,SQLException,Exception {
        B070_CHECK_PROCESS();
        if (pgmRtn) return;
        BPRFEAG.KEY.CLT_CI_NO = BPCSCIMD.CI_NO_OLD;
        BPCRFEAG.INFO.REC_LEN = 677;
        BPCRFEAG.INFO.POINTER = BPRFEAG;
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B070_START_BROWSE_2();
        if (pgmRtn) return;
        B080_READ_NEXT_2();
        if (pgmRtn) return;
        while (BPCRFEAG.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R000_TRANS_DATA_OUTPUT_2();
            if (pgmRtn) return;
            B060_READUPD_PROCESS_2();
            if (pgmRtn) return;
            B060_DELETE_PROCESS_2();
            if (pgmRtn) return;
            B021_TRANS_DATA_PARAMETER_2();
            if (pgmRtn) return;
            B060_CREATE_PROCESS_2();
            if (pgmRtn) return;
            B080_READ_NEXT_2();
            if (pgmRtn) return;
        }
        B090_END_BROWSE_2();
        if (pgmRtn) return;
    }
    public void B060_READUPD_PROCESS_1() throws IOException,SQLException,Exception {
        BPRCMZR.KEY.CI_NO = WS_CI_NO;
        BPRCMZR.KEY.CMZ_FLG = WS_CMZ_FLG;
        BPRCMZR.KEY.CMZ_AC = WS_CMZ_AC;
        BPRCMZR.KEY.EFF_DATE = WS_EFF_DATE;
        BPRCMZR.KEY.FEE_CODE = WS_FEE_CODE;
        BPCRCMZR.INFO.FUNC = 'R';
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        BPCRCMZR.INFO.REC_LEN = 279;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void B060_READUPD_PROCESS_2() throws IOException,SQLException,Exception {
        BPRFEAG.KEY.CLT_CI_NO = WK_CLT_CI_NO;
        BPRFEAG.KEY.CLT_AC = WK_CLT_AC;
        BPRFEAG.KEY.CLT_TYPE = WK_CLT_TYPE;
        BPRFEAG.KEY.EFF_DATE = WK_EFF_DATE;
        BPCRFEAG.INFO.FUNC = 'R';
        BPCRFEAG.INFO.POINTER = BPRFEAG;
        BPCRFEAG.INFO.REC_LEN = 677;
        S000_CALL_BPZRFEAG();
        if (pgmRtn) return;
    }
    public void B060_DELETE_PROCESS_1() throws IOException,SQLException,Exception {
        BPCRCMZR.INFO.FUNC = 'D';
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        BPCRCMZR.INFO.REC_LEN = 279;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void B060_DELETE_PROCESS_2() throws IOException,SQLException,Exception {
        BPCRFEAG.INFO.FUNC = 'D';
        BPCRFEAG.INFO.POINTER = BPRFEAG;
        BPCRFEAG.INFO.REC_LEN = 677;
        S000_CALL_BPZRFEAG();
        if (pgmRtn) return;
    }
    public void B060_CREATE_PROCESS_1() throws IOException,SQLException,Exception {
        BPCRCMZR.INFO.FUNC = 'C';
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        BPCRCMZR.INFO.REC_LEN = 279;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void B060_CREATE_PROCESS_2() throws IOException,SQLException,Exception {
        BPCRFEAG.INFO.FUNC = 'C';
        BPCRFEAG.INFO.POINTER = BPRFEAG;
        BPCRFEAG.INFO.REC_LEN = 677;
        S000_CALL_BPZRFEAG();
        if (pgmRtn) return;
    }
    public void B070_START_BROWSE_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCMZR);
        BPCRCMZR.INFO.FUNC = 'B';
        BPCRCMZR.INFO.OPT = 'T';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void B070_START_BROWSE_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFEAG);
        BPCRFEAG.INFO.FUNC = 'S';
        BPCRFEAG.INFO.REC_LEN = 677;
        BPCRFEAG.INFO.POINTER = BPRFEAG;
        S000_CALL_BPZRFEAG();
        if (pgmRtn) return;
    }
    public void B080_READ_NEXT_1() throws IOException,SQLException,Exception {
        BPCRCMZR.INFO.FUNC = 'B';
        BPCRCMZR.INFO.OPT = 'N';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void B080_READ_NEXT_2() throws IOException,SQLException,Exception {
        BPCRFEAG.INFO.FUNC = 'N';
        BPCRFEAG.INFO.REC_LEN = 677;
        BPCRFEAG.INFO.POINTER = BPRFEAG;
        S000_CALL_BPZRFEAG();
        if (pgmRtn) return;
    }
    public void B090_END_BROWSE_1() throws IOException,SQLException,Exception {
        BPCRCMZR.INFO.FUNC = 'B';
        BPCRCMZR.INFO.OPT = 'E';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void B090_END_BROWSE_2() throws IOException,SQLException,Exception {
        BPCRFEAG.INFO.FUNC = 'E';
        BPCRFEAG.INFO.REC_LEN = 677;
        BPCRFEAG.INFO.POINTER = BPRFEAG;
        S000_CALL_BPZRFEAG();
        if (pgmRtn) return;
    }
    public void B021_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B070_CHECK_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSCIMD.CI_NO_OLD.trim().length() == 0) {

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

public class BPZSMVHS {
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_HIS_REMARKS = "VCH SORT MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHVHS";
    short K_MAX_CASE_SEQ = 99;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    char WS_SAVE_RESULT = ' ';
    int WS_CHK_DATE = 0;
    BPZSMVHS_WS_FMT_DATE WS_FMT_DATE = new BPZSMVHS_WS_FMT_DATE();
    BPZSMVHS_WS_CASE_SEQ[] WS_CASE_SEQ = new BPZSMVHS_WS_CASE_SEQ[99];
    char WS_DAY_TYP_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FIT_COND_FLG = ' ';
    char WS_SEQ_FND_FLG = ' ';
    char WS_SEQ_DUP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPARM BPCPARM = new BPCPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRVCHS BPRVCHS = new BPRVCHS();
    BPCHVHS BPCOHVHS = new BPCHVHS();
    BPCHVHS BPCNHVHS = new BPCHVHS();
    BPCX220 BPCX220 = new BPCX220();
    BPCQ02 BPCQ02 = new BPCQ02();
    SCCGWA SCCGWA;
    BPCSMVHS BPCSMVHS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZSMVHS() {
        for (int i=0;i<99;i++) WS_CASE_SEQ[i] = new BPZSMVHS_WS_CASE_SEQ();
    }
    public void MP(SCCGWA SCCGWA, BPCSMVHS BPCSMVHS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMVHS = BPCSMVHS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMVHS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, BPCSMVHS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSMVHS.FUNC == 'Q'
            || BPCSMVHS.FUNC == 'A'
            || BPCSMVHS.FUNC == 'U'
            || BPCSMVHS.FUNC == 'C'
            || BPCSMVHS.FUNC == 'D') {
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMVHS.FUNC == 'B') {
            B220_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VCH_IPT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!(BPCSMVHS.OUTPUT_FLG == 'Y' 
            || BPCSMVHS.OUTPUT_FLG == 'N')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VCH_IPT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "SUCC 1");
        if (BPCSMVHS.FUNC == 'Q' 
            || BPCSMVHS.FUNC == 'A' 
            || BPCSMVHS.FUNC == 'U' 
            || BPCSMVHS.FUNC == 'C' 
            || BPCSMVHS.FUNC == 'D') {
            if (BPCSMVHS.AP_MMO.compareTo(SPACE) <= 0 
                || BPCSMVHS.TR_CD.compareTo(SPACE) <= 0 
                || BPCSMVHS.CASE_NO.compareTo(SPACE) <= 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VCH_IPT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "SUCC 2");
        if (BPCSMVHS.FUNC == 'A' 
            || BPCSMVHS.FUNC == 'U') {
            R000_CHECK_DETAIL_INFO();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "SUCC 3");
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        BPRVCHS.KEY.PARM_TYPE = "VCHS ";
        BPRVCHS.KEY.PARM_CODE.AP_MMO = BPCSMVHS.AP_MMO;
        BPRVCHS.KEY.PARM_CODE.TR_CD = BPCSMVHS.TR_CD;
        BPRVCHS.KEY.PARM_CODE.CASE_NO = BPCSMVHS.CASE_NO;
        IBS.init(SCCGWA, BPCPARM);
        if (BPCSMVHS.FUNC == 'Q'
            || BPCSMVHS.FUNC == 'C') {
            BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
            BPCPARM.PARM_DATA.PARM_OPT_2 = 'O';
        } else if (BPCSMVHS.FUNC == 'A') {
            BPCPARM.PARM_DATA.PARM_FUNC = 'A';
            B211_DATA_TEXT_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMVHS.FUNC == 'U'
            || BPCSMVHS.FUNC == 'D') {
            BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
            BPCPARM.PARM_DATA.PARM_OPT_2 = 'U';
        }
        BPCPARM.PARM_DATA.PARM_TYPE = BPRVCHS.KEY.PARM_TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = IBS.CLS2CPY(SCCGWA, BPRVCHS.KEY.PARM_CODE);
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        R000_CHECK_RETURN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSMVHS.CHECK_RESULT);
        if (BPCSMVHS.FUNC == 'D') {
            BPCPARM.PARM_DATA.PARM_FUNC = 'D';
            R000_PARM_DATA_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMVHS.FUNC == 'U') {
            BPCPARM.PARM_DATA.PARM_FUNC = 'U';
            B211_DATA_TEXT_PROCESS();
            if (pgmRtn) return;
            R000_PARM_DATA_PROCESS();
            if (pgmRtn) return;
        } else {
        }
        BPCSMVHS.EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
        BPCSMVHS.EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
        CEP.TRC(SCCGWA, BPCSMVHS.CHECK_RESULT);
        BPCSMVHS.DATA_TEXT.CNT = K_MAX_CASE_SEQ;
        ITM = new BPCSMVHS_ITM();
        BPCSMVHS.DATA_TEXT.INFO.ITM.add(ITM);
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPCSMVHS.DATA_TEXT);
        BPCSMVHS.CHECK_RESULT = WS_SAVE_RESULT;
        CEP.TRC(SCCGWA, BPCSMVHS.CHECK_RESULT);
        if (BPCSMVHS.FUNC == 'A' 
            || BPCSMVHS.FUNC == 'D' 
            || BPCSMVHS.FUNC == 'U') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSMVHS.OUTPUT_FLG == 'Y') {
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B211_DATA_TEXT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMVHS.FUNC);
        if (BPCSMVHS.FUNC == 'U') {
            BPCOHVHS.NAME = " ";
            BPCOHVHS.EFF_DATE = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            BPCOHVHS.EXP_DATE = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            BPCOHVHS.DATA_TEXT.CNT = K_MAX_CASE_SEQ;
            ITM = new OHVHS_ITM();
            BPCOHVHS.DATA_TEXT.INFO.ITM.add(ITM);

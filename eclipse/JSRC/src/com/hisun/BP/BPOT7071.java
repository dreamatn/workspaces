package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICQACAC;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMM;
import com.hisun.SC.SCCSUBS;
import com.hisun.SC.SCRCWAT;
import com.hisun.SC.SCRPRMT;

public class BPOT7071 {
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BPW01";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String K_PAMC_MMO = "MMO  ";
    int WS_LEN = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    int WS_BR = 0;
    String WS_AGR_NO = " ";
    String WS_CCY = " ";
    double WS_CUR_BAL = 0;
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    char WS_BROWS_COND = ' ';
    BPOT7071_WS_FILE_NAME WS_FILE_NAME = new BPOT7071_WS_FILE_NAME();
    BPOT7071_WS_FILE_OUTNAME WS_FILE_OUTNAME = new BPOT7071_WS_FILE_OUTNAME();
    int WS_TRF_AC_SEQ = 0;
    char WS_FRZ_FLG = ' ';
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    int WS_SET_DT = 0;
    int WS_SET_TM = 0;
    String WS_ACO_AC = " ";
    long WS_JRNNO = 0;
    short WS_JRN_SEQ = 0;
    int WS_TX_BR = 0;
    String WS_TX_TLR = " ";
    int WS_AC_SEQ = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCF7071 BPCF7071 = new BPCF7071();
    BPRFHIS BPRFHIS = new BPRFHIS();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPCQFHIS BPCQFHIS = new BPCQFHIS();
    CICQACAC CICQACAC = new CICQACAC();
    BPRFHIS BPRFHIS1 = new BPRFHIS();
    BPRFHIS BPRFHIS2 = new BPRFHIS();
    BPCO7071 BPCO7071 = new BPCO7071();
    BPCUIBAL BPCUIBAL = new BPCUIBAL();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCPRMM SCCSRMM = new SCCPRMM();
    SCRPRMT SCRSRMT = new SCRPRMT();
    SCCGWA SCCGWA;
    BPB7071_AWA_7071 BPB7071_AWA_7071;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT7071 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB7071_AWA_7071>");
        BPB7071_AWA_7071 = (BPB7071_AWA_7071) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB7071_AWA_7071.STR_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7071_AWA_7071.END_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7071_AWA_7071.STR_DT > BPB7071_AWA_7071.END_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_GT_EXPDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB7071_AWA_7071.STR_DT);
        CEP.TRC(SCCGWA, BPB7071_AWA_7071.END_DT);
        if (BPB7071_AWA_7071.END_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7071_AWA_7071.JRNNO == 0 
            && BPB7071_AWA_7071.AC.trim().length() == 0 
            && BPB7071_AWA_7071.TX_TOOL.trim().length() == 0 
            && BPB7071_AWA_7071.TX_TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INP_ONE_COND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7071_AWA_7071.JRNNO != 0 
                && BPB7071_AWA_7071.STR_DT != 0) {
            CEP.TRC(SCCGWA, "BROWSE_BY_JRNNO");
            WS_BROWS_COND = '1';
        } else if (BPB7071_AWA_7071.AC.trim().length() == 0 
                && BPB7071_AWA_7071.TX_TOOL.trim().length() == 0 
                && BPB7071_AWA_7071.TX_CD.trim().length() == 0 
                && BPB7071_AWA_7071.TX_BR != 0 
                && BPB7071_AWA_7071.TX_TLR.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BROWSE BY BR TLR");
            WS_BROWS_COND = '2';
        } else {
            CEP.TRC(SCCGWA, "CI WAY");
            WS_BROWS_COND = '3';
        }
        CEP.TRC(SCCGWA, WS_BROWS_COND);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("302000")) {
            WS_FILE_NAME.WS_FILE_SYS = "TFP";
        } else if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("CNTA")) {
            CEP.TRC(SCCGWA, "IBSTST");
            WS_FILE_NAME.WS_FILE_SYS = "TFP";
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_SYS_ERROR);
        }
        WS_FILE_NAME.WS_FILE_ACDATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_FILE_OUTNAME.WS_FILE_ACDATE2 = SCCGWA.COMM_AREA.AC_DATE;
        WS_FILE_NAME.WS_FILE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        WS_FILE_OUTNAME.WS_FILE_JRN2 = SCCGWA.COMM_AREA.JRN_NO;

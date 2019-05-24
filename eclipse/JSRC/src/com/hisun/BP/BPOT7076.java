package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.DC.DCCMSG_ERROR_MSG;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCIQBAL;
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

public class BPOT7076 {
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BPZ01";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String K_PAMC_MMO = "MMO  ";
    String WS_ACO_AC = " ";
    int WS_I = 0;
    int WS_K = 0;
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    short WS_ROW_NUM = 0;
    char WS_DRCR_FLG = ' ';
    char WS_STS = ' ';
    String WS_TX_MMO = " ";
    int WS_LEN = 0;
    int WS_J = 0;
    short WS_D = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_OUT_CI_NO = " ";
    double WS_OUT_CUR_BAL = 0;
    double WS_CUR_BAL = 0;
    double WS_TX_AMT = 0;
    char WS_DRCRFLG = ' ';
    String WS_AGR_NO = " ";
    char WS_END_FLG = ' ';
    int WS_MIN_NUM = 0;
    int WS_MAX_NUM = 0;
    char WS_FRZ_FLG = ' ';
    char WS_QUARY_FUNC = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPRFHIS BPRFHIS = new BPRFHIS();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPCQFHIS BPCQFHIS = new BPCQFHIS();
    CICQACAC CICQACAC = new CICQACAC();
    BPRFHIS BPRFHIS1 = new BPRFHIS();
    BPRFHIS BPRFHIS2 = new BPRFHIS();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    BPCF7075 BPCF7075 = new BPCF7075();
    BPCG7075 BPCG7075 = new BPCG7075();
    BPCO7075 BPCO7075 = new BPCO7075();
    BPCUIBAL BPCUIBAL = new BPCUIBAL();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRFHISA BPRFHISA = new BPRFHISA();
    CICCUST CICCUST = new CICCUST();
    SCCPRMM SCCSRMM = new SCCPRMM();
    SCRPRMT SCRSRMT = new SCRPRMT();
    SCCGWA SCCGWA;
    BPB7076_AWA_7076 BPB7076_AWA_7076;
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
        CEP.TRC(SCCGWA, "BPOT7076 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB7076_AWA_7076>");
        BPB7076_AWA_7076 = (BPB7076_AWA_7076) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        if ((BPB7076_AWA_7076.INP_NUB != 0) 
            && (BPB7076_AWA_7076.STR_DT != 0 
            || BPB7076_AWA_7076.END_DT != 0 
            || BPB7076_AWA_7076.ROW_NUM != 0 
            || BPB7076_AWA_7076.PAGE_NO != 0)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_STR_DT = BPB7076_AWA_7076.STR_DT;
        WS_END_DT = BPB7076_AWA_7076.END_DT;
        WS_DRCR_FLG = BPB7076_AWA_7076.DRCR_FLG;
        WS_STS = BPB7076_AWA_7076.STS;
        WS_TX_MMO = BPB7076_AWA_7076.TX_MMO;
        if (BPB7076_AWA_7076.INP_NUB != 0) {
            WS_QUARY_FUNC = '1';
        } else {
            WS_QUARY_FUNC = '2';
        }
        if (BPB7076_AWA_7076.ROW_NUM > 1000) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (BPB7076_AWA_7076.ROW_NUM > 0) {
                WS_ROW_NUM = BPB7076_AWA_7076.ROW_NUM;
            } else {
                WS_ROW_NUM = 1000;
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (WS_QUARY_FUNC == '1') {
            B030_QUARY_BY_INPNUM();
            if (pgmRtn) return;
        } else {
            B030_QUARY_BY_DT();
            if (pgmRtn) return;
        }
    }
    public void B030_QUARY_BY_INPNUM() throws IOException,SQLException,Exception {

package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZUIHI1 {
    boolean pgmRtn = false;
    String CPN_R_INQ_FHIST = "BP-R-INQ-FHIST";
    String CPN_R_PROC_NHIST = "BP-R-PROC-NHIST";
    String CPN_R_INQ_FHISA = "BP-R-INQ-FHISA";
    int K_MAX_OUTPUT_CNT = 500;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    double WS_CUR_BAL = 0;
    int WS_CUR_CNT = 0;
    int WS_COND_CNT = 0;
    char WS_AC_COND = ' ';
    char WS_FND_FLG = ' ';
    int WS_CCY_CNT = 0;
    int WS_REC_CNT = 0;
    BPZUIHI1_WS_CCY_BALS[] WS_CCY_BALS = new BPZUIHI1_WS_CCY_BALS[100];
    BPZUIHI1_WS_TS_QUEUE WS_TS_QUEUE = new BPZUIHI1_WS_TS_QUEUE();
    char WS_FHIST_FLG = ' ';
    char WS_NHIST_FLG = ' ';
    char WS_INQ_FHIST = ' ';
    char WS_INQ_NHIST = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPRNHIST BPRNHIST = new BPRNHIST();
    BPCTHIST BPCTHIST = new BPCTHIST();
    BPRFHISA BPRFHISA = new BPRFHISA();
    BPCIFHSA BPCIFHSA = new BPCIFHSA();
    BPCUIBAL BPCUIBAL = new BPCUIBAL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCUIHIS BPCUIHIS;
    public BPZUIHI1() {
        for (int i=0;i<100;i++) WS_CCY_BALS[i] = new BPZUIHI1_WS_CCY_BALS();
    }
    public void MP(SCCGWA SCCGWA, BPCUIHIS BPCUIHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUIHIS = BPCUIHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUIHI1 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCUIHIS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_HIST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUIHIS);
    }
    public void B020_BROWSE_HIST() throws IOException,SQLException,Exception {
        R000_ADD_PAGE_TITLE();
        if (pgmRtn) return;
        R000_STR_BPTFHIST();
        if (pgmRtn) return;
        R000_RX_BPTFHIST();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (WS_FHIST_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            if (BPCUIHIS.DATA.CCY.equalsIgnoreCase(BPRFHIST.TX_CCY)) {
                WS_CNT += 1;
                R000_OUTPUT_FHIST_REC();
                if (pgmRtn) return;
            }
            R000_RX_BPTFHIST();
            if (pgmRtn) return;
        }
        R000_EBR_BPTFHIST();
        if (pgmRtn) return;
    }
    public void R000_GET_AC_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUIBAL);
        BPCUIBAL.INPUT.AC_DT = BPRFHIST.KEY.AC_DT;
        BPCUIBAL.INPUT.JRNNO = BPRFHIST.KEY.JRNNO;
        BPCUIBAL.INPUT.JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
        BPCUIBAL.INPUT.AC_IN = BPRFHIST.KEY.AC;
        BPCUIBAL.INPUT.CCY = BPRFHIST.TX_CCY;
        BPCUIBAL.INPUT.CCY_TYP = BPRFHIST.TX_CCY_TYPE;
        BPCUIBAL.INPUT.TX_AMT_IN = BPRFHIST.TX_AMT;
        BPCUIBAL.INPUT.TX_DRCRFLG = BPRFHIST.DRCRFLG;
        if (BPCUIBAL.INPUT.AC_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            S000_CALL_BPZUIBAL();
            if (pgmRtn) return;
            WS_CUR_BAL = BPCUIBAL.OUTPUT.BAL_E;
        }
    }
    public void S000_CALL_BPZUIBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INQ-BAL", BPCUIBAL);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUIBAL.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUIBAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUIHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_OUTPUT_FHIST_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TS_QUEUE);
        WS_TS_QUEUE.WS_TS_TX_DT = BPRFHIST.TX_DT;
        WS_TS_QUEUE.WS_TS_AC_DT = BPRFHIST.KEY.AC_DT;
        WS_TS_QUEUE.WS_TS_JRNNO = BPRFHIST.KEY.JRNNO;
        WS_TS_QUEUE.WS_TS_JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
        WS_TS_QUEUE.WS_TS_AC = BPRFHIST.KEY.AC;
        WS_TS_QUEUE.WS_TS_REF_NO = BPRFHIST.REF_NO;
        WS_TS_QUEUE.WS_TS_TX_TOOL = BPRFHIST.TX_TOOL;
        WS_TS_QUEUE.WS_TS_APP_MMO = BPRFHIST.APP_MMO;
        WS_TS_QUEUE.WS_TS_TX_MMO = BPRFHIST.TX_MMO;
        WS_TS_QUEUE.WS_TS_TX_CD = BPRFHIST.TX_CD;
        WS_TS_QUEUE.WS_TS_TX_BR = BPRFHIST.TX_BR;
        WS_TS_QUEUE.WS_TS_TX_DP = BPRFHIST.TX_DP;
        WS_TS_QUEUE.WS_TS_TX_TLR = BPRFHIST.TX_TLR;
        WS_TS_QUEUE.WS_TS_SUP1 = BPRFHIST.SUP1;
        WS_TS_QUEUE.WS_TS_SUP2 = BPRFHIST.SUP2;
        WS_TS_QUEUE.WS_TS_TX_CHNL = BPRFHIST.TX_CHNL;
        WS_TS_QUEUE.WS_TS_FIN_FLG = 'F';
        WS_TS_QUEUE.WS_TS_VCHNO = BPRFHIST.VCHNO;
        WS_TS_QUEUE.WS_TS_CI_NO = BPRFHIST.CI_NO;
        WS_TS_QUEUE.WS_TS_DRCR_FLG = BPRFHIST.DRCRFLG;
        WS_TS_QUEUE.WS_TS_TX_CCY = BPRFHIST.TX_CCY;
        WS_TS_QUEUE.WS_TS_TX_AMT = BPRFHIST.TX_AMT;
        WS_TS_QUEUE.WS_TS_TX_VAL_DT = BPRFHIST.TX_VAL_DT;
        WS_TS_QUEUE.WS_TS_PROD_CD = BPRFHIST.PROD_CD;
        WS_TS_QUEUE.WS_TS_STS = BPRFHIST.TX_STS;
        WS_TS_QUEUE.WS_TS_TX_TM = BPRFHIST.TX_TM;

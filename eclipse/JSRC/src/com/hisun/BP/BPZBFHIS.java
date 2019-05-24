package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZBFHIS {
    String JIBS_tmp_str[] = new String[10];
    BPCBFHIS_OUTPUT OUTPUT;
    boolean pgmRtn = false;
    String CPN_R_INQ_FHIST = "BP-R-INQ-FHIST";
    int WS_COND_CNT = 0;
    int WS_J = 0;
    short WS_OUTPUT_NUM = 0;
    short WS_INPUT_NUM = 0;
    char WS_END_BROWSE = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCBFHIS BPCBFHIS;
    public void MP(SCCGWA SCCGWA, BPCBFHIS BPCBFHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCBFHIS = BPCBFHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZBFHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        IBS.init(SCCGWA, BPCIFHIS);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCBFHIS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_PROCESS();
        if (pgmRtn) return;
        B030_CHECK_OUTPUT_PROCESS();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_INPUT_NUM = (short) (BPCBFHIS.INPUT.END_NUM - BPCBFHIS.INPUT.STR_NUM);
        CEP.TRC(SCCGWA, WS_INPUT_NUM);
        if (WS_INPUT_NUM > 100 
            || WS_INPUT_NUM < 1) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_TOO_LARGE, BPCBFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_COND_CNT = 0;
        if (BPCBFHIS.INPUT.AC_DT != 0) {
            WS_COND_CNT += 1;
        }
        if (BPCBFHIS.INPUT.JRNNO != 0) {
            WS_COND_CNT += 1;
        }
        if (BPCBFHIS.INPUT.AC.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        if (BPCBFHIS.INPUT.REF_NO.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        if (BPCBFHIS.INPUT.TX_TOOL.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        if (BPCBFHIS.INPUT.TX_CD.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        if (BPCBFHIS.INPUT.TX_CCY.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        if (WS_COND_CNT > 1) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INP_ONE_COND, BPCBFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFHIS);
        IBS.init(SCCGWA, BPRFHIST);
        WS_OUTPUT_NUM = 0;
        WS_J = 0;
        WS_END_BROWSE = 'N';
        BPCIFHIS.INPUT.JRNNO = BPCBFHIS.INPUT.JRNNO;
        BPCIFHIS.INPUT.AC = BPCBFHIS.INPUT.AC;
        BPCIFHIS.INPUT.REF_NO = BPCBFHIS.INPUT.REF_NO;
        BPCIFHIS.INPUT.TX_TOOL = BPCBFHIS.INPUT.TX_TOOL;
        BPCIFHIS.INPUT.TX_CD = BPCBFHIS.INPUT.TX_CD;
        BPCIFHIS.INPUT.TX_CCY = BPCBFHIS.INPUT.TX_CCY;
        BPCIFHIS.INPUT.STR_AMT = BPCBFHIS.INPUT.STR_AMT;
        BPCIFHIS.INPUT.END_AMT = BPCBFHIS.INPUT.END_AMT;
        BPCIFHIS.INPUT.STR_AC_DT = BPCBFHIS.INPUT.STR_AC_DT;
        BPCIFHIS.INPUT.END_AC_DT = BPCBFHIS.INPUT.END_AC_DT;
        BPCIFHIS.INPUT.FUNC = '1';
        BPCIFHIS.INPUT.REC_PT = BPRFHIST;
        BPCIFHIS.INPUT.REC_LEN = 690;
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
        BPCIFHIS.INPUT.FUNC = '2';
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE) 
            && WS_END_BROWSE != 'Y') {
            WS_OUTPUT_NUM += 1;
            CEP.TRC(SCCGWA, WS_OUTPUT_NUM);
            if (WS_OUTPUT_NUM >= BPCBFHIS.INPUT.STR_NUM 
                && WS_OUTPUT_NUM <= BPCBFHIS.INPUT.END_NUM) {
                WS_J += 1;
                R010_OUTPUT_PROCESS();
                if (pgmRtn) return;
            }
            if (WS_J > BPCBFHIS.INPUT.END_NUM) {
                WS_END_BROWSE = 'Y';
            }
            BPCIFHIS.INPUT.FUNC = '2';
            S000_CALL_BPZIFHIS();
            if (pgmRtn) return;
        }
        BPCIFHIS.INPUT.FUNC = '3';
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
    }
    public void B030_CHECK_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCBFHIS.NUM);
        if (BPCBFHIS.NUM == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_REC_NOTFND, BPCBFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R010_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_J);
        BPCBFHIS.NUM = (short) WS_J;
        OUTPUT = new BPCBFHIS_OUTPUT();
        BPCBFHIS.OUTPUT.add(OUTPUT);
        OUTPUT.OUT_AC_DT = BPRFHIST.KEY.AC_DT;
        OUTPUT.OUT_JRNNO = BPRFHIST.KEY.JRNNO;
        OUTPUT.OUT_JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
        OUTPUT.OUT_VCHNO = BPRFHIST.VCHNO;
        OUTPUT.OUT_AC = BPRFHIST.KEY.AC;
        OUTPUT.OUT_REF_NO = BPRFHIST.REF_NO;
        OUTPUT.OUT_TX_TOOL = BPRFHIST.TX_TOOL;
        OUTPUT.OUT_CI_NO = BPRFHIST.CI_NO;
        OUTPUT.OUT_TX_CD = BPRFHIST.TX_CD;
        OUTPUT.OUT_TX_CCY = BPRFHIST.TX_CCY;
        OUTPUT.OUT_TX_AMT = BPRFHIST.TX_AMT;
        OUTPUT.OUT_TX_MMO = BPRFHIST.TX_MMO;
    }
    public void S000_CALL_BPZIFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_INQ_FHIST, BPCIFHIS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL) 
            && !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCBFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCBFHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCBFHIS = ");
            CEP.TRC(SCCGWA, BPCBFHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

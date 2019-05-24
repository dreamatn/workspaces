package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZBNHIS {
    String JIBS_tmp_str[] = new String[10];
    BPCBNHIS_OUTPUT OUTPUT;
    boolean pgmRtn = false;
    String CPN_R_PROC_NHIST = "BP-R-PROC-NHIST";
    int WS_COND_CNT = 0;
    short WS_COND_NUM = 0;
    short WS_I = 0;
    short WS_J = 0;
    char WS_NHIST_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRNHIST BPRNHIST = new BPRNHIST();
    BPCTHIST BPCTHIST = new BPCTHIST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    BPCBNHIS BPCBNHIS;
    public void MP(SCCGWA SCCGWA, BPCBNHIS BPCBNHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCBNHIS = BPCBNHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZBNHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCBNHIS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_NHIST();
        if (pgmRtn) return;
        B030_CHECK_OUPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_COND_CNT = 0;
        WS_COND_NUM = 0;
        WS_COND_NUM = (short) (BPCBNHIS.INPUT.END_NUM - BPCBNHIS.INPUT.STR_NUM);
        CEP.TRC(SCCGWA, WS_COND_NUM);
        if (WS_COND_NUM < 1 
            || WS_COND_NUM > 100) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_TOO_LARGE, BPCBNHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCBNHIS.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCBNHIS.INPUT.AC);
        CEP.TRC(SCCGWA, BPCBNHIS.INPUT.REF_NO);
        CEP.TRC(SCCGWA, BPCBNHIS.INPUT.TX_TOOL);
        CEP.TRC(SCCGWA, BPCBNHIS.INPUT.TX_CD);
        CEP.TRC(SCCGWA, BPCBNHIS.INPUT.STR_AC_DT);
        CEP.TRC(SCCGWA, BPCBNHIS.INPUT.END_AC_DT);
        CEP.TRC(SCCGWA, BPCBNHIS.INPUT.STR_NUM);
        CEP.TRC(SCCGWA, BPCBNHIS.INPUT.END_NUM);
        if (BPCBNHIS.INPUT.JRNNO != 0) {
            WS_COND_CNT += 1;
        }
        if (BPCBNHIS.INPUT.AC.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        if (BPCBNHIS.INPUT.REF_NO.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        if (BPCBNHIS.INPUT.TX_TOOL.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        if (BPCBNHIS.INPUT.TX_CD.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        if (WS_COND_CNT > 1) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INP_ONE_COND, BPCBNHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_NHIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTHIST);
        IBS.init(SCCGWA, BPRNHIST);
        WS_I = 0;
        WS_J = 0;
        WS_NHIST_FLG = 'N';
        BPCTHIST.INFO.JRNNO = BPCBNHIS.INPUT.JRNNO;
        BPCTHIST.INFO.AC = BPCBNHIS.INPUT.AC;
        BPCTHIST.INFO.REF_NO = BPCBNHIS.INPUT.REF_NO;
        BPCTHIST.INFO.TX_TOOL = BPCBNHIS.INPUT.TX_TOOL;
        BPCTHIST.INFO.TX_CD = BPCBNHIS.INPUT.TX_CD;
        BPCTHIST.INFO.STR_AC_DT = BPCBNHIS.INPUT.STR_AC_DT;
        BPCTHIST.INFO.END_AC_DT = BPCBNHIS.INPUT.END_AC_DT;
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        BPCTHIST.INFO.FUNC = '1';
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
        BPCTHIST.INFO.FUNC = '2';
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTHIST.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE) 
            && BPCTHIST.RETURN_INFO != 'N' 
            && WS_NHIST_FLG != 'Y') {
            WS_I += 1;
            if (WS_I >= BPCBNHIS.INPUT.STR_NUM 
                && WS_I <= BPCBNHIS.INPUT.END_NUM) {
                WS_J += 1;
                R010_OUTPUT_DATA_PROCESS();
                if (pgmRtn) return;
            }
            if (WS_I > BPCBNHIS.INPUT.END_NUM) {
                WS_NHIST_FLG = 'Y';
            }
            BPCTHIST.INFO.FUNC = '2';
            S000_CALL_BPZTHIST();
            if (pgmRtn) return;
        }
        BPCTHIST.INFO.FUNC = '3';
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
    }
    public void B030_CHECK_OUPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCBNHIS.OUT_NUM);
        if (BPCBNHIS.OUT_NUM == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_REC_NOTFND, BPCBNHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R010_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        BPCBNHIS.OUT_NUM = WS_J;
        OUTPUT = new BPCBNHIS_OUTPUT();
        BPCBNHIS.OUTPUT.add(OUTPUT);
        OUTPUT.O_AC_DT = BPRNHIST.KEY.AC_DT;
        OUTPUT.O_JRNNO = BPRNHIST.KEY.JRNNO;
        OUTPUT.O_JRN_SEQ = BPRNHIST.KEY.JRN_SEQ;
        OUTPUT.O_AC = BPRNHIST.AC;
        OUTPUT.O_CI_NO = BPRNHIST.CI_NO;
        OUTPUT.O_REF_NO = BPRNHIST.REF_NO;
        OUTPUT.O_TX_TOOL = BPRNHIST.TX_TOOL;
        OUTPUT.O_TX_CD = BPRNHIST.TX_CD;
        OUTPUT.O_TX_STS = BPRNHIST.TX_STS;
    }
    public void S000_CALL_BPZTHIST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_PROC_NHIST, BPCTHIST);
        CEP.TRC(SCCGWA, BPCTHIST.RC);
        CEP.TRC(SCCGWA, BPCTHIST.RETURN_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCBNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCBNHIS = ");
            CEP.TRC(SCCGWA, BPCBNHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

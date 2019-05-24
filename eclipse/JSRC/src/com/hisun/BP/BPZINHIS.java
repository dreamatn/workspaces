package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZINHIS {
    BPCINHIS_DETAIL DETAIL;
    boolean pgmRtn = false;
    String CPN_R_PROC_NHISD = "BP-R-PROC-NHISD";
    String CPN_R_PROC_NHIST = "BP-R-PROC-NHIST";
    int WS_CNT = 0;
    char WS_COND_Y = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRNHIST BPRNHIST = new BPRNHIST();
    BPCTHIST BPCTHIST = new BPCTHIST();
    BPRNHISD BPRNHISD = new BPRNHISD();
    BPCTHISD BPCTHISD = new BPCTHISD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    BPCINHIS BPCINHIS;
    public void MP(SCCGWA SCCGWA, BPCINHIS BPCINHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCINHIS = BPCINHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZINHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRNHIST);
        IBS.init(SCCGWA, BPCTHIST);
        IBS.init(SCCGWA, BPRNHISD);
        IBS.init(SCCGWA, BPCTHISD);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCINHIS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_INQUIRE_NHIS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCINHIS.INPUT.AC_DT);
        CEP.TRC(SCCGWA, BPCINHIS.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCINHIS.INPUT.JRN_SEQ);
        if (BPCINHIS.INPUT.AC_DT == 0 
            || BPCINHIS.INPUT.JRNNO == 0 
            || BPCINHIS.INPUT.JRN_SEQ == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCINHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQUIRE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTHIST);
        IBS.init(SCCGWA, BPRNHIST);
        BPRNHIST.KEY.AC_DT = BPCINHIS.INPUT.AC_DT;
        BPRNHIST.KEY.JRNNO = BPCINHIS.INPUT.JRNNO;
        BPRNHIST.KEY.JRN_SEQ = BPCINHIS.INPUT.JRN_SEQ;
        BPCTHIST.INFO.FUNC = '4';
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
        R010_OUTPUT_NHIST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTHISD);
        IBS.init(SCCGWA, BPRNHISD);
        WS_CNT = 0;
        BPCTHISD.INFO.AC_DT = BPCINHIS.INPUT.AC_DT;
        BPCTHISD.INFO.JRNNO = BPCINHIS.INPUT.JRNNO;
        BPCTHISD.INFO.JRN_SEQ = BPCINHIS.INPUT.JRN_SEQ;
        BPCTHISD.INFO.FUNC = '1';
        BPCTHISD.PTR = BPRNHISD;
        BPCTHISD.LEN = 331;
        S000_CALL_BPZTHISD();
        if (pgmRtn) return;
        BPCTHISD.INFO.FUNC = '2';
        BPCTHISD.PTR = BPRNHISD;
        BPCTHISD.LEN = 331;
        S000_CALL_BPZTHISD();
        if (pgmRtn) return;
        while (BPCTHISD.RETURN_INFO != 'N') {
            WS_CNT += 1;
            R020_OUTPUT_NHISD();
            if (pgmRtn) return;
            BPCTHISD.INFO.FUNC = '2';
            S000_CALL_BPZTHISD();
            if (pgmRtn) return;
        }
        BPCTHISD.INFO.FUNC = '3';
        S000_CALL_BPZTHISD();
        if (pgmRtn) return;
    }
    public void R010_OUTPUT_NHIST() throws IOException,SQLException,Exception {
        BPCINHIS.OUTPUT.AC = BPRNHIST.AC;
        BPCINHIS.OUTPUT.CI_NO = BPRNHIST.CI_NO;
        BPCINHIS.OUTPUT.REF_NO = BPRNHIST.REF_NO;
        BPCINHIS.OUTPUT.TX_TOOL = BPRNHIST.TX_TOOL;
        BPCINHIS.OUTPUT.TX_TYP = BPRNHIST.TX_TYP;
        BPCINHIS.OUTPUT.TX_TYP_CD = BPRNHIST.TX_TYP_CD;
        BPCINHIS.OUTPUT.APP_MMO = BPRNHIST.APP_MMO;
        BPCINHIS.OUTPUT.TX_CD = BPRNHIST.TX_CD;
        BPCINHIS.OUTPUT.TX_STS = BPRNHIST.TX_STS;
        BPCINHIS.OUTPUT.TX_DT = BPRNHIST.TX_DT;
        BPCINHIS.OUTPUT.TX_TM = BPRNHIST.TX_TM;
        BPCINHIS.OUTPUT.TX_RMK = BPRNHIST.TX_RMK;
        BPCINHIS.OUTPUT.TX_CHNL = BPRNHIST.TX_CHNL;
        BPCINHIS.OUTPUT.TX_TLR = BPRNHIST.TX_TLR;
        BPCINHIS.OUTPUT.TX_BR = BPRNHIST.TX_BR;
        BPCINHIS.OUTPUT.TX_DP = BPRNHIST.TX_DP;
        BPCINHIS.OUTPUT.SUP1 = BPRNHIST.SUP1;
        BPCINHIS.OUTPUT.SUP2 = BPRNHIST.SUP2;
    }
    public void R020_OUTPUT_NHISD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CNT);
        BPCINHIS.OUTPUT.NUM = (short) WS_CNT;
        DETAIL = new BPCINHIS_DETAIL();
        BPCINHIS.OUTPUT.DETAIL.add(DETAIL);
        DETAIL.CNAME = BPRNHISD.FLD_CDESC;
        DETAIL.ENAME = BPRNHISD.FLD_EDESC;
        DETAIL.OLD_TXT = BPRNHISD.FLD_DAT_OLD;
        DETAIL.NEW_TXT = BPRNHISD.FLD_DAT_NEW;
    }
    public void S000_CALL_BPZTHIST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_PROC_NHIST, BPCTHIST);
        CEP.TRC(SCCGWA, BPCTHIST.RC);
        CEP.TRC(SCCGWA, BPCTHIST.RETURN_INFO);
        if (BPCTHIST.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCINHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTHISD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_PROC_NHISD, BPCTHISD);
        CEP.TRC(SCCGWA, BPCTHISD.RC);
        CEP.TRC(SCCGWA, BPCTHISD.RETURN_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCINHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCINHIS = ");
            CEP.TRC(SCCGWA, BPCINHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

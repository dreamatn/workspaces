package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQRDT {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_LAST_DATE = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFCTR BPRFCTR = new BPRFCTR();
    BPCRFCTR BPCRFCTR = new BPCRFCTR();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPCRFSCH BPCRFSCH = new BPCRFSCH();
    SCCGWA SCCGWA;
    BPCPQRDT BPCPQRDT;
    public void MP(SCCGWA SCCGWA, BPCPQRDT BPCPQRDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQRDT = BPCPQRDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQRDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQRDT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_INQ_LAST_DATE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPQRDT.REL_CTRT_NO.trim().length() == 0 
            && BPCPQRDT.REL_CTRT_NO.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQRDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_LAST_DATE() throws IOException,SQLException,Exception {
        WS_LAST_DATE = 0;
        CEP.TRC(SCCGWA, BPCPQRDT.REL_CTRT_NO);
        IBS.init(SCCGWA, BPCRFCTR);
        IBS.init(SCCGWA, BPRFCTR);
        BPRFCTR.REL_CTRT_NO = BPCPQRDT.REL_CTRT_NO;
        BPCRFCTR.INFO.FUNC = 'B';
        BPCRFCTR.INFO.OPT = '4';
        S000_CALL_BPZRFCTR();
        if (pgmRtn) return;
        BPCRFCTR.INFO.FUNC = 'N';
        S000_CALL_BPZRFCTR();
        if (pgmRtn) return;
        while (BPCRFCTR.RETURN_INFO != 'N') {
            if (BPRFCTR.REAL_AC_DATE > WS_LAST_DATE) {
                WS_LAST_DATE = BPRFCTR.REAL_AC_DATE;
            }
            BPCRFCTR.INFO.FUNC = 'N';
            S000_CALL_BPZRFCTR();
            if (pgmRtn) return;
        }
        BPCRFCTR.INFO.FUNC = 'E';
        S000_CALL_BPZRFCTR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_LAST_DATE);
        IBS.init(SCCGWA, BPCRFSCH);
        IBS.init(SCCGWA, BPRFSCH);
        BPRFSCH.REL_CTRT_NO = BPCPQRDT.REL_CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'B';
        BPCRFSCH.INFO.OPT = '4';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        BPCRFSCH.INFO.FUNC = 'N';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        while (BPCRFSCH.RETURN_INFO != 'N') {
            if (BPRFSCH.LAST_SETT_DATE > WS_LAST_DATE) {
                WS_LAST_DATE = BPRFSCH.LAST_SETT_DATE;
            }
            BPCRFSCH.INFO.FUNC = 'N';
            S000_CALL_BPZRFSCH();
            if (pgmRtn) return;
        }
        BPCRFSCH.INFO.FUNC = 'E';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_LAST_DATE);
        BPCPQRDT.LAST_DATE = WS_LAST_DATE;
    }
    public void S000_CALL_BPZRFCTR() throws IOException,SQLException,Exception {
        BPCRFCTR.INFO.POINTER = BPRFCTR;
        BPCRFCTR.INFO.REC_LEN = 889;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-FEECTR", BPCRFCTR);
        if (BPCRFCTR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFCTR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRFSCH() throws IOException,SQLException,Exception {
        BPCRFSCH.INFO.POINTER = BPRFSCH;
        BPCRFSCH.INFO.REC_LEN = 816;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-FEESCH", BPCRFSCH);
        if (BPCRFSCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFSCH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQRDT.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQRDT = ");
            CEP.TRC(SCCGWA, BPCPQRDT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

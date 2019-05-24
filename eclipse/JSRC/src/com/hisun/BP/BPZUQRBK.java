package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUQRBK {
    boolean pgmRtn = false;
    int BP_BR = 999999;
    String WS_ERR_MSG = " ";
    int WS_IDX = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRRTBK BPRRTBK = new BPRRTBK();
    BPCRRTBK BPCRRTBK = new BPCRRTBK();
    SCCGWA SCCGWA;
    BPCUQRBK BPCUQRBK;
    public void MP(SCCGWA SCCGWA, BPCUQRBK BPCUQRBK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUQRBK = BPCUQRBK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUQRBK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUQRBK.OUTPUT_DATA);
        CEP.TRC(SCCGWA, BPCUQRBK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCUQRBK.FUNC_CODE == 'Q') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            B030_CHK_RTBK_REC_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUQRBK.INPUT_DATA.JRNNO);
        if (BPCUQRBK.FUNC_CODE != 'Q' 
            && BPCUQRBK.FUNC_CODE != 'C') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCUQRBK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUQRBK.FUNC_CODE == 'Q') {
            if (BPCUQRBK.INPUT_DATA.JRNNO == 0 
                || BPCUQRBK.INPUT_DATA.JRNNO == ' ') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_JRNNO_MUST_INPUT, BPCUQRBK.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCUQRBK.FUNC_CODE == 'C') {
            if (BPCUQRBK.INPUT_DATA.DATE == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_INVALID, BPCUQRBK.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCUQRBK.INPUT_DATA.RATE_ID_INPUT.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_ID_MUST_INPUT, BPCUQRBK.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRTBK);
        IBS.init(SCCGWA, BPCRRTBK);
        WS_IDX = 1;
        BPCRRTBK.INFO.FUNC = 'B';
        BPCRRTBK.INFO.OPT = 'S';
        BPRRTBK.KEY.DATE = BPCUQRBK.INPUT_DATA.DATE;
        BPRRTBK.KEY.JRNNO = BPCUQRBK.INPUT_DATA.JRNNO;
        BPCRRTBK.INFO.POINTER = BPRRTBK;
        BPCRRTBK.LEN = 111;
        S000_CALL_BPZRRTBK();
        if (pgmRtn) return;
        if (BPCRRTBK.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_RECORD_NOTFND, BPCUQRBK.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            while (BPCRRTBK.RETURN_INFO != 'N' 
                && WS_IDX <= 366) {
                BPCRRTBK.INFO.OPT = 'N';
                S000_CALL_BPZRRTBK();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCRRTBK.RETURN_INFO);
                CEP.TRC(SCCGWA, BPRRTBK.RATE_ID);
                CEP.TRC(SCCGWA, BPRRTBK.KEY.BATCH_SEQ);
                if (BPCRRTBK.RETURN_INFO != 'N') {
                    BPCUQRBK.OUTPUT_DATA.RATE_INFO.BR = BP_BR;
                    BPCUQRBK.OUTPUT_DATA.RATE_INFO.CCY = BPRRTBK.CCY;
                    BPCUQRBK.OUTPUT_DATA.RATE_INFO.BASE_TYPE = BPRRTBK.BASE_TYP;
                    BPCUQRBK.OUTPUT_DATA.RATE_INFO.TENOR = BPRRTBK.TENOR;
                    BPCUQRBK.OUTPUT_DATA.RATE_INFO.RATE_ID = BPRRTBK.RATE_ID;
                    BPCUQRBK.OUTPUT_DATA.RATE_INFO.RATE_DETAIL[WS_IDX-1].BATCH_SEQ = BPRRTBK.KEY.BATCH_SEQ;
                    BPCUQRBK.OUTPUT_DATA.RATE_INFO.RATE_DETAIL[WS_IDX-1].FR_DT = BPRRTBK.FR_DT;
                    BPCUQRBK.OUTPUT_DATA.RATE_INFO.RATE_DETAIL[WS_IDX-1].TO_DT = BPRRTBK.TO_DT;
                    BPCUQRBK.OUTPUT_DATA.RATE_INFO.RATE_DETAIL[WS_IDX-1].OLD_RATE = BPRRTBK.OLD_RATE;
                    BPCUQRBK.OUTPUT_DATA.RATE_INFO.RATE_DETAIL[WS_IDX-1].NEW_RATE = BPRRTBK.NEW_RATE;
                    WS_IDX = WS_IDX + 1;
                } else {
                    if (WS_IDX == 1) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_RECORD_NOTFND, BPCUQRBK.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, BPCUQRBK.RC);
        BPCRRTBK.INFO.OPT = 'E';
        S000_CALL_BPZRRTBK();
        if (pgmRtn) return;
    }
    public void B030_CHK_RTBK_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRTBK);
        IBS.init(SCCGWA, BPCRRTBK);
        BPCUQRBK.OUTPUT_DATA.CHK_RESULT = 'N';
        BPCRRTBK.INFO.FUNC = 'B';
        BPCRRTBK.INFO.OPT = 'S';
        BPRRTBK.KEY.DATE = BPCUQRBK.INPUT_DATA.DATE;
        BPRRTBK.RATE_ID = BPCUQRBK.INPUT_DATA.RATE_ID_INPUT;
        BPCRRTBK.INFO.POINTER = BPRRTBK;
        BPCRRTBK.LEN = 111;
        S000_CALL_BPZRRTBK();
        if (pgmRtn) return;
        if (BPCRRTBK.RETURN_INFO == 'N') {
            BPCUQRBK.OUTPUT_DATA.CHK_RESULT = 'N';
        } else {
            while (BPCRRTBK.RETURN_INFO != 'N' 
                && BPCUQRBK.OUTPUT_DATA.CHK_RESULT != 'F') {
                BPCRRTBK.INFO.OPT = 'N';
                S000_CALL_BPZRRTBK();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCRRTBK.RETURN_INFO);
                CEP.TRC(SCCGWA, BPRRTBK.RATE_ID);
                if (BPCRRTBK.RETURN_INFO != 'N' 
                    && BPCUQRBK.INPUT_DATA.DATE == BPRRTBK.KEY.DATE 
                    && BPCUQRBK.INPUT_DATA.RATE_ID_INPUT.equalsIgnoreCase(BPRRTBK.RATE_ID)) {
                    BPCUQRBK.OUTPUT_DATA.CHK_RESULT = 'F';
                }
            }
        }
        CEP.TRC(SCCGWA, BPCUQRBK.RC);
        BPCRRTBK.INFO.OPT = 'E';
        S000_CALL_BPZRRTBK();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRRTBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTBK-MAINT", BPCRRTBK);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUQRBK.RC.RTNCODE != 0) {
            CEP.TRC(SCCGWA, "BPCUQRBK = ");
            CEP.TRC(SCCGWA, BPCUQRBK);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

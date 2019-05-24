package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQTLH {
    String CPN_TDTL_MT = "BP-R-TLR-HOL-M      ";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTDTL BPRTDTL = new BPRTDTL();
    BPCRTLTS BPCRTLTS = new BPCRTLTS();
    SCCGWA SCCGWA;
    BPCPQTLH BPCPQTLH;
    public void MP(SCCGWA SCCGWA, BPCPQTLH BPCPQTLH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQTLH = BPCPQTLH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPQTLH return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQTLH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PROCESS();
        B030_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPQTLH.INFO.TLR.trim().length() == 0 
            || BPCPQTLH.INFO.TYPE == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT, BPCPQTLH.RC);
            return;
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTDTL);
        IBS.init(SCCGWA, BPCRTLTS);
        BPCRTLTS.INFO.FUNC = 'Q';
        BPRTDTL.KEY.TLR = BPCPQTLH.INFO.TLR;
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.TYPE);
        BPRTDTL.KEY.TYPE = BPCPQTLH.INFO.TYPE;
        BPCRTLTS.INFO.POINTER = BPRTDTL;
        BPCRTLTS.INFO.LEN = 93;
        S000_CALL_BPZRTLTS();
        if (BPCRTLTS.RETURN_INFO == 'F') {
            BPCPQTLH.RC.RC_CODE = 0;
        } else if (BPCRTLTS.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCPQTLH.RC);
            return;
        } else {
        }
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCPQTLH.INFO.BEGIN_DT = BPRTDTL.BEGIN_DT;
        BPCPQTLH.INFO.BEGIN_TIME = BPRTDTL.BEGIN_TIME;
        BPCPQTLH.INFO.END_DT = BPRTDTL.END_DT;
        BPCPQTLH.INFO.END_TIME = BPRTDTL.END_TIME;
        BPCPQTLH.INFO.SIGN_FLG = BPRTDTL.SIGN_FLG;
        BPCPQTLH.INFO.OUT_BR = BPRTDTL.OUT_BR;
        BPCPQTLH.INFO.OUT_EFF_DT = BPRTDTL.OUT_EFF_DATE;
        BPCPQTLH.INFO.IN_BR = BPRTDTL.IN_BR;
        BPCPQTLH.INFO.IN_EFF_DT = BPRTDTL.IN_EFF_DATE;
        BPCPQTLH.INFO.RUN_FLG = BPRTDTL.RUN_FLG;
        CEP.TRC(SCCGWA, BPCPQTLH);
    }
    public void S000_CALL_BPZRTLTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_TDTL_MT, BPCRTLTS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQTLH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQTLH = ");
            CEP.TRC(SCCGWA, BPCPQTLH);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

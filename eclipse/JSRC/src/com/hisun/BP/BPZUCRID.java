package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUCRID {
    int BP_BR = 999999;
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRRTID BPRRTID = new BPRRTID();
    BPCRMRTD BPCRMRTD = new BPCRMRTD();
    BPCRINTM BPCRINTM = new BPCRINTM();
    SCCGWA SCCGWA;
    BPCUCRID BPCUCRID;
    public void MP(SCCGWA SCCGWA, BPCUCRID BPCUCRID) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUCRID = BPCUCRID;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZUCRID return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUCRID);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CHECK_RATE_EXIST();
        B030_CHECK_RATE_REC_EXIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCUCRID.RATE_ID.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_ID_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHECK_RATE_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMRTD);
        IBS.init(SCCGWA, BPRRTID);
        BPCRMRTD.INFO.FUNC = 'Q';
        BPRRTID.KEY.ID = BPCUCRID.RATE_ID;
        BPCRMRTD.INFO.POINTER = BPRRTID;
        S000_CALL_BPZRMRTD();
        if (BPCRMRTD.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_ID_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_RATE_REC_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.FUNC = 'I';
        BPCRINTM.BR = BP_BR;
        BPCRINTM.BASE_TYP = BPRRTID.BASE_TYP;
        BPCRINTM.TENOR = BPRRTID.TENOR;
        BPCRINTM.CCY = BPRRTID.CCY;
        S000_CALL_BPZRINTM();
        if (BPCRINTM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        }
        BPCUCRID.CCY = BPCRINTM.CCY;
        BPCUCRID.BASE_TYP = BPCRINTM.BASE_TYP;
        BPCUCRID.TENOR = BPCRINTM.TENOR;
        BPCUCRID.DT = BPCRINTM.DT;
        BPCUCRID.RATE = BPCRINTM.RATE;
    }
    public void S000_CALL_BPZRMRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTID-MAINT", BPCRMRTD);
    }
    public void S000_CALL_BPZRINTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INTR-MAINT", BPCRINTM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUCRID.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUCRID = ");
            CEP.TRC(SCCGWA, BPCUCRID);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

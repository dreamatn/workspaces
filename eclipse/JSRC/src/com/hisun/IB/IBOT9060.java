package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT9060 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCGRATE IBCGRATE = new IBCGRATE();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB9050_AWA_9050 IBB9050_AWA_9050;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBOT9060 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB9050_AWA_9050>");
        IBB9050_AWA_9050 = (IBB9050_AWA_9050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_TRANS_DATA_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB9050_AWA_9050.RAT_TYPE);
        CEP.TRC(SCCGWA, IBB9050_AWA_9050.RAT_TERM);
        CEP.TRC(SCCGWA, IBB9050_AWA_9050.IN_DATE);
        if (IBB9050_AWA_9050.RAT_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.RATE_TYPE_M);
        }
        if (IBB9050_AWA_9050.RAT_TERM.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.RATE_TERM_M);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCGRATE);
        IBCGRATE.RATE_TYPE = IBB9050_AWA_9050.RAT_TYPE;
        IBCGRATE.RATE_TERM = IBB9050_AWA_9050.RAT_TERM;
        IBCGRATE.RATE_PCT = IBB9050_AWA_9050.RAT_PCT;
        IBCGRATE.RATE_SPREAD = IBB9050_AWA_9050.RAT_SPRE;
        IBCGRATE.IN_DATE = IBB9050_AWA_9050.IN_DATE;
        IBCGRATE.BR = IBB9050_AWA_9050.BR;
        IBCGRATE.CCY = IBB9050_AWA_9050.CCY;
        S000_CALL_IBZGRATE();
        if (pgmRtn) return;
    }
    public void S000_CALL_IBZGRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-GET-RATE", IBCGRATE);
        if (IBCGRATE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCGRATE.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4890 {
    String CPN_S_TSTS_MAINTAIN = "BP-S-TSTS-MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTSTS BPCSTSTS = new BPCSTSTS();
    SCCGWA SCCGWA;
    BPC4890 BPC4890;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4890 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC4890 = new BPC4890();
        IBS.init(SCCGWA, BPC4890);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC4890);
        CEP.TRC(SCCGWA, BPC4890);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_STS_AUTH_INF_BROWSE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPC4890.TSTS_APP);
        if (BPC4890.TSTS_APP.equalsIgnoreCase("0")) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void B020_STS_AUTH_INF_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTSTS);
        R000_TRANS_DATA();
        BPCSTSTS.FUNC = 'B';
        S000_CALL_BPZSTSTS();
        CEP.TRC(SCCGWA, BPCSTSTS.DESC);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPCSTSTS.TSTS_APP = BPC4890.TSTS_APP;
    }
    public void S000_CALL_BPZSTSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TSTS_MAINTAIN, BPCSTSTS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

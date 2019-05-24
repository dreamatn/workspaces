package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4894 {
    String K_OUTPUT_FMT = "BP553";
    String CPN_S_TSTS_MAINTAIN = "BP-S-TSTS-MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCSTSTS BPCSTSTS = new BPCSTSTS();
    BPCOTSTS BPCOTSTS = new BPCOTSTS();
    SCCGWA SCCGWA;
    BPC4894 BPC4894;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4894 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC4894 = new BPC4894();
        IBS.init(SCCGWA, BPC4894);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC4894);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_STS_AUTH_INF_DELETE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPC4894.TSTS_APP.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, BPC4894.TSTS_APP);
        }
        if (BPC4894.TSTS_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, BPC4894.TSTS_NO);
        }
    }
    public void B020_STS_AUTH_INF_DELETE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTSTS);
        BPCSTSTS.TSTS_APP = BPC4894.TSTS_APP;
        BPCSTSTS.TSTS_NO = BPC4894.TSTS_NO;
        BPCSTSTS.CHNL = BPC4894.CHNL;
        BPCSTSTS.FUNC = 'D';
        S000_CALL_BPZSTSTS();
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
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

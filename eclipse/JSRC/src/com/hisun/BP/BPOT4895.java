package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4895 {
    String K_OUTPUT_FMT = "BPX01";
    String CPN_S_TSTS_MAINTAIN = "BP-S-TSTS-MAINTAIN  ";
    String CPN_F_SUBS = "SC-SET-SUBS-TRANS   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTSTS BPCSTSTS = new BPCSTSTS();
    BPCOTSTB BPCOTSTB = new BPCOTSTB();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    BPC4895 BPC4895;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4895 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC4895 = new BPC4895();
        IBS.init(SCCGWA, BPC4895);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC4895);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_STS_AUTH_INF_INQUIRY();
        B030_SET_SUBS_TRANS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPC4895.TSTS_APP.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPC4895.TSTS_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_STS_AUTH_INF_INQUIRY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTSTS);
        BPCSTSTS.TSTS_APP = BPC4895.TSTS_APP;
        BPCSTSTS.TSTS_NO = BPC4895.TSTS_NO;
        BPCSTSTS.FUNC = 'Q';
        BPCSTSTS.SUB_IND = 'Y';
        S000_CALL_BPZSTSTS();
    }
    public void B030_SET_SUBS_TRANS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPC4895.FUNC);
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        if (BPC4895.FUNC == 'Q') {
            SCCSUBS.TR_CODE = 4893;
        } else if (BPC4895.FUNC == 'M') {
            SCCSUBS.TR_CODE = 4897;
        } else if (BPC4895.FUNC == 'D') {
            SCCSUBS.TR_CODE = 4894;
        } else {
        }
        S000_CALL_SCZSUBS();
    }
    public void S000_CALL_BPZSTSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TSTS_MAINTAIN, BPCSTSTS);
    }
    public void S000_CALL_SCZSUBS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS   ", SCCSUBS);
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

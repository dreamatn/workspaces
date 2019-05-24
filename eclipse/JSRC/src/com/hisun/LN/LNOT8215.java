package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8215 {
    int K_ZERO = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    LNOT8215_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT8215_WS_TEMP_VARIABLE();
    LNOT8215_WS_MSG_INFO WS_MSG_INFO = new LNOT8215_WS_MSG_INFO();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCINQPB LNCINQPB = new LNCINQPB();
    SCCGWA SCCGWA;
    LNB5210_AWA_5210 LNB5210_AWA_5210;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT8215 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5210_AWA_5210>");
        LNB5210_AWA_5210 = (LNB5210_AWA_5210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_SVR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB5210_AWA_5210.CTA_NO.compareTo(SPACE) <= 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCINQPB);
        CEP.TRC(SCCGWA, LNCINQPB.COMM_DATA.INPUT_AMT);
        LNCINQPB.COMM_DATA.CTA_NO = LNB5210_AWA_5210.CTA_NO;
        LNCINQPB.COMM_DATA.INPUT_AMT = LNB5210_AWA_5210.PRIN_AMT;
        LNCINQPB.COMM_DATA.TERM_NO = LNB5210_AWA_5210.TERM_NO;
        LNCINQPB.COMM_DATA.VAL_DT = LNB5210_AWA_5210.VAL_DAT;
        LNCINQPB.COMM_DATA.DUE_DT = LNB5210_AWA_5210.DUE_DAT;
        LNCINQPB.COMM_DATA.TRAN_SEQ = LNB5210_AWA_5210.TRAN_SEQ;
        CEP.TRC(SCCGWA, LNCINQPB.COMM_DATA.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNCINQPB.COMM_DATA.INPUT_AMT);
        S000_CALL_LNZINQPB();
    }
    public void S000_CALL_LNZINQPB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-INQ-PART-BANK", LNCINQPB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

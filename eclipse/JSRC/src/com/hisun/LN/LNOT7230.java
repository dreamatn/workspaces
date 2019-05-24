package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7230 {
    LNOT7230_WS_MSGID WS_MSGID = new LNOT7230_WS_MSGID();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNCSDARL LNCSDARL = new LNCSDARL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    LNB7230_AWA_7230 LNB7230_AWA_7230;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT7230 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7230_AWA_7230>");
        LNB7230_AWA_7230 = (LNB7230_AWA_7230) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_UPD_PTR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_UPD_PTR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSDARL);
        CEP.TRC(SCCGWA, LNB7230_AWA_7230.CONT_NO);
        CEP.TRC(SCCGWA, LNB7230_AWA_7230.WHD_RUL);
        LNCSDARL.REC_DATA.CONTRACT_NO = LNB7230_AWA_7230.CONT_NO;
        LNCSDARL.FUNC = '5';
        CEP.TRC(SCCGWA, LNCSDARL.FUNC);
        S000_CALL_LNZSDARL();
    }
    public void S000_CALL_LNZSDARL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-DARL-MAIN", LNCSDARL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

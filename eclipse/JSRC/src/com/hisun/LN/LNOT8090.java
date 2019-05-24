package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8090 {
    String JIBS_tmp_str[] = new String[10];
    LNOT8090_WS_ERR_MSG WS_ERR_MSG = new LNOT8090_WS_ERR_MSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    LNCICTPY LNCICTPY = new LNCICTPY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    LNB8090_AWA_8090 LNB8090_AWA_8090;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8090 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8090_AWA_8090>");
        LNB8090_AWA_8090 = (LNB8090_AWA_8090) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_GET_CTPY_INF();
    }
    public void B100_GET_CTPY_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTPY);
        LNCICTPY.O_INPUT_INFO.LOAN_ACNO = LNB8090_AWA_8090.LOAN_AC;
        S000_CALL_LNZICTPY();
    }
    public void S000_CALL_LNZICTPY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-GET-CTPY-INF", LNCICTPY);
        CEP.TRC(SCCGWA, "ICTPY-RC-CODE");
        CEP.TRC(SCCGWA, LNCICTPY.RC.RC_CODE);
        if (LNCICTPY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTPY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

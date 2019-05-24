package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5715 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSSUBS LNCSSUBS = new LNCSSUBS();
    SCCGWA SCCGWA;
    LNB5710_AWA_5710 LNB5710_AWA_5710;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT5715 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5710_AWA_5710>");
        LNB5710_AWA_5710 = (LNB5710_AWA_5710) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCSSUBS.RC.RC_MMO = "LN";
        LNCSSUBS.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_SUBSMULA_MAIN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB5710_AWA_5710.PROJ_NO == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PROJ_NO_M_INPUT;
            WS_FLD_NO = LNB5710_AWA_5710.PROJ_NO_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_SUBSMULA_MAIN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB5710_AWA_5710.PROJ_NO);
        IBS.init(SCCGWA, LNCSSUBS);
        LNCSSUBS.FUNC = 'I';
        LNCSSUBS.DATA.KEY.PROJ_NO = LNB5710_AWA_5710.PROJ_NO;
        S000_CALL_LNZSSUBS();
    }
    public void S000_CALL_LNZSSUBS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-SUBS-MAIN", LNCSSUBS);
        if (LNCSSUBS.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + LNCSSUBS.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

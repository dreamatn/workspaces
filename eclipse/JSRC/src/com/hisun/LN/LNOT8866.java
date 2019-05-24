package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8866 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCILNQ LNCILNQ = new LNCILNQ();
    SCCGWA SCCGWA;
    LNB8866_AWA_8866 LNB8866_AWA_8866;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8866 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8866_AWA_8866>");
        LNB8866_AWA_8866 = (LNB8866_AWA_8866) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCILNQ.RC.RC_MMO = "LN";
        LNCILNQ.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_FACILITY_MAINTENANCE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB8866_AWA_8866.CONT_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB8866_AWA_8866.CONT_NO_NO;
            S000_ERR_MSG_PROC();
        }
        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_FACILITY_MAINTENANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCILNQ);
        LNCILNQ.DATA.CONTRACT_NO = LNB8866_AWA_8866.CONT_NO;
        LNCILNQ.DATA.SUB_CONT_NO = "" + LNB8866_AWA_8866.SUB_C_NO;
        JIBS_tmp_int = LNCILNQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCILNQ.DATA.SUB_CONT_NO = "0" + LNCILNQ.DATA.SUB_CONT_NO;
        LNCILNQ.LNQ_OPT = LNB8866_AWA_8866.OPT_CD;
        CEP.TRC(SCCGWA, LNB8866_AWA_8866.OPT_CD);
        CEP.TRC(SCCGWA, LNB8866_AWA_8866.CONT_NO);
        CEP.TRC(SCCGWA, LNB8866_AWA_8866.SUB_C_NO);
        CEP.TRC(SCCGWA, LNCILNQ.DATA.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCILNQ.DATA.SUB_CONT_NO);
        LNCILNQ.LNQ_ACT = 'N';
        LNCILNQ.FUNC = 'D';
        S000_CALL_LNZILNQ();
    }
    public void S000_CALL_LNZILNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-CONT-Q-ALL", LNCILNQ);
        if (LNCILNQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCILNQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
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

package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT1800 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDCSMAGT TDCSMAGT = new TDCSMAGT();
    SCCGWA SCCGWA;
    TDB1800_AWA_1800 TDB1800_AWA_1800;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT1800 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1800_AWA_1800>");
        TDB1800_AWA_1800 = (TDB1800_AWA_1800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_INT_WIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB1800_AWA_1800.FUNC != 'I') {
            WS_CNT = WS_CNT + 1;
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_FUNC_ERR;
            if (TDB1800_AWA_1800.FUNC == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+TDB1800_AWA_1800.FUNC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_CNT > 0) {
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B020_ADD_INT_WIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB1800_AWA_1800.AGT_NO);
        IBS.init(SCCGWA, TDCSMAGT);
        TDCSMAGT.FUNC = TDB1800_AWA_1800.FUNC;
        TDCSMAGT.AC = TDB1800_AWA_1800.AC;
        TDCSMAGT.AC_SEQ = TDB1800_AWA_1800.AC_SEQ;
        TDCSMAGT.AGT_NO = TDB1800_AWA_1800.AGT_NO;
        TDCSMAGT.CI_NO = TDB1800_AWA_1800.CI_NO;
        TDCSMAGT.AGT_TYPE = TDB1800_AWA_1800.AGT_TYP;
        TDCSMAGT.AGT_LVL = TDB1800_AWA_1800.AGT_LVL;
        TDCSMAGT.EFF_DATE = TDB1800_AWA_1800.EFF_DATE;
        TDCSMAGT.EXP_DATE = TDB1800_AWA_1800.EXP_DATE;
        TDCSMAGT.AGT_TNUM = TDB1800_AWA_1800.AGT_TNUM;
        TDCSMAGT.REMARK = TDB1800_AWA_1800.REMARK;
        S000_CALL_TDZSMAGT();
    }
    public void S000_CALL_TDZSMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDZSMAGT", TDCSMAGT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

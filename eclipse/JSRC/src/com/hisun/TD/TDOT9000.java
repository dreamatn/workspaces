package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT9000 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCUPARM TDCUPARM = new TDCUPARM();
    SCCGWA SCCGWA;
    TDB9000_AWA_9000 TDB9000_AWA_9000;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT9000 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB9000_AWA_9000>");
        TDB9000_AWA_9000 = (TDB9000_AWA_9000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB9000_AWA_9000.FUNC);
        if (TDB9000_AWA_9000.FUNC == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB9000_AWA_9000.FUNC);
        CEP.TRC(SCCGWA, TDB9000_AWA_9000.BTZ_DAYS);
        CEP.TRC(SCCGWA, TDB9000_AWA_9000.YBT_LINE);
        CEP.TRC(SCCGWA, TDB9000_AWA_9000.RE_DT);
        CEP.TRC(SCCGWA, TDB9000_AWA_9000.LDT);
        CEP.TRC(SCCGWA, TDB9000_AWA_9000.CLO_DT);
        CEP.TRC(SCCGWA, TDB9000_AWA_9000.LUS_DT);
        CEP.TRC(SCCGWA, TDB9000_AWA_9000.TIC_TERM);
        CEP.TRC(SCCGWA, TDB9000_AWA_9000.COM_LNDT);
        IBS.init(SCCGWA, TDCUPARM);
        TDCUPARM.FUNC = TDB9000_AWA_9000.FUNC;
        TDCUPARM.PARM_DATA.BTZ_DAYS = TDB9000_AWA_9000.BTZ_DAYS;
        TDCUPARM.PARM_DATA.YBT_LINE_LMT = TDB9000_AWA_9000.YBT_LINE;
        TDCUPARM.PARM_DATA.LOST_EXP_DAY = TDB9000_AWA_9000.LDT;
        TDCUPARM.PARM_DATA.CAN_LOST_DAYS = TDB9000_AWA_9000.CLO_DT;
        TDCUPARM.PARM_DATA.LOST_NEWUSE_DAYS = TDB9000_AWA_9000.LUS_DT;
        TDCUPARM.PARM_DATA.TICKET_TERM = TDB9000_AWA_9000.TIC_TERM;
        TDCUPARM.PARM_DATA.COM_LNDT = TDB9000_AWA_9000.COM_LNDT;
        S000_CALL_TDZUPARM();
    }
    public void S000_CALL_TDZUPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-TDPARM", TDCUPARM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

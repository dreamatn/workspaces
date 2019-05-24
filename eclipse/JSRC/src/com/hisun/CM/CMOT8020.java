package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMOT8020 {
    String WS_ERR_MSG = " ";
    int WS_LAST_DT = 0;
    int WS_CURR_DT = 0;
    int WS_NEXT_DT = 0;
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    CMCF802 CMCF802 = new CMCF802();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    CMCS8020 CMCS8020;
    public void MP(SCCGWA SCCGWA, CMCS8020 CMCS8020) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS8020 = CMCS8020;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMOT8020 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_COUNT_DATE();
        B050_NEXT_DATE_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
    }
    public void B030_COUNT_DATE() throws IOException,SQLException,Exception {
        WS_CURR_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = "SY";
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CAL_CD);
        CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
        BPCOCLWD.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        BPCOCLWD.WDAYS = 2;
        CEP.TRC(SCCGWA, "1111111111111111");
        S000_CALL_BPZPCLWD();
        WS_NEXT_DT = BPCOCLWD.DATE2;
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = "SY";
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CAL_CD);
        CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
        BPCOCLWD.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        BPCOCLWD.WDAYS = -2;
        CEP.TRC(SCCGWA, "2222222222222222");
        S000_CALL_BPZPCLWD();
        WS_LAST_DT = BPCOCLWD.DATE2;
        CEP.TRC(SCCGWA, WS_CURR_DT);
        CEP.TRC(SCCGWA, WS_NEXT_DT);
    }
    public void B050_NEXT_DATE_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CMCF802.LAST_AC_DT = WS_LAST_DT;
        CMCF802.CURR_AC_DT = WS_CURR_DT;
        CMCF802.NEXT_AC_DT = WS_NEXT_DT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CM802";
        SCCFMT.ODM_FLG = 'Y';
        SCCFMT.DATA_PTR = CMCF802;
        SCCFMT.DATA_LEN = 24;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

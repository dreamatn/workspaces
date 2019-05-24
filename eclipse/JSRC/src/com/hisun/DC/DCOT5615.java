package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DCOT5615 {
    boolean pgmRtn = false;
    String DC_U_ANDY_SERVICE = "DC-U-ANDY-SERVICE";
    String WS_MSG_ID = " ";
    String WS_TD_OUTPUT = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCUSBEI DCCUSBEI = new DCCUSBEI();
    SCCGWA SCCGWA;
    DCB5615_AWA_5615 DCB5615_AWA_5615;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT5615 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5615_AWA_5615>");
        DCB5615_AWA_5615 = (DCB5615_AWA_5615) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        if (pgmRtn) return;
        B200_INQ_TD_AC_PROC();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCB5615_AWA_5615.DD_AC.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DD_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_INQ_TD_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUSBEI);
        DCCUSBEI.IO_AREA.DD_AC = DCB5615_AWA_5615.DD_AC;
        CEP.TRC(SCCGWA, DCB5615_AWA_5615.DD_AC);
        S000_CALL_DCZUSBEI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        WS_TD_OUTPUT = DCCUSBEI.OUTPUT.TD_AC;
        SCCFMT.FMTID = "DC615";
        SCCFMT.DATA_PTR = DCOT5615_WS2;
        SCCFMT.DATA_LEN = 32;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZUSBEI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, DC_U_ANDY_SERVICE, DCCUSBEI, true);

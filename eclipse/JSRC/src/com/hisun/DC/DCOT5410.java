package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5410 {
    int JIBS_tmp_int;
    char K_AC_MTH = '0';
    char K_CARD_MTH = '1';
    String CDD_B_AUTO_TD_PLAN = "DC-B-AUTO-TD-PLAN";
    String WS_MSG_ID = " ";
    char WS_PROC_STS = ' ';
    int WS_DATA_LEN = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSBATP DCCSBATP = new DCCSBATP();
    SCCGWA SCCGWA;
    DCB5410_AWA_5410 DCB5410_AWA_5410;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5410 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5410_AWA_5410>");
        DCB5410_AWA_5410 = (DCB5410_AWA_5410) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_INQ_CON_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        WS_DATA_LEN = 0;
        CEP.TRC(SCCGWA, WS_DATA_LEN);
        CEP.TRC(SCCGWA, DCB5410_AWA_5410);
        CEP.TRC(SCCGWA, DCB5410_AWA_5410.AGR_NO);
        CEP.TRC(SCCGWA, DCB5410_AWA_5410.CI_NAME);
        CEP.TRC(SCCGWA, DCB5410_AWA_5410.PROC_STS);
        CEP.TRC(SCCGWA, DCB5410_AWA_5410.PROC_TYP);
        CEP.TRC(SCCGWA, DCB5410_AWA_5410.PROD_CDE);
        if (DCB5410_AWA_5410.AGR_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_AC;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        } else {
        }
        WS_PROC_STS = DCB5410_AWA_5410.PROC_STS;
        CEP.TRC(SCCGWA, DCB5410_AWA_5410.PROC_STS);
        CEP.TRC(SCCGWA, WS_PROC_STS);
        if ((WS_PROC_STS == '1' 
            || WS_PROC_STS == '2')) {
        } else {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PROC_STS_INV;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_INQ_CON_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSBATP);
        DCCSBATP.IO_AREA.FUNC_INQ = DCB5410_AWA_5410.FUNC_INQ;
        DCCSBATP.IO_AREA.AGR_NO = DCB5410_AWA_5410.AGR_NO;
        DCCSBATP.IO_AREA.PROC_STS = DCB5410_AWA_5410.PROC_STS;
        DCCSBATP.PAGE_ROW = DCB5410_AWA_5410.PAGE_ROW;
        DCCSBATP.PAGE_NUM = DCB5410_AWA_5410.PAGE_NUM;
        DCCSBATP.IO_AREA.CI_NAME = DCB5410_AWA_5410.CI_NAME;
        if (DCB5410_AWA_5410.PROC_TYP == ' ') {
            DCCSBATP.IO_AREA.PROC_TYP = "A";
        } else {
            DCCSBATP.IO_AREA.PROC_TYP = "" + DCB5410_AWA_5410.PROC_TYP;
            JIBS_tmp_int = DCCSBATP.IO_AREA.PROC_TYP.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) DCCSBATP.IO_AREA.PROC_TYP = "0" + DCCSBATP.IO_AREA.PROC_TYP;
        }
        DCCSBATP.IO_AREA.PROD_CDE = DCB5410_AWA_5410.PROD_CDE;
        CEP.TRC(SCCGWA, DCB5410_AWA_5410.PAGE_ROW);
        CEP.TRC(SCCGWA, DCB5410_AWA_5410.PAGE_NUM);
        S000_CALL_DCZSBATP();
    }
    public void S000_CALL_DCZSBATP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_B_AUTO_TD_PLAN, DCCSBATP);
        if (DCCSBATP.O_AREA.RC_CODE == 0) {
        } else {
            WS_MSG_ID = DCCSBATP.O_AREA.MSG_ID;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

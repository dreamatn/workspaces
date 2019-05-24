package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5411 {
    int JIBS_tmp_int;
    String CDD_I_AUTO_TD_PLAN = "DC-I-AUTO-TD-PLAN";
    char K_ERROR = 'E';
    String WS_MSG_ID = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSIATP DCCSIATP = new DCCSIATP();
    SCCGWA SCCGWA;
    DCB5411_AWA_5411 DCB5411_AWA_5411;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5411 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5411_AWA_5411>");
        DCB5411_AWA_5411 = (DCB5411_AWA_5411) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCB5411_AWA_5411.AGR_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_AC;
            CEP.ERRC(SCCGWA, WS_MSG_ID);
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSIATP);
        DCCSIATP.IO_AREA.FUNC_INQ = DCB5411_AWA_5411.FUNC_INQ;
        DCCSIATP.IO_AREA.OVR_NO = DCB5411_AWA_5411.OVR_NO;
        DCCSIATP.IO_AREA.PROD_CDE = DCB5411_AWA_5411.PROD_CDE;
        DCCSIATP.IO_AREA.AGR_NO = DCB5411_AWA_5411.AGR_NO;
        DCCSIATP.IO_AREA.CI_NAME = DCB5411_AWA_5411.CI_NAME;
        DCCSIATP.IO_AREA.PROC_TYP = "" + DCB5411_AWA_5411.PROC_TYP;
        JIBS_tmp_int = DCCSIATP.IO_AREA.PROC_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) DCCSIATP.IO_AREA.PROC_TYP = "0" + DCCSIATP.IO_AREA.PROC_TYP;
        DCCSIATP.IO_AREA.PROC_STS = DCB5411_AWA_5411.PROC_STS;
        CEP.TRC(SCCGWA, DCB5411_AWA_5411.FUNC_INQ);
        CEP.TRC(SCCGWA, DCB5411_AWA_5411.AGR_NO);
        CEP.TRC(SCCGWA, DCB5411_AWA_5411.CI_NAME);
        CEP.TRC(SCCGWA, DCB5411_AWA_5411.PROC_STS);
        CEP.TRC(SCCGWA, DCB5411_AWA_5411.PROC_TYP);
        CEP.TRC(SCCGWA, DCB5411_AWA_5411.PROD_CDE);
        CEP.TRC(SCCGWA, DCB5411_AWA_5411.OVR_NO);
        S000_CALL_DCZSIATP();
    }
    public void S000_CALL_DCZSIATP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_I_AUTO_TD_PLAN, DCCSIATP);
        if (DCCSIATP.O_AREA.RC_CODE == 0) {
        } else {
            WS_MSG_ID = DCCSIATP.O_AREA.MSG_ID;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSG_ID);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR);
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

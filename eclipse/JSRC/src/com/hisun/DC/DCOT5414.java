package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5414 {
    int JIBS_tmp_int;
    String CDD_D_AUTO_TD_PLAN = "DC-D-AUTO-TD-PLAN";
    char K_AC_MTH = '0';
    char K_CARD_MTH = '1';
    String WS_MSG_ID = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSDATP DCCSDATP = new DCCSDATP();
    SCCGWA SCCGWA;
    DCB5414_AWA_5414 DCB5414_AWA_5414;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5414 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5414_AWA_5414>");
        DCB5414_AWA_5414 = (DCB5414_AWA_5414) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCB5414_AWA_5414.AGR_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_AC;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        } else {
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSDATP);
        DCCSDATP.IO_AREA.FUNC_INQ = DCB5414_AWA_5414.FUNC_INQ;
        DCCSDATP.IO_AREA.AGR_NO = DCB5414_AWA_5414.AGR_NO;
        DCCSDATP.IO_AREA.CI_NAME = DCB5414_AWA_5414.CI_NAME;
        DCCSDATP.IO_AREA.PROC_STS = DCB5414_AWA_5414.PROC_STS;
        DCCSDATP.IO_AREA.PROC_TYP = "" + DCB5414_AWA_5414.PROC_TYP;
        JIBS_tmp_int = DCCSDATP.IO_AREA.PROC_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) DCCSDATP.IO_AREA.PROC_TYP = "0" + DCCSDATP.IO_AREA.PROC_TYP;
        DCCSDATP.IO_AREA.PROD_CDE = DCB5414_AWA_5414.PROD_CDE;
        DCCSDATP.IO_AREA.OVR_NO = DCB5414_AWA_5414.OVR_NO;
        CEP.TRC(SCCGWA, "MJL TEST:");
        CEP.TRC(SCCGWA, DCB5414_AWA_5414.FUNC_INQ);
        CEP.TRC(SCCGWA, DCB5414_AWA_5414.AGR_NO);
        CEP.TRC(SCCGWA, DCB5414_AWA_5414.CI_NAME);
        CEP.TRC(SCCGWA, DCB5414_AWA_5414.PROC_STS);
        CEP.TRC(SCCGWA, DCB5414_AWA_5414.PROC_TYP);
        CEP.TRC(SCCGWA, DCB5414_AWA_5414.PROD_CDE);
        CEP.TRC(SCCGWA, DCB5414_AWA_5414.OVR_NO);
        S000_CALL_DCZSDATP();
    }
    public void S000_CALL_DCZSDATP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_D_AUTO_TD_PLAN, DCCSDATP);
        if (DCCSDATP.O_AREA.RC_CODE == 0) {
        } else {
            WS_MSG_ID = DCCSDATP.O_AREA.MSG_ID;
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

package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT3080 {
    String JIBS_tmp_str[] = new String[10];
    BAOT3080_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BAOT3080_WS_TEMP_VARIABLE();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BACUQPAY BACUQPAY = new BACUQPAY();
    SCCGWA SCCGWA;
    BAB3080_AWA_3080 BAB3080_AWA_3080;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAOT3080 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB3080_AWA_3080>");
        BAB3080_AWA_3080 = (BAB3080_AWA_3080) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        CEP.TRC(SCCGWA, BAB3080_AWA_3080.DRWR_AC);
        CEP.TRC(SCCGWA, BAB3080_AWA_3080.CNTR_NO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_MAIN_PROCESS();
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUQPAY);
        BACUQPAY.DATA.DRWR_AC = BAB3080_AWA_3080.DRWR_AC;
        BACUQPAY.DATA.CNTR_NO = BAB3080_AWA_3080.CNTR_NO;
        if (BAB3080_AWA_3080.PAGE_ROW == 0) {
            BACUQPAY.DATA.PAGE_ROW = 25;
        } else {
            BACUQPAY.DATA.PAGE_ROW = BAB3080_AWA_3080.PAGE_ROW;
        }
        BACUQPAY.DATA.PAGE_NUM = BAB3080_AWA_3080.PAGE_NUM;
        S000_CALL_BAZUQPAY();
    }
    public void S000_CALL_BAZUQPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-INQ-RPAY", BACUQPAY);
        if (BACUQPAY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACUQPAY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

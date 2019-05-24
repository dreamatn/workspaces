package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5613 {
    char K_AC_MTH = '0';
    char K_CARD_MTH = '1';
    char K_MOD_FUNC = 'M';
    char K_STS_FUNC = 'S';
    String CDC_M_COMPA_DR_PLAN = "DC-M-COMPA-DR-PLAN";
    String WS_MSG_ID = " ";
    double WS_TRIG_AMT = 0;
    DCOT5613_REDEFINES3 REDEFINES3 = new DCOT5613_REDEFINES3();
    char WS_PROC_STS = ' ';
    char WS_FUNC = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCUMCID DCCUMCID = new DCCUMCID();
    SCCGWA SCCGWA;
    DCB5613_AWA_5613 DCB5613_AWA_5613;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5613 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5613_AWA_5613>");
        DCB5613_AWA_5613 = (DCB5613_AWA_5613) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5613_AWA_5613.FUNC);
        CEP.TRC(SCCGWA, DCB5613_AWA_5613.OVR_NO);
        CEP.TRC(SCCGWA, DCB5613_AWA_5613.PROC_STS);
        CEP.TRC(SCCGWA, DCB5613_AWA_5613.AC_NO);
        CEP.TRC(SCCGWA, DCB5613_AWA_5613.PROD_CDE);
        CEP.TRC(SCCGWA, DCB5613_AWA_5613.PROCS_DT);
        CEP.TRC(SCCGWA, DCB5613_AWA_5613.PROCL_DT);
        CEP.TRC(SCCGWA, DCB5613_AWA_5613.MRM_AMT);
        CEP.TRC(SCCGWA, DCB5613_AWA_5613.LNK_AC);
        WS_FUNC = DCB5613_AWA_5613.FUNC;
        if ((WS_FUNC == 'M' 
            || WS_FUNC == 'S')) {
        } else {
            WS_MSG_ID = DCCMSG_ERROR_MSG.INPUT_FALSE;
            S000_ERR_MSG_PROC();
        }
        if (DCB5613_AWA_5613.OVR_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_OVR_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCB5613_AWA_5613.FUNC == K_MOD_FUNC) {
            if (DCB5613_AWA_5613.MRM_AMT < 0) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_KEEP_AMT_INV;
                S000_ERR_MSG_PROC();
            }
            if (DCB5613_AWA_5613.PROCS_DT == 0) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DT_M_INPUT;
                CEP.ERR(SCCGWA, WS_MSG_ID);
            }
            if (DCB5613_AWA_5613.PROCL_DT == 0) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_DT_M_INPUT;
                CEP.ERR(SCCGWA, WS_MSG_ID);
            }
        }
        if (DCB5613_AWA_5613.FUNC == K_STS_FUNC) {
            WS_PROC_STS = DCB5613_AWA_5613.PROC_STS;
            if ((WS_PROC_STS == '1' 
                || WS_PROC_STS == '2')) {
            } else {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PROC_STS_INV;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMCID);
        DCCUMCID.IO_AREA.FUNC_M = DCB5613_AWA_5613.FUNC;
        DCCUMCID.IO_AREA.OVR_NO = DCB5613_AWA_5613.OVR_NO;
        DCCUMCID.IO_AREA.CHG_STS = DCB5613_AWA_5613.PROC_STS;
        DCCUMCID.IO_AREA.MRM_AMT = DCB5613_AWA_5613.MRM_AMT;
        DCCUMCID.IO_AREA.AC_NO = DCB5613_AWA_5613.AC_NO;
        DCCUMCID.IO_AREA.PROD_CDE = DCB5613_AWA_5613.PROD_CDE;
        DCCUMCID.IO_AREA.PROCS_DT = DCB5613_AWA_5613.PROCS_DT;
        DCCUMCID.IO_AREA.PROCL_DT = DCB5613_AWA_5613.PROCL_DT;
        DCCUMCID.IO_AREA.SMR = DCB5613_AWA_5613.SMR;
        DCCUMCID.IO_AREA.LNK_AC = DCB5613_AWA_5613.LNK_AC;
        DCCUMCID.IO_AREA.POST_OPT = DCB5613_AWA_5613.POST_OPT;
        DCCUMCID.IO_AREA.DEP_RATE = DCB5613_AWA_5613.DEP_RATE;
        DCCUMCID.IO_AREA.OD_RATE = DCB5613_AWA_5613.OD_RATE;
        S000_CALL_DCZUMCID();
    }
    public void S000_CALL_DCZUMCID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDC_M_COMPA_DR_PLAN, DCCUMCID);
        if (DCCUMCID.O_AREA.MSG_ID.RC == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DCCUMCID.O_AREA.MSG_ID);
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

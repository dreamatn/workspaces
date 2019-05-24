package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5611 {
    String CDC_M_COMPA_DR_PLAN = "DC-M-COMPA-DR-PLAN";
    String WS_MSG_ID = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCUMCID DCCUMCID = new DCCUMCID();
    DCCOMCID DCCOMCID = new DCCOMCID();
    SCCGWA SCCGWA;
    DCB5611_AWA_5611 DCB5611_AWA_5611;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5611 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5611_AWA_5611>");
        DCB5611_AWA_5611 = (DCB5611_AWA_5611) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
        B300_DATA_RETURN();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCB5611_AWA_5611.AC_NO.trim().length() == 0 
            && DCB5611_AWA_5611.OVR_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_AC;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        } else {
        }
        CEP.TRC(SCCGWA, DCB5611_AWA_5611.OVR_NO);
        CEP.TRC(SCCGWA, DCB5611_AWA_5611.AC_NO);
        CEP.TRC(SCCGWA, DCB5611_AWA_5611.PROC_STS);
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMCID);
        DCCUMCID.IO_AREA.FUNC_M = 'I';
        DCCUMCID.IO_AREA.OVR_NO = DCB5611_AWA_5611.OVR_NO;
        DCCUMCID.IO_AREA.AC_NO = DCB5611_AWA_5611.AC_NO;
        DCCUMCID.IO_AREA.PROC_STS = DCB5611_AWA_5611.PROC_STS;
        S000_CALL_DCZUMCID();
    }
    public void B300_DATA_RETURN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, DCCOMCID);
        DCCOMCID.OUT_DATA.AC_NO = DCCUMCID.IO_AREA.AC_NO;
        DCCOMCID.OUT_DATA.PROD_COE = DCCUMCID.IO_AREA.PROD_CDE;
        DCCOMCID.OUT_DATA.CCY = DCCUMCID.IO_AREA.CCY;
        DCCOMCID.OUT_DATA.CCY_TYP = DCCUMCID.IO_AREA.CCY_TYP;
        DCCOMCID.OUT_DATA.PROCS_DT = DCCUMCID.IO_AREA.PROCS_DT;
        DCCOMCID.OUT_DATA.PROCL_DT = DCCUMCID.IO_AREA.PROCL_DT;
        DCCOMCID.OUT_DATA.MRM_AMT = DCCUMCID.IO_AREA.MRM_AMT;
        DCCOMCID.OUT_DATA.PROC_STS = DCCUMCID.IO_AREA.PROC_STS;
        DCCOMCID.OUT_DATA.OVR_NO = DCCUMCID.IO_AREA.OVR_NO;
        DCCOMCID.OUT_DATA.LNK_AC = DCCUMCID.IO_AREA.LNK_AC;
        DCCOMCID.OUT_DATA.POST_OPT = DCCUMCID.IO_AREA.POST_OPT;
        DCCOMCID.OUT_DATA.DEP_RATE = DCCUMCID.IO_AREA.DEP_RATE;
        DCCOMCID.OUT_DATA.OD_RATE = DCCUMCID.IO_AREA.OD_RATE;
        DCCOMCID.OUT_DATA.CI_NAME = DCCUMCID.IO_AREA.AC_NAME;
        DCCOMCID.OUT_DATA.SMR = DCCUMCID.IO_AREA.SMR;
        CEP.TRC(SCCGWA, DCCOMCID);
        SCCFMT.FMTID = "DC611";
        SCCFMT.DATA_PTR = DCCOMCID;
        SCCFMT.DATA_LEN = 562;
        IBS.FMT(SCCGWA, SCCFMT);
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

package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1351 {
    String K_OUTPUT_FMT = "SMM80";
    String CPN_DDIC_MAINTAIN = "SM-S_DDIC_MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SMCODICM SMCODICM = new SMCODICM();
    SCCGWA SCCGWA;
    SMB1350_AWA_1350 SMB1350_AWA_1350;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "SMOT1351 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1350_AWA_1350>");
        SMB1350_AWA_1350 = (SMB1350_AWA_1350) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_DDIC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_ADD_DDIC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCODICM);
        SMCODICM.FUNC = 'A';
        SMCODICM.OUTPUT_FMT = K_OUTPUT_FMT;
        SMCODICM.NAME = SMB1350_AWA_1350.NAME;
        SMCODICM.CL_CODE = SMB1350_AWA_1350.CL_CODE;
        SMCODICM.ALIAS = SMB1350_AWA_1350.ALIAS;
        SMCODICM.EN_NAME = SMB1350_AWA_1350.EN_NAME;
        SMCODICM.CH_NAME = SMB1350_AWA_1350.CH_NAME;
        SMCODICM.TYPE = SMB1350_AWA_1350.TYPE;
        SMCODICM.LEN = SMB1350_AWA_1350.LEN;
        SMCODICM.DEC_PNT = SMB1350_AWA_1350.DEC_PNT;
        SMCODICM.SIGN = SMB1350_AWA_1350.SIGN;
        SMCODICM.AP_NAME = SMB1350_AWA_1350.AP_NAME;
        SMCODICM.BSN_DESC = SMB1350_AWA_1350.BS_DESC;
        SMCODICM.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        SMCODICM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_SMZDDICM();
    }
    public void S000_CALL_SMZDDICM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DDIC_MAINTAIN, SMCODICM);
        if (SMCODICM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SMCODICM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

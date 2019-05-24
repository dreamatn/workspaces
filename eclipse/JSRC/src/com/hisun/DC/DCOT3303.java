package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT3303 {
    double K_MAX_AMT = 1000;
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    double WS_TEMP_AMT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCSECQC DCCSECQC = new DCCSECQC();
    SCCGWA SCCGWA;
    DCB3303_AWA_3303 DCB3303_AWA_3303;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT3303 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB3303_AWA_3303>");
        DCB3303_AWA_3303 = (DCB3303_AWA_3303) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "IN");
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.CARD_NO);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.QC_TYP);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.CHIP_BAL);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.QC_AMT);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.UNAN_CRD);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.PSW);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.FEE);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.ARQC);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.ARQC_DAT);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.VERY_RET);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.BR_DT);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.PRAC_SEQ);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.COUNT_N);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.CLEAR_AC);
        if (DCB3303_AWA_3303.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCB3303_AWA_3303.QC_AMT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_QC_AMT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        WS_TEMP_AMT = DCB3303_AWA_3303.CHIP_BAL + DCB3303_AWA_3303.QC_AMT;
        CEP.TRC(SCCGWA, WS_TEMP_AMT);
        CEP.TRC(SCCGWA, K_MAX_AMT);
        if (WS_TEMP_AMT > K_MAX_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_QC_AMT_OVR_MAXAMT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCB3303_AWA_3303.QC_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_QC_TYP;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        } else {
            if (DCB3303_AWA_3303.QC_TYP == '1') {
                if (DCB3303_AWA_3303.UNAN_CRD.trim().length() == 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_UNAN_CRD;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DCB3303_AWA_3303.PSW.trim().length() == 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
            } else if (DCB3303_AWA_3303.QC_TYP == '0') {
                if (DCB3303_AWA_3303.PSW.trim().length() == 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
            }
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSECQC);
        DCCSECQC.IO_AREA.CARD_NO = DCB3303_AWA_3303.CARD_NO;
        DCCSECQC.IO_AREA.QC_TYP = DCB3303_AWA_3303.QC_TYP;
        DCCSECQC.IO_AREA.CHIP_BAL_AMT = DCB3303_AWA_3303.CHIP_BAL;
        DCCSECQC.IO_AREA.QC_AMT = DCB3303_AWA_3303.QC_AMT;
        DCCSECQC.IO_AREA.UNASSIGN_CRD = DCB3303_AWA_3303.UNAN_CRD;
        DCCSECQC.IO_AREA.PSW = DCB3303_AWA_3303.PSW;
        DCCSECQC.IO_AREA.FEE = DCB3303_AWA_3303.FEE;
        DCCSECQC.IO_AREA.ARQC = DCB3303_AWA_3303.ARQC;
        DCCSECQC.IO_AREA.ARQC_DATA = DCB3303_AWA_3303.ARQC_DAT;
        DCCSECQC.VERIFY_RLT = DCB3303_AWA_3303.VERY_RET;
        DCCSECQC.ISSUE_DATA = DCB3303_AWA_3303.BR_DT;
        DCCSECQC.CARD_SEQ = DCB3303_AWA_3303.PRAC_SEQ;
        DCCSECQC.COUNT_NUM = DCB3303_AWA_3303.COUNT_N;
        DCCSECQC.SLT_AC = DCB3303_AWA_3303.CLEAR_AC;
        S000_CALL_DCZSECQC();
    }
    public void S000_CALL_DCZSECQC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-DCZSECQC", DCCSECQC);
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

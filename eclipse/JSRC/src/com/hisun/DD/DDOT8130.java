package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT8130 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSQPOC DDCSQPOC = new DDCSQPOC();
    SCCGWA SCCGWA;
    DDB8130_AWA_8130 DDB8130_AWA_8130;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT8130 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8130_AWA_8130>");
        DDB8130_AWA_8130 = (DDB8130_AWA_8130) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "111");
        CEP.TRC(SCCGWA, DDB8130_AWA_8130.START_DT);
        CEP.TRC(SCCGWA, DDB8130_AWA_8130.END_DT);
        CEP.TRC(SCCGWA, DDB8130_AWA_8130.INQU_TYP);
        if (DDB8130_AWA_8130.END_DT == 0) {
            DDB8130_AWA_8130.END_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (DDB8130_AWA_8130.CARD_NO.trim().length() > 0 
            && DDB8130_AWA_8130.PSBK_NO.trim().length() > 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CARD_VCH_NOT_BOTH;
            S000_ERR_MSG_PROC();
        }
        if (DDB8130_AWA_8130.INQU_TYP != ' ') {
            if ((DDB8130_AWA_8130.INQU_TYP == 'O') 
                || (DDB8130_AWA_8130.INQU_TYP == 'C')) {
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQPOC);
        DDCSQPOC.START_DATE = DDB8130_AWA_8130.START_DT;
        DDCSQPOC.END_DATE = DDB8130_AWA_8130.END_DT;
        if (DDB8130_AWA_8130.END_DT == 0) {
            DDCSQPOC.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        DDCSQPOC.TLR_NO = DDB8130_AWA_8130.TLR_NO;
        DDCSQPOC.BANK_NO = DDB8130_AWA_8130.BANK_NO;
        DDCSQPOC.INQU_TYPE = DDB8130_AWA_8130.INQU_TYP;
        DDCSQPOC.CARD_NO = DDB8130_AWA_8130.CARD_NO;
        DDCSQPOC.PSBK_NO = DDB8130_AWA_8130.PSBK_NO;
        CEP.TRC(SCCGWA, DDCSQPOC.START_DATE);
        CEP.TRC(SCCGWA, DDCSQPOC.END_DATE);
        CEP.TRC(SCCGWA, DDCSQPOC.TLR_NO);
        CEP.TRC(SCCGWA, DDCSQPOC.BANK_NO);
        CEP.TRC(SCCGWA, DDCSQPOC.CARD_NO);
        CEP.TRC(SCCGWA, DDCSQPOC.PSBK_NO);
        CEP.TRC(SCCGWA, DDCSQPOC.INQU_TYPE);
        S000_CALL_DDZSQPOC();
    }
    public void S000_CALL_DDZSQPOC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-QRY-P-OCAC", DDCSQPOC);
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

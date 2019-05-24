package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7560 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSPLAD DCCSPLAD = new DCCSPLAD();
    SCCGWA SCCGWA;
    DCB7560_AWA_7560 DCB7560_AWA_7560;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT7560 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB7560_AWA_7560>");
        DCB7560_AWA_7560 = (DCB7560_AWA_7560) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.AC);
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.CARD_NO);
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.SEQ);
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.CHQ_NO);
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.HLD_TYP);
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.SPR_TYP);
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.LAW_NM2);
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.LAW_NM1);
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.LAW_NO2);
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.LAW_NO1);
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.CARD_FLG);
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.PSW_FLG);
        CEP.TRC(SCCGWA, DCB7560_AWA_7560.TRK_FLG);
        if (DCB7560_AWA_7560.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            WS_FLD_NO = DCB7560_AWA_7560.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7560_AWA_7560.HLD_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_TYP_MUST_INPUT;
            WS_FLD_NO = DCB7560_AWA_7560.HLD_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7560_AWA_7560.HLD_TYP != '1' 
            && DCB7560_AWA_7560.HLD_TYP != '2' 
            && DCB7560_AWA_7560.HLD_TYP != '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_TYP_INVALID;
            WS_FLD_NO = DCB7560_AWA_7560.HLD_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7560_AWA_7560.SPR_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_MUST_INPUT;
            WS_FLD_NO = DCB7560_AWA_7560.SPR_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7560_AWA_7560.SPR_TYP != '1' 
            && DCB7560_AWA_7560.SPR_TYP != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_INVALID;
            WS_FLD_NO = DCB7560_AWA_7560.SPR_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((DCB7560_AWA_7560.HLD_TYP == '2' 
            || DCB7560_AWA_7560.HLD_TYP == '3') 
            && DCB7560_AWA_7560.CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACCCY_MUST_INPUT;
            WS_FLD_NO = DCB7560_AWA_7560.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((DCB7560_AWA_7560.HLD_TYP == '2' 
            || DCB7560_AWA_7560.HLD_TYP == '3') 
            && DCB7560_AWA_7560.AMT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT;
            WS_FLD_NO = DCB7560_AWA_7560.AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7560_AWA_7560.SPR_TYP == '1' 
            && DCB7560_AWA_7560.SPR_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_WRIT_NO_M_INPUT;
            WS_FLD_NO = DCB7560_AWA_7560.SPR_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7560_AWA_7560.SPR_TYP == '1' 
            && DCB7560_AWA_7560.SPR_NM.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_NM_M_INPUT;
            WS_FLD_NO = DCB7560_AWA_7560.SPR_NM_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7560_AWA_7560.SPR_TYP == '1' 
            && DCB7560_AWA_7560.LAW_NM1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NM_M_INPUT;
            WS_FLD_NO = DCB7560_AWA_7560.LAW_NM1_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7560_AWA_7560.SPR_TYP == '1' 
            && DCB7560_AWA_7560.LAW_NO1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NO_M_INPUT;
            WS_FLD_NO = DCB7560_AWA_7560.LAW_NO1_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7560_AWA_7560.SPR_TYP == '1' 
            && DCB7560_AWA_7560.LAW_NM2.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NM_M_INPUT;
            WS_FLD_NO = DCB7560_AWA_7560.LAW_NM2_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7560_AWA_7560.SPR_TYP == '1' 
            && DCB7560_AWA_7560.LAW_NO2.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NO_M_INPUT;
            WS_FLD_NO = DCB7560_AWA_7560.LAW_NO2_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7560_AWA_7560.HLD_TYP != '3' 
            && DCB7560_AWA_7560.CHQ_NO.trim().length() > 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_LOAD_CHQ_NO;
            WS_FLD_NO = DCB7560_AWA_7560.CHQ_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSPLAD);
        DCCSPLAD.DATA.AC = DCB7560_AWA_7560.AC;
        DCCSPLAD.DATA.SEQ = DCB7560_AWA_7560.SEQ;
        DCCSPLAD.DATA.CHQ_NO = DCB7560_AWA_7560.CHQ_NO;
        DCCSPLAD.DATA.HLD_TYP = DCB7560_AWA_7560.HLD_TYP;
        DCCSPLAD.DATA.SPR_TYP = DCB7560_AWA_7560.SPR_TYP;
        DCCSPLAD.DATA.CCY = DCB7560_AWA_7560.CCY;
        DCCSPLAD.DATA.CCY_TYP = DCB7560_AWA_7560.CCY_TYP;
        DCCSPLAD.DATA.AMT = DCB7560_AWA_7560.AMT;
        DCCSPLAD.DATA.SPR_NO = DCB7560_AWA_7560.SPR_NO;
        DCCSPLAD.DATA.SPR_NM = DCB7560_AWA_7560.SPR_NM;
        DCCSPLAD.DATA.RSN = DCB7560_AWA_7560.RSN;
        DCCSPLAD.DATA.EFF_DT = DCB7560_AWA_7560.EFF_DT;
        DCCSPLAD.DATA.EXP_DT = DCB7560_AWA_7560.EXP_DT;
        DCCSPLAD.DATA.RMK = DCB7560_AWA_7560.RMK;
        DCCSPLAD.DATA.HLD_BR = DCB7560_AWA_7560.HLD_BR;
        DCCSPLAD.DATA.LAW_NM1 = DCB7560_AWA_7560.LAW_NM1;
        DCCSPLAD.DATA.LAW_NO1 = DCB7560_AWA_7560.LAW_NO1;
        DCCSPLAD.DATA.LAW_NM2 = DCB7560_AWA_7560.LAW_NM2;
        DCCSPLAD.DATA.LAW_NO2 = DCB7560_AWA_7560.LAW_NO2;
        DCCSPLAD.DATA.REF_OLD = DCB7560_AWA_7560.REF_OLD;
        DCCSPLAD.DATA.PSW = DCB7560_AWA_7560.PSW;
        DCCSPLAD.DATA.TRK2_DAT = DCB7560_AWA_7560.TRK2_DAT;
        DCCSPLAD.DATA.TRK3_DAT = DCB7560_AWA_7560.TRK3_DAT;
        DCCSPLAD.DATA.CARD_NO = DCB7560_AWA_7560.CARD_NO;
        DCCSPLAD.DATA.CARD_FLG = DCB7560_AWA_7560.CARD_FLG;
        DCCSPLAD.DATA.PSW_FLG = DCB7560_AWA_7560.PSW_FLG;
        DCCSPLAD.DATA.TRK_FLG = DCB7560_AWA_7560.TRK_FLG;
        S000_CALL_DCZSPLAD();
    }
    public void S000_CALL_DCZSPLAD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-PLAD", DCCSPLAD);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

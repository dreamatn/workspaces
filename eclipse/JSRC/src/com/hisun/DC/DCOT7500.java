package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7500 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSHOLD DCCSHOLD = new DCCSHOLD();
    SCCGWA SCCGWA;
    DDB7500_AWA_7500 DDB7500_AWA_7500;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT7500 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB7500_AWA_7500>");
        DDB7500_AWA_7500 = (DDB7500_AWA_7500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB7500_AWA_7500.AC);
        CEP.TRC(SCCGWA, DDB7500_AWA_7500.HLD_TYP);
        CEP.TRC(SCCGWA, DDB7500_AWA_7500.SPR_TYP);
        if (DDB7500_AWA_7500.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            WS_FLD_NO = DDB7500_AWA_7500.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7500_AWA_7500.HLD_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_TYP_MUST_INPUT;
            WS_FLD_NO = DDB7500_AWA_7500.HLD_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7500_AWA_7500.HLD_TYP != '1' 
            && DDB7500_AWA_7500.HLD_TYP != '2' 
            && DDB7500_AWA_7500.HLD_TYP != 'A') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_TYP_INVALID;
            WS_FLD_NO = DDB7500_AWA_7500.HLD_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7500_AWA_7500.SPR_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_MUST_INPUT;
            WS_FLD_NO = DDB7500_AWA_7500.SPR_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7500_AWA_7500.SPR_TYP != '1' 
            && DDB7500_AWA_7500.SPR_TYP != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_INVALID;
            WS_FLD_NO = DDB7500_AWA_7500.SPR_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((DDB7500_AWA_7500.HLD_TYP == '2' 
            || DDB7500_AWA_7500.HLD_TYP == 'A') 
            && DDB7500_AWA_7500.AMT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT;
            WS_FLD_NO = DDB7500_AWA_7500.AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7500_AWA_7500.SPR_TYP == '1' 
            && DDB7500_AWA_7500.SPR_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_WRIT_NO_M_INPUT;
            WS_FLD_NO = DDB7500_AWA_7500.SPR_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7500_AWA_7500.SPR_TYP == '1' 
            && DDB7500_AWA_7500.SPR_NM.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_NM_M_INPUT;
            WS_FLD_NO = DDB7500_AWA_7500.SPR_NM_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7500_AWA_7500.SPR_TYP == '1' 
            && DDB7500_AWA_7500.LAW_NM1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NM_M_INPUT;
            WS_FLD_NO = DDB7500_AWA_7500.LAW_NM1_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, DDB7500_AWA_7500.SPR_TYP);
        CEP.TRC(SCCGWA, DDB7500_AWA_7500.LAW_NO1);
        if (DDB7500_AWA_7500.SPR_TYP == '1' 
            && DDB7500_AWA_7500.LAW_NO1.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AAAAAA");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NO_M_INPUT;
            WS_FLD_NO = DDB7500_AWA_7500.LAW_NO1_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, DDB7500_AWA_7500.HLD_TYP);
        CEP.TRC(SCCGWA, DDB7500_AWA_7500.REF_OLD);
        if (DDB7500_AWA_7500.HLD_TYP == 'A' 
            && DDB7500_AWA_7500.REF_OLD.trim().length() == 0) {
            CEP.TRC(SCCGWA, "BBBBBB");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            if (DDB7500_AWA_7500.REF_OLD.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB7500_AWA_7500.REF_OLD);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7500_AWA_7500.HLD_FLG != ' ' 
            && DDB7500_AWA_7500.HLD_FLG != '1' 
            && DDB7500_AWA_7500.HLD_FLG != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_FLG_INVALID;
            WS_FLD_NO = DDB7500_AWA_7500.HLD_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSHOLD);
        DCCSHOLD.DATA.AC = DDB7500_AWA_7500.AC;
        DCCSHOLD.DATA.SEQ = DDB7500_AWA_7500.SEQ;
        DCCSHOLD.DATA.HLD_TYP = DDB7500_AWA_7500.HLD_TYP;
        DCCSHOLD.DATA.SPR_TYP = DDB7500_AWA_7500.SPR_TYP;
        DCCSHOLD.DATA.CCY = DDB7500_AWA_7500.CCY;
        DCCSHOLD.DATA.CCY_TYP = DDB7500_AWA_7500.CCY_TYP;
        DCCSHOLD.DATA.AMT = DDB7500_AWA_7500.AMT;
        DCCSHOLD.DATA.SPR_NO = DDB7500_AWA_7500.SPR_NO;
        DCCSHOLD.DATA.SPR_NM = DDB7500_AWA_7500.SPR_NM;
        DCCSHOLD.DATA.RSN = DDB7500_AWA_7500.RSN;
        DCCSHOLD.DATA.EFF_DT = DDB7500_AWA_7500.EFF_DT;
        DCCSHOLD.DATA.EXP_DT = DDB7500_AWA_7500.EXP_DT;
        DCCSHOLD.DATA.RMK = DDB7500_AWA_7500.RMK;
        DCCSHOLD.DATA.HLD_BR = DDB7500_AWA_7500.HLD_BR;
        DCCSHOLD.DATA.LAW_NM1 = DDB7500_AWA_7500.LAW_NM1;
        DCCSHOLD.DATA.LAW_NO1 = DDB7500_AWA_7500.LAW_NO1;
        DCCSHOLD.DATA.LAW_NM2 = DDB7500_AWA_7500.LAW_NM2;
        DCCSHOLD.DATA.LAW_NO2 = DDB7500_AWA_7500.LAW_NO2;
        DCCSHOLD.DATA.REF_OLD = DDB7500_AWA_7500.REF_OLD;
        DCCSHOLD.DATA.HLD_CLS = DDB7500_AWA_7500.HLD_CLS;
        DCCSHOLD.DATA.CHK_OPT = DDB7500_AWA_7500.CHK_OPT;
        DCCSHOLD.DATA.PSWD = DDB7500_AWA_7500.PSWD;
        DCCSHOLD.DATA.TRK_DAT2 = DDB7500_AWA_7500.TRK_DAT2;
        DCCSHOLD.DATA.TRK_DAT3 = DDB7500_AWA_7500.TRK_DAT3;
        DCCSHOLD.DATA.HLD_FLG = DDB7500_AWA_7500.HLD_FLG;
        S000_CALL_DCZSHOLD();
    }
    public void S000_CALL_DCZSHOLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-HOLD", DCCSHOLD);
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

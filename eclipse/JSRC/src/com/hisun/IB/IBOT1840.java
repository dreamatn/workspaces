package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT1840 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    IBCSTRAC IBCSTRAC = new IBCSTRAC();
    SCCGWA SCCGWA;
    IBB1840_AWA_1840 IBB1840_AWA_1840;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "IBOT1840 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB1840_AWA_1840>");
        IBB1840_AWA_1840 = (IBB1840_AWA_1840) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.FR_TYP);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.FRIB_AC);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.FR_CNM);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.FR_ENM);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.BR);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.CCY);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.AMT);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.TO_TYP);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.TOIB_AC);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.TO_CNM);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.TO_ENM);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.REF_NO);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.MMO);
        CEP.TRC(SCCGWA, IBB1840_AWA_1840.RMK);
        if (IBB1840_AWA_1840.FRIB_AC.trim().length() == 0) {
            WS_ERR_MSG = IBCMSG_ERROR_MSG.INPUT;
            if (IBB1840_AWA_1840.FRIB_AC.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(IBB1840_AWA_1840.FRIB_AC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (IBB1840_AWA_1840.CCY.trim().length() == 0) {
            WS_ERR_MSG = IBCMSG_ERROR_MSG.INPUT;
            if (IBB1840_AWA_1840.CCY.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(IBB1840_AWA_1840.CCY);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (IBB1840_AWA_1840.AMT == 0) {
            WS_ERR_MSG = IBCMSG_ERROR_MSG.INPUT;
            WS_FLD_NO = (short) IBB1840_AWA_1840.AMT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (IBB1840_AWA_1840.TOIB_AC.trim().length() == 0) {
            WS_ERR_MSG = IBCMSG_ERROR_MSG.INPUT;
            if (IBB1840_AWA_1840.TOIB_AC.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(IBB1840_AWA_1840.TOIB_AC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = IBCMSG_ERROR_MSG.INPUT;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCSTRAC);
        IBCSTRAC.FR_TYP = IBB1840_AWA_1840.FR_TYP;
        IBCSTRAC.FRIB_AC = IBB1840_AWA_1840.FRIB_AC;
        IBCSTRAC.FR_CNM = IBB1840_AWA_1840.FR_CNM;
        IBCSTRAC.FR_ENM = IBB1840_AWA_1840.FR_ENM;
        IBCSTRAC.BR = IBB1840_AWA_1840.BR;
        IBCSTRAC.CCY = IBB1840_AWA_1840.CCY;
        IBCSTRAC.AMT = IBB1840_AWA_1840.AMT;
        IBCSTRAC.TO_TYP = IBB1840_AWA_1840.TO_TYP;
        IBCSTRAC.TOIB_AC = IBB1840_AWA_1840.TOIB_AC;
        IBCSTRAC.TO_CNM = IBB1840_AWA_1840.TO_CNM;
        IBCSTRAC.TO_ENM = IBB1840_AWA_1840.TO_ENM;
        IBCSTRAC.REF_NO = IBB1840_AWA_1840.REF_NO;
        IBCSTRAC.MMO = IBB1840_AWA_1840.MMO;
        IBCSTRAC.RMK = IBB1840_AWA_1840.RMK;
        CEP.TRC(SCCGWA, IBCSTRAC.FR_TYP);
        CEP.TRC(SCCGWA, IBCSTRAC.FRIB_AC);
        CEP.TRC(SCCGWA, IBCSTRAC.FR_CNM);
        CEP.TRC(SCCGWA, IBCSTRAC.FR_ENM);
        CEP.TRC(SCCGWA, IBCSTRAC.BR);
        CEP.TRC(SCCGWA, IBCSTRAC.CCY);
        CEP.TRC(SCCGWA, IBCSTRAC.AMT);
        CEP.TRC(SCCGWA, IBCSTRAC.TO_TYP);
        CEP.TRC(SCCGWA, IBCSTRAC.TOIB_AC);
        CEP.TRC(SCCGWA, IBCSTRAC.TO_CNM);
        CEP.TRC(SCCGWA, IBCSTRAC.TO_ENM);
        CEP.TRC(SCCGWA, IBCSTRAC.REF_NO);
        CEP.TRC(SCCGWA, IBCSTRAC.MMO);
        CEP.TRC(SCCGWA, IBCSTRAC.RMK);
        S000_CALL_IBZSTRAC();
    }
    public void S000_CALL_IBZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-S-TR-AC", IBCSTRAC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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

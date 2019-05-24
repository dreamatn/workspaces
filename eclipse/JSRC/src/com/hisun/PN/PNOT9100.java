package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT9100 {
    String CPN_U_PNZSMBCC = "PN-S-INF-NOTE";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCSMBCC PNCSMBCC = new PNCSMBCC();
    PNCOCISS PNCOCISS = new PNCOCISS();
    SCCGWA SCCGWA;
    PNB9100_AWA_9100 PNB9100_AWA_9100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT9100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB9100_AWA_9100>");
        PNB9100_AWA_9100 = (PNB9100_AWA_9100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_INF_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB9100_AWA_9100.AMT1 != 0 
            || PNB9100_AWA_9100.AMT2 != 0) {
            if (PNB9100_AWA_9100.AMT1 > PNB9100_AWA_9100.AMT2) {
                CEP.TRC(SCCGWA, "33333333333333333333");
                WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_AMT1_GT_AMT2;
                CEP.ERR(SCCGWA, WS_ERR_MSG, PNB9100_AWA_9100.AMT1_NO);
            }
        }
        if (PNB9100_AWA_9100.ISS_DT1 != 0 
            || PNB9100_AWA_9100.ISS_DT2 != 0) {
            if (PNB9100_AWA_9100.ISS_DT1 > PNB9100_AWA_9100.ISS_DT2) {
                CEP.TRC(SCCGWA, "22222222222222222222222");
                WS_ERR_MSG = PNCMSG_ERROR_MSG.ISSDT1_GT_ISSDT2;
                CEP.ERR(SCCGWA, WS_ERR_MSG, PNB9100_AWA_9100.ISS_DT1_NO);
            }
        }
    }
    public void B200_INF_NOTE_PROC() throws IOException,SQLException,Exception {
        B220_INF_PNT_PROC();
    }
    public void B220_INF_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCSMBCC);
        PNCSMBCC.FUNC = 'B';
        PNCSMBCC.DATA.KEY.CC_NO = PNB9100_AWA_9100.BILL_NO;
        PNCSMBCC.DATA.APP_AC = PNB9100_AWA_9100.APP_AC;
        PNCSMBCC.DATA.ISS_BR = PNB9100_AWA_9100.BR;
        PNCSMBCC.DATA.AMT_FR = PNB9100_AWA_9100.AMT1;
        PNCSMBCC.DATA.AMT_TO = PNB9100_AWA_9100.AMT2;
        PNCSMBCC.DATA.STS = PNB9100_AWA_9100.STS;
        PNCSMBCC.DATA.ISS_FD = PNB9100_AWA_9100.ISS_DT1;
        PNCSMBCC.DATA.ISS_TD = PNB9100_AWA_9100.ISS_DT2;
        CEP.TRC(SCCGWA, PNB9100_AWA_9100.BILL_KND);
        if (PNB9100_AWA_9100.BILL_KND.equalsIgnoreCase("02")) {
            PNCSMBCC.DATA.KEY.KND = "C00001";
        } else {
            PNCSMBCC.DATA.KEY.KND = PNB9100_AWA_9100.BILL_KND;
        }
        CEP.TRC(SCCGWA, PNB9100_AWA_9100.BILL_KND);
        PNCSMBCC.DATA.APP_ACNM = PNB9100_AWA_9100.APP_NAME;
        PNCSMBCC.DATA.ODUE_FLG = PNB9100_AWA_9100.ODUE_FLG;
        PNCSMBCC.DATA.PAGE_NUM = PNB9100_AWA_9100.PAGE_NUM;
        CEP.TRC(SCCGWA, PNCSMBCC.DATA.KEY.CC_NO);
        CEP.TRC(SCCGWA, PNCSMBCC.DATA.KEY.KND);
        S000_CALL_PNZSMBCC();
    }
    public void S000_CALL_PNZSMBCC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PNZSMBCC, PNCSMBCC);
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

package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT5800 {
    String CPN_TD_ZM_ACO_PROC = "TD-ZM-ACO-PROC";
    String WS_ERR_MSG = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    TDCMACO TDCMACO = new TDCMACO();
    SCCGWA SCCGWA;
    GDB5800_AWA_5800 GDB5800_AWA_5800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT5800 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB5800_AWA_5800>");
        GDB5800_AWA_5800 = (GDB5800_AWA_5800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_OPEN_TDAC_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5800_AWA_5800.PROD_CD);
        CEP.TRC(SCCGWA, GDB5800_AWA_5800.ID_TYPE);
        CEP.TRC(SCCGWA, GDB5800_AWA_5800.ID_NO);
        CEP.TRC(SCCGWA, GDB5800_AWA_5800.MAIN_AC);
        CEP.TRC(SCCGWA, GDB5800_AWA_5800.CI_NO);
        CEP.TRC(SCCGWA, GDB5800_AWA_5800.AC_NAME);
        CEP.TRC(SCCGWA, GDB5800_AWA_5800.DRAW_BR);
        CEP.TRC(SCCGWA, GDB5800_AWA_5800.BUS_KNB);
        CEP.TRC(SCCGWA, GDB5800_AWA_5800.FRG_IND);
        if (GDB5800_AWA_5800.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PROD_CD_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB5800_AWA_5800.ID_TYPE.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_ID_TYPE_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (GDB5800_AWA_5800.ID_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_ID_NO_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (GDB5800_AWA_5800.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_ID_NO_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_OPEN_TDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCMACO);
        TDCMACO.PROD_CD = GDB5800_AWA_5800.PROD_CD;
        TDCMACO.ID_TYPE = GDB5800_AWA_5800.ID_TYPE;
        TDCMACO.ID_NO = GDB5800_AWA_5800.ID_NO;
        TDCMACO.CI_NO = GDB5800_AWA_5800.CI_NO;
        TDCMACO.AC_NAME = GDB5800_AWA_5800.AC_NAME;
        TDCMACO.DRAW_MTH = GDB5800_AWA_5800.DRAW_MTH;
        TDCMACO.CCY = GDB5800_AWA_5800.CCY;
        TDCMACO.BV_CD = GDB5800_AWA_5800.BV_CD;
        TDCMACO.BV_TYPE = 'G';
        TDCMACO.CROS_CR = GDB5800_AWA_5800.CR_FLG;
        TDCMACO.CROS_DR = GDB5800_AWA_5800.DR_FLG;
        TDCMACO.BUS_KNB = GDB5800_AWA_5800.BUS_KNB;
        TDCMACO.FRG_IND = GDB5800_AWA_5800.FRG_IND;
        TDCMACO.OIC_NO = GDB5800_AWA_5800.OIC_NO;
        TDCMACO.RES_CENT = GDB5800_AWA_5800.RES_CENT;
        TDCMACO.SUB_DP = GDB5800_AWA_5800.SUB_DP;
        CEP.TRC(SCCGWA, TDCMACO.DRAW_MTH);
        S000_CALL_TDZMACO();
    }
    public void S000_CALL_TDZMACO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_TD_ZM_ACO_PROC, TDCMACO);
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

package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT2700 {
    String CPN_U_PNZUTKTP = "PN-U-TKTP-PNT";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCUTKTP PNCUTKTP = new PNCUTKTP();
    PNCOTKTP PNCOTKTP = new PNCOTKTP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNB2300_AWA_2300 PNB2300_AWA_2300;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT2700 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB2300_AWA_2300>");
        PNB2300_AWA_2300 = (PNB2300_AWA_2300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_IPT_CHK_PROC();
        B200_TKTP_PNT_PROC();
    }
    public void B100_IPT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB2300_AWA_2300.AMT == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_AMT_MUST_IPT, PNB2300_AWA_2300.AMT_NO);
        }
        if (PNB2300_AWA_2300.CCY.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_CCY_MUST_IPT, PNB2300_AWA_2300.CCY_NO);
        }
        if (PNB2300_AWA_2300.BILL_KND.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT, PNB2300_AWA_2300.BILL_KND_NO);
        }
        if (PNB2300_AWA_2300.BILL_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_NO_MUST_IPT, PNB2300_AWA_2300.BILL_NO_NO);
        }
        if (PNB2300_AWA_2300.STL_FLG == ' ') {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_STLFLG_NOT_IPT, PNB2300_AWA_2300.STL_FLG_NO);
        }
        if (PNB2300_AWA_2300.STL_NM.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_STLNM_NOT_IPT, PNB2300_AWA_2300.STL_NM_NO);
        }
        if (PNB2300_AWA_2300.CLR_CHNL == ' ') {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_CLR_CHNL_IPT, PNB2300_AWA_2300.CLR_CHNL);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA);
        }
    }
    public void B200_TKTP_PNT_PROC() throws IOException,SQLException,Exception {
        B210_TKTP_PNT_PROC();
    }
    public void B210_TKTP_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCUTKTP);
        PNCUTKTP.DATA.FUNC = 'T';
        PNCUTKTP.DATA.DRFT_NO = PNB2300_AWA_2300.BILL_NO;
        PNCUTKTP.DATA.KND = PNB2300_AWA_2300.BILL_KND;
        PNCUTKTP.DATA.ISS_DATE = PNB2300_AWA_2300.ISS_DATE;
        PNCUTKTP.DATA.CCY = PNB2300_AWA_2300.CCY;
        PNCUTKTP.DATA.ISS_AMT = PNB2300_AWA_2300.ISS_AMT;
        PNCUTKTP.DATA.AMT = PNB2300_AWA_2300.AMT;
        PNCUTKTP.DATA.BAL_AMT = PNB2300_AWA_2300.BAL_AMT;
        PNCUTKTP.DATA.STL_FLG = PNB2300_AWA_2300.STL_FLG;
        PNCUTKTP.DATA.CLR_CHNL = PNB2300_AWA_2300.CLR_CHNL;
        PNCUTKTP.DATA.STL_AC = PNB2300_AWA_2300.STL_AC;
        PNCUTKTP.DATA.STL_NM = PNB2300_AWA_2300.STL_NM;
        PNCUTKTP.DFT_CINO = PNB2300_AWA_2300.DFT_CINO;
        CEP.TRC(SCCGWA, PNB2300_AWA_2300.BILL_NO);
        CEP.TRC(SCCGWA, PNB2300_AWA_2300.BILL_KND);
        CEP.TRC(SCCGWA, PNB2300_AWA_2300.ISS_DATE);
        CEP.TRC(SCCGWA, PNB2300_AWA_2300.CCY);
        CEP.TRC(SCCGWA, PNB2300_AWA_2300.ISS_AMT);
        CEP.TRC(SCCGWA, PNB2300_AWA_2300.AMT);
        CEP.TRC(SCCGWA, PNB2300_AWA_2300.BAL_AMT);
        CEP.TRC(SCCGWA, PNB2300_AWA_2300.STL_FLG);
        CEP.TRC(SCCGWA, PNB2300_AWA_2300.CLR_CHNL);
        CEP.TRC(SCCGWA, PNB2300_AWA_2300.STL_AC);
        CEP.TRC(SCCGWA, PNB2300_AWA_2300.STL_NM);
        CEP.TRC(SCCGWA, PNCUTKTP.DFT_CINO);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            PNCUTKTP.STS = PNB2300_AWA_2300.STS;
            CEP.TRC(SCCGWA, PNCUTKTP.STS);
        }
        S000_CALL_PNZUTKTP();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            PNB2300_AWA_2300.STS = PNCUTKTP.STS;
            CEP.TRC(SCCGWA, PNB2300_AWA_2300.STS);
        }
    }
    public void S000_CALL_PNZUTKTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PNZUTKTP, PNCUTKTP);
        if (PNCUTKTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, PNCUTKTP.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

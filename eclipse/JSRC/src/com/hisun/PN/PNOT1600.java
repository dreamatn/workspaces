package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT1600 {
    String CPN_U_PNZUREFD = "PN-U-REF-PNT";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCUREFD PNCUREFD = new PNCUREFD();
    PNCOREFD PNCOREFD = new PNCOREFD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNB1600_AWA_1600 PNB1600_AWA_1600;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT1600 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB1600_AWA_1600>");
        PNB1600_AWA_1600 = (PNB1600_AWA_1600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_IPT_CHK_PROC();
        B200_REF_PNT_PROC();
    }
    public void B100_IPT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.BILL_NO);
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.BILL_KND);
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.ISS_DATE);
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.CCY);
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.ISS_AMT);
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.STL_FLG);
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.CLR_CHNL);
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.STL_AC);
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.STL_NM);
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.BCC_CINO);
        CEP.TRC(SCCGWA, 1111111);
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.BILL_KND);
        if (PNB1600_AWA_1600.BILL_KND.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT, PNB1600_AWA_1600.BILL_KND_NO);
        }
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.BILL_NO);
        if (PNB1600_AWA_1600.BILL_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_NO_MUST_IPT, PNB1600_AWA_1600.BILL_NO_NO);
        }
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.CCY);
        if (PNB1600_AWA_1600.CCY.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_CCY_MUST_IPT, PNB1600_AWA_1600.CCY_NO);
        }
        if (PNB1600_AWA_1600.ISS_AMT == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_AMT_MUST_IPT, PNB1600_AWA_1600.ISS_AMT_NO);
        }
        if (PNB1600_AWA_1600.STL_FLG == ' ') {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_STLFLG_NOT_IPT, PNB1600_AWA_1600.STL_FLG_NO);
        }
        if (PNB1600_AWA_1600.STL_NM.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_STLNM_NOT_IPT, PNB1600_AWA_1600.STL_NM_NO);
        }
        if (PNB1600_AWA_1600.CLR_CHNL == ' ') {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_CLR_CHNL_IPT, PNB1600_AWA_1600.CLR_CHNL_NO);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA);
        }
    }
    public void B200_REF_PNT_PROC() throws IOException,SQLException,Exception {
        B210_MIS_PNT_PROC();
    }
    public void B210_MIS_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCUREFD);
        PNCUREFD.DATA.KND = PNB1600_AWA_1600.BILL_KND;
        PNCUREFD.DATA.CC_NO = PNB1600_AWA_1600.BILL_NO;
        PNCUREFD.DATA.CCY = PNB1600_AWA_1600.CCY;
        PNCUREFD.DATA.ISS_DATE = PNB1600_AWA_1600.ISS_DATE;
        PNCUREFD.DATA.ISS_AMT = PNB1600_AWA_1600.ISS_AMT;
        CEP.TRC(SCCGWA, PNCUREFD.DATA.ISS_DATE);
        CEP.TRC(SCCGWA, PNCUREFD.DATA.ISS_AMT);
        PNCUREFD.DATA.STL_FLG = PNB1600_AWA_1600.STL_FLG;
        CEP.TRC(SCCGWA, PNB1600_AWA_1600.STL_AC);
        PNCUREFD.DATA.STL_AC = PNB1600_AWA_1600.STL_AC;
        PNCUREFD.DATA.BCC_CINO = PNB1600_AWA_1600.BCC_CINO;
        CEP.TRC(SCCGWA, PNCUREFD.DATA.STL_AC);
        CEP.TRC(SCCGWA, PNCUREFD.DATA.BCC_CINO);
        PNCUREFD.DATA.STL_NM = PNB1600_AWA_1600.STL_NM;
        PNCUREFD.DATA.FUNC = 'T';
        CEP.TRC(SCCGWA, PNCUREFD.DATA.FUNC);
        PNCUREFD.DATA.CLR_CHNL = PNB1600_AWA_1600.CLR_CHNL;
        S000_CALL_PNZUREFD();
    }
    public void S000_CALL_PNZUREFD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PNZUREFD, PNCUREFD);
        if (PNCUREFD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, PNCUREFD.RC);
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

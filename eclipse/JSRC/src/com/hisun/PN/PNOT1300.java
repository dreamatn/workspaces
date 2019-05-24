package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT1300 {
    String CPN_U_PNZUREFD = "PN-U-REF-PNT";
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "PN130";
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
    PNB1300_AWA_1300 PNB1300_AWA_1300;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT1300 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB1300_AWA_1300>");
        PNB1300_AWA_1300 = (PNB1300_AWA_1300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_IPT_CHK_PROC();
        B200_REF_PNT_PROC();
    }
    public void B100_IPT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PNB1300_AWA_1300.BILL_KND);
        CEP.TRC(SCCGWA, PNB1300_AWA_1300.BILL_NO);
        CEP.TRC(SCCGWA, PNB1300_AWA_1300.ENCRY_NO);
        CEP.TRC(SCCGWA, PNB1300_AWA_1300.ISS_DATE);
        CEP.TRC(SCCGWA, PNB1300_AWA_1300.ISS_AMT);
        CEP.TRC(SCCGWA, PNB1300_AWA_1300.STL_FLG);
        CEP.TRC(SCCGWA, PNB1300_AWA_1300.STL_AC);
        CEP.TRC(SCCGWA, PNB1300_AWA_1300.STL_NM);
        CEP.TRC(SCCGWA, PNB1300_AWA_1300.REASON);
        CEP.TRC(SCCGWA, PNB1300_AWA_1300.CLR_CHNL);
        CEP.TRC(SCCGWA, PNB1300_AWA_1300.CI_NO);
        if (PNB1300_AWA_1300.BILL_KND.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT, PNB1300_AWA_1300.BILL_KND_NO);
        }
        if (PNB1300_AWA_1300.BILL_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_NO_MUST_IPT, PNB1300_AWA_1300.BILL_NO_NO);
        }
        if (PNB1300_AWA_1300.ENCRY_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_ENCRY_NOT_IPT, PNB1300_AWA_1300.ENCRY_NO_NO);
        }
        if (PNB1300_AWA_1300.STL_FLG == ' ') {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_STLFLG_NOT_IPT, PNB1300_AWA_1300.STL_FLG_NO);
        }
        if (PNB1300_AWA_1300.STL_NM.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_STLNM_NOT_IPT, PNB1300_AWA_1300.STL_NM_NO);
        }
        if (PNB1300_AWA_1300.CLR_CHNL == ' ') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_CLR_CHNL_MSUST_IPT);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR);
        }
    }
    public void B200_REF_PNT_PROC() throws IOException,SQLException,Exception {
        B210_MIS_PNT_PROC();
    }
    public void B210_MIS_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCUREFD);
        PNCUREFD.DATA.FUNC = 'P';
        PNCUREFD.DATA.KND = PNB1300_AWA_1300.BILL_KND;
        PNCUREFD.DATA.CC_NO = PNB1300_AWA_1300.BILL_NO;
        PNCUREFD.DATA.ENCRY_NO = PNB1300_AWA_1300.ENCRY_NO;
        PNCUREFD.DATA.ISS_DATE = PNB1300_AWA_1300.ISS_DATE;
        PNCUREFD.DATA.ISS_AMT = PNB1300_AWA_1300.ISS_AMT;
        PNCUREFD.DATA.STL_FLG = PNB1300_AWA_1300.STL_FLG;
        PNCUREFD.DATA.STL_AC = PNB1300_AWA_1300.STL_AC;
        PNCUREFD.DATA.STL_NM = PNB1300_AWA_1300.STL_NM;
        PNCUREFD.DATA.REASON = PNB1300_AWA_1300.REASON;
        PNCUREFD.DATA.CLR_CHNL = PNB1300_AWA_1300.CLR_CHNL;
        PNCUREFD.DATA.BCC_CINO = PNB1300_AWA_1300.CI_NO;
        S000_CALL_PNZUREFD();
    }
    public void S000_CALL_PNZUREFD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PNZUREFD, PNCUREFD);
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

package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT2900 {
    String CPN_U_PNZUBLHX = "PN-U-BLHX-PNT";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCUBLHX PNCUBLHX = new PNCUBLHX();
    PNCOBLHX PNCOBLHX = new PNCOBLHX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNB2900_AWA_2900 PNB2900_AWA_2900;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT2900 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB2900_AWA_2900>");
        PNB2900_AWA_2900 = (PNB2900_AWA_2900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_IPT_CHK_PROC();
        B200_PNT_PROC();
    }
    public void B100_IPT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB2900_AWA_2900.BILL_KND.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT, PNB2900_AWA_2900.BILL_KND_NO);
        }
        if (PNB2900_AWA_2900.BILL_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_NO_MUST_IPT, PNB2900_AWA_2900.BILL_NO_NO);
        }
        if (PNB2900_AWA_2900.STL_FLG == ' ') {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_STLFLG_NOT_IPT, PNB2900_AWA_2900.STL_FLG_NO);
        }
        CEP.TRC(SCCGWA, PNB2900_AWA_2900.STL_CHNL);
        if (PNB2900_AWA_2900.STL_NM.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_STLNM_NOT_IPT, PNB2900_AWA_2900.STL_NM_NO);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR);
        }
    }
    public void B200_PNT_PROC() throws IOException,SQLException,Exception {
        B210_PNT_PROC();
    }
    public void B210_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCUBLHX);
        PNCUBLHX.DATA.KND = PNB2900_AWA_2900.BILL_KND;
        PNCUBLHX.DATA.DRFT_NO = PNB2900_AWA_2900.BILL_NO;
        PNCUBLHX.DATA.ISS_DATE = PNB2900_AWA_2900.ISS_DATE;
        PNCUBLHX.DATA.BAL_AMT = PNB2900_AWA_2900.BAL_AMT;
        PNCUBLHX.DATA.STL_FLG = PNB2900_AWA_2900.STL_FLG;
        PNCUBLHX.DATA.STL_AC = PNB2900_AWA_2900.STL_AC;
        PNCUBLHX.DATA.STL_NM = PNB2900_AWA_2900.STL_NM;
        PNCUBLHX.DATA.STL_CHNL = PNB2900_AWA_2900.STL_CHNL;
        PNCUBLHX.DATA.CI_NO = PNB2900_AWA_2900.CI_NO;
        CEP.TRC(SCCGWA, PNCUBLHX.DATA.ISS_DATE);
        CEP.TRC(SCCGWA, PNCUBLHX.DATA.BAL_AMT);
        S000_CALL_PNZUBLHX();
    }
    public void S000_CALL_PNZUBLHX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PNZUBLHX, PNCUBLHX);
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

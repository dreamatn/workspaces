package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5124 {
    String JIBS_tmp_str[] = new String[10];
    String WS_MSGID = " ";
    short WS_IDX = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSMTDF DDCSMTDF = new DDCSMTDF();
    SCCGWA SCCGWA;
    DDB5122_AWA_5122 DDB5122_AWA_5122;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5124 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5122_AWA_5122>");
        DDB5122_AWA_5122 = (DDB5122_AWA_5122) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5122_AWA_5122.I_OPT == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5122_AWA_5122.I_DD_AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5122_AWA_5122.I_CCY.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5122_AWA_5122.I_FLG == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5122_AWA_5122.I_OPT);
        CEP.TRC(SCCGWA, DDB5122_AWA_5122.I_DD_AC);
        CEP.TRC(SCCGWA, DDB5122_AWA_5122.I_CCY);
        CEP.TRC(SCCGWA, DDB5122_AWA_5122.I_PROLNO);
        CEP.TRC(SCCGWA, DDB5122_AWA_5122.I_MTDT);
        CEP.TRC(SCCGWA, DDB5122_AWA_5122.I_STDT);
        CEP.TRC(SCCGWA, DDB5122_AWA_5122.I_EDDT);
        IBS.init(SCCGWA, DDCSMTDF);
        DDCSMTDF.OPT = DDB5122_AWA_5122.I_OPT;
        DDCSMTDF.AC = DDB5122_AWA_5122.I_DD_AC;
        DDCSMTDF.CCY = DDB5122_AWA_5122.I_CCY;
        DDCSMTDF.FLG = DDB5122_AWA_5122.I_FLG;
        DDCSMTDF.CI_CNM = DDB5122_AWA_5122.I_CI_CNM;
        DDCSMTDF.CI_ENM = DDB5122_AWA_5122.I_CI_ENM;
        DDCSMTDF.ADP_NO = DDB5122_AWA_5122.I_PROLNO;
        DDCSMTDF.ADP_DATE = DDB5122_AWA_5122.I_MTDT;
        DDCSMTDF.ADP_STRDATE = DDB5122_AWA_5122.I_STDT;
        DDCSMTDF.ADP_EXPDATE = DDB5122_AWA_5122.I_EDDT;
        DDCSMTDF.ADP_POST_PERIOD = DDB5122_AWA_5122.I_JXOPT;
        DDCSMTDF.ADP_POST_FEQ = DDB5122_AWA_5122.I_SEQ2;
        DDCSMTDF.TIR_TYPE = DDB5122_AWA_5122.I_FCTYP;
        DDCSMTDF.AGSP_FLG = DDB5122_AWA_5122.I_CJTYP;
        for (WS_IDX = 1; WS_IDX <= 5; WS_IDX += 1) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDB5122_AWA_5122.TIR_INF[WS_IDX-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCSMTDF.TIR_DATA.TIR_INF[WS_IDX-1]);
        }
        S000_CALL_DDZSMTDF();
    }
    public void S000_CALL_DDZSMTDF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-Z-DDZSMTDF", DDCSMTDF);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
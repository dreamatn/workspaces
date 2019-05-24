package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSOCEN {
    String K_OUTPUT_FMT = "DD507";
    String WS_ERR_MSG = " ";
    DDZSOCEN_WS_OUTPUT_INF WS_OUTPUT_INF = new DDZSOCEN_WS_OUTPUT_INF();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCACAAC BPCACAAC = new BPCACAAC();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSOCEN DDCSOCEN;
    public void MP(SCCGWA SCCGWA, DDCSOCEN DDCSOCEN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSOCEN = DDCSOCEN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSOCEN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA_VALIDITY();
        B020_PROD_PARAM_PROC();
        B040_GET_GL_MST_PROC();
        B050_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSOCEN.PROD_CD);
        if (DDCSOCEN.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_PROD_PARAM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDCSOCEN.PROD_CD;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.CCY);
        S000_CALL_DDZIQPRD();
        WS_OUTPUT_INF.WS_CUST_TYPE = DDVMPRD.VAL.CUST_TYPE;
        WS_OUTPUT_INF.WS_CUR_TYP = DDVMPRD.VAL.CUR_TYPE;
        WS_OUTPUT_INF.WS_PB_IND = DDVMPRD.VAL.PRD_TOOL_PSB;
        WS_OUTPUT_INF.WS_PRD_AC_TYPE = DDCIQPRD.OUTPUT_DATA.AC_TYPE;
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_CUST_TYPE);
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_CUR_TYP);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.PRD_TOOL_PSB);
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_PB_IND);
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_CNY_COMP_TYPE);
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_FCY_TYPE);
    }
    public void B040_GET_GL_MST_PROC() throws IOException,SQLException,Exception {
        B040_10_GET_CI_INF();
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCACAAC);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 21;
        BPCACAAC.PROD_TYPE = DDCSOCEN.PROD_CD;
        BPCACAAC.CI_TYPE = CICCUST.DATA.ID_TYPE.charAt(0);
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACAAC;
        S000_CALL_BPZQCNGL();
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[3-1].MSTNO);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[4-1].MSTNO);
        WS_OUTPUT_INF.WS_GL_BQ = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        WS_OUTPUT_INF.WS_GL_HO = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
        WS_OUTPUT_INF.WS_GL_IAS39 = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[3-1].MSTNO;
        WS_OUTPUT_INF.WS_GL_UNUSE = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[4-1].MSTNO;
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_GL_BQ);
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_GL_HO);
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_GL_IAS39);
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_GL_UNUSE);
    }
    public void B040_10_GET_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = DDCSOCEN.CI_NO;
        S000_CALL_CISOCUST();
    }
    public void S000_CALL_CISOCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void B050_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_INF;
        SCCFMT.DATA_LEN = 42;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
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

package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT3020 {
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSSCHE LNCSSCHE = new LNCSSCHE();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    SCCGWA SCCGWA;
    LNB3020_AWA_3020 LNB3020_AWA_3020;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT3020 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB3020_AWA_3020>");
        LNB3020_AWA_3020 = (LNB3020_AWA_3020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CALL_LNZSSCHE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB3020_AWA_3020.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            if (LNB3020_AWA_3020.CTA_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB3020_AWA_3020.CTA_NO);
            S000_ERR_MSG_PROC();
        }
        if (LNB3020_AWA_3020.DT_FROM != 0 
            && LNB3020_AWA_3020.DT_TO != 0 
            && LNB3020_AWA_3020.DT_FROM > LNB3020_AWA_3020.DT_TO) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MAT_DT_GE_SRT_DT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CALL_LNZSSCHE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSCHE);
        LNCSSCHE.DATA_AREA.CONTRACT_NO = LNB3020_AWA_3020.CTA_NO;
        LNCSSCHE.DATA_AREA.SUF_NO = " ";
        LNCSSCHE.DATA_AREA.PROD_CD = LNB3020_AWA_3020.PROD_CD;
        CEP.TRC(SCCGWA, LNB3020_AWA_3020.PAY_TYP);
        LNCSSCHE.DATA_AREA.PAY_TYP = LNB3020_AWA_3020.PAY_TYP;
        LNCSSCHE.DATA_AREA.BANK_NAME = LNB3020_AWA_3020.BANK_NM;
        LNCSSCHE.DATA_AREA.CITY_CD = LNB3020_AWA_3020.CITY_CD;
        LNCSSCHE.DATA_AREA.DT_FROM = LNB3020_AWA_3020.DT_FROM;
        LNCSSCHE.DATA_AREA.DT_TO = LNB3020_AWA_3020.DT_TO;
        LNCSSCHE.DATA_AREA.TRAN_SEQ = LNB3020_AWA_3020.TRAN_SEQ;
        LNCSSCHE.PAGE_ROW = LNB3020_AWA_3020.PAGE_ROW;
        LNCSSCHE.PAGE_NUM = LNB3020_AWA_3020.PAGE_NUM;
        LNCSSCHE.SEQ_NO = LNB3020_AWA_3020.SEQ_NO;
        LNCSSCHE.CUR_FLG = LNB3020_AWA_3020.CUR_FLG;
        CEP.TRC(SCCGWA, LNCSSCHE.DATA_AREA.PAY_TYP);
        S000_CALL_LNZSSCHE();
    }
    public void S000_CALL_LNZSSCHE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-INQ-SSCHE", LNCSSCHE);
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

package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT8011 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSMSTR DCCSMSTR = new DCCSMSTR();
    SCCGWA SCCGWA;
    DCB8011_AWA_8011 DCB8011_AWA_8011;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT8011 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB8011_AWA_8011>");
        DCB8011_AWA_8011 = (DCB8011_AWA_8011) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB8011_AWA_8011.OPT_FLG);
        CEP.TRC(SCCGWA, DCB8011_AWA_8011.CARD_NO);
        B010_CHECK_INPUT();
        B020_INQ_IAMST_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCB8011_AWA_8011.OPT_FLG != '1' 
            && DCB8011_AWA_8011.OPT_FLG != '2' 
            && DCB8011_AWA_8011.OPT_FLG != '3') {
            WS_CNT = WS_CNT + 1;
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT;
            if (DCB8011_AWA_8011.OPT_FLG == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+DCB8011_AWA_8011.OPT_FLG);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB8011_AWA_8011.CARD_NO.trim().length() == 0) {
            WS_CNT = WS_CNT + 1;
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_AC_NOT_EXIST;
            if (DCB8011_AWA_8011.CARD_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DCB8011_AWA_8011.CARD_NO);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_CNT > 0) {
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B020_INQ_IAMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSMSTR);
        DCCSMSTR.KEY.OPT_FLG = DCB8011_AWA_8011.OPT_FLG;
        DCCSMSTR.KEY.CARD_NO = DCB8011_AWA_8011.CARD_NO;
        S000_CALL_DCZSMSTR();
    }
    public void S000_CALL_DCZSMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-INQ-IAMST", DCCSMSTR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

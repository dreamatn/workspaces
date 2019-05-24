package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5130 {
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    String K_HIS_REMARKS = "REMARK INTEREST";
    String K_PROD_CD = "REM INT";
    String K_HIS_CPB_NM1 = "REM INT";
    String K_HIS_RMKS = "REM INT";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCOWLAD LNCOWLAD = new LNCOWLAD();
    LNCSREMI LNCSREMI = new LNCSREMI();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    CICSSTC CICSSTC = new CICSSTC();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    LNCSSETL LNCSSETL = new LNCSSETL();
    SCCGWA SCCGWA;
    LNB5130_AWA_5130 LNB5130_AWA_5130;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT5130 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5130_AWA_5130>");
        LNB5130_AWA_5130 = (LNB5130_AWA_5130) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANCHE_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB5130_AWA_5130);
        CEP.TRC(SCCGWA, LNB5130_AWA_5130.CONT_TYP);
        CEP.TRC(SCCGWA, LNB5130_AWA_5130.CONT_NO);
        if (LNB5130_AWA_5130.CONT_NO.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            if (LNB5130_AWA_5130.CONT_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB5130_AWA_5130.CONT_NO);
            S000_ERR_MSG_PROC();
        }
        if (LNB5130_AWA_5130.CONT_TYP == ' ') {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_CONT_TYPE_MUST_INPUT;
            if (LNB5130_AWA_5130.CONT_TYP == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+LNB5130_AWA_5130.CONT_TYP);
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSETL);
        LNCSSETL.CONTRACT_ATTR = LNB5130_AWA_5130.CONT_TYP;
        LNCSSETL.CONTRACT_NO = LNB5130_AWA_5130.CONT_NO;
        if (LNB5130_AWA_5130.PAGE_ROW == 0) {
            LNCSSETL.PAGE_ROW = 25;
        } else {
            LNCSSETL.PAGE_ROW = LNB5130_AWA_5130.PAGE_ROW;
        }
        LNCSSETL.PAGE_NUM = LNB5130_AWA_5130.PAGE_NUM;
        S000_CALL_LNZSSETL();
    }
    public void S000_CALL_LNZSSETL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-LNZSSETL", LNCSSETL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

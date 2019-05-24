package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT4200 {
    String JIBS_tmp_str[] = new String[10];
    String K_INQ_FMT = "TD100";
    String K_PRDP_TYP = "PRDPR";
    String K_PRDT_MODEL = "MMDP";
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    TDOT4200_WS_PRDCODE WS_PRDCODE = new TDOT4200_WS_PRDCODE();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    DCCIMSTP DCCIMSTP = new DCCIMSTP();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCF100 TDCF100 = new TDCF100();
    TDCPROD TDCPROD = new TDCPROD();
    TDCQPMP TDCQPMP = new TDCQPMP();
    SCCGWA SCCGWA;
    TDB4200_AWA_4200 TDB4200_AWA_4200;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT4200 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB4200_AWA_4200>");
        TDB4200_AWA_4200 = (TDB4200_AWA_4200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B090_OUTPUT_INFO_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB4200_AWA_4200.FUNC);
        CEP.TRC(SCCGWA, TDB4200_AWA_4200.MAC_NO);
        CEP.TRC(SCCGWA, TDB4200_AWA_4200.PROD_CD);
        if (TDB4200_AWA_4200.MAC_NO.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_MAIN_AC_INP_ERR;
            S000_ERR_MSG_PROC();
        }
        TDCF100.MAC_NO = TDB4200_AWA_4200.MAC_NO;
        if (TDB4200_AWA_4200.FUNC == 'I') {
            B010_INQ_PROC();
        } else if (TDB4200_AWA_4200.FUNC == 'M') {
            B020_MOD_PROC();
        } else {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIMSTR);
        DCCIMSTR.KEY.VIA_AC = TDB4200_AWA_4200.MAC_NO;
        S000_CALL_DCZIMSTR();
        TDCF100.PROD_CD = DCCIMSTR.DATA.PRD_CODE;
    }
    public void B020_MOD_PROC() throws IOException,SQLException,Exception {
        if (TDB4200_AWA_4200.PROD_CD.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRODUCT_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDB4200_AWA_4200.PROD_CD;
        S000_CALL_BPZPQPRD();
        IBS.init(SCCGWA, TDCQPMP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
        CEP.TRC(SCCGWA, TDCPRDP.PRDMO_CD);
        if (!TDCPRDP.PRDMO_CD.equalsIgnoreCase("MMDP")) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRT_FLG_ERROR;
            S000_ERR_MSG_PROC();
        }
        DCCIMSTP.VIA_AC = TDB4200_AWA_4200.MAC_NO;
        DCCIMSTP.PRD_CODE = TDB4200_AWA_4200.PROD_CD;
        S000_CALL_DCZIMSTP();
        TDCF100.PROD_CD = DCCIMSTP.PRD_CODE;
    }
    public void B090_OUTPUT_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_INQ_FMT;
        SCCFMT.DATA_PTR = TDCF100;
        SCCFMT.DATA_LEN = 42;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZIMSTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-MST-UPD-PRD", DCCIMSTP);
        if (DCCIMSTP.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
        if (DCCIMSTR.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

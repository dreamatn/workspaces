package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT7130 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCACFIS IBCACFIS = new IBCACFIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB7110_AWA_7110 IBB7110_AWA_7110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT7130 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB7110_AWA_7110>");
        IBB7110_AWA_7110 = (IBB7110_AWA_7110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CLOSE_AC_PROC();
        B030_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.PRI_ACNO);
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.NOS_CD);
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.CCY);
        if (IBB7110_AWA_7110.PRI_ACNO.trim().length() == 0 
            && (IBB7110_AWA_7110.NOS_CD.trim().length() == 0 
            || IBB7110_AWA_7110.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
        if (IBB7110_AWA_7110.DRW_AMT == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.DRW_AMT_M);
        }
        if (IBB7110_AWA_7110.DRW_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.DRW_DATE_M);
        }
    }
    public void B020_CLOSE_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCACFIS);
        IBCACFIS.FUNC = IBB7110_AWA_7110.FCN_C;
        IBCACFIS.NOSTR_CD = IBB7110_AWA_7110.NOS_CD;
        IBCACFIS.PRIM_AC_NO = IBB7110_AWA_7110.PRI_ACNO;
        IBCACFIS.SEQ_NO = IBB7110_AWA_7110.SEQ_NO;
        IBCACFIS.PROD_CD = IBB7110_AWA_7110.PROD_CD;
        IBCACFIS.CCY = IBB7110_AWA_7110.CCY;
        IBCACFIS.DRW_AMT = IBB7110_AWA_7110.DRW_AMT;
        IBCACFIS.DRW_DATE = IBB7110_AWA_7110.DRW_DATE;
        IBCACFIS.RATE = IBB7110_AWA_7110.RATE;
        IBCACFIS.ADV_RATE = IBB7110_AWA_7110.ADV_RATE;
        IBCACFIS.OVD_RATE = IBB7110_AWA_7110.OVD_RATE;
        IBCACFIS.BUD_INT = IBB7110_AWA_7110.BUD_INT;
        IBCACFIS.DRW_INT = IBB7110_AWA_7110.DRW_INT;
        IBCACFIS.ERATE = IBB7110_AWA_7110.ERATE;
        IBCACFIS.EADV_RATE = IBB7110_AWA_7110.EADV_RAT;
        IBCACFIS.EOVD_RATE = IBB7110_AWA_7110.EOVD_RAT;
        IBCACFIS.ASET_AMT = IBB7110_AWA_7110.ASET_AMT;
        IBCACFIS.OTH_AC_ATTR = IBB7110_AWA_7110.OTAC_ATR;
        IBCACFIS.OTH_AC_NO = IBB7110_AWA_7110.OTH_ACNO;
        IBCACFIS.SUSP_SEQ = IBB7110_AWA_7110.SUSP_SEQ;
        IBCACFIS.CURR_BAL = IBB7110_AWA_7110.CURR_BAL;
        IBCACFIS.RATE1 = IBB7110_AWA_7110.RATE1;
        IBCACFIS.ADV_RAT1 = IBB7110_AWA_7110.ADV_RAT1;
        IBCACFIS.OVD_RAT1 = IBB7110_AWA_7110.OVD_RAT1;
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.CURR_BAL);
        CEP.TRC(SCCGWA, IBCACFIS.RATE1);
        CEP.TRC(SCCGWA, IBCACFIS.OVD_RAT1);
        CEP.TRC(SCCGWA, IBCACFIS.ADV_RAT1);
        S000_CALL_IBZACFIS();
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBB7110_AWA_7110.RATE1 = IBCACFIS.RATE1;
        IBB7110_AWA_7110.ADV_RAT1 = IBCACFIS.ADV_RAT1;
        IBB7110_AWA_7110.OVD_RAT1 = IBCACFIS.OVD_RAT1;
    }
    public void S000_CALL_IBZACFIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-ACFI-SUB", IBCACFIS);
        if (IBCACFIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCACFIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

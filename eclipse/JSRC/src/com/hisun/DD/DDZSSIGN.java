package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSSIGN {
    String JIBS_tmp_str[] = new String[10];
    String K_LOC_CCY = "CNY";
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "0001";
    String K_OUTPUT_FMT = "DD807";
    String K_HIS_CPB_NM = "DDCHCHQB";
    String K_HIS_RMKS = "CHEQUE ISSUE";
    String CPN_DD_U_SIGN_PROC = "DD-U-SIGN-PROC";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    String WS_TS_REC = " ";
    short WS_LEN = 0;
    long WS_STR_CHQ_NO = 0;
    long WS_END_CHQ_NO = 0;
    long WS_CHQ_NO = 0;
    short WS_RET = 0;
    short WS_RMDR = 0;
    char WS_CHQ_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCOSIGN DDCOSIGN = new DDCOSIGN();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    CICACCU CICACCU = new CICACCU();
    DDCUSIGN DDCUSIGN = new DDCUSIGN();
    DDRMST DDRMST = new DDRMST();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSSIGN DDCSSIGN;
    public void MP(SCCGWA SCCGWA, DDCSSIGN DDCSSIGN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSSIGN = DDCSSIGN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSSIGN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_SIG_CHQB_DDZUSIGN_PROC();
    }
    public void B010_SIG_CHQB_DDZUSIGN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUSIGN);
        DDCUSIGN.AC_NO = DDCSSIGN.AC_NO;
        DDCUSIGN.TX_TYP = DDCSSIGN.TX_TYP;
        DDCUSIGN.CNAME = DDCSSIGN.CNAME;
        DDCUSIGN.ENAME = DDCSSIGN.ENAME;
        DDCUSIGN.TXBV_NO = DDCSSIGN.TXBV_NO;
        DDCUSIGN.CCY = DDCSSIGN.CCY;
        DDCUSIGN.CCY_TYPE = DDCSSIGN.CCY_TYPE;
        for (WS_IDX = 1; WS_IDX <= DDCSSIGN.ROW_CNT; WS_IDX += 1) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCSSIGN.CHQ_DATA[WS_IDX-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUSIGN.CHQ_DATA[WS_IDX-1]);
        }
        DDCUSIGN.ROW_CNT = DDCSSIGN.ROW_CNT;
        DDCUSIGN.CBK_SIZE = DDCSSIGN.CBK_SIZE;
        DDCUSIGN.AUTH_TYP = DDCSSIGN.AUTH_TYP;
        DDCUSIGN.AUTH_NO = DDCSSIGN.AUTH_NO;
        DDCUSIGN.AUTH_CNM = DDCSSIGN.AUTH_CNM;
        DDCUSIGN.TXSMR = DDCSSIGN.TXSMR;
        CEP.TRC(SCCGWA, DDCUSIGN.AC_NO);
        CEP.TRC(SCCGWA, DDCSSIGN.CCY);
        CEP.TRC(SCCGWA, DDCUSIGN.CCY);
        S000_CALL_DDZUSIGN();
    }
    public void S000_CALL_DDZUSIGN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_U_SIGN_PROC, DDCUSIGN);
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

package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPRMR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CIZOPCP {
    boolean pgmRtn = false;
    String K_ID_TYPE = "10100";
    String K_STS_NORMAL = "00";
    String K_STS_REAL = "40";
    String K_STS_IDCK = "42";
    String K_STS_BSP = "24";
    String K_OUTPUT_FMT = "CI004";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_RMK = "CI OPEN PERSONAL INFO  ";
    String K_HIS_CPY = "CICOPCP";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    char WS_CIF_FLG = ' ';
    char WS_BAS_FLG = ' ';
    char WS_ID_FLG = ' ';
    char WS_NAM_FLG = ' ';
    char WS_ADR_FLG = ' ';
    char WS_CNT_FLG = ' ';
    char WS_LS1_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICOPCS CICOPCS = new CICOPCS();
    CICMPDM CICMPDM = new CICMPDM();
    CICMCNT CICMCNT = new CICMCNT();
    CICMADR CICMADR = new CICMADR();
    CICCKID CICCKID = new CICCKID();
    CICMLS1 CICMLS1 = new CICMLS1();
    CICMSTS CICMSTS = new CICMSTS();
    CICSVER CICSVER = new CICSVER();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICOPCPN CICOPCPN = new CICOPCPN();
    CICOOPCP CICOOPCP = new CICOOPCP();
    CICMID CICMID = new CICMID();
    CICMNAM CICMNAM = new CICMNAM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    CIRBAS CIRBAS = new CIRBAS();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRADR CIRADR = new CIRADR();
    CIRCNT CIRCNT = new CIRCNT();
    CIRLS1 CIRLS1 = new CIRLS1();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICOPCP CICOPCP;
    public void MP(SCCGWA SCCGWA, CICOPCP CICOPCP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICOPCP = CICOPCP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZOPCP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_ADD_PDM_INF();
        if (pgmRtn) return;
        B040_ADD_ID_INF();
        if (pgmRtn) return;
        B050_ADD_NAME_INF();
        if (pgmRtn) return;
        B060_ADD_ADDR_INF();
        if (pgmRtn) return;
        B070_ADD_CNT_INF();
        if (pgmRtn) return;
        B080_ADD_STS_INF();
        if (pgmRtn) return;
        B090_ADD_LS1_INF();
        if (pgmRtn) return;
        B100_ADD_VER_INF();
        if (pgmRtn) return;
        B120_ADD_LST_INF();
        if (pgmRtn) return;
        B110_ADD_HIS_INF();
        if (pgmRtn) return;
        B130_CHECK_CI_STS_24();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CICOPCP.DATA.ID_TYP.equalsIgnoreCase(K_ID_TYPE)) {
            IBS.init(SCCGWA, CICCKID);
            CICCKID.DATA.ID_NO = CICOPCP.DATA.ID_NO;
            S000_LINK_CIZCKID();
            if (pgmRtn) return;
            if (CICCKID.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_CARD_ILLEGAL);
            }
        }
        if (CICOPCP.DATA.CI_BBR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = CICOPCP.DATA.CI_BBR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        } else {
            CICOPCP.DATA.CI_BBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        if (CICOPCP.DATA.PAY_FLG == '1' 
            && CICOPCP.DATA.OCCP.equalsIgnoreCase("99990")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INOCC_NOT_PAY);
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICOPCP.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (WS_BAS_FLG == 'Y') {

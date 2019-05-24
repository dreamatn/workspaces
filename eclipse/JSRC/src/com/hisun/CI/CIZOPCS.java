package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class CIZOPCS {
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_RMK = "OPEN CI NO          ";
    String K_CI_CNM_TYP = "01";
    String K_CI_ENM_TYP = "03";
    String K_HIS_CPY = "CIRBAS";
    String K_OUTPUT_FMT = "CI001";
    String K_STS_NORMAL = "00";
    String K_STS_INF_NOTCOM = "23";
    String K_STS_REAL = "40";
    String K_STS_IDCK = "42";
    String K_INTEL_UNCOMLETE = "02";
    char K_DEFAULT_STCE_TYP = '2';
    int WS_I = 0;
    int WS_ID_LTH = 0;
    char WS_BAS_FLG = ' ';
    char WS_ID_FLAG = ' ';
    char WS_NAM_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    CICSSCH CICSSCH = new CICSSCH();
    CICMID CICMID = new CICMID();
    CICMNAM CICMNAM = new CICMNAM();
    CIRBAS CIRBAS = new CIRBAS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMSTS CICMSTS = new CICMSTS();
    CICMCDM CICMCDM = new CICMCDM();
    CICMFDM CICMFDM = new CICMFDM();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CICOOPCS CICOOPCS = new CICOOPCS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICOPCS CICOPCS;
    public void MP(SCCGWA SCCGWA, CICOPCS CICOPCS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICOPCS = CICOPCS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZOPCS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRBAS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICOPCS.DATA.CI_TYP == '2') {
            B020_AM_CDM_PROC();
            if (pgmRtn) return;
        }
        if (CICOPCS.DATA.CI_TYP == '3') {
            B030_AM_FDM_PROC();
            if (pgmRtn) return;
        }
        B040_AM_ID_PROC();
        if (pgmRtn) return;
        B050_AM_NAM_PROC();
        if (pgmRtn) return;
        B060_AM_STS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICOPCS.DATA.CI_NO);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICOPCS.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (WS_BAS_FLG == 'Y') {

package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCOCLWD;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCSGSEQ;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CIZSUFC {
    boolean pgmRtn = false;
    int WS_BEFORE_DATE = 0;
    char WS_BAS_FLG = ' ';
    char WS_FDM_FLG = ' ';
    char WS_ID_FLG = ' ';
    char WS_NM_FLG = ' ';
    char WS_ENM_FLG = ' ';
    char WS_CINM_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRFDM CIRFDM = new CIRFDM();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRALT CIRALT = new CIRALT();
    CIRGRPM CIRGRPM = new CIRGRPM();
    CIRBAS CIRBASO = new CIRBAS();
    CIRFDM CIRFDMO = new CIRFDM();
    CIRID CIRIDO = new CIRID();
    CIRNAM CIRNAMO = new CIRNAM();
    CIRALT CIRALTO = new CIRALT();
    CIRGRPM CIRGRPMO = new CIRGRPM();
    CIRBAS CIRBASN = new CIRBAS();
    CIRFDM CIRFDMN = new CIRFDM();
    CIRID CIRIDN = new CIRID();
    CIRNAM CIRNAMN = new CIRNAM();
    CIRALT CIRALTN = new CIRALT();
    CIRGRPM CIRGRPMN = new CIRGRPM();
    CIRACR CIRACR = new CIRACR();
    CIRGRLST CIRGRLST = new CIRGRLST();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    int WS_AC_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSUFC CICSUFC;
    public void MP(SCCGWA SCCGWA, CICSUFC CICSUFC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSUFC = CICSUFC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSUFC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_UPDATE_BAS_INF();
        if (pgmRtn) return;
        B030_UPDATE_FDM_INF();
        if (pgmRtn) return;
        B040_UPDATE_ID_INF();
        if (pgmRtn) return;
        B050_UPDATE_NM_INF();
        if (pgmRtn) return;
        B060_UPDATE_ENM_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSUFC.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICSUFC.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSUFC.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSUFC.DATA.CI_NM);
        if (CICSUFC.DATA.ID_TYPE.trim().length() > 0 
            && CICSUFC.DATA.ID_NO.trim().length() > 0 
            && CICSUFC.DATA.CI_NM.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.ID_TYPE = CICSUFC.DATA.ID_TYPE;
            CIRBAS.ID_NO = CICSUFC.DATA.ID_NO;

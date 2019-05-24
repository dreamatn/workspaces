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

public class CIZSUPC {
    boolean pgmRtn = false;
    int WS_BEFORE_DATE = 0;
    char WS_BAS_FLG = ' ';
    char WS_PDM_FLG = ' ';
    char WS_ID_FLG = ' ';
    char WS_NM_FLG = ' ';
    char WS_ENM_FLG = ' ';
    char WS_CNT_FLG = ' ';
    char WS_CINM_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRPDM CIRPDM = new CIRPDM();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRCNT CIRCNT = new CIRCNT();
    CIRALT CIRALT = new CIRALT();
    CIRGRPM CIRGRPM = new CIRGRPM();
    CIRBAS CIRBASO = new CIRBAS();
    CIRPDM CIRPDMO = new CIRPDM();
    CIRID CIRIDO = new CIRID();
    CIRNAM CIRNAMO = new CIRNAM();
    CIRCNT CIRCNTO = new CIRCNT();
    CIRALT CIRALTO = new CIRALT();
    CIRGRPM CIRGRPMO = new CIRGRPM();
    CIRBAS CIRBASN = new CIRBAS();
    CIRPDM CIRPDMN = new CIRPDM();
    CIRID CIRIDN = new CIRID();
    CIRNAM CIRNAMN = new CIRNAM();
    CIRCNT CIRCNTN = new CIRCNT();
    CIRALT CIRALTN = new CIRALT();
    CIRGRPM CIRGRPMN = new CIRGRPM();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRGRLST CIRGRLST = new CIRGRLST();
    CICSAGEN CICSAGEN = new CICSAGEN();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    DDCSZFJZ DDCSZFJZ = new DDCSZFJZ();
    DCCIQHLD DCCIQHLD = new DCCIQHLD();
    DDRFBID DDRFBID = new DDRFBID();
    DDCRFBID DDCRFBID = new DDCRFBID();
    int WS_AC_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSUPC CICSUPC;
    public void MP(SCCGWA SCCGWA, CICSUPC CICSUPC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSUPC = CICSUPC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSUPC return!");
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
        B030_UPDATE_PDM_INF();
        if (pgmRtn) return;
        B040_UPDATE_ID_INF();
        if (pgmRtn) return;
        B050_UPDATE_NM_INF();
        if (pgmRtn) return;
        B060_UPDATE_ENM_INF();
        if (pgmRtn) return;
        B070_UPDATE_CNT_INF();
        if (pgmRtn) return;
        if (CICSUPC.DATA.AG_FLG == '1') {
            B080_ADD_AGENT_INF();
            if (pgmRtn) return;
        }
        B022_UPD_TAX_CHECK_ATL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSUPC.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICSUPC.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSUPC.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSUPC.DATA.CI_NM);
        if (CICSUPC.DATA.ID_TYPE.trim().length() > 0 
            && CICSUPC.DATA.ID_NO.trim().length() > 0 
            && CICSUPC.DATA.CI_NM.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.ID_TYPE = CICSUPC.DATA.ID_TYPE;
            CIRBAS.ID_NO = CICSUPC.DATA.ID_NO;

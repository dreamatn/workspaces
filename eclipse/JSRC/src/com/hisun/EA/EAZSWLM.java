package com.hisun.EA;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPRMR;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class EAZSWLM {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    int WS_CURRENT_ROW = 0;
    int WS_MIN_ROW = 0;
    int WS_MAX_ROW = 0;
    int WS_RECORD_NUM = 0;
    EACMSG_ERROR_MSG EACMSG_ERROR_MSG = new EACMSG_ERROR_MSG();
    EARWLST EARWLST = new EARWLST();
    EACO570 EACO570 = new EACO570();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCBINF SCCBINF = new SCCBINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EACSWLM EACSWLM;
    public void MP(SCCGWA SCCGWA, EACSWLM EACSWLM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EACSWLM = EACSWLM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EAZSWLM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (EACSWLM.FUNC == 'A') {
            CEP.TRC(SCCGWA, "B010-INSERT-SWLM-INF");
            B090_CHECK_DATA_FOR_INSERT();
            if (pgmRtn) return;
            B010_INSERT_SWLM_INF();
            if (pgmRtn) return;
        } else if (EACSWLM.FUNC == 'M') {
            CEP.TRC(SCCGWA, "B030-REWRITE-SWLM-INF");
            B080_CHECK_DATA_FOR_UPDATE();
            if (pgmRtn) return;
            R000_READUPDATE_SWLM_INF();
            if (pgmRtn) return;
            B030_REWRITE_SWLM_INF();
            if (pgmRtn) return;
        } else if (EACSWLM.FUNC == 'D') {
            CEP.TRC(SCCGWA, "B020-DELETE-SWLM-INF");
            B070_CHECK_DATA_FOR_DELETE();
            if (pgmRtn) return;
            R000_READUPDATE_SWLM_INF();
            if (pgmRtn) return;
            B020_DELETE_SWLM_INF();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_FUNC_ERROR);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_READUPDATE_SWLM_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSWLM.AC_FLG);
        CEP.TRC(SCCGWA, EACSWLM.DC_FLG);
        CEP.TRC(SCCGWA, EACSWLM.LST_FLG);
        CEP.TRC(SCCGWA, EACSWLM.LST_AC);
        CEP.TRC(SCCGWA, EACSWLM.REQ_SYS);
        CEP.TRC(SCCGWA, EACSWLM.CON_BNK);
        EARWLST.KEY.REQ_SYS = EACSWLM.REQ_SYS;
        EARWLST.KEY.DC_FLG = EACSWLM.DC_FLG;
        EARWLST.KEY.AC_FLG = EACSWLM.AC_FLG;
        EARWLST.KEY.LST_FLG = EACSWLM.LST_FLG;
        EARWLST.KEY.LST_AC = EACSWLM.LST_AC;
        EARWLST.CON_BNK = EACSWLM.CON_BNK;
        EATWLST_RD = new DBParm();
        EATWLST_RD.TableName = "EATWLST";
        EATWLST_RD.eqWhere = "REQ_SYS, DC_FLG, AC_FLG, LST_FLG, LST_AC";
        EATWLST_RD.upd = true;
        IBS.READ(SCCGWA, EARWLST, EATWLST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_SWLM_INFO_NOTFOUND);
        }
    }
    public void B090_CHECK_DATA_FOR_INSERT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSWLM.AC_FLG);
        CEP.TRC(SCCGWA, EACSWLM.DC_FLG);
        CEP.TRC(SCCGWA, EACSWLM.LST_FLG);
        CEP.TRC(SCCGWA, EACSWLM.LST_AC);
        CEP.TRC(SCCGWA, EACSWLM.REQ_SYS);
        if (EACSWLM.REQ_SYS.trim().length() == 0) {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_CHNL_MUST_INPUT);
        }
        if (EACSWLM.AC_FLG == ' ') {

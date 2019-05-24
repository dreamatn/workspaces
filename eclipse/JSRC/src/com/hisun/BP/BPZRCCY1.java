package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZRCCY1 {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRCCY";
    String K_PARM_CCY = "CCY";
    String CPN_REC_NHIS = "BP-REC-NHIS  ";
    String K_HIS_REMARKS = "CCY DETAILS INFO ";
    String K_HIS_COPYBOOK_NAME = "BPCOHCCY";
    String CPN_R_FEE_BPZPRMM = "BP-PARM-MAINTAIN    ";
    String CPN_R_FEE_BPZPRMB = "BP-PARM-BROWSE      ";
    String WS_TEMP_RECORD = " ";
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    short WS_CCY_CD = 0;
    short WS_CNT1 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCOHCCY BPCOOHCY = new BPCOHCCY();
    BPCOHCCY BPCONHCY = new BPCOHCCY();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    BPRPRMT BPRPCCY = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCRCCY BPCRCCY;
    public void MP(SCCGWA SCCGWA, BPCRCCY BPCRCCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCCY = BPCRCCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRCCY1 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPCCY);
        IBS.init(SCCGWA, BPCOOHCY);
        IBS.init(SCCGWA, BPCONHCY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRCCY.OP_FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCCY.OP_FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCCY.OP_FUNC == 'M') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCCY.OP_FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCCY.OP_FUNC == 'S') {
            B051_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCCY.OP_FUNC == 'R') {
            B052_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCCY.OP_FUNC == 'E') {
            B053_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        S000_TRANS_BPRPCCY();
        if (pgmRtn) return;
        if (BPCRCCY.DATA.CCY.trim().length() > 0) {
            BPCPRMM.FUNC = '3';
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            S010_TRANS_RCCY_DATA();
            if (pgmRtn) return;
        } else {
            T000_BROWSE_BPRPCCY();
            if (pgmRtn) return;
        }
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        S000_TRANS_BPRPCCY();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        S010_TRANS_RCCY_DATA();
        if (pgmRtn) return;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        S000_TRANS_BPRPCCY();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.DATA);

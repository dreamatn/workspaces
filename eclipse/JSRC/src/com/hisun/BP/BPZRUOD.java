package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZRUOD {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRUOD";
    String K_PARM_UOD = "UOD";
    String K_PARM_DESC = "OVER DRAFT MAINTAIN";
    String K_PARM_CDESC = "OVER DRAFT MAINTAIN";
    String CPN_REC_NHIS = "BP-REC-NHIS  ";
    String K_HIS_REMARKS = "UOD DETAILS INFO ";
    String K_HIS_COPYBOOK_NAME = "BPCOHUOD";
    String CPN_R_FEE_BPZPRMM = "BP-PARM-MAINTAIN    ";
    String CPN_R_FEE_BPZRMBPM = "BP-R-MBRW-PARM      ";
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    short WS_CNT1 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    BPCOSUOD BPCOOUOD = new BPCOSUOD();
    BPRPRMT BPRPUOD = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPARM BPRMBPM = new BPRPARM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCOUOD BPCORUOD;
    BPCOSUOD BPCOSUOD;
    public void MP(SCCGWA SCCGWA, BPCOUOD BPCORUOD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCORUOD = BPCORUOD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRUOD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCOSUOD = (BPCOSUOD) BPCORUOD.POINTER;
        IBS.init(SCCGWA, BPCPRMM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCORUOD.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCORUOD.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCORUOD.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCORUOD.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCORUOD.BR == 'S') {
            B051_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCORUOD.BR == 'N') {
            B052_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCORUOD.BR == 'E') {
            B053_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {

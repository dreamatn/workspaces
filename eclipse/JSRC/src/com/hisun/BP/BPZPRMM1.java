package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class BPZPRMM1 {
    boolean pgmRtn = false;
    char BPZPRMM1_FILLER1 = ' ';
    char WS_CURR_EFF_FLG = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_EFF_TYP = ' ';
    BPRPARM BPRPARM = new BPRPARM();
    BPRPARP BPRPARP = new BPRPARP();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    BPCPRMM BPCPRMM;
    BPRPRMT BPRPRMT = new BPRPRMT();
    public void MP(SCCGWA SCCGWA, BPCPRMM BPCPRMM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPRMM = BPCPRMM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPRMM1 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPRMM.RC.RC_APP = "BP";
        BPCPRMM.RC.RC_RTNCODE = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.DAT_PTR);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT);
        if (BPRPRMT.KEY.BK.trim().length() == 0) {
            BPRPRMT.KEY.BK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        if (BPCPRMM.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (BPCPRMM.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (BPCPRMM.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (BPCPRMM.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (BPCPRMM.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCPRMM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        if ((BPCPRMM.FUNC != '1') 
            && BPCPRMM.EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPRPARP.KEY.TYPE = BPRPRMT.KEY.TYP;
        T00_READ_BPTPARP();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            WS_EFF_TYP = BPRPARP.ENA;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_EFF_TYP == '0' 
            && BPCPRMM.FUNC == '0' 
            && BPCPRMM.EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((BPCPRMM.FUNC == '0' 
            || BPCPRMM.FUNC == '2') 
            && BPCPRMM.EXP_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EXP_DT, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_CURR_EFF_FLG = ' ';
        WS_UPDATE_FLG = ' ';
        if (BPCPRMM.EFF_DT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_CURR_EFF_FLG = 'Y';
        }
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_ALEADY_EXIST, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRPARM);
        BPRPARM.KEY.TYPE = BPRPRMT.KEY.TYP;
        BPRPARM.KEY.CODE = BPRPRMT.KEY.CD;
        BPRPARM.KEY.IBS_AC_BK = BPRPRMT.KEY.BK;
        BPRPARM.EFF_DATE = BPCPRMM.EFF_DT;
        BPRPARM.EXP_DATE = BPCPRMM.EXP_DT;
        BPRPARM.DESC = BPRPRMT.DESC;
        BPRPARM.CDESC = BPRPRMT.CDESC;

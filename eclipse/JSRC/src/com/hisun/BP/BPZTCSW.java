package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTCSW {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    int WS_FLG_POS = 0;
    char WS_NEW_FLG = ' ';
    short WS_IDX = 0;
    BPRTRT BPRTRT = new BPRTRT();
    BPRCPN BPRCPN = new BPRCPN();
    SCCRLPRM SCCRLPRM = new SCCRLPRM();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    BPCTCSW BPCTCSW;
    public void MP(SCCGWA SCCGWA, BPCTCSW BPCTCSW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTCSW = BPCTCSW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTCSW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCTCSW.RC.RC_APP = "BP";
        BPCTCSW.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARMC);
        IBS.init(SCCGWA, BPCPRMM);
        if (BPCTCSW.FUNC == '0'
            || BPCTCSW.FUNC == '1') {
            BPCPARMC.KEY.TYP = "TRT";
            BPCPARMC.KEY.CD = BPCTCSW.TR_CODE;
            WS_FLG_POS = 5 + 1;
            WS_IDX = 9;
            if (BPCTCSW.FUNC == '0') {
                WS_NEW_FLG = 'Y';
            } else {
                WS_NEW_FLG = 'N';
            }
        } else if (BPCTCSW.FUNC == '2'
            || BPCTCSW.FUNC == '3') {
            BPCPARMC.KEY.TYP = "CPN";
            BPCPARMC.KEY.CD = BPCTCSW.CPN_NAME;
            WS_FLG_POS = 8 + 1 + 1 + 32 + 1;
            WS_IDX = 2;
            if (BPCTCSW.FUNC == '2') {
                WS_NEW_FLG = 'Y';
            } else {
                WS_NEW_FLG = 'N';
            }
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCTCSW.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        S000_SWITCH_DB();
        if (pgmRtn) return;
        S000_SWITCH_MMT();
        if (pgmRtn) return;
    }
    public void S000_SWITCH_DB() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '3';
        CEP.TRC(SCCGWA, BPCPARMC.KEY.CD);
        BPCPRMM.DAT_PTR = BPCPARMC;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCTCSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPARMC.DATA_TXT);
        JIBS_tmp_str[1] = "" + WS_NEW_FLG;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_tmp_str[0] = JIBS_tmp_str[0].substring(0, WS_FLG_POS - 1) + JIBS_tmp_str[1] + JIBS_tmp_str[0].substring(WS_FLG_POS + 1 - 1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], BPCPARMC.DATA_TXT);
        BPCPRMM.FUNC = '0';
        BPCPRMM.DAT_PTR = BPCPARMC;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UPDATE_REC_ERR, BPCTCSW.RC);
        }
    }
    public void S000_SWITCH_MMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCRLPRM);
        SCCRLPRM.IDX = WS_IDX;
        SCZRLPRM SCZRLPRM = new SCZRLPRM();
        SCZRLPRM.MP(SCCGWA, SCCRLPRM);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-PARM-MAINTAIN";
        SCCCALL.COMMAREA_PTR = BPCPRMM;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTCSW.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "BPCTCSW = ");
            CEP.TRC(SCCGWA, BPCTCSW);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

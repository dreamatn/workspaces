package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSBPRD {
    boolean pgmRtn = false;
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_REC_BPRD = "BP-R-BRW-BVPRD      ";
    String K_HIS_REMARKS = "BV PRODUCT PARM MAINTAIN";
    String K_CPY_BPRPBPRD = "BPRPBPRD";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSBPRD_WS_BPRD_KEY WS_BPRD_KEY = new BPZSBPRD_WS_BPRD_KEY();
    char WS_TBL_BPRD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRPBPRD BPRPBPRD = new BPRPBPRD();
    BPRPBPRD BPROBPRD = new BPRPBPRD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCOBPRO BPCOBPRO = new BPCOBPRO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRBPRD BPCRBPRD = new BPCRBPRD();
    BPRBPRD BPRBPRD = new BPRBPRD();
    SCCGWA SCCGWA;
    BPCSBPRD BPCSBPRD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBPRD BPCSBPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBPRD = BPCSBPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBPRD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBPRD.FUNC);
        if (BPCSBPRD.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBPRD.FUNC == 'A') {
            B020_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBPRD.FUNC == 'M') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBPRD.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B050_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBPRD);
        IBS.init(SCCGWA, BPRBPRD);
        if (BPCSBPRD.IBS_AC_BK.trim().length() == 0) {
            BPRBPRD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        } else {
            BPRBPRD.KEY.IBS_AC_BK = BPCSBPRD.IBS_AC_BK;
        }
        WS_BPRD_KEY.WS_BPRD_BR = BPCSBPRD.BR;
        WS_BPRD_KEY.WS_BPRD_CODE = BPCSBPRD.CODE;
        BPRBPRD.EFF_DT = BPCSBPRD.EFF_DATE;
        BPRBPRD.KEY.CODE = IBS.CLS2CPY(SCCGWA, WS_BPRD_KEY);
        BPCRBPRD.INFO.FUNC = 'R';
        BPCRBPRD.INFO.POINTER = BPRBPRD;
        BPCRBPRD.INFO.LEN = 512;
        S000_CALL_BPZRBPRD();
        if (pgmRtn) return;
        if (BPCRBPRD.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BPRD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBPRD);
        IBS.init(SCCGWA, BPRBPRD);
        BPCRBPRD.INFO.FUNC = 'C';
        if (BPCSBPRD.IBS_AC_BK.trim().length() == 0) {
            BPRBPRD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        } else {
            BPRBPRD.KEY.IBS_AC_BK = BPCSBPRD.IBS_AC_BK;
        }
        WS_BPRD_KEY.WS_BPRD_BR = BPCSBPRD.BR;
        WS_BPRD_KEY.WS_BPRD_CODE = BPCSBPRD.CODE;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            R000_TRANS_DATA_PARAMETER_CH();
            if (pgmRtn) return;
        } else {
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
        }
        BPCRBPRD.INFO.POINTER = BPRBPRD;
        BPCRBPRD.INFO.LEN = 512;
        S000_CALL_BPZRBPRD();
        if (pgmRtn) return;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPBPRD;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBPRD);
        IBS.init(SCCGWA, BPRBPRD);
        if (BPCSBPRD.IBS_AC_BK.trim().length() == 0) {
            BPRBPRD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        } else {
            BPRBPRD.KEY.IBS_AC_BK = BPCSBPRD.IBS_AC_BK;
        }
        WS_BPRD_KEY.WS_BPRD_BR = BPCSBPRD.BR;
        WS_BPRD_KEY.WS_BPRD_CODE = BPCSBPRD.CODE;
        BPRBPRD.KEY.CODE = IBS.CLS2CPY(SCCGWA, WS_BPRD_KEY);
        BPRBPRD.EFF_DT = BPCSBPRD.EFF_DATE;
        BPRBPRD.EXP_DT = BPCSBPRD.EXP_DATE;
        BPCRBPRD.INFO.FUNC = 'U';
        BPCRBPRD.INFO.LEN = 512;
        BPCRBPRD.INFO.POINTER = BPRBPRD;
        S000_CALL_BPZRBPRD();
        if (pgmRtn) return;
        if (BPCRBPRD.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BPRD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBPRD);
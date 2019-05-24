package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSMORG {
    boolean pgmRtn = false;
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String BP_BR_BROWSE = "BP-BR-BROWSE      ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "ORG  INFOMATION MAINTAIN";
    String CPN_R_ORGS_MT = "BP-R-ORGS-MAINTAIN  ";
    String K_CPY_BPRPORGM = "BPRPORGM";
    int K_MAX_CNT = 2000;
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String K_TYPE_ORGM = "ORGM";
    char K_STS_CLOSE = 'C';
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_ORGM_READ = "BP-R-BRW-ORGM       ";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_CNT = 0;
    int WS_REC_CNT = 0;
    short WS_STR_CNT = 0;
    short WS_STR_LEN = 0;
    short WS_STR_FROM = 0;
    short WS_BR_LEN = 0;
    short WS_DATA_LEN = 0;
    BPZSMORG_WS_ORGM_KEY WS_ORGM_KEY = new BPZSMORG_WS_ORGM_KEY();
    BPZSMORG_WS_SORGB_HEAD WS_SORGB_HEAD = new BPZSMORG_WS_SORGB_HEAD();
    BPZSMORG_WS_SORGB_DETAIL WS_SORGB_DETAIL = new BPZSMORG_WS_SORGB_DETAIL();
    char WS_END_FLAG = ' ';
    char WS_STOP_FLAG = ' ';
    char WS_OUT_FLAG = ' ';
    char WS_FND_TELLER = ' ';
    char WS_RGND_FLG = ' ';
    char WS_PREAC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCRORGM BPCRORGM = new BPCRORGM();
    BPRORGM BPRPORGM = new BPRORGM();
    BPRORGM BPROORGM = new BPRORGM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSORGO BPCSORGO = new BPCSORGO();
    BPRTLT BPRTLT = new BPRTLT();
    BPCRTLTB BPCRTLTB = new BPCRTLTB();
    BPRRGND BPRRGND = new BPRRGND();
    BPRPREAC BPRPREAC = new BPRPREAC();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCRMOGS BPCRMOGS = new BPCRMOGS();
    BPRORGS BPRORGS = new BPRORGS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPCSMORG BPCSMORG;
    public void MP(SCCGWA SCCGWA, BPCSMORG BPCSMORG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMORG = BPCSMORG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMORG return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMORG.RC);
        IBS.init(SCCGWA, BPCPQORG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_DATA();
        if (pgmRtn) return;
        if (BPCSMORG.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMORG.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMORG.FUNC == 'U') {
            R000_CHECK_ORG_STS();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMORG.FUNC == 'D') {
            R000_CHECK_ORG_STS();
            if (pgmRtn) return;
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMORG.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMORG.FUNC != 'B') {
            B060_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK_DATA() throws IOException,SQLException,Exception {
        if (BPCSMORG.FUNC == 'A') {

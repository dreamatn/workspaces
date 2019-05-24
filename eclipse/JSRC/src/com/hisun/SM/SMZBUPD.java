package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPRAWA;
import com.hisun.BP.BPRDDIC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMM;
import com.hisun.SC.SCCWOUT;
import com.hisun.SC.SCRPRMT;

public class SMZBUPD {
    boolean pgmRtn = false;
    String CPN_DDIC_MAINTAIN = "SM-R_DDIC_MAINTAIN  ";
    SMZBUPD_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMZBUPD_WS_TEMP_VARIABLE();
    short WS_FLD_NO = 0;
    String WS_INFO = " ";
    short WS_I = 0;
    short WS_J = 0;
    String WK_NAME = "        ";
    char WK_NAME_ERROR = ' ';
    short WK_I1 = 0;
    short WK_I2 = 0;
    short WK_NUM = 0;
    short WK_NUM1 = 0;
    int WK_BUPD_FLD_LTH = 0;
    int WK_BUPD_FLD_OST = 0;
    int WK_INPT_FLD_LTH = 0;
    int WK_INPT_FLD_OST = 0;
    int WK_OUPT_FLD_LTH = 0;
    int WK_OUPT_FLD_OST = 0;
    short WK_ARRAY_NO = 0;
    int WK_ARRAY_OST = 0;
    int WK_GRP_FLD_LTH = 0;
    int WK_NXT_OSTI = 0;
    int WK_NXT_OSTO = 0;
    String WS_TSQ_REC = " ";
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCPRMM SCCPRMM = new SCCPRMM();
    SCRPRMT SCRPRMT = new SCRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRAWA BPRAWAD = new BPRAWA();
    SMCTDICM SMCTDICM = new SMCTDICM();
    SMCODICM SMCODICM = new SMCODICM();
    BPRDDIC BPRDDIC = new BPRDDIC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SMCBUPD SMCBUPD;
    public void MP(SCCGWA SCCGWA, SMCBUPD SMCBUPD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCBUPD = SMCBUPD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZBUPD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "SM";
        SCCPRMM.DAT_PTR = SCRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B100_READU_PROCESS();
        if (pgmRtn) return;
        B100_REWRITE_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '4';
        SCRPRMT.KEY.TYP = SMCBUPD.COMM_DATA.PTYP;
        SCRPRMT.KEY.CD = SMCBUPD.COMM_DATA.CODE;
        SCCPRMM.EFF_DT = SMCBUPD.COMM_DATA.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;

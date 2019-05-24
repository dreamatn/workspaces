package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class BPZIQCIT {
    boolean pgmRtn = false;
    String K_PARM_CITY = "CITY";
    String K_PGM_NAME = "BPZIQCIT";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRCITY BPRCITY = new BPRCITY();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCGWA SCCGWA;
    BPCIQCIT BPCIQCIT;
    public void MP(SCCGWA SCCGWA, BPCIQCIT BPCIQCIT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCIQCIT = BPCIQCIT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZIQCIT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCITY);
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCIQCIT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_CITY_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCIQCIT.INPUT_DAT.CNTY_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTY_CD_M_INPUT, BPCIQCIT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCIQCIT.INPUT_DAT.CITY_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CITY_CD_M_INPUT, BPCIQCIT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_CITY_INFO() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = K_PARM_CITY;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCIQCIT.INPUT_DAT);
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;

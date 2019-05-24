package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6102 {
    String K_DATA_CLEAN_MAINT = "BP-S-MAINT-CLND-INFO";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    char WS_FUNC = ' ';
    char WS_STOP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMCLN BPCSMCLN = new BPCSMCLN();
    SCCGWA SCCGWA;
    BPC6102 BPC6102;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6102 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC6102 = new BPC6102();
        IBS.init(SCCGWA, BPC6102);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC6102);
        IBS.init(SCCGWA, BPCSMCLN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMCLN);
        BPCSMCLN.BNK = BPC6102.BNK;
        BPCSMCLN.CLN_ID = BPC6102.CLN_ID;
        BPCSMCLN.CLN_RULE = BPC6102.CLN_RULE;
        BPCSMCLN.INFO.FUNC = 'Q';
        BPCSMCLN.OUTPUT_FLG = 'Y';
        S000_CALL_BPZSMCLN();
    }
    public void S000_CALL_BPZSMCLN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_DATA_CLEAN_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSMCLN;
        SCCCALL.ERR_FLDNO = BPC6102.CLN_ID;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

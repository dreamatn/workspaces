package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6100 {
    String K_DATA_CLEAN_MAINT = "BP-S-MAINT-CLND-INFO";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMCLN BPCSMCLN = new BPCSMCLN();
    SCCGWA SCCGWA;
    BPC6100 BPC6100;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC6100 = new BPC6100();
        IBS.init(SCCGWA, BPC6100);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC6100);
        IBS.init(SCCGWA, BPCSMCLN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B100-MAIN-PROCESS");
        IBS.init(SCCGWA, BPCSMCLN);
        BPCSMCLN.BNK = BPC6100.BNK;
        BPCSMCLN.CLN_ID = BPC6100.CLN_ID;
        BPCSMCLN.CLN_RULE = BPC6100.CLN_RULE;
        BPCSMCLN.INFO.FUNC = 'B';
        BPCSMCLN.OUTPUT_FLG = 'Y';
        S000_CALL_BPZSMCLN();
    }
    public void S000_CALL_BPZSMCLN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_DATA_CLEAN_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSMCLN;
        SCCCALL.ERR_FLDNO = BPC6100.CLN_ID;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

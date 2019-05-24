package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6106 {
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
    BPC6106 BPC6106;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6106 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC6106 = new BPC6106();
        IBS.init(SCCGWA, BPC6106);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC6106);
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
        BPCSMCLN.BNK = BPC6106.BNK;
        BPCSMCLN.CLN_ID = BPC6106.CLN_ID;
        BPCSMCLN.CLN_RULE = BPC6106.CLN_RULE;
        BPCSMCLN.DES = BPC6106.DES;
        BPCSMCLN.STS = BPC6106.STS;
        BPCSMCLN.CLN_FRY = BPC6106.CLN_FRY;
        BPCSMCLN.CLN_CYC = BPC6106.CLN_CYC;
        BPCSMCLN.RES_FRY = BPC6106.RES_FRY;
        BPCSMCLN.RES_CYC = BPC6106.RES_CYC;
        BPCSMCLN.RES_FLG = BPC6106.RES_FLG;
        BPCSMCLN.CLN_FLG = BPC6106.CLN_FLG;
        BPCSMCLN.CPN = BPC6106.CPN;
        BPCSMCLN.REMARK = BPC6106.REMARK;
        BPCSMCLN.UPT_DT = BPC6106.UPT_DT;
        BPCSMCLN.UPT_TLR = BPC6106.UPT_TLR;
        BPCSMCLN.UPT_BRH = BPC6106.UPT_BRH;
        BPCSMCLN.INFO.FUNC = 'D';
        BPCSMCLN.OUTPUT_FLG = 'Y';
        S000_CALL_BPZSMCLN();
    }
    public void S000_CALL_BPZSMCLN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_DATA_CLEAN_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSMCLN;
        SCCCALL.ERR_FLDNO = BPC6106.CLN_ID;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

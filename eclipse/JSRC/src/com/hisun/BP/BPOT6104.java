package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6104 {
    String JIBS_tmp_str[] = new String[10];
    String K_DATA_CLEAN_MAINT = "BP-S-MAINT-CLND-INFO";
    String K_CHECK_CPN_EXSIT = "BP-PARM-READ        ";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_PARM_TYPE = "CPN  ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short WK_7 = 7;
    short WK_10 = 10;
    short WK_31 = 31;
    short WK_50 = 50;
    short WK_99 = 99;
    BPOT6104_WS_ERR_MSG WS_ERR_MSG = new BPOT6104_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    String WS_CPN = " ";
    short WS_CPN_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
    char WS_FUNC = ' ';
    char WS_STOP_FLG = ' ';
    char WS_STS_FLG = ' ';
    char WS_SPLIT_FLG = ' ';
    char WS_CLN_FLG = ' ';
    char WS_CLN_FRY_FLG = ' ';
    char WS_RES_FRY_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRCPN BPRCPN = new BPRCPN();
    BPCSMCLN BPCSMCLN = new BPCSMCLN();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPC6104 BPC6104;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6104 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC6104 = new BPC6104();
        IBS.init(SCCGWA, BPC6104);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC6104);
        IBS.init(SCCGWA, BPCSMCLN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPC6104.BNK);
        CEP.TRC(SCCGWA, BPC6104.CLN_ID);
        CEP.TRC(SCCGWA, BPC6104.CLN_RULE);
        CEP.TRC(SCCGWA, BPC6104.STS);
        CEP.TRC(SCCGWA, BPC6104.CLN_FRY);
        CEP.TRC(SCCGWA, BPC6104.CLN_CYC);
        CEP.TRC(SCCGWA, BPC6104.RES_FRY);
        CEP.TRC(SCCGWA, BPC6104.RES_CYC);
        CEP.TRC(SCCGWA, BPC6104.RES_FLG);
        CEP.TRC(SCCGWA, BPC6104.CLN_FLG);
        WS_CLN_FRY_FLG = BPC6104.CLN_FRY;
        if ((WS_CLN_FRY_FLG != 'D' 
            && WS_CLN_FRY_FLG != 'W' 
            && WS_CLN_FRY_FLG != 'T' 
            && WS_CLN_FRY_FLG != 'M' 
            && WS_CLN_FRY_FLG != 'Q' 
            && WS_CLN_FRY_FLG != 'H' 
            && WS_CLN_FRY_FLG != 'Y' 
            && WS_CLN_FRY_FLG != 'S')) {
            CEP.TRC(SCCGWA, "001");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPC6104.CLN_CYC == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_CLN_FRY_FLG == 'W') {
            if (BPC6104.CLN_CYC > WK_7) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_CYC_OVER_SUND, WS_ERR_MSG);
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (WS_CLN_FRY_FLG == 'T') {
            if (BPC6104.CLN_CYC > WK_10) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_CYC_OVER_TEN, WS_ERR_MSG);
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (WS_CLN_FRY_FLG == 'M' 
            || WS_CLN_FRY_FLG == 'Q' 
            || WS_CLN_FRY_FLG == 'H') {
            if (BPC6104.CLN_CYC > WK_31) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_CYC_OVER_MONT, WS_ERR_MSG);
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (WS_CLN_FRY_FLG == 'S') {
            WS_DATE = BPC6104.CLN_CYC;
            R000_CHECK_DATE();
        }
        WS_RES_FRY_FLG = BPC6104.RES_FRY;
        if ((WS_RES_FRY_FLG != 'D' 
            && WS_RES_FRY_FLG != 'M' 
            && WS_RES_FRY_FLG != 'Y')) {
            CEP.TRC(SCCGWA, "003");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_RES_FRY_FLG == 'M') {
            if (BPC6104.RES_CYC > WK_99) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RES_CYC_OVER_99, WS_ERR_MSG);
                S000_ERR_MSG_PROC_CONTINUE();
            }
            CEP.TRC(SCCGWA, WS_SPLIT_FLG);
            WS_SPLIT_FLG = BPC6104.RES_FLG;
            if ((WS_SPLIT_FLG != 'Y' 
                && WS_SPLIT_FLG != 'N')) {
                CEP.TRC(SCCGWA, "004");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, WS_ERR_MSG);
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (BPC6104.RES_CYC == 0 
            && WS_RES_FRY_FLG != 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_RES_FRY_FLG == 'Y') {
            if (BPC6104.RES_CYC > WK_50) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RES_CYC_OVER_50, WS_ERR_MSG);
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_STS_FLG = BPC6104.STS;
        if ((WS_STS_FLG != 'Y' 
            && WS_STS_FLG != 'N')) {
            CEP.TRC(SCCGWA, "006");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_CLN_FLG = BPC6104.CLN_FLG;
        if ((WS_CLN_FLG != 'Y' 
            && WS_CLN_FLG != 'N')) {
            CEP.TRC(SCCGWA, "007");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_CLN_FLG == 'Y') {
            WS_CPN = BPC6104.CPN;
            R000_CHECK_CPN();
        }
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, WS_ERR_MSG);
        S000_ERR_MSG_PROC_LAST();
    }
    public void R000_GET_CLN_FLD_NO() throws IOException,SQLException,Exception {
    }
    public void R000_GET_RES_FLD_NO() throws IOException,SQLException,Exception {
    }
    public void R000_CHECK_CPN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPRCPN);
        BPRPRMT.KEY.CD = WS_CPN;
        CEP.TRC(SCCGWA, WS_CPN);
        BPRPRMT.KEY.TYP = K_PARM_TYPE;
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        CEP.TRC(SCCGWA, "1");
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TX_NOTFND, WS_ERR_MSG);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG.WS_ERR_AP = "SC";
            WS_ERR_MSG.WS_ERR_CODE = SCCCKDT.RC;
            WS_FLD_NO = WS_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMCLN);
        CEP.TRC(SCCGWA, BPC6104.BNK);
        BPCSMCLN.BNK = BPC6104.BNK;
        BPCSMCLN.CLN_ID = BPC6104.CLN_ID;
        BPCSMCLN.CLN_RULE = BPC6104.CLN_RULE;
        BPCSMCLN.DES = BPC6104.DES;
        BPCSMCLN.STS = BPC6104.STS;
        BPCSMCLN.CLN_FRY = BPC6104.CLN_FRY;
        BPCSMCLN.CLN_CYC = BPC6104.CLN_CYC;
        BPCSMCLN.RES_FRY = BPC6104.RES_FRY;
        BPCSMCLN.RES_CYC = BPC6104.RES_CYC;
        BPCSMCLN.RES_FLG = BPC6104.RES_FLG;
        BPCSMCLN.CLN_FLG = BPC6104.CLN_FLG;
        BPCSMCLN.CPN = BPC6104.CPN;
        BPCSMCLN.REMARK = BPC6104.REMARK;
        BPCSMCLN.INFO.FUNC = 'A';
        BPCSMCLN.OUTPUT_FLG = 'Y';
        S000_CALL_BPZSMCLN();
    }
    public void S000_CALL_BPZSMCLN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_DATA_CLEAN_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSMCLN;
        SCCCALL.ERR_FLDNO = BPC6104.CLN_ID;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CHECK_CPN_EXSIT, BPCPRMR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

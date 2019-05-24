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

public class BPZFSSVR {
    boolean pgmRtn = false;
    String CPN_U_MAINTAIN_FSVR = "BP-F-U-MAINTAIN-FSVR";
    String WS_ERR_MSG = " ";
    short WS_CNT1 = 0;
    char WS_TBL_SVR_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOSVRO BPCOSVRO = new BPCOSVRO();
    BPCOSVRB BPCOSVRB = new BPCOSVRB();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    BPCOFSVR BPCOSSVR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFSVR BPCOSSVR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSSVR = BPCOSSVR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSSVR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOSSVR.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSSVR.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSSVR.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSSVR.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSSVR.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUSVR();
        if (pgmRtn) return;
        S000_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUSVR();
        if (pgmRtn) return;
        S000_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUSVR();
        if (pgmRtn) return;
        S000_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUSVR();
        if (pgmRtn) return;
        S000_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        BPCOSSVR.FUNC = 'S';
        S000_CALL_BPZFUSVR();
        if (pgmRtn) return;
        S000_INITIALIZE_MPAG();
        if (pgmRtn) return;
        while (BPCOSSVR.FOUND_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCOSSVR);
            BPCOSSVR.FUNC = 'N';
            S000_CALL_BPZFUSVR();
            if (pgmRtn) return;
            if (BPCOSSVR.FOUND_FLG != 'N') {
                S000_WRITE_TS();
                if (pgmRtn) return;
            }
        }
        BPCOSSVR.FUNC = 'E';
        S000_CALL_BPZFUSVR();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_BPZFUSVR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_MAINTAIN_FSVR, BPCOSSVR);
    }
    public void S000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            R000_TRANS_DATA_OUTPUT_CN();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOSSVR.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOSVRO;
        SCCFMT.DATA_LEN = 1950;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_INITIALIZE_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 26;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        WS_TBL_SVR_FLAG = 'F';
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            R000_TRANS_DATA_BROWSE_CN();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOSVRB);
        SCCMPAG.DATA_LEN = 26;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUTPUT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOSVRO);
        BPCOSVRO.KEY.SVR_NO = BPCOSSVR.KEY.SVR_NO;
        BPCOSVRO.VAL.AUT_FLG = BPCOSSVR.VAL.AUT_FLG;
        BPCOSVRO.VAL.DRMCR_FLG = BPCOSSVR.VAL.DRMCR_FLG;
        BPCOSVRO.VAL.MATCH_FLG = BPCOSSVR.VAL.MATCH_FLG;
        BPCOSVRO.VAL.EFF_DATE = BPCOSSVR.VAL.EFF_DATE;
        BPCOSVRO.VAL.EXP_DATE = BPCOSSVR.VAL.EXP_DATE;
        CEP.TRC(SCCGWA, WS_TBL_SVR_FLAG);
        for (WS_CNT1 = 1; WS_CNT1 <= 20 
            && WS_TBL_SVR_FLAG != 'T'; WS_CNT1 += 1) {
            if (BPCOSSVR.VAL.DATA[WS_CNT1-1].FEE_CODE.trim().length() > 0) {
                BPCOSVRO.VAL.FEE_INFO[WS_CNT1-1].FEE_CODE = BPCOSSVR.VAL.DATA[WS_CNT1-1].FEE_CODE;
                BPCOSVRO.VAL.FEE_INFO[WS_CNT1-1].PROD_CODE = BPCOSSVR.VAL.DATA[WS_CNT1-1].PROD_CODE;
                BPCOSVRO.VAL.FEE_INFO[WS_CNT1-1].BVF_CODE = BPCOSSVR.VAL.DATA[WS_CNT1-1].BVF_CODE;
                BPCOSVRO.VAL.FEE_INFO[WS_CNT1-1].ATTR_VAL.BNK_FLG = BPCOSSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BNK_FLG;

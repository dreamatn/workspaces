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

public class BPZFSAGR {
    boolean pgmRtn = false;
    String CPN_U_MAINTAIN_FAGR = "BP-F-U-MAINTAIN-FAGR";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOAGRO BPCOAGRO = new BPCOAGRO();
    SCCGWA SCCGWA;
    BPCOFAGR BPCOSAGR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFAGR BPCOSAGR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSAGR = BPCOSAGR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSAGR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOSAGR.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSAGR.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSAGR.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSAGR.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSAGR.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        B011_CHECK_KEY_VALIDITY();
        if (pgmRtn) return;
        S000_CALL_BPZFUAGR();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        B021_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        S000_CALL_BPZFUAGR();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        B021_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        S000_CALL_BPZFUAGR();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        B011_CHECK_KEY_VALIDITY();
        if (pgmRtn) return;
        S000_CALL_BPZFUAGR();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B070_START_BROWSE();
        if (pgmRtn) return;
        B080_READ_NEXT();
        if (pgmRtn) return;
        while (BPCOSAGR.RETURN_INFOR != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R020_TRANS_DATA_MPAGE_OUTPUT();
            if (pgmRtn) return;
            B080_READ_NEXT();
            if (pgmRtn) return;
        }
        B090_END_BROWSE();
        if (pgmRtn) return;
    }
    public void B070_START_BROWSE() throws IOException,SQLException,Exception {
        BPCOSAGR.FUNC = 'B';
        BPCOSAGR.OPT = 'S';
        S000_CALL_BPZFUAGR();
        if (pgmRtn) return;
    }
    public void B080_READ_NEXT() throws IOException,SQLException,Exception {
        BPCOSAGR.FUNC = 'B';
        BPCOSAGR.OPT = 'R';
        S000_CALL_BPZFUAGR();
        if (pgmRtn) return;
    }
    public void B090_END_BROWSE() throws IOException,SQLException,Exception {
        BPCOSAGR.FUNC = 'B';
        BPCOSAGR.OPT = 'E';
        S000_CALL_BPZFUAGR();
        if (pgmRtn) return;
    }
    public void B011_CHECK_KEY_VALIDITY() throws IOException,SQLException,Exception {
        if (BPCOSAGR.KEY.CHG_AC.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHG_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        B011_CHECK_KEY_VALIDITY();
        if (pgmRtn) return;
        if ((BPCOSAGR.VAL.CYC_UNIT != '0') 
            && (BPCOSAGR.VAL.CYC_UNIT != '1') 
            && (BPCOSAGR.VAL.CYC_UNIT != '2') 
            && (BPCOSAGR.VAL.CYC_UNIT != '3')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CYC_UNIT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSAGR.VAL.CYC_NUM == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CYC_NUM_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCOSAGR.VAL.HOLIDAY_FLG != '0') 
            && (BPCOSAGR.VAL.HOLIDAY_FLG != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HOLIDAY_FLG_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAGRO);

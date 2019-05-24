package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSUOD {
    boolean pgmRtn = false;
    String K_MMO = "BP";
    String K_PGM_NAME = "BPZSUOD";
    String K_CALL_BPZRUOD = "BP-U-R-UOD";
    String K_CMP_CALL_BPZPCCAL = "BP-P-CHK-CAL-CODE   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCOUOD BPCORUOD = new BPCOUOD();
    BPCOUODO BPCOUODO = new BPCOUODO();
    SCCGWA SCCGWA;
    BPCOSUOD BPCOSUOD;
    BPCOUOD BPCOUOD;
    public void MP(SCCGWA SCCGWA, BPCOUOD BPCOUOD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUOD = BPCOUOD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAINTAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSUOD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCOSUOD = (BPCOSUOD) BPCOUOD.POINTER;
    }
    public void B000_MAINTAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCORUOD);
        if (BPCOUOD.FUNC == 'I') {
            BPCORUOD.FUNC = 'I';
            S000_QUERY_CHECK();
            if (pgmRtn) return;
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            S000_CALL_BPZRUOD();
            if (pgmRtn) return;
            S000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCOUOD.FUNC == 'A') {
            BPCORUOD.FUNC = 'A';
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            S000_CALL_BPZRUOD();
            if (pgmRtn) return;
            S000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCOUOD.FUNC == 'U') {
            BPCORUOD.FUNC = 'U';
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            S000_CALL_BPZRUOD();
            if (pgmRtn) return;
            S000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCOUOD.FUNC == 'D') {
            BPCORUOD.FUNC = 'D';
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            S000_CALL_BPZRUOD();
            if (pgmRtn) return;
            S000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCOUOD.FUNC == 'B') {
            BPCORUOD.FUNC = 'B';
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            T000_BROWSE_BPCOSUOD();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_BROWSE_BPCOSUOD() throws IOException,SQLException,Exception {
        BPCORUOD.BR = 'S';
        S000_CALL_BPZRUOD();
        if (pgmRtn) return;
        S000_INITIALIZE_MPAG();
        if (pgmRtn) return;
        while (BPCORUOD.FOUND_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCOSUOD);
            BPCORUOD.BR = 'N';
            S000_CALL_BPZRUOD();
            if (pgmRtn) return;
            if (BPCORUOD.FOUND_FLG != 'N') {
                S000_WRITE_TS();
                if (pgmRtn) return;
            }
        }
        BPCORUOD.BR = 'E';
        S000_CALL_BPZRUOD();
        if (pgmRtn) return;
    }
    public void S000_QUERY_CHECK() throws IOException,SQLException,Exception {
        if (BPCOSUOD.KEY.UOD_BR == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VBBR_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
    }
    public void S000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOUODO);
        BPCOUODO.KEY.UOD_BK = BPCOSUOD.KEY.UOD_BK;
        BPCOUODO.KEY.UOD_BR = BPCOSUOD.KEY.UOD_BR;
        BPCOUODO.VAL.EFF_DT = BPCOSUOD.VAL.EFF_DT;
        BPCOUODO.VAL.EXP_DT = BPCOSUOD.VAL.EXP_DT;
        WS_CNT = 1;
        while (WS_CNT <= 10) {
            BPCOUODO.VAL.UOD_AMT[WS_CNT-1].UOD_LMT = BPCOSUOD.VAL.UOD_AMT[WS_CNT-1].UOD_LMT;
            WS_CNT = (short) (WS_CNT + 1);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOUOD.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOUODO;
        SCCFMT.DATA_LEN = 211;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_INITIALIZE_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 211;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOUODO);
        BPCOUODO.KEY.UOD_BK = BPCOSUOD.KEY.UOD_BK;
        BPCOUODO.KEY.UOD_BR = BPCOSUOD.KEY.UOD_BR;
        BPCOUODO.VAL.EFF_DT = BPCOSUOD.VAL.EFF_DT;
        BPCOUODO.VAL.EXP_DT = BPCOSUOD.VAL.EXP_DT;
        WS_CNT = 1;
        while (WS_CNT <= 10) {
            BPCOUODO.VAL.UOD_AMT[WS_CNT-1].UOD_LMT = BPCOSUOD.VAL.UOD_AMT[WS_CNT-1].UOD_LMT;
            WS_CNT = (short) (WS_CNT + 1);
        }
        BPCOUODO.UPT_DT = BPCOSUOD.UPT_DT;
        BPCOUODO.UPT_TLR = BPCOSUOD.UPT_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOUODO);
        SCCMPAG.DATA_LEN = 211;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRUOD() throws IOException,SQLException,Exception {
        BPCORUOD.POINTER = BPCOSUOD;
        IBS.CALLCPN(SCCGWA, K_CALL_BPZRUOD, BPCORUOD);
        if (BPCORUOD.RC.RC_CODE == 0) {
            BPCOUOD.RC.RC_CODE = 0;
        } else {

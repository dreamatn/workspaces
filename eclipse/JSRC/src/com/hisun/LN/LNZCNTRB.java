package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZCNTRB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WK_REC_CNT = 20;
    short WK_I = 0;
    int WS_KEY_LEN = 0;
    LNZCNTRB_WS_CNTRO_KEY WS_CNTRO_KEY = new LNZCNTRB_WS_CNTRO_KEY();
    LNZCNTRB_WS_CNTRN_KEY WS_CNTRN_KEY = new LNZCNTRB_WS_CNTRN_KEY();
    String WK_TIMES = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNRCNTR LNRCNTR = new LNRCNTR();
    SCCGWA SCCGWA;
    LNCCNTRB LNCCNTRB;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, LNCCNTRB LNCCNTRB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCCNTRB = LNCCNTRB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZCNTRB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPRMB);
        IBS.init(SCCGWA, BPRPRMT);
        LNCCNTRB.RC.RC_APP = "LN";
        LNCCNTRB.RC.RC_RTNCODE = 0;
        BPCPRMB.DAT_PTR = BPRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCCNTRB.PTYP);
        CEP.TRC(SCCGWA, LNCCNTRB.CODE);
        WS_KEY_LEN = 9;
        if (LNCCNTRB.CODE == null) LNCCNTRB.CODE = "";
        JIBS_tmp_int = LNCCNTRB.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) LNCCNTRB.CODE += " ";
        IBS.CPY2CLS(SCCGWA, LNCCNTRB.CODE.substring(0, WS_KEY_LEN), WS_CNTRO_KEY);
        WK_I = 0;
        S001_STARTBR_PARM();
        if (pgmRtn) return;
        S002_READNEXT_PARM();
        if (pgmRtn) return;
        while (BPCPRMB.RC.RC_RTNCODE == 0 
            && WK_I < WK_REC_CNT) {
            if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
            JIBS_tmp_int = BPRPRMT.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
            IBS.CPY2CLS(SCCGWA, BPRPRMT.KEY.CD.substring(0, WS_KEY_LEN), WS_CNTRN_KEY);
            if (BPRPRMT.KEY.TYP.equalsIgnoreCase(LNCCNTRB.PTYP) 
                && WS_CNTRN_KEY.WS_CNTRN_CODE.equalsIgnoreCase(WS_CNTRO_KEY.WS_CNTRO_CODE)) {
                WK_I += 1;
                CEP.TRC(SCCGWA, WK_I);
                CEP.TRC(SCCGWA, WS_CNTRN_KEY);
                B210_CNTR_PROCESS();
                if (pgmRtn) return;
            }
            S002_READNEXT_PARM();
            if (pgmRtn) return;
        }
        S003_ENDBR_PARM();
        if (pgmRtn) return;
        LNCCNTRB.REC_CNT = WK_I;
        CEP.TRC(SCCGWA, LNCCNTRB.REC_CNT);
        if (LNCCNTRB.REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CNTR_NOTFND, LNCCNTRB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_CNTR_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNTRB.REC_DATAS.REC_TXT[WK_I-1]);
        CEP.TRC(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[WK_I-1].KEY);
        CEP.TRC(SCCGWA, LNCCNTRB.REC_DATAS.REC_TXT[WK_I-1].DATA_TXT);
    }
    public void S001_STARTBR_PARM() throws IOException,SQLException,Exception {
        BPCPRMB.FUNC = '0';
        BPRPRMT.KEY.TYP = LNCCNTRB.PTYP;
        BPRPRMT.KEY.CD = LNCCNTRB.CODE;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
    }
    public void S002_READNEXT_PARM() throws IOException,SQLException,Exception {
        BPCPRMB.FUNC = '1';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
    }
    public void S003_ENDBR_PARM() throws IOException,SQLException,Exception {
        BPCPRMB.FUNC = '2';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-BROWSE", BPCPRMB);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCCNTRB.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCCNTRB=");
            CEP.TRC(SCCGWA, LNCCNTRB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZIRULB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WK_I = 0;
    int WS_KEY_LEN = 0;
    LNZIRULB_WS_IRULO_KEY WS_IRULO_KEY = new LNZIRULB_WS_IRULO_KEY();
    LNZIRULB_WS_IRULN_KEY WS_IRULN_KEY = new LNZIRULB_WS_IRULN_KEY();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNRIRUL LNRIRUL = new LNRIRUL();
    SCCGWA SCCGWA;
    LNCIRULB LNCIRULB;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, LNCIRULB LNCIRULB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIRULB = LNCIRULB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZIRULB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPRMB);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMB.DAT_PTR = BPRPRMT;
        LNCIRULB.RC.RC_APP = "LN";
        LNCIRULB.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCIRULB.PTYP);
        CEP.TRC(SCCGWA, LNCIRULB.CODE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
        B300_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_KEY_LEN = 15;
        if (LNCIRULB.CODE == null) LNCIRULB.CODE = "";
        JIBS_tmp_int = LNCIRULB.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) LNCIRULB.CODE += " ";
        IBS.CPY2CLS(SCCGWA, LNCIRULB.CODE.substring(0, WS_KEY_LEN), WS_IRULO_KEY);
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
            IBS.CPY2CLS(SCCGWA, BPRPRMT.KEY.CD.substring(0, WS_KEY_LEN), WS_IRULN_KEY);
            if (BPRPRMT.KEY.TYP.equalsIgnoreCase(LNCIRULB.PTYP) 
                && WS_IRULN_KEY.WS_IRULN_CODE.equalsIgnoreCase(WS_IRULO_KEY.WS_IRULO_CODE) 
                && WS_IRULN_KEY.WS_IRULN_TYPE == WS_IRULO_KEY.WS_IRULO_TYPE) {
                WK_I += 1;
                B210_IRUL_PROCESS();
                if (pgmRtn) return;
            }
            S002_READNEXT_PARM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPRMB.RC.RC_RTNCODE);
        S003_ENDBR_PARM();
        if (pgmRtn) return;
        LNCIRULB.REC_CNT = WK_I;
        CEP.TRC(SCCGWA, LNCIRULB.REC_CNT);
        if (LNCIRULB.REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_IRUL_NOTFND, LNCIRULB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_IRUL_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCIRULB.REC_DATAS.REC_TXT[WK_I-1]);
    }
    public void S001_STARTBR_PARM() throws IOException,SQLException,Exception {
        BPCPRMB.FUNC = '0';
        BPRPRMT.KEY.TYP = LNCIRULB.PTYP;
        BPRPRMT.KEY.CD = LNCIRULB.CODE;
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
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
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        for (WK_I = 1; WK_I <= LNCIRULB.REC_CNT; WK_I += 1) {
            CEP.TRC(SCCGWA, LNCIRULB.REC_DATAS.REC_TXT[WK_I-1]);
        }
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-BROWSE", BPCPRMB);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCIRULB.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCIRULB=");
            CEP.TRC(SCCGWA, LNCIRULB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

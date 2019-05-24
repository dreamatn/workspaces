package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPRDCHK;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class SMZSDCHK {
    boolean pgmRtn = false;
    short K_ITM_CNT = 48;
    short K_Q_MAX_CNT = 5000;
    String K_NEXT_TXN_FMT = "SMX01";
    String CPN_DCHK_MAINTAIN = "SM-R_DCHK_MAINTAIN  ";
    String CPN_DCHK_BROWSE = "SM-U_DCHK_BROWSE    ";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    char WS_END_FLG = ' ';
    short WS_LEN = 0;
    short WS_TOT_NUM = 0;
    short WS_TS_CNT = 0;
    String WS_TS_REC = " ";
    SMZSDCHK_WS_TS_HEAD WS_TS_HEAD = new SMZSDCHK_WS_TS_HEAD();
    SMZSDCHK_WS_TMP_TEXT WS_TMP_TEXT = new SMZSDCHK_WS_TMP_TEXT();
    SMZSDCHK_WS_ITM_TEXT WS_ITM_TEXT = new SMZSDCHK_WS_ITM_TEXT();
    char WS_TBL_BANK_FLAG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SMCX1364 SMCX1364 = new SMCX1364();
    SMCTCHKM SMCTCHKM = new SMCTCHKM();
    SMCTCHKB SMCTCHKB = new SMCTCHKB();
    SMCOCHKO SMCOCHKO = new SMCOCHKO();
    BPRDCHK BPRDCHK = new BPRDCHK();
    SCCGWA SCCGWA;
    SMCOCHKM SMCOCHKM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, SMCOCHKM SMCOCHKM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCOCHKM = SMCOCHKM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZSDCHK return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRDCHK);
        IBS.init(SCCGWA, SMCTCHKM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SMCOCHKM.FUNC == 'I') {
            B010_INQUIRE_PROCESS();
            if (pgmRtn) return;
            R020_TRANS_DATA_INTERFACE();
            if (pgmRtn) return;
        } else if (SMCOCHKM.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            R100_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (SMCOCHKM.FUNC == 'U') {
            B030_UPDATE_PROCESS();
            if (pgmRtn) return;
            R100_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (SMCOCHKM.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            R100_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (SMCOCHKM.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, SMCOCHKM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B090_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_INQUIRE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDCHK);
        BPRDCHK.KEY.NAME = SMCOCHKM.NAME;
        BPRDCHK.KEY.FMT = SMCOCHKM.FMTNO;
        SMCTCHKM.INFO.FUNC = 'Q';
        SMCTCHKM.INFO.POINTER = BPRDCHK;
        SMCTCHKM.INFO.LEN = 122;
        S000_CALL_SMZTCHKM();
        if (pgmRtn) return;
        if (SMCTCHKM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_DCHK_NOTFND, SMCOCHKM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPRDCHK.KEY.NAME);
        CEP.TRC(SCCGWA, SMCOCHKM.FMTNO);
        CEP.TRC(SCCGWA, BPRDCHK.OPT);
        CEP.TRC(SCCGWA, BPRDCHK.RMK);
        CEP.TRC(SCCGWA, BPRDCHK.CRE_ID);
        CEP.TRC(SCCGWA, BPRDCHK.CRE_DATE);
        CEP.TRC(SCCGWA, BPRDCHK.UPD_DATE);

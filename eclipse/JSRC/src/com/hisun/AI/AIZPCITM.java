package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPGDIN;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCQBKPM;
import com.hisun.BP.BPRVCHT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class AIZPCITM {
    boolean pgmRtn = false;
    String DD_U_CLEAR_CCY = "DD-U-CLEAR-CCY      ";
    String K_CPY_AIRITM = "AIRITM";
    String K_CPY_AIRMSTT = "AIRMSTT";
    String AI_HIS_ITMRMKS = "COA NO MAINTAIN HISTORY";
    String AI_HIS_MSTRMKS = "GL AC  MAINTAIN HISTORY";
    short WS_INDEX = 0;
    short WS_CNT = 0;
    String WS_ERR_MSG = " ";
    String WS_ITM_COA_FLG = " ";
    String WS_ITM_NO = " ";
    short WS_INT_X = 0;
    String WK_ITM_COA_FLG = " ";
    String WK_ITM_NO = " ";
    String WK_MSTT_ITM_NO = " ";
    AIZPCITM_WS_MSTT_VAR WS_MSTT_VAR = new AIZPCITM_WS_MSTT_VAR();
    char WS_AIRITM_FLG = ' ';
    char WS_AIRFVCH_FLG = ' ';
    char WS_BPRVCHT_FLG = ' ';
    char WS_AIRITAD_FLG = ' ';
    char WS_AIRMSTT_FLG = ' ';
    char WS_REWRITE_AITMSTT_FLG = ' ';
    char WS_AITMSTT_BAL_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICITAD1 AICITAD1 = new AICITAD1();
    AIRITAD AIRITAD = new AIRITAD();
    AIRFVCH AIRFVCH = new AIRFVCH();
    BPRVCHT BPRVCHT = new BPRVCHT();
    AIRITM AIRITM = new AIRITM();
    AIRITM AIROITM = new AIRITM();
    AIRITM AIRNITM = new AIRITM();
    AIRMSTT AIRMSTT = new AIRMSTT();
    AIRMSTT AIROMSTT = new AIRMSTT();
    AIRMSTT AIRNMSTT = new AIRMSTT();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICPCITM AICCITM;
    public void MP(SCCGWA SCCGWA, AICPCITM AICCITM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICCITM = AICCITM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPCITM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICCITM.INPUT_DATA.FUNC);
        if (AICCITM.INPUT_DATA.FUNC == 'C') {
            B020_CLOSE_COA_AND_GLAC();
            if (pgmRtn) return;
        } else {
            B020_REOPEN_COA_AND_GLAC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICCITM.INPUT_DATA.BOOK_FLG);
        CEP.TRC(SCCGWA, AICCITM.INPUT_DATA.COA_FLG);
        CEP.TRC(SCCGWA, AICCITM.INPUT_DATA.COA_NO);
        CEP.TRC(SCCGWA, AICCITM.INPUT_DATA.FUNC);
        if (AICCITM.INPUT_DATA.BOOK_FLG.trim().length() == 0 
            && AICCITM.INPUT_DATA.COA_FLG.trim().length() == 0) {

package com.hisun.TD;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGCPT;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class TDZGEDT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_MMO = "TD";
    String WS_PGDIN_CCY = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    TDCOGEDT TDCOGEDT;
    public void MP(SCCGWA SCCGWA, TDCOGEDT TDCOGEDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCOGEDT = TDCOGEDT;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZGEDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        TDCOGEDT.RC.RC_MMO = K_MMO;
        TDCOGEDT.RC.RC_CODE = 0;
    }
    public void B00_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCOGEDT.GET_DATE_FLG);
        CEP.TRC(SCCGWA, TDCOGEDT.CCY);
        B01_CHECK_INPUT();
        if (pgmRtn) return;
        if (TDCOGEDT.CCY.trim().length() == 0) {
            WS_PGDIN_CCY = BPCRBANK.LOC_CCY1;
        } else {
            WS_PGDIN_CCY = TDCOGEDT.CCY;
        }
        if (TDCOGEDT.GET_DATE_FLG == '1') {
            R00_GET_PREV_NOR_DATE();
            if (pgmRtn) return;
        } else if (TDCOGEDT.GET_DATE_FLG == '2') {
            R00_GET_NEXT_NOR_DATE();
            if (pgmRtn) return;
        } else if (TDCOGEDT.GET_DATE_FLG == '3') {
            R00_GET_ONL_PREV_ACDT();
            if (pgmRtn) return;
        } else if (TDCOGEDT.GET_DATE_FLG == '4') {
            R00_GET_ONL_NEXT_ACDT();
            if (pgmRtn) return;
        } else if (TDCOGEDT.GET_DATE_FLG == '5') {
            R00_GET_BAT_PREV_ACDT();
            if (pgmRtn) return;
        } else if (TDCOGEDT.GET_DATE_FLG == '6') {
            R00_GET_BAT_NEXT_ACDT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_OPT, TDCOGEDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK_01");
        if (TDCOGEDT.GET_DATE_FLG == ' ') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_OPT, TDCOGEDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK_02");
        if (TDCOGEDT.INPUT_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT, TDCOGEDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_GET_PREV_NOR_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPGDIN);
        BPCPGDIN.INPUT_DATA.FUNC = '2';
        BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
        BPCPGDIN.INPUT_DATA.CCY = WS_PGDIN_CCY;
        BPCPGDIN.INPUT_DATA.DATE_1 = TDCOGEDT.INPUT_DATE;
        BPCPGDIN.INPUT_DATA.DAYS = -2;
        S00_CALL_BPZPGDIN();
        if (pgmRtn) return;
        TDCOGEDT.OUTPUT_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
    }
    public void R00_GET_ONL_PREV_ACDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPGDIN);
        BPCPGDIN.INPUT_DATA.FUNC = '2';
        BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
        BPCPGDIN.INPUT_DATA.CCY = WS_PGDIN_CCY;
        BPCPGDIN.INPUT_DATA.DATE_1 = TDCOGEDT.INPUT_DATE;
        BPCPGDIN.INPUT_DATA.WDAYS = -1;
        CEP.TRC(SCCGWA, "TEST-------------------");
        CEP.TRC(SCCGWA, WS_PGDIN_CCY);
        CEP.TRC(SCCGWA, TDCOGEDT.INPUT_DATE);
        CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.WDAYS);
        S00_CALL_BPZPGDIN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE_2);
        CEP.TRC(SCCGWA, TDCOGEDT.INPUT_DATE);
        if (BPCPGDIN.OUTPUT_DATA.DATE_2 == TDCOGEDT.INPUT_DATE) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.FUNC = '2';
            BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
            BPCPGDIN.INPUT_DATA.CCY = WS_PGDIN_CCY;
            BPCPGDIN.INPUT_DATA.DATE_1 = TDCOGEDT.INPUT_DATE;
            BPCPGDIN.INPUT_DATA.WDAYS = -2;
            S00_CALL_BPZPGDIN();
            if (pgmRtn) return;
        }
        TDCOGEDT.OUTPUT_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
    }
    public void R00_GET_NEXT_NOR_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPGDIN);
        BPCPGDIN.INPUT_DATA.FUNC = '2';
        BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
        BPCPGDIN.INPUT_DATA.CCY = WS_PGDIN_CCY;
        BPCPGDIN.INPUT_DATA.DATE_1 = TDCOGEDT.INPUT_DATE;
        BPCPGDIN.INPUT_DATA.DAYS = 2;
        S00_CALL_BPZPGDIN();
        if (pgmRtn) return;
        TDCOGEDT.OUTPUT_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
    }
    public void R00_GET_ONL_NEXT_ACDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPGDIN);
        BPCPGDIN.INPUT_DATA.FUNC = '2';
        BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
        BPCPGDIN.INPUT_DATA.CCY = WS_PGDIN_CCY;
        BPCPGDIN.INPUT_DATA.DATE_1 = TDCOGEDT.INPUT_DATE;
        BPCPGDIN.INPUT_DATA.WDAYS = 1;
        S00_CALL_BPZPGDIN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE_2);
        CEP.TRC(SCCGWA, TDCOGEDT.INPUT_DATE);
        if (BPCPGDIN.OUTPUT_DATA.DATE_2 == TDCOGEDT.INPUT_DATE) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.FUNC = '2';
            BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
            BPCPGDIN.INPUT_DATA.CCY = WS_PGDIN_CCY;
            BPCPGDIN.INPUT_DATA.DATE_1 = TDCOGEDT.INPUT_DATE;
            BPCPGDIN.INPUT_DATA.WDAYS = 2;
            S00_CALL_BPZPGDIN();
            if (pgmRtn) return;
        }
        TDCOGEDT.OUTPUT_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
    }
    public void R00_GET_BAT_PREV_ACDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPGDIN);
        BPCPGDIN.INPUT_DATA.FUNC = '2';
        BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
        BPCPGDIN.INPUT_DATA.CCY = WS_PGDIN_CCY;
        BPCPGDIN.INPUT_DATA.DATE_1 = TDCOGEDT.INPUT_DATE;
        BPCPGDIN.INPUT_DATA.WDAYS = -1;
        S00_CALL_BPZPGDIN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE_2);
        CEP.TRC(SCCGWA, TDCOGEDT.INPUT_DATE);
        if (BPCPGDIN.OUTPUT_DATA.DATE_2 == TDCOGEDT.INPUT_DATE) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.FUNC = '2';
            BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
            BPCPGDIN.INPUT_DATA.CCY = WS_PGDIN_CCY;
            BPCPGDIN.INPUT_DATA.DATE_1 = TDCOGEDT.INPUT_DATE;
            BPCPGDIN.INPUT_DATA.WDAYS = -2;
            S00_CALL_BPZPGDIN();
            if (pgmRtn) return;
        }
        TDCOGEDT.OUTPUT_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
    }
    public void R00_GET_BAT_NEXT_ACDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPGDIN);
        BPCPGDIN.INPUT_DATA.FUNC = '2';
        BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
        BPCPGDIN.INPUT_DATA.CCY = WS_PGDIN_CCY;
        BPCPGDIN.INPUT_DATA.DATE_1 = TDCOGEDT.INPUT_DATE;
        BPCPGDIN.INPUT_DATA.WDAYS = 1;
        S00_CALL_BPZPGDIN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE_2);
        CEP.TRC(SCCGWA, TDCOGEDT.INPUT_DATE);
        if (BPCPGDIN.OUTPUT_DATA.DATE_2 == TDCOGEDT.INPUT_DATE) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.FUNC = '2';
            BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
            BPCPGDIN.INPUT_DATA.CCY = WS_PGDIN_CCY;
            BPCPGDIN.INPUT_DATA.DATE_1 = TDCOGEDT.INPUT_DATE;
            BPCPGDIN.INPUT_DATA.WDAYS = 2;
            S00_CALL_BPZPGDIN();
            if (pgmRtn) return;
        }
        TDCOGEDT.OUTPUT_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
    }
    public void S00_CALL_BPZPGDIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-GET-DT-INFO", BPCPGDIN);
        if (BPCPGDIN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPGDIN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCOGEDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (TDCOGEDT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "TDZGEDT=");
            CEP.TRC(SCCGWA, TDCOGEDT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

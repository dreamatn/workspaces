package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZUIANO {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    AIZUIANO_WS_IA_NO WS_IA_NO = new AIZUIANO_WS_IA_NO();
    char WS_PROC_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICRMIB AICRMIB = new AICRMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AIRMIB AIRMIB = new AIRMIB();
    BPCQCCY BPCQCCY = new BPCQCCY();
    AIRCMIB AIRCMIB = new AIRCMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    AICUIANO AICUIANO;
    public void MP(SCCGWA SCCGWA, AICUIANO AICUIANO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICUIANO = AICUIANO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZUIANO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_MIB_INFO_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICUIANO.INPUT_DATA.GL_BOOK);
        if (AICUIANO.INPUT_DATA.GL_BOOK.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BOOK_FLG_MUST_INPUT, AICUIANO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "xixi");
        CEP.TRC(SCCGWA, AICUIANO.INPUT_DATA.AC);
        CEP.TRC(SCCGWA, AICUIANO.INPUT_DATA.CCY);
        CEP.TRC(SCCGWA, AICUIANO.INPUT_DATA.BR);
        CEP.TRC(SCCGWA, AICUIANO.INPUT_DATA.ITM);
        CEP.TRC(SCCGWA, AICUIANO.INPUT_DATA.SEQ);
        if (AICUIANO.INPUT_DATA.AC.trim().length() == 0 
            && (AICUIANO.INPUT_DATA.BR == 0 
            || AICUIANO.INPUT_DATA.ITM.equalsIgnoreCase("0") 
            || AICUIANO.INPUT_DATA.SEQ == 0 
            || AICUIANO.INPUT_DATA.CCY.trim().length() == 0)) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICUIANO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (AICUIANO.INPUT_DATA.CCY.trim().length() == 0 
            && AICUIANO.INPUT_DATA.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_CCY_MUST_INPUT, AICUIANO.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (AICUIANO.INPUT_DATA.CCY.trim().length() == 0) {
                if (AICUIANO.INPUT_DATA.AC == null) AICUIANO.INPUT_DATA.AC = "";
                JIBS_tmp_int = AICUIANO.INPUT_DATA.AC.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) AICUIANO.INPUT_DATA.AC += " ";
                IBS.CPY2CLS(SCCGWA, AICUIANO.INPUT_DATA.AC.substring(0, 25), WS_IA_NO);
                AICUIANO.INPUT_DATA.CCY = WS_IA_NO.WS_CCY;
                CEP.TRC(SCCGWA, AICUIANO.INPUT_DATA.CCY);
            }
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = AICUIANO.INPUT_DATA.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        if (AICUIANO.INPUT_DATA.AC.trim().length() == 0) {
            WS_PROC_FLG = 'G';
        } else {
            WS_PROC_FLG = 'C';
        }
        CEP.TRC(SCCGWA, "HAHA");
        CEP.TRC(SCCGWA, WS_PROC_FLG);
    }
    public void B020_INQ_MIB_INFO_PROCESS() throws IOException,SQLException,Exception {
        if (WS_PROC_FLG == 'G') {
            R000_GEN_ACNO();
            if (pgmRtn) return;
        } else {
            R000_GET_ACNO();
            if (pgmRtn) return;
        }
    }
    public void R000_GEN_ACNO() throws IOException,SQLException,Exception {
        WS_IA_NO.WS_BR = AICUIANO.INPUT_DATA.BR;
        WS_IA_NO.WS_CCY = AICUIANO.INPUT_DATA.CCY;
        WS_IA_NO.WS_ITM = AICUIANO.INPUT_DATA.ITM;
        WS_IA_NO.WS_SEQ = AICUIANO.INPUT_DATA.SEQ;
        CEP.TRC(SCCGWA, WS_IA_NO.WS_BR);
        CEP.TRC(SCCGWA, WS_IA_NO.WS_CCY);
        CEP.TRC(SCCGWA, WS_IA_NO.WS_ITM);
        CEP.TRC(SCCGWA, WS_IA_NO.WS_SEQ);
        IBS.init(SCCGWA, AIRMIB);
        IBS.init(SCCGWA, AICRMIB);
        AICRMIB.FUNC = 'Q';
        AIRMIB.KEY.GL_BOOK = AICUIANO.INPUT_DATA.GL_BOOK;
        AIRMIB.KEY.BR = AICUIANO.INPUT_DATA.BR;
        AIRMIB.KEY.ITM_NO = AICUIANO.INPUT_DATA.ITM;
        AIRMIB.KEY.SEQ = AICUIANO.INPUT_DATA.SEQ;
        AIRMIB.KEY.CCY = AICUIANO.INPUT_DATA.CCY;
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        if (AICRMIB.RETURN_INFO == 'F') {
            AICUIANO.INPUT_DATA.EXIST_FLG = 'Y';
            AICUIANO.INPUT_DATA.AC = AIRMIB.AC_NO;
        } else {
            AICUIANO.INPUT_DATA.EXIST_FLG = 'N';
            AICUIANO.INPUT_DATA.AC = IBS.CLS2CPY(SCCGWA, WS_IA_NO);
            CEP.TRC(SCCGWA, AICUIANO.INPUT_DATA.AC);
        }
    }
    public void R000_GET_ACNO() throws IOException,SQLException,Exception {
        if (AICUIANO.INPUT_DATA.AC == null) AICUIANO.INPUT_DATA.AC = "";
        JIBS_tmp_int = AICUIANO.INPUT_DATA.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AICUIANO.INPUT_DATA.AC += " ";
        IBS.CPY2CLS(SCCGWA, AICUIANO.INPUT_DATA.AC.substring(0, 25), WS_IA_NO);
        CEP.TRC(SCCGWA, WS_IA_NO);
        CEP.TRC(SCCGWA, WS_IA_NO.WS_BR);
        CEP.TRC(SCCGWA, WS_IA_NO.WS_CCY);
        CEP.TRC(SCCGWA, WS_IA_NO.WS_ITM);
        CEP.TRC(SCCGWA, WS_IA_NO.WS_SEQ);
        IBS.init(SCCGWA, AIRMIB);
        IBS.init(SCCGWA, AICRMIB);
        AICRMIB.FUNC = 'Q';
        AICRMIB.FUNC = 'B';
        AICRMIB.OPT = 'F';
        AIRMIB.KEY.GL_BOOK = AICUIANO.INPUT_DATA.GL_BOOK;
        AIRMIB.AC_NO = AICUIANO.INPUT_DATA.AC;
        AIRMIB.KEY.CCY = WS_IA_NO.WS_CCY;
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        if (AICRMIB.RETURN_INFO == 'F') {
            AICUIANO.INPUT_DATA.EXIST_FLG = 'Y';
            AICUIANO.INPUT_DATA.BR = AIRMIB.KEY.BR;
            AICUIANO.INPUT_DATA.ITM = AIRMIB.KEY.ITM_NO;
            AICUIANO.INPUT_DATA.SEQ = AIRMIB.KEY.SEQ;
            AICUIANO.INPUT_DATA.CCY = AIRMIB.KEY.CCY;
        } else {
            AICUIANO.INPUT_DATA.EXIST_FLG = 'N';
            AICUIANO.INPUT_DATA.BR = WS_IA_NO.WS_BR;
            AICUIANO.INPUT_DATA.ITM = WS_IA_NO.WS_ITM;
            AICUIANO.INPUT_DATA.SEQ = WS_IA_NO.WS_SEQ;
            AICUIANO.INPUT_DATA.CCY = WS_IA_NO.WS_CCY;
        }
    }
    public void S000_CALL_AIZRMIB() throws IOException,SQLException,Exception {
        AICRMIB.POINTER = AIRMIB;
        AICRMIB.REC_LEN = 634;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIB", AICRMIB);
        CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
    }
    public void S000_CALL_AIZRCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-CMIB", AICRCMIB);
        if (AICRMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICRCMIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICUIANO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICUIANO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICUIANO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICUIANO = ");
            CEP.TRC(SCCGWA, AICUIANO);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

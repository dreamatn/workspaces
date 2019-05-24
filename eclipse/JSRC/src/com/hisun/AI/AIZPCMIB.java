package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPCMIB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String TBL_AITCMIB = "AITCMIB";
    int WS_CNT = 0;
    AIZPCMIB_WS_CMIB_KEY WS_CMIB_KEY = new AIZPCMIB_WS_CMIB_KEY();
    String WS_TX_CD = " ";
    char WS_FND_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AIRCMIB AIRXMIB = new AIRCMIB();
    BPCPORUP BPCPORUP = new BPCPORUP();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICPCMIB AICPCMIB;
    AIRCMIB AIRLCMIB;
    public void MP(SCCGWA SCCGWA, AICPCMIB AICPCMIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICPCMIB = AICPCMIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPCMIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, AICPCMIB.RC);
        AIRLCMIB = (AIRCMIB) AICPCMIB.POINTER;
        IBS.init(SCCGWA, AIRCMIB);
        AICPCMIB.REC_LEN = 407;
        IBS.CLONE(SCCGWA, AIRLCMIB, AIRCMIB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        B020_GET_CMIB();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, AIRCMIB, AIRLCMIB);
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        WS_CMIB_KEY.WS_GL_BOOK = AIRCMIB.KEY.GL_BOOK;
        WS_CMIB_KEY.WS_BR = AIRCMIB.KEY.BR;
        WS_CMIB_KEY.WS_ITM = AIRCMIB.KEY.ITM;
        WS_CMIB_KEY.WS_SEQ = AIRCMIB.KEY.SEQ;
        CEP.TRC(SCCGWA, WS_CMIB_KEY.WS_GL_BOOK);
        CEP.TRC(SCCGWA, WS_CMIB_KEY.WS_BR);
        CEP.TRC(SCCGWA, WS_CMIB_KEY.WS_ITM);
        CEP.TRC(SCCGWA, WS_CMIB_KEY.WS_SEQ);
        if (WS_CMIB_KEY.WS_GL_BOOK.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BOOK_FLG_MUST_INPUT, AICPCMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_CMIB_KEY.WS_BR == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BR_MUST_INPUT, AICPCMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_CMIB_KEY.WS_ITM.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_ITMP_GL_ITEM_INPUT, AICPCMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CMIB() throws IOException,SQLException,Exception {
        WS_FND_FLG = 'N';
        IBS.init(SCCGWA, AICRCMIB);
        IBS.init(SCCGWA, AIRXMIB);
        CEP.TRC(SCCGWA, "INQ CMIB");
        AICRCMIB.FUNC = 'Q';
        AIRXMIB.KEY.GL_BOOK = WS_CMIB_KEY.WS_GL_BOOK;
        AIRXMIB.KEY.BR = WS_CMIB_KEY.WS_BR;
        AIRXMIB.KEY.ITM = WS_CMIB_KEY.WS_ITM;
        AIRXMIB.KEY.SEQ = WS_CMIB_KEY.WS_SEQ;
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRCMIB.RETURN_INFO);
        if (AICRCMIB.RETURN_INFO == 'N') {
            R000_GET_SUPR_BR();
            if (pgmRtn) return;
            if (BPCPORUP.DATA_INFO.CNT > 0) {
                WS_CNT = 1;
                while (WS_CNT <= BPCPORUP.DATA_INFO.CNT 
                    && WS_FND_FLG != 'Y') {
                    CEP.TRC(SCCGWA, WS_CNT);
                    CEP.TRC(SCCGWA, WS_CMIB_KEY.WS_GL_BOOK);
                    CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT-1].SUPR_BR);
                    CEP.TRC(SCCGWA, WS_CMIB_KEY.WS_ITM);
                    CEP.TRC(SCCGWA, WS_CMIB_KEY.WS_SEQ);
                    IBS.init(SCCGWA, AICRCMIB);
                    IBS.init(SCCGWA, AIRXMIB);
                    AICRCMIB.FUNC = 'Q';
                    AIRXMIB.KEY.GL_BOOK = WS_CMIB_KEY.WS_GL_BOOK;
                    AIRXMIB.KEY.BR = BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT-1].SUPR_BR;
                    AIRXMIB.KEY.ITM = WS_CMIB_KEY.WS_ITM;
                    AIRXMIB.KEY.SEQ = WS_CMIB_KEY.WS_SEQ;
                    S000_CALL_AIZRCMIB();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, AICRCMIB.RETURN_INFO);
                    if (AICRCMIB.RETURN_INFO == 'F') {
                        CEP.TRC(SCCGWA, "FOUND");
                        WS_FND_FLG = 'Y';
                    }
                    WS_CNT += 1;
                }
            }
            if (WS_FND_FLG == 'N') {
                IBS.init(SCCGWA, AICRCMIB);
                IBS.init(SCCGWA, AIRXMIB);
                CEP.TRC(SCCGWA, "INQ CMIB");
                AICRCMIB.FUNC = 'Q';
                AIRXMIB.KEY.GL_BOOK = WS_CMIB_KEY.WS_GL_BOOK;
                AIRXMIB.KEY.BR = 999999;
                AIRXMIB.KEY.ITM = WS_CMIB_KEY.WS_ITM;
                AIRXMIB.KEY.SEQ = WS_CMIB_KEY.WS_SEQ;
                S000_CALL_AIZRCMIB();
                if (pgmRtn) return;
                if (AICRCMIB.RETURN_INFO == 'F') {
                    WS_FND_FLG = 'Y';
                }
            }
        } else {
            WS_FND_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_FND_FLG);
        if (WS_FND_FLG == 'Y') {
            AICPCMIB.RETURN_INFO = 'F';
            IBS.CLONE(SCCGWA, AIRXMIB, AIRCMIB);
        } else {
            AICPCMIB.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND, AICPCMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AIRCMIB);
    }
    public void R000_GET_SUPR_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_CMIB_KEY.WS_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE == 0) {
            IBS.init(SCCGWA, BPCPORUP);
            BPCPORUP.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORUP.DATA_INFO.BR = WS_CMIB_KEY.WS_BR;
            S000_CALL_BPZPORUP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.CNT);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
            BPCPORUP.DATA_INFO.CNT += 1;
            BPCPORUP.DATA_INFO.SUPR_GRP[BPCPORUP.DATA_INFO.CNT-1].SUPR_BR = 999999;
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.CNT);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICPCMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPORUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-SUPR-ORG", BPCPORUP);
    }
    public void S000_CALL_AIZRCMIB() throws IOException,SQLException,Exception {
        AICRCMIB.POINTER = AIRXMIB;
        AICRCMIB.REC_LEN = 407;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-CMIB", AICRCMIB);
        CEP.TRC(SCCGWA, AICRCMIB.RC.RC_CODE);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICPCMIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICPCMIB = ");
            CEP.TRC(SCCGWA, AICPCMIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

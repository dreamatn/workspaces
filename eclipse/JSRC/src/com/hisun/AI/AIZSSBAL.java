package com.hisun.AI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSSBAL {
    String WS_ERR_MSG = " ";
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AICRSBAL AICRSBAL = new AICRSBAL();
    AIRMSTT AIRMSTT = new AIRMSTT();
    SCCGWA SCCGWA;
    AICSSBAL AICSSBAL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA, AICSSBAL AICSSBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSSBAL = AICSSBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_TRAN_DATA();
        B100_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIZSSBAL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_TRAN_DATA() throws IOException,SQLException,Exception {
        AICRSBAL.COMM_DATA.BOOK_FLG = AICSSBAL.COMM_DATA.BOOK_FLG;
        AICRSBAL.COMM_DATA.ITM_NO = AICSSBAL.COMM_DATA.ITM_NO;
        AICRSBAL.COMM_DATA.BR_START = AICSSBAL.COMM_DATA.BR_START;
        AICRSBAL.COMM_DATA.BR_END = AICSSBAL.COMM_DATA.BR_END;
        AICRSBAL.COMM_DATA.CCY = AICSSBAL.COMM_DATA.CCY;
        CEP.TRC(SCCGWA, "ZST05------------------11");
    }
    public void B100_MAIN_PROC() throws IOException,SQLException,Exception {
        if (AICSSBAL.FUNC == 'I') {
            B070_INQ_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + AICSSBAL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B070_INQ_PROC() throws IOException,SQLException,Exception {
        AICRSBAL.FUNC = 'I';
        S000_CALL_AIZRSBAL();
    }
    public void S000_CALL_AIZRSBAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICRSBAL.RC.RC_CODE);
        AICRSBAL.REC_PTR = AIRMSTT;
        AICRSBAL.REC_LEN = 200;
        IBS.CALLCPN(SCCGWA, "AI-R-AIZR8530", AICRSBAL);
        CEP.TRC(SCCGWA, "AIZSSBAL----------------------CALL ZR");
        if (AICRSBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRSBAL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

package com.hisun.PY;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPRGST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class PYOT8200 {
    boolean pgmRtn = false;
    SCCMPAG SCCMPAG = new SCCMPAG();
    String WS_ERR_MSG = " ";
    PYOT8200_WS_OUT_DATA WS_OUT_DATA = new PYOT8200_WS_OUT_DATA();
    PYCMSG_ERROR_MSG PYCMSG_ERROR_MSG = new PYCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPRGST BPCPRGST = new BPCPRGST();
    PYRSQRV PYRSQRV = new PYRSQRV();
    PYCRSQRV PYCRSQRV = new PYCRSQRV();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    PYCI8200 PYCI8200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PYOT8200 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        PYCI8200 = new PYCI8200();
        IBS.init(SCCGWA, PYCI8200);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, PYCI8200);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MPAG_OUTPUT();
        if (pgmRtn) return;
        B030_QUREY_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (PYCI8200.ST_DT > PYCI8200.ED_DT) {
            WS_ERR_MSG = PYCMSG_ERROR_MSG.PY_ED_MUST_GT_ST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != PYCI8200.RES_BR) {
            IBS.init(SCCGWA, BPCPRGST);
            BPCPRGST.BR1 = PYCI8200.RES_BR;
            BPCPRGST.BR2 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPRGST();
            if (pgmRtn) return;
            if (BPCPRGST.LVL_RELATION != 'C') {
                CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
                WS_ERR_MSG = PYCMSG_ERROR_MSG.PY_ORG_NOT_COMM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_MPAG_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 0;
        SCCMPAG.MAX_COL_NO = 65;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_QUREY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PYRSQRV);
        IBS.init(SCCGWA, PYCRSQRV);
        if (PYCI8200.SR_FLG == '1') {
            PYRSQRV.RCV_BR = PYCI8200.RES_BR;
            PYRSQRV.FND_BR = PYCI8200.FND_BR;
        } else {
            PYRSQRV.FND_BR = PYCI8200.RES_BR;
        }
        PYRSQRV.KEY.FND_NO = PYCI8200.FND_NO;
        PYRSQRV.RCV_FLG = PYCI8200.FD_FLG;
        PYCRSQRV.INFO.ST_DT = PYCI8200.ST_DT;
        PYCRSQRV.INFO.ED_DT = PYCI8200.ED_DT;
        PYCRSQRV.INFO.LEN = 201;
        PYRSQRV.RCV_FLG = PYRSQRV;

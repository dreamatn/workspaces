package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQPDR {
    boolean pgmRtn = false;
    String CPN_P_BRW_TP_REL_PRD = "BP-P-BRW-TP-REL-PRD";
    String CPN_R_MAINT_PRD_TYPE = "BP-R-MAINT-PRD-TYPE";
    String CPN_R_BRW_PRD_TYPE = "BP-R-BRW-PRD-TYPE";
    int K_MAX_TYPE_CNT = 50;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_TP_CNT = 0;
    int WS_LOW_TYPE_CNT = 0;
    BPZPQPDR_WS_LOW_TYPE_INFO WS_LOW_TYPE_INFO = new BPZPQPDR_WS_LOW_TYPE_INFO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPDTP BPRPDTP = new BPRPDTP();
    BPCRMPDT BPCRMPDT = new BPCRMPDT();
    BPCRBPDT BPCRBPDT = new BPCRBPDT();
    SCCGWA SCCGWA;
    BPCPQPDR BPCPQPDR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPQPDR BPCPQPDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQPDR = BPCPQPDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQPDR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPDR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_SEARCH_BOTTOM_TYPE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPQPDR.PRDT_TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQPDR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_SEARCH_BOTTOM_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDTP);
        BPRPDTP.KEY.PRDT_TYPE = BPCPQPDR.PRDT_TYPE;
        IBS.init(SCCGWA, BPCRMPDT);
        BPCRMPDT.INFO.FUNC = 'Q';
        BPCRMPDT.INFO.POINTER = BPRPDTP;
        BPCRMPDT.INFO.LEN = 237;
        S000_CALL_BPZRMPDT();
        if (pgmRtn) return;
        if (BPCRMPDT.RC.RC_CODE != 0 
            || BPCRMPDT.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_TYPE_NOTFND, BPCPQPDR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRPDTP.BOTTOM_IND == 'Y') {
            BPCPQPDR.TYPE_CNT = BPCPQPDR.TYPE_CNT + 1;
            BPCPQPDR.BT_INFO.BT_TYPE[BPCPQPDR.TYPE_CNT-1] = BPCPQPDR.PRDT_TYPE;
        } else {
            IBS.init(SCCGWA, BPRPDTP);
            BPRPDTP.KEY.PRDT_TYPE = BPCPQPDR.PRDT_TYPE;
            IBS.init(SCCGWA, BPCRBPDT);
            BPCRBPDT.INFO.POINTER = BPRPDTP;
            BPCRBPDT.INFO.LEN = 237;
            BPCRBPDT.INFO.FUNC = 'S';
            S000_CALL_BPZRBPDT();
            if (pgmRtn) return;
            WS_TP_CNT = 0;
            while (BPCRBPDT.RC.RC_CODE == 0 
                && BPCRBPDT.RETURN_INFO != 'N' 
                && WS_TP_CNT < K_MAX_TYPE_CNT) {
                IBS.init(SCCGWA, BPCRBPDT.RC);
                BPCRBPDT.INFO.FUNC = 'R';
                S000_CALL_BPZRBPDT();
                if (pgmRtn) return;
                if (BPCRBPDT.RC.RC_CODE == 0 
                    && BPCRBPDT.RETURN_INFO == 'F') {
                    WS_TP_CNT = WS_TP_CNT + 1;
                    WS_LOW_TYPE_INFO.WS_LOW_TYPE[WS_TP_CNT-1] = BPRPDTP.KEY.PRDT_TYPE;
                }
            }
            IBS.init(SCCGWA, BPCRBPDT.RC);
            BPCRBPDT.INFO.FUNC = 'E';
            S000_CALL_BPZRBPDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_TP_CNT);
            for (WS_CNT = 1; WS_CNT < WS_TP_CNT; WS_CNT += 1) {
                BPCPQPDR.PRDT_TYPE = WS_LOW_TYPE_INFO.WS_LOW_TYPE[WS_CNT-1];
                S000_CALL_BPZPQPDR();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPQPDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_BRW_TP_REL_PRD, BPCPQPDR);
    }
    public void S000_CALL_BPZRMPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_PRD_TYPE, BPCRMPDT);
    }
    public void S000_CALL_BPZRBPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_PRD_TYPE, BPCRBPDT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQPDR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQPDR = ");
            CEP.TRC(SCCGWA, BPCPQPDR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

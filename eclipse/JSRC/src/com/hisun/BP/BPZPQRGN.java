package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQRGN {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_CNT = 10;
    String CPN_R_INQ_RGND = "BP-R-BRW-RGND ";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    int WS_CNT = 0;
    BPZPQRGN_WS_RGN_CODE WS_RGN_CODE = new BPZPQRGN_WS_RGN_CODE();
    char WS_OUT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRRGND BPRRGND = new BPRRGND();
    BPCTBRGN BPCTBRGN = new BPCTBRGN();
    BPCPRGST BPCPRGST = new BPCPRGST();
    SCCGWA SCCGWA;
    BPCPQRGN BPCPQRGN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPQRGN BPCPQRGN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQRGN = BPCPQRGN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQRGN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQRGN.RC);
        IBS.init(SCCGWA, BPCPQRGN.RGN_NO_INFO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_QRGN_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPQRGN.RGN_TYPE.trim().length() == 0 
            || (BPCPQRGN.UNIT.trim().length() == 0 
            || BPCPQRGN.UNIT.charAt(0) == 0X00)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQRGN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQRGN.BNK.equalsIgnoreCase("0")) {
            BPCPQRGN.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B020_GET_QRGN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRGND);
        BPRRGND.KEY.BNK = BPCPQRGN.BNK;
        BPRRGND.KEY.RGN_TYPE = BPCPQRGN.RGN_TYPE;
        IBS.init(SCCGWA, BPCTBRGN);
        BPCTBRGN.INFO.POINTER = BPRRGND;
        BPCTBRGN.DATA_LEN = 184;
        BPCTBRGN.FUNC = 'S';
        S000_CALL_BPZTBRGN();
        if (pgmRtn) return;
        BPCTBRGN.FUNC = 'R';
        S000_CALL_BPZTBRGN();
        if (pgmRtn) return;
        if (BPCTBRGN.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RGN_NO_NOTFND, BPCPQRGN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CNT = 1;
        while (WS_CNT <= K_MAX_CNT 
            && BPCTBRGN.RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, BPRRGND.KEY.RGN_NO);
            CEP.TRC(SCCGWA, BPRRGND.KEY.RGN_UNIT);
            if (BPRRGND.FLG == '0') {
                if (BPRRGND.KEY.RGN_UNIT.equalsIgnoreCase(BPCPQRGN.UNIT)) {
                    WS_OUT_FLG = 'Y';
                } else {
                    WS_OUT_FLG = 'N';
                }
            } else if (BPRRGND.FLG == '1'
                || BPRRGND.FLG == '2') {
                if (BPRRGND.KEY.RGN_UNIT.equalsIgnoreCase(BPCPQRGN.UNIT)) {
                    WS_OUT_FLG = 'Y';
                } else {
                    IBS.init(SCCGWA, BPCPRGST);
                    BPCPRGST.BNK = BPCPQRGN.BNK;
                    if (BPCPQRGN.UNIT.trim().length() == 0) BPCPRGST.BR1 = 0;
                    else BPCPRGST.BR1 = Integer.parseInt(BPCPQRGN.UNIT);
                    if (BPRRGND.KEY.RGN_UNIT.trim().length() == 0) BPCPRGST.BR2 = 0;
                    else BPCPRGST.BR2 = Integer.parseInt(BPRRGND.KEY.RGN_UNIT);
                    S000_CALL_BPZPRGST();
                    if (pgmRtn) return;
                    if (BPCPRGST.FLAG == 'Y' 
                        && (BPCPRGST.LVL_RELATION == 'A' 
                        && BPRRGND.FLG == '1' 
                        || BPCPRGST.LVL_RELATION == 'C' 
                        && BPRRGND.FLG == '2')) {
                        WS_OUT_FLG = 'Y';
                    } else {
                        WS_OUT_FLG = 'N';
                    }
                }
            } else {
                WS_OUT_FLG = 'N';
            }
            if (WS_OUT_FLG == 'Y') {
                WS_RGN_CODE.WS_RGN_TYPE = BPRRGND.KEY.RGN_TYPE;
                WS_RGN_CODE.WS_RGN_SEQ = BPRRGND.KEY.RGN_NO;
                BPCPQRGN.RGN_NO_INFO.RGN_CODE[WS_CNT-1] = IBS.CLS2CPY(SCCGWA, WS_RGN_CODE);
                WS_CNT = WS_CNT + 1;
            }
            do {
                BPCTBRGN.FUNC = 'R';
                S000_CALL_BPZTBRGN();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPRRGND.KEY.RGN_NO);
                CEP.TRC(SCCGWA, BPRRGND.KEY.RGN_UNIT);
            } while (BPRRGND.KEY.RGN_NO == WS_RGN_CODE.WS_RGN_SEQ 
                && BPCTBRGN.RETURN_INFO != 'N');
        }
        BPCTBRGN.FUNC = 'E';
        S000_CALL_BPZTBRGN();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZTBRGN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_INQ_RGND, BPCTBRGN);
        if (BPCTBRGN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTBRGN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQRGN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQRGN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQRGN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQRGN = ");
            CEP.TRC(SCCGWA, BPCPQRGN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

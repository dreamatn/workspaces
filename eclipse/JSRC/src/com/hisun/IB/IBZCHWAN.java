package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class IBZCHWAN {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT = "IBG70   ";
    double WS_DEL_AMT = 0;
    double WS_SELL_AMT = 0;
    double WS_CNT_AMT = 0;
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCOCHWN IBCOCHWN = new IBCOCHWN();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCFX BPCFX = new BPCFX();
    BPCPRMR BPCPRMR = new BPCPRMR();
    IBCSETCK IBCSETCK = new IBCSETCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    IBCCHWAN IBCCHWAN;
    public void MP(SCCGWA SCCGWA, IBCCHWAN IBCCHWAN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCCHWAN = IBCCHWAN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBZCHWAN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (IBCCHWAN.FUNC == '1') {
            B020_EXP_DATE_CHECK();
        } else if (IBCCHWAN.FUNC == '2') {
            B030_SETT_AMT_CHECK();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + IBCCHWAN.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B040_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCCHWAN.FUNC);
        CEP.TRC(SCCGWA, IBCCHWAN.CCY);
        if (IBCCHWAN.FUNC == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT);
        }
        if (IBCCHWAN.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY_M);
        }
    }
    public void B020_EXP_DATE_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCCHWAN.EXP_DATE);
        IBS.init(SCCGWA, BPCPGDIN);
        BPCPGDIN.INPUT_DATA.FUNC = '1';
        BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
        BPCPGDIN.INPUT_DATA.DATE_1 = IBCCHWAN.EXP_DATE;
        BPCPGDIN.INPUT_DATA.CCY = IBCCHWAN.CCY;
        S000_CALL_BPZPGDIN();
        if (BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
            IBCOCHWN.NEED_FLG = 'Y';
        } else {
            IBCOCHWN.NEED_FLG = 'N';
        }
    }
    public void B030_SETT_AMT_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCCHWAN.ESET_AMT);
        CEP.TRC(SCCGWA, IBCCHWAN.ASET_AMT);
        if (IBCCHWAN.ESET_AMT != IBCCHWAN.ASET_AMT) {
            WS_DEL_AMT = IBCCHWAN.ASET_AMT - IBCCHWAN.ESET_AMT;
            bigD = new BigDecimal(WS_DEL_AMT);
            WS_DEL_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        }
        CEP.TRC(SCCGWA, WS_DEL_AMT);
        if (WS_DEL_AMT != 0) {
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, IBCSETCK);
            IBCSETCK.KEY.TYP = "PIB01";
            IBCSETCK.KEY.CD = "DIFCK";
            BPCPRMR.DAT_PTR = IBCSETCK;
            S000_CALL_BPZPRMR();
            WS_CNT_AMT = IBCSETCK.DATA_TXT.CNT_AMT;
            if (IBCCHWAN.CCY.equalsIgnoreCase("156")) {
                WS_SELL_AMT = WS_DEL_AMT;
            } else {
                CEP.TRC(SCCGWA, BPCRBANK.EX_RA);
                CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
                BPCFX.EXR_TYPE = "MDR";
                BPCFX.BUY_CCY = IBCCHWAN.CCY;
                BPCFX.BUY_AMT = WS_DEL_AMT;
                BPCFX.SELL_CCY = BPCRBANK.LOC_CCY1;
                S000_CALL_BPZSFX();
                WS_SELL_AMT = BPCFX.SELL_AMT;
            }
            CEP.TRC(SCCGWA, WS_SELL_AMT);
            CEP.TRC(SCCGWA, WS_CNT_AMT);
            if (WS_SELL_AMT > WS_CNT_AMT) {
                IBCOCHWN.NEED_FLG = 'Y';
            } else {
                IBCOCHWN.NEED_FLG = 'N';
            }
        }
    }
    public void B040_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCOCHWN.NEED_FLG);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOCHWN;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPGDIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-GET-DT-INFO", BPCPGDIN);
        if (BPCPGDIN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPGDIN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFX.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

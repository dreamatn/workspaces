package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQBOX {
    boolean pgmRtn = false;
    String CPN_R_ADW_CLIB = "BP-R-ADW-CLIB    ";
    String CPN_R_BRE_CLBI = "BP-R-BRE-CLBI    ";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_CNT = 0;
    int WS_BR = 0;
    String WS_CRNT_TLR = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRCLBI BPRCLBI = new BPRCLBI();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCTLBIB BPCTLBIB = new BPCTLBIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBOX BPCPQBOX;
    public void MP(SCCGWA SCCGWA, BPCPQBOX BPCPQBOX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQBOX = BPCPQBOX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQBOX return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRCLBI);
        IBS.init(SCCGWA, BPCPQBOX.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B020_QUERY_BOX_INF1();
        if (pgmRtn) return;
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.VB_FLG);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.VB_TLR);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.VB_BR);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CCY);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.OPP_TLR);
        WS_CRNT_TLR = BPCPQBOX.DATA_INFO.OPP_TLR;
        BPCPQBOX.DATA_INFO.OPP_TLR = " ";
        if (BPCPQBOX.DATA_INFO.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_INPUT, BPCPQBOX.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_BOX_INF1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = BPCPQBOX.DATA_INFO.VB_BR;
        BPRTLVB.KEY.PLBOX_NO = BPCPQBOX.DATA_INFO.PLBOX_NO;
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        BPCTLVBF.INFO.FUNC = 'Q';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        if (BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CLIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "TEXT10");
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR1);
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR2);
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR3);
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR4);
        CEP.TRC(SCCGWA, WS_CRNT_TLR);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CASH_TYP);
        if (BPRTLVB.PLBOX_TP != '6') {
            BPCPQBOX.DATA_INFO.MGR_TLR = BPRTLVB.CRNT_TLR;
        } else {
            if (BPRTLVB.CRNT_TLR.trim().length() > 0) {
                if (WS_CRNT_TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR)) {
                    BPCPQBOX.DATA_INFO.MGR_TLR = BPRTLVB.CRNT_TLR;
                }
            }
            if (BPRTLVB.CRNT_TLR1.trim().length() > 0) {
                if (WS_CRNT_TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR1)) {
                    BPCPQBOX.DATA_INFO.MGR_TLR = BPRTLVB.CRNT_TLR1;
                }
            }
            if (BPRTLVB.CRNT_TLR2.trim().length() > 0) {
                if (WS_CRNT_TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR2)) {
                    BPCPQBOX.DATA_INFO.MGR_TLR = BPRTLVB.CRNT_TLR2;
                }
            }
            if (BPRTLVB.CRNT_TLR3.trim().length() > 0) {
                if (WS_CRNT_TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR3)) {
                    BPCPQBOX.DATA_INFO.MGR_TLR = BPRTLVB.CRNT_TLR3;
                }
            }
            if (BPRTLVB.CRNT_TLR4.trim().length() > 0) {
                if (WS_CRNT_TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR4)) {
                    BPCPQBOX.DATA_INFO.MGR_TLR = BPRTLVB.CRNT_TLR4;
                }
            }
        }
        BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
        BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPRCLIB.KEY.CASH_TYP = BPCPQBOX.DATA_INFO.CASH_TYP;
        BPRCLIB.KEY.CCY = BPCPQBOX.DATA_INFO.CCY;
        BPCTLIBF.INFO.FUNC = 'Q';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPQBOX.DATA_INFO.LMT_U = BPRCLIB.LMT_U;
        BPCPQBOX.DATA_INFO.LMT_L = BPRCLIB.LMT_L;
        BPCPQBOX.DATA_INFO.BAL = BPRCLIB.BAL;
        BPCPQBOX.DATA_INFO.GD_AMT = BPRCLIB.GD_AMT;
        BPCPQBOX.DATA_INFO.BD_AMT = BPRCLIB.BD_AMT;
        BPCPQBOX.DATA_INFO.HBD_AMT = BPRCLIB.HBD_AMT;
        BPCPQBOX.DATA_INFO.L_GD_AMT = BPRCLIB.L_GD_AMT;
        BPCPQBOX.DATA_INFO.L_BD_AMT = BPRCLIB.L_BD_AMT;
        BPCPQBOX.DATA_INFO.L_HBD_AMT = BPRCLIB.L_HBD_AMT;
        BPCPQBOX.DATA_INFO.L_TLT_AMT = BPRCLIB.L_TLT_AMT;
        BPCPQBOX.DATA_INFO.LAST_DT = BPRCLIB.LAST_DT;
        BPCPQBOX.DATA_INFO.BAL_FLG = BPRCLIB.BAL_FLG;
        BPCPQBOX.DATA_INFO.OPP_TLR = BPCPQBOX.DATA_INFO.MGR_TLR;
    }
    public void B030_QUERY_BOX_INF2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLBI);
        IBS.init(SCCGWA, BPCTLBIB);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.VB_FLG);
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CCY);
        BPRCLBI.KEY.VB_BR = BPCPQBOX.DATA_INFO.VB_BR;
        BPRCLBI.KEY.PLBOX_NO = BPCPQBOX.DATA_INFO.PLBOX_NO;
        BPRCLBI.KEY.CASH_TYP = BPCPQBOX.DATA_INFO.CASH_TYP;
        BPRCLBI.KEY.CCY = BPCPQBOX.DATA_INFO.CCY;
        CEP.TRC(SCCGWA, BPRCLBI.KEY.VB_BR);
        CEP.TRC(SCCGWA, BPRCLBI.KEY.CCY);
        BPCTLBIB.INFO.FUNC = '2';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTLBIB.RC);
        BPCTLBIB.INFO.FUNC = 'N';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCLBI);
        for (WS_CNT = 1; BPCTLBIB.RETURN_INFO != 'N' 
            && WS_CNT <= 20; WS_CNT += 1) {
            BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].PAR_VAL = BPRCLBI.KEY.PAR_VAL;
            BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].M_FLG = BPRCLBI.KEY.M_FLG;
            BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].GD_NUM = BPRCLBI.GD_NUM;
            BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].BD_NUM = BPRCLBI.BD_NUM;
            BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].HBD_NUM = BPRCLBI.HBD_NUM;
            BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].L_GD_NUM = BPRCLBI.L_GD_NUM;
            BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].L_BD_NUM = BPRCLBI.L_BD_NUM;
            BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].L_HBD_NUM = BPRCLBI.L_HBD_NUM;
            BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].B_GD_NUM = BPRCLBI.B_GD_NUM;
            BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].B_BD_NUM = BPRCLBI.B_BD_NUM;
            BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].B_HBD_NUM = BPRCLBI.B_HBD_NUM;
            CEP.TRC(SCCGWA, BPCPQBOX);
            IBS.init(SCCGWA, BPCTLBIB.RC);
            BPCTLBIB.INFO.FUNC = 'N';
            BPCTLBIB.POINTER = BPRCLBI;
            BPCTLBIB.LEN = 222;
            S000_CALL_BPZTLBIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRCLBI);
        }
        IBS.init(SCCGWA, BPCTLBIB.RC);
        BPCTLBIB.INFO.FUNC = 'E';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCLBI);
        WS_CNT = WS_CNT - 1;
        BPCPQBOX.DATA_INFO.CNT = WS_CNT;
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_CLIB, BPCTLIBF);
    }
    public void S000_CALL_BPZTLBIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_CLBI, BPCTLBIB);
        if (BPCTLBIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCTLBIB.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLBIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQBOX.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQBOX = ");
            CEP.TRC(SCCGWA, BPCPQBOX);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCSEX {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP142";
    String K_CCY_CNY = "156";
    String CPN_EXCHANGE = "BP-EX         ";
    String CPN_P_VCH_CPNT = "BP-P-VWA-WRITE";
    String CPN_P_CASH_PROD_INQ = "BP-P-CASH-PROD-INQ";
    String CPN_R_HISF = "BP-R-ADW-THIS    ";
    String CPN_F_TLR_ACCU_MGM = "BP-F-TLR-ACCU-MGM ";
    String CPN_U_CASH_IN = "BP-U-CASH-IN        ";
    String CPN_U_CASH_OUT = "BP-U-CASH-OUT       ";
    String CPN_P_ADD_CHIS = "BP-P-ADD-CHIS       ";
    String WS_ERR_MSG = " ";
    String WS_TEMP_CCY = " ";
    double WS_TEMP_AMT = 0;
    int WS_SEQ = 0;
    int WS_INFO_CNT = 0;
    double WS_SELL_AMT = 0;
    double WS_BUY_AMT = 0;
    char WS_SIGN_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTHIS BPRTHIS = new BPRTHIS();
    BPCTHISF BPCTHISF = new BPCTHISF();
    BPCEX BPCEX = new BPCEX();
    BPCUCSIN BPCUCSIN = new BPCUCSIN();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    BPCPPRDQ BPCPPRDQ = new BPCPPRDQ();
    BPCUCSTO BPCUCSTO = new BPCUCSTO();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCOCSEX BPCOCSEX = new BPCOCSEX();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    SCCGWA SCCGWA;
    BPCSCSEX BPCSCSEX;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSCSEX BPCSCSEX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCSEX = BPCSCSEX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSCSEX return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B030_EXCHANGE_FOR_CN();
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
                && BPCSCSEX.PL_AMT != 0) {
                B050_PROFIT_LOST_PROC();
            }
        } else {
            B030_EXCHANGE();
            if (BPCSCSEX.L_AMT != 0) {
                B040_CASH_OUT_PROC();
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
                && BPCSCSEX.PL_AMT != 0) {
                B050_PROFIT_LOST_PROC();
            }
            B060_TELLER_TOTAL_PROC();
            B070_HISTORY_PROC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B080_OUTPUT_PROC();
        }
    }
    public void B010_CHECK_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_CASH_IN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCSIN);
        BPCUCSIN.CASH_STAT = '0';
        BPCUCSIN.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCSIN.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUCSIN.VB_FLG = '0';
        BPCUCSIN.CCY = BPCSCSEX.F_CCY;
        BPCUCSIN.CS_KIND = '0';
        BPCUCSIN.TX_AMT = BPCSCSEX.F_AMT;
        BPCUCSIN.PLBOX_TYP = '3';
        BPCUCSIN.CASH_TYP = "01";
        CEP.TRC(SCCGWA, BPCUCSIN.CCY);
        CEP.TRC(SCCGWA, BPCUCSIN.TX_AMT);
        S000_CALL_BPZUCSIN();
    }
    public void B030_EXCHANGE_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEX);
        BPCEX.BUY_CCY = BPCSCSEX.F_CCY;
        BPCEX.BUY_AMT = BPCSCSEX.F_AMT;
        BPCEX.SELL_CCY = BPCSCSEX.L_CCY;
        BPCEX.SELL_AMT = BPCSCSEX.L_AMT;
        BPCEX.EXR_TYPE = "TRE";
        BPCEX.TRN_EX_RATE = BPCSCSEX.EX_RATE;
        BPCEX.READ_RT_TIME = BPCSCSEX.EX_TIME;
        S000_CALL_BPZEX();
    }
    public void B030_EXCHANGE() throws IOException,SQLException,Exception {
    }
    public void B040_CASH_OUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCSTO);
        BPCUCSTO.CASH_STAT = '0';
        BPCUCSTO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCSTO.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUCSTO.VB_FLG = '0';
        BPCUCSTO.CCY = BPCSCSEX.L_CCY;
        BPCUCSTO.CS_KIND = '0';
        BPCUCSTO.TX_AMT = BPCSCSEX.L_AMT;
        BPCUCSTO.PLBOX_TYP = '3';
        BPCUCSTO.CASH_TYP = "01";
        CEP.TRC(SCCGWA, BPCUCSTO.CCY);
        CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
        S000_CALL_BPZUCSTO();
    }
    public void B050_PROFIT_LOST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        BPCPOEWA.DATA.EVENT_CODE = "CSMOCB";
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSCSEX.L_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSCSEX.PL_AMT;
        S000_CALL_BPZPOEWA();
    }
    public void B060_TELLER_TOTAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLAM);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCFTLAM.OP_CODE = 'D';
        } else {
            BPCFTLAM.OP_CODE = 'A';
        }
        BPCFTLAM.ACCU_TYP = "01";
        BPCFTLAM.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCFTLAM.CCY = BPCSCSEX.F_CCY;
        BPCFTLAM.AMT = BPCSCSEX.F_AMT;
        S000_CALL_BPZFTLAM();
        IBS.init(SCCGWA, BPCFTLAM);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCFTLAM.OP_CODE = 'D';
        } else {
            BPCFTLAM.OP_CODE = 'A';
        }
        BPCFTLAM.ACCU_TYP = "02";
        BPCFTLAM.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCFTLAM.CCY = BPCSCSEX.L_CCY;
        BPCFTLAM.AMT = BPCSCSEX.L_AMT;
        S000_CALL_BPZFTLAM();
    }
    public void B070_HISTORY_PROC() throws IOException,SQLException,Exception {
        WS_TEMP_CCY = BPCSCSEX.F_CCY;
        WS_TEMP_AMT = BPCSCSEX.F_AMT;
        WS_SIGN_FLG = 'C';
        WS_SEQ = WS_SEQ + 1;
        R000_ADD_HISTORY();
        WS_TEMP_CCY = BPCSCSEX.L_CCY;
        WS_TEMP_AMT = BPCSCSEX.L_AMT;
        WS_SIGN_FLG = 'D';
        WS_SEQ = WS_SEQ + 1;
        R000_ADD_HISTORY();
    }
    public void R000_ADD_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHIS);
        IBS.init(SCCGWA, BPCTHISF);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRTHIS.KEY.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
            BPRTHIS.KEY.SEQ = WS_SEQ;
            BPRTHIS.AP_CODE = SCCGWA.COMM_AREA.TR_MMO;
            BPRTHIS.TR_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            BPRTHIS.DC_FLG = WS_SIGN_FLG;
            if (WS_SIGN_FLG == 'C') {
                BPRTHIS.RCE_PBNO = BPCUCSIN.PLBOX_NO;
            } else {
                BPRTHIS.PAY_PBNO = BPCUCSTO.PLBOX_NO;
            }
            BPRTHIS.CCY = WS_TEMP_CCY;
            BPRTHIS.CCY_TYP = BPCSCSEX.CCY_TYP;
            BPRTHIS.AC = BPCSCSEX.OPP_AC;
            BPRTHIS.AC_NAME = BPCSCSEX.OPP_ACNM;
            BPRTHIS.AMT = WS_TEMP_AMT;
            BPRTHIS.ID_TYP = BPCSCSEX.ID_TYP;
            BPRTHIS.IDNO = BPCSCSEX.IDNO;
            BPRTHIS.CASH_NO = BPCSCSEX.CASH_NO;
            BPRTHIS.RMK = BPCSCSEX.RMK;
            BPRTHIS.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRTHIS.STS = '0';
            BPCTHISF.LEN = 959;
            BPCTHISF.INFO.FUNC = 'A';
            CEP.TRC(SCCGWA, BPRTHIS.KEY.DATE);
            CEP.TRC(SCCGWA, BPRTHIS.KEY.VCH_NO);
            CEP.TRC(SCCGWA, BPRTHIS.KEY.SEQ);
            CEP.TRC(SCCGWA, BPRTHIS.AP_CODE);
            CEP.TRC(SCCGWA, BPRTHIS.TR_CODE);
            CEP.TRC(SCCGWA, BPRTHIS.DC_FLG);
            CEP.TRC(SCCGWA, BPRTHIS.CCY);
            CEP.TRC(SCCGWA, BPRTHIS.CCY_TYP);
            CEP.TRC(SCCGWA, BPRTHIS.AC);
            CEP.TRC(SCCGWA, BPRTHIS.AC_NAME);
            CEP.TRC(SCCGWA, BPRTHIS.AMT);
            CEP.TRC(SCCGWA, BPRTHIS.ID_TYP);
            CEP.TRC(SCCGWA, BPRTHIS.IDNO);
            CEP.TRC(SCCGWA, BPRTHIS.CASH_NO);
            CEP.TRC(SCCGWA, BPRTHIS.RMK);
            CEP.TRC(SCCGWA, BPRTHIS.TLR);
            CEP.TRC(SCCGWA, BPRTHIS.STS);
            BPCTHISF.POINTER = BPRTHIS;
            S000_CALL_BPZTHISF();
        } else {
            BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRTHIS.KEY.VCH_NO = "" + GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            JIBS_tmp_int = BPRTHIS.KEY.VCH_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) BPRTHIS.KEY.VCH_NO = "0" + BPRTHIS.KEY.VCH_NO;
            BPRTHIS.KEY.SEQ = WS_SEQ;
            BPCTHISF.LEN = 959;
            BPCTHISF.INFO.FUNC = 'R';
            BPCTHISF.POINTER = BPRTHIS;
            S000_CALL_BPZTHISF();
            BPRTHIS.STS = '1';
            BPCTHISF.LEN = 959;
            BPCTHISF.INFO.FUNC = 'U';
            BPCTHISF.POINTER = BPRTHIS;
            S000_CALL_BPZTHISF();
        }
    }
    public void B080_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCSEX);
        BPCOCSEX.F_CCY = BPCSCSEX.F_CCY;
        BPCOCSEX.F_AMT = BPCSCSEX.F_AMT;
        BPCOCSEX.L_CCY = BPCSCSEX.L_CCY;
        BPCOCSEX.L_AMT = BPCSCSEX.L_AMT;
        BPCOCSEX.PL_AMT = BPCSCSEX.PL_AMT;
        BPCOCSEX.EX_TIME = BPCSCSEX.EX_TIME;
        BPCOCSEX.EX_RATE = BPCSCSEX.EX_RATE;
        BPCOCSEX.ID_TYP = BPCSCSEX.ID_TYP;
        BPCOCSEX.IDNO = BPCSCSEX.IDNO;
        BPCOCSEX.RMK = BPCSCSEX.RMK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOCSEX;
        SCCFMT.DATA_LEN = 275;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZTHISF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_HISF, BPCTHISF);
        if (BPCTHISF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTHISF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_VCH_CPNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_VCH_CPNT, BPCOVAWR);
        if (BPCOVAWR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOVAWR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_EXCHANGE, BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_ACCU_MGM, BPCFTLAM);
        if (BPCFTLAM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLAM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPPRDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CASH_PROD_INQ, BPCPPRDQ);
        if (BPCPPRDQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPPRDQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCSIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_IN, BPCUCSIN);
        if (BPCUCSIN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSIN.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCSTO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_OUT, BPCUCSTO);
        if (BPCUCSTO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSTO.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_ADD_CHIS, BPCPCHIS);
        if (BPCPCHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

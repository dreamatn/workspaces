package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DD.*;
import com.hisun.AI.*;
import com.hisun.IB.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMOT8050 {
    String WS_ERR_MSG = " ";
    String WS_ACTY_STD_AC = " ";
    int WS_I = 0;
    CMOT8050_WS_OUT_INFO WS_OUT_INFO = new CMOT8050_WS_OUT_INFO();
    char WS_AC_TYPE_FLG = ' ';
    String WS_AC_DETL_FLG = " ";
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    AICPQMIB AICPQMIB = new AICPQMIB();
    IBCQINF IBCQINF = new IBCQINF();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    CMB8050_AWA_8050 CMB8050_AWA_8050;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMOT8050 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CMB8050_AWA_8050>");
        CMB8050_AWA_8050 = (CMB8050_AWA_8050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_INFO();
        B050_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMB8050_AWA_8050.AC_TYPE);
        CEP.TRC(SCCGWA, CMB8050_AWA_8050.AC);
        CEP.TRC(SCCGWA, CMB8050_AWA_8050.CCY);
        if ((CMB8050_AWA_8050.AC_TYPE != 'A' 
            && CMB8050_AWA_8050.AC_TYPE != 'K' 
            && CMB8050_AWA_8050.AC_TYPE != 'D' 
            && CMB8050_AWA_8050.AC_TYPE != 'I' 
            && CMB8050_AWA_8050.AC_TYPE != 'N')) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_AC_TYPE_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = CMB8050_AWA_8050.AC;
        CEP.TRC(SCCGWA, CMB8050_AWA_8050.AC);
        CEP.TRC(SCCGWA, DCCPACTY.INPUT.AC);
        S000_CALL_DCZPACTY();
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_FREE_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_DETL);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        WS_AC_TYPE_FLG = DCCPACTY.OUTPUT.AC_TYPE;
        WS_AC_DETL_FLG = DCCPACTY.OUTPUT.AC_DETL;
        WS_ACTY_STD_AC = DCCPACTY.OUTPUT.STD_AC;
        if ((CMB8050_AWA_8050.AC_TYPE == 'K' 
            && WS_AC_TYPE_FLG != 'K') 
            || (CMB8050_AWA_8050.AC_TYPE == 'D' 
            && WS_AC_TYPE_FLG != 'D') 
            || (CMB8050_AWA_8050.AC_TYPE == 'I' 
            && WS_AC_TYPE_FLG != 'G') 
            || (CMB8050_AWA_8050.AC_TYPE == 'N' 
            && !WS_AC_DETL_FLG.equalsIgnoreCase("IB"))) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_NOT_MATCH_ERR;
            S000_ERR_MSG_PROC();
        }
        if (WS_AC_DETL_FLG.equalsIgnoreCase("TD")) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_NOT_INOUT_TD_AC;
            S000_ERR_MSG_PROC();
        }
        if ((WS_AC_TYPE_FLG == 'D' 
            && WS_AC_DETL_FLG.equalsIgnoreCase("DD")) 
            || WS_AC_TYPE_FLG == 'K') {
            CEP.TRC(SCCGWA, "DD-K-AC");
            IBS.init(SCCGWA, DDCIMCCY);
            DDCIMCCY.DATA[1-1].AC = WS_ACTY_STD_AC;
            DDCIMCCY.DATA[1-1].CCY = CMB8050_AWA_8050.CCY;
            S000_CALL_DDZIMCCY();
            for (WS_I = 1; !DDCIMCCY.DATA[WS_I-1].CCY.equalsIgnoreCase(CMB8050_AWA_8050.CCY) 
                && DDCIMCCY.DATA[WS_I-1].CCY.trim().length() != 0; WS_I += 1) {
            }
            if (DDCIMCCY.DATA[WS_I-1].CCY.trim().length() == 0) {
                WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_CCY_ERROR;
                S000_ERR_MSG_PROC();
            } else {
                WS_OUT_INFO.WS_BAL = DDCIMCCY.DATA[WS_I-1].LAST_DAYEND_BAL;
            }
            CEP.TRC(SCCGWA, DDCIMCCY.DATA[WS_I-1].LAST_DAYEND_BAL);
            if (DDCIMCCY.DATA[WS_I-1].LAST_DAYEND_BAL < 0) {
                WS_OUT_INFO.WS_BAL_SIGN = '-';
            } else {
                WS_OUT_INFO.WS_BAL_SIGN = '+';
            }
        }
        if (WS_AC_TYPE_FLG == 'G' 
            || DCCPACTY.OUTPUT.AC_TYPE == 'G') {
            CEP.TRC(SCCGWA, "IN-AC");
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = CMB8050_AWA_8050.AC;
            AICPQMIB.INPUT_DATA.CCY = CMB8050_AWA_8050.CCY;
            S000_CALL_AIZPQMIB();
            CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.LBAL);
            WS_OUT_INFO.WS_BAL = AICPQMIB.OUTPUT_DATA.LBAL;
            if (AICPQMIB.OUTPUT_DATA.LBAL < 0) {
                WS_OUT_INFO.WS_BAL_SIGN = '-';
            } else {
                WS_OUT_INFO.WS_BAL_SIGN = '+';
            }
        }
        if (WS_AC_DETL_FLG.equalsIgnoreCase("IB") 
            || DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("IB")) {
            CEP.TRC(SCCGWA, "IB-AC");
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.NOSTRO_CD = WS_ACTY_STD_AC;
            IBCQINF.INPUT_DATA.CCY = CMB8050_AWA_8050.CCY;
            S000_CALL_IBZQINF();
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.L_VALUE_BAL);
            WS_OUT_INFO.WS_BAL = IBCQINF.OUTPUT_DATA.L_VALUE_BAL;
            if (IBCQINF.OUTPUT_DATA.L_VALUE_BAL < 0) {
                WS_OUT_INFO.WS_BAL_SIGN = '-';
            } else {
                WS_OUT_INFO.WS_BAL_SIGN = '+';
            }
        }
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B050-OUTPUT-PROC-BEGIN");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "35805";
        SCCFMT.ODM_FLG = 'Y';
        SCCFMT.DATA_PTR = WS_OUT_INFO;
        SCCFMT.DATA_LEN = 17;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "S000-CALL-DCZPACTY-BEGIN");
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
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

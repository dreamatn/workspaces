package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.IB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPACTY {
    String IB_ACCO = "60";
    String DD_ACCO = "11";
    String TD_ACCO = "12";
    String LN_ACCO = "13";
    String DC_ACCO = "26";
    char CARD_NO = 'K';
    char ACCO_NO = 'D';
    char INAC_NO = 'G';
    String WS_ERR_MSG = " ";
    char WS_AC_INFO = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICACCU CICACCU = new CICACCU();
    IBCQINF IBCQINF = new IBCQINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPACTY BPCPACTY;
    public void MP(SCCGWA SCCGWA, BPCPACTY BPCPACTY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPACTY = BPCPACTY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPACTY return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_INQ_AC_INFO_PROCESS();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B200_INQ_AC_INFO_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        IBS.init(SCCGWA, CICACCU);
        IBS.init(SCCGWA, IBCQINF);
        AICPQMIB.INPUT_DATA.AC = BPCPACTY.INPUT_DATA.AC;
        CICACCU.DATA.AGR_NO = BPCPACTY.INPUT_DATA.AC;
        IBCQINF.INPUT_DATA.NOSTRO_CD = BPCPACTY.INPUT_DATA.AC;
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.AC);
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, WS_AC_INFO);
        if (CICACCU.RC.RC_CODE != 0) {
            S000_CALL_AIZPQMIB();
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.FRM_APP);
        CEP.TRC(SCCGWA, WS_AC_INFO);
        if (AICPQMIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.NOSTRO_CD);
            S00_CALL_IBZQINF();
        }
        if (WS_AC_INFO == 'G') {
            BPCPACTY.OUTPUT_DATA.AC_TYPE = INAC_NO;
        } else if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
            BPCPACTY.OUTPUT_DATA.AC_TYPE = CARD_NO;
            BPCPACTY.OUTPUT_DATA.AC_DETL = DC_ACCO;
        } else if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DD")) {
            BPCPACTY.OUTPUT_DATA.AC_TYPE = ACCO_NO;
            BPCPACTY.OUTPUT_DATA.AC_DETL = DD_ACCO;
        } else if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("TD")) {
            BPCPACTY.OUTPUT_DATA.AC_TYPE = ACCO_NO;
            BPCPACTY.OUTPUT_DATA.AC_DETL = TD_ACCO;
        } else if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("LN")) {
            BPCPACTY.OUTPUT_DATA.AC_TYPE = ACCO_NO;
            BPCPACTY.OUTPUT_DATA.AC_DETL = LN_ACCO;
        } else if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("IB")) {
            BPCPACTY.OUTPUT_DATA.AC_TYPE = ACCO_NO;
            BPCPACTY.OUTPUT_DATA.AC_DETL = IB_ACCO;
        } else {
            CEP.TRC(SCCGWA, "NOTFIND");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_TYPE_NOTFND, BPCPACTY.RC);
        }
        CEP.TRC(SCCGWA, BPCPACTY.OUTPUT_DATA.AC_TYPE);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
        } else {
            WS_AC_INFO = 'G';
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZACCU", CICACCU);
        CEP.TRC(SCCGWA, "CIF");
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
        }
    }
    public void S00_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_TYP_NOT_DEF;
        } else {
            CICACCU.DATA.FRM_APP = IB_ACCO;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPACTY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPACTY = ");
            CEP.TRC(SCCGWA, BPCPACTY);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

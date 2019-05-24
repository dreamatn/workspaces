package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSRRVS {
    DBParm AITGINF_RD;
    DBParm AITGRVS_RD;
    String WS_ERR_MSG = " ";
    String WS_RVS_NO = " ";
    char WS_RRVS_TYPE = ' ';
    short WS_VWA_CNT = 0;
    char WS_GINF_FLG = ' ';
    char WS_GRVS_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPRVWA BPROVWA = new BPRVWA();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    AIRGINF AIRGINF = new AIRGINF();
    AIRGRVS AIRGRVS = new AIRGRVS();
    SCCGWA SCCGWA;
    BPRVWA BPRVWA;
    AICSRRVS AICSRRVS;
    public void MP(SCCGWA SCCGWA, AICSRRVS AICSRRVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSRRVS = AICSRRVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIZSRRVS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        WS_RVS_NO = AICSRRVS.GRVS_NO;
        B020_GET_RVS_INFO_FOR_VWA();
        WS_RRVS_TYPE = AIRGRVS.SIGN;
        if (AIRGINF.STS == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.CAN_RVS_HAVE_HAPPENNED;
            S000_ERR_MSG_PROC();
        }
        if (AIRGINF.G_BAL != 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.GINF_BAL_GT_ZERO;
            S000_ERR_MSG_PROC();
        }
        WS_RVS_NO = AICSRRVS.CRVS_NO;
        B020_GET_RVS_INFO_FOR_VWA();
        if (AIRGINF.STS == 'R') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.CAN_RVS_HAVE_HAPPENNED;
            S000_ERR_MSG_PROC();
        }
        if (AIRGINF.C_AMT != 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.CRVS_NO_OFF_HAPPENED;
            S000_ERR_MSG_PROC();
        }
        if (AIRGRVS.SIGN != WS_RRVS_TYPE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.TWO_RVS_TYPE_MUST_SAME;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AICSRRVS.GRVS_NO.trim().length() == 0 
            || AICSRRVS.CRVS_NO.trim().length() == 0 
            || AICSRRVS.AMT == 0 
            || AICSRRVS.GRVS_NO.equalsIgnoreCase(AICSRRVS.CRVS_NO)) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void R00_MOVE_VAWR_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOVAWR);
        BPCOVAWR.PARTB.BOOK_FLG = AIRGINF.BOOK_FLG;
        BPCOVAWR.PARTB.ITM = AIRGINF.ITM;
        BPCOVAWR.PARTB.BR = AIRGINF.BR;
        BPCOVAWR.PARTB.CCY = AIRGINF.CCY;
        BPCOVAWR.PARTB.MIB_NO = AIRGINF.AC;
        BPCOVAWR.PARTB.RVS_NO = AIRGINF.KEY.RVS_NO;
        if (AICSRRVS.GRVS_NO.equalsIgnoreCase(WS_RVS_NO)) {
            BPCOVAWR.PARTB.SIGN = AIRGRVS.SIGN;
        } else {
            if (AIRGRVS.SIGN == 'C') {
                BPCOVAWR.PARTB.SIGN = 'D';
            } else {
                BPCOVAWR.PARTB.SIGN = 'C';
            }
        }
        if (AIRGINF.G_AMT != AICSRRVS.AMT) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.GINF_AMT_NE_CANCEL_CAMT;
            S000_ERR_MSG_PROC();
        }
        BPCOVAWR.PARTB.AMT = AIRGINF.G_AMT;
        BPCOVAWR.PARTB.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOVAWR.PARTB.CI_NO = " ";
        BPCOVAWR.PARTB.CNTR_TYPE = "IA";
        BPCOVAWR.PARTB.PROD_CODE = "INTERNAL";
        BPCOVAWR.PARTB.DESC = "RRVS";
        BPCOVAWR.PARTB.POST_NARR = "RRVS";
    }
    public void R00_GEN_VWA() throws IOException,SQLException,Exception {
        WS_VWA_CNT += 1;
        if (WS_VWA_CNT == 1) {
            BPCOVAWR.FST_FLG = 'Y';
        } else {
            BPCOVAWR.FST_FLG = 'N';
        }
        if (BPCOVAWR.PARTB.AMT > 0) {
            BPCOVAWR.PARTB.RED_FLG = 'B';
        } else {
            BPCOVAWR.PARTB.RED_FLG = 'R';
        }
        S000_CALL_BPZPVAWR();
    }
    public void B020_GET_RVS_INFO_FOR_VWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, AIRGINF);
        AIRGRVS.KEY.RVS_NO = WS_RVS_NO;
        AIRGINF.KEY.RVS_NO = WS_RVS_NO;
        T000_READ_AITGRVS_FIRST();
        T000_READ_AITGINF();
        R00_MOVE_VAWR_DATA();
        R00_GEN_VWA();
    }
    public void S000_CALL_BPZPVAWR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-VWA-WRITE", BPCOVAWR);
        if (BPCOVAWR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOVAWR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_AITGINF() throws IOException,SQLException,Exception {
        AITGINF_RD = new DBParm();
        AITGINF_RD.TableName = "AITGINF";
        AITGINF_RD.errhdl = true;
        IBS.READ(SCCGWA, AIRGINF, AITGINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
            CEP.TRC(SCCGWA, "AIRGINF = ");
            CEP.TRC(SCCGWA, AIRGINF);
    } //FROM #ENDIF
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_AITGRVS_FIRST() throws IOException,SQLException,Exception {
        AITGRVS_RD = new DBParm();
        AITGRVS_RD.TableName = "AITGRVS";
        AITGRVS_RD.where = "RVS_NO = :AIRGRVS.KEY.RVS_NO";
        AITGRVS_RD.fst = true;
        AITGRVS_RD.order = "RVS_SEQ DESC";
        IBS.READ(SCCGWA, AIRGRVS, this, AITGRVS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GRVS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GRVS_FLG = 'N';
        } else {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
            CEP.TRC(SCCGWA, "AIRGRVS = ");
            CEP.TRC(SCCGWA, AIRGRVS);
    } //FROM #ENDIF
        }
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
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

package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZCKPRM {
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    short WS_I = 0;
    char WS_TXNBR_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    IBCPMORG IBCPMORG = new IBCPMORG();
    IBCSETCK IBCSETCK = new IBCSETCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    IBCCKPRM IBCCKPRM;
    public void MP(SCCGWA SCCGWA, IBCCKPRM IBCCKPRM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCCKPRM = IBCCKPRM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZCKPRM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
        IBCCKPRM.RC.RC_MMO = " ";
        IBCCKPRM.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (IBCCKPRM.FUNC == '1') {
            B020_GET_SETTL_DIF_AMT();
            if (pgmRtn) return;
        } else if (IBCCKPRM.FUNC == '2') {
            B030_GET_BR_CTRL_PRM();
            if (pgmRtn) return;
        } else if (IBCCKPRM.FUNC == '3') {
            B020_GET_SETTL_DIF_AMT();
            if (pgmRtn) return;
            B040_CHECK_SETTL_DIF_AMT();
            if (pgmRtn) return;
        } else if (IBCCKPRM.FUNC == '4') {
            B030_GET_BR_CTRL_PRM();
            if (pgmRtn) return;
            B050_FIND_TXNBR();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + IBCCKPRM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCCKPRM.CD);
        CEP.TRC(SCCGWA, IBCCKPRM.FUNC);
        CEP.TRC(SCCGWA, IBCCKPRM.DIF_AMT);
        CEP.TRC(SCCGWA, IBCCKPRM.TXNBR);
        if (IBCCKPRM.CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.PARM_CD_M, IBCCKPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((IBCCKPRM.FUNC == '3' 
            && IBCCKPRM.DIF_AMT == 0) 
            || (IBCCKPRM.FUNC == '4' 
            && IBCCKPRM.TXNBR == 0)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INPUT, IBCCKPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_SETTL_DIF_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, IBCSETCK);
        IBCSETCK.KEY.TYP = "PIB01";
        IBCSETCK.KEY.CD = IBCCKPRM.CD;
        BPCPRMR.DAT_PTR = IBCSETCK;
        S000_LINK_BPZPRMR();
        if (pgmRtn) return;
        IBCCKPRM.CNT_AMT = IBCSETCK.DATA_TXT.CNT_AMT;
    }
    public void B030_GET_BR_CTRL_PRM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, IBCPMORG);
        IBCPMORG.KEY.TYP = "PIB09";
        IBCPMORG.KEY.CD = IBCCKPRM.CD;
        BPCPRMR.DAT_PTR = IBCPMORG;
        S000_LINK_BPZPRMR();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            IBCCKPRM.BR_TBL.OPER_BR[WS_I-1] = IBCPMORG.DATA_TXT.BR[WS_I-1];
        }
    }
    public void B040_CHECK_SETTL_DIF_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCCKPRM.DIF_AMT);
        CEP.TRC(SCCGWA, IBCSETCK.DATA_TXT.CNT_AMT);
        if (IBCCKPRM.DIF_AMT > IBCSETCK.DATA_TXT.CNT_AMT) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.ASAMT_THAN_ESAMT_MOR);
        }
    }
    public void B050_FIND_TXNBR() throws IOException,SQLException,Exception {
        if (IBCPMORG.DATA_TXT.CTL_TYP == '0') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR, IBCCKPRM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (IBCPMORG.DATA_TXT.CTL_TYP == '1') {
            WS_TXNBR_FLAG = 'N';
            for (WS_I = 1; WS_TXNBR_FLAG != 'Y' 
                && WS_I <= 10; WS_I += 1) {
                if (IBCCKPRM.TXNBR == IBCPMORG.DATA_TXT.BR[WS_I-1]) {
                    WS_TXNBR_FLAG = 'Y';
                }
            }
            if (WS_TXNBR_FLAG == 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else {
        }
    }
    public void S000_LINK_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ P TAB RC, RC=" + BPCPRMR.RC.RC_RTNCODE;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

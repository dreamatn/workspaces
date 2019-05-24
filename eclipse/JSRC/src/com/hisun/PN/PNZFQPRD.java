package com.hisun.PN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PNZFQPRD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_F_GET_RPD = "PN-F-GET-PRD";
    String K_CTL_PRM_TYP = "PRDPR";
    String WS_ERR_MSG = " ";
    PNZFQPRD_WS_MPRD_KEY WS_MPRD_KEY = new PNZFQPRD_WS_MPRD_KEY();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    PNVMPRD PNVMPRD = new PNVMPRD();
    SCCGWA SCCGWA;
    PNCFQPRD PNCFQPRD;
    public void MP(SCCGWA SCCGWA, PNCFQPRD PNCFQPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCFQPRD = PNCFQPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNZFQPRD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B101_INPUT_CHK_PROC();
        if (pgmRtn) return;
        B201_GET_PRD_PARM();
        if (pgmRtn) return;
        B202_GET_PRD_INFO();
        if (pgmRtn) return;
        B203_RETURN_DATA();
        if (pgmRtn) return;
    }
    public void B101_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNCFQPRD.PRD_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_PRD_MUST_IPT, PNCFQPRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B201_GET_PRD_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = PNCFQPRD.PRD_CD;
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "--------GET----JICHUCHANPIN---------");
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            CEP.TRC(SCCGWA, "---------PQPRD-PARM-CODE = SPACE---------");
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_PRD_PARM_NUL, PNCFQPRD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            PNCFQPRD.PRD_CD = BPCPQPRD.PARM_CODE;
            CEP.TRC(SCCGWA, PNCFQPRD.PRD_CD);
        }
    }
    public void B202_GET_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, PNVMPRD);
        BPRPRMT.KEY.TYP = K_CTL_PRM_TYP;
        WS_MPRD_KEY.FLR = "999999";
        WS_MPRD_KEY.WS_PARM_CODE = BPCPQPRD.PARM_CODE;
        CEP.TRC(SCCGWA, WS_MPRD_KEY.WS_PARM_CODE);
        CEP.TRC(SCCGWA, WS_MPRD_KEY);
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_MPRD_KEY);
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_MPRD_REC_NOTFND, PNCFQPRD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNVMPRD);
            CEP.TRC(SCCGWA, "-----CCY---PAY-TERM-------AUTO-REL-------");
            CEP.TRC(SCCGWA, PNVMPRD.VAL.CCY);
            CEP.TRC(SCCGWA, PNVMPRD.VAL.PAY_TERM);
            CEP.TRC(SCCGWA, PNVMPRD.VAL.AUTO_REL);
        }
    }
    public void B203_RETURN_DATA() throws IOException,SQLException,Exception {
        PNCFQPRD.VAL.CCY = PNVMPRD.VAL.CCY;
        PNCFQPRD.VAL.PAY_TERM = PNVMPRD.VAL.PAY_TERM;
        PNCFQPRD.VAL.AUTO_REL = PNVMPRD.VAL.AUTO_REL;
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCFQPRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (PNCFQPRD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "FQPRD-RC=");
            CEP.TRC(SCCGWA, PNCFQPRD.RC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

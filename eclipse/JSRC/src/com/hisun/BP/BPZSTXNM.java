package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTXNM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP725";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRTRT BPRTRT = new BPRTRT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCOTXNM BPCOTXNM = new BPCOTXNM();
    SCCGWA SCCGWA;
    BPCSTXNM BPCSTXNM;
    public void MP(SCCGWA SCCGWA, BPCSTXNM BPCSTXNM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTXNM = BPCSTXNM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTXNM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_GET_TXN_DESC_PROC();
        if (pgmRtn) return;
        B050_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSTXNM.TXN_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HIS_MUST_INP_TYP_CD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_TXN_DESC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTRT);
        IBS.init(SCCGWA, BPCPRMR);
        CEP.TRC(SCCGWA, "INITIALIZE SUCCESSFULLY");
        BPRTRT.KEY.TYP = "TRT";
        BPRTRT.KEY.CD = BPCSTXNM.TXN_CODE;
        CEP.TRC(SCCGWA, BPRTRT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRTRT.KEY.CD);
        BPCPRMR.DAT_PTR = BPRTRT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRTRT.DESC);
        CEP.TRC(SCCGWA, BPRTRT.CDESC);
        IBS.init(SCCGWA, BPCSTXNM);
        BPCSTXNM.TXN_DESC = BPRTRT.DESC;
        BPCSTXNM.TXN_CDESC = BPRTRT.CDESC;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            BPCSTXNM.TXN_DESC = " ";
            BPCSTXNM.TXN_CDESC = " ";
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TXN_CODE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTXNM);
        BPCOTXNM.TXN_DESC = BPCSTXNM.TXN_DESC;
        BPCOTXNM.TXN_CDESC = BPCSTXNM.TXN_CDESC;
        CEP.TRC(SCCGWA, BPCOTXNM.TXN_DESC);
        CEP.TRC(SCCGWA, BPCOTXNM.TXN_CDESC);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTXNM;
        SCCFMT.DATA_LEN = 81;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
        BPCSTXNM.TXN_DESC = " ";
        BPCSTXNM.TXN_CDESC = " ";
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

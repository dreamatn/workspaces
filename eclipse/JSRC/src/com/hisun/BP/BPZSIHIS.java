package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSIHIS {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_U_INQ_HIST = "BP-U-INQ-HIST";
    String WS_COMPONENT_NAME = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCUIHIS BPCUIHIS = new BPCUIHIS();
    BPCIPHIS BPCPPHIS = new BPCIPHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCSIHIS BPCSIHIS;
    BPCIPHIS BPCIPHIS;
    public void MP(SCCGWA SCCGWA, BPCSIHIS BPCSIHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSIHIS = BPCSIHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSIHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSIHIS.RC);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            BPCIPHIS = (BPCIPHIS) BPCSIHIS.DATA.PAGE_POINTER;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCUIHIS);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B030_TRANPAGE_CN();
            if (pgmRtn) return;
        }
        B020_BROWSE_HIST();
        if (pgmRtn) return;
    }
    public void B030_TRANPAGE_CN() throws IOException,SQLException,Exception {
        BPCUIHIS.DATA.PAGE_LEN = 9425;
        BPCUIHIS.DATA.PAGE_POINTER = BPCIPHIS;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_HIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIHIS);
        BPCUIHIS.DATA.JRNNO = BPCSIHIS.DATA.JRNNO;
        BPCUIHIS.DATA.CI_NO = BPCSIHIS.DATA.CI_NO;
        BPCUIHIS.DATA.AC = BPCSIHIS.DATA.AC;
        BPCUIHIS.DATA.REF_NO = BPCSIHIS.DATA.REF_NO;
        BPCUIHIS.DATA.TX_TOOL = BPCSIHIS.DATA.TX_TOOL;
        BPCUIHIS.DATA.CCY = BPCSIHIS.DATA.CCY;
        BPCUIHIS.DATA.STR_AMT = BPCSIHIS.DATA.STR_AMT;
        BPCUIHIS.DATA.END_AMT = BPCSIHIS.DATA.END_AMT;
        BPCUIHIS.DATA.TX_CD = BPCSIHIS.DATA.TX_CD;
        BPCUIHIS.DATA.TX_TYPE_CD = BPCSIHIS.DATA.TX_TYPE_CD;
        BPCUIHIS.DATA.TLR = BPCSIHIS.DATA.TLR;
        BPCUIHIS.DATA.STR_AC_DT = BPCSIHIS.DATA.STR_AC_DT;
        BPCUIHIS.DATA.END_AC_DT = BPCSIHIS.DATA.END_AC_DT;
        BPCUIHIS.DATA.STR_TX_DT = BPCSIHIS.DATA.STR_TX_DT;
        BPCUIHIS.DATA.END_TX_DT = BPCSIHIS.DATA.END_TX_DT;
        BPCUIHIS.DATA.STR_TX_TM = BPCSIHIS.DATA.STR_TX_TM;
        BPCUIHIS.DATA.END_TX_TM = BPCSIHIS.DATA.END_TX_TM;
        BPCUIHIS.DATA.DC_FLG = BPCSIHIS.DATA.DC_FLG;
        BPCUIHIS.DATA.BR = BPCSIHIS.DATA.BR;
        BPCUIHIS.DATA.FINANCE_FLG = BPCSIHIS.DATA.FINANCE_FLG;
        BPCUIHIS.DATA.MAKER_ID = BPCSIHIS.DATA.MAKER_ID;
        BPCUIHIS.DATA.CHECKER_ID = BPCSIHIS.DATA.CHECKER_ID;
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.MAKER_ID);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.CHECKER_ID);
        CEP.TRC(SCCGWA, BPCSIHIS.DATA.BR);
        CEP.TRC(SCCGWA, BPCSIHIS.DATA.LIST_CNT);
        BPCUIHIS.DATA.REC_NUM = BPCSIHIS.DATA.REC_NUM;
        BPCUIHIS.DATA.REC_FLG = BPCSIHIS.DATA.REC_FLG;
        BPCUIHIS.DATA.LIST_CNT = BPCSIHIS.DATA.LIST_CNT;
        BPCUIHIS.DATA.SORT_FLG = BPCSIHIS.DATA.SORT_FLG;
        CEP.TRC(SCCGWA, BPCSIHIS.DATA.REC_NUM);
        CEP.TRC(SCCGWA, BPCSIHIS.DATA.REC_FLG);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.REC_NUM);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.REC_FLG);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.LIST_CNT);
        CEP.TRC(SCCGWA, BPCSIHIS.DATA.SORT_FLG);
        S000_CALL_BPZUIHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZUIHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_INQ_HIST, BPCUIHIS);
        CEP.TRC(SCCGWA, BPCUIHIS.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUIHIS.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUIHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSIHIS.RC);
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

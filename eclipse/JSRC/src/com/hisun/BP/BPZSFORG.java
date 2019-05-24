package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICMSG_ERROR_MSG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZSFORG {
    boolean pgmRtn = false;
    char K_STS_OPEN = 'O';
    char K_STS_CLOSE = 'C';
    char K_STS_FORCE = 'F';
    String K_HIS_REMARKS = "ORG STATUS MAINTAIN     ";
    String K_CPY_BPRORGS = "BPRORGS";
    String CPN_F_TLR_STS = "BP-F-TLR-STS-QUERY  ";
    String CPN_R_ORGS_MT = "BP-R-ORGS-MAINTAIN  ";
    String CPN_F_TLR_SIGNOFF = "BP-F-TLR-SIGN-C-OFF ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_MSG_TYPE = ' ';
    BPZSFORG_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSFORG_WS_TEMP_VARIABLE();
    String WS_INFO = " ";
    BPZSFORG_WS_ERROR_INFO WS_ERROR_INFO = new BPZSFORG_WS_ERROR_INFO();
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRMOGS BPCRMOGS = new BPCRMOGS();
    BPCFTLSQ BPCFTLSQ = new BPCFTLSQ();
    BPCFTSGM BPCFTSGM = new BPCFTSGM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPBVBR BPCPBVBR = new BPCPBVBR();
    BPRORGS BPRORGS = new BPRORGS();
    BPRORGS BPROLDS = new BPRORGS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    AICPZMIB AICPZMIB = new AICPZMIB();
    BPRMSG BPRMSG = new BPRMSG();
    AICPGINF AICPGINF = new AICPGINF();
    BPCPCSBR BPCPCSBR = new BPCPCSBR();
    SCCGWA SCCGWA;
    BPCSFORG BPCSFORG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSFORG BPCSFORG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSFORG = BPCSFORG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSFORG return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRORGS);
        IBS.init(SCCGWA, BPCRMOGS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_ORG_STATUS();
        if (pgmRtn) return;
        B030_FORCIBLY_CLOSE_ORG();
        if (pgmRtn) return;
        B040_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B010_CHECK_ORG_STATUS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMOGS);
        IBS.init(SCCGWA, BPRORGS);
        CEP.TRC(SCCGWA, "B010-        ");
        if (BPCSFORG.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANT_FORCE_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRORGS.KEY.BNK = SCCGWA.COMM_AREA.TR_BANK;
        CEP.TRC(SCCGWA, BPRORGS.KEY.BNK);
        CEP.TRC(SCCGWA, BPCSFORG.BR);
        BPRORGS.KEY.BR = BPCSFORG.BR;
        CEP.TRC(SCCGWA, BPRORGS.KEY.BR);
        CEP.TRC(SCCGWA, BPRORGS);
        BPCRMOGS.FUNC = 'R';
        BPCRMOGS.DATA_LEN = 39;
        BPCRMOGS.POINTER = BPRORGS;
        S000_CALL_BPZTMOGS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B010- END    ");
        if (BPCRMOGS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (BPRORGS.STS != K_STS_OPEN) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_IS_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPCFTLSQ);
        BPCFTLSQ.TLR_BR = BPCSFORG.BR;
        BPCFTLSQ.SIGN_STS = 'O';
        B020_01_QUERY_TLR_SIGNOFF();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFTLSQ);
        BPCFTLSQ.TLR_BR = BPCSFORG.BR;
        BPCFTLSQ.SIGN_STS = 'T';
        B020_01_QUERY_TLR_SIGNOFF();
        if (pgmRtn) return;
        B020_02_CHECK_BALANCE();
        if (pgmRtn) return;
        B020_04_CHECK_ACCOUNT();
        if (pgmRtn) return;
        B020_05_CHECK_BV_ONWAY();
        if (pgmRtn) return;
        B030_CHECK_CASH_ONWAY();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, BPRORGS, BPROLDS);
    }
    public void B020_CHECK_TELLER_SIGNON() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLSQ);
        BPCFTLSQ.TLR_BR = BPCSFORG.BR;
        CEP.TRC(SCCGWA, BPCSFORG.BR);
        BPCFTLSQ.SIGN_STS = 'O';
        CEP.TRC(SCCGWA, BPCFTLSQ);
        CEP.TRC(SCCGWA, "TELLER BEFORE");
        S000_CALL_BPZFTLSQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TELLER AFTER ");
        CEP.TRC(SCCGWA, BPCFTLSQ.CNT);
        if (BPCFTLSQ.CNT == 0) {
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SHOULD_CLOSE_NORMAL;
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B020_01_QUERY_TLR_SIGNOFF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFTLSQ.SIGN_STS);
        CEP.TRC(SCCGWA, BPCFTLSQ.TLR_BR);
        S000_CALL_BPZSTLSQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLSQ.CNT);
        if (BPCFTLSQ.CNT != 0) {
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_SIGNON;
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B020_02_CHECK_BALANCE() throws IOException,SQLException,Exception {
        WS_TEMP_VARIABLE.WS_CNT = 0;
        IBS.init(SCCGWA, AICPZMIB);
        AICPZMIB.BR = BPCSFORG.BR;
        CEP.TRC(SCCGWA, AICPZMIB.BR);
        S000_CALL_AIZPZMIB();
        if (pgmRtn) return;
        if (AICPZMIB.FLG == 'Y') {
            IBS.init(SCCGWA, BPRMSG);
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ZERO_BALANCE_EXIST;
            WS_TEMP_VARIABLE.WS_CNT = 1;
            while (WS_TEMP_VARIABLE.WS_CNT <= AICPZMIB.CNT 
                && WS_TEMP_VARIABLE.WS_CNT <= 30) {
                WS_ERROR_INFO.WS_AC_INFO[WS_TEMP_VARIABLE.WS_CNT-1].WS_AC = "AC:";
                WS_ERROR_INFO.WS_AC_INFO[WS_TEMP_VARIABLE.WS_CNT-1].WS_AC_NO = AICPZMIB.OUT_DATA.OUT_OCCURS.get(WS_TEMP_VARIABLE.WS_CNT-1).AC_NO;
                if (WS_TEMP_VARIABLE.WS_CNT == 30) {
                    WS_ERROR_INFO.WS_MORE_INFO = " AND MORE...";
                }
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
                CEP.TRC(SCCGWA, AICPZMIB.CNT);
                WS_TEMP_VARIABLE.WS_CNT = WS_TEMP_VARIABLE.WS_CNT + 1;
            }
            WS_INFO = IBS.CLS2CPY(SCCGWA, WS_ERROR_INFO);
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B020_04_CHECK_ACCOUNT() throws IOException,SQLException,Exception {
        WS_INFO = " ";
        IBS.init(SCCGWA, AICPGINF);
        AICPGINF.BR = BPCSFORG.BR;
        CEP.TRC(SCCGWA, AICPGINF.BR);
        S000_CALL_AIZPGINF();
        if (pgmRtn) return;
    }
    public void B020_05_CHECK_BV_ONWAY() throws IOException,SQLException,Exception {
        WS_INFO = " ";
        IBS.init(SCCGWA, BPCPBVBR);
        BPCPBVBR.BR = BPCSFORG.BR;
        S000_CALL_BPZPBVBR();
        if (pgmRtn) return;
        if (BPCPBVBR.DL_BVOW_FLG == 'Y') {
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_HAVE_ONWAY;
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
        if (BPCPBVBR.SL_BVOW_FLG == 'Y') {
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_HAVE_ONWAY;
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_CASH_ONWAY() throws IOException,SQLException,Exception {
        WS_INFO = " ";
        IBS.init(SCCGWA, BPCPCSBR);
        BPCPCSBR.BR = BPCSFORG.BR;
        S000_CALL_BPZPCSBR();
        if (pgmRtn) return;
    }
    public void B030_FORCIBLY_CLOSE_ORG() throws IOException,SQLException,Exception {
        B030_01_FORCIBLY_SIGNOFF_TLR();
        if (pgmRtn) return;
        BPRORGS.CLS_CNT = BPRORGS.CLS_CNT + 1;
        BPRORGS.CLS_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRORGS.LAS_ACDT = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGS.STS = K_STS_FORCE;
        BPCRMOGS.FUNC = 'U';
        BPCRMOGS.DATA_LEN = 39;
        BPCRMOGS.POINTER = BPRORGS;
        S000_CALL_BPZTMOGS();
        if (pgmRtn) return;
    }
    public void B030_01_FORCIBLY_SIGNOFF_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTSGM);
        BPCFTSGM.TLR_BR = BPCSFORG.BR;
        S000_CALL_BPZFTSGM();
        if (pgmRtn) return;
    }
    public void B040_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRORGS;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "PD08";
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = BPROLDS;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRORGS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = WS_ERR_MSG;
        SCCMSG.TYPE = WS_MSG_TYPE;
        SCCMSG.INFO = WS_INFO;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {

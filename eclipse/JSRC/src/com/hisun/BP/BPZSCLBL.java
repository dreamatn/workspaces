package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCLBL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MAINT_CLIB = "BP-R-ADW-CLIB";
    String CPN_R_MAINT_CLBI = "BP-R-ADW-CLBI";
    String CPN_R_BRE_CLBI = "BP-R-BRE-CLBI    ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_BRE_CLIB = "BP-R-BRE-CLIB       ";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    int K_MAX_PAR_CNT = 30;
    char K_FILLER_X01 = 0X01;
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    char K_STSW_FLG_Y = '1';
    String K_HIS_REMARKS = "CASH CLOSING/BALANCING";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSCLBL_WS_VB_DETAIL WS_VB_DETAIL = new BPZSCLBL_WS_VB_DETAIL();
    char WS_CLIB_BAL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRCLIB BPROCLIB = new BPRCLIB();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    BPRCLBI BPRCLBI = new BPRCLBI();
    BPCTLBIB BPCTLBIB = new BPCTLBIB();
    BPCTLBIF BPCTLBIF = new BPCTLBIF();
    BPCTLIBB BPCTLIBB = new BPCTLIBB();
    BPCCLBLO BPCCLBLO = new BPCCLBLO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRINVT BPRINVT = new BPRINVT();
    BPCTINVT BPCTINVT = new BPCTINVT();
    SCCGWA SCCGWA;
    BPCSCLBL BPCSCLBL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSCLBL BPCSCLBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCLBL = BPCSCLBL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCLBL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCLBL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCLBL);
        CEP.TRC(SCCGWA, BPCSCLBL.PAR_INFO1[1-1].PAR_VAL1);
        CEP.TRC(SCCGWA, BPCSCLBL.PAR_INFO1[1-1].PAR_NUM1);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPCSCLBL.FUN == '0') {
                B010_01_OUTPUT_TITLE();
                if (pgmRtn) return;
                B010_01_BROWSE_BOX_OR_LIB_F_CN();
                if (pgmRtn) return;
            } else if (BPCSCLBL.FUN == '1') {
                B020_MODIFY_CLIB_CLBI_FOR_CN();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (BPCSCLBL.FUN == '0') {
                B010_01_OUTPUT_TITLE();
                if (pgmRtn) return;
                B010_01_BROWSE_BOX_OR_LIB();
                if (pgmRtn) return;
            } else if (BPCSCLBL.FUN == '1') {
                B020_MODIFY_CLIB_CLBI();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_MODIFY_CLIB_CLBI_FOR_CN() throws IOException,SQLException,Exception {
        if (BPCSCLBL.INVNTYP == '2') {
            B020_PROCESS_BPTCLIB_FOR_CN();
            if (pgmRtn) return;
        }
        B030_PROCESS_BPTINVT_FOR_CN();
        if (pgmRtn) return;
        B040_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B030_PROCESS_BPTINVT_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTINVT);
        IBS.init(SCCGWA, BPRINVT);
        BPRINVT.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (SCCGWA.COMM_AREA.VCH_NO.trim().length() == 0) BPRINVT.KEY.JRNNO = 0;
        else BPRINVT.KEY.JRNNO = Long.parseLong(SCCGWA.COMM_AREA.VCH_NO);
        BPRINVT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRINVT.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRINVT.TX_CODE = BPCSCLBL.TX_CODE;
        BPRINVT.CB_TYP = '0';
        BPRINVT.INVNTYP = BPCSCLBL.INVNTYP;
        BPRINVT.VB_FLG = BPCSCLBL.VB_FLG;
        BPRINVT.TLR_D = BPCSCLBL.TLR;
        if (BPCSCLBL.INVNTYP == '0') {
            BPRINVT.INVNTY = BPCSCLBL.INVNTY;
            BPRINVT.HANDLER = BPCSCLBL.HANDLER;
            BPRINVT.INTY_NM = BPCSCLBL.INTY_NM;
            BPRINVT.HLD_NM = BPCSCLBL.HLD_NM;
        }
        BPRINVT.STS = BPCSCLBL.STS;
        WS_CNT = 0;
        for (WS_CNT = 1; WS_CNT <= 200; WS_CNT += 1) {
            BPRINVT.REDEFINES28.FILLER2.REC_DATA1[WS_CNT-1].MACH_PVAL = 0;
            BPRINVT.REDEFINES28.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRINVT.REDEFINES28.FILLER2);
            BPRINVT.REDEFINES28.FILLER2.REC_DATA1[WS_CNT-1].ACTU_CASH_NUM = 0;
            BPRINVT.REDEFINES28.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRINVT.REDEFINES28.FILLER2);
        }
        WS_CNT = 0;
        for (WS_CNT = 1; WS_CNT <= K_MAX_PAR_CNT; WS_CNT += 1) {
            BPRINVT.REDEFINES28.FILLER2.REC_DATA1[WS_CNT-1].MACH_PVAL = BPCSCLBL.PAR_INFO1[WS_CNT-1].PAR_VAL1;
            BPRINVT.REDEFINES28.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRINVT.REDEFINES28.FILLER2);
            BPRINVT.REDEFINES28.FILLER2.REC_DATA1[WS_CNT-1].ACTU_CASH_NUM = BPCSCLBL.PAR_INFO1[WS_CNT-1].PAR_NUM1;
            BPRINVT.REDEFINES28.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRINVT.REDEFINES28.FILLER2);
        }
        BPRINVT.BLOB_VAL = IBS.CLS2CPY(SCCGWA, BPRINVT.REDEFINES28);
        BPRINVT.PLBOX_TYPE = BPCSCLBL.PLBOX_TP;
        BPRINVT.PLBOX_NO = BPCSCLBL.PLBOX_NO;
        BPRINVT.CCY = BPCSCLBL.CCY;
        BPRINVT.MACH_AMT = BPCSCLBL.AMT;
        BPRINVT.ACTU_AMT = BPCSCLBL.ACTU_AMT;
        BPRINVT.LAST_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRINVT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRINVT.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = BPRINVT.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRINVT.TS = "0" + BPRINVT.TS;
        BPCTINVT.INFO.FUNC = '5';
        BPCTINVT.POINTER = BPRINVT;
        BPCTINVT.LEN = 409;
        S000_CALL_BPZRINVT();
        if (pgmRtn) return;
        if (BPCTINVT.RETURN_INFO == 'D') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST);
        }
    }
    public void S000_CALL_BPZRINVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-PROC-INVT", BPCTINVT);
        if (BPCTINVT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTINVT.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_MODIFY_CLIB_CLBI() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "aaa");
        CEP.TRC(SCCGWA, BPCSCLBL.PAR_INFO1[1-1].PAR_VAL1);
        CEP.TRC(SCCGWA, BPCSCLBL.PAR_INFO1[1-1].PAR_NUM1);
        B020_PROCESS_BPTCLIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "bbb");
        CEP.TRC(SCCGWA, BPCSCLBL.PAR_INFO1[1-1].PAR_VAL1);
        CEP.TRC(SCCGWA, BPCSCLBL.PAR_INFO1[1-1].PAR_NUM1);
        B040_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_01_BROWSE_BOX_OR_LIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        IBS.init(SCCGWA, BPRCLIB);
        BPCFTLRQ.INFO.TLR = BPCSCLBL.TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCSCLBL.PLBOX_TP == '3') {
            IBS.init(SCCGWA, BPCTLVBF);
            IBS.init(SCCGWA, BPRTLVB);
            BPRTLVB.PLBOX_TP = BPCSCLBL.PLBOX_TP;
            BPRTLVB.CRNT_TLR = BPCSCLBL.TLR;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
            BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            if (BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B010_01_BROWSE_BOX_LIB_DETAIL();
            if (pgmRtn) return;
        }
        if (BPCSCLBL.PLBOX_TP == '1' 
            || BPCSCLBL.PLBOX_TP == '2') {
            IBS.init(SCCGWA, BPCTLVBF);
            IBS.init(SCCGWA, BPRTLVB);
            BPRTLVB.PLBOX_TP = BPCSCLBL.PLBOX_TP;
            BPRTLVB.CRNT_TLR = BPCSCLBL.TLR;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
            BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            if (BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B010_01_BROWSE_BOX_LIB_DETAIL();
            if (pgmRtn) return;
        }
    }
    public void B010_01_BROWSE_BOX_OR_LIB_F_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        if (BPCSCLBL.PLBOX_TP == '3') {
            IBS.init(SCCGWA, BPCTLVBF);
            IBS.init(SCCGWA, BPRTLVB);
            BPRTLVB.PLBOX_TP = BPCSCLBL.PLBOX_TP;
            BPRTLVB.CRNT_TLR = BPCSCLBL.TLR;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
            BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            if (BPCTLVBF.RETURN_INFO == 'N' 
                || BPRTLVB.KEY.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B010_01_BROWSE_BOX_LIB_DETAIL();
            if (pgmRtn) return;
        }
        if (BPCSCLBL.PLBOX_TP == '1' 
            || BPCSCLBL.PLBOX_TP == '2' 
            || BPCSCLBL.PLBOX_TP == '5') {
            IBS.init(SCCGWA, BPCTLVBF);
            IBS.init(SCCGWA, BPRTLVB);
            BPRTLVB.PLBOX_TP = BPCSCLBL.PLBOX_TP;
            BPRTLVB.CRNT_TLR = BPCSCLBL.TLR;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
            BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            if (BPCTLVBF.RETURN_INFO == 'N' 
                || BPRTLVB.KEY.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B010_01_BROWSE_BOX_LIB_DETAIL();
            if (pgmRtn) return;
        }
    }
    public void B010_01_BROWSE_BOX_LIB_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLIBB);
        IBS.init(SCCGWA, WS_VB_DETAIL);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
        CEP.TRC(SCCGWA, BPRCLIB);
        BPCTLIBB.INFO.FUNC = '2';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
        BPCTLIBB.INFO.FUNC = 'N';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND) 
            && SCCMPAG.FUNC != 'E') {
            T000_TRANSFER_DATA();
            if (pgmRtn) return;
            B010_04_OUTPUT_DETAIL();
            if (pgmRtn) return;
            WS_CNT += 1;
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_VB_DETAIL.WS_VB_FLG);
            CEP.TRC(SCCGWA, WS_VB_DETAIL.WS_VB_CCY);
            CEP.TRC(SCCGWA, WS_VB_DETAIL.WS_VB_BAL_FLG);
            CEP.TRC(SCCGWA, WS_VB_DETAIL.WS_VB_BR);
            CEP.TRC(SCCGWA, WS_VB_DETAIL.WS_VB_TLR);
        }
        BPCTLIBB.INFO.FUNC = 'E';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
    }
    public void T000_TRANSFER_DATA() throws IOException,SQLException,Exception {
        WS_VB_DETAIL.WS_PLBOX_NO = BPRCLIB.KEY.PLBOX_NO;
        WS_VB_DETAIL.WS_VB_FLG = BPRCLIB.PLBOX_TP;
        WS_VB_DETAIL.WS_VB_BR = BPRCLIB.KEY.BR;
        WS_VB_DETAIL.WS_VB_TLR = BPRTLVB.CRNT_TLR;
        WS_VB_DETAIL.WS_CASH_TYP = BPRCLIB.KEY.CASH_TYP;
        WS_VB_DETAIL.WS_VB_BAL_FLG = BPRCLIB.BAL_FLG;
        WS_VB_DETAIL.WS_VB_CCY = BPRCLIB.KEY.CCY;
    }
    public void B010_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 27;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_04_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_VB_DETAIL);
        SCCMPAG.DATA_LEN = 27;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VB_DETAIL);
    }
    public void B020_PROCESS_BPTCLIB_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPRCLIB);
        BPRCLIB.KEY.BR = BPCSCLBL.BRANCH;
        BPRCLIB.KEY.PLBOX_NO = BPCSCLBL.PLBOX_NO;
        BPRCLIB.KEY.CASH_TYP = BPCSCLBL.CASH_TYP;
        BPRCLIB.KEY.CCY = BPCSCLBL.CCY;
        BPCTLIBF.INFO.FUNC = 'R';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRCLIB.BAL > BPRCLIB.LMT_U 
            && BPCSCLBL.VB_FLG == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.M_OVER_BAL_A;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRCLIB.BAL < BPRCLIB.LMT_L) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.M_LESS_BAL_W;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCTLIBF.RC);
        BPRCLIB.BAL_FLG = 'Y';
        BPCTLIBF.INFO.FUNC = 'U';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRCLIB.PLBOX_TP != '1' 
            && BPRCLIB.PLBOX_TP != '2' 
            && BPRCLIB.PLBOX_TP != '5') {
            WS_CLIB_BAL_FLAG = 'Y';
            IBS.init(SCCGWA, BPCTLIBB);
            IBS.init(SCCGWA, BPRCLIB);
            BPRCLIB.KEY.BR = BPCSCLBL.BRANCH;
            BPRCLIB.KEY.PLBOX_NO = BPCSCLBL.PLBOX_NO;
            BPCTLIBB.INFO.FUNC = '2';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            if (pgmRtn) return;
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
                if (BPRCLIB.BAL_FLG == 'N') {
                    WS_CLIB_BAL_FLAG = 'N';
                }
                BPCTLIBB.INFO.FUNC = 'N';
                BPCTLIBB.POINTER = BPRCLIB;
                BPCTLIBB.LEN = 352;
                S000_CALL_BPZTLIBB();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCTLIBB.RC);
            }
            BPCTLIBB.INFO.FUNC = 'E';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            if (pgmRtn) return;
            if (WS_CLIB_BAL_FLAG == 'Y') {
                IBS.init(SCCGWA, BPRCLIB);
                IBS.init(SCCGWA, BPRTLVB);
                BPRTLVB.KEY.BR = BPCSCLBL.BRANCH;
                BPRTLVB.KEY.PLBOX_NO = BPCSCLBL.PLBOX_NO;
                BPCTLVBF.INFO.FUNC = 'R';
                BPCTLVBF.POINTER = BPRTLVB;
                BPCTLVBF.LEN = 187;
                S000_CALL_BPZTLVBF();
                if (pgmRtn) return;
                if (BPCTLVBF.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, BPRTLVB.BIND_TYPE);
                if (BPRTLVB.BIND_TYPE == 'N') {
                    CEP.TRC(SCCGWA, "DEV");
                    BPRTLVB.LAST_TLR = BPRTLVB.CRNT_TLR;
                    if (BPRTLVB.PLBOX_TP != '6') {
                        BPRTLVB.CRNT_TLR = " ";
                    } else {
                        if (BPCSCLBL.TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR)) {
                            BPRTLVB.CRNT_TLR = " ";
                        }
                        if (BPCSCLBL.TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR1)) {
                            BPRTLVB.CRNT_TLR1 = " ";
                        }
                        if (BPCSCLBL.TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR2)) {
                            BPRTLVB.CRNT_TLR2 = " ";
                        }
                        if (BPCSCLBL.TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR3)) {
                            BPRTLVB.CRNT_TLR3 = " ";
                        }
                        if (BPCSCLBL.TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR4)) {
                            BPRTLVB.CRNT_TLR4 = " ";
                        }
                    }
                    BPRTLVB.RLTD_FLG = 'Y';
                    BPRTLVB.LAST_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRTLVB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPCTLVBF.INFO.FUNC = 'U';
                    BPCTLVBF.POINTER = BPRTLVB;
                    BPCTLVBF.LEN = 187;
                    S000_CALL_BPZTLVBF();
                    if (pgmRtn) return;
                    if (BPCTLVBF.RETURN_INFO != 'F') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
                CEP.TRC(SCCGWA, BPRTLVB.RLTD_FLG);
            }
        }
    }
    public void B020_PROCESS_BPTCLIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPRCLIB);
        BPRCLIB.KEY.BR = BPCSCLBL.BRANCH;
        BPRCLIB.KEY.PLBOX_NO = BPCSCLBL.PLBOX_NO;
        BPRCLIB.KEY.CASH_TYP = BPCSCLBL.CASH_TYP;
        BPRCLIB.KEY.CCY = BPCSCLBL.CCY;
        CEP.TRC(SCCGWA, BPCSCLBL.VB_FLG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, BPRCLIB);
        BPCTLIBF.INFO.FUNC = 'R';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCTLIBF.RC);
        BPRCLIB.BAL_FLG = 'Y';
        BPCTLIBF.INFO.FUNC = 'U';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_PROCESS_BPTCLBI_FOR_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCLBL.GD_AMT);
        CEP.TRC(SCCGWA, BPCSCLBL.BD_AMT);
        CEP.TRC(SCCGWA, BPCSCLBL.HBD_AMT);
        if (BPCSCLBL.GD_AMT > 0) {
            for (WS_CNT = 1; BPCSCLBL.PAR_INFO1[WS_CNT-1].PAR_VAL1 != 0 
                && WS_CNT <= K_MAX_PAR_CNT; WS_CNT += 1) {
                IBS.init(SCCGWA, BPRCLBI);
                IBS.init(SCCGWA, BPCTLBIF);
                BPRCLBI.KEY.VB_BR = BPCSCLBL.BRANCH;
                BPRCLBI.KEY.PLBOX_NO = BPCSCLBL.PLBOX_NO;
                BPRCLBI.KEY.CASH_TYP = BPCSCLBL.CASH_TYP;
                BPRCLBI.KEY.CCY = BPCSCLBL.CCY;
                BPRCLBI.KEY.PAR_VAL = BPCSCLBL.PAR_INFO1[WS_CNT-1].PAR_VAL1;
                BPRCLBI.KEY.M_FLG = BPCSCLBL.PAR_INFO1[WS_CNT-1].M_FLG1;
                BPCTLBIF.INFO.FUNC = 'Q';
                BPCTLBIF.POINTER = BPRCLBI;
                BPCTLBIF.LEN = 222;
                S000_CALL_BPZTLBIF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCSCLBL.PAR_INFO1[WS_CNT-1].PAR_NUM1);
                CEP.TRC(SCCGWA, BPRCLBI.GD_NUM);
                if (BPCTLBIF.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_THIS_PVAL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    if (BPCSCLBL.PAR_INFO1[WS_CNT-1].PAR_NUM1 != BPRCLBI.GD_NUM) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.M_UNRIGHT_PAR_E;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (BPCSCLBL.BD_AMT > 0) {
            for (WS_CNT = 1; BPCSCLBL.PAR_INFO2[WS_CNT-1].PAR_VAL2 != 0 
                && WS_CNT <= K_MAX_PAR_CNT; WS_CNT += 1) {
                IBS.init(SCCGWA, BPRCLBI);
                IBS.init(SCCGWA, BPCTLBIF);
                BPRCLBI.KEY.VB_BR = BPCSCLBL.BRANCH;
                BPRCLBI.KEY.PLBOX_NO = BPCSCLBL.PLBOX_NO;
                BPRCLBI.KEY.CASH_TYP = BPCSCLBL.CASH_TYP;
                BPRCLBI.KEY.CCY = BPCSCLBL.CCY;
                BPRCLBI.KEY.PAR_VAL = BPCSCLBL.PAR_INFO2[WS_CNT-1].PAR_VAL2;
                BPRCLBI.KEY.M_FLG = BPCSCLBL.PAR_INFO2[WS_CNT-1].M_FLG2;
                BPCTLBIF.INFO.FUNC = 'Q';
                BPCTLBIF.POINTER = BPRCLBI;
                BPCTLBIF.LEN = 222;
                S000_CALL_BPZTLBIF();
                if (pgmRtn) return;
                if (BPCTLBIF.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_THIS_PVAL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    if (BPCSCLBL.PAR_INFO2[WS_CNT-1].PAR_NUM2 != BPRCLBI.BD_NUM) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.M_UNRIGHT_PAR_E;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (BPCSCLBL.HBD_AMT > 0) {
            for (WS_CNT = 1; BPCSCLBL.PAR_INFO3[WS_CNT-1].PAR_VAL3 != 0 
                && WS_CNT <= K_MAX_PAR_CNT; WS_CNT += 1) {
                IBS.init(SCCGWA, BPRCLBI);
                IBS.init(SCCGWA, BPCTLBIF);
                BPRCLBI.KEY.VB_BR = BPCSCLBL.BRANCH;
                BPRCLBI.KEY.PLBOX_NO = BPCSCLBL.PLBOX_NO;
                BPRCLBI.KEY.CASH_TYP = BPCSCLBL.CASH_TYP;
                BPRCLBI.KEY.CCY = BPCSCLBL.CCY;
                BPRCLBI.KEY.PAR_VAL = BPCSCLBL.PAR_INFO3[WS_CNT-1].PAR_VAL3;
                BPRCLBI.KEY.M_FLG = BPCSCLBL.PAR_INFO3[WS_CNT-1].M_FLG3;
                BPCTLBIF.INFO.FUNC = 'Q';
                BPCTLBIF.POINTER = BPRCLBI;
                BPCTLBIF.LEN = 222;
                S000_CALL_BPZTLBIF();
                if (pgmRtn) return;
                if (BPCTLBIF.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_THIS_PVAL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    if (BPCSCLBL.PAR_INFO3[WS_CNT-1].PAR_NUM3 != BPRCLBI.HBD_NUM) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.M_UNRIGHT_PAR_E;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B030_PROCESS_BPTCLBI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLBIF);
        IBS.init(SCCGWA, BPCTLBIB);
        IBS.init(SCCGWA, BPRCLBI);
        BPRCLBI.KEY.VB_BR = BPCSCLBL.BRANCH;
        BPRCLBI.KEY.PLBOX_NO = BPCSCLBL.PLBOX_NO;
        BPRCLBI.KEY.CASH_TYP = BPCSCLBL.CASH_TYP;
        BPRCLBI.KEY.CCY = BPCSCLBL.CCY;
        BPCTLBIB.INFO.FUNC = '1';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCLBI.KEY.M_FLG);
        IBS.init(SCCGWA, BPCTLBIB.RC);
        BPCTLBIB.INFO.FUNC = 'N';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TLBIB-READNEXT");
        for (WS_CNT = 1; BPCTLBIB.RETURN_INFO != 'N' 
            && WS_CNT <= K_MAX_PAR_CNT; WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPRCLBI.KEY.VB_BR);
            CEP.TRC(SCCGWA, BPRCLBI.KEY.CCY);
            CEP.TRC(SCCGWA, BPRCLBI.KEY.PAR_VAL);
            CEP.TRC(SCCGWA, BPRCLBI.KEY.M_FLG);
            CEP.TRC(SCCGWA, BPRCLBI);
            CEP.TRC(SCCGWA, "------ DELETE RECORD---");
            BPCTLBIB.INFO.FUNC = 'D';
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
        }
        IBS.init(SCCGWA, BPCTLBIB.RC);
        BPCTLBIB.INFO.FUNC = 'E';
        BPCTLBIB.POINTER = BPRCLBI;
        BPCTLBIB.LEN = 222;
        S000_CALL_BPZTLBIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCLBI.KEY.CCY);
        CEP.TRC(SCCGWA, WS_CNT);
        if (BPCSCLBL.GD_AMT > 0) {
            for (WS_CNT = 1; (BPCSCLBL.PAR_INFO1[WS_CNT-1].PAR_VAL1 != 0) 
                && WS_CNT <= K_MAX_PAR_CNT; WS_CNT += 1) {
                IBS.init(SCCGWA, BPRCLBI);
                IBS.init(SCCGWA, BPCTLBIF);
                CEP.TRC(SCCGWA, "IN GOOD AMT");
                CEP.TRC(SCCGWA, BPCSCLBL.PAR_INFO1[WS_CNT-1].PAR_VAL1);
                CEP.TRC(SCCGWA, WS_CNT);
                BPRCLBI.KEY.VB_BR = BPCSCLBL.BRANCH;
                BPRCLBI.KEY.PLBOX_NO = BPCSCLBL.PLBOX_NO;
                BPRCLBI.KEY.CASH_TYP = BPCSCLBL.CASH_TYP;
                BPRCLBI.KEY.CCY = BPCSCLBL.CCY;
                BPRCLBI.KEY.PAR_VAL = BPCSCLBL.PAR_INFO1[WS_CNT-1].PAR_VAL1;
                BPRCLBI.GD_NUM = BPCSCLBL.PAR_INFO1[WS_CNT-1].PAR_NUM1;
                BPRCLBI.KEY.M_FLG = BPCSCLBL.PAR_INFO1[WS_CNT-1].M_FLG1;
                BPCTLBIF.POINTER = BPRCLBI;
                BPCTLBIF.LEN = 222;
                BPCTLBIF.INFO.FUNC = 'A';
                CEP.TRC(SCCGWA, BPRCLBI);
                S000_CALL_BPZTLBIF();
                if (pgmRtn) return;
                if (BPCTLBIF.RETURN_INFO == 'D') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (BPCSCLBL.BD_AMT > 0) {
            for (WS_CNT = 1; (BPCSCLBL.PAR_INFO2[WS_CNT-1].PAR_VAL2 != 0) 
                && WS_CNT <= K_MAX_PAR_CNT; WS_CNT += 1) {
                IBS.init(SCCGWA, BPRCLBI);
                IBS.init(SCCGWA, BPCTLBIF);
                CEP.TRC(SCCGWA, "IN BAD AMT");
                CEP.TRC(SCCGWA, BPCSCLBL.BD_AMT);
                CEP.TRC(SCCGWA, BPCSCLBL.PAR_INFO2[WS_CNT-1].PAR_VAL2);
                CEP.TRC(SCCGWA, BPCSCLBL.PAR_INFO2[WS_CNT-1].PAR_NUM2);
                CEP.TRC(SCCGWA, WS_CNT);
                BPRCLBI.KEY.VB_BR = 0;
                BPRCLBI.KEY.VB_BR = BPCSCLBL.BRANCH;
                BPRCLBI.KEY.PLBOX_NO = BPCSCLBL.PLBOX_NO;
                BPRCLBI.KEY.CASH_TYP = BPCSCLBL.CASH_TYP;
                BPRCLBI.KEY.CCY = BPCSCLBL.CCY;
                BPRCLBI.KEY.PAR_VAL = BPCSCLBL.PAR_INFO2[WS_CNT-1].PAR_VAL2;
                BPRCLBI.BD_NUM = BPCSCLBL.PAR_INFO2[WS_CNT-1].PAR_NUM2;
                BPRCLBI.KEY.M_FLG = BPCSCLBL.PAR_INFO2[WS_CNT-1].M_FLG2;
                BPCTLBIF.POINTER = BPRCLBI;
                BPCTLBIF.LEN = 222;
                BPCTLBIF.INFO.FUNC = 'R';
                S000_CALL_BPZTLBIF();
                if (pgmRtn) return;
                if (BPCTLBIF.RC.RC_CODE == 0) {
                    BPRCLBI.BD_NUM = BPCSCLBL.PAR_INFO2[WS_CNT-1].PAR_NUM2;
                    CEP.TRC(SCCGWA, "IN BD NUM MODIFY");
                    CEP.TRC(SCCGWA, BPRCLBI.BD_NUM);
                    BPCTLBIF.POINTER = BPRCLBI;
                    BPCTLBIF.LEN = 222;
                    BPCTLBIF.INFO.FUNC = 'U';
                    S000_CALL_BPZTLBIF();
                    if (pgmRtn) return;
                } else {
                    BPRCLBI.BD_NUM = BPCSCLBL.PAR_INFO2[WS_CNT-1].PAR_NUM2;
                    CEP.TRC(SCCGWA, BPRCLBI.BD_NUM);
                    BPCTLBIF.POINTER = BPRCLBI;
                    BPCTLBIF.LEN = 222;
                    BPCTLBIF.INFO.FUNC = 'A';
                    S000_CALL_BPZTLBIF();
                    if (pgmRtn) return;
                }
            }
        }
        if (BPCSCLBL.HBD_AMT > 0) {
            for (WS_CNT = 1; (BPCSCLBL.PAR_INFO3[WS_CNT-1].PAR_VAL3 != 0) 
                && WS_CNT <= K_MAX_PAR_CNT; WS_CNT += 1) {
                IBS.init(SCCGWA, BPRCLBI);
                IBS.init(SCCGWA, BPCTLBIF);
                CEP.TRC(SCCGWA, "IN HALF BAD AMT");
                CEP.TRC(SCCGWA, BPCSCLBL.PAR_INFO3[WS_CNT-1].PAR_VAL3);
                CEP.TRC(SCCGWA, WS_CNT);
                BPRCLBI.KEY.VB_BR = 0;
                BPRCLBI.KEY.VB_BR = BPCSCLBL.BRANCH;
                BPRCLBI.KEY.PLBOX_NO = BPCSCLBL.PLBOX_NO;
                BPRCLBI.KEY.CASH_TYP = BPCSCLBL.CASH_TYP;
                BPRCLBI.KEY.CCY = BPCSCLBL.CCY;
                BPRCLBI.KEY.PAR_VAL = BPCSCLBL.PAR_INFO3[WS_CNT-1].PAR_VAL3;
                BPRCLBI.HBD_NUM = BPCSCLBL.PAR_INFO3[WS_CNT-1].PAR_NUM3;
                BPRCLBI.KEY.M_FLG = BPCSCLBL.PAR_INFO3[WS_CNT-1].M_FLG3;
                BPCTLBIF.POINTER = BPRCLBI;
                BPCTLBIF.LEN = 222;
                BPCTLBIF.INFO.FUNC = 'R';
                S000_CALL_BPZTLBIF();
                if (pgmRtn) return;
                if (BPCTLBIF.RC.RC_CODE == 0) {
                    BPRCLBI.HBD_NUM = BPCSCLBL.PAR_INFO3[WS_CNT-1].PAR_NUM3;
                    CEP.TRC(SCCGWA, "IN BD NUM MODIFY");
                    CEP.TRC(SCCGWA, BPRCLBI.HBD_NUM);
                    BPCTLBIF.POINTER = BPRCLBI;
                    BPCTLBIF.LEN = 222;
                    BPCTLBIF.INFO.FUNC = 'U';
                    S000_CALL_BPZTLBIF();
                    if (pgmRtn) return;
                } else {
                    BPRCLBI.HBD_NUM = BPCSCLBL.PAR_INFO3[WS_CNT-1].PAR_NUM3;
                    CEP.TRC(SCCGWA, BPRCLBI.HBD_NUM);
                    BPCTLBIF.POINTER = BPRCLBI;
                    BPCTLBIF.LEN = 222;
                    BPCTLBIF.INFO.FUNC = 'A';
                    S000_CALL_BPZTLBIF();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCLBLO);
        BPCCLBLO.TLR = BPCSCLBL.TLR;
        BPCCLBLO.CASH_TYP = BPCSCLBL.CASH_TYP;
        BPCCLBLO.CCY = BPCSCLBL.CCY;
        BPCCLBLO.PLBOX_NO = BPCSCLBL.PLBOX_NO;
        BPCCLBLO.VB_FLG = BPCSCLBL.VB_FLG;
        BPCCLBLO.GD_AMT = BPCSCLBL.GD_AMT;
        BPCCLBLO.FIL1 = K_FILLER_X01;
        BPCCLBLO.BD_AMT = BPCSCLBL.BD_AMT;
        BPCCLBLO.FIL2 = K_FILLER_X01;
        BPCCLBLO.HBD_AMT = BPCSCLBL.HBD_AMT;
        BPCCLBLO.FIL3 = K_FILLER_X01;
        BPCCLBLO.L_AMT = BPCSCLBL.L_AMT;
        BPCCLBLO.FIL4 = K_FILLER_X01;
        BPCCLBLO.AMT_C = BPCSCLBL.AMT_C;
        BPCCLBLO.FIL5 = K_FILLER_X01;
        BPCCLBLO.AMT_D = BPCSCLBL.AMT_D;
        BPCCLBLO.FIL6 = K_FILLER_X01;
        BPCCLBLO.BAL = BPCSCLBL.BAL;
        BPCCLBLO.FIL7 = K_FILLER_X01;
        BPCCLBLO.TX_CNT = BPCSCLBL.TX_CNT;
        BPCCLBLO.MACH_AMT = BPCSCLBL.AMT;
        BPCCLBLO.ACTU_AMT = BPCSCLBL.ACTU_AMT;
        BPCCLBLO.STS = BPCSCLBL.STS;
        BPCCLBLO.INVNTYP = BPCSCLBL.INVNTYP;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSCLBL.OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCSCLBL.OUTPUT_FMT);
        SCCFMT.DATA_PTR = BPCCLBLO;
        SCCFMT.DATA_LEN = 684;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CLIB, BPCTLIBF);
        CEP.TRC(SCCGWA, BPCTLIBF.RC);
    }
    public void S000_CALL_BPZTLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_CLIB, BPCTLIBB);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        if (BPCTLIBB.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTLBIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CLBI, BPCTLBIF);
        CEP.TRC(SCCGWA, BPCTLBIF.RC);
        CEP.TRC(SCCGWA, BPCTLBIF.RETURN_INFO);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
        if (BPCTLBIF.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLBI_NOTFND)) {
            CEP.TRC(SCCGWA, "ERROR");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTLBIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_CLBI, BPCTLBIB);
        CEP.TRC(SCCGWA, BPCTLBIB.RC);
        CEP.TRC(SCCGWA, BPCTLBIB.RETURN_INFO);
        if (BPCTLBIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLBIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
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

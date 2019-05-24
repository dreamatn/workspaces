package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2203 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTTLVB_RD;
    brParm BPTCLIB_BR = new brParm();
    boolean pgmRtn = false;
    String OUTPUT_FMT = "BP124";
    BPOT2203_WS_VARIABLES WS_VARIABLES = new BPOT2203_WS_VARIABLES();
    BPOT2203_WS_TLVB_REC WS_TLVB_REC = new BPOT2203_WS_TLVB_REC();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCGWA SCCGWA;
    BPB2203_AWA_2203 AWA_2203;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    SCCOWA SCCOWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2203 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_2203 = new BPB2203_AWA_2203();
        IBS.init(SCCGWA, AWA_2203);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_2203);
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_TLVB_REC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AWA_2203.BR);
        CEP.TRC(SCCGWA, AWA_2203.TLR);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AWA_2203.BR == 0 
            || AWA_2203.TLR.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = AWA_2203.BR;
        S000_CHECK_BR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = AWA_2203.TLR;
        S000_CHECK_TLR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.NEW_BR);
        if (BPCFTLRQ.INFO.NEW_BR != AWA_2203.BR) {
            CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR163);
        }
    }
    public void B020_QUERY_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, WS_TLVB_REC);
        BPRTLVB.KEY.BR = AWA_2203.BR;
        BPRTLVB.CRNT_TLR = AWA_2203.TLR;
        S000_READ_BPTTLVB();
        if (pgmRtn) return;
        if (BPRTLVB.PLBOX_TP == '1' 
            || BPRTLVB.PLBOX_TP == '2') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, ERROR_MSG.BP_NOT_CASHLIB_TLR);
            }
        } else {
            if (BPRTLVB.PLBOX_TP == '5') {
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                    || !BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, ERROR_MSG.BP_NOT_CASHBOX_TLR);
                }
            } else {
                if (BPRTLVB.PLBOX_TP == '3' 
                    || BPRTLVB.PLBOX_TP == '4' 
                    || BPRTLVB.PLBOX_TP == '6') {
                    if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                    JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                    if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                        CEP.ERR(SCCGWA, ERROR_MSG.BP_NOT_CASHBOX_TLR);
                    }
                }
            }
        }
        WS_TLVB_REC.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        WS_TLVB_REC.PLBOX_TYPE = BPRTLVB.PLBOX_TP;
        if (WS_TLVB_REC.PLBOX_TYPE == '1' 
            || WS_TLVB_REC.PLBOX_TYPE == '2' 
            || WS_TLVB_REC.PLBOX_TYPE == '5') {
            WS_TLVB_REC.VB_FLG = '1';
        } else {
            if (WS_TLVB_REC.PLBOX_TYPE == '3' 
                || WS_TLVB_REC.PLBOX_TYPE == '4' 
                || WS_TLVB_REC.PLBOX_TYPE == '6') {
                WS_TLVB_REC.VB_FLG = '0';
            }
        }
        CEP.TRC(SCCGWA, WS_TLVB_REC.PLBOX_TYPE);
        CEP.TRC(SCCGWA, WS_TLVB_REC.VB_FLG);
        CEP.TRC(SCCGWA, WS_TLVB_REC.PLBOX_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        WS_VARIABLES.BPTCLIB_FLG = 'Y';
        BPRCLIB.KEY.BR = AWA_2203.BR;
        BPRCLIB.KEY.PLBOX_NO = WS_TLVB_REC.PLBOX_NO;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            S000_STARTBR_BPTCLIB();
            if (pgmRtn) return;
            S000_READNEXT_BPTCLIB();
            if (pgmRtn) return;
            for (WS_VARIABLES.CNT = 1; WS_VARIABLES.BPTCLIB_FLG != 'N' 
                && WS_VARIABLES.CNT <= 20; WS_VARIABLES.CNT += 1) {
                WS_TLVB_REC.WS_CCY_INFO[WS_VARIABLES.CNT-1].CCY = BPRCLIB.KEY.CCY;
                WS_TLVB_REC.WS_CCY_INFO[WS_VARIABLES.CNT-1].AMT = BPRCLIB.BAL;
                CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
                CEP.TRC(SCCGWA, WS_TLVB_REC.WS_CCY_INFO[WS_VARIABLES.CNT-1].CCY);
                CEP.TRC(SCCGWA, WS_TLVB_REC.WS_CCY_INFO[WS_VARIABLES.CNT-1].AMT);
                S000_READNEXT_BPTCLIB();
                if (pgmRtn) return;
            }
            S000_ENDBR_BPTCLIB();
            if (pgmRtn) return;
        }
        TOOO_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void S000_CHECK_BR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CHECK_TLR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY", BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_READ_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.where = "BR = :BPRTLVB.KEY.BR "
            + "AND CRNT_TLR = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR2 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR3 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR4 = :BPRTLVB.CRNT_TLR";
        BPTTLVB_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
    }
    public void S000_STARTBR_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR "
            + "AND PLBOX_NO = :BPRCLIB.KEY.PLBOX_NO";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void S000_READNEXT_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.BPTCLIB_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTCLIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ENDBR_BPTCLIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCLIB_BR);
    }
    public void TOOO_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_TLVB_REC;
        SCCFMT.DATA_LEN = 388;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

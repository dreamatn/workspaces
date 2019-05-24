package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2123 {
    int JIBS_tmp_int;
    DBParm BPTTLVB_RD;
    DBParm BPTBRIS_RD;
    DBParm BPTCLIB_RD;
    DBParm BPTMTPA_RD;
    brParm BPTALIB_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP125";
    String CPN_S_CASHAPP_MAINTAIN = "BP-S-CSHAPP-MAINTAIN";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRMTPA BPRMTPA = new BPRMTPA();
    BPRALIB BPRALIB = new BPRALIB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOLIBB BPCOLIBB = new BPCOLIBB();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRBRIS BPRBRIS = new BPRBRIS();
    BPRCLIB BPRCLIB = new BPRCLIB();
    SCCGWA SCCGWA;
    BPB2123_AWA_2123 BPB2123_AWA_2123;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2123 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2123_AWA_2123>");
        BPB2123_AWA_2123 = (BPB2123_AWA_2123) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2123_AWA_2123);
        CEP.TRC(SCCGWA, BPB2123_AWA_2123.UP_BR);
        CEP.TRC(SCCGWA, BPB2123_AWA_2123.APP_TYPE);
        CEP.TRC(SCCGWA, BPB2123_AWA_2123.APP_DT);
        CEP.TRC(SCCGWA, BPB2123_AWA_2123.APP_AMT);
        CEP.TRC(SCCGWA, BPB2123_AWA_2123.CCY);
        CEP.TRC(SCCGWA, BPB2123_AWA_2123.ALLOT_TP);
        CEP.TRC(SCCGWA, BPB2123_AWA_2123.APP_NOTE);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_ADD_CASH_APP();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR35);
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRALIB);
        BPRALIB.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRALIB.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRALIB.ALLOT_TYPE = BPB2123_AWA_2123.ALLOT_TP;
        TOOO_STARTBR_BPTALIB();
        if (pgmRtn) return;
        TOOO_READNEXT_BPTALIB();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_CNT <= 1) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR153);
            WS_CNT = 2;
            TOOO_READNEXT_BPTALIB();
            if (pgmRtn) return;
        }
        TOOO_ENDBR_BPTALIB();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRMTPA);
        BPRMTPA.KEY.MT_BR = BPB2123_AWA_2123.UP_BR;
        BPRMTPA.KEY.APP_FLG = '1';
        BPRMTPA.MT_FLG = '0';
        TOOO_READ_BPTMTPA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRMTPA.STAT_TM);
        CEP.TRC(SCCGWA, BPRMTPA.END_TM);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (SCCGWA.COMM_AREA.TR_TIME >= BPRMTPA.STAT_TM 
                && SCCGWA.COMM_AREA.TR_TIME <= BPRMTPA.END_TM) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APP_TM_NOT_MTPA);
            }
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_HAS_NO_AUTH);
        }
        if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTMATCH_ORG);
        }
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_READ_BPTTLVB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        if (BPRTLVB.PLBOX_TP == '1' 
            || BPRTLVB.PLBOX_TP == '2' 
            || BPRTLVB.PLBOX_TP == '5') {
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR122);
        }
        IBS.init(SCCGWA, BPRBRIS);
        BPRBRIS.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBRIS.KEY.LMT_CCY = BPB2123_AWA_2123.CCY;
        T000_READ_BPTBRIS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRCLIB);
        BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPRCLIB.KEY.CASH_TYP = "01";
        BPRCLIB.KEY.CCY = BPB2123_AWA_2123.CCY;
        T000_READ_BPTCLIB();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.where = "BR = :BPRTLVB.KEY.BR "
            + "AND ( CRNT_TLR = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR2 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR3 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR4 = :BPRTLVB.CRNT_TLR )";
        BPTTLVB_RD.upd = true;
        BPTTLVB_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR123);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLVB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTBRIS() throws IOException,SQLException,Exception {
        BPTBRIS_RD = new DBParm();
        BPTBRIS_RD.TableName = "BPTBRIS";
        BPTBRIS_RD.where = "BR = :BPRBRIS.KEY.BR "
            + "AND LMT_CCY = :BPRBRIS.KEY.LMT_CCY";
        BPTBRIS_RD.upd = true;
        BPTBRIS_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRBRIS, this, BPTBRIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTBRIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_RD = new DBParm();
        BPTCLIB_RD.TableName = "BPTCLIB";
        BPTCLIB_RD.where = "BR = :BPRCLIB.KEY.BR "
            + "AND PLBOX_NO = :BPRCLIB.KEY.PLBOX_NO "
            + "AND CASH_TYP = :BPRCLIB.KEY.CASH_TYP "
            + "AND CCY = :BPRCLIB.KEY.CCY";
        BPTCLIB_RD.upd = true;
        BPTCLIB_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRCLIB, this, BPTCLIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR125);
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
    public void TOOO_READ_BPTMTPA() throws IOException,SQLException,Exception {
        BPTMTPA_RD = new DBParm();
        BPTMTPA_RD.TableName = "BPTMTPA";
        BPTMTPA_RD.where = "MT_BR = :BPRMTPA.KEY.MT_BR "
            + "AND APP_FLG = :BPRMTPA.KEY.APP_FLG "
            + "AND MT_FLG = :BPRMTPA.MT_FLG";
        BPTMTPA_RD.upd = true;
        BPTMTPA_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRMTPA, this, BPTMTPA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTMTPA";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void TOOO_STARTBR_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_BR.rp = new DBParm();
        BPTALIB_BR.rp.TableName = "BPTALIB";
        BPTALIB_BR.rp.where = "APP_BR = :BPRALIB.APP_BR "
            + "AND ALLOT_TYPE = :BPRALIB.ALLOT_TYPE "
            + "AND APP_DT = :BPRALIB.APP_DT";
        IBS.STARTBR(SCCGWA, BPRALIB, this, BPTALIB_BR);
    }
    public void TOOO_READNEXT_BPTALIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRALIB, this, BPTALIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTALIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void TOOO_ENDBR_BPTALIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTALIB_BR);
    }
    public void B020_ADD_CASH_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLIBB);
        BPCOLIBB.FUNC = 'A';
        BPCOLIBB.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        S000_CALL_BPZSLIBB();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOLIBB.UP_BR = BPB2123_AWA_2123.UP_BR;
        BPCOLIBB.UP_TLR = BPB2123_AWA_2123.UP_TLR;
        BPCOLIBB.APP_TYPE = BPB2123_AWA_2123.APP_TYPE;
        BPCOLIBB.APP_DT = BPB2123_AWA_2123.APP_DT;
        BPCOLIBB.APP_AMT = BPB2123_AWA_2123.APP_AMT;
        BPCOLIBB.APP_NOTE = BPB2123_AWA_2123.APP_NOTE;
        CEP.TRC(SCCGWA, BPCOLIBB.APP_NOTE);
        CEP.TRC(SCCGWA, BPB2123_AWA_2123.APP_AMT);
        CEP.TRC(SCCGWA, BPCOLIBB.APP_AMT);
        BPCOLIBB.CCY = BPB2123_AWA_2123.CCY;
        if (BPB2123_AWA_2123.CCY.trim().length() > 0) {
            BPCOLIBB.CASH_TYP = "01";
        } else {
            BPCOLIBB.CASH_TYP = " ";
        }
        BPCOLIBB.ALLOT_TP = BPB2123_AWA_2123.ALLOT_TP;
        for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
            BPCOLIBB.PVAL_INFO[WS_CNT-1].PVAL = BPB2123_AWA_2123.PVAL_INF[WS_CNT-1].PVAL;
            BPCOLIBB.PVAL_INFO[WS_CNT-1].NUM = BPB2123_AWA_2123.PVAL_INF[WS_CNT-1].NUM;
        }
    }
    public void S000_CALL_BPZSLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-CSHAPP-MAINTAIN", BPCOLIBB);
        CEP.TRC(SCCGWA, BPCOLIBB);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_TLR_INF_QUERY;
        SCCCALL.COMMAREA_PTR = BPCFTLRQ;
        SCCCALL.ERR_FLDNO = SCCGWA.COMM_AREA.TL_ID;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

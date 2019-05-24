package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2065 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTTLVB_RD;
    DBParm BPTCLIB_RD;
    String K_OUTPUT_FMT = "BP116";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOLIBF BPCOLIBF = new BPCOLIBF();
    SCCGWA SCCGWA;
    BPB2065_AWA_2065 BPB2065_AWA_2065;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCOWA SCCOWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2065 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2065_AWA_2065>");
        BPB2065_AWA_2065 = (BPB2065_AWA_2065) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2065_AWA_2065.BR);
        CEP.TRC(SCCGWA, BPB2065_AWA_2065.TLR);
        CEP.TRC(SCCGWA, BPB2065_AWA_2065.CCY);
        B010_CHECK_INPUT();
        B020_QUERY_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB2065_AWA_2065.BR == 0 
            || BPB2065_AWA_2065.TLR.trim().length() == 0 
            || BPB2065_AWA_2065.CCY.equalsIgnoreCase("0")) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB2065_AWA_2065.BR;
        S000_CHECK_BR();
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2065_AWA_2065.TLR;
        S000_CHECK_TLR();
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPB2065_AWA_2065.CCY;
        S000_CHECK_CCY();
    }
    public void B020_QUERY_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = BPB2065_AWA_2065.BR;
        BPRTLVB.CRNT_TLR = BPB2065_AWA_2065.TLR;
        S000_READ_BPTTLVB();
        if (BPRTLVB.PLBOX_TP == '1' 
            || BPRTLVB.PLBOX_TP == '2') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR);
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
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR);
                }
            } else {
                if (BPRTLVB.PLBOX_TP == '3' 
                    || BPRTLVB.PLBOX_TP == '4' 
                    || BPRTLVB.PLBOX_TP == '6') {
                    if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                    JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                    if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR);
                    }
                }
            }
        }
        IBS.init(SCCGWA, BPRCLIB);
        BPRCLIB.KEY.CCY = BPB2065_AWA_2065.CCY;
        S000_READ_BPTCLIB();
        TOOO_OUTPUT_DATA();
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
    public void S000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
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
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR171);
        }
    }
    public void S000_READ_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_RD = new DBParm();
        BPTCLIB_RD.TableName = "BPTCLIB";
        BPTCLIB_RD.where = "BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_NO = :BPRTLVB.KEY.PLBOX_NO "
            + "AND CASH_TYP = '01' "
            + "AND CCY = :BPRCLIB.KEY.CCY";
        BPTCLIB_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRCLIB, this, BPTCLIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND);
        }
    }
    public void TOOO_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLIBF);
        BPCOLIBF.BR = BPRCLIB.KEY.BR;
        BPCOLIBF.TLR = BPB2065_AWA_2065.TLR;
        BPCOLIBF.PLBOX_NO = BPRCLIB.KEY.PLBOX_NO;
        BPCOLIBF.CCY = BPRCLIB.KEY.CCY;
        BPCOLIBF.BAL = BPRCLIB.BAL;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOLIBF;
        SCCFMT.DATA_LEN = 39;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

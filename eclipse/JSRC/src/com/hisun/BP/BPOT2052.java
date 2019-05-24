package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2052 {
    int JIBS_tmp_int;
    DBParm BPTORGR_RD;
    String CPN_S_BRW_CHIB = "BP-S-BRW-CHIS ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TEXT = " ";
    char WS_FOUND1_FLG = ' ';
    char WS_FOUND2_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRORGR BPRORGR = new BPRORGR();
    BPCSCHIB BPCSCHIB = new BPCSCHIB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPB2052_AWA_2052 BPB2052_AWA_2052;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2052 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2052_AWA_2052>");
        BPB2052_AWA_2052 = (BPB2052_AWA_2052) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_RGND_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB2052_AWA_2052.TLR_NO.trim().length() == 0) {
        } else {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB2052_AWA_2052.TLR_NO;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                if (BPB2052_AWA_2052.TLR_NO.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BPB2052_AWA_2052.TLR_NO);
                S000_ERR_MSG_PROC();
            }
            if (BPCFTLRQ.INFO.NEW_BR != BPB2052_AWA_2052.BR) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_BELONG_TO_BR);
            }
        }
        if (BPB2052_AWA_2052.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCFTLCM.BR = BPB2052_AWA_2052.BR;
            S000_CALL_BPZFTLCM();
        }
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_ERR);
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TX_LVL != '0' 
            || BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
        } else {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                if (!SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPB2052_AWA_2052.TLR_NO)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_OLY_QURY_SELF);
                }
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_HAS_NO_AUTH);
            }
        }
        if (BPB2052_AWA_2052.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_FOUND1_FLG = 'N';
            WS_FOUND2_FLG = 'N';
            IBS.init(SCCGWA, BPRORGR);
            BPRORGR.KEY.TYP = "07";
            BPRORGR.KEY.BR = BPB2052_AWA_2052.BR;
            BPRORGR.REL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPTORGR_RD = new DBParm();
            BPTORGR_RD.TableName = "BPTORGR";
            BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
                + "AND BR = :BPRORGR.KEY.BR "
                + "AND REL_BR = :BPRORGR.REL_BR";
            IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_FOUND1_FLG = 'Y';
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            }
            IBS.init(SCCGWA, BPRORGR);
            BPRORGR.KEY.TYP = "07";
            BPRORGR.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRORGR.REL_BR = BPB2052_AWA_2052.BR;
            BPTORGR_RD = new DBParm();
            BPTORGR_RD.TableName = "BPTORGR";
            BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
                + "AND BR = :BPRORGR.KEY.BR "
                + "AND REL_BR = :BPRORGR.REL_BR";
            IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_FOUND2_FLG = 'Y';
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            }
            if (WS_FOUND1_FLG == 'N' 
                && WS_FOUND2_FLG == 'N') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_RLT_BRANCH);
            }
        }
    }
    public void B020_BROWSE_RGND_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCHIB);
        BPCSCHIB.FUNC = 'B';
        BPCSCHIB.BR = BPB2052_AWA_2052.BR;
        BPCSCHIB.TLR = BPB2052_AWA_2052.TLR_NO;
        BPCSCHIB.START_DT = BPB2052_AWA_2052.START_DT;
        BPCSCHIB.END_DT = BPB2052_AWA_2052.END_DT;
        BPCSCHIB.JRN = BPB2052_AWA_2052.JRN;
        BPCSCHIB.CASH_TYP = BPB2052_AWA_2052.CASH_TYP;
        BPCSCHIB.CCY = BPB2052_AWA_2052.CCY;
        BPCSCHIB.CONF_NO = BPB2052_AWA_2052.CONF_NO;
        CEP.TRC(SCCGWA, BPCSCHIB.BR);
        CEP.TRC(SCCGWA, BPCSCHIB.TLR);
        CEP.TRC(SCCGWA, BPCSCHIB.START_DT);
        CEP.TRC(SCCGWA, BPCSCHIB.END_DT);
        CEP.TRC(SCCGWA, BPCSCHIB.JRN);
        CEP.TRC(SCCGWA, BPCSCHIB.CASH_TYP);
        CEP.TRC(SCCGWA, BPCSCHIB.CCY);
        CEP.TRC(SCCGWA, BPCSCHIB.CONF_NO);
        S000_CALL_BPZSCHIB();
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-F-TLR-QRY-BR-CHK";
        SCCCALL.COMMAREA_PTR = BPCFTLCM;
        SCCCALL.ERR_FLDNO = BPB2052_AWA_2052.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void S000_CALL_BPZSCHIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BRW_CHIB, BPCSCHIB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

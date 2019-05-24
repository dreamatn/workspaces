package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3802 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm BPTORGR_RD;
    String CPN_S_BV_ON_WAY_INQ = "BP-S-BV-ON-WAY-INQ ";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
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
    BPCSBVOW BPCSBVOW = new BPCSBVOW();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRGST BPCPRGST = new BPCPRGST();
    SCCGWA SCCGWA;
    BPB3800_AWA_3800 BPB3800_AWA_3800;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3802 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3800_AWA_3800>");
        BPB3800_AWA_3800 = (BPB3800_AWA_3800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            B020_BROWSE_BMOV_RECORD_CN();
        } else {
            B010_CHECK_INPUT();
            B020_BROWSE_BMOV_RECORD();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB3800_AWA_3800.BR;
        S000_CALL_BPZPQORG();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB3800_AWA_3800.BR_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
            if (SCCGWA.COMM_AREA.TL_ID.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(SCCGWA.COMM_AREA.TL_ID);
            S000_ERR_MSG_PROC();
        }
        if (BPB3800_AWA_3800.TLR.trim().length() == 0) {
        } else {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB3800_AWA_3800.TLR;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
                WS_FLD_NO = BPB3800_AWA_3800.TLR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (BPB3800_AWA_3800.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_FOUND1_FLG = 'N';
            WS_FOUND2_FLG = 'N';
            IBS.init(SCCGWA, BPRORGR);
            BPRORGR.KEY.TYP = "09";
            BPRORGR.KEY.BR = BPB3800_AWA_3800.BR;
            BPRORGR.REL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CEP.TRC(SCCGWA, BPRORGR.KEY.BR);
            CEP.TRC(SCCGWA, BPRORGR.REL_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
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
            BPRORGR.KEY.TYP = "09";
            BPRORGR.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRORGR.REL_BR = BPB3800_AWA_3800.BR;
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
    public void B020_BROWSE_BMOV_RECORD_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.BR);
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.TLR);
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.BV_CODE);
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.MOV_STS);
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.INQ_TYP);
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.TYPE);
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.START_DT);
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.END_DT);
        IBS.init(SCCGWA, BPCSBVOW);
        BPCSBVOW.BR = BPB3800_AWA_3800.BR;
        BPCSBVOW.TLR = BPB3800_AWA_3800.TLR;
        BPCSBVOW.CODE = BPB3800_AWA_3800.BV_CODE;
        BPCSBVOW.MOV_STS = BPB3800_AWA_3800.MOV_STS;
        BPCSBVOW.INQ_TYP = BPB3800_AWA_3800.INQ_TYP;
        BPCSBVOW.TYPE = BPB3800_AWA_3800.TYPE;
        BPCSBVOW.START_DT = BPB3800_AWA_3800.START_DT;
        BPCSBVOW.END_DT = BPB3800_AWA_3800.END_DT;
        BPCSBVOW.CONF_NO = BPB3800_AWA_3800.CONF_NO;
        BPCSBVOW.OTH_BR = BPB3800_AWA_3800.OTH_BR;
        BPCSBVOW.FUNC = 'B';
        S000_CALL_BPZSBVOW();
    }
    public void B020_BROWSE_BMOV_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.BR);
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.TLR);
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.BV_CODE);
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.MOV_STS);
        IBS.init(SCCGWA, BPCSBVOW);
        BPCSBVOW.BR = BPB3800_AWA_3800.BR;
        BPCSBVOW.TLR = BPB3800_AWA_3800.TLR;
        BPCSBVOW.CODE = BPB3800_AWA_3800.BV_CODE;
        BPCSBVOW.MOV_STS = BPB3800_AWA_3800.MOV_STS;
        BPCSBVOW.FUNC = 'B';
        S000_CALL_BPZSBVOW();
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = BPB3800_AWA_3800.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            WS_FLD_NO = BPB3800_AWA_3800.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSBVOW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_ON_WAY_INQ, BPCSBVOW);
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

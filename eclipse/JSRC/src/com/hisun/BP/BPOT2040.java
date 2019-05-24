package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2040 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm BPTORGR_BR = new brParm();
    String CPN_S_BRW_CMOV = "BP-S-BRW-CMOV ";
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
    BPCSCMOV BPCSCMOV = new BPCSCMOV();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPB2040_AWA_2040 BPB2040_AWA_2040;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2040 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2040_AWA_2040>");
        BPB2040_AWA_2040 = (BPB2040_AWA_2040) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2040_AWA_2040.BR);
        CEP.TRC(SCCGWA, BPB2040_AWA_2040.CASH_TYP);
        CEP.TRC(SCCGWA, BPB2040_AWA_2040.TLR);
        CEP.TRC(SCCGWA, BPB2040_AWA_2040.MOV_TYP);
        CEP.TRC(SCCGWA, BPB2040_AWA_2040.CCY);
        CEP.TRC(SCCGWA, BPB2040_AWA_2040.MOV_STS);
        CEP.TRC(SCCGWA, BPB2040_AWA_2040.MOV_DT);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_FOR_CN();
        } else {
            B010_CHECK_INPUT();
        }
        B020_BROWSE_RGND_RECORD();
    }
    public void B010_CHECK_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB2040_AWA_2040.BR;
        S000_CALL_BPZPQORG();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB2040_AWA_2040.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB2040_AWA_2040.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            IBS.init(SCCGWA, BPCFTLCM);
            BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCFTLCM.BR = BPB2040_AWA_2040.BR;
            S000_CALL_BPZFTLCM();
            if (BPCFTLCM.AUTH_FLG != 'Y') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NO_AUTH_TO_BR);
            }
        }
        if (BPB2040_AWA_2040.TLR.trim().length() > 0) {
            BPCFTLRQ.INFO.TLR = BPB2040_AWA_2040.TLR;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_EXIST);
            }
            if (BPCFTLRQ.INFO.NEW_BR != BPB2040_AWA_2040.BR) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_BELONG_TO_BR);
            }
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
                if (!SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPB2040_AWA_2040.TLR)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_OLY_QURY_SELF);
                }
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_HAS_NO_AUTH);
            }
        }
        CEP.TRC(SCCGWA, BPB2040_AWA_2040.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (BPB2040_AWA_2040.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_FOUND1_FLG = 'N';
            WS_FOUND2_FLG = 'N';
            IBS.init(SCCGWA, BPRORGR);
            BPRORGR.KEY.TYP = "07";
            BPRORGR.KEY.BR = BPB2040_AWA_2040.BR;
            BPRORGR.REL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPTORGR_BR.rp = new DBParm();
            BPTORGR_BR.rp.TableName = "BPTORGR";
            BPTORGR_BR.rp.where = "TYP = :BPRORGR.KEY.TYP "
                + "AND BR = :BPRORGR.KEY.BR "
                + "AND REL_BR = :BPRORGR.REL_BR";
            IBS.STARTBR(SCCGWA, BPRORGR, this, BPTORGR_BR);
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                IBS.READNEXT(SCCGWA, BPRORGR, this, BPTORGR_BR);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_FOUND1_FLG = 'Y';
                } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                }
            }
            IBS.ENDBR(SCCGWA, BPTORGR_BR);
            IBS.init(SCCGWA, BPRORGR);
            BPRORGR.KEY.TYP = "07";
            BPRORGR.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRORGR.REL_BR = BPB2040_AWA_2040.BR;
            BPTORGR_BR.rp = new DBParm();
            BPTORGR_BR.rp.TableName = "BPTORGR";
            BPTORGR_BR.rp.where = "TYP = :BPRORGR.KEY.TYP "
                + "AND BR = :BPRORGR.KEY.BR "
                + "AND REL_BR = :BPRORGR.REL_BR";
            IBS.STARTBR(SCCGWA, BPRORGR, this, BPTORGR_BR);
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                IBS.READNEXT(SCCGWA, BPRORGR, this, BPTORGR_BR);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_FOUND2_FLG = 'Y';
                } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                }
            }
            IBS.ENDBR(SCCGWA, BPTORGR_BR);
            if (WS_FOUND1_FLG == 'N' 
                && WS_FOUND2_FLG == 'N') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_RLT_BRANCH);
            }
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB2040_AWA_2040.BR;
        S000_CALL_BPZPQORG();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB2040_AWA_2040.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB2040_AWA_2040.TLR.trim().length() == 0) {
            CEP.TRC(SCCGWA, "SPACE");
        } else {
            IBS.init(SCCGWA, BPCFTLRQ);
            CEP.TRC(SCCGWA, "111111");
            BPCFTLRQ.INFO.TLR = BPB2040_AWA_2040.TLR;
            S000_CALL_BPZFTLRQ();
            CEP.TRC(SCCGWA, BPCFTLRQ.RC.RC_CODE);
            CEP.TRC(SCCGWA, "2222");
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                WS_FLD_NO = BPB2040_AWA_2040.TLR_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB2040_AWA_2040.BR != BPCFTLRQ.INFO.TLR_BR) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INP_TLR_NOT_INP_BR;
                WS_FLD_NO = BPB2040_AWA_2040.TLR_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_BROWSE_RGND_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCMOV);
        BPCSCMOV.FUNC = 'B';
        BPCSCMOV.BR = BPB2040_AWA_2040.BR;
        BPCSCMOV.TLR = BPB2040_AWA_2040.TLR;
        BPCSCMOV.MOV_TYP = BPB2040_AWA_2040.MOV_TYP;
        BPCSCMOV.CASH_TYP = BPB2040_AWA_2040.CASH_TYP;
        BPCSCMOV.CCY = BPB2040_AWA_2040.CCY;
        BPCSCMOV.MOV_STS = BPB2040_AWA_2040.MOV_STS;
        BPCSCMOV.MOV_DT = BPB2040_AWA_2040.MOV_DT;
        CEP.TRC(SCCGWA, BPCSCMOV.BR);
        CEP.TRC(SCCGWA, BPCSCMOV.CASH_TYP);
        CEP.TRC(SCCGWA, BPCSCMOV.TLR);
        CEP.TRC(SCCGWA, BPCSCMOV.MOV_TYP);
        CEP.TRC(SCCGWA, BPCSCMOV.CCY);
        CEP.TRC(SCCGWA, BPCSCMOV.MOV_STS);
        CEP.TRC(SCCGWA, BPCSCMOV.MOV_DT);
        S000_CALL_BPZSMOVB();
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = BPB2040_AWA_2040.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            WS_FLD_NO = BPB2040_AWA_2040.BR_NO;
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
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-F-TLR-QRY-BR-CHK";
        SCCCALL.COMMAREA_PTR = BPCFTLCM;
        SCCCALL.ERR_FLDNO = BPB2040_AWA_2040.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSMOVB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_BRW_CMOV;
        SCCCALL.COMMAREA_PTR = BPCSCMOV;
        SCCCALL.ERR_FLDNO = BPB2040_AWA_2040.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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

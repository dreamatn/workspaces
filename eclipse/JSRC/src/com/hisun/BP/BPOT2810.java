package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2810 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP111";
    String CPN_S_MAINTN_INVT = "BP-S-MAINTN-INVT    ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_AC_DT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSINVT BPCSINVT = new BPCSINVT();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOINVO BPCOINVO = new BPCOINVO();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPB2810_AWA_2810 BPB2810_AWA_2810;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2810 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2810_AWA_2810>");
        BPB2810_AWA_2810 = (BPB2810_AWA_2810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSINVT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.BR);
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.DT);
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.CB_TYP);
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.FUNC_CD);
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.JRNNO);
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.DT_END);
        if (BPB2810_AWA_2810.FUNC_CD == 'B') {
            B010_CHECK_INPUT();
            B020_CHECK_TLR();
            B030_BROWSE_LIB_INVT();
        }
        if (BPB2810_AWA_2810.FUNC_CD == 'I') {
            B010_CHECK_INPUT_Q();
            B040_QURY_LIB_INVT();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        B010_01_CHECK_DT();
        B010_02_CHECK_BR();
    }
    public void B010_CHECK_INPUT_Q() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.DT);
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.BR);
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.CB_TYP);
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.FUNC_CD);
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.JRNNO);
        if (BPB2810_AWA_2810.DT == 0 
            || BPB2810_AWA_2810.JRNNO == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void B010_01_CHECK_DT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B01001");
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPB2810_AWA_2810.DT != 0) {
            WS_AC_DT = SCCGWA.COMM_AREA.AC_DATE;
            if (BPB2810_AWA_2810.DT > WS_AC_DT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
                WS_FLD_NO = BPB2810_AWA_2810.DT_NO;
                S000_ERR_MSG_PROC();
            } else {
                BPCSINVT.DATE = BPB2810_AWA_2810.DT;
            }
        }
        if (BPB2810_AWA_2810.DT == ' ') {
            BPCSINVT.DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B010_02_CHECK_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B01002");
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (BPB2810_AWA_2810.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            if (BPB2810_AWA_2810.BR != 0) {
                IBS.init(SCCGWA, BPCPRGST);
                BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCPRGST.BR2 = BPB2810_AWA_2810.BR;
                CEP.TRC(SCCGWA, BPCPRGST.BR1);
                CEP.TRC(SCCGWA, BPCPRGST.BR2);
                S000_CALL_BPZPRGST();
                CEP.TRC(SCCGWA, BPCPRGST.FLAG);
                if (BPCPRGST.FLAG == 'Y') {
                    if (BPCPRGST.LVL_RELATION == 'A') {
                        BPCSINVT.BR = BPB2810_AWA_2810.BR;
                        CEP.TRC(SCCGWA, "BRNOTEQUAL");
                        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                        CEP.TRC(SCCGWA, BPB2810_AWA_2810.BR);
                        CEP.TRC(SCCGWA, BPCPRGST.BR1);
                        CEP.TRC(SCCGWA, BPCPRGST.BR2);
                    } else {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_ERROR;
                        WS_FLD_NO = BPB2810_AWA_2810.BR_NO;
                        S000_ERR_MSG_PROC();
                    }
                } else {
                    CEP.TRC(SCCGWA, "NOT WRONG");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_ERROR;
                    WS_FLD_NO = BPB2810_AWA_2810.BR_NO;
                    S000_ERR_MSG_PROC();
                }
            } else {
                BPCSINVT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CEP.TRC(SCCGWA, "AWABRZERO");
                CEP.TRC(SCCGWA, BPCSINVT.BR);
            }
        } else {
            BPCSINVT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        CEP.TRC(SCCGWA, BPB2810_AWA_2810.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPCSINVT.BR);
    }
    public void B020_CHECK_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        CEP.TRC(SCCGWA, "B020CHECK");
        CEP.TRC(SCCGWA, BPCFTLRQ.RC);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            S000_ERR_MSG_PROC();
        }
        if (BPB2810_AWA_2810.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCFTLCM.BR = BPB2810_AWA_2810.BR;
            S000_CALL_BPZFTLCM();
            if (BPCFTLCM.AUTH_FLG != 'Y') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NO_AUTH_TO_BR);
            }
        }
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-QRY-BR-CHK", BPCFTLCM);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void B030_BROWSE_LIB_INVT() throws IOException,SQLException,Exception {
        BPCSINVT.FUNC = 'B';
        BPCSINVT.BR = BPB2810_AWA_2810.BR;
        BPCSINVT.DATE = BPB2810_AWA_2810.DT;
        BPCSINVT.DATE_END = BPB2810_AWA_2810.DT_END;
        BPCSINVT.CB_TYP = BPB2810_AWA_2810.CB_TYP;
        CEP.TRC(SCCGWA, "SINVT-FUNC");
        CEP.TRC(SCCGWA, BPCSINVT.BR);
        CEP.TRC(SCCGWA, BPCSINVT.DATE);
        S000_CALL_BPZSINVT();
    }
    public void B040_QURY_LIB_INVT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSINVT);
        BPCSINVT.FUNC = 'I';
        BPCSINVT.DATE = BPB2810_AWA_2810.DT;
        BPCSINVT.JRNNO = BPB2810_AWA_2810.JRNNO;
        BPCSINVT.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCSINVT.DATE);
        CEP.TRC(SCCGWA, BPCSINVT.JRNNO);
        S000_CALL_BPZSINVT();
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = BPB2810_AWA_2810.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            WS_FLD_NO = BPB2810_AWA_2810.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSINVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MAINTN_INVT, BPCSINVT);
        if (BPCSINVT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSINVT.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "SINVT-RC-CODE");
        CEP.TRC(SCCGWA, BPCSINVT.RC.RC_CODE);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
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

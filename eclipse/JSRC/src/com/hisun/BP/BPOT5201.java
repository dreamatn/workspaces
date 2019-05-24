package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5201 {
    String CPN_INTR_INQ = "BP-S-INTR-INQ     ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    short WS_SPACE_NO = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    char WS_COMPLETE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSINTI BPCSINTI = new BPCSINTI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPB5201_AWA_5201 BPB5201_AWA_5201;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5201 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5201_AWA_5201>");
        BPB5201_AWA_5201 = (BPB5201_AWA_5201) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5201_AWA_5201.DT);
        CEP.TRC(SCCGWA, BPB5201_AWA_5201.BR);
        for (WS_I = 1; BPB5201_AWA_5201.CCY_A[WS_I-1].CCY.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, BPB5201_AWA_5201.CCY_A[WS_I-1].CCY);
            CEP.TRC(SCCGWA, BPB5201_AWA_5201.BASE_A[WS_I-1].BASE_TYP);
            CEP.TRC(SCCGWA, BPB5201_AWA_5201.TENOR_A[WS_I-1].TENOR);
        }
        if (BPB5201_AWA_5201.DT != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_DATE_NO_VALID;
            WS_FLD_NO = BPB5201_AWA_5201.DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB5201_AWA_5201.BR == ' ' 
            || BPB5201_AWA_5201.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            WS_FLD_NO = BPB5201_AWA_5201.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            WS_BR = BPB5201_AWA_5201.BR;
            R000_CHECK_BRANCH();
        }
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (BPB5201_AWA_5201.CCY_A[WS_I-1].CCY.trim().length() == 0 
                && BPB5201_AWA_5201.BASE_A[WS_I-1].BASE_TYP.trim().length() == 0 
                && (BPB5201_AWA_5201.TENOR_A[WS_I-1].TENOR.equalsIgnoreCase("0") 
                || BPB5201_AWA_5201.TENOR_A[WS_I-1].TENOR.trim().length() == 0)) {
                WS_COMPLETE_FLG = 'Y';
                WS_SPACE_NO = WS_I;
            } else {
                if (BPB5201_AWA_5201.CCY_A[WS_I-1].CCY.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_CCY_NO_VALID;
                    WS_FLD_NO = BPB5201_AWA_5201.CCY_A[WS_I-1].CCY_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                } else {
                    WS_CCY = BPB5201_AWA_5201.CCY_A[WS_I-1].CCY;
                    R000_CHECK_CCY();
                }
                if (BPB5201_AWA_5201.BASE_A[WS_I-1].BASE_TYP.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BASETYP_NO_VALID;
                    WS_FLD_NO = BPB5201_AWA_5201.BASE_A[WS_I-1].BASE_TYP_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                } else {
                    WS_BASE_TYP = BPB5201_AWA_5201.BASE_A[WS_I-1].BASE_TYP;
                    R000_CHECK_BASE_TYP();
                }
                if (BPB5201_AWA_5201.TENOR_A[WS_I-1].TENOR.equalsIgnoreCase("0") 
                    || BPB5201_AWA_5201.TENOR_A[WS_I-1].TENOR.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_TENOR_NO_VALID;
                    WS_FLD_NO = BPB5201_AWA_5201.TENOR_A[WS_I-1].TENOR_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                } else {
                    WS_TENOR = BPB5201_AWA_5201.TENOR_A[WS_I-1].TENOR;
                    R000_CHECK_TENOR();
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSINTI);
        BPCSINTI.DT = BPB5201_AWA_5201.DT;
        BPCSINTI.BR = BPB5201_AWA_5201.BR;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            BPCSINTI.INTR_TBL[WS_I-1].CCY = BPB5201_AWA_5201.CCY_A[WS_I-1].CCY;
            BPCSINTI.INTR_TBL[WS_I-1].BASE_TYP = BPB5201_AWA_5201.BASE_A[WS_I-1].BASE_TYP;
            BPCSINTI.INTR_TBL[WS_I-1].TENOR = BPB5201_AWA_5201.TENOR_A[WS_I-1].TENOR;
        }
        S000_CALL_BPZSINTI();
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB5201_AWA_5201.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            WS_FLD_NO = BPB5201_AWA_5201.CCY_A[WS_I-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void R000_CHECK_BASE_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RBASE;
        BPCOQPCD.INPUT_DATA.CODE = WS_BASE_TYP;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            WS_FLD_NO = BPB5201_AWA_5201.BASE_A[WS_I-1].BASE_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void R000_CHECK_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RTENO;
        BPCOQPCD.INPUT_DATA.CODE = WS_TENOR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            WS_FLD_NO = BPB5201_AWA_5201.TENOR_A[WS_I-1].TENOR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZSINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INTR_INQ, BPCSINTI);
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

package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5214 {
    String BP_S_CUR_IRATE_QUE = "BP-S-CUR-IRATE-QUE";
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
    BPCSCURQ BPCSCURQ = new BPCSCURQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPB5214_AWA_5214 BPB5214_AWA_5214;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5214 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5214_AWA_5214>");
        BPB5214_AWA_5214 = (BPB5214_AWA_5214) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5214_AWA_5214.BR);
        CEP.TRC(SCCGWA, BPB5214_AWA_5214.CCY);
        CEP.TRC(SCCGWA, BPB5214_AWA_5214.BASE_TYP);
        CEP.TRC(SCCGWA, BPB5214_AWA_5214.TENOR);
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5214_AWA_5214.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.HQT_BANK);
        if (BPB5214_AWA_5214.BR == ' ' 
            || BPB5214_AWA_5214.BR == 0) {
            BPB5214_AWA_5214.BR = SCCGWA.COMM_AREA.HQT_BANK;
        } else {
            if (BPB5214_AWA_5214.BR == 999999) {
            } else {
                WS_BR = BPB5214_AWA_5214.BR;
                R000_CHECK_BRANCH();
            }
        }
        CEP.TRC(SCCGWA, BPB5214_AWA_5214.CCY);
        if (BPB5214_AWA_5214.CCY.trim().length() > 0) {
            WS_CCY = BPB5214_AWA_5214.CCY;
            R000_CHECK_CCY();
        }
        CEP.TRC(SCCGWA, BPB5214_AWA_5214.BASE_TYP);
        if (BPB5214_AWA_5214.BASE_TYP.trim().length() > 0) {
            WS_BASE_TYP = BPB5214_AWA_5214.BASE_TYP;
            R000_CHECK_BASE_TYP();
        }
        CEP.TRC(SCCGWA, BPB5214_AWA_5214.TENOR);
        if (BPB5214_AWA_5214.TENOR.trim().length() > 0) {
            WS_TENOR = BPB5214_AWA_5214.TENOR;
            R000_CHECK_TENOR();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B200HERE");
        IBS.init(SCCGWA, BPCSCURQ);
        CEP.TRC(SCCGWA, BPB5214_AWA_5214.BR);
        CEP.TRC(SCCGWA, BPB5214_AWA_5214.CCY);
        CEP.TRC(SCCGWA, BPB5214_AWA_5214.BASE_TYP);
        CEP.TRC(SCCGWA, BPB5214_AWA_5214.TENOR);
        BPCSCURQ.DATA.BR = BPB5214_AWA_5214.BR;
        BPCSCURQ.DATA.CCY = BPB5214_AWA_5214.CCY;
        BPCSCURQ.DATA.BASE_TYP = BPB5214_AWA_5214.BASE_TYP;
        BPCSCURQ.DATA.TENOR = BPB5214_AWA_5214.TENOR;
        S000_CALL_BPZSCURQ();
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB5214_AWA_5214.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            WS_FLD_NO = BPB5214_AWA_5214.CCY_NO;
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
            WS_FLD_NO = BPB5214_AWA_5214.BASE_TYP_NO;
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
            WS_FLD_NO = BPB5214_AWA_5214.BASE_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZSCURQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_S_CUR_IRATE_QUE, BPCSCURQ);
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

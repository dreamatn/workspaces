package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8111 {
    DBParm LNTSETL_RD;
    String FMT_ID = "LN888";
    LNOT8111_WS_VARIABLES WS_VARIABLES = new LNOT8111_WS_VARIABLES();
    LNOT8111_WS_OUT_RECODE WS_OUT_RECODE = new LNOT8111_WS_OUT_RECODE();
    LNOT8111_WS_DB_VARS WS_DB_VARS = new LNOT8111_WS_DB_VARS();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRTRAN LNRTRAN = new LNRTRAN();
    CICCUST CICCUST = new CICCUST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    LNRSETL LNRSETL = new LNRSETL();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRRATN LNRRATN = new LNRRATN();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    LNCRCVDM LNCRCVDM = new LNCRCVDM();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    SCCGWA SCCGWA;
    LNB8111_AWA_8111 AWA_8111;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8111 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_8111 = new LNB8111_AWA_8111();
        IBS.init(SCCGWA, AWA_8111);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_8111);
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_OUT_RECODE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AWA_8111.LOAN_AC);
        if (AWA_8111.LOAN_AC.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_GET_PAY_INFO();
        B000_OUTPUT();
    }
    public void B000_GET_PAY_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = AWA_8111.BR;
        WS_OUT_RECODE.WS_OUT_DATA.O_TR_BR = AWA_8111.BR;
        CEP.TRC(SCCGWA, AWA_8111.BR);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA.O_TR_BR);
        S000_CALL_BPZPQORG();
        WS_OUT_RECODE.WS_OUT_DATA.O_TR_BR_NM = BPCPQORG.CHN_NM;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA.O_TR_BR_NM);
        R000_GET_D_CNM();
        if (LNRSETL.AC.trim().length() > 0) {
            WS_OUT_RECODE.WS_OUT_DATA.O_D_CNM = CICCUST.O_DATA.O_CI_NM;
        }
        IBS.init(SCCGWA, LNRAGRE);
        IBS.init(SCCGWA, LNCRAGRE);
        LNCRAGRE.FUNC = 'I';
        LNRAGRE.KEY.CONTRACT_NO = AWA_8111.LOAN_AC;
        S000_CALL_LNZRAGRE();
        WS_OUT_RECODE.WS_OUT_DATA.O_PAPER_NO = LNRAGRE.PAPER_NO;
        WS_OUT_RECODE.WS_OUT_DATA.O_DRAW_NO = LNRAGRE.DRAW_NO;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = LNRAGRE.PAPER_NO;
        S000_CALL_CIZCUST();
        WS_OUT_RECODE.WS_OUT_DATA.O_C_CNM = CICCUST.O_DATA.O_CI_NM;
        if (CICCUST.O_DATA.O_CI_TYP == '1') {
            WS_OUT_RECODE.WS_OUT_DATA.O_FLG = '1';
        } else {
            WS_OUT_RECODE.WS_OUT_DATA.O_FLG = '0';
        }
        R000_GEN_PROD_INFO();
        WS_OUT_RECODE.WS_OUT_DATA.O_PROD_CNM = WS_VARIABLES.PROD_CNM;
        LNCSCKPD.FUNC = '0';
        LNCSCKPD.PROD_CD = LNRCONT.PROD_CD;
        S000_CALL_LNZSCKPD();
        WS_OUT_RECODE.WS_OUT_DATA.O_PROD_CNM = LNCSCKPD.PROD_NM;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA.O_TR_BR);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA.O_TR_BR_NM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA.O_PAPER_NO);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA.O_DRAW_NO);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA.O_C_CNM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA.O_D_CNM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA.O_PROD_CNM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA.O_FLG);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_GET_D_CNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = AWA_8111.LOAN_AC;
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = 'C' "
            + "AND SETTLE_TYPE = '4'";
        LNTSETL_RD.fst = true;
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRSETL.AC);
        if (LNRSETL.AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = LNRSETL.AC;
            S000_CALL_CIZCUST();
        }
    }
    public void S000_CALL_LNZRAGRE() throws IOException,SQLException,Exception {
        LNCRAGRE.REC_PTR = LNRAGRE;
        LNCRAGRE.REC_LEN = 203;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTAGRE", LNCRAGRE);
        if (LNCRAGRE.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRAGRE.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST, true);
    }
    public void R000_GEN_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = AWA_8111.LOAN_AC;
        S000_CALL_LNZRCONT();
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B000_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = FMT_ID;
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 819;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

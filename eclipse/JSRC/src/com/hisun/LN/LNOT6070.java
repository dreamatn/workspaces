package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT6070 {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    LNOT6070_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT6070_WS_TEMP_VARIABLE();
    LNOT6070_WS_MSG_INFO WS_MSG_INFO = new LNOT6070_WS_MSG_INFO();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCSWRTF LNCSWRTF = new LNCSWRTF();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCIPART LNCIPART = new LNCIPART();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNRCONT LNRCONT = new LNRCONT();
    SCCGWA SCCGWA;
    LNB6070_AWA_6070 LNB6070_AWA_6070;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT6070 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB6070_AWA_6070>");
        LNB6070_AWA_6070 = (LNB6070_AWA_6070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_SVR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB6070_AWA_6070.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNB6070_AWA_6070.RECS_FLG == ' ') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_RECS_FLG_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNB6070_AWA_6070.WROF_TOT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_WROF_TOT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNB6070_AWA_6070.WROF_TP == ' ') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_WROF_TP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, LNCSSTBL);
            LNCSSTBL.CON_NO = LNB6070_AWA_6070.CTA_NO;
            LNCSSTBL.TR_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
            JIBS_tmp_int = LNCSSTBL.TR_CODE.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) LNCSSTBL.TR_CODE = "0" + LNCSSTBL.TR_CODE;
            S000_CALL_LNZSSTBL();
        }
        IBS.init(SCCGWA, LNCPCFTA);
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB6070_AWA_6070.CTA_NO;
        T000_READ_LNTCONT();
        LNCPCFTA.BR_GP[1-1].BR = LNRCONT.BOOK_BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        S000_CALL_LNZPCFTA();
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        if (LNCPCFTA.FTA_FLG == 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FTA_NOT_SAME;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSWRTF);
        LNCSWRTF.CTA_NO = LNB6070_AWA_6070.CTA_NO;
        LNCSWRTF.DOMI_BR = LNB6070_AWA_6070.DOMI_BR;
        LNCSWRTF.VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
        LNCSWRTF.WROF_TP = LNB6070_AWA_6070.WROF_TP;
        LNCSWRTF.CI_NO = LNB6070_AWA_6070.CI_NO;
        LNCSWRTF.CI_CNM = LNB6070_AWA_6070.CI_CNM;
        LNCSWRTF.PROD_CD = LNB6070_AWA_6070.PROD_CD;
        LNCSWRTF.CCY = LNB6070_AWA_6070.CCY;
        LNCSWRTF.DUE_DT = LNB6070_AWA_6070.DUE_DT;
        LNCSWRTF.PRIN_AMT = LNB6070_AWA_6070.PRIN_AMT;
        LNCSWRTF.BAL_AMT = LNB6070_AWA_6070.BAL_AMT;
        LNCSWRTF.LN_STS = LNB6070_AWA_6070.LN_STS;
        LNCSWRTF.TOT_AMTS.WRF_NP_AMT = LNB6070_AWA_6070.WOF_P_N;
        LNCSWRTF.TOT_AMTS.WRF_OP_AMT = LNB6070_AWA_6070.WOF_P_O;
        LNCSWRTF.TOT_AMTS.WRF_NI_AMT = LNB6070_AWA_6070.WOF_I_N;
        LNCSWRTF.TOT_AMTS.WRF_OI_AMT = LNB6070_AWA_6070.WOF_I_O;
        LNCSWRTF.TOT_AMTS.WRF_O_AMT = LNB6070_AWA_6070.WOF_O;
        LNCSWRTF.TOT_AMTS.WRF_L_AMT = LNB6070_AWA_6070.WOF_L;
        LNCSWRTF.ATTR_FLG = LNB6070_AWA_6070.RECS_FLG;
        LNCSWRTF.TOT_AMTS.WROF_TOT = LNB6070_AWA_6070.WROF_TOT;
        LNCSWRTF.RMK1 = LNB6070_AWA_6070.RMK_L1;
        S000_CALL_LNZSWRTF();
    }
    public void S000_CALL_LNZSWRTF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-LN-WRT-OFF", LNCSWRTF);
        if (LNCSWRTF.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSWRTF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

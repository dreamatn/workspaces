package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT4050 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    LNOT4050_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT4050_WS_TEMP_VARIABLE();
    LNOT4050_WS_MSG_INFO WS_MSG_INFO = new LNOT4050_WS_MSG_INFO();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCSWRTF LNCSWRTF = new LNCSWRTF();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCIPART LNCIPART = new LNCIPART();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    SCCGWA SCCGWA;
    LNB4050_AWA_4050 LNB4050_AWA_4050;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT4050 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB4050_AWA_4050>");
        LNB4050_AWA_4050 = (LNB4050_AWA_4050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_SVR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB4050_AWA_4050.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNB4050_AWA_4050.RECS_FLG == ' ') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_RECS_FLG_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNB4050_AWA_4050.WROF_TOT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_WROF_TOT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNB4050_AWA_4050.WROF_TP == ' ') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_WROF_TP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, LNCSSTBL);
            LNCSSTBL.CON_NO = LNB4050_AWA_4050.CTA_NO;
            LNCSSTBL.TR_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
            JIBS_tmp_int = LNCSSTBL.TR_CODE.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) LNCSSTBL.TR_CODE = "0" + LNCSSTBL.TR_CODE;
            S000_CALL_LNZSSTBL();
        }
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSWRTF);
        LNCSWRTF.CTA_NO = LNB4050_AWA_4050.CTA_NO;
        LNCSWRTF.DOMI_BR = LNB4050_AWA_4050.DOMI_BR;
        LNCSWRTF.VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
        LNCSWRTF.WROF_TP = LNB4050_AWA_4050.WROF_TP;
        LNCSWRTF.CI_NO = LNB4050_AWA_4050.CI_NO;
        LNCSWRTF.CI_CNM = LNB4050_AWA_4050.CI_CNM;
        LNCSWRTF.PROD_CD = LNB4050_AWA_4050.PROD_CD;
        LNCSWRTF.CCY = LNB4050_AWA_4050.CCY;
        LNCSWRTF.DUE_DT = LNB4050_AWA_4050.DUE_DT;
        LNCSWRTF.PRIN_AMT = LNB4050_AWA_4050.PRIN_AMT;
        LNCSWRTF.BAL_AMT = LNB4050_AWA_4050.BAL_AMT;
        LNCSWRTF.LN_STS = LNB4050_AWA_4050.LN_STS;
        LNCSWRTF.TOT_AMTS.WRF_NP_AMT = LNB4050_AWA_4050.WOF_P_N;
        LNCSWRTF.TOT_AMTS.WRF_OP_AMT = LNB4050_AWA_4050.WOF_P_O;
        LNCSWRTF.TOT_AMTS.WRF_NI_AMT = LNB4050_AWA_4050.WOF_I_N;
        LNCSWRTF.TOT_AMTS.WRF_OI_AMT = LNB4050_AWA_4050.WOF_I_O;
        LNCSWRTF.TOT_AMTS.WRF_O_AMT = LNB4050_AWA_4050.WOF_O;
        LNCSWRTF.TOT_AMTS.WRF_L_AMT = LNB4050_AWA_4050.WOF_L;
        LNCSWRTF.ATTR_FLG = LNB4050_AWA_4050.RECS_FLG;
        LNCSWRTF.TOT_AMTS.WROF_TOT = LNB4050_AWA_4050.WROF_TOT;
        LNCSWRTF.RMK1 = LNB4050_AWA_4050.RMK_L1;
        S000_CALL_LNZSWRTF();
    }
    public void S000_CALL_LNZSWRTF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-LN-WRT-OFF", LNCSWRTF);
        if (LNCSWRTF.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSWRTF.RC);
            S000_ERR_MSG_PROC();
        }
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

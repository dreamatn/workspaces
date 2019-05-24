package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT4063 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    LNOT4063_WS_ERR_MSG WS_ERR_MSG = new LNOT4063_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    double WS_AMT_CNT = 0;
    double WS_AMT_CNT1 = 0;
    LNOT4063_WS_OUTPUT_LIST WS_OUTPUT_LIST = new LNOT4063_WS_OUTPUT_LIST();
    char WS_CNT_PLPI = ' ';
    char WS_ADJ_FLG = ' ';
    LNOT4063_WS_OUT_PUT WS_OUT_PUT = new LNOT4063_WS_OUT_PUT();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSINTA LNCSINTA = new LNCSINTA();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCICUT LNCICUT = new LNCICUT();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    LNB4063_AWA_4063 LNB4063_AWA_4063;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT4063 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB4063_AWA_4063>");
        LNB4063_AWA_4063 = (LNB4063_AWA_4063) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_BAS_INFO();
        B030_OUTPUT_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB4063_AWA_4063.CTA_NO);
        CEP.TRC(SCCGWA, LNB4063_AWA_4063.ADJ_AMT);
        CEP.TRC(SCCGWA, LNB4063_AWA_4063.CTA_NO);
        CEP.TRC(SCCGWA, LNB4063_AWA_4063.CTA_NO);
        CEP.TRC(SCCGWA, LNB4063_AWA_4063.CTA_NO);
        CEP.TRC(SCCGWA, LNB4063_AWA_4063.CTA_NO);
        CEP.TRC(SCCGWA, "AAAAAA");
        if (LNB4063_AWA_4063.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT, WS_ERR_MSG);
            WS_FLD_NO = LNB4063_AWA_4063.CTA_NO_NO;
            S000_ERR_MSG_PROC();
        }
        if (LNB4063_AWA_4063.ADJ_AMT == 0 
            && LNB4063_AWA_4063.AMT == 0 
            && LNB4063_AWA_4063.T_L_INT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_1678, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CON_NO = LNB4063_AWA_4063.CTA_NO;
        LNCSSTBL.TR_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = LNCSSTBL.TR_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCSSTBL.TR_CODE = "0" + LNCSSTBL.TR_CODE;
        S000_CALL_LNZSSTBL();
    }
    public void B020_BROWSE_BAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNB4063_AWA_4063.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (LNB4063_AWA_4063.ADJ_AMT != 0) {
            B022_ADJ_INT_PROC();
        }
        if (LNB4063_AWA_4063.AMT != 0) {
            WS_ADJ_FLG = '2';
            B021_ADJ_AMT_PROC();
        }
        if (LNB4063_AWA_4063.T_L_INT != 0) {
            WS_ADJ_FLG = '3';
            B021_ADJ_AMT_PROC();
        }
    }
    public void B021_ADJ_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB4063_AWA_4063.PAY_TYP);
        IBS.init(SCCGWA, LNCSINTA);
        LNCSINTA.FUNC = 'O';
        LNCSINTA.CTA_NO = LNB4063_AWA_4063.CTA_NO;
        LNCSINTA.CI_NO = LNB4063_AWA_4063.CI_NO;
        LNCSINTA.CI_ENMS = LNB4063_AWA_4063.CI_ENMS;
        LNCSINTA.CI_CNM = LNB4063_AWA_4063.CI_CNM;
        LNCSINTA.PRIN = LNB4063_AWA_4063.PRIN;
        LNCSINTA.OSBAL = LNB4063_AWA_4063.OSBAL;
        LNCSINTA.CCY = LNB4063_AWA_4063.CCY;
        LNCSINTA.STS = LNB4063_AWA_4063.STS;
        if (WS_ADJ_FLG == '2') {
            if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
                LNCSINTA.PAY_TYP = 'C';
            } else {
                LNCSINTA.PAY_TYP = 'P';
            }
            LNCSINTA.LC_TYP = 'O';
            LNCSINTA.ADJ_AMT = LNB4063_AWA_4063.AMT;
        }
        if (WS_ADJ_FLG == '3') {
            if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
                LNCSINTA.PAY_TYP = 'C';
            } else {
                LNCSINTA.PAY_TYP = LNB4063_AWA_4063.PAY_TYP;
            }
            LNCSINTA.LC_TYP = 'L';
            LNCSINTA.ADJ_L_AMT = LNB4063_AWA_4063.T_L_INT;
        }
        LNCSINTA.PAY_TERM = LNB4063_AWA_4063.PAY_TERM;
        LNCSINTA.VAL_DT = LNB4063_AWA_4063.VAL_DT;
        LNCSINTA.DUE_DT = LNB4063_AWA_4063.DUE_DT;
        LNCSINTA.INT = LNB4063_AWA_4063.INT;
        LNCSINTA.OVE_AMTS = LNB4063_AWA_4063.OVE_AMTS;
        LNCSINTA.TOT_INT = LNB4063_AWA_4063.TOT_INT;
        LNCSINTA.LVE_AMTS = LNB4063_AWA_4063.LVE_AMTS;
        LNCSINTA.RSN = LNB4063_AWA_4063.RSN;
        S000_CALL_LNZSINTA();
        C020_MOVE_RECORDS();
    }
    public void B022_ADJ_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSINTA);
        LNCSINTA.FUNC = 'M';
        LNCSINTA.CTA_NO = LNB4063_AWA_4063.CTA_NO;
        LNCSINTA.CI_NO = LNB4063_AWA_4063.CI_NO;
        LNCSINTA.CI_ENMS = LNB4063_AWA_4063.CI_ENMS;
        LNCSINTA.CI_CNM = LNB4063_AWA_4063.CI_CNM;
        LNCSINTA.PRIN = LNB4063_AWA_4063.PRIN;
        LNCSINTA.OSBAL = LNB4063_AWA_4063.OSBAL;
        LNCSINTA.CCY = LNB4063_AWA_4063.CCY;
        LNCSINTA.STS = LNB4063_AWA_4063.STS;
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            LNCSINTA.PAY_TYP = 'C';
        } else {
            LNCSINTA.PAY_TYP = 'I';
        }
        LNCSINTA.PAY_TERM = LNB4063_AWA_4063.ADJ_PTEM;
        LNCSINTA.VAL_DT = LNB4063_AWA_4063.ADJ_VDT;
        LNCSINTA.DUE_DT = LNB4063_AWA_4063.ADJ_DDT;
        LNCSINTA.INT = LNB4063_AWA_4063.ADJ_INT;
        LNCSINTA.ADJ_AMT = LNB4063_AWA_4063.ADJ_AMT;
        LNCSINTA.ADJ_AMTS = WS_AMT_CNT;
        LNCSINTA.RSN = LNB4063_AWA_4063.ADJ_RSN;
        S000_CALL_LNZSINTA();
        C010_MOVE_ADJ_RECORDS();
        WS_ADJ_FLG = '1';
    }
    public void B030_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LNP63";
        SCCFMT.DATA_PTR = WS_OUT_PUT;
        SCCFMT.DATA_LEN = 812;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void C010_MOVE_ADJ_RECORDS() throws IOException,SQLException,Exception {
        WS_OUT_PUT.WS_CTA_NO = LNCSINTA.CTA_NO;
        WS_OUT_PUT.WS_CI_NO = LNCSINTA.CI_NO;
        WS_OUT_PUT.WS_CI_ENMS = LNCSINTA.CI_ENMS;
        WS_OUT_PUT.WS_CI_CNM = LNCSINTA.CI_CNM;
        WS_OUT_PUT.WS_PRIN = LNCSINTA.PRIN;
        WS_OUT_PUT.WS_OSBAL = LNCSINTA.OSBAL;
        WS_OUT_PUT.WS_CCY = LNCSINTA.CCY;
        WS_OUT_PUT.WS_STS = LNCSINTA.STS;
        WS_OUT_PUT.WS_ADJ_PTYP = LNCSINTA.PAY_TYP;
        WS_OUT_PUT.WS_ADJ_PTEM = LNCSINTA.PAY_TERM;
        WS_OUT_PUT.WS_ADJ_VDT = LNCSINTA.VAL_DT;
        WS_OUT_PUT.WS_ADJ_DDT = LNCSINTA.DUE_DT;
        WS_OUT_PUT.WS_ADJ_INT = LNCSINTA.INT;
        WS_OUT_PUT.WS_ADJ_AMT = LNCSINTA.ADJ_AMT;
        WS_OUT_PUT.WS_ADJ_AMTS = LNCSINTA.ADJ_AMTS;
        WS_OUT_PUT.WS_ADJ_RSN = LNB4063_AWA_4063.ADJ_RSN;
        CEP.TRC(SCCGWA, LNCSINTA.CTA_NO);
        CEP.TRC(SCCGWA, LNCSINTA.CI_NO);
        CEP.TRC(SCCGWA, LNCSINTA.CI_ENMS);
        CEP.TRC(SCCGWA, LNCSINTA.CI_CNM);
        CEP.TRC(SCCGWA, LNCSINTA.PRIN);
        CEP.TRC(SCCGWA, LNCSINTA.OSBAL);
        CEP.TRC(SCCGWA, LNCSINTA.CCY);
        CEP.TRC(SCCGWA, LNCSINTA.STS);
        CEP.TRC(SCCGWA, LNCSINTA.PAY_TYP);
        CEP.TRC(SCCGWA, LNCSINTA.PAY_TERM);
        CEP.TRC(SCCGWA, LNCSINTA.VAL_DT);
        CEP.TRC(SCCGWA, LNCSINTA.DUE_DT);
        CEP.TRC(SCCGWA, LNCSINTA.INT);
        CEP.TRC(SCCGWA, LNCSINTA.ADJ_AMT);
        CEP.TRC(SCCGWA, LNCSINTA.ADJ_AMTS);
        CEP.TRC(SCCGWA, LNB4063_AWA_4063.ADJ_RSN);
    }
    public void C020_MOVE_RECORDS() throws IOException,SQLException,Exception {
        WS_OUT_PUT.WS_CTA_NO = LNCSINTA.CTA_NO;
        WS_OUT_PUT.WS_CI_NO = LNCSINTA.CI_NO;
        WS_OUT_PUT.WS_CI_ENMS = LNCSINTA.CI_ENMS;
        WS_OUT_PUT.WS_CI_CNM = LNCSINTA.CI_CNM;
        WS_OUT_PUT.WS_PRIN = LNCSINTA.PRIN;
        WS_OUT_PUT.WS_OSBAL = LNCSINTA.OSBAL;
        WS_OUT_PUT.WS_CCY = LNCSINTA.CCY;
        WS_OUT_PUT.WS_STS = LNCSINTA.STS;
        WS_OUT_PUT.WS_PAY_TYP = LNB4063_AWA_4063.PAY_TYP;
        WS_OUT_PUT.WS_PAY_TERM = LNB4063_AWA_4063.PAY_TERM;
        WS_OUT_PUT.WS_VAL_DT = LNCSINTA.VAL_DT;
        WS_OUT_PUT.WS_DUE_DT = LNCSINTA.DUE_DT;
        WS_OUT_PUT.WS_INT = LNCSINTA.PRIN;
        WS_OUT_PUT.WS_OVE_AMTS = LNB4063_AWA_4063.OVE_AMTS;
        WS_OUT_PUT.WS_AMT = LNB4063_AWA_4063.AMT;
        WS_OUT_PUT.WS_LVE_AMTS = LNB4063_AWA_4063.LVE_AMTS;
        WS_OUT_PUT.WS_TOT_INT = WS_OUT_PUT.WS_AMT + WS_OUT_PUT.WS_OVE_AMTS;
        WS_OUT_PUT.WS_T_L_INT = LNB4063_AWA_4063.OVE_AMTS + LNB4063_AWA_4063.ADJ_AMT;
        WS_OUT_PUT.WS_RSN = LNB4063_AWA_4063.RSN;
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_PAY_TYP);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_PAY_TERM);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_VAL_DT);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_DUE_DT);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_INT);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_OVE_AMTS);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_AMT);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_LVE_AMTS);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_TOT_INT);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_T_L_INT);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_A_L_AMT);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_RSN);
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
    }
    public void S000_CALL_LNZSINTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-ADJ-INTA", LNCSINTA);
    }
    public void S000_CALL_LNZRPLPI() throws IOException,SQLException,Exception {
        WS_CNT_PLPI = ' ';
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        if (LNCRPLPI.RETURN_INFO != 'F') {
            if (LNCRPLPI.RETURN_INFO == 'E' 
                || LNCRPLPI.RETURN_INFO == 'N') {
                WS_CNT_PLPI = 'Y';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICUT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, LNCAPRDM.RC.RC_RTNCODE+"", WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

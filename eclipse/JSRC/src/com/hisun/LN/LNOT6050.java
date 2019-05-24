package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT6050 {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTICTL_RD;
    DBParm LNTRCVD_RD;
    boolean pgmRtn = false;
    LNOT6050_WS_ERR_MSG WS_ERR_MSG = new LNOT6050_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    double WS_AMT_CNT = 0;
    double WS_AMT_CNT1 = 0;
    LNOT6050_WS_OUTPUT_LIST WS_OUTPUT_LIST = new LNOT6050_WS_OUTPUT_LIST();
    char WS_CNT_PLPI = ' ';
    char WS_ADJ_FLG = ' ';
    LNOT6050_WS_OUT_PUT WS_OUT_PUT = new LNOT6050_WS_OUT_PUT();
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
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNRCONT LNRCONT = new LNRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    LNRRCVD LNRRCVD = new LNRRCVD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    LNB6050_AWA_6050 LNB6050_AWA_6050;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT6050 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB6050_AWA_6050>");
        LNB6050_AWA_6050 = (LNB6050_AWA_6050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_BAS_INFO();
        if (pgmRtn) return;
        B030_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB6050_AWA_6050.CTA_NO);
        CEP.TRC(SCCGWA, LNB6050_AWA_6050.ADJ_AMT);
        CEP.TRC(SCCGWA, LNB6050_AWA_6050.CTA_NO);
        CEP.TRC(SCCGWA, LNB6050_AWA_6050.CTA_NO);
        CEP.TRC(SCCGWA, LNB6050_AWA_6050.CTA_NO);
        CEP.TRC(SCCGWA, LNB6050_AWA_6050.CTA_NO);
        CEP.TRC(SCCGWA, "AAAAAA");
        if (LNB6050_AWA_6050.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT, WS_ERR_MSG);
            WS_FLD_NO = LNB6050_AWA_6050.CTA_NO_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6050_AWA_6050.ADJ_AMT == 0 
            && LNB6050_AWA_6050.AMT == 0 
            && LNB6050_AWA_6050.T_L_INT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_1678, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6050_AWA_6050.AMT != 0 
            && LNB6050_AWA_6050.PAY_TYP == 'I') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_INPUT_NO_MATCH, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6050_AWA_6050.T_L_INT != 0 
            && LNB6050_AWA_6050.PAY_TYP == 'P') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_INPUT_NO_MATCH, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB6050_AWA_6050.CTA_NO;
        T000_READ_CONT_PROC();
        if (pgmRtn) return;
        LNCPCFTA.BR_GP[1-1].BR = LNRCONT.DOMI_BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        if (LNCPCFTA.FTA_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NO_TRAN_FTA_NOT_SAME, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CON_NO = LNB6050_AWA_6050.CTA_NO;
        LNCSSTBL.TR_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = LNCSSTBL.TR_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCSSTBL.TR_CODE = "0" + LNCSSTBL.TR_CODE;
        S000_CALL_LNZSSTBL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNB6050_AWA_6050.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNB6050_AWA_6050.CTA_NO;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNB6050_AWA_6050.AMT);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNB6050_AWA_6050.CTA_NO;
            LNRRCVD.KEY.AMT_TYP = LNB6050_AWA_6050.ADJ_PTYP;
            LNRRCVD.KEY.TERM = LNB6050_AWA_6050.ADJ_PTEM;
            CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
            CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
            if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
                LNRRCVD.KEY.AMT_TYP = 'C';
            }
            T000_READ_LNTRCVD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNB6050_AWA_6050.ADJ_AMT);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
                && LNB6050_AWA_6050.ADJ_AMT != 0 
                && LNRRCVD.DUE_DT != SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_IO_ADJ, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1") 
                && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CD_ADJ, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNB6050_AWA_6050.CTA_NO;
        LNRRCVD.KEY.AMT_TYP = LNB6050_AWA_6050.PAY_TYP;
        LNRRCVD.KEY.TERM = LNB6050_AWA_6050.PAY_TERM;
        CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
        CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.PAY_MTH);
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            LNRRCVD.KEY.AMT_TYP = 'C';
        }
        T000_READ_LNTRCVD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNB6050_AWA_6050.ADJ_AMT);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1") 
            && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CD_ADJ, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_BAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNB6050_AWA_6050.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        if (LNB6050_AWA_6050.ADJ_AMT != 0) {
            B022_ADJ_INT_PROC();
            if (pgmRtn) return;
        }
        if (LNB6050_AWA_6050.AMT != 0) {
            WS_ADJ_FLG = '2';
            B021_ADJ_AMT_PROC();
            if (pgmRtn) return;
        }
        if (LNB6050_AWA_6050.T_L_INT != 0) {
            WS_ADJ_FLG = '3';
            B021_ADJ_AMT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_ADJ_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB6050_AWA_6050.PAY_TYP);
        IBS.init(SCCGWA, LNCSINTA);
        LNCSINTA.FUNC = 'O';
        LNCSINTA.CTA_NO = LNB6050_AWA_6050.CTA_NO;
        LNCSINTA.CI_NO = LNB6050_AWA_6050.CI_NO;
        LNCSINTA.CI_ENMS = LNB6050_AWA_6050.CI_ENMS;
        LNCSINTA.CI_CNM = LNB6050_AWA_6050.CI_CNM;
        LNCSINTA.PRIN = LNB6050_AWA_6050.PRIN;
        LNCSINTA.OSBAL = LNB6050_AWA_6050.OSBAL;
        LNCSINTA.CCY = LNB6050_AWA_6050.CCY;
        LNCSINTA.STS = LNB6050_AWA_6050.STS;
        if (WS_ADJ_FLG == '2') {
            if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
                LNCSINTA.PAY_TYP = 'C';
            } else {
                LNCSINTA.PAY_TYP = 'P';
            }
            LNCSINTA.LC_TYP = 'O';
            LNCSINTA.ADJ_AMT = LNB6050_AWA_6050.AMT;
        }
        if (WS_ADJ_FLG == '3') {
            if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
                LNCSINTA.PAY_TYP = 'C';
            } else {
                LNCSINTA.PAY_TYP = LNB6050_AWA_6050.PAY_TYP;
            }
            LNCSINTA.LC_TYP = 'L';
            LNCSINTA.ADJ_L_AMT = LNB6050_AWA_6050.T_L_INT;
        }
        LNCSINTA.PAY_TERM = LNB6050_AWA_6050.PAY_TERM;
        LNCSINTA.VAL_DT = LNB6050_AWA_6050.VAL_DT;
        LNCSINTA.DUE_DT = LNB6050_AWA_6050.DUE_DT;
        LNCSINTA.INT = LNB6050_AWA_6050.INT;
        LNCSINTA.OVE_AMTS = LNB6050_AWA_6050.OVE_AMTS;
        LNCSINTA.TOT_INT = LNB6050_AWA_6050.TOT_INT;
        LNCSINTA.LVE_AMTS = LNB6050_AWA_6050.LVE_AMTS;
        LNCSINTA.RSN = LNB6050_AWA_6050.RSN;
        S000_CALL_LNZSINTA();
        if (pgmRtn) return;
        C020_MOVE_RECORDS();
        if (pgmRtn) return;
    }
    public void B022_ADJ_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSINTA);
        LNCSINTA.FUNC = 'M';
        LNCSINTA.CTA_NO = LNB6050_AWA_6050.CTA_NO;
        LNCSINTA.CI_NO = LNB6050_AWA_6050.CI_NO;
        LNCSINTA.CI_ENMS = LNB6050_AWA_6050.CI_ENMS;
        LNCSINTA.CI_CNM = LNB6050_AWA_6050.CI_CNM;
        LNCSINTA.PRIN = LNB6050_AWA_6050.PRIN;
        LNCSINTA.OSBAL = LNB6050_AWA_6050.OSBAL;
        LNCSINTA.CCY = LNB6050_AWA_6050.CCY;
        LNCSINTA.STS = LNB6050_AWA_6050.STS;
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            LNCSINTA.PAY_TYP = 'C';
        } else {
            LNCSINTA.PAY_TYP = 'I';
        }
        LNCSINTA.PAY_TERM = LNB6050_AWA_6050.ADJ_PTEM;
        LNCSINTA.VAL_DT = LNB6050_AWA_6050.ADJ_VDT;
        LNCSINTA.DUE_DT = LNB6050_AWA_6050.ADJ_DDT;
        LNCSINTA.INT = LNB6050_AWA_6050.ADJ_INT;
        LNCSINTA.ADJ_AMT = LNB6050_AWA_6050.ADJ_AMT;
        LNCSINTA.ADJ_AMTS = WS_AMT_CNT;
        LNCSINTA.RSN = LNB6050_AWA_6050.ADJ_RSN;
        S000_CALL_LNZSINTA();
        if (pgmRtn) return;
        C010_MOVE_ADJ_RECORDS();
        if (pgmRtn) return;
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
        WS_OUT_PUT.WS_ADJ_RSN = LNB6050_AWA_6050.ADJ_RSN;
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
        CEP.TRC(SCCGWA, LNB6050_AWA_6050.ADJ_RSN);
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
        WS_OUT_PUT.WS_PAY_TYP = LNB6050_AWA_6050.PAY_TYP;
        WS_OUT_PUT.WS_PAY_TERM = LNB6050_AWA_6050.PAY_TERM;
        WS_OUT_PUT.WS_VAL_DT = LNCSINTA.VAL_DT;
        WS_OUT_PUT.WS_DUE_DT = LNCSINTA.DUE_DT;
        WS_OUT_PUT.WS_INT = LNCSINTA.PRIN;
        WS_OUT_PUT.WS_OVE_AMTS = LNB6050_AWA_6050.OVE_AMTS;
        WS_OUT_PUT.WS_AMT = LNB6050_AWA_6050.AMT;
        WS_OUT_PUT.WS_LVE_AMTS = LNB6050_AWA_6050.LVE_AMTS;
        WS_OUT_PUT.WS_TOT_INT = WS_OUT_PUT.WS_AMT + WS_OUT_PUT.WS_OVE_AMTS;
        WS_OUT_PUT.WS_T_L_INT = LNB6050_AWA_6050.OVE_AMTS + LNB6050_AWA_6050.ADJ_AMT;
        WS_OUT_PUT.WS_RSN = LNB6050_AWA_6050.RSN;
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
    public void T000_READ_CONT_PROC() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NOTFND, WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
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
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICUT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, LNCAPRDM.RC.RC_RTNCODE+"", WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.where = "CONTRACT_NO = :LNRICTL.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.where = "CONTRACT_NO = :LNRRCVD.KEY.CONTRACT_NO "
            + "AND AMT_TYP = :LNRRCVD.KEY.AMT_TYP "
            + "AND TERM = :LNRRCVD.KEY.TERM";
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}

package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT6030 {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    DBParm LNTICTL_RD;
    DBParm LNTRCVD_RD;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_AMT_CNT = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSINTA LNCSINTA = new LNCSINTA();
    LNC603 LNC603 = new LNC603();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNRCONT LNRCONT = new LNRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    LNB6030_AWA_6030 LNB6030_AWA_6030;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT6030 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB6030_AWA_6030>");
        LNB6030_AWA_6030 = (LNB6030_AWA_6030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.FUNC);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.CTA_NO);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.CI_NO);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.CI_ENMS);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.CI_CNM);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.PRIN);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.OSBAL);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.CCY);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.STS);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.PAY_TYP);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.PAY_TERM);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.VAL_DT);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.DUE_DT);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.INT);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.ADJ_AMT);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.ADJ_AMTS);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.RSN);
        if (LNB6030_AWA_6030.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            WS_FLD_NO = LNB6030_AWA_6030.CTA_NO_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, " ");
        if (LNB6030_AWA_6030.PAY_TERM == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB6030_AWA_6030.PAY_TERM_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, " ");
        if (LNB6030_AWA_6030.FUNC == 'I') {
        } else {
            if (LNB6030_AWA_6030.PAY_TYP != ' ' 
                && LNB6030_AWA_6030.PAY_TYP != 'I' 
                && LNB6030_AWA_6030.PAY_TYP != 'C') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAY_MTH;
                WS_FLD_NO = LNB6030_AWA_6030.PAY_TYP_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, "AWA-PAY-TYP OK");
        if (LNB6030_AWA_6030.VAL_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_DATE_THEN;
            WS_FLD_NO = LNB6030_AWA_6030.VAL_DT_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, " ");
        CEP.TRC(SCCGWA, WS_AMT_CNT);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.INT);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.ADJ_AMT);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.INT);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.ADJ_AMT);
        WS_AMT_CNT = LNB6030_AWA_6030.INT + LNB6030_AWA_6030.ADJ_AMT;
        CEP.TRC(SCCGWA, " ");
        CEP.TRC(SCCGWA, WS_AMT_CNT);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.ADJ_AMTS);
        if (LNB6030_AWA_6030.ADJ_AMTS != 0 
            && WS_AMT_CNT != LNB6030_AWA_6030.ADJ_AMTS) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.ERR_NOT_EQUAL;
            WS_FLD_NO = LNB6030_AWA_6030.ADJ_AMT_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB6030_AWA_6030.CTA_NO;
        T000_READ_CONT_PROC();
        LNCPCFTA.BR_GP[1-1].BR = LNRCONT.DOMI_BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        S000_CALL_LNZPCFTA();
        if (LNCPCFTA.FTA_FLG == 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_NO_TRAN_FTA_NOT_SAME;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CON_NO = LNB6030_AWA_6030.CTA_NO;
        LNCSSTBL.TR_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = LNCSSTBL.TR_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCSSTBL.TR_CODE = "0" + LNCSSTBL.TR_CODE;
        S000_CALL_LNZSSTBL();
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNB6030_AWA_6030.CTA_NO;
        S000_CALL_LNZAPRDM();
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNB6030_AWA_6030.CTA_NO;
        T000_READ_LNTICTL();
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNB6030_AWA_6030.CTA_NO;
            LNRRCVD.KEY.AMT_TYP = LNB6030_AWA_6030.PAY_TYP;
            LNRRCVD.KEY.TERM = LNB6030_AWA_6030.PAY_TERM;
            CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
            CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
            CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.PAY_MTH);
            if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
                LNRRCVD.KEY.AMT_TYP = 'C';
            }
            T000_READ_LNTRCVD();
            CEP.TRC(SCCGWA, LNB6030_AWA_6030.ADJ_AMT);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
                && LNB6030_AWA_6030.ADJ_AMT != 0 
                && LNRRCVD.DUE_DT != SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_IO_ADJ;
                S000_ERR_MSG_PROC();
            }
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1") 
                && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CD_ADJ;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNB6030_AWA_6030.CTA_NO;
        LNRRCVD.KEY.AMT_TYP = LNB6030_AWA_6030.PAY_TYP;
        LNRRCVD.KEY.TERM = LNB6030_AWA_6030.PAY_TERM;
        CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
        CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.PAY_MTH);
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            LNRRCVD.KEY.AMT_TYP = 'C';
        }
        T000_READ_LNTRCVD();
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.ADJ_AMT);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1") 
            && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CD_ADJ;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_BROWSE_BAS_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.FUNC);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.CTA_NO);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.PAY_TYP);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.PAY_TERM);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.VAL_DT);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.DUE_DT);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.ADJ_AMT);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.ADJ_AMTS);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.RSN);
        CEP.TRC(SCCGWA, LNB6030_AWA_6030.SEQ);
        IBS.init(SCCGWA, LNCSINTA);
        LNCSINTA.FUNC = LNB6030_AWA_6030.FUNC;
        LNCSINTA.CTA_NO = LNB6030_AWA_6030.CTA_NO;
        LNCSINTA.CI_NO = LNB6030_AWA_6030.CI_NO;
        LNCSINTA.CI_ENMS = LNB6030_AWA_6030.CI_ENMS;
        LNCSINTA.CI_CNM = LNB6030_AWA_6030.CI_CNM;
        LNCSINTA.PRIN = LNB6030_AWA_6030.PRIN;
        LNCSINTA.OSBAL = LNB6030_AWA_6030.OSBAL;
        LNCSINTA.CCY = LNB6030_AWA_6030.CCY;
        LNCSINTA.STS = LNB6030_AWA_6030.STS;
        if (LNB6030_AWA_6030.PAY_TYP != ' ') {
            LNCSINTA.PAY_TYP = LNB6030_AWA_6030.PAY_TYP;
        } else {
            LNCSINTA.PAY_TYP = 'I';
        }
        LNCSINTA.PAY_TERM = LNB6030_AWA_6030.PAY_TERM;
        LNCSINTA.VAL_DT = LNB6030_AWA_6030.VAL_DT;
        LNCSINTA.DUE_DT = LNB6030_AWA_6030.DUE_DT;
        LNCSINTA.INT = LNB6030_AWA_6030.INT;
        LNCSINTA.ADJ_AMT = LNB6030_AWA_6030.ADJ_AMT;
        LNCSINTA.ADJ_AMTS = WS_AMT_CNT;
        LNCSINTA.RSN = LNB6030_AWA_6030.RSN;
        S000_CALL_LNZSINTA();
    }
    public void B030_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNC603);
        IBS.init(SCCGWA, SCCFMT);
        LNC603.CTA_NO = LNCSINTA.CTA_NO;
        LNC603.CI_NO = LNCSINTA.CI_NO;
        LNC603.CI_ENMS = LNCSINTA.CI_ENMS;
        LNC603.CI_CNM = LNCSINTA.CI_CNM;
        LNC603.PRIN = LNCSINTA.PRIN;
        LNC603.OSBAL = LNCSINTA.OSBAL;
        LNC603.CCY = LNCSINTA.CCY;
        LNC603.STS = LNCSINTA.STS;
        LNC603.PAY_TYP = LNCSINTA.PAY_TYP;
        LNC603.PAY_TERM = LNCSINTA.PAY_TERM;
        LNC603.VAL_DT = LNCSINTA.VAL_DT;
        LNC603.DUE_DT = LNCSINTA.DUE_DT;
        LNC603.INT = LNCSINTA.INT;
        LNC603.OVE_AMTS = LNCSINTA.OVE_AMTS;
        LNC603.LVE_AMTS = LNCSINTA.LVE_AMTS;
        LNC603.ADJ_AMT = LNCSINTA.ADJ_AMT;
        if (LNB6030_AWA_6030.RSN.trim().length() > 0) {
            LNC603.RSN = LNB6030_AWA_6030.RSN;
        } else {
            LNC603.RSN = " ";
        }
        LNC603.D_PRIN = LNCSINTA.D_PRIN;
        LNC603.D_INT = LNCSINTA.D_INT;
        LNC603.TOT_INT = LNCSINTA.TOT_INT;
        LNC603.ADJ_AMTS = LNCSINTA.ADJ_AMTS;
        LNC603.TOT_L_INT = LNCSINTA.TOT_L_INT;
        CEP.TRC(SCCGWA, LNC603.CTA_NO);
        CEP.TRC(SCCGWA, LNC603.CI_NO);
        CEP.TRC(SCCGWA, LNC603.CI_ENMS);
        CEP.TRC(SCCGWA, LNC603.CI_CNM);
        CEP.TRC(SCCGWA, LNC603.PRIN);
        CEP.TRC(SCCGWA, LNC603.OSBAL);
        CEP.TRC(SCCGWA, LNC603.CCY);
        CEP.TRC(SCCGWA, LNC603.STS);
        CEP.TRC(SCCGWA, LNC603.PAY_TYP);
        CEP.TRC(SCCGWA, LNC603.PAY_TERM);
        CEP.TRC(SCCGWA, LNC603.VAL_DT);
        CEP.TRC(SCCGWA, LNC603.DUE_DT);
        CEP.TRC(SCCGWA, LNC603.INT);
        CEP.TRC(SCCGWA, LNC603.OVE_AMTS);
        CEP.TRC(SCCGWA, LNC603.LVE_AMTS);
        CEP.TRC(SCCGWA, LNC603.TOT_INT);
        CEP.TRC(SCCGWA, LNC603.ADJ_AMTS);
        CEP.TRC(SCCGWA, LNC603.TOT_L_INT);
        CEP.TRC(SCCGWA, LNC603.RSN);
        CEP.TRC(SCCGWA, LNCSINTA.INT);
        CEP.TRC(SCCGWA, LNCSINTA.VAL_DT);
        CEP.TRC(SCCGWA, LNCSINTA.DUE_DT);
        CEP.TRC(SCCGWA, LNC603.ADJ_AMT);
        SCCFMT.FMTID = "LN603";
        SCCFMT.DATA_PTR = LNC603;
        SCCFMT.DATA_LEN = 660;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZSINTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-ADJ-INTA", LNCSINTA);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void T000_READ_CONT_PROC() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NOTFND;
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
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = "" + LNCAPRDM.RC.RC_RTNCODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
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

package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT6040 {
    int JIBS_tmp_int;
    DBParm LNTICTL_RD;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_AMT_CNT = 0;
    double WS_AMT_CNT1 = 0;
    LNOT6040_WS_OUTPUT_LIST WS_OUTPUT_LIST = new LNOT6040_WS_OUTPUT_LIST();
    char WS_CNT_PLPI = ' ';
    char WS_PAY_TYP = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSINTA LNCSINTA = new LNCSINTA();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCICUT LNCICUT = new LNCICUT();
    LNCP80 LNCP80 = new LNCP80();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    LNRICTL LNRICTL = new LNRICTL();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    LNB6040_AWA_6040 LNB6040_AWA_6040;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT6040 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB6040_AWA_6040>");
        LNB6040_AWA_6040 = (LNB6040_AWA_6040) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_BAS_INFO();
        B050_GET_INFO();
        B030_OUTPUT_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "AAAAAA");
        if (LNB6040_AWA_6040.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            WS_FLD_NO = LNB6040_AWA_6040.CTA_NO_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "BAAAAA");
        CEP.TRC(SCCGWA, "CAAAAA");
        CEP.TRC(SCCGWA, LNB6040_AWA_6040.PAY_TERM);
        if (LNB6040_AWA_6040.PAY_TERM == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_WRD_PLS_CNG_PLPI;
            WS_FLD_NO = LNB6040_AWA_6040.PAY_TERM_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "DAAAAA");
        CEP.TRC(SCCGWA, LNB6040_AWA_6040.OVE_AMTS);
        CEP.TRC(SCCGWA, LNB6040_AWA_6040.ADJ_AMT);
        WS_AMT_CNT = LNB6040_AWA_6040.OVE_AMTS + LNB6040_AWA_6040.ADJ_AMT;
        if (WS_AMT_CNT < 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NOT_LESS_ZERO;
            WS_FLD_NO = LNB6040_AWA_6040.ADJ_AMT_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "EAAAAA");
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CON_NO = LNB6040_AWA_6040.CTA_NO;
        S000_CALL_LNZSSTBL();
    }
    public void B020_BROWSE_BAS_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB6040_AWA_6040.FUNC);
        CEP.TRC(SCCGWA, LNB6040_AWA_6040.CTA_NO);
        CEP.TRC(SCCGWA, LNB6040_AWA_6040.PAY_TYP);
        CEP.TRC(SCCGWA, LNB6040_AWA_6040.PAY_TERM);
        CEP.TRC(SCCGWA, LNB6040_AWA_6040.VAL_DT);
        CEP.TRC(SCCGWA, LNB6040_AWA_6040.DUE_DT);
        CEP.TRC(SCCGWA, LNB6040_AWA_6040.ADJ_AMT);
        CEP.TRC(SCCGWA, LNB6040_AWA_6040.OVE_AMTS);
        CEP.TRC(SCCGWA, LNB6040_AWA_6040.RSN);
        IBS.init(SCCGWA, LNCSINTA);
        LNCSINTA.FUNC = LNB6040_AWA_6040.FUNC;
        LNCSINTA.CTA_NO = LNB6040_AWA_6040.CTA_NO;
        LNCSINTA.CI_NO = LNB6040_AWA_6040.CI_NO;
        LNCSINTA.CI_ENMS = LNB6040_AWA_6040.CI_ENMS;
        LNCSINTA.CI_CNM = LNB6040_AWA_6040.CI_CNM;
        LNCSINTA.PRIN = LNB6040_AWA_6040.PRIN;
        LNCSINTA.OSBAL = LNB6040_AWA_6040.OSBAL;
        LNCSINTA.CCY = LNB6040_AWA_6040.CCY;
        LNCSINTA.STS = LNB6040_AWA_6040.STS;
        LNCSINTA.PAY_TYP = LNB6040_AWA_6040.PAY_TYP;
        if (LNB6040_AWA_6040.PAY_TYP == 'P') {
            LNCSINTA.LC_TYP = 'O';
        }
        if (LNB6040_AWA_6040.PAY_TYP == 'I') {
            LNCSINTA.LC_TYP = 'L';
        }
        LNCSINTA.PAY_TERM = LNB6040_AWA_6040.PAY_TERM;
        LNCSINTA.VAL_DT = LNB6040_AWA_6040.VAL_DT;
        LNCSINTA.DUE_DT = LNB6040_AWA_6040.DUE_DT;
        LNCSINTA.INT = LNB6040_AWA_6040.INT;
        LNCSINTA.OVE_AMTS = LNB6040_AWA_6040.OVE_AMTS;
        LNCSINTA.TOT_INT = LNB6040_AWA_6040.TOT_INT;
        LNCSINTA.ADJ_AMT = LNB6040_AWA_6040.ADJ_AMT;
        LNCSINTA.TOT_INT = LNB6040_AWA_6040.OVE_AMTS + LNB6040_AWA_6040.ADJ_AMT;
        LNCSINTA.LVE_AMTS = LNB6040_AWA_6040.LVE_AMTS;
        LNCSINTA.ADJ_L_AMT = LNB6040_AWA_6040.T_L_INT;
        LNCSINTA.TOT_L_INT = LNB6040_AWA_6040.LVE_AMTS + LNB6040_AWA_6040.T_L_INT;
        LNCSINTA.RSN = LNB6040_AWA_6040.RSN;
        S000_CALL_LNZSINTA();
        CEP.TRC(SCCGWA, LNCSINTA.ADJ_AMTS);
        CEP.TRC(SCCGWA, LNCSINTA.ADJ_L_AMTS);
    }
    public void B050_GET_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCP80);
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNB6040_AWA_6040.CTA_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        LNCP80.CI_NO = LNCCLNQ.DATA.CI_NO;
        LNCP80.CI_ENMS = LNCCLNQ.DATA.CI_SNAME;
        LNCP80.CI_CNM = LNCCLNQ.DATA.CI_CNAME;
        LNCP80.CCY = LNCCLNQ.DATA.CCY;
        LNCP80.PRIN = LNCCLNQ.DATA.PRIN;
        LNCP80.OSBAL = LNCCLNQ.DATA.TOT_BAL;
        LNCP80.STS = LNCCLNQ.DATA.STS;
        if (LNB6040_AWA_6040.FUNC != 'Q') {
            IBS.init(SCCGWA, LNRPLPI);
            IBS.init(SCCGWA, LNCRPLPI);
            LNRPLPI.KEY.CONTRACT_NO = LNB6040_AWA_6040.CTA_NO;
            LNRPLPI.KEY.SUB_CTA_NO = 0;
            LNRPLPI.KEY.REPY_MTH = LNB6040_AWA_6040.PAY_TYP;
            IBS.init(SCCGWA, LNCAPRDM);
            LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNB6040_AWA_6040.CTA_NO;
            LNCAPRDM.FUNC = '3';
            S000_CALL_LNZAPRDM();
            if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
                LNRPLPI.KEY.REPY_MTH = 'C';
            }
            LNRPLPI.KEY.TERM = LNB6040_AWA_6040.PAY_TERM;
            LNCRPLPI.FUNC = 'I';
            CEP.TRC(SCCGWA, LNRPLPI.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
            CEP.TRC(SCCGWA, LNRPLPI.KEY.SUB_CTA_NO);
            CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
            S000_CALL_LNZRPLPI();
            if (WS_CNT_PLPI == 'Y') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
                S000_ERR_MSG_PROC();
            }
            LNCP80.VAL_DT = LNRPLPI.VAL_DT;
            LNCP80.DUE_DT = LNRPLPI.DUE_DT;
            C000_CALL_LNZICUT();
            LNCP80.INT = LNCICUT.COMM_DATA.INT_AMT;
            CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
            CEP.TRC(SCCGWA, LNCP80.INT);
            if (WS_OUTPUT_LIST.WS_CNT_PAY1 != 'Y') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_WRD_PLS_CNG_PLPI;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        LNCP80.CTA_NO = LNB6040_AWA_6040.CTA_NO;
        LNCP80.CI_CNM = LNB6040_AWA_6040.CI_CNM;
        LNCP80.PAY_TYP = LNB6040_AWA_6040.PAY_TYP;
        LNCP80.PAY_TERM = LNB6040_AWA_6040.PAY_TERM;
        LNCP80.LVE_AMTS = LNCSINTA.LVE_AMTS;
        LNCP80.OVE_AMTS = LNCSINTA.OVE_AMTS;
        LNCP80.ADJ_AMT = LNB6040_AWA_6040.ADJ_AMT;
        LNCP80.TOT_INT = LNCSINTA.ADJ_AMTS;
        LNCP80.ADJ_L_AMT = LNB6040_AWA_6040.T_L_INT;
        LNCP80.TOT_L_INT = LNCSINTA.ADJ_L_AMTS;
        LNCP80.RSN = LNB6040_AWA_6040.RSN;
        SCCFMT.FMTID = "LNP80";
        SCCFMT.DATA_PTR = LNCP80;
        SCCFMT.DATA_LEN = 626;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_PROCESS_DIARY() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
    }
    public void S000_CALL_LNZSINTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-ADJ-INTA", LNCSINTA);
    }
    public void C000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.LN_AC = LNB6040_AWA_6040.CTA_NO;
        LNCICUT.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCICUT.COMM_DATA.SUF_NO.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNCICUT.COMM_DATA.SUF_NO = "0" + LNCICUT.COMM_DATA.SUF_NO;
        LNCICUT.COMM_DATA.BEG_DATE = LNRPLPI.VAL_DT;
        LNCICUT.COMM_DATA.END_DATE = LNRPLPI.DUE_DT;
        LNCICUT.COMM_DATA.TERM = LNB6040_AWA_6040.PAY_TERM;
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.TYPE = 'I';
        S000_CALL_LNZICUT();
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
        WS_OUTPUT_LIST.WS_CNT_PAY1 = 'Y';
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            S000_ERR_MSG_PROC();
        }
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
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICUT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.where = "CONTRACT_NO = :LNRICTL.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
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
